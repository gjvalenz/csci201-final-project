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
</head>
<body>
 <div class="register">
 <% if (errd) {%>
 <%= error %>
 <%} else {%>
 Hello, <%= name %>(<%= user_id %>)
 <div>
 	Message:<input type="text" name="message" id="message" />
 	To:<input type="text" id="whom" />
 	<button id="send-message">Send</button>
 </div>
 <% } %>
 <%= messages %>
 <button id="get-messages">Get Messages</button>
 <div id="messages"></div>
 <button id="check-new-messages">Check New Messages</button>
 <div id="new-messages"></div>
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