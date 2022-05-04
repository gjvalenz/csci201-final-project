<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>


  <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
   <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
 <head>
    <title>Profile</title>
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
          font-size: 15px;
          padding: 10px;
      }
      .logout button:hover {
          border-color: black;
      }
      .profile {
          text-align: center;
      }
      .name {
          font-size: 50px;
      }
      .github {
          font-size: 25px;
      }
      .company {
          font-size: 25px;
      }
      .email{
      	font-size: 25px;
      }
    </style>
  </head>
  

  
  <body>
    <div class="toppage">
        <a href="index.jsp">
            <img src="FPlogo.png">
        </a>
        <div class="logout">
            <button onclick="window.location.href='index.jsp';">Logout</button>
        </div>
    </div>
    <div class="profile">
        <div class="name">Name: <%=(String)request.getAttribute("name")%></div>
        <div class="github">Github: <%=(String)request.getAttribute("github")%></div>
        <div class="company">Company: <%=(String)request.getAttribute("company")%></div>
        <div class="email">Email: <%=(String)request.getAttribute("email")%></div>
        <div>Posts</div>
    </div>
    
    <c:forEach var="User" items="${profile}">

	<hr style="height:2px; width:90%; border-width:0; color:grey; background-color:grey; position: center; align:center">
	
	<div class="restaurant_" style="margin-left: 7.5% ">
	<span style="display: inline">
	
	<a href = "ProfileDispatcher?email=${User.getEmail()}" >${User.getName()}</a><br>
	</c:forEach>
	</span>
	<span>
	<br>
	<br>
  </body>
</html>
