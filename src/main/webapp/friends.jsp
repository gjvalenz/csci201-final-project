<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

         
  <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
   <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
  <head>
    <title>Friends</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/3204349982.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <style>
    body {
        height: 600px;
        background-color: #6367dd;
        background-repeat: no-repeat;
        background-image: linear-gradient(#d0d9dc, #6367dd);
    }
    .toppage img {
        width: 200px;
        height: auto;
    }
    .logout {
        float: right;
    }
    .logout button {
        border: solid;
        border-color: transparent;
        border-radius: 5px;
        background-color: transparent;
        font-size: 20px;
        padding: 10px;
    }
    .logout button:hover {
        border-color: black;
    }
    .friends {
        font-size: 20px;
        color: white;
        text-decoration: none;
    }
    .friends:hover {
        color: blue;
    }
    .friends:active {
        color: yellow;
    }
    .remove {
        background-color: red; /* Green */
        border: none;
        border-radius: 8px;
        color: white;
        padding: 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        cursor: pointer;
    }
    .remove:hover {
        color: yellow;
    }
    </style>
  </head>
  <body>
  
  	<div class = "toppage">
  	<a href="index3.jsp">
            <img src="FPlogo.png">
   </a>
   <form class="logout" action="LogoutDispatcher" method="GET">
        	<button type="submit" class="btn btn-outline-success me-2" id="custom">Logout</button>
    	</form>
    </div>	
    	
 	<h1 style ="margin-left: 5%;">Friends</h1>
  				    
	<c:forEach var="Profile" items="${friends}">

	<hr style="height:2px; width:90%; border-width:0; color:grey; background-color:grey; position: center; align:center">
	
	<div style="margin-left: 7.5%; display: flex; padding-top: 20px; padding-bottom: 20px">


	<div style = "float: left; width: 50%">
	<a class = "friends" style = "max-width: 50%"href = "ProfileDispatcher?email2=${Profile.getEmail()}" >${Profile.getName()}</a> 
	</div>
	<div style = "width: 40%; float: right">
	<form action = "SendDispatcher" method = "GET">
	<button class = "remove" type = "submit" name="remove" value="${Profile.getEmail()}">Remove Friend</button>
	</form>
	</div>
	</div>
	</c:forEach>
  </body>
</html>
