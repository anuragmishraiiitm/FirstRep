package com.actiance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupTestExample {
	private String message=".com";
	MessageUtil messageUtil=new MessageUtil(message);
	
	@Test(groups={"G2","G3"})
	public void testPrintMessage()
	{
	
		System.out.println(message);
		Assert.assertEquals(message, messageUtil.printMessage());
	}
	
	@Test(groups={"G2"})
	public void testSalutationMessage(){
		
		message="tutorialspoint"+message;
		System.out.println(message);
		Assert.assertEquals(message, messageUtil.salutationMessage());
	}
	@Test(groups={"G3"})
	public void testExitMessage(){
		
		message="www."+message;
		System.out.println(message);
		Assert.assertEquals(message, messageUtil.exitMessage());
	}

}
