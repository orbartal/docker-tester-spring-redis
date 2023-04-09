package orbartal.springbootdemoredistester.test.runner;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import orbartal.springbootdemoredistester.test.report.MultiTestsReport;
import orbartal.springbootdemoredistester.test.report.SingleTestReport;
import orbartal.springbootdemoredistester.test.report.TestStatusEnum;

public class TestListener extends RunListener {

   private final MultiTestsReport report = new MultiTestsReport();

   public void testRunStarted(Description description)
      throws Exception {
      report.setNumberOfTestRun(description.testCount());
   }

   public void testStarted(Description description)
      throws Exception {
      SingleTestReport r = report.addTest(description.getMethodName());
      r.setMethod(description.getMethodName());
      r.setStatus(TestStatusEnum.RUNNING);
   }

   public void testFinished(Description description)
      throws Exception {
      SingleTestReport r = report.addTest(description.getMethodName());
      if (TestStatusEnum.RUNNING.equals(r.getStatus())) {
         r.setStatus(TestStatusEnum.SUCCESS);
      }
   }

   public void testRunFinished(Result result)
      throws Exception {
      report.setNumberOfTestFail(result.getFailureCount());
      report.setNumberOfTestIgnore(result.getIgnoreCount());
      report.setRunTimeOfAllTests(result.getRunTime());
   }

   public void testFailure(Failure failure)
      throws Exception {
      SingleTestReport r = report.addTest(failure.getDescription().getMethodName());
      r.setStatus(TestStatusEnum.FAIL);
      r.setException(failure.getException().getClass().getName());
      r.setMessage(failure.getMessage());
   }

   public void testAssumptionFailure(Failure failure) {
      SingleTestReport r = report.addTest(failure.getDescription().getMethodName());
      r.setStatus(TestStatusEnum.FAIL);
      r.setException(failure.getException().getClass().getName() + ": " + failure.getException().getMessage());
      r.setMessage(failure.getMessage());
   }

   public void testIgnored(Description d)
      throws Exception {
      SingleTestReport r = report.addTest(d.getMethodName());
      r.setStatus(TestStatusEnum.IGNORE);
   }

   public MultiTestsReport getReport() {
      return report;
   }

   public String toString() {
      return report.toString();
   }
}