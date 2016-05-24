package com.actiance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;



public class NewWaitTest {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String DB_URL = "jdbc:sqlserver://192.168.114.214;databaseName=SP13141;user=sa;password=FaceTime@123";
	 static final String USER = "sa";
		static final String PASS = "FaceTime@123";
		ArrayList<HashMap<String, String>> fromDBList=new ArrayList<HashMap<String,String>>();
		 Connection conn = null;
		 Statement stmt = null;
		 ResultSet rs=null;
		 public static void reg_driver() throws SQLException
		 {
			 System.out.println("hello ");
			    DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			 
		 }
			    //STEP 3: Open a connection
		 
		
		 
		
		   
		 @Test
		    public void timeCheck() throws SQLException, InterruptedException {
			 String tempinterid="5";
			 String tempcontent="103";
			 Boolean flag=false;
			 rs=stmt.executeQuery("select interid,buddyname,contenttype from Interactions where interid=5");
			 rs.next();
			System.out.println(rs.getString("contenttype"));
				if(tempinterid.equalsIgnoreCase(rs.getString("interid"))){
					
					{
						System.out.println("hello1");
						while(true){
							if(tempinterid.equalsIgnoreCase(rs.getString("interid"))){
							System.out.println("checking value");
							Thread.sleep(10000);
							rs=stmt.executeQuery("select interid,buddyname,contenttype from Interactions where interid=5");
							 rs.next();
							if(tempcontent.equalsIgnoreCase(rs.getString("contenttype")))
							{
								System.out.println("checking value again");
								Thread.sleep(8000);
								rs=stmt.executeQuery("select interid,buddyname,contenttype from Interactions where interid=5");
								 rs.next();
								if(tempcontent.equalsIgnoreCase(rs.getString("contenttype")))
								{
									
									System.out.println("fixed value of"+tempinterid+"is contenttype"+tempcontent);
									flag=true;
									System.exit(0);
								}
								
							}
						}
						}
						
					}
					
				}
		 }
			 
			
	@BeforeTest
		public void MakeDB() throws SQLException
	{
		System.out.println("connected");
    	
	    conn = DriverManager.getConnection(DB_URL);
	    	

	    	if(conn!=null)
	    		System.out.println("Connection created");
	    //STEP 4: Execute a query
	    System.out.println("Creating statement...");
	   
	    stmt = conn.createStatement();
	   
	    if(stmt!=null)
			System.out.println("statement Created");
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