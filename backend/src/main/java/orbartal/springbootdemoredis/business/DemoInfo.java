package orbartal.springbootdemoredis.business;

import java.util.UUID;

public class DemoInfo {

	private final UUID uid;
	private final String value;

	public DemoInfo(UUID uid, String value) {
		this.uid = uid;
		this.value = value;
	}

	public UUID getUid() {
		return uid;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "DemoInfo [uid=" + uid + ", value=" + value + "]";
	}

}
