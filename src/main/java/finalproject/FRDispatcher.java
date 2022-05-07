package finalproject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.reflect.TypeToken;

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

@WebServlet("/FRDispatcher") //added
public class FRDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    /**
     * Default constructor.
     */
    public FRDispatcher() {
    	
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
    	
    	String email = request.getParameter("email");
    	ArrayList<User> profiles = GetProfile.getFriendRequests(email);
    	
    	request.setAttribute("friend_requests", profiles);
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/requests.jsp");
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
