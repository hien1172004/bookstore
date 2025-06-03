package ptit.example.btlwebbook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Basic Operations
    public void setValue(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            log.debug("Set value for key: {}", key);
        } catch (Exception e) {
            log.error("Error setting value for key: {}", key, e);
            throw e;
        }
    }

    public void setValueWithExpiration(String key, Object value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
            log.debug("Set value with expiration for key: {}, timeout: {} {}", key, timeout, unit);
        } catch (Exception e) {
            log.error("Error setting value with expiration for key: {}", key, e);
            throw e;
        }
    }

    public Object getValue(String key) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            log.debug("Get value for key: {}", key);
            return value;
        } catch (Exception e) {
            log.error("Error getting value for key: {}", key, e);
            throw e;
        }
    }

    public Boolean deleteKey(String key) {
        try {
            Boolean result = redisTemplate.delete(key);
            log.debug("Delete key: {}", key);
            return result;
        } catch (Exception e) {
            log.error("Error deleting key: {}", key, e);
            throw e;
        }
    }

    public Boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("Error checking key existence: {}", key, e);
            throw e;
        }
    }

    // Hash Operations
    public void hSet(String key, String hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            log.debug("Set hash value for key: {}, hashKey: {}", key, hashKey);
        } catch (Exception e) {
            log.error("Error setting hash value for key: {}, hashKey: {}", key, hashKey, e);
            throw e;
        }
    }

    public Object hGet(String key, String hashKey) {
        try {
            return redisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            log.error("Error getting hash value for key: {}, hashKey: {}", key, hashKey, e);
            throw e;
        }
    }

    public Map<Object, Object> hGetAll(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error("Error getting all hash values for key: {}", key, e);
            throw e;
        }
    }

    // List Operations
    public Long lPush(String key, Object value) {
        try {
            return redisTemplate.opsForList().leftPush(key, value);
        } catch (Exception e) {
            log.error("Error pushing value to list: {}", key, e);
            throw e;
        }
    }

    public Object lPop(String key) {
        try {
            return redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            log.error("Error popping value from list: {}", key, e);
            throw e;
        }
    }

    public List<Object> lRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("Error getting range from list: {}", key, e);
            throw e;
        }
    }

    // Set Operations
    public Long sAdd(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("Error adding values to set: {}", key, e);
            throw e;
        }
    }

    public Set<Object> sMembers(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("Error getting members from set: {}", key, e);
            throw e;
        }
    }

    // Counter Operations
    public Long increment(String key, long delta) {
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (Exception e) {
            log.error("Error incrementing value for key: {}", key, e);
            throw e;
        }
    }

    public Long decrement(String key, long delta) {
        try {
            return redisTemplate.opsForValue().decrement(key, delta);
        } catch (Exception e) {
            log.error("Error decrementing value for key: {}", key, e);
            throw e;
        }
    }

    // Expiration Operations
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        try {
            return redisTemplate.expire(key, timeout, unit);
        } catch (Exception e) {
            log.error("Error setting expiration for key: {}", key, e);
            throw e;
        }
    }

    public Long getExpire(String key, TimeUnit unit) {
        try {
            return redisTemplate.getExpire(key, unit);
        } catch (Exception e) {
            log.error("Error getting expiration for key: {}", key, e);
            throw e;
        }
    }
} 