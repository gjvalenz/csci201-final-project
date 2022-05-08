
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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
    
    String getInfoFrom(String url) throws Exception
    {
    	System.out.println(url);
    	URL getUrl = new URL(url);
    	HttpURLConnection conection = (HttpURLConnection) getUrl.openConnection();
        
        // Set request method
        conection.setRequestMethod("GET");

        // Getting response code
        int responseCode = conection.getResponseCode();

        // If responseCode is 200 means we get data successfully
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            
            // Append response line by line
            while ((readLine = in.readLine()) != null) {
                jsonResponseData.append(readLine);
            } 
            
            in.close();
            // Print result in string format
            System.out.println("JSON String Data " + jsonResponseData.toString());
            return jsonResponseData.toString();
        } else {
            System.out.println(responseCode);
        	return null;
        }

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
			System.out.println(emailLogin);
			if(request.getParameter("email2") == null) {
				email = (String) request.getAttribute("email2");
				System.out.println(email);
			}
			if(request.getParameter("you") != null) {
				email = emailLogin;
				System.out.println(email);
				request.setAttribute("name", (String)session2.getAttribute("name"));
				request.setAttribute("email", (String)session2.getAttribute("email"));
				request.setAttribute("github", (String)session2.getAttribute("github"));
				request.setAttribute("company", (String)session2.getAttribute("company"));
				request.setAttribute("areFriends", false);
				request.setAttribute("sentRequest", false);
				String github_url = "https://api.github.com/users/" + (String)session2.getAttribute("github");
				String repos_url = "https://api.github.com/users/" + (String)session2.getAttribute("github") + "/repos";
				String githubInfo = "";
				String reposInfo = "";
				try {
					githubInfo = this.getInfoFrom(github_url);
					reposInfo = this.getInfoFrom(repos_url);
				} catch(Exception e)
				{
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
				request.setAttribute("github_info", githubInfo);
				request.setAttribute("repos_info", reposInfo);
				RequestDispatcher requestdispatcher = getServletContext().getRequestDispatcher("/Profile.jsp");
				requestdispatcher.forward(request, response);
				return;
			} 
			request.setAttribute("areFriends", Verify.areFriends(emailLogin, email));
			request.setAttribute("sentRequest", Verify.sentRequest(emailLogin,  email));
		}
		long startTime = System.nanoTime();
		User profile = GetProfile.getProfile(email);
		long endTime = System.nanoTime();
		System.out.println("took " + (endTime-startTime)/1000000 + "ms to fetch profile");
		request.setAttribute("name", profile.getName());
		request.setAttribute("email", profile.getEmail());
		request.setAttribute("github", profile.getGithub());
		request.setAttribute("company", profile.getCompany());
		String github_url = "https://api.github.com/users/" + (String)session2.getAttribute("github");
		String repos_url = "https://api.github.com/users/" + (String)session2.getAttribute("github") + "/repos";
		String githubInfo = "";
		String reposInfo = "";
		try {
			startTime = System.nanoTime();
			githubInfo = this.getInfoFrom(github_url);
			reposInfo = this.getInfoFrom(repos_url);
			endTime = System.nanoTime();
			System.out.println("took " + (endTime-startTime)/1000000 + "ms to fetch github profile");
		} catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		request.setAttribute("github_info", githubInfo);
		request.setAttribute("repos_info", reposInfo);
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
