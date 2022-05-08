import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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
	
	public static ArrayList<User> getFriends(int id) {
        Connection conn = null;
    	PreparedStatement st = null;
		ResultSet rs = null;
		System.out.println("HELLO");
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("HELLO2");
        String sql = null;
		sql = "SELECT friend2 FROM friend WHERE friend1 = '" + id + "';";
        ArrayList<Integer> friendEmails = new ArrayList<Integer>();
        ArrayList<User> friends = new ArrayList<User>();
        try {
        	st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				friendEmails.add(rs.getInt("friend2"));
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String list = "";
    	if(friendEmails != null)
    	{
    		for(int i = 0; i < friendEmails.size(); i++) {
    			if(i != friendEmails.size() - 1) {
    				list += friendEmails.get(i).toString() + ",";
    			} else {
    				list += friendEmails.get(i).toString();
    			}

    		}
    	}
		sql = String.format("SELECT u.passkey, u.email, p.name, p.name, p.github_profile, p.company_name FROM user AS u, profile_info AS p WHERE u.profile_id = p.profile_id AND u.user_id IN (%s)", list);
        try {
        	st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				friends.add(new User(rs.getString("passkey"), rs.getString("email"), rs.getString("name"), rs.getString("github_profile"), rs.getString("company_name")));
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //TODO get list of business based on the param
        return friends; 

    }
    
    public static ArrayList<User> getFriendRequests(int id) {
        Connection conn = null;
    	PreparedStatement st = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Integer> friendEmails = new ArrayList<Integer>();
		ArrayList<User> friendRequests = new ArrayList<User>();
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
        	sql = "SELECT uffrom FROM friend_request WHERE ufto = '" + id + "';";
        	st = conn.prepareStatement(sql);
        	rs = st.executeQuery();
			while(rs.next()) {
				friendEmails.add(rs.getInt("uffrom"));
			}
			String list = "";
	    	if(friendEmails != null)
	    	{
	    		for(int i = 0; i < friendEmails.size(); i++) {
	    			if(i != friendEmails.size() - 1) {
	    				list += friendEmails.get(i).toString() + ",";
	    			} else {
	    				list += friendEmails.get(i).toString();
	    			}

	    		}
	    	}
			sql = String.format("SELECT u.passkey, u.email, p.name, p.name, p.github_profile, p.company_name FROM user AS u, profile_info AS p WHERE u.profile_id = p.profile_id AND u.user_id IN (%s)", list);
			try {
	        	st = conn.prepareStatement(sql);
				rs = st.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString("name"));
					friendRequests.add(new User(rs.getString("passkey"), rs.getString("email"), rs.getString("name"), rs.getString("github_profile"), rs.getString("company_name")));
				} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return friendRequests; 

    }
}
