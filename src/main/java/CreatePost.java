
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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/api/post/create")
public class CreatePost extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    public String JsonResponse(String error, Boolean success, int id)
    {
    	return String.format("{ \"error\": \"%s\", \"success\": %b, \"postID\": %d }", error, success, id);
    	
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
    	String body = request.getParameter("body"); 
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
			if(session.getAttribute("user_id") != null) // valid user
			{
				int user_id = (int)session.getAttribute("user_id");
				if(user_id == -1)
				{
					session.invalidate();
					out.print(JsonResponse("Invalid session."));
					out.flush();
					return;
				}
				String name = (String) session.getAttribute("name");
    			String sql ="INSERT INTO post(body, puser, ctime, uname, like_count) VALUES(?, ?, ?, ?, ?)";
    			try(
    	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    	    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	    	{
    				stmt.setString(1, body);
    				stmt.setInt(2, user_id);
    				long time = System.currentTimeMillis();
    				String date = Constant.sdf.format(new Date(time));
    				stmt.setString(3, date);
    				stmt.setString(4, name);
    				stmt.setInt(5, 0);
    				stmt.executeUpdate();
        			ResultSet rs = stmt.getGeneratedKeys();
        			if(rs.next())
        			{
        				if(rs.wasNull())
        				{
        					out.print(JsonResponse("Could not create post. Please try again."));
        					out.flush();
            	    		return;
        				}
        				int post_id = rs.getInt(1);
        				out.print(JsonResponse(true, post_id));
        				out.flush();
        				return;
        			}
        			else
        			{
        				out.print(JsonResponse("Could not create post. Please try again."));
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
