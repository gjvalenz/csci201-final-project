package finalproject;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;
import java.util.Set;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out; 



@WebServlet("/LoginDispatcher") 

public class LoginDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO
    	
    	response.setContentType("text/html");
    	
    	String email = request.getParameter("email");
    	String passkey = request.getParameter("passkey");

    	
    	Boolean valid = true;
    	
    	//check if email exists in user database
    	
    	if(!Verify.isValidEmail(email)) {
    		valid = false;
    	}
    	
    	
    	//check if email and password match
    	else if(valid) {
    		try {
				if(!Verify.verifyPassword(email, passkey)) { //incorrect password
					valid = false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	
    	if(!valid) {
    		
    		request.setAttribute("login_error", "Invalid login. Please try again."); 
    		
    		
    		request.getRequestDispatcher("login.jsp").include(request, response); 
    		 
    		
    	}
    	
    	else {
    		
    		String name = null;
			try {
				name = Verify.getUserName(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		String r_name = name.replaceAll(" ", ""); //replace all spaces in name before creating cookie w/ that name
    		
    		Cookie cookie = new Cookie(r_name, email);
    		Set sessionManager;
			boolean sessionIdUrlRewritingEnabled = false;
    		cookie.setMaxAge(60*60); 
    		response.addCookie(cookie);
    		cookie.setDomain("http://localhost:8080/gighub"); //XXX
			
			
    		//login httpsession
    		
    		 HttpSession session=request.getSession();  
    		 
    		        
    		 String helloUser = null;
			try {
				helloUser = Verify.getUserName(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		session.setAttribute("HelloUser", "Hello, " + helloUser);
    		
    		request.setAttribute("Logout", "Logout");
    		
    		response.sendRedirect("index3.jsp");
    		
    		
    		
    		
    	}
    	

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
