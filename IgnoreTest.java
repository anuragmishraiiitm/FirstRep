package com.actiance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class IgnoreTest {
   String message = "Manisha";
   MessageUtil messageUtil = new MessageUtil(message);

   @Test(enabled = true)
   public void testPrintMessage() {
      System.out.println("Inside testPrintMessage()");
      message = "Manisha";
   Assert.assertEquals(message, messageUtil.printMessage(),"hello print");
   }

   @Test
   public void testSalutationMessage() {
      System.out.println("Inside testSalutationMessage()");
      message = "Hi!" + "Manisha";
      Assert.assertEquals(message, messageUtil.salutationMessage(),"salute");
   }
}