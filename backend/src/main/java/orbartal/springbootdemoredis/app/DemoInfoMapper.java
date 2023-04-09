package orbartal.springbootdemoredis.app;

import java.util.UUID;

import org.springframework.stereotype.Component;

import orbartal.springbootdemoredis.api.DemoDto;
import orbartal.springbootdemoredis.business.DemoInfo;

@Component
public class DemoInfoMapper {

	public DemoDto toDto(DemoInfo input) {
		DemoDto out = new DemoDto();
		out.setUuid(input.getUid().toString());
		out.setValue(input.getValue());
		return out;
	}

	public DemoInfo toInfo(DemoDto input) {
		UUID uid = UUID.fromString(input.getUuid());
		String value = input.getValue();
		return new DemoInfo(uid, value);
	}

	public UUID toUid(String suid) {
		return UUID.fromString(suid);
	}

}
