package orbartal.springbootdemoredis.redis.persistence;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface DemoRedisRepository extends CrudRepository<DemoEntityRedis, UUID> {

}
