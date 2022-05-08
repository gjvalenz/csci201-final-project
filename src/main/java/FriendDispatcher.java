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



@WebServlet("/FriendDispatcher") //added
public class FriendDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     */
    public FriendDispatcher() {
    	
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
    	try {
    		UserJobs.endJob();
    	} catch(InterruptedException e)
    	{
    		Thread.currentThread().interrupt();
    	}
    	HttpSession session = request.getSession(false);
    	ArrayList<Integer> friends = (ArrayList<Integer>) session.getAttribute("friends");
    	int id = (int) session.getAttribute("user_id");
    	
    	ArrayList<User> profiles = GetProfile.getFriends(friends);
    	
    	request.setAttribute("friends", profiles);
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/friends.jsp");
    	dispatcher.forward(request, response);
    	
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
