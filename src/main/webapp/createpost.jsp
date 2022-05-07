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
	 return "<div class='post' data-index='" + index +"' data-post-id='" + post.post_id + "'><h3>" +post.post_user_name + "</h3><p>" + post.body + "</p><button class='post-like'" + (post.liked ? "disabled":"")+ ">Like</button><p class='post-likes-count'>" + post.likes_count + "</p><p>" + post.time + "</p></div>";
 }
 
 function addPosts(posts)
 {
	 for(var i = 0; i < posts.length; i++)
	 {
		 $('#feed').append(generatePost(posts[i], i));
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
 </script>
</body>
</html>