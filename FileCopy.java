package com.actiance;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.testng.annotations.Test;

public class FileCopy {
	@Test
	public void F1() throws IOException{
		
		File f1=new File("C:\\Users\\amishra\\workspace\\JavaProject\\newfile.txt");
		File f2=new File("C:\\Users\\amishra\\workspace\\JavaProject\\output.txt");
		InputStream is= new FileInputStream(f1);
		OutputStream os=new FileOutputStream(f2);
		 byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	}

}
