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
}
