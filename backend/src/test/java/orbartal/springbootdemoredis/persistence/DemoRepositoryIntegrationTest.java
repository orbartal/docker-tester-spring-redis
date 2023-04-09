package orbartal.springbootdemoredis.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import orbartal.springbootdemoredis.config.TestRedisConfiguration;
import orbartal.springbootdemoredis.redis.persistence.DemoEntityRedis;
import orbartal.springbootdemoredis.redis.persistence.DemoRedisRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoRepositoryIntegrationTest {

	@Autowired
	private DemoRedisRepository demoRepository;

	@Test
	public void saveToRedis() {
		UUID uid = UUID.randomUUID();
		DemoEntityRedis input = new DemoEntityRedis();
		input.setUid(uid);
		input.setValue("va1");

		DemoEntityRedis out = demoRepository.save(input);

		assertNotNull(out);

		Optional<DemoEntityRedis> pout2 = demoRepository.findById(uid);

		assertNotNull(pout2.get());

	}
}