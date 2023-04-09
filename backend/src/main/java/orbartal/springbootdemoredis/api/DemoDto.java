package orbartal.springbootdemoredis.api;

import io.swagger.v3.oas.annotations.media.Schema;

// Data object for request, response and internal usage.
// In a real system its advice to used separate domain for each.
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class DemoDto {

	private String uuid;

	private String value;

	public DemoDto() {}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DemoDto [Uid=" + uuid + ", value=" + value + "]";
	}

}
