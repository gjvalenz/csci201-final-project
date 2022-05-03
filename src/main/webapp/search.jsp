<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
        rel="stylesheet">
    <script src="https://kit.fontawesome.com/3204349982.js" crossorigin="anonymous"></script>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ page import ="Util.User" %>
    <%@ page import ="java.util.*" %>
     <% 
     	ArrayList<User> profiles = (ArrayList<User>)request.getAttribute("results");
     %>
        
    <style>
    @font-face {
    	font-family:"Lobster";
    	src:url("Lobster-Regular.ttf");
    }
    #main {
    	text-align:center;
    }
    #logo {
    	font-family:"Lobster", serif;
    	color:red;
    	text-align:left;
    	font-size:20px;
    }
   	ul {
	  list-style-type: none;
	  margin-left: 20px;
      margin-right:20px;
	  padding: 0;
	  display: flex;
	  flex-direction: row;
	  justify-content:space-between;
	  padding-bottom:10px;
	}
	li {
		padding:5px;
	}
	a {
		text-decoration:none;
	}
	 .other {
		color: gray;
	} 
	#nav {
		display:flex;
		flex-direction: row;
	} 
	.rad {
		color:gray;
		padding-right:100px;
	}
	#searchbar {
		width:40%;
	}
	.fa {
		color:white;
	}
	#redbutton {
		background-color:rgb(185, 28, 28);
		width:35px;
	}
	#rating {
	/* margin-left: 350px; */
	/* padding-left:350px; */
	/* float:right; */
	text-align:right;
	justify-content:right;
	}
	#radio {
		width:30px;
	}
	#ratings {
	padding-left:37.8%;
	}
	#spec {
    max-width: 100%;
    max-height: 100%;
    border-radius: 15px;
	border: 2px solid gray;
}
	
	#cont {
	display:flex;
	flex-direction:row;
	margin-left: 30px;
	}
	
	#contt {
		text-align:left;
	}
	#star {
		width:10px;
		height:auto;
	}
    </style>
</head>
<body>
    <div>
	<% if(profiles.size() == 0) {%>
	<p style = "padding: 20px">Sorry, there are no profiles that meet your search filters</p>
	<% } else { %>
		<c:forEach var = "profile" items = "${profiles}">
		<div style = "border-bottom: 1px solid #E2E2E2">
		<div style = "margin-left: 30px; padding-top: 10px">
		<a href = "ProfileDispatcher?email=${profile.getEmail()}" >${profile.getName()}</a><br>
		</div>
		</div>
		</c:forEach>
	<% } %>
	</div>
</body>
</html>
