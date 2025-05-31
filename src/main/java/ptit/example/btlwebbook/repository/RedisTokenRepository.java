package ptit.example.btlwebbook.repository;

import org.springframework.data.repository.CrudRepository;
import ptit.example.btlwebbook.model.RedisToken;

public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
