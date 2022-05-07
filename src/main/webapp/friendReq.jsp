
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

         
  <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
   <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
  <head>
    <title>Register</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
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
      .loginredirect {
        float: right;
      }
      .loginredirect button {
          border: solid;
          border-color: transparent;
          border-radius: 5px;
          background-color: transparent;
          font-size: 25px;
          padding: 10px;
      }
      .loginredirect button:hover {
          border-color: black;
          
      }
      .register {
          display: block;
          text-align: center;
      }
      
		.box{
		    width: 100%;
		    text-align:center;
		}
		
		.box input {
	     height: 50px;
	     width: 800px;
	}
		.borderless td, .borderless th {border: none;}
		
		.section-wrap {
		  padding: 50px;
		  overflow: hidden;
		  text-align:center;
		}
		
      .profile {
          text-align: center;
      }
      .name {
          font-size: 50px;
      }


    </style>
  </head>
  <body>
    <div class="toppage">
        <a href="index3.jsp">
            <img src="FPlogo.png">
        </a>
        <div class="loginredirect">
            <button onclick="window.location.href='index3.jsp';">Logout</button>
        </div>
      </div>
    <!--  change this to do a call via ajax so we don't actually need to do form[POST] -->

	<div>
    <div class="profile">
        <div class="name">Friend Requests: <%=(String)request.getAttribute("name")%></div>
    </div>
  				  
  				    
	<c:forEach var="Profile" items="${results}">

	<hr style="height:2px; width:90%; border-width:0; color:grey; background-color:grey; position: center; align:center">
	
	<div class="restaurant_" style="margin-left: 7.5% ">
	
	<span style="display: inline">
	
	</span>

	<span>
	<br>

	
	<a href = "ProfileDispatcher?email=${Profile.getEmail()}" >${Profile.getName()}</a><br>
	<br>

	</span>
	
	
	
	
	



	<br>
	<br>
	
	</div></c:forEach>
	      		

  </body>
</html>
