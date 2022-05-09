<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!--  TESTING ONLY -->
<%@ page import="java.util.ArrayList" %>
<%@ page import="util.Message" %>
<%
String error = (String) request.getAttribute("error");
Boolean errd = (error != null);
int user_id = (int) request.getAttribute("user_id");
String name = (String) request.getAttribute("name");
int their_id = (int) request.getAttribute("their_id");
String their_name = (String) request.getAttribute("their_name");
ArrayList<Message> messages = (ArrayList<Message>) request.getAttribute("messages");
String messagesStr = "[";
for(Message m: messages)
{
	messagesStr += m.asJSON() + ',';
}
if(messages.size() > 0)
{
	messagesStr = messagesStr.substring(0, messagesStr.length() - 1);
}
messagesStr += "]";
 %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="301645777112-2rlc9gth0f5d4reimjcm9bf0kj7ahec0.apps.googleusercontent.com"
          name="google-signin-client_id">
    <title>Messages</title>
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
		Hello, <%= name %>! Messaging: <%= their_name %>!</h1>
 <div class="row align-items-center">
	 <div class="col-10">
		<label for="message">Message:</label>
		<input type="text" name="message" id="message" class="form-control"/>
	 </div>
	 <div class="col-2 text-center">
		<button id="send-message" class="btn btn-success">Send</button>
	</div>
 </div>
 <% } %>
 <div class="row mt-3 mb-4">
	 <h3>Message Log:</h3>
	 <div id="messages">
	 </div>
	<% for(Message m: messages) {%>
		
	<%} %>
 </div>
 </div>
 <script>
 var user_messages = <%= messagesStr %>;
 
 function generateMessage(message)
 {
	 return `
	 	<div class="row align-items-center">
			<div><h3>\${message.nFrom}</h3>: <p>\${message.message}</p><p>Sent at: \${message.time}</p></div>
		</div>`;
 }

 
 function addMessages(messages)
 {
	 $('#messages').empty();

	 for(var i = 0; i < messages.length; i++)
	 {
		 $('#messages').append(generateMessage(messages[i]));
	 }	 
 }
 
 function populateMessages(){
	 addMessages(user_messages);
 }
 
 populateMessages();
 
 $('#send-message').click(function(){
	 var msg = $('#message').val();
	 $.post('./api/message/send', {message: $('#message').val(), userID: <%= their_id %>}, function(data){
	  	 if(data.success)
	  	 {
	  		 $('#message').val('');
	  		 user_messages.push({ nFrom: "<%= name %>", message: msg, time: "Now" });
	  		 populateMessages();
	  	  }
	  	 else
	     {
	  		 alert("Error: " + data.error);
	     }
	 });
 });
 
 setInterval(function(){ 
	    //code goes here that will be run every 2 seconds.
	     $.get('./api/messages/new', {time: Date.now() - 1000*2}, function(data){
	  	 if(data.success)
	  	 {
	  		var msgs = data.messages;
	  		if(msgs.length > 0)
	  		{
	  			for(var i = 0; i < msgs.length; i++)
	  			{
	  				var newMsg = msgs[i];
	  				newMsg.nFrom = "<%= their_name %>";
	  				newMsg.nTo = "<%= name %>";
	  				user_messages.push(newMsg);
	  			}
	  			populateMessages();
	  		}
	  	  }
	  	 else
	     {
	  		 alert("Error: " + data.error);
	     }
	});
	}, 2500);
 </script>
</body>
</html>