

package com.actiance;
import java.sql.*;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class Test2 {
	// JDBC driver name and database URL
	 //static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	 //static final String DB_URL = "jdbc:sqlserver://192.168.114.214;databaseName=SP13141;user=sa;password=FaceTime@123";
	 String JDBC_DRIVER,DB_URL,USER,PASS;
	 @Parameters({"Driver","Dburl","Username","password"})
	 @BeforeSuite
	 public  void initialize(String JDBC_DRIVER,String DB_URL,String USER,String PASS)
	 {
		 this.JDBC_DRIVER=JDBC_DRIVER;
		 this.DB_URL=DB_URL;
		 this.USER=USER;
		 this.PASS=PASS;
		 
	 }
	// jdbc:sqlserver://" + DBSERVERIP+ ";databaseName=" + DBName + ";user=" + sDBUser + ";password=" + sDBPass;
	 //  Database credentials
	// static final String USER = "sa";
	 //static final String PASS = "FaceTime@123";
	 Connection conn = null;
	 Statement stmt = null;
	 ResultSet rs=null;
	 ResultSet ps=null;
	 
		 //STEP 2: Register JDBC driver
		    //Class.forName(JDBC_DRIVER);
	
	 public static void reg_driver() throws SQLException
	 {
		 System.out.println("hello ");
		    DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		 
	 }
		    //STEP 3: Open a connection
		   // System.out.println("Connecting to database...");
		    @Test
		    public void connect() throws SQLException 
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
		    }
		    
		   
		    
		   /* @Test(dependsOnMethods={"connect"})
		    public void PrintDate() throws SQLException
		    {
		    	
		    	 rs=stmt.executeQuery("select top 10 starttime from Interactions");
		    	 while(rs.next())
		    	 {
		    		 System.out.println(rs.getString("starttime"));
		    	 }
		    	 
		    }*/
		    @Test(dependsOnMethods={"connect"})
		    public void printres() throws SQLException
		    {
		    	ps=stmt.executeQuery("select top 10 resName from resources");
		    	while(ps.next())
		    	{
		    	System.out.println("Resname is   "+ps.getString("resName"));
		    	}
		    }
		    @AfterSuite
		    public void Disconnect() throws SQLException
		    {
		    	System.out.println("Disconnected");
		    	
		    	rs.close();
		        stmt.close();
		        conn.close();
		    	ps.close();
		    }
	 }
		    
