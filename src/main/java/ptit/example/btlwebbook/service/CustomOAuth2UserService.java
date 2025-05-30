package ptit.example.btlwebbook.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.example.btlwebbook.model.Avatar;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.repository.UserRepository;
import ptit.example.btlwebbook.utils.ROLE;
import ptit.example.btlwebbook.utils.UserStatus;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            log.info("Starting OAuth2 user loading process");
            log.info("Client registration: {}", userRequest.getClientRegistration());
            
            OAuth2User oAuth2User = super.loadUser(userRequest);
            String provider = userRequest.getClientRegistration().getRegistrationId();
            String email;
            String name;
            String picture;
            String serviceId;
            
            log.info("Loading user {}", oAuth2User.getName());
            log.info("Provider: {}", provider);
            
            if ("google".equals(provider)) {
                log.info("Loading Google User");
                OidcUser oidcUser = (OidcUser) oAuth2User;
                email = oidcUser.getEmail();
                name = oidcUser.getFullName();
                log.info("Email: {}", email);
                picture = oidcUser.getPicture();
                serviceId = oidcUser.getSubject();
            } else {
                log.info("Loading other OAuth2 provider");
                Map<String, Object> attributes = oAuth2User.getAttributes();
                email = (String) attributes.get("email");
                name = (String) attributes.get("name");
                picture = (String) attributes.get("picture");
                serviceId = (String) attributes.get("id");
            }
            
            log.info("Looking up user in database with email: {}", email);
            Optional<User> user = userRepository.findByEmail(email);
            
            User processedUser;
            if(user.isEmpty()) {
                log.info("User not found, creating new user");
                processedUser = createNewUser(email, name, provider, picture, serviceId);
            } else {
                log.info("User found in database, updating information");
                processedUser = updateExistingUser(user.get(), name, email, picture);
            }
            
            return new CustomOauth2User(processedUser, oAuth2User.getAttributes());
            
        } catch (Exception e) {
            log.error("Error in OAuth2 user loading process", e);
            throw e;
        }
    }
    
    private User createNewUser(String email, String name, String provider, String picture, String serviceId) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFullName(name);
        newUser.setService(provider);
        newUser.setRole(ROLE.USER);
        newUser.setStatus(UserStatus.ACTIVE);
        
        Avatar avatar = newUser.getAvatar();
        avatar.setUrl(picture);
        newUser.setServiceId(serviceId);
        
        log.info("Saving new user to database");
        newUser = userRepository.save(newUser);
        log.info("New user created successfully with ID: {}", newUser.getId());
        
        return newUser;
    }
    
    private User updateExistingUser(User user, String name, String email, String picture) {
        user.setFullName(name);
        user.setEmail(email);
        
        Avatar avatar = user.getAvatar() != null ? user.getAvatar() : new Avatar();
        avatar.setUrl(picture);
        user.setAvatar(avatar);
        
        log.info("Saving updated user to database");
        user = userRepository.save(user);
        log.info("User updated successfully with ID: {}", user.getId());
        
        return user;
    }
}
