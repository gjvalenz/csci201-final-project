
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.util.*; 




public class Profile{
	String name;
	String github;
	String company_name;
	
	
	public Profile(String name, String github, String company_name){
		this.name = name;
		this.github = github;
		this.company_name = company_name;
	}
}
