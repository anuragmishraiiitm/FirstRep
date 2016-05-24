package com.actiance;

public class TestException {
	
	String message;
	public  TestException(String str)
	{
		this.message=str;
	}
	public void Printmessage()
	{
		System.out.println(message);
		int a=5;
		int b=0;
		int c=a/b;
		System.out.println(c);
	}
	


}
