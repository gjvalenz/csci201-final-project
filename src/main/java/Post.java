
import java.util.ArrayList;

public class Post {
	int post_id;
	String name;
	String body;
	String time;
	int likes_count;
	int post_user;
	ArrayList<Comment> comments;
	Boolean liked;
	
	Post(int id, String bod, String tm, String nm, int lcount, int puser, Boolean lkd)
	{
		post_id = id;
		body = bod;
		time = tm;
		name = nm;
		likes_count = lcount;
		post_user = puser;
		liked = lkd;
	}
	
	String asJSON()
	{
		return String.format("{\"post_id\": %d, \"body\": \"%s\", \"time\": \"%s\", \"likes_count\": %d, \"post_user\": %d, \"liked\": %b, \"post_user_name\": \"%s\"}",
								post_id, body, time, likes_count, post_user, liked, name);

	}
}
