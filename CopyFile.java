package com.actiance;

import java.io.*;

public class CopyFile {
   public static void main(String args[]) throws IOException
   {
      FileReader in = null;
      FileWriter out = null;
      
     // System.out.println(openFile.getAbsolutePath());
      try {
         in = new FileReader("C:\\Users\\amishra\\workspace\\JavaProject\\newfile.txt");
         out = new FileWriter("C:\\Users\\amishra\\workspace\\JavaProject\\output.txt");
         BufferedReader br = new BufferedReader(in);
         String line = null;
         while ((line = br.readLine()) != null) {
           System.out.println(line);
         }
         String c;
         while ((c = br.readLine()) != null) {
            out.write(c);
         out.flush();
         }
         
      }finally {
         if (in != null) {
            in.close();
         }
         if (out != null) {
            out.close();
         }
      }
   }
}