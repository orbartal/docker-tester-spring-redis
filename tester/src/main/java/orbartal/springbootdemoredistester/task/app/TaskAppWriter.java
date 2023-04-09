package orbartal.springbootdemoredistester.task.app;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springbootdemoredistester.task.api.model.TaskCreateResponseDto;
import orbartal.springbootdemoredistester.task.app.mapper.TaskResponseFactory;
import orbartal.springbootdemoredistester.task.data.TaskDataWriter;
import orbartal.springbootdemoredistester.task.model.RunnableTask;


@Component
public class TaskAppWriter {

	@Autowired
	private TaskResponseFactory responseFactory;

	@Autowired
	private TaskDataWriter taskExecuter;

	public TaskCreateResponseDto runTask(RunnableTask task) {
		UUID uuid = taskExecuter.addNewTask(task);
		return responseFactory.create(uuid, task);
	}

}
