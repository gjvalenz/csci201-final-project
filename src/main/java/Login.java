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
import javax.servlet.http.HttpSession;

import Util.Constant;

/**
 * API
 */
@WebServlet("/api/user/login")
public class Login extends HttpServlet {
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
    
    public String JsonResponse(String error, Boolean success, String id)
    {
    	return String.format("{ \"error\": \"%s\", \"success\": %b, \"user\": \"%s\"}", error, success, id);
    	
    }
    
    public String JsonResponse(Boolean success, String id)
    {
    	return JsonResponse("", true, id);
    }
    
    public String JsonResponse(String error)
    {
    	return JsonResponse(error, false, "-1");
    }

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		if(Register.anyNull(email, password))
		{
			out.print(JsonResponse("A required field is empty"));
			out.flush();
    		return;
		}
		/* example on how to use database*/
		String sql = "SELECT user_id, profile_id, name, github FROM user where email = ? and password = ?";
		//String sql2 = String.format("INSERT INTO profile_info(name, github_profile, company_name) VALUES('%s', '%s', '%s')", name, github, company);
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
    		stmt.setString(2, password);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next())
    		{
    			if(!rs.wasNull())
    			{
    				int user_id = rs.getInt(1);
    				int profile_id = rs.getInt(2);
    				String name = rs.getString(3);
    				String github = rs.getString(4);
 
    				HttpSession session=request.getSession();
    				session.setAttribute("user_id", user_id);
    				session.setAttribute("profile_id", profile_id);
    				session.setAttribute("email", email);
    				session.setAttribute("name", name);
    				session.setAttribute("github", github);
    				out.print(JsonResponse(true, session.getId()));
    				out.flush();
    				return;
    			}
    			else
    			{
    				out.print(JsonResponse("Invalid email or password."));
    				out.flush();
    				return;
    			}
    		}
    		else
    		{
    			out.print(JsonResponse("Invalid email or password."));
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
