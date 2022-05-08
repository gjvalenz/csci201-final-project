<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!--  TESTING ONLY -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="301645777112-2rlc9gth0f5d4reimjcm9bf0kj7ahec0.apps.googleusercontent.com"
          name="google-signin-client_id">
    <title>User Posts</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <script crossorigin="anonymous"
            src="https://kit.fontawesome.com/3204349982.js"></script>
            <script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
    <link href="index.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto"
          rel="stylesheet" type="text/css">
</head>
<body>
 <div class="register">
 <div>
 	UserID:<input type="text" id="userID" />
 	<button id="get-posts">Get their posts</button>
 </div>
 <div id="feed"></div>
 </div>
 <script>
 var user_posts = [];
 
 function generatePost(post, index)
 {
	 return "<div class='post' data-index='" + index +"' data-post-id='" + post.post_id + "'><h3>" + post.post_user_name + "</h3><p>" + post.body + "</p><button class='post-like'" + (post.liked ? "disabled":"")+ ">Like</button><p class='post-likes-count'>" + post.likes_count + "</p><button class='fetch-comments'>Fetch comments</button><div class='comments'></div><input type='text' name='comment'/><button class='post-comment'>Post comment</button><p>" + post.time + "</p></div>";
 }
 
 function generateComment(comment, index)
 {
	 return "<div class='comment' data-index='" + index +"' data-comment-id='" + comment.comment_id + "'><h3>" + comment.comment_user_name + "</h3><p>" + comment.body + "</p><p class='comment-likes-count'>" + comment.likes_count + "</p></div>";
 }
 
 function addComments(cb, cs)
 {
	 for(var i = 0; i < cs.length; i++)
	 {
		 cb.append(generateComment(cs[i], i));
	 }	 
 }
 
 function addPosts(posts)
 {
	 for(var i = 0; i < posts.length; i++)
	 {
		 $('#feed').append(generatePost(posts[i], i));
	 }	 
 }
 
 $('#get-posts').click(function(){
	 $.get('./api/user/posts', {userID: $('#userID').val()}, function(data){
	  	 if(data.success)
	  	 {
	  		user_posts = data.feed;
	  		addPosts(user_posts);
	  	 }
	  	 else
	     {
	  		 alert("Error: " + data.error);
	     }
	 });
 });
 
 $('#feed').on('click', '.fetch-comments', function(){
	 var that = $(this);
	 var index = Number($(this).parent().attr('data-index'));
	 var comment_base = $(this).siblings('.comments');
	 var post_id = $(this).parent().attr('data-post-id');
	 $.get('./api/post/comments', {postID: $(this).parent().attr('data-post-id')}, function(data){
		 if(data.success)
		 {
			 if(user_posts[index].comments)
		     {
				 user_posts[index].comments;
				 comment_base.empty();
			 }
			 user_posts[index].comments = data.comments;
			 addComments(comment_base, user_posts[index].comments);
		 }
		 else
		 {
			 alert("Error: " + data.error);
		 }
	 });
 });
 
 $('#feed').on('click', '.post-comment', function(){
	 var that = $(this);
	 var index = Number($(this).parent().attr('data-index'));
	 var comment = $(this).siblings("input").val();
	 var comment_base = $(this).siblings('.comments');
	 var post_id = $(this).parent().attr('data-post-id');
	 console.log(comment);
	 console.log(index);
	 console.log(post_id);
	 $.post('./api/comment/create', {postID: $(this).parent().attr('data-post-id'), body: comment}, function(data){
		 if(data.success)
		 {
			 var name = null;
			 if(localStorage.getItem('userInfo'))
			 {
				 name = JSON.parse(localStorage.getItem('userInfo')).name;
			 }
			 if(user_posts[index].comments)
		     {
				 user_posts[index].comments.push({comment_id: data.commentID, likes_count: 0, body: comment, comment_user_name: name || 'Null' });
			 }
			 else
		     {
				user_posts[index].comments = [{comment_id: data.commentID, likes_count: 0, body: comment,  comment_user_name: name || 'Null' }];
		     }
			 addComments(comment_base, [{comment_id: data.commentID, likes_count: 0, body: comment,  comment_user_name: name || 'Null' }]);
		 }
		 else
		 {
			 alert("Error: " + data.error);
		 }
	 });
 });
 </script>
</body>
</html>