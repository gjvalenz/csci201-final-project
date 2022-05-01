//package Util; // commented out

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.util.*; 




public class Comment{
	User fromUser;
	String comment;

	
	
	public void Comment(User user, String comment){
		this.comment = comment;
		this.fromUser = user;
		
	}
	
	public String getComment(){
		return this.comment;
	}
	
	
}
