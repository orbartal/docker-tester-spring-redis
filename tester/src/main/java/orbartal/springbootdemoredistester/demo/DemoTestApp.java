package orbartal.springbootdemoredistester.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orbartal.springbootdemoredistester.demo.test.CrudMultiErrorTest;
import orbartal.springbootdemoredistester.demo.test.CrudMultiValidTest;
import orbartal.springbootdemoredistester.demo.test.CrudOneValidTest;
import orbartal.springbootdemoredistester.task.api.model.TaskCreateResponseDto;
import orbartal.springbootdemoredistester.task.app.TaskAppWriter;
import orbartal.springbootdemoredistester.task.model.RunnableTask;
import orbartal.springbootdemoredistester.testtask.runnable.TestRunnableTask;
import orbartal.springbootdemoredistester.testtask.worker.TestTaskWorker;
import orbartal.springbootdemoredistester.testtask.worker.TestTaskWorkerFactory;

@Component
public class DemoTestApp {

	@Autowired
	private TaskAppWriter taskWriter;

	public TaskCreateResponseDto testCrudOneValid() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudOneValidTest.class);
		RunnableTask task = new TestRunnableTask("testCrudOneValid", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCrudManyValid() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudMultiValidTest.class);
		RunnableTask task = new TestRunnableTask("testCrudManyValid", worker);
		return taskWriter.runTask(task);
	}

	public TaskCreateResponseDto testCrudManyError() {
		TestTaskWorker worker = TestTaskWorkerFactory.fromTestClassWithOrder(CrudMultiErrorTest.class);
		RunnableTask task = new TestRunnableTask("testCrudManyError", worker);
		return taskWriter.runTask(task);
	}

}
