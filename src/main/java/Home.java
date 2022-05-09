import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
//@WebServlet("/Home")
@WebServlet("")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    */
	
	public void init() throws ServletException
	{
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String sql = "SHOW tables";
		try
    	{
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    	} catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		request.setAttribute("error", e.getMessage());
    		request.getRequestDispatcher("/index3.jsp").forward(request, response);
    		return;
    	}
    	try(
    			Connection conn = DriverManager.getConnection(Constant.DBURL, Constant.DBUserName, Constant.DBPassword);
    			PreparedStatement stmt = conn.prepareStatement(sql);)
    	{
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next())
    		{
    			System.out.println(rs.getString(1));
    		}
    	} catch(SQLException e)
    	{
    		System.out.println(e.getMessage());
    		System.out.println(e.getStackTrace());
    	}
		response.setContentType("text/html");
		request.getRequestDispatcher("/index3.jsp").forward(request, response);
	}
	public void destroy() {
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}*/

}
