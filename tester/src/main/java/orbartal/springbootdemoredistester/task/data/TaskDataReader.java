package orbartal.springbootdemoredistester.task.data;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springbootdemoredistester.task.dao.TaskDb;
import orbartal.springbootdemoredistester.task.model.RunnableTask;
import orbartal.springbootdemoredistester.task.model.TaskReport;
import orbartal.springbootdemoredistester.task.model.TaskStatusEnum;

@Component
public class TaskDataReader {

	@Autowired
	private TaskDb taskDb;

	public Optional<TaskStatusEnum> getStatus(UUID uid) {
		RunnableTask task = taskDb.getTaskByUid(uid);
		if (task == null) {
			return Optional.empty();
		}
		return Optional.of(task.getStatus());
	}

	public Optional<TaskReport> getReport(UUID uid) {
		RunnableTask task = taskDb.getTaskByUid(uid);
		if (task == null) {
			return Optional.empty();
		}
		return task.getReport();
	}

}
