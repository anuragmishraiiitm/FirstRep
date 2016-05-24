package com.actiance;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Sample {

	@Test
	@Parameters({"firstname","lastname"})
	public void Test(String first,String last){
		System.out.println("Inside Test");
		System.out.println(first+last);
	}
}
