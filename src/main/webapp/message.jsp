<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!--  TESTING ONLY -->
<%
String error = (String) request.getAttribute("error");
Boolean errd = (error != null);
int user_id = (int) request.getAttribute("user_id");
String name = (String) request.getAttribute("name");
String messages = (String) request.getAttribute("messages");
 %>
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
	 <h1><% if (errd) {%>
		<%= error %>
		<%} else {%>
		Hello, <%= name %>!</h1>
 <div class="row align-items-center">
	 <div class="col-5">
		<label for="message">Message:</label>
		<input type="text" name="message" id="message" class="form-control"/>
	 </div>
	 <div class="col-5">
		<label for="whom">To:</label>
		<input type="text" id="whom" class="form-control"/>
	 </div>
	 <div class="col-2 text-center">
		<button id="send-message" class="btn btn-success">Send</button>
	</div>
 </div>
 <% } %>
 <div class="row mt-3 mb-4">
	 <h3>Message Log:</h3>
	<%= messages %>
 </div>
 <div class="row my-2">
	<button id="get-messages" class="btn btn-warning col-2">Get Messages</button>
	<div id="messages" class="col"></div>
 </div>
 <div class="row my-2">
	<button id="check-new-messages" class="btn btn-warning col-2">Check New Messages</button>
	<div id="new-messages" class="col"></div>
 </div>
 </div>
 <script>
 $('#send-message').click(function(){
	 $.post('./api/message/send', {message: $('#message').val(), userID: parseInt($('#whom').val())}, function(data){
	  	 if(data.success)
	  	 {
	  		 alert("Success! Message id is " + data.messageID);
	  	  }
	  	 else
	     {
	  		 alert("Error: " + data.error);
	     }
	 });
 });
 
 $("#get-messages").click(function(){
	 $.get('./api/messages/received', function(data){
	  	 if(data.success)
	  	 {
	  		$("#messages").text(JSON.stringify(data.messages));
	  	  }
	  	 else
	     {
	  		 alert("Error: " + data.error);
	     }
	 }); 
 });
 
 $("#check-new-messages").click(function(){
	// 20 second buffer
	$.get('./api/messages/new', {time: Date.now() - 1000*20}, function(data){
	  	 if(data.success)
	  	 {
	  		$("#new-messages").text(JSON.stringify(data.messages));
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