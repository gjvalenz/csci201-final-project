
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
import java.util.ArrayList;

/**
 * Servlet implementation class LogoutDispatcher
 */
@WebServlet("/api/post/comments")
public class GetPostComments extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    public String asJSONArray(ArrayList<Comment> cs)
    {
    	String array = "[";
    	for(Comment c: cs)
    		array += c.asJSON() + ',';
    	if(cs.size() > 0)
    		array = array.substring(0, array.length() - 1);
    	array += "]";
    	return array;
    }
    
    public String JsonResponse(String error, Boolean success, ArrayList<Comment> comments)
    {
    	return String.format("{ \"error\": \"%s\", \"success\": %b, \"comments\": %s }", error, success, asJSONArray(comments));
    	
    }
    
    public String JsonResponse(Boolean success, ArrayList<Comment> comments)
    {
    	return JsonResponse("", true, comments);
    }
    
    public String JsonResponse(String error)
    {
    	return JsonResponse(error, false, new ArrayList<Comment>());
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
    	int postID = Integer.parseInt(request.getParameter("postID"));
    	try {
    		UserJobs.endJob();
    	} catch(InterruptedException e)
    	{
    		Thread.currentThread().interrupt();
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
		if(session != null && request.isRequestedSessionIdValid())
		{
			System.out.println("valid session??");
			if(session.getAttribute("user_id") != null) // valid user
			{
				int user_id = (int)session.getAttribute("user_id");
				String sql = "SELECT comment_id, body, cuser, ctime, cuname, likes_count, ppost FROM comment WHERE ppost = ? ";
				String sql2 = "SELECT COUNT(*), SUM(CASE WHEN comment_id = ? AND luser = ? THEN 1 ELSE 0 END) as Liked FROM comment_like";
				try(
    	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    	    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	    	{
    				ArrayList<Comment> comments = new ArrayList<Comment>();
    				stmt.setInt(1, postID);
    				ResultSet rs = stmt.executeQuery();
    				while(rs.next())
    				{
    					if(!rs.wasNull())
    					{
    						int comment_id = rs.getInt(1);
    						String body = rs.getString(2);
    						String time = rs.getString(4);
    						int comment_user = rs.getInt(3);
    						String name = rs.getString(5);
    						int like_count = rs.getInt(6);
    						Boolean liked = false;
    						PreparedStatement stmt2 = conn.prepareStatement(sql2);
    						stmt2.setInt(1, comment_id);
    						stmt2.setInt(2, user_id);
    						ResultSet rs2 = stmt2.executeQuery();
    						if(rs2.next())
    						{
    							if(!rs2.wasNull())
    							{
    								int total = rs2.getInt(1);
    								int did_like = rs2.getInt(2);
    								System.out.println("total: " + total);
    								System.out.println("did like: " + did_like);
    								liked = did_like > 0;
    							}
    						}
    						comments.add(new Comment(comment_id, body, time, name, like_count,comment_user, postID, liked));
    					}
    				}
    				comments.sort((Comment c1, Comment c2) -> c1.time.compareTo(c2.time));
    				out.print(JsonResponse(true, comments));
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
		else // not logged in, send feed
		{
			String sql = "SELECT comment_id, body, cuser, ctime, cuname, likes_count, ppost FROM comment WHERE ppost = ? ";
			try(
	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
	    			PreparedStatement stmt = conn.prepareStatement(sql);)
	    	{
				ArrayList<Comment> comments = new ArrayList<Comment>();
				stmt.setInt(1, postID);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					if(!rs.wasNull())
					{
						int comment_id = rs.getInt(1);
						String body = rs.getString(2);
						String time = rs.getString(4);
						int comment_user = rs.getInt(3);
						String name = rs.getString(5);
						int like_count = rs.getInt(6);
						Boolean liked = false;
						comments.add(new Comment(comment_id, body, time, name, like_count,comment_user, postID, liked));
					}
				}
				comments.sort((Comment c1, Comment c2) -> c1.time.compareTo(c2.time));
				out.print(JsonResponse(true, comments));
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
    }

}
