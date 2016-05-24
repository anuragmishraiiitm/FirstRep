package com.actiance;

import java.io.File;

public class FirstRename 
{
 public static void main(String args[])
 {
	 File oldname= new File("C:\\Users\\amishra\\workspace\\JavaProject\\FirstFile.txt");
	 File newname=new File("C:\\Users\\amishra\\workspace\\JavaProject\\NewFile.txt");
	 if(oldname.renameTo(newname))
	 {
		 System.out.println("renamed");
	 }
	 else
		 System.out.println("error");
 }
}
