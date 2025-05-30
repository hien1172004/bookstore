package ptit.example.btlwebbook.service;

import org.springframework.security.core.userdetails.UserDetails;
import ptit.example.btlwebbook.utils.TokenType;

public interface JwtService {
    String generateToken(UserDetails user);

    String extractUsername(String token, TokenType type);

    boolean isValid(String token, TokenType type, UserDetails userDetails);

    String generateRefreshToken(UserDetails user);

    String generateResetToken(UserDetails userDetails);

    String generateVerificationToken(UserDetails userDetails);

}
