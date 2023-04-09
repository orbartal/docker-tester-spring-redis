package orbartal.springbootdemoredistester.test.runner;

import org.junit.runner.*;
import org.junit.runner.notification.*;

public class TestBeanRunner extends Runner {

   private TestFunction test;

   public TestBeanRunner(TestFunction test) {
      this.test = test;
   }

   @Override
   public Description getDescription() {
      return Description.createTestDescription(test.getClass(), test.getClass().getSimpleName());
   }

   @Override
   public void run(RunNotifier notifier) {
      Description description = getDescription();
      notifier.fireTestStarted(description);
      try {
         test.test();
         notifier.fireTestFinished(description);
      }
      catch (Throwable throwable) {
         notifier.fireTestFailure(new Failure(description, throwable));
      }
   }
}
