package orbartal.springbootdemoredistester.task.app;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springbootdemoredistester.task.api.model.TaskStatusResponseDto;
import orbartal.springbootdemoredistester.task.app.mapper.TaskResponseFactory;
import orbartal.springbootdemoredistester.task.data.TaskDataReader;
import orbartal.springbootdemoredistester.task.model.TaskReport;
import orbartal.springbootdemoredistester.task.model.TaskStatusEnum;

@Component
public class TaskAppReader {

	@Autowired
	private TaskDataReader taskReader;

	@Autowired
	private TaskResponseFactory responseFactory;

	public TaskStatusResponseDto readTaskStatusById(String id) {
		UUID uid = UUID.fromString(id);
		Optional<TaskStatusEnum> status = taskReader.getStatus(uid);
		if (status.isPresent()) {
			return responseFactory.status(id, status);
		}
		throw new RuntimeException("Not found task for uid");
	}

	public TaskReport readTaskReportById(String id) {
		UUID uid = UUID.fromString(id);
		Optional<TaskReport> r = taskReader.getReport(uid);
		if (r.isPresent()) {
			return r.get();
		}
		throw new RuntimeException("Not found task report for uid");
	}

}
