//package ptit.example.btlwebbook.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ptit.example.btlwebbook.dto.response.TokenResponse;
//import ptit.example.btlwebbook.model.Token;
//import ptit.example.btlwebbook.model.User;
//import ptit.example.btlwebbook.service.JwtService;
//import ptit.example.btlwebbook.service.TokenService;
//import ptit.example.btlwebbook.service.UserService;
//@RestController
//@Slf4j
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class Oauth2Controller {
//    private final JwtService jwtService;
//    private final UserService userService;
//    private final TokenService tokenService;
//
//    @GetMapping("/google")
//    public ResponseEntity<TokenResponse> getGoogleToken(@AuthenticationPrincipal OAuth2AuthenticationToken authentication) {
//        // Kiểm tra provider là Google
//        log.info("--------------google--------");
//        if (authentication.getAuthorizedClientRegistrationId().equals("google")) {
//            String email = authentication.getName();
//            log.info("email {}", email);// Lấy thông tin người dùng từ Google
//            User user = userService.getByEmail(email);
//            String accessToken = jwtService.generateToken(user);
//            String refreshToken = jwtService.generateRefreshToken(user);
//            Token token = Token.builder()
//                    .accessToken(accessToken)
//                    .refreshToken(refreshToken)
//                    .build();// Sinh JWT token cho người dùng
//            tokenService.save(token);
//            TokenResponse tokenResponse = TokenResponse.builder()
//                    .accessToken(accessToken)
//                    .refreshToken(refreshToken)
//                    .userId(user.getId())
//                    .build();
//            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//}
//
