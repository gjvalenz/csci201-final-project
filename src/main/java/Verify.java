//package Util; // commented out

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import java.sql.ResultSet; 

import java.sql.Connection; 

import java.sql.DriverManager; 

import java.sql.Statement; 
import java.util.Scanner; 

import java.sql.*; 

import java.util.*; 

import javax.servlet.RequestDispatcher;



public class Verify{
    /**
     * check if name is valid
     *
     * @param name the name user provides
     * @return valid or not valid
     */
    public static boolean isValidName(String name) {
    	if(name==null) {
    		return false;
    	}
    	//check if it contains non alphabetic characters / non letters
    	
    	 return Constant.namePattern.matcher(name).matches();
    	
    }
    
    public static boolean isValidGithub(String github) {
    	if(github==null) {
    		return false;
    	}
    	//check if it contains non alphabetic characters / non letters
    	
    	 return Constant.namePattern.matcher(github).matches();
    	
    }
    
    public static boolean isValidCompany(String company) {
    	if(company==null) {
    		return false;
    	}
    	//check if it contains non alphabetic characters / non letters
    	
    	 return Constant.namePattern.matcher(company).matches();
    	
    }
    
 
 	//check if password is valid (i.e. not null)
    public static boolean isValidPassword(String password) {
    	if(password==null) {
    		return false;
    	}
        return Constant.namePattern.matcher(password).matches();
    }

    /**
     * check if email is valid
     *
     * @param email the email user provides
     * @return valid or not valid
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        else if(email.indexOf('@')==-1) { //does not contain @
        	return false;
        }
        else if(!email.contains(".com") && !email.contains(".net") && !email.contains(".edu")) { 
        	return false;
        }
        else if(!email.contains(".")) { //is this enough?
        	return false;
        }
        return Constant.emailPattern.matcher(email).matches();
    }

    /**
     * Get username with the email
     *
     * @param email
     * @return userName
     * @throws SQLException
     */
    public static String getUserName(String email) throws SQLException {
        //TODO
    	
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        Connection connection = null;
        
        try {
			connection = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword); //XXX
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        String SQL = "SELECT name FROM final.profile_info INNER JOIN final.user ON final.profile_info.profile_id=final.user.profile_id WHERE email='" + email +"';";
        
        
        /*SELECT * FROM PA4Users.Restaurant INNER JOIN PA4Users.Category ON PA4Users.Category.restaurant_id=PA4Users.Restaurant.restaurant_id INNER JOIN PA4Users.Restaurant_details ON PA4Users.Restaurant_details.details_id=restaurant.id INNER JOIN PA4Users.Rating_details ON PA4Users.Rating_details.rating_id=PA4Users.Restaurant.id"
        				+ " WHERE category_name=*/
        
        Statement stmt = null;
        
        stmt = connection.createStatement();
        
        ResultSet rs = null;
        
        rs = stmt.executeQuery(SQL);
        
        String s = null;
        
        if(rs.next()) {
        	s = rs.getString("name"); 
        }
        
        return s;
        
        
    }

    /**
     * Get userID with email
     *
     * @param email
     * @return userID
     * @throws SQLException
     */
    public static int getUserID(String email) throws SQLException {
        //TODO
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        Connection connection = null;
        
        try {
			connection = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword); //user,password
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        String SQL = "SELECT user_id FROM final.user WHERE email='" + email +"';";
        
        Statement stmt = null;
        
        stmt = connection.createStatement();
        
        ResultSet rs = null;
        
        rs = stmt.executeQuery(SQL);
        
        
        int s = -1;
        
        if(rs.next()) {
        	s = rs.getInt("user_id"); 
        }
        
        return s;
    	
    	
    
    }

    /**
     * check if the email and password matches
     *
     * @param email
     * @param password
     */
    public static boolean verifyPassword(String email, String passkey) throws SQLException {
        //TODO
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Connection connection = null;
    	
    	String SQL = "SELECT passkey FROM final.user WHERE email='" + email +"';";
         
    	try {
			connection = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword); //"com.mysql.jdbc.Driver", url, username, password
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
         Statement stmt = null;
         
         stmt = connection.createStatement();
         
         ResultSet rs = null;
         
         rs = stmt.executeQuery(SQL);
         
         
         String s = null;
         
         if(rs.next()) {
        	 s = rs.getString("passkey");
         }
         
         if(s==null) {
 			return false;
 		}
         if(s.equals(passkey)) {
        	 return true;
         }

        return false;
    }

    /**
     * Check if email is already registered
     *
     * @param email
     * @param request
     * @param response
     * @return email registered or not
     * @throws ServletException
     * @throws IOException
     */
    public static boolean emailRegistered(String email, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO

    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Connection connection = null;
		try {
			connection = DriverManager.getConnection(Constant.DBURL,  Constant.DBUserName, Constant.DBPassword); //"com.mysql.jdbc.Driver", url, username, password
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //"jdbc:mysql://localhost/tutorial"
    	Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String SQL = "SELECT * FROM user WHERE email='" + email +"'";
    	ResultSet rs = null;
    	
    	//boolean r = false;
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String s = null;
		
		try {
			if(rs.next()) {
			 s = rs.getString("email");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		if(s==null) {
			return false;
		}
		else if(s.equals(email)) {
       	 return true;
        }
        
        
        return false;
    }
}
