
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Message;

//test purposes only
@WebServlet("/message")
public class MessagesTestDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException
	{
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "SELECT message_id, ufrom, uto, cstatus, ctime, mmessage FROM message WHERE (ufrom = ? AND uto = ?) OR (uto = ? AND ufrom = ?)";
		HttpSession session = request.getSession(false);
		System.out.println(request.getParameter("id"));
		System.out.println(request.getParameter("name"));
		int theirId = Integer.parseInt(request.getParameter("id"));
;		String theirName = request.getParameter("name");
		request.setAttribute("their_id", theirId);
		request.setAttribute("their_name", theirName);
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
	    		request.getRequestDispatcher("/index3.jsp").include(request, response);

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
	    		request.getRequestDispatcher("/index3.jsp").include(request, response);
			}
		}
		else
		{
			System.out.println("has no session ");
			request.setAttribute("error", "User is not logged in!");
			request.setAttribute("user_id", -1);
			request.setAttribute("name", "");
    		request.getRequestDispatcher("/index3.jsp").include(request, response);

		}
		try
    	{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	} catch(Exception e)
    	{
    		request.setAttribute("error", e.getMessage());
    		return;
    	}
    	try(
    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	{
    		int user_id = (int)session.getAttribute("user_id");
    		String myName = (String)session.getAttribute("name");
    		stmt.setInt(1, user_id);
    		stmt.setInt(2, theirId);
    		stmt.setInt(3, user_id);
    		stmt.setInt(4, theirId);
    		ResultSet rs = stmt.executeQuery();
    		ArrayList<Message> messages = new ArrayList<Message>();
    		//String messages = "";
    		while(rs.next())
    		{
    			if(!rs.wasNull())
    			{
    				int message_id = rs.getInt(1);
    				int ufrom = rs.getInt(2);
    				String fromName = (ufrom == user_id ? myName : theirName);
    				int uto = rs.getInt(3);
    				String toName = (uto == user_id ? myName : theirName);
    				String cstatus = rs.getString(4);
    				String ctime = rs.getString(5);
    				//long time = rs.getDate(5).getTime();
    				//System.out.println(time);
    				String mmessage = rs.getString(6);
    				Message m = new Message(message_id, mmessage, ctime, cstatus, ufrom, uto);
    				m.setNames(fromName, toName);
    				messages.add(m);
    				//String message = message_id + ":[" + mmessage + "]; " + ufrom + "->" + uto + ", " + ctime + "[" + "], " + cstatus + "<br/>";
    				//System.out.println(user);
    				//messages += message;
    			}
    		}
			messages.sort((Message m1, Message m2) -> m1.time.compareTo(m2.time));
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
