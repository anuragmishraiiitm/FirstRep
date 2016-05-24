package com.actiance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDependancy {
	String message="Anurag";
	MessageUtil Message=new MessageUtil(message);
	
	@Test
	public void  CallPrintMessage()
	{
		
		
		Assert.assertEquals(message, Message.printMessage());
	}
	@Test(priority=1)
	public void checkDependancy()
	{
		System.out.println("Inside CheckDependancy");
	}
	@Test(dependsOnMethods={"checkDependancy"})
	public void CallSalutationMessage()
	{
		
		Assert.assertEquals("Hi!"+message, Message.salutationMessage());
	}
	

}
