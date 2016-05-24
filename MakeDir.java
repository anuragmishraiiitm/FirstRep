package com.actiance;

import java.io.File;
import java.io.IOException;

public class MakeDir {
	
	public static void main(String[] args) throws IOException {
		File F=new File("C:\\Users\\amishra\\workspace\\JavaProject\\hello");
		F.mkdir();
		if(F.exists())
		{
			System.out.println("Directory built");
			File F1=new File("C:\\Users\\amishra\\workspace\\JavaProject\\hello\\one.txt");
			F1.createNewFile();
			if(F1.exists())
				System.out.println("File created");
			else
				System.out.println("error");
		}
		else
			System.out.println("error");
	}
	

}
