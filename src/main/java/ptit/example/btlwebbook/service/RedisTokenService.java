package ptit.example.btlwebbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.model.RedisToken;
import ptit.example.btlwebbook.repository.RedisTokenRepository;

@Service
@RequiredArgsConstructor
public class RedisTokenService {
    private final RedisTokenRepository redisTokenRepository;

    public String save(RedisToken redisToken) {
        RedisToken result =  redisTokenRepository.save(redisToken);
        return result.getId();
    }

    public void delete(String id) {
        redisTokenRepository.deleteById(id);
    }

    public RedisToken getById(String email) {
       return  redisTokenRepository.findById(email).orElseThrow(()-> new ResourceNotFoundException("Email not found"));
    }
}
