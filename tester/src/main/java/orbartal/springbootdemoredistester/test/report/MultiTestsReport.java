package orbartal.springbootdemoredistester.test.report;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MultiTestsReport {

	private int numberOfTestRun;
	private int numberOfTestFail;
	private int numberOfTestIgnore;
	private long runTimeOfAllTests;

	// @Getter(AccessLevel.NONE)
	// @Setter(AccessLevel.NONE)
	private final Map<String, SingleTestReport> tests = new HashMap<>();

	public SingleTestReport addTest(String name) {
		if (name != null && !name.isEmpty() && !tests.containsKey(name)) {
			SingleTestReport str = new SingleTestReport();
			str.setMethod(name);
			tests.put(name, str);
		}
		return tests.get(name);
	}

	public Map<String, SingleTestReport> getTests() {
		return Collections.unmodifiableMap(tests);
	}

	public int getNumberOfTestRun() {
		return numberOfTestRun;
	}

	public void setNumberOfTestRun(int numberOfTestRun) {
		this.numberOfTestRun = numberOfTestRun;
	}

	public int getNumberOfTestFail() {
		return numberOfTestFail;
	}

	public void setNumberOfTestFail(int numberOfTestFail) {
		this.numberOfTestFail = numberOfTestFail;
	}

	public int getNumberOfTestIgnore() {
		return numberOfTestIgnore;
	}

	public void setNumberOfTestIgnore(int numberOfTestIgnore) {
		this.numberOfTestIgnore = numberOfTestIgnore;
	}

	public long getRunTimeOfAllTests() {
		return runTimeOfAllTests;
	}

	public void setRunTimeOfAllTests(long runTimeOfAllTests) {
		this.runTimeOfAllTests = runTimeOfAllTests;
	}

}
