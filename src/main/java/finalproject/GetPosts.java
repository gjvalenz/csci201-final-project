package finalproject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Util.Constant;
import Util.UserJobs;

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
@WebServlet("/api/posts")
public class GetPosts extends HttpServlet {
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
    	return String.format("{ \"error\": \"%s\", \"success\": %b, \"posts\": %s }", error, success, asJSONArray(posts));
    	
    }
    
    public String JsonResponse(Boolean success, ArrayList<Post> posts)
    {
    	return JsonResponse("", true, posts);
    }
    
    public String JsonResponse(String error)
    {
    	return JsonResponse(error, false, new ArrayList<Post>());
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
		if(session != null)
		{
			int user_id = (int)session.getAttribute("user_id");
			if(user_id != -1) // valid user
			{
		    	ArrayList<Integer> friends = (ArrayList<Integer>) session.getAttribute("friends");
		    	String list = "";
		    	if(friends != null)
		    	{
			    	for(Integer f: friends)
			    		list += f.toString() + ",";
			    	list += Integer.toString(user_id);
		    	}
		    	else
		    	{
		    		out.print(JsonResponse(true, new ArrayList<Post>()));
	    			out.flush();
	    			return;
		    	}
				String sql = String.format("SELECT post_id, body, puser, ctime FROM post WHERE puser IN (%s)", list);
				System.out.println(sql);
				String sql2 = "SELECT COUNT(*), SUM(CASE WHEN post_id = ? THEN 1 ELSE 0 END) as LikeCount, SUM(CASE WHEN post_id = ? AND pluser = ? THEN 1 ELSE 0 END) as Liked FROM post_like";
    			try(
    	    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    	    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	    	{
    				ArrayList<Post> posts = new ArrayList<Post>();
    				ResultSet rs = stmt.executeQuery();
    				while(rs.next())
    				{
    					if(!rs.wasNull())
    					{
    						int post_id = rs.getInt(1);
    						String body = rs.getString(2);
    						String time = rs.getString(4);
    						int post_user = rs.getInt(3);
    						Boolean liked = false;
    						int likes_count = 0;
    						PreparedStatement stmt2 = conn.prepareStatement(sql2);
    						stmt2.setInt(1, post_id);
    						stmt2.setInt(2, post_id);
    						stmt2.setInt(3, user_id);
    						ResultSet rs2 = stmt2.executeQuery();
    						if(rs2.next())
    						{
    							if(!rs2.wasNull())
    							{
    								int total = rs2.getInt(1);
    								int likes = rs2.getInt(2);
    								int did_like = rs2.getInt(3);
    								System.out.println("total: " + total);
    								System.out.println("likes: " + likes);
    								System.out.println("did like: " + did_like);
    								likes_count = likes;
    								liked = did_like > 0;
    							}
    						}
    						posts.add(new Post(post_id, body, time, likes_count, post_user, liked));
    					}
    				}
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