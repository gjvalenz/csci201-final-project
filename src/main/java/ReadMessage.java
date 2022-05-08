
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/api/message/read")
public class ReadMessage extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    public String JsonResponse(String error, Boolean success)
    {
    	return String.format("{ \"error\": \"%s\", \"success\": %b }", error, success);
    	
    }
    
    public String JsonResponse(Boolean success)
    {
    	return JsonResponse("", true);
    }
    
    public String JsonResponse(String error)
    {
    	return JsonResponse(error, false);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
    	String p1 = request.getParameter("messageID");
    	int message_id = Integer.parseInt(p1);
    	if(message_id == -1)
    	{
    		out.print(JsonResponse("Failed to mark message as read"));
    		out.flush();
    		return;
    	}
    	HttpSession session=request.getSession(false);
    	try
    	{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	} catch(Exception e)
    	{
    		out.print(JsonResponse("Could not connect to database. Try again"));
			out.flush();
    		return;
    	}
		if(session != null)
		{
			int ufrom = (int)session.getAttribute("user_id");
			if(ufrom != -1) // valid user
			{
				String ctime = Constant.sdf.format(new java.util.Date());
				String cstatus = "S";
    			String sql = String.format("UPDATE message SET cstatus='%s' WHERE message_id=%d", "R", message_id);
    			try(
    	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    	    			Statement stmt = conn.createStatement();)
    	    	{
    				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        			ResultSet rs = stmt.getGeneratedKeys();
        			if(rs.next())
        			{
        				if(rs.wasNull())
        				{
        					out.print(JsonResponse("Could not mark message as read."));
        					out.flush();
            	    		return;
        				}
        				out.print(JsonResponse(true));
        				out.flush();
        				return;
        			}
        			else
        			{
        				out.print(JsonResponse("Could not mark message as read."));
    					out.flush();
        	    		return;
        			}
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
