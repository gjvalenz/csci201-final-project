import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public class GetFriendsIntoSession extends Thread
{
	HttpSession session;
	ArrayList<Integer> friends;
	
	GetFriendsIntoSession(HttpSession s)
	{
		session = s;
		friends = new ArrayList<Integer>();
	}
	
	public void run()
	{
		int user_id = (int)session.getAttribute("user_id");
		String sql = "SELECT friend1, friend2 FROM friend WHERE friend1=? OR friend2=?";
		try
    	{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	} catch(Exception e)
    	{
    		return;
    	}
		try(
    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	{
			stmt.setInt(1, user_id);
			stmt.setInt(2, user_id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				if(!rs.wasNull())
				{
					int friend1 = rs.getInt(1);
					int friend2 = rs.getInt(2);
					if(friend1 == user_id)
					{
						friends.add(friend2);
						System.out.println(friend2);
					}
					else
					{
						friends.add(friend1);
						System.out.println(friend1);
					}
				}
			}
			System.out.println(friends);
			session.setAttribute("friends", friends);
    	} catch(SQLException e)
		{
    		System.err.println("oof");
    		System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
