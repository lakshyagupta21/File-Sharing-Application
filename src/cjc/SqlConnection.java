package cjc;

import java.sql.*;
import java.util.HashMap;
import java.util.Date ;

import javax.servlet.RequestDispatcher;
public class SqlConnection {
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static protected HashMap<String,String> usPass  = new HashMap <String,String>();
		static final String DB_URL = "jdbc:mysql://localhost:3306/LAB";
		//  Database credentials
		static final String USER = "root";
		static final String PASS = "root";
		public static Connection conn ;
		public SqlConnection(){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				System.out.println("SQLCONNECTION");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static Connection connect_db()
		{ 
		   	try{
		   		System.out.println("CLASS YO");
		   		createDatabase();
		   		accessUsPass();
		   		}
		   		catch(SQLException se1){                      //Handle errors for JDBC
		   			se1.printStackTrace();
		   		}
		   		catch(Exception e){                            //Handle errors for Class.forName
		   			e.printStackTrace();
		   		}
		   		return conn ;
		}
		private static void createDatabase() {
			// TODO Auto-generated method stub
			try {
				Statement st = conn.createStatement();
				st.execute("CREATE TABLE IF NOT EXISTS USERDATA(FIRSTNAME VARCHAR(10) , LASTNAME VARCHAR(10) , USERNAME VARCHAR(10) , PASSWORD VARCHAR(15) , EMAILID VARCHAR(15) , GENDER VARCHAR(1) , DOB DATE);");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		private static void accessUsPass() throws SQLException {
			// TODO Auto-generated method stub
			PreparedStatement ps = conn.prepareStatement("Select * from USERDATA;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String user = rs.getString("USERNAME");
				String pass = rs.getString("PASSWORD");
				usPass.put(user, pass);
			}
			ps.close();
			
		}
		public void dataUpdate(UserDetails u){
			String query = "INSERT INTO USERDATA VALUES (?,?,?,?,?,?,?) ;" ;
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, u.getFirstname());
				ps.setString(2, u.getLastname());
				ps.setString(3, u.getUsername());
				ps.setString(4,u.getPassword() );
				ps.setString(5, u.getEmailId());
				ps.setString(6, u.getG());
				ps.setDate(7, new java.sql.Date(u.getDob().getTime()));
				ps.executeUpdate();
				System.out.println("Finally sign up ");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			};
			try {
				ps.close();
				//conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public UserDetails getUserDetails(String uname){
			String query = "SELECT * FROM USERDATA WHERE USERNAME = ?;" ;
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, uname);
				System.out.println("UNAME"+uname);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					System.out.println("LOOP");
					String fname = rs.getString("FIRSTNAME");
					String lname = rs.getString("LASTNAME");
					Date DOB = rs.getDate("DOB");
					String email = rs.getString("EMAILID");
					String gender = rs.getString("GENDER");
					System.out.println(fname+lname+DOB+email+gender);
					UserDetails udetails = new UserDetails();
					udetails.setEmailId(email);
					udetails.setDob(DOB);
					udetails.setFirstname(fname);
					udetails.setLastname(lname);
					udetails.setG(gender);
					udetails.setUsername(uname);
					return udetails;
				}
				else{
					System.out.println("Not able to find data");
				}
				System.out.println("Finally ");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			};
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return null;
		}
		public ResultSet getSentMessages(String user) throws SQLException{
			Statement statement=conn.createStatement();
			String sql ;
			sql="SELECT * FROM TRANSFER WHERE SENDERS = '" + user +"';";
			ResultSet resultSet = statement.executeQuery(sql);
			return resultSet ; 
		}
		public ResultSet getRecieveMessages(String user) throws SQLException{
			Statement statement=conn.createStatement();
			String sql ;
			sql="SELECT * FROM TRANSFER WHERE RECIEVERS = '" + user +"';";
			ResultSet resultSet = statement.executeQuery(sql);
			return resultSet ; 
		}
		public ResultSet downloadFile(int id) throws SQLException{
			String sql = "SELECT * FROM TRANSFER WHERE ID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
			return result;
		}
		public int addFriend(String friend_user) throws SQLException{
			Statement statement=conn.createStatement();
			String sql =  "INSERT INTO FRIENDS SELECT * FROM (SELECT '"+ UserName.USERNAME +"','"+ friend_user +"') AS TMP WHERE NOT EXISTS (SELECT FRIENDTEMP.FRIEND FROM (SELECT A.FOLLOWING AS FRIEND FROM FRIENDS A WHERE A.FOLLOWED = '"+ UserName.USERNAME +"' UNION SELECT B.FOLLOWED FROM FRIENDS B WHERE B.FOLLOWING = '"+ UserName.USERNAME +"')FRIENDTEMP WHERE FRIENDTEMP.FRIEND = '" + friend_user +"') LIMIT 1;";
			int resultset = statement.executeUpdate(sql);
			return resultset ;
		}
		public ResultSet getFriendlist() throws SQLException{
			Statement statement=conn.createStatement();
			String sql = "SELECT A.FOLLOWING AS FRIEND FROM FRIENDS A WHERE A.FOLLOWED = '"+ UserName.USERNAME +"' UNION SELECT B.FOLLOWED FROM FRIENDS B WHERE B.FOLLOWING = '"+ UserName.USERNAME +"';";
			ResultSet resultset = statement.executeQuery(sql); 
			return resultset; 
		}
		public ResultSet searchUser(String pid) throws SQLException{
			Statement statement=conn.createStatement();
			String query = "SELECT * FROM USERDATA WHERE USERNAME LIKE '%" + pid + "%' ";
			ResultSet rs = statement.executeQuery(query);
			return rs;
		}
		public void closeConnection() throws SQLException{
			conn.close();
		}
}//end FirstExample
