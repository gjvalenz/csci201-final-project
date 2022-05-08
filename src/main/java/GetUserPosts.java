
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
@WebServlet("/api/user/posts")
public class GetUserPosts extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    public String asJSONArray(ArrayList<Post> ps)
    {
    	String array = "[";
    	for(Post p: ps)
    		array += p.asJSON() + ',';
    	if(ps.size() > 0)
    		array = array.substring(0, array.length() - 1);
    	array += "]";
    	return array;
    }
    
    public String JsonResponse(String error, Boolean success, ArrayList<Post> posts)
    {
    	return String.format("{ \"error\": \"%s\", \"success\": %b, \"feed\": %s }", error, success, asJSONArray(posts));
    	
    }
    
    public String JsonResponse(Boolean success, ArrayList<Post> posts)
    {
    	return JsonResponse("", true, posts);
    }
    
    public String JsonResponse(String error)
    {
    	return JsonResponse(error, false, new ArrayList<Post>());
    }
    
    void notLoggedInPosts(PrintWriter out, int post_user)
    {
    	String sql = "SELECT post_id, body, puser, ctime, uname, like_count FROM post where puser = ?";
		try(
    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	{
			ArrayList<Post> posts = new ArrayList<Post>();
			stmt.setInt(1, post_user);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				if(!rs.wasNull())
				{
					int post_id = rs.getInt(1);
					String body = rs.getString(2);
					String time = rs.getString(4);
					String uname = rs.getString(5);
					int likes_count = rs.getInt(6);
					Boolean liked = false;
					posts.add(new Post(post_id, body, time, uname, likes_count, post_user, liked));
				}
			}
			posts.sort((Post p1, Post p2) -> p2.time.compareTo(p1.time));
			out.print(JsonResponse(true, posts));
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
    	int post_user = Integer.parseInt(request.getParameter("userID"));
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
				String sql = "SELECT post_id, body, puser, ctime, uname, like_count FROM post WHERE puser = ?";
				System.out.println(sql);
				//String sql2 = "SELECT COUNT(*), SUM(CASE WHEN post_id = ? THEN 1 ELSE 0 END) as LikeCount, SUM(CASE WHEN post_id = ? AND pluser = ? THEN 1 ELSE 0 END) as Liked FROM post_like";
				String sql2 = "SELECT COUNT(*), SUM(CASE WHEN post_id = ? AND pluser = ? THEN 1 ELSE 0 END) as Liked FROM post_like";
				try(
    	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    	    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	    	{
    				ArrayList<Post> posts = new ArrayList<Post>();
    				stmt.setInt(1, post_user);
    				ResultSet rs = stmt.executeQuery();
    				while(rs.next())
    				{
    					if(!rs.wasNull())
    					{
    						int post_id = rs.getInt(1);
    						String body = rs.getString(2);
    						String time = rs.getString(4);
    						String name = rs.getString(5);
    						int like_count = rs.getInt(6);
    						Boolean liked = false;
    						PreparedStatement stmt2 = conn.prepareStatement(sql2);
    						stmt2.setInt(1, post_id);
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
    						posts.add(new Post(post_id, body, time, name, like_count, post_user, liked));
    					}
    				}
    				posts.sort((Post p1, Post p2) -> p2.time.compareTo(p1.time));
    				out.print(JsonResponse(true, posts));
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
				this.notLoggedInPosts(out, post_user);
				return;
			}
		}
		else // not logged in, send feed
		{
			this.notLoggedInPosts(out, post_user);
			return;
		}
    }

}
