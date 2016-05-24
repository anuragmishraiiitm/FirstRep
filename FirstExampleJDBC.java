package com.actiance;
//STEP 1. Import required packages
import java.sql.*;

import org.testng.annotations.BeforeMethod;

public class FirstExampleJDBC {
 // JDBC driver name and database URL
 static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
 static final String DB_URL = "jdbc:sqlserver://192.168.114.214;databaseName=AnuragTest;user=sa;password=FaceTime@123";
// jdbc:sqlserver://" + DBSERVERIP+ ";databaseName=" + DBName + ";user=" + sDBUser + ";password=" + sDBPass;
 //  Database credentials
 static final String USER = "sa";
 static final String PASS = "FaceTime@123";
 @BeforeMethod
 public static void main(String[] args) {
 Connection conn = null;
 Statement stmt = null;
 try{
	 System.out.println(JDBC_DRIVER);
    //STEP 2: Register JDBC driver
    //Class.forName(JDBC_DRIVER);
    DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL);

    	if(conn!=null)
    		System.out.println("Connected");
    //STEP 4: Execute a query
    System.out.println("Creating statement...");
    stmt = conn.createStatement();
    if(stmt!=null)
		System.out.println("Created");
    //String sql;
    //sql = "SELECT id, first, last, age FROM Employee";
    ResultSet rs = stmt.executeQuery("SELECT id, first, last, age FROM Employee");

    //STEP 5: Extract data from result set
    while(rs.next()){		
       //Retrieve by column name
       int id  = rs.getInt("id");
       int age = rs.getInt("age");
       String first = rs.getString("first");
       String last = rs.getString("last");
       String middle = rs.getString("last");
       //Display values
       System.out.print("ID: " + id);
       System.out.print(", Age: " + age);
       
       System.out.print(", First: " + first);
       System.out.print(", Middle-: " + middle);
       System.out.println(", Last: " + last);
    }
    //STEP 6: Clean-up environment
    rs.close();
    stmt.close();
    conn.close();
 }catch(SQLException se){
    //Handle errors for JDBC
    se.printStackTrace();
 }catch(Exception e){
    //Handle errors for Class.forName
    e.printStackTrace();
 }finally{
    //finally block used to close resources
    try{
       if(stmt!=null)
          stmt.close();
    }catch(SQLException se2){
    }// nothing we can do
    try{
       if(conn!=null)
          conn.close();
    }catch(SQLException se){
       se.printStackTrace();
    }//end finally try
 }//end try
 System.out.println("Goodbye!");
}//end main
}//end FirstExample