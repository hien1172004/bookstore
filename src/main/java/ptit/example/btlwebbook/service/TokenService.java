package ptit.example.btlwebbook.service;



import org.springframework.stereotype.Service;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.model.Token;
import ptit.example.btlwebbook.repository.TokenRepository;

import java.util.Optional;

@Service
public record TokenService(TokenRepository tokenRepository) {
    public long save(Token token){
        Optional<Token> optional = tokenRepository.findByEmail(token.getEmail());
        if(optional.isEmpty()){
            tokenRepository.save(token);
            return token.getId();
        } else {
            Token currentToken = optional.get();
            currentToken.setAccessToken(token.getAccessToken());
            currentToken.setRefreshToken(token.getRefreshToken());
            tokenRepository.save(currentToken);
            return currentToken.getId();
        }
    }
    public void delete(String email) {
        Token token = getByEmail(email);
        tokenRepository.delete(token);
    }

    public Token getByEmail(String email){
        return tokenRepository.findByEmail((email)).orElseThrow(() -> new ResourceNotFoundException("not found token"));
    }
}

