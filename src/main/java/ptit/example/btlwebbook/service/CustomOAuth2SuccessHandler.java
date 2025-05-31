package ptit.example.btlwebbook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.example.btlwebbook.dto.response.TokenResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.model.Avatar;
import ptit.example.btlwebbook.model.RedisToken;
import ptit.example.btlwebbook.model.Token;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.repository.RedisTokenRepository;
import ptit.example.btlwebbook.repository.TokenRepository;
import ptit.example.btlwebbook.repository.UserRepository;
import ptit.example.btlwebbook.utils.ROLE;
import ptit.example.btlwebbook.utils.UserStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;
//    private final TokenRepository tokenRepository;

    private final RedisTokenService  redisTokenService;
    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
        try {
            log.info("Starting OAuth2 success handler");
            
            // Kiểm tra loại principal
            String email;
            String fullname;
            String image;
            String phone;
            Object principal = authentication.getPrincipal();
            log.info("Authentication principal type: {}", principal);

            if (principal instanceof CustomOauth2User customOauth2User) {
                image = "";
                phone = "";
                fullname = "";
                email = customOauth2User.getEmail();
                log.info("Processing CustomOauth2User with email: {}", email);
            } else if (principal instanceof OidcUser oidcUser) {
                email = oidcUser.getEmail();
                image = oidcUser.getPicture();
                fullname = oidcUser.getGivenName();
                phone = oidcUser.getPhoneNumber();
                log.info("Processing Google user with email: {}", phone);
            } else {
                image = "";
                phone = "";
                fullname = "";
                if (principal instanceof OAuth2User oAuth2User) {
                    Map<String, Object> attributes = oAuth2User.getAttributes();
                    email = (String) attributes.get("email");
                    log.info("Processing OAuth2 user with email: {}", email);
                } else {
                    email = "";
                }
            }
            // Tìm hoặc tạo người dùng
            User user = userRepository.findByEmail(email).orElseGet(() -> {
                log.info("Creating new user with email: {}", email);
                User newUser = User.builder()
                        .email(email)
                        .fullName(fullname)
                        .phoneNumber(phone)
                        .status(UserStatus.ACTIVE)
                        .role(ROLE.USER)
                        .build();
                Avatar avatar = new Avatar();
                avatar.setUrl(image);
                newUser.setAvatar(avatar);
                return newUser;
            });

            // Cập nhật thông tin nếu người dùng đã tồn tại
            if (user.getId() != null) {
                log.info("Updating existing user with email: {}", email);
                user.setFullName(fullname);
                user.setPhoneNumber(phone);
                if (!image.isEmpty()) {
                    Avatar avatar = user.getAvatar() != null ? user.getAvatar() : new Avatar();
                    avatar.setUrl(image);
                    user.setAvatar(avatar);
                }
            }
            userRepository.save(user);
            log.info("Looking up user in database with email: {}", user);
            log.info("Looking up user in database with email: {}", email);
            // Tạo JWT
            String token = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            log.info("Generated JWT tokens for user: {}", user.getId());

            // Lưu token
            RedisToken savedToken = RedisToken.builder()
                    .accessToken(token)
                    .refreshToken(refreshToken)
                    .id(email)
                    .build();
            redisTokenService.save(savedToken);
            log.info("Saved tokens to database for user: {}", user.getId());

            // Redirect về frontend
            String redirectUrl = String.format(
                    "http://localhost:3000/oauth2/redirect?token=%s&refreshToken=%s",
                    token, refreshToken
            );
            log.info("Redirecting to frontend: {}", redirectUrl);
            response.sendRedirect(redirectUrl);
            
        } catch (ResourceNotFoundException e) {
            log.error("User not found error: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("User not found: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error in OAuth2 success handler", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Internal server error: " + e.getMessage());
        }
    }
}

