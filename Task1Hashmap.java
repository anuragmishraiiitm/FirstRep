package com.actiance;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;	
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
	public class Task1Hashmap {
		// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
		static final String DB_URL = "jdbc:sqlserver://192.168.114.214;databaseName=SP13141;user=sa;password=FaceTime@123";
		 //String JDBC_DRIVER,DB_URL,USER,PASS;
		 //@Parameters({"Driver","Dburl","Username","password"})
		 //@BeforeSuite
		 /*public  void initialize(String JDBC_DRIVER,String DB_URL,String USER,String PASS)
		 {
			 this.JDBC_DRIVER=JDBC_DRIVER;
			 this.DB_URL=DB_URL;
			 this.USER=USER;
			 this.PASS=PASS;
			 
		 }*/
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
			 @DataProvider
			    public Object[][] Interact() throws SQLException
			    {
			    	//System.out.println("hello");
			    	
			    	ArrayList<HashMap<String, String>> AL=new ArrayList<HashMap<String, String>>();	
			    	// rs=stmt.executeQuery("select top 10 interid,buddyname,contenttype from Interactions");
			    	 
			    	
			    		 HashMap<String, String> H=new HashMap<String,String>();
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
//			    		 H=new HashMap<String,String>();
//			    		 H.put("interid", 8);
//			    		 H.put("buddyname","achandra@actianceengg.onmicrosoft.com" );
//			    		 H.put("contenttype", 122);
//			    		 AL.add(H);
//			    		 H=new HashMap<String,Object>();
//			    		 H.put("interid", 11);
//			    		 H.put("buddyname","lupita@actianceengg.onmicrosoft.com" );
//			    		 H.put("contenttype", 101);
//			    		 
//			    		 AL.add(H);
			    			    	
			     //System.out.println(AL);
			    		 Iterator<HashMap<String, String>> itr =AL.iterator();
			    		 Object[][] params = new Object[AL.size()][2];
			    		 int count=0;
			    		 while(itr.hasNext())
			    		 {
			    			
			    			 String tc = "TC000" + count;
			    			
			    			 Object[] result = new Object[] { tc, itr.next() };
			    			   params[count] = result;
			    			   count = count + 1;
			    		 }
			    		 return params;
			    }
			   
			    
			  
			 @Test(dataProvider="Interact",dependsOnMethods={"connect"})
			    public void PrintDate(String tc,HashMap<String, String> hash) throws SQLException //getting value from data provider
			    {
				 System.out.println("hash");
				 System.out.println(hash);
				 rs=stmt.executeQuery("select top 2 interid,buddyname,contenttype from Interactions");
				 //ArrayList<Object> AL=new ArrayList<Object>();
				 
				// ArrayList<Object> BL=new ArrayList<Object>();
				 
//				 Set<Map.Entry<String,Object>> entryset=hash.entrySet();
//				 for(Map.Entry<String,Object>entry:entryset)
//				 {
//					 BL.add(entry.getValue());
//				 }
				
//				 System.out.println("From DP");
//				 System.out.println(BL);
				//Object actual=false;
				ArrayList<HashMap<String, String>> fromDBList =new ArrayList<HashMap<String, String>>();	 
			    while(rs.next()) //making hash using database to compare with hash from dataprovider
			    {
			    	HashMap<String, String> fromDB = new HashMap<String, String> ();
			    	fromDB.put("buddyname",rs.getString("buddyName"));
			    	fromDB.put("interid",rs.getString("interid"));
			    	fromDB.put("contenttype",rs.getString("contenttype"));
			    	fromDBList.add(fromDB);
//			    	AL.add(rs.getString("buddyname"));
//			    	AL.add(rs.getInt("interid"));
//			    	AL.add( rs.getInt("contenttype"));
//			    	 Iterator<Object> itrbl=BL.iterator();
//			    	Iterator<Object> itral=AL.iterator();
//			    	int count=0;
//			    	while(itrbl.hasNext()&&itral.hasNext())
//			    	{
//			    		if(itrbl.next()==itral.next()){
//			    			count++;
//			    		}
//			    	}
			    //	System.out.println("count= "+count+"    Hashmap is  "+hash);
//			    	if(count==3){
//			    		actual=true;
//			    		break;
//			    	}
			    //Assert.assertEquals(true, actual);	
			    }
			    System.out.println("From DB");
			    System.out.println(fromDBList);
			    
			    String interid = (String) hash.get("interid");
			    Iterator itr = fromDBList.iterator();
			    while(itr.hasNext())
			    {
			    	HashMap<String, String> temp = (HashMap<String, String>) itr.next();
			    	System.out.println("temp from DP  "+temp);
			    	if(interid.equalsIgnoreCase( temp.get("interid")))
			    	{
			    	System.out.println( temp.get("interid"));
			    		System.out.println("Interaction matched");
			    		String buddyName = hash.get("buddyname");
			    		String contenttype = hash.get("contenttype");
			    		System.out.println(buddyName.equalsIgnoreCase(temp.get("buddyname")));
			    		System.out.println(contenttype);
			    		System.out.println(temp.get("contenttype"));
			    		System.out.println(contenttype.equalsIgnoreCase(temp.get("contenttype")));
			    		if(buddyName.equalsIgnoreCase(temp.get("buddyname")) && contenttype.equalsIgnoreCase(temp.get("contenttype")))
			    		{
			    			System.out.println("Interaction found and verified");
			    		}
			    		else
			    		{
			    			System.out.println("Data mismatch in interaction");
			    		}
			    		
			    		
			    	}
			    	else
			    	{
			    		System.out.println( temp.get("interid") + " interaction didnt match");
			    	}
			    	
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
			    
