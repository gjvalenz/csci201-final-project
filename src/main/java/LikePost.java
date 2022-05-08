
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/api/post/like")
public class LikePost extends HttpServlet {
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
    	String p1 = request.getParameter("postID");
    	int post_id = Integer.parseInt(p1);
    	if(post_id == -1)
    	{
    		out.print(JsonResponse("Failed to like post"));
    		out.flush();
    		return;
    	}
    	HttpSession session=request.getSession(false);
    	if(session == null || !request.isRequestedSessionIdValid())
    	{
    		out.print(JsonResponse("No user logged in with this session."));
    		out.flush();
    		return;
    	}
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
			int pluser = (int)session.getAttribute("user_id");
			if(pluser != -1) // valid user
			{
    			String sql = "INSERT INTO post_like(post_id, pluser) VALUES(?, ?)";
    			String sql2 = "UPDATE post SET like_count = like_count + 1 WHERE post_id = ?";
    			try(
    	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    	    			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
    	    	{
    				stmt.setInt(1, post_id);
    				stmt.setInt(2, pluser);
        			stmt.executeUpdate();
        			ResultSet rs = stmt.getGeneratedKeys();
        			if(rs.next())
        			{
        				if(rs.wasNull())
        				{
        					out.print(JsonResponse("Could not like post."));
        					out.flush();
            	    		return;
        				}
        				PreparedStatement ps2 = conn.prepareStatement(sql2);
        				ps2.setInt(1, post_id);
        				ps2.executeUpdate();
        				out.print(JsonResponse(true));
        				out.flush();
        				return;
        			}
        			else
        			{
        				out.print(JsonResponse("Could not like post."));
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
    }

}
