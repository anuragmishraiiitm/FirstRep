package com.actiance;

import org.testng.annotations.Test;
public class ValidateTestException 
{
String name="John";
TestException T=new TestException(name);

@Test(expectedExceptions = ArithmeticException.class)
public void TestMessage()
{
	T.Printmessage();
}


}
