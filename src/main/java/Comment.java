public class Comment{
	int comment_id;
	String comment;
	String time;
	String name;
	int likes;
	int from_user;
	int to_post;
	Boolean liked;
	
	Comment(int id, String bod, String tm, String nm, int lcount, int cuser, int post, Boolean lkd)
	{
		comment_id = id;
		comment = bod;
		time = tm;
		name = nm;
		likes = lcount;
		from_user = cuser;
		to_post = post;
		liked = lkd;
	}
	
	String asJSON()
	{
		return String.format("{\"comment_id\": %d, \"body\": \"%s\", \"time\": \"%s\", \"likes_count\": %d, \"comment_user\": %d, \"liked\": %b, \"comment_user_name\": \"%s\", \"postID\": %d}",
								comment_id, comment, time, likes, from_user, liked, name, to_post);

	}
	
}
