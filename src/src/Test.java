package src;
import java.sql.*;

public class Test {
		// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/LAB";
	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	   public static void main(String[] args)
	   { 
		   	Connection conn = null;
		   	Statement stmt = null;
		   	try{
		   		//STEP 2: Register JDBC driver
		   		Class.forName("com.mysql.jdbc.Driver");

		   		//STEP 3: Open a connection
		   		System.out.println("Connecting to database...");
		   		conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   		//STEP 4: Execute a query
		   		System.out.println("Creating statement...");
		   		stmt = conn.createStatement();
		   		String sql;
		   		sql = "INSERT INTO EMP(FNAME, LNAME) VALUES(\"LAKSHYA\",\"GUPTA\") ; ";
		   		ResultSet rs = stmt.executeQuery(sql);
		   		int i = stmt.executeUpdate(sql);

		   		//STEP 5: Extract data from result set
		   		while(rs.next()){
		   			//Retrieve by column name
		   			//int id  = rs.getInt("id");
		   			//int age = rs.getInt("age");
		   			String first = rs.getString("FNAME");
		   			String last = rs.getString("LNAME");
		   			//System.out.println("NUMBER OF ROWS : " + i );
		   			//Display values
		   			//System.out.print("ID: " + id);
		   			//System.out.print(", Age: " + age);
		   			System.out.print("First: " + first);
		   			System.out.println(" Last: " + last);
		   			//STEP 6: Clean-up environment
		   			
		   		}
		   		String stmt2 = "SELECT * FROM EMP ;";
		   		rs.close();
	   			stmt.close();
	   			conn.close();
		   	}
		   		catch(SQLException se1){                      //Handle errors for JDBC
		   			se1.printStackTrace();
		   		}
		   		catch(Exception e){                            //Handle errors for Class.forName
		   			e.printStackTrace();
		   		}
		   		finally{ //finally block used to close resources
		   			try{
		   				if(stmt!=null)
		   					stmt.close();
		   			}
		   			catch(SQLException se2){
		   			}// nothing we can do
		   			try{
		   				if(conn!=null)
		   					conn.close();
		   			}
		   			catch(SQLException se3){
		   				se3.printStackTrace();
		   			}
		   		}//end finally
		   	}
}//end FirstExample