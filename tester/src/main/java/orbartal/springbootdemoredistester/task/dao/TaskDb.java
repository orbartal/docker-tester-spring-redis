package orbartal.springbootdemoredistester.task.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import orbartal.springbootdemoredistester.task.model.RunnableTask;

@Component
public class TaskDb {

	private final Map<UUID, RunnableTask> taskByUid = new HashMap<>();

	public void addTask(UUID uid, RunnableTask task) {
		taskByUid.put(uid, task);
	}

	public RunnableTask getTaskByUid(UUID uid) {
		return taskByUid.get(uid);
	}

}
