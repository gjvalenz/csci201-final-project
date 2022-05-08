
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    	String email = request.getParameter("email2");
    	HttpSession session2 = request.getSession(false);
		
		String emailLogin = null;
		
		
		
		if(session2!=null) {
			emailLogin = (String) session2.getAttribute("email");
			if(request.getParameter("email2") == null) {
				email = (String) request.getAttribute("email2");
			}
			if(request.getParameter("you") != null) {
				email = emailLogin;
			} 
			request.setAttribute("areFriends", Verify.areFriends(emailLogin, email));
			request.setAttribute("sentRequest", Verify.sentRequest(emailLogin,  email));
		}
		
		User profile = GetProfile.getProfile(email); 
		request.setAttribute("name", profile.getName());
		request.setAttribute("email", profile.getEmail());
		request.setAttribute("github", profile.getGithub());
		request.setAttribute("company", profile.getCompany());
		RequestDispatcher requestdispatcher = getServletContext().getRequestDispatcher("/Profile.jsp");
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
