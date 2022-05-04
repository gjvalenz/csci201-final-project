

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import Util.Constant;


public class User extends Profile{ 
	private String passkey;
	private String email;
	//private ArrayList<User> friends;
	//private ArrayList<User> friendRequests;
	Connection conn;
	Statement st;
	int rs;
	
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
	
	public void sendRequest(User user) {
		//user.friendRequests.add(this);
		String sql = "INSERT INTO friend_request(uffrom, ufto) VALUES('" + this.email + "', '" + user.email + "');";
		try {
			rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeRequest(User user) {
		//user.friendRequests.remove(this);
		String sql = "REMOVE FROM friend_request WHERE uffrom = '" + this.email + "' AND ufto = '" + user.email + "';";
		try {
			rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void acceptRequest(User user) {
		//this.friends.add(user);
		//user.friends.add(this);
		try {
		String sql = "INSERT INTO friend(friend1, friend2) VALUES('" + this.email + "', '" + user.email + "');";
		rs = st.executeUpdate(sql);
		sql = "INSERT INTO friend(friend1, friend2) VALUES('" + user.email + "', '" + this.email + "');";
		rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void denyRequest(User user) {
		//this.friendRequests.remove(user);
		String sql = "REMOVE FROM friend_request WHERE uffrom = '" + user.email + "' AND ufto = '" + this.email + "';";
		try {
			rs = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void removeFriend(User user) {
		//this.friends.remove(user);
		//user.friends.remove(this);
		try {
		String sql = "REMOVE FROM friend WHERE friend1 = '" + this.email + "' AND friend2 = '" + user.email + "';";
		rs = st.executeUpdate(sql);
		sql = "REMOVE FROM friend WHERE friend1 = '" + user.email + "' AND friend2 = '" + this.email + "';";
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
	
	public String getEmail(){
		return this.email;
	}
	
}
