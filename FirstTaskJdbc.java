package com.actiance;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstTaskJdbc {
	// JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	 static final String DB_URL = "jdbc:sqlserver://192.168.114.214;databaseName=SP13141;user=sa;password=FaceTime@123";
	// jdbc:sqlserver://" + DBSERVERIP+ ";databaseName=" + DBName + ";user=" + sDBUser + ";password=" + sDBPass;
	 //  Database credentials
	 static final String USER = "sa";
	 static final String PASS = "FaceTime@123";
	 Connection conn = null;
	 Statement stmt = null;
	 ResultSet rs=null;
	
	 
		 //STEP 2: Register JDBC driver
		    //Class.forName(JDBC_DRIVER);
	
	 public static void reg_driver() throws SQLException
	 {
		 System.out.println("hello ");
		    //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		 
	 }
		    //STEP 3: Open a connection
		   // System.out.println("Connecting to database...");
		    @BeforeSuite
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
		    @DataProvider
		    public  Object[][] IndianDate() throws SQLException
		    {
		    	String date[][]=new String[10][2];
		    		
		    	 rs=stmt.executeQuery("select starttime,endtime from Interactions");
		    	int count=0,bount=0;
		    	while(rs.next())
		    	{
		    		
		    		for(int i=0;i<2;i++)
		    		{
		    			if(i==0)
		    				date[count++][i]=rs.getString("starttime");
		    			else
		    				date[bount++][i]=rs.getString("endtime");
		    		}
		    		if(bount>9)
		    			break;
		    	}
		    	
		    	for(int i=0;i<10;i++)
		    	{
		    		System.out.println(date[i][0]+" "+date[i][1]);
		    	}
		    	return date;
		    }
		    
		    @Test(dataProvider="IndianDate")
		    public void PrintDate(String starttime,String endtime)
		    {
		    	
		    long epoch = Long.parseLong( starttime );
		    
		 
		    	DateFormat df = new SimpleDateFormat("hh:mm:ss a");
		    	String str = df.format(epoch);
		    	
		    	epoch=Long.parseLong(endtime);
		    	 df = new SimpleDateFormat("hh:mm:ss a");
		    	String str1 = df.format(epoch);
		    	
		    	System.out.println("Start time is=	"+str+"		End time is:-"+str1);
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
		    
	 


	
	
		

	    
	   
	 

		
	    

	
