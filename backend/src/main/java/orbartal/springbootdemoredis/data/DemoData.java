package orbartal.springbootdemoredis.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import orbartal.springbootdemoredis.business.DemoInfo;
import orbartal.springbootdemoredis.redis.persistence.DemoEntityRedis;
import orbartal.springbootdemoredis.redis.persistence.DemoRedisRepository;


@Transactional
@Component
public class DemoData {

	@Autowired
	private DemoRedisRepository demoRepository;

	static final Comparator<DemoInfo> uidComparator = (a, b) -> a.getUid().toString().compareTo(b.getUid().toString());

	@Transactional(readOnly = true)
	public synchronized List<DemoInfo> readAll() {
		List<DemoEntityRedis> entities = new ArrayList<>();
		demoRepository.findAll().forEach(entities::add);
		return entities.stream().map(e -> toDemoInfo(e)).sorted(uidComparator).toList();
	}

	@Transactional(readOnly = true)
	public synchronized Optional<DemoInfo> readByUid(UUID uid) {
		Optional<DemoEntityRedis> entitiy = demoRepository.findById(uid);
		return (entitiy != null) ? entitiy.map(e -> toDemoInfo(e)) : Optional.empty();
	}

	@Transactional(readOnly = false)
	public synchronized DemoInfo create(DemoInfo input) {
		UUID uuid = input.getUid();
		String value = input.getValue();
		Optional<DemoEntityRedis> pOldEntitiy = demoRepository.findById(uuid);
		if (pOldEntitiy != null && pOldEntitiy.isPresent()) {
			throw new RuntimeException("Duplicate uuid: " + input.getUid());
		}
		DemoEntityRedis newEntitiy = new DemoEntityRedis();
		newEntitiy.setUid(uuid);
		newEntitiy.setValue(value);
		demoRepository.save(newEntitiy);
		return toDemoInfo(newEntitiy);
	}

	@Transactional(readOnly = false)
	public synchronized DemoInfo update(DemoInfo input) {
		String value = input.getValue();
		UUID uuid = input.getUid();
		Optional<DemoEntityRedis> pEntitiy = demoRepository.findById(uuid);
		if (pEntitiy == null || pEntitiy.isEmpty()) {
			throw new RuntimeException("Missing uuid: " + input.getUid());
		}
		DemoEntityRedis entity = pEntitiy.get();
		entity.setValue(value);
		demoRepository.save(entity);
		return toDemoInfo(entity);
	}

	@Transactional(readOnly = false)
	public synchronized void deleteByUid(UUID uid) {
		Optional<DemoEntityRedis> pEntitiy = demoRepository.findById(uid);
		if (pEntitiy == null || pEntitiy.isEmpty()) {
			throw new RuntimeException("Missing uuid: " + uid);
		}
		demoRepository.delete(pEntitiy.get());
	}

	@Transactional(readOnly = false)
	public synchronized void deleteAll() {
		demoRepository.deleteAll();
	}

	private DemoInfo toDemoInfo(DemoEntityRedis input) {
		return new DemoInfo(input.getUid(), input.getValue());
	}

}
