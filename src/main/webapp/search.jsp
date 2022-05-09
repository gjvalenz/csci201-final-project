
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
         
  <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
   <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
  <head>
    <title>Search</title>
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
      .loginredirect {
        float: right;
      }
      .loginredirect button {
          border: solid;
          border-color: transparent;
          border-radius: 5px;
          background-color: transparent;
          font-size: 15px;
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
		
		.buttonHolder{ text-align: center; }
			
		a, a:hover, a:focus, a:active {
     		 text-decoration: none;
     		 color: inherit;
		 }


    </style>
    
       <%HttpSession session2 = request.getSession(false);
    
    String name = null;
    
    if(session2!=null){
    	
    	name = (String)session2.getAttribute("name");
    }
    %>
    
  </head>
  <body>
    <div class="toppage">
        <a href="index3.jsp">
            <img src="FPlogo.png">
        </a>
        <div class="loginredirect">
    
            
            	                <% if(name == null){ %>
  		<span >
	  <button class="btn btn-outline-success me-2" id="custom" type="button" onclick="location.href='login.jsp'">Login</button>
	  <button class="btn btn-sm btn-outline-secondary" id="custom" type="button" onclick="location.href='register.jsp'">Register</button>
	  </span>

	 
	
  <% } %>
  
    <%
  if(name != null){ 
  %>
  	
	     <form class="logout" action="LogoutDispatcher" method="GET">
        	<button type="submit" class="btn btn-outline-success me-2" id="custom">Logout</button>
    	</form>
  
  <% }
  
  %>
  
  
        </div>
      </div>
    <!--  change this to do a call via ajax so we don't actually need to do form[POST] -->
	<form action="SearchDispatcher" method="GET">
	
	


      		<br>
        		
  					<div class="box">
    					<input name="searchbar" type="text" class="form-control" id="searchbar"  placeholder="Search by Name or Company">
  					</div>
  			
  		<div class="section-wrap">
		   <input id="name" name="category" type="radio" value="name" checked class="flavors-radio">
		   <label for="name"><span></span>Name</label>
		        <input id="company" name="category" type="radio" value="company" class="flavors-radio">
		   <label for="company"><span></span>Company</label>
		 </div>
		 
		<div class="buttonHolder">
      		<br>
 
  				<button type="submit" name="submit" value="Submit Form" style="height:30px; width:100px"><i class="fa fa-search">Search</i></button>

  			</div>
  			
  			</form>
  			
  			<%if (request.getAttribute("keyWord_") != null) {%>
  			
  				  <h1 style="margin-left: 5%; font-size: 16px; font-family: Roboto">Results for ${keyWord_} in ${searchType}</h1>
  				  
  				  <% } %>
  				    
	<c:forEach var="Profile" items="${results}">

	<hr style="height:1px; width:90%; border-width:0; color:black; background-color:black; position: center; align:center">
	
	<div class="restaurant_" style="margin-left: 7.5%; display: flex;">
	
	<br>
	<div style = "width: 10%; float: left">
	<h1 style = "font-size: 40px"><i class="fa fa-user"></i></h1></div>
	<div style = "width: 80%; float: right">
	<br>
	<a style = "max-width: 50" href = "ProfileDispatcher?email2=${Profile.getEmail()}" >${Profile.getName()}</a><br>
	<p style = "color: #3B3B3B "> Email: ${Profile.getEmail()} </p>
	</div>
	<br>


	<br>
	<br>
	
	</div></c:forEach>
	      		

  </body>
</html>
