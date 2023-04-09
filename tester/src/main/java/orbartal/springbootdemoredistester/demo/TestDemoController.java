package orbartal.springbootdemoredistester.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbartal.springbootdemoredistester.task.api.model.TaskCreateResponseDto;

@RestController
@RequestMapping(path = "/api/v1/test/executer")
public class TestDemoController {

	@Autowired
	private DemoTestApp executer;

	@PostMapping(path = "/demo/crud/one/valid", produces = "application/json")
	public TaskCreateResponseDto test1() {
		return executer.testCrudOneValid();
	}

	@PostMapping(path = "/demo/crud/many/valid", produces = "application/json")
	public TaskCreateResponseDto testManyValid() {
		return executer.testCrudManyValid();
	}
	
	@PostMapping(path = "/demo/crud/many/error", produces = "application/json")
	public TaskCreateResponseDto testManyError() {
		return executer.testCrudManyError();
	}

}
