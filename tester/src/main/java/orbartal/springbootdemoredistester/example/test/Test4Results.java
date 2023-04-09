package orbartal.springbootdemoredistester.example.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class Test4Results {

   @Test
   public void test1Valid()
      throws Exception {
      Assert.assertEquals(1, 1);
   }

   @Test
   public void test2FailAssert()
      throws Exception {
      Assert.assertEquals(1, 2);
   }

   @Test
   public void test3RuntimeException()
      throws Exception {
      throw new RuntimeException();
   }

   @Ignore
   @Test
   public void test4Ignore()
      throws Exception {
   }
}
