package orbartal.springbootdemoredistester.demo.util;

import orbartal.springbootdemoredistester.demo.DemoDto;

public class DemoDtoFactory {

	static public DemoDto buildDemoDto(String key, String value) {
		DemoDto entity = new DemoDto();
		entity.setUuid(key);
		entity.setValue(value);
		return entity;
	}

}
