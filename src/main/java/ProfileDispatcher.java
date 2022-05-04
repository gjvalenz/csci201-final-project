
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Servlet implementation class ProfileDispatcher
 */

@WebServlet("/ProfileDispatcher")

public class ProfileDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public ProfileDispatcher() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
    	String email = request.getParameter("email");
		User profile = GetProfile.getProfile(email);  //Get Profile from Search
		request.setAttribute("profile", profile);
		RequestDispatcher requestdispatcher = getServletContext().getRequestDispatcher("/profile.jsp");
		requestdispatcher.forward(request, response);
		
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response); 
    }
}
