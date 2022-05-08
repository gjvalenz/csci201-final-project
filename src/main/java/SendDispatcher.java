
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher; //added
import javax.servlet.ServletConfig; //added
import javax.servlet.ServletContext; //added
import java.nio.charset.*; //added


@WebServlet("/SendDispatcher") //added
public class SendDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     */
    public SendDispatcher() {
    	
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
    	
    	HttpSession session = request.getSession(false);
    	
    	User user = new User("a", "b", "c", "d", "e");
    	String from = (String) session.getAttribute("email");
		String to = (String) session.getAttribute("email2");
    	if(request.getParameter("fr")!=null) {
    		user.sendRequest(from, to);
    		request.setAttribute("email2", to);
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ProfileDispatcher");
        	dispatcher.forward(request, response);
    		
    	} else if(request.getParameter("accept")!=null) {
    		to = request.getParameter("accept");
    		user.acceptRequest(from, to);
    		UserJobs.setActive();
			UserJobs.startJob(session);
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FRDispatcher");
        	dispatcher.forward(request, response);
    		
    	}
    	else if(request.getParameter("deny")!=null) {
    		to = request.getParameter("deny");
    		user.denyRequest(to, from);
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FRDispatcher");
        	dispatcher.forward(request, response);
    		
    	} else if(request.getParameter("remove")!=null) {
    		to = request.getParameter("remove");
    		user.removeFriend(to, from);
    		UserJobs.setActive();
			UserJobs.startJob(session);
    		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/FriendDispatcher");
        	dispatcher.forward(request, response);
    		
    	}
    	
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
