package com.actiance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task2Hashmap {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
	static final String DB_URL = "jdbc:sqlserver://192.168.114.214;databaseName=SP13141;user=sa;password=FaceTime@123";
	 static final String USER = "sa";
		static final String PASS = "FaceTime@123";
		 ArrayList<HashMap<String, String>> fromDBList=new ArrayList<HashMap<String, String>>();// global arraylist to store hashmaps frmp DB
		 
		 Connection conn = null;
		 Statement stmt = null;
		 ResultSet rs=null;
		 public static void reg_driver() throws SQLException
		 {
			 System.out.println("hello ");
			    DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			 
		 }
			    //STEP 3: Open a connection
		 
		
		 
		 //DPDPPDPDPDPD
		 
		 
		 @DataProvider // Data provider for manual values
		    public Object[][] Interact() throws SQLException
		    {
		    	//System.out.println("hello");
		    	
		    	ArrayList<HashMap<String, String>> AL=new ArrayList<HashMap<String, String>>();	
		    	// rs=stmt.executeQuery("select top 10 interid,buddyname,contenttype from Interactions");
		    	 
		    	
		    		 HashMap<String, String> H=new HashMap<String,String>(); //Manual hashmaps adding in Arraylist
		    		 H.put("interid", "5");
		    		 H.put("buddyname","achandra@actianceengg.onmicrosoft.com" );
		    		 H.put("contenttype", "102");
		    		 AL.add(H);
		    		 H=new HashMap<String,String>();
		    		 H.put("interid", "6");
		    		 H.put("buddyname","achandra@actianceengg.onmicrosoft.com" );
		    		 H.put("contenttype", "102");
		    		 AL.add(H);
		    		 
		    		 H=new HashMap<String,String>();
		    		 H.put("interid", "8");
		    		 H.put("buddyname","achandra@actianceengg.onmicrosoft.com" );
		    		 H.put("contenttype", "107");
		    		 AL.add(H);
		    		 H=new HashMap<String,String>();
		    		 H.put("interid", "8");
	    		 H.put("buddyname","achandra@actianceengg.onmicrosoft.com" );
		    		 H.put("contenttype", "122");
		    		 AL.add(H);
		    		 H=new HashMap<String,String>();
		    		 H.put("interid", "9");
		    		 H.put("buddyname","achandra@actianceengg.onmicrosoft.com" );
		    		 H.put("contenttype", "109");
	    		 
	    		 AL.add(H);
		    			    	
		     //System.out.println(AL);
		    		 Iterator<HashMap<String, String>> itr =AL.iterator(); //iterator for accessing manual Hashmaps and convert into 2-d array
		    		 Object[][] params = new Object[AL.size()][2];
		    		 int count=0;
		    		 while(itr.hasNext())
		    		 {
		    			
		    			 String tc = "TC000" + count; //Testcase no.
		    			
		    			 Object[] result = new Object[] { tc, itr.next() };
		    			   params[count] = result;
		    			   count = count + 1;
		    		 }
		    		 return params; //Returning 2-d object array containing all manual hashmaps along with testcase no.
		    }
		   
		 @Test(dataProvider="Interact") //taking value from Dataprovider one by one
		    public void PrintDate(String tc,HashMap<String, String> hash) throws SQLException {
		   
			 Iterator<HashMap<String, String>> itr=fromDBList.iterator();
			
			 boolean isInteractionPresent = false;
			 boolean isnamesame = false;
			 boolean iscontentsame = false;
			 while(itr.hasNext()){
				 HashMap<String, String> temp=itr.next();
				 //System.out.println("temp=   "+temp);
				 Object flag=true;
				
				 if(hash.get("interid").equalsIgnoreCase(temp.get("interid")))   //checking if all the things are equal or not
				 {
					 isInteractionPresent = true;
					 if(hash.get("buddyname").equalsIgnoreCase(temp.get("buddyname")))
					 {
						 isnamesame = true; 
						 if(hash.get("contenttype").equalsIgnoreCase(temp.get("contenttype")))
								 {
							 iscontentsame=true;
						 System.out.println("interaction matched");
					 Assert.assertEquals(true,flag,"Test case passed");
				
					 }
					 }
				 }
				 
				 
				 
				 
			 }
			 
			 if(isInteractionPresent == false)
				 Assert.assertEquals(true, isInteractionPresent, "Interaction not present in DB");
			 if(isnamesame == false)
				 Assert.assertEquals(true, isnamesame, "name is not same in DB");
			 if(iscontentsame == false)
				 Assert.assertEquals(true, iscontentsame, "content is different in DB");
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
		 rs=stmt.executeQuery("select top 5 interid,buddyname,contenttype from Interactions"); //fetching from database
		 while(rs.next()) 
		    {
			 HashMap<String, String> fromDB = new HashMap<String, String> ();
		    	
		    	
		    	fromDB.put("interid",rs.getString("interid"));
		    	fromDB.put("buddyname",rs.getString("buddyName"));
		    	fromDB.put("contenttype",rs.getString("contenttype"));
		    	fromDBList.add(fromDB); //
	}
		 Iterator<HashMap<String, String>> i=fromDBList.iterator();
		 System.out.println("in before test");
		 while(i.hasNext())
		 {
			 System.out.println(i.next());
		 }
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