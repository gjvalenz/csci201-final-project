<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!--  TESTING ONLY -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="301645777112-2rlc9gth0f5d4reimjcm9bf0kj7ahec0.apps.googleusercontent.com" name="google-signin-client_id">
    <title>Posts</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script crossorigin="anonymous" src="https://kit.fontawesome.com/3204349982.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link href="index.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<style>
		body {
			background: linear-gradient(#d0d9dc, #6367dd) no-repeat center center fixed !important; 
  			-webkit-background-size: cover;
  			-moz-background-size: cover;
  			-o-background-size: cover;
  			background-size: cover;
      	}
		img {
        	width: 200px;
        	height: auto;
      	}
	</style>
</head>
<body>
	<a href="index3.jsp"><img id="gighub" src="FPlogo.png"/></a>
	<div class="container register">
		<div class="row">
			<h1 class="text-center my-5">Posts</h1>
			 <div class="col mx-2">
				<div class="text-center">
					<h3>Your Feed</h3>
				</div>
 				<div id="feed"></div>
			 </div>
			 <div class="col mx-2">
				<label for="body">Create a Post</label>
				<textarea name="body" id="body" placeholder="Enter a post body..." class="form-control my-3" cols="10" rows="10"></textarea>
				<div class="text-center">
					<button id="create-post" class="btn btn-success">Create!</button>
				</div>
			 </div>
		</div>
 	</div>
<script>
 var global_posts = [];
 
 $('#create-post').click(function(){
	 $.post('./api/post/create', {body: $('#body').val()}, function(data){
	  	 if(data.success)
	  	 {
	  		 fetchFeed();
	  		 $('#body').val('');
	  	  }
	  	 else
	     {
	  		 alert("Error: " + data.error);
	     }
	 });
 });
 
 function generatePost(post, index)
 {
	 return `
	 	<div class='post row border-top my-5 py-3 border-dark' data-index='\${index}' data-post-id='\${post.post_id}'>
			<h3>\${post.post_user_name}</h3>
			<h5 class='mb-3'>Posted at \${post.time}:</h4>
			<p class="border-top border-bottom">\${post.body}</p>
			<div class='row mt-2 mb-4'>
				<button class='post-like btn btn-danger col-2'\${(post.liked ? "disabled":"")}>Like</button>
				<p class='post-likes-count col'>\${post.likes_count}</p>
			</div>
			<button class='fetch-comments btn btn-warning mb-3'>Load comments...</button>
			<div class='comments'></div>
			<input type='text' name='comment' class='form-control col me-2'/> 
			<button class='post-comment btn btn-success col-2 align-self-end'>Comment</button>
		</div>`;
 }
 
 function generateComment(comment, index)
 {
	 return `<div class='comment row border-top my-2 pt-4' data-index='\${index}' data-comment-id='\${comment.comment_id}'>
			<p><span class='h5'>\${comment.comment_user_name} said: </span>\${comment.body}</p>
		</div>`;
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
 
 function fetchFeed(){
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
 }
 
 fetchFeed();
 
 $('#feed').on('click', '.post-like', function(){
	 console.log('clicked');
	 var that = $(this);
	 var index = Number($(this).parent().parent().attr('data-index'));
	 $.post('./api/post/like', {postID: $(this).parent().parent().attr('data-post-id')}, function(data){
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