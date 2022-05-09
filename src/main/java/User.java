
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class User extends Profile{ 
	private String passkey;
	private String email;
	//private ArrayList<User> friends;
	//private ArrayList<User> friendRequests;
	Connection conn;
	Statement st;
	int rs;
	ResultSet rs2;
	
	public User(String passkey, String email, String username, String github, String company) {
		super(username, github, company);
		this.passkey = passkey;
		this.email = email;

		try {
			conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
        	st = conn.createStatement();
		}
		catch(SQLException ie) {
			ie.printStackTrace();
		}
	}
	
	public void sendRequest(String from, String to) {
		//user.friendRequests.add(this);
		int idfrom = -1; int idto = -1;
		try {
			idfrom = Verify.getUserID(from);
			idto = Verify.getUserID(to);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "INSERT INTO friend_request(uffrom, ufto) VALUES('" + idfrom + "', '" + idto + "');";
		try {
			rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeRequest(String from, String to) {
		//user.friendRequests.remove(this);
		int idfrom = -1; int idto = -1;
		try {
			idfrom = Verify.getUserID(from);
			idto = Verify.getUserID(to);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "DELETE FROM friend_request WHERE uffrom = '" + idfrom + "' AND ufto = '" + idto + "';";
		try {
			rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void acceptRequest(String from, String to) {
		System.out.println("cookies");
		//this.friends.add(user);
		//user.friends.add(this);
		int idfrom = -1; int idto = -1;
		try {
			idfrom = Verify.getUserID(from);
			idto = Verify.getUserID(to);
			
			System.out.println(idfrom);
			System.out.println(idto);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		String sql = "INSERT INTO friend(friend1, friend2) VALUES('" + idfrom + "', '" + idto + "');";
		rs = st.executeUpdate(sql);
		sql = "INSERT INTO friend(friend1, friend2) VALUES('" + idto + "', '" + idfrom + "');";
		rs = st.executeUpdate(sql);
		sql = "DELETE FROM friend_request WHERE uffrom = '" + idto + "' AND ufto = '" + idfrom + "';";
		rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void denyRequest(String from, String to) {
		//this.friendRequests.remove(user);
		int idfrom = -1; int idto = -1;
		try {
			idfrom = Verify.getUserID(from);
			idto = Verify.getUserID(to);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "DELETE FROM friend_request WHERE uffrom = '" + idfrom + "' AND ufto = '" + idto + "';";
		try {
			rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeFriend(String from, String to) {
		//this.friends.remove(user);
		//user.friends.remove(this);
		int idfrom = -1; int idto = -1;
		try {
			idfrom = Verify.getUserID(from);
			idto = Verify.getUserID(to);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		String sql = "DELETE FROM friend WHERE friend1 = '" + idfrom + "' AND friend2 = '" + idto + "';";
		rs = st.executeUpdate(sql);
		sql = "DELETE FROM friend WHERE friend1 = '" + idto + "' AND friend2 = '" + idfrom + "';";
		rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getGithub() {
		return this.github;
	}
	
	public String getCompany() {
		return this.company_name;
	} 
	
	public String getEmail() {
		return this.email;
	} 
	
	public int getUser_Id() {
		String sql = "SELECT user_id FROM user WHERE email = '" + email + "';";
		try {
			rs2 = st.executeQuery(sql);
			rs2.next();
			return rs2.getInt("user_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}
	
}
