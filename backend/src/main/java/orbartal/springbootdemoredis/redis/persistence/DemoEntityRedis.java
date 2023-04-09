package orbartal.springbootdemoredis.redis.persistence;

import java.util.UUID;
import org.springframework.data.annotation.Id;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("demo")
public class DemoEntityRedis {

	@Id
	// universally unique identifier used in the api
	private UUID uid;
	//The value is a string but can easily modify to any other type of data we want to store.
	private String value;

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DemoEntityRedis [uid=" + uid + ", value=" + value + "]";
	}

}
