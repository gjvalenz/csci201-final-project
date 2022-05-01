import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;

import java.sql.*; //added

import java.util.*; //added

import javax.servlet.RequestDispatcher; //added


/**
 * Servlet implementation class RegisterDispatcher
 */

@WebServlet("/RegisterDispatcher") //added

public class RegisterDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String url = "jdbc:mysql://localhost:3306/PA4Users";
    

    /**
     * Default constructor.
     */
    public RegisterDispatcher() {
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO
    	
    	
    	//use the names that you provided for boxes in auth.html and use request.getParameter to get what user provided
    	String email = request.getParameter("email");
    	String passkey = request.getParameter("passkey");
    	String confirmpasskey = request.getParameter("confirmpasskey");
    	String name = request.getParameter("name");
    	String github = request.getParameter("github");
    	String company = request.getParameter("company");

    	
    	
    	//check if inputted data is valid, Validate.java for validating
    	
    	Boolean valid = true;
    	
    	if(!Verify.isValidName(name)) {
    		
    		valid = false;
    	}
    	else if(!Verify.isValidEmail(email)) {
    		
    		valid = false;
    	}
    	else if(Verify.emailRegistered(email, request, response)) {
    		
    		valid = false;
    	}
    	
    	
    	else if(!Verify.verifyPassword(passkey)) {
    		valid = false;

    	}
    	
    	else if(!confirmpasskey.equals(passkey)) { //confirm password entry does not match password
    		valid = false;
    	}
    	

    	
    	
    	
    	if(!valid) {
    		//display error message
    		request.setAttribute("register_error", "Invalid Registration."); //**
    		
    		
    		request.getRequestDispatcher("register.jsp").include(request, response); //back to register page
    		
    	}
    	
    	else {
    		try {
				Class.forName("com.mysql.cj.jdbc.Driver"); 
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    		
    		Connection connection = null;
    		try {
				connection = DriverManager.getConnection(Constant.DBUrl,  Constant.DBUserName, Constant.DBPassword); //user,password
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    		Statement stmt = null;
    		try {
				stmt = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
    		
    		ResultSet rs = null;
    		
    		try {
				stmt.execute("INSERT INTO user(username, email, passkey) VALUES(" + "'" + name + "', '" + email + "', '" + passkey +"');");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		finally { //should i put this after the stmt catch
    			try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
   
    			
    		Cookie cookie = new Cookie(name, email);
    		cookie.setMaxAge(60*60); 
    		response.addCookie(cookie);
    		cookie.setDomain("http://localhost:8080/test2/"); //XXX
    		
    		
    		request.setAttribute("HelloUser", "Hi, "+name+"!"); 
    		
    		
    		
    		request.setAttribute("Logout", "Logout");
    	
    		
    		response.sendRedirect("index.jsp");
    		
    		
    	}
    	
    	
    
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
