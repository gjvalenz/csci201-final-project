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

/**
 * Servlet implementation class SearchDispatcher
 */

@WebServlet("/SearchDispatcher") //added
public class SearchDispatcher extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    
    String keyWord;
    String sort;
    String searchType;

    /**
     * Default constructor.
     */
    public SearchDispatcher() {
    	
    	
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
    	
    	searchType = "name"; //default
    	
    	keyWord = request.getParameter("searchbar");
    	
    	
    	String category = request.getParameter("category");
    	
    	
    	
    	GetProfile getProfile = new GetProfile();
    	
    	ArrayList<User> profiles = GetProfile.getProfiles(keyWord, category);
    	
    	//Profile[] profile = profiles.toArray(new Profile[profiles.size()]);
    	
    	request.setAttribute("results", profiles);
    	
    	request.setAttribute("keyWord_", keyWord);
    	//request.setAttribute("searchType_", searchType);
    	request.setAttribute("searchType", category);
    	
 
 
    	
    	
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/search.jsp");
    	dispatcher.forward(request, response);
    	
    	//response.sendRedirect("search.jsp");
    	
    
    	
    	
    	
    	
    	
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
