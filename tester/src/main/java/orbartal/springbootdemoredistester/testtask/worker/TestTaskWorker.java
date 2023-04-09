package orbartal.springbootdemoredistester.testtask.worker;

import orbartal.springbootdemoredistester.test.runner.TestListener;
import orbartal.springbootdemoredistester.testtask.model.TestTaskReport;

public class TestTaskWorker implements TaskWorker<TestTaskReport> {

	private final JunitRunner runner;

	public TestTaskWorker(JunitRunner runner) {
		this.runner = runner;
	}

	@Override
	public TestTaskReport work() {
		TestListener listener = new TestListener();
		runner.run(listener);
		return new TestTaskReport(listener.getReport());
	}

}
