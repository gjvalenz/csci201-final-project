package finalproject; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.util.*; 




public class Comment{
	int comment_id;
	String comment;
	String time;
	int likes;
	int from_user;
	int to_post;
	
	public void Comment(/*User user, */String comment){
		this.comment = comment;
		//this.fromUser = user;
		
	}
	
	public String getComment(){
		return this.comment;
	}
	
	
}
