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

import Util.Constant;

//test purposes only
@WebServlet("/message")
public class MessagesTestDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException
	{
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "SELECT message_id, ufrom, uto, cstatus, ctime, mmessage FROM message";
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			if(session.getAttribute("user_id") != null)
			{
				System.out.println("has session with id " + Integer.toString((int)session.getAttribute("user_id")));
				int user_id_ = (int)session.getAttribute("user_id");
				request.setAttribute("user_id", user_id_);
			}
			else
			{
				System.out.println("has no session ");
				request.setAttribute("error", "User not logged in.");
				request.setAttribute("user_id", -1);
			}
			if(session.getAttribute("name") != null)
			{
			    String name_ = (String)session.getAttribute("name");
				request.setAttribute("name", name_);
			}
			else
			{
				request.setAttribute("error", "User not logged in.");
				request.setAttribute("name", "");
			}
		}
		else
		{
			System.out.println("has no session ");
			request.setAttribute("error", "User is not logged in!");
			request.setAttribute("user_id", -1);
			request.setAttribute("name", "");
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
    		String messages = "";
    		while(rs.next())
    		{
    			if(!rs.wasNull())
    			{
    				String message_id = Integer.toString(rs.getInt(1));
    				String ufrom = Integer.toString(rs.getInt(2));
    				String uto = Integer.toString(rs.getInt(3));
    				String cstatus = rs.getString(4);
    				String ctime = rs.getString(5);
    				//long time = rs.getDate(5).getTime();
    				//System.out.println(time);
    				String mmessage = rs.getString(6);
    				String message = message_id + ":[" + mmessage + "]; " + ufrom + "->" + uto + ", " + ctime + "[" + "], " + cstatus + "<br/>";
    				//System.out.println(user);
    				messages += message;
    			}
    		}
    		request.setAttribute("messages", messages);
    		request.getRequestDispatcher("/message.jsp").include(request, response);
    			
    	} catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println(e.getStackTrace());
    		request.setAttribute("error", e.getMessage());
    		request.getRequestDispatcher("/message.jsp").include(request, response);
			return;
    	}
	}
	public void destroy() {
		
	}
}
