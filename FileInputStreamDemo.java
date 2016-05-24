package com.actiance;

import java.io.IOException;
import java.io.FileInputStream;

public class FileInputStreamDemo {
   public static void main(String[] args) throws IOException {
      
      FileInputStream fis = null;
      int i = 0;
      char c;
      byte[] bs = new byte[4];
      
      try{
         // create new file input stream
         fis = new FileInputStream("C:\\Users\\amishra\\workspace\\JavaProject\\newfile.txt");
         
         // read bytes to the buffer
         i=fis.read(bs, 2, 1);
         System.out.println(bs);
         // prints
         System.out.println("Number of bytes read: "+i);
         System.out.print("Bytes read: ");
         
         // for each byte in buffer
         for(byte b:bs)
         {
            // converts byte to character
            c=(char)b;
            if(b==0)
               c='-';
            
            // print
            System.out.print(c);
         }   
      }catch(Exception ex){
         // if any error occurs
         ex.printStackTrace();
      }finally{
         
         // releases all system resources from the streams
         if(fis!=null)
            fis.close();
      }
   }
}