package orbartal.springbootdemoredistester.example.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springbootdemoredistester.example.test.CallOrderTest;
import orbartal.springbootdemoredistester.example.test.Test4Results;
import orbartal.springbootdemoredistester.task.api.model.TaskCreateResponseDto;
import orbartal.springbootdemoredistester.task.app.TaskAppWriter;
import orbartal.springbootdemoredistester.task.model.RunnableTask;
import orbartal.springbootdemoredistester.testtask.runnable.TestRunnableTask;
import orbartal.springbootdemoredistester.testtask.worker.TestTaskWorker;
import orbartal.springbootdemoredistester.testtask.worker.TestTaskWorkerFactory;

@Component
public class ExampleTestApp {

	@Autowired
	private TaskAppWriter taskWriter;

	public TaskCreateResponseDto test4Results() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClass(Test4Results.class);
		RunnableTask task = new TestRunnableTask("test4Results", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCallOrder() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClass(CallOrderTest.class);
		RunnableTask task = new TestRunnableTask("testCallOrder", worker);
		return taskWriter.runTask(task);
	}

}
