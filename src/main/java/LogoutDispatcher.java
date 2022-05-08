
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie; // added

/**
 * Servlet implementation class LogoutDispatcher
 */

@WebServlet("/LogoutDispatcher") 
public class LogoutDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * @throws ServletException 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
    	

		//delete user cookies
    	Cookie[] cookies  = request.getCookies();

    	
    	for (Cookie aCookie : cookies)
    	{
    		System.out.println("Cookie Name: " + aCookie.getName( ));
    		aCookie.setMaxAge(0); 
    		
    		response.addCookie(aCookie);
    		
    	}
    	
    	request.setAttribute("Logout", null);
    	
    	request.setAttribute("HelloUser", null); 
    	

    	HttpSession session = request.getSession();
    	session.invalidate();
    	
    	response.sendRedirect("index3.jsp");
    	
    	
    	
    	
    	
    	
    }

    /**
     * @throws ServletException 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }

}
