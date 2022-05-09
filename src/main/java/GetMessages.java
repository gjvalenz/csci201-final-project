
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/api/messages/received")
public class GetMessages extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    public String asJSONArray(ArrayList<Message> ms)
    {
    	String array = "[";
    	for(Message m: ms)
    		array += m.asJSON() + ',';
    	if(ms.size() > 0)
    		array = array.substring(0, array.length() - 1);
    	array += "]";
    	return array;
    }
    
    public String JsonResponse(String error, Boolean success, ArrayList<Message> messages)
    {
    	return String.format("{ \"error\": \"%s\", \"success\": %b, \"messages\": %s }", error, success, asJSONArray(messages));
    	
    }
    
    public String JsonResponse(Boolean success, ArrayList<Message> messages)
    {
    	return JsonResponse("", true, messages);
    }
    
    public String JsonResponse(String error)
    {
    	return JsonResponse(error, false, new ArrayList<Message>());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
    	HttpSession session=request.getSession(false);
		if(session != null)
		{
			int user_id = (int)session.getAttribute("user_id");
			if(user_id != -1) // valid user
			{
				String sql = "SELECT message_id, mmessage, ctime, cstatus, ufrom, uto FROM message where uto = ? or ufrom = ?";
    			try(
    	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    	    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	    	{
    				stmt.setInt(1, user_id);
    				stmt.setInt(2, user_id);
    				ArrayList<Message> messages = new ArrayList<Message>();
    				ResultSet rs = stmt.executeQuery();
    				while(rs.next())
    				{
    					if(!rs.wasNull())
    					{
    						int message_id = rs.getInt(1);
    						String msg = rs.getString(2);
    						String date = rs.getString(3);
    						String status = rs.getString(4);
    						int from = rs.getInt(5);
    						int to = rs.getInt(6);
    						messages.add(new Message(message_id, msg, date, status, from, to));
    					}
    				}
    				out.print(JsonResponse(true, messages));
    				out.flush();
    				return;
    	    	}
    			catch(SQLException e)
    			{
    				System.err.println(e.getMessage());
    				e.printStackTrace();
    				out.print(JsonResponse("SQL error."));
    				out.flush();
    				return;
    			}
			}
			else // invalid user
			{
				session.invalidate();
				out.print(JsonResponse("Invalid session."));
				out.flush();
				return;
			}
		}
		else
		{
			out.print(JsonResponse("No user logged in with this session."));
			out.flush();
			return;
		}
    }

}
