<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!--  TESTING ONLY -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="301645777112-2rlc9gth0f5d4reimjcm9bf0kj7ahec0.apps.googleusercontent.com"
          name="google-signin-client_id">
    <title>Login / Register</title>
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
 	Post:<input type="text" name="body" id="body" />
 	<button id="create-post">Send</button>
 </div>
 <button id="get-feed">Get Feed</button>
 <div id="feed"></div>
 </div>
 <script>
 var global_posts = [];
 
 $('#create-post').click(function(){
	 $.post('./api/post/create', {body: $('#body').val()}, function(data){
	  	 if(data.success)
	  	 {
	  		 alert("Success! Post id is " + data.postID);
	  	  }
	  	 else
	     {
	  		 alert("Error: " + data.error);
	     }
	 });
 });
 
 function generatePost(post, index)
 {
	 return "<div class='post' data-index='" + index +"' data-post-id='" + post.post_id + "'><h3>" + post.post_user_name + "</h3><p>" + post.body + "</p><button class='post-like'" + (post.liked ? "disabled":"")+ ">Like</button><p class='post-likes-count'>" + post.likes_count + "</p><button class='fetch-comments'>Fetch comments</button><div class='comments'></div><input type='text' name='comment'/><button class='post-comment'>Post comment</button><p>" + post.time + "</p></div>";
 }
 
 function generateComment(comment, index)
 {
	 return "<div class='comment' data-index='" + index +"' data-comment-id='" + comment.comment_id + "'><h3>" + comment.comment_user_name + "</h3><p>" + comment.body + "</p><p class='comment-likes-count'>" + comment.likes_count + "</p></div>";
 }
 
 function addPosts(posts)
 {
	 $('#feed').empty();

	 for(var i = 0; i < posts.length; i++)
	 {
		 $('#feed').append(generatePost(posts[i], i));
	 }	 
 }
 
 function addComments(cb, cs)
 {
	 for(var i = 0; i < cs.length; i++)
	 {
		 cb.append(generateComment(cs[i], i));
	 }	 
 }
 
 $("#get-feed").click(function(){
	 $.get('./api/feed', function(data){
	  	 if(data.success)
	  	 {
	  		 global_posts = data.feed;
	  		 addPosts(global_posts);
	  		 console.log(global_posts);
	  		//$("#feed").text(JSON.stringify(data.feed));
	  	  }
	  	 else
	     {
	  		 alert("Error: " + data.error);
	     }
	 }); 
 });
 
 $('#feed').on('click', '.post-like', function(){
	 console.log('clicked');
	 var that = $(this);
	 var index = Number($(this).parent().attr('data-index'));
	 var comment = $(this).siblings("input").val();
	 $.post('./api/post/like', {postID: $(this).parent().attr('data-post-id')}, function(data){
		 if(data.success)
		 {	
			 global_posts[index].liked = true;
			 global_posts[index].likes_count++;
			 $('.post-likes').eq(index).text("Liked");
			 var num = Number($('.post-likes-count').eq(index).text());
			 $('.post-likes-count').eq(index).text(num + 1);
			 that.attr("disabled", true);
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
			 if(global_posts[index].comments)
		     {
				 global_posts[index].comments.push({comment_id: data.commentID, likes_count: 0, body: comment, comment_user_name: name || 'Null' });
			 }
			 else
		     {
				global_posts[index].comments = [{comment_id: data.commentID, likes_count: 0, body: comment,  comment_user_name: name || 'Null' }];
		     }
			 addComments(comment_base, [{comment_id: data.commentID, likes_count: 0, body: comment,  comment_user_name: name || 'Null' }]);
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
			 if(global_posts[index].comments)
		     {
				 global_posts[index].comments;
				 comment_base.empty();
			 }
			 global_posts[index].comments = data.comments;
			 addComments(comment_base, global_posts[index].comments);
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