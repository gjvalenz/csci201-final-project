import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.Constant;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/api/message/send")
public class SendMessage extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    public String JsonResponse(String error, Boolean success, int id)
    {
    	return String.format("{ \"error\": \"%s\", \"success\": %b, \"messageID\": %d }", error, success, id);
    	
    }
    
    public String JsonResponse(Boolean success, int id)
    {
    	return JsonResponse("", true, id);
    }
    
    public String JsonResponse(String error)
    {
    	return JsonResponse(error, false, -1);
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
    	String p1 = request.getParameter("userID");
    	String mmessage = request.getParameter("message");
    	int uto = Integer.parseInt(p1);
    	if(uto == -1)
    	{
    		out.print(JsonResponse("No valid message recepient"));
    		out.flush();
    		return;
    	}
    	HttpSession session=request.getSession(false);
		if(session != null)
		{
			int ufrom = (int)session.getAttribute("user_id");
			if(ufrom != -1) // valid user
			{
				//String ctime = Constant.sdf.format(new java.util.Date());
				String cstatus = "S";
    			String sql ="INSERT INTO message(ufrom, uto, mmessage, cstatus, ctime) VALUES(?, ?, ?, ?, ?)";
    			try(
    	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    	    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	    	{
    				stmt.setInt(1, ufrom);
    				stmt.setInt(2, uto);
    				stmt.setString(3, mmessage);
    				stmt.setString(4, cstatus);
    				long time = System.currentTimeMillis();
    				String date = Constant.sdf.format(new Date(time));
    				System.out.println(date);
    				//System.out.println(new Date(time).getTime());
    				stmt.setString(5, date);
    				stmt.executeUpdate();
        			ResultSet rs = stmt.getGeneratedKeys();
        			if(rs.next())
        			{
        				if(rs.wasNull())
        				{
        					out.print(JsonResponse("Could not send message. Please try again."));
        					out.flush();
            	    		return;
        				}
        				int message_id = rs.getInt(1);
        				out.print(JsonResponse(true, message_id));
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