
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * API
 */
@WebServlet("/api/user/logout")
public class Logout extends HttpServlet {
	public void init() throws ServletException
	{
		
	}
	
    public String JsonResponse(Boolean success, String error)
    {
    	return String.format("{ \"success\": %b, \"error\": \"%s\" }", success, error);
    	
    }
    

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession(false);
		if(session != null)
		{
			session.invalidate();
			out.print(JsonResponse(true, ""));
		}
		else
			out.print(JsonResponse(true, "No user logged in with this session."));
		out.flush();
		return;
	}
	
	public void destroy() {
		
	}
}
