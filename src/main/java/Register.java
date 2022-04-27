import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Util.Constant;

/**
 * API
 */
@WebServlet("/api/user/register")
public class Register extends HttpServlet {
	public void init() throws ServletException
	{
		
	}
	
    public static Boolean anyNull(String... strs)
    {
    	for(String s: strs)
    		if(s == null || s.isBlank())
    			return true;
    	return false;
    }
    
    public String JsonResponse(String error, Boolean success, int id)
    {
    	return String.format("{ \"error\": \"%s\", \"success\": %b, \"user\": %d}", error, success, id);
    	
    }
    
    public String JsonResponse(Boolean success, int id)
    {
    	return JsonResponse("", true, id);
    }
    
    public String JsonResponse(String error)
    {
    	return JsonResponse(error, false, -1);
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String email = request.getParameter("email");
		String github = request.getParameter("github");
		String company = request.getParameter("company");
		if(Register.anyNull(name, password, confirmPassword, email))
		{
			System.out.println(String.format("%s, %s, %s, %s", name, password, confirmPassword, email));
			out.print(JsonResponse("A required field is empty"));
			out.flush();
    		return;
		}
		if(!password.equals(confirmPassword))
		{
			out.print(JsonResponse("Password differ"));
			out.flush();
    		return;
		}
		/* example on how to use database*/
		String sql = "SELECT user_id FROM user where email = ?";
		String sql2 = String.format("INSERT INTO profile_info(name, github_profile, company_name) VALUES('%s', '%s', '%s')", name, github, company);
		try
    	{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	} catch(Exception e)
    	{
    		out.print(JsonResponse("Could not connect to database. Try again"));
			out.flush();
    		return;
    	}
    	try(
    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	{
    		stmt.setString(1, email);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next())
    		{
    			if(!rs.wasNull())
    			{
    				out.print(JsonResponse("User already exists"));
    				out.flush();
    	    		return;
    			}
    		}
    		Statement s2 = conn.createStatement();
    		s2.executeUpdate(sql2, Statement.RETURN_GENERATED_KEYS);
    		ResultSet r = s2.getGeneratedKeys();
    		if(r.next())
    		{
    			if(r.wasNull())
    			{
    				out.print(JsonResponse("Could not create profile"));
    				out.flush();
    	    		return;
    			}
    			int profile_id = r.getInt(1);
    			String sql3 = String.format("INSERT INTO user(passkey, email, profile_id) VALUES('%s', '%s', %d)", password, email, profile_id);
    			Statement s3 = conn.createStatement();
    			s3.executeUpdate(sql3, Statement.RETURN_GENERATED_KEYS);
    			ResultSet r2 = s3.getGeneratedKeys();
    			if(r2.next())
    			{
    				if(r2.wasNull())
    				{
    					out.print(JsonResponse("Could not create user"));
    					out.flush();
        	    		return;
    				}
    				int user_id = r2.getInt(1);
    				out.print(JsonResponse(true, user_id));
    				out.flush();
    				return;
    			}
    			else
    			{
    				out.print(JsonResponse("Could not create user"));
    				out.flush();
    	    		return;
    			}
    		}
    		else
    		{
    			out.print(JsonResponse("Could not create profile"));
    			out.flush();
	    		return;
    		}
    	} catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println(e.getStackTrace());
    		out.print(JsonResponse(e.getMessage()));
			out.flush();
			return;
    	}
	}
	
	public void destroy() {
		
	}
}
