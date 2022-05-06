import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Util.Constant;

//import Util.*;

public class GetProfile {
	
	public GetProfile(){
	}
	
	public static User getProfile(String email) {
    	Connection conn = null;
    	Statement st = null;
		ResultSet rs = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
        	st = conn.createStatement();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "SELECT * FROM user INNER JOIN profile_info ON user.profile_id = profile_info.profile_id WHERE email = '" + email+ "';";
        User user = null;
        try {
			rs = st.executeQuery(sql);
			rs.next();
			user = new User(rs.getString("passkey"), rs.getString("email"), rs.getString("name"), rs.getString("github_profile"), rs.getString("company_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //TODO return business based on id
        return user;
    }

    /**
     * @param keyWord    the search keyword
     * @param sort       the sort option (price, review count, rating)
     * @param searchType search in category or name
     * @return the list of business matching the criteria
     */
    public static ArrayList<User> getProfiles(String keyWord, String searchType) {
        Connection conn = null;
    	Statement st = null;
		ResultSet rs = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
        	st = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "SELECT * FROM user INNER JOIN profile_info ON user.profile_id = profile_info.profile_id";
        if(!keyWord.equals("") && searchType.equals("name")) {
        	
        	sql += " WHERE profile_info.name LIKE '%" + keyWord + "%'";
        } 
        else if(!keyWord.equals("") && searchType.equals("company")) {
        	
        	sql += " WHERE profile_info.company_name LIKE '%" + keyWord + "%'";
        }
  
        sql += ";";
        ArrayList<User> profiles = new ArrayList<User>();
        try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				User user = new User(rs.getString("passkey"), rs.getString("email"), rs.getString("name"), rs.getString("github_profile"), rs.getString("company_name"));
				profiles.add(user);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //TODO get list of business based on the param
        return profiles; 

    }
	
	public static ArrayList<User> getFriends(String email) {
        Connection conn = null;
    	Statement st = null;
		ResultSet rs = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
        	st = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "SELECT * FROM friend WHERE friend1 = '" + email + "';";
        ArrayList<String> friendEmails = new ArrayList<String>();
        try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				friendEmails.add(rs.getString("friend2"));
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ArrayList<User> friends = new ArrayList<User>();
        try {
        	for(String user: friendEmails) {
        		sql = "SELECT * FROM user INNER JOIN profile_info ON user.profile_id = profile_info.profile_id WHERE user.email = '" + user + "';";
        		ResultSet rs2 = st.executeQuery(sql);
        		User friend = new User(rs2.getString("passkey"), rs2.getString("email"), rs2.getString("name"), rs2.getString("github_profile"), rs2.getString("company_name"));
				friends.add(friend);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //TODO get list of business based on the param
        return friends; 

    }
    
    public static ArrayList<User> getFriendRequests(String email) {
        Connection conn = null;
    	Statement st = null;
		ResultSet rs = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
        	st = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "SELECT * FROM friend_request WHERE ufto = '" + email + "';";
        ArrayList<String> friendEmails = new ArrayList<String>();
        try {
			rs = st.executeQuery(sql);
			while(rs.next()) {
				friendEmails.add(rs.getString("uffrom"));
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ArrayList<User> friendRequests = new ArrayList<User>();
        try {
        	for(String user: friendEmails) {
        		sql = "SELECT * FROM user INNER JOIN profile_info ON user.profile_id = profile_info.profile_id WHERE user.email = '" + user + "';";
        		ResultSet rs2 = st.executeQuery(sql);
        		User friendRequest = new User(rs2.getString("passkey"), rs2.getString("email"), rs2.getString("name"), rs2.getString("github_profile"), rs2.getString("company_name"));
				friendRequests.add(friendRequest);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //TODO get list of business based on the param
        return friendRequests; 

    }
}
