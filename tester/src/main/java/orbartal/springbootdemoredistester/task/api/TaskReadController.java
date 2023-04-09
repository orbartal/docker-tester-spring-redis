package orbartal.springbootdemoredistester.task.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import orbartal.springbootdemoredistester.task.api.model.TaskStatusResponseDto;
import orbartal.springbootdemoredistester.task.app.TaskAppReader;
import orbartal.springbootdemoredistester.task.model.TaskReport;

@RestController
@RequestMapping(path = "/api/v1/task")
public class TaskReadController {

	@Autowired
	private TaskAppReader taskApplication;

	@GetMapping(path = "/{id}/status", produces = "application/json")
	public TaskStatusResponseDto getTaskStatus(@PathVariable String id) {
		return taskApplication.readTaskStatusById(id);
	}

	@GetMapping(path = "/{id}/report", produces = "application/json")
	public TaskReport getTaskReport(@PathVariable String id) {
		return taskApplication.readTaskReportById(id);
	}

}
