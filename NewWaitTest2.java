//Track when contenttype is fixed 


package com.actiance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;




public class NewWaitTest2 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String DB_URL = "jdbc:sqlserver://192.168.114.214;databaseName=SP13141;user=sa;password=FaceTime@123";
	 static final String USER = "sa";
		static final String PASS = "FaceTime@123";
		ArrayList<HashMap<String, String>> fromDBList=new ArrayList<HashMap<String,String>>();
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs=null;
		 ResultSet ps=null;
		 Properties prop = new Properties();
		
		 
		 public static void reg_driver() throws SQLException
		 {
			 System.out.println("hello ");
			    DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			 
		 }
			    //STEP 3: Open a connection
		 
		
		 
		
		 @Test
		    public void timeCheck() throws SQLException, InterruptedException {
			
			 int count=0;
			Boolean flag=false;
			 rs=stmt.executeQuery();	//selecting interid,	buddyname, contenttype where interid is 5 
			 rs.next(); 
			 String interidnew=rs.getString("interid");		//getting interid
			 String contenttypenew=rs.getString("contenttype");	//getting content type
			System.out.println("content type for "+rs.getString("interid")+"is "+rs.getString("contenttype"));
				if( interidnew.equalsIgnoreCase(rs.getString("interid"))){
					
						System.out.println("hello1");
						
						while(true){
							System.out.println("checking value");
							Thread.sleep(5000);
							rs=stmt.executeQuery();
							 rs.next();
							if(contenttypenew.equalsIgnoreCase(rs.getString("contenttype"))) //if content type is same 
							{
																							//increase counter and sleep again
								count++;
								System.out.println("if sleep1");
								Thread.sleep(5000);
								System.out.println("if sleep2");
							
							}
							else{												//if content type has been changed then
								
								System.out.println("else sleep");				//change the value of content type to new value
								contenttypenew=rs.getString("contenttype");		
							count=0;
								continue;
							}
							int counter=0;
							Long newInterval;
							newInterval=Long.parseLong(prop.getProperty("interval"))*10*1000;//Time should be in mili seconds
							//System.out.println("new inteval is"+newInterval);				// Getting value from property file(min)
							while(count>=2&&contenttypenew.equalsIgnoreCase(rs.getString("contenttype")))
							{
								//if count>2 then wait for some interval. If after interval it is again same 
								//then print the fix value
								System.out.println("checking value again");
								Thread.sleep(newInterval);
								
								rs=stmt.executeQuery();
								 rs.next();
								if(contenttypenew.equalsIgnoreCase(rs.getString("contenttype"))&&flag==false)
								{
									//if after the interval it is fixed then print the value.
									System.out.println("fixed value of"+interidnew+"is contenttype"+contenttypenew);
									flag=true;
									
								}
								else if(contenttypenew.equalsIgnoreCase(rs.getString("contenttype")))
								{
									counter++;
									if(counter>2)
									{
										//if counter>2 it means that even after 2 cycles the value is fixed
										//exit after time out
										System.out.println("Exit because the value is fixed and timeout");
										System.exit(0);
										
									}
									
								}
								else
								{
									//if the value is changed in between
									//reset all the counter and again check the value.
									flag=false;
									contenttypenew=rs.getString("contenttype");
									counter=0;
									count=0;
									break;
								}
								
							}
							
							
						}
						
				}
		 }
			
			
	@BeforeTest
		public void MakeDB() throws SQLException, IOException
	{
		System.out.println("connected");
    	
	    conn = DriverManager.getConnection(DB_URL);
	    	

	    	if(conn!=null)
	    		System.out.println("Connection created");
	//accessing property file
	    	 String propFileName="C:\\Users\\amishra\\AppData\\Roaming\\Skype\\My Skype Received Files\\propFileName.properties";
	    	 //String propFileName="propFileName.properties";
	    	FileInputStream input = new FileInputStream(propFileName);
			//InputStream input=getClass().getClassLoader().getResourceAsStream(propFileName);
				 prop.load(input);
				 System.out.println("property extracted");
				    //STEP 4: Execute a query	 
	    System.out.println("Creating statement...");
	   
	    stmt =conn.prepareStatement("select interid,buddyname,contenttype from Interactions where interid=5");
	   
	    if(stmt!=null)
			System.out.println("statement Created");
	    
	   
		 
		 System.out.println(prop.getProperty("interval"));
		 /*rs=stmt.executeQuery("select top 5 interid,buddyname,contenttype from Interactions"); //fetching from database
		 while(rs.next()) 
		    {
			 HashMap<String, String> fromDB = new HashMap<String, String> ();
		    	
		    	
		    	fromDB.put("interid",rs.getString("interid"));
		    	fromDB.put("buddyname",rs.getString("buddyName"));
		    	fromDB.put("contenttype",rs.getString("contenttype"));
		    	fromDBList.add(fromDB); //
	}*/

		 System.out.println("in before test");
		 
	}
		 
		 @AfterSuite
		    public void Disconnect() throws SQLException
		    {
		    	System.out.println("Disconnected");
		    	
		    	rs.close();
		        stmt.close();
		        conn.close();
		    	
		    }
	
}