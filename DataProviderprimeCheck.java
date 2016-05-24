package com.actiance;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderprimeCheck {
   private PrimeNumberChecker primeNumberChecker;

   @BeforeMethod
   public void initialize() {
      primeNumberChecker = new PrimeNumberChecker();
   }

   @DataProvider
   public static Object[][] primeNumbers() {
      return new Object[][] {{2, true}, {6, false}, {19, true}, {22, false}, {23, false}};
   }

   // This test will run 4 times since we have 5 parameters defined
   @Test(dataProvider = "primeNumbers")
   public void testPrimeNumberChecker(Integer inputNumber, Boolean expectedResult) {
      System.out.println(inputNumber + " " + expectedResult); //printing data provider Array
      Assert.assertEquals(expectedResult, primeNumberChecker.validate(inputNumber)); //Comparing results
   }
}