package orbartal.springbootdemoredis.business;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springbootdemoredis.data.DemoData;

@Component
public class DemoBusiness {

	@Autowired
	private DemoData data;

	public List<DemoInfo> readAll() {
		return data.readAll();
	}

	public Optional<DemoInfo> readByUid(UUID uid) {
		return data.readByUid(uid);
	}

	public DemoInfo create(DemoInfo input) {
		return data.create(input);
	}

	public DemoInfo update(DemoInfo input) {
		return data.update(input);
	}

	public void deleteByUid(UUID uid) {
		data.deleteByUid(uid);
	}

	public void deleteAll() {
		data.deleteAll();
	}

}
