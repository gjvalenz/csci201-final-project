
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//test purposes only
@WebServlet("/users")
public class GetAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException
	{
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "SELECT user_id, passkey, email, profile_id FROM user";
		HttpSession session = request.getSession(false);
		String res = "";
		if(session!= null)
		{
			int user_id_ = (int)session.getAttribute("user_id");
		    int profile_id_ = (int)session.getAttribute("profile_id");
		    String email_ = (String)session.getAttribute("email");
		    String name_ = (String)session.getAttribute("name");
		    res =  name_ + "(" + email_ + ") has user_id " + Integer.toString(user_id_) + " and profile_id " + Integer.toString(profile_id_);
		}
		try
    	{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	} catch(Exception e)
    	{
    		request.setAttribute("error", e.getMessage());
    		request.getRequestDispatcher("/users.jsp").include(request, response);
    		return;
    	}
    	try(
    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	{

    		ResultSet rs = stmt.executeQuery();
    		String users = "";
    		while(rs.next())
    		{
    			if(!rs.wasNull())
    			{
    				String user_id = Integer.toString(rs.getInt(1));
    				String passkey = rs.getString(2);
    				String email = rs.getString(3);
    				String profile_id = Integer.toString(rs.getInt(4));
    				String user = user_id + ", " + passkey + ", " + email + ", " + profile_id + "<br/>";
    				//System.out.println(user);
    				users += user;
    			}
    		}
    		request.setAttribute("users", users);
    		request.setAttribute("info", res);
    		request.getRequestDispatcher("/users.jsp").include(request, response);
    			
    	} catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println(e.getStackTrace());
    		request.setAttribute("error", e.getMessage());
    		request.getRequestDispatcher("/users.jsp").include(request, response);
			return;
    	}
	}
	public void destroy() {
		
	}
}
