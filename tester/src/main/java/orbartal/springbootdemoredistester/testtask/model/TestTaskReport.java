package orbartal.springbootdemoredistester.testtask.model;

import orbartal.springbootdemoredistester.task.model.TaskReport;
import orbartal.springbootdemoredistester.test.report.MultiTestsReport;

public class TestTaskReport implements TaskReport {

	private final MultiTestsReport report;

	public TestTaskReport(MultiTestsReport report) {
		this.report = report;
	}

	public MultiTestsReport getReport() {
		return report;
	}

}
