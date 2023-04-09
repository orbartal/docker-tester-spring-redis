package orbartal.springbootdemoredis.app;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import orbartal.springbootdemoredis.api.DemoDto;
import orbartal.springbootdemoredis.business.DemoBusiness;
import orbartal.springbootdemoredis.business.DemoInfo;

@SuppressWarnings("rawtypes")
@Component
public class DemoApp {

	private static final Logger logger = LoggerFactory.getLogger(DemoApp.class);

	@Autowired
	private DemoInfoMapper mapper;

	@Autowired
	private DemoBusiness business;

	public ResponseEntity readAll() {
		try {
			List<DemoDto> dtos = business.readAll().stream().map(i -> mapper.toDto(i)).toList();
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("readAll: error = {}", e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity readByUid(String suid) {
		try {
			UUID uid = mapper.toUid(suid);
			Optional<DemoInfo> out = business.readByUid(uid);
			DemoDto dto = mapper.toDto(out.get());
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("readByUid: uid = {} error = {}", suid, e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity create(DemoDto inDto) {
		try {
			DemoInfo inInfo = mapper.toInfo(inDto);
			DemoInfo outInfo = business.create(inInfo);
			DemoDto outDto = mapper.toDto(outInfo);
			return new ResponseEntity<>(outDto, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("create: inDto = {} error = {}", inDto, e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity update(DemoDto inDto) {
		try {
			DemoInfo inInfo = mapper.toInfo(inDto);
			DemoInfo outInfo = business.update(inInfo);
			DemoDto outDto = mapper.toDto(outInfo);
			return new ResponseEntity<>(outDto, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("update: inDto = {} error = {}", inDto, e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity deleteByUid(String suid) {
		try {
			UUID uid = mapper.toUid(suid);
			business.deleteByUid(uid);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("deleteByUid: suid = {} error = {}", suid, e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity deleteAll() {
		try {
			business.deleteAll();
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("deleteAll: error = {}", e.getMessage());
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
