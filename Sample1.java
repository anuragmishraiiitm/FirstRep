package com.actiance;

import org.testng.annotations.Test;

public class Sample1 {

	@Test
	
	public void Test(String first,String last){
		System.out.println("Inside Test");
		System.out.println(first+last);
	}
}
