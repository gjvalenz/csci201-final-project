<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>


  <%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
   <%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    

<!DOCTYPE html>
<html>
 <head>
 
     <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script crossorigin="anonymous" src="https://kit.fontawesome.com/3204349982.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <link href="index.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	
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
      .fr {
        text-align: center;
      }
      .logout button, .fr button {
          border: solid;
          border-color: transparent;
          border-radius: 5px;
          background-color: transparent;
          font-size: 20px;
          padding: 10px;
      }
      .logout button:hover, .fr button:hover {
          border-color: black;
      }
      .profile {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        gap: 10px;
        grid-auto-rows: minmax(100px, auto);
        text-align: center;
      }
      .name {
        grid-column: 2;
        grid-row: 1;
        font-size: 30px;
        color: black;
      }
      .github {
        grid-column: 2;
        grid-row: 2;
        float: left;
        color: black;
      }
      .company {
        grid-column: 2;
        grid-row: 3;
        float: left;
        color: black;
      }
      .email{
        grid-column: 2;
        grid-row: 4;
        float: left;
        color: black;
      }
      

      
      
       </style>
    
     
       <%
       HttpSession session2 = request.getSession(false);
    
    String name = null;
    
    String emailFrom = null;
    
    String emailTo = null;
    
    Boolean showRequest = false;
    
    Boolean sentRequest = false;
    
    if((String)session2.getAttribute("name")!=null){
    	name = (String)session2.getAttribute("name");
    	
    	emailFrom =  (String)session2.getAttribute("email");
    	emailTo = (String)request.getAttribute("email");
    	
    	session2.setAttribute("email2", emailTo);
    	System.out.println(name);
    	System.out.println(emailFrom);
    	System.out.println(emailTo);
    	System.out.println(emailFrom.equals(emailTo));
    	
    	if(!emailFrom.equals(emailTo) && !(Boolean)request.getAttribute("areFriends")){
    		
    		showRequest = true;
    	}
    	
    	if((Boolean)request.getAttribute("sentRequest")){
    		sentRequest = true;
    		showRequest = false;
    	}
    	 
    }
    
    
    %>
    
  </head>
  

  
  <body>
  
  
  <div class="toppage">
        <a href="index3.jsp">
            <img src="FPlogo.png">
        </a>
        <%if(name!=null) {%>
        <form class="logout" action="LogoutDispatcher" method="GET">
        	<button type="submit" class="btn btn-outline-success me-2" id="custom">Logout</button>
    	</form>
    	<% } else { %>
    	<form class="logout" action="register.jsp">
        	<button href = "register.jsp" class="logout" id="custom">Register</button>
    	</form>
    	<form class="logout" action="login.jsp">
        	<button href = "login.jsp" class="logout" id="custom">Login</button>
    	</form>
    	<% } %>
    </div>
    
    
    <div class="profile">
        <div class="name"><%=(String)request.getAttribute("name")%>'s Profile</div>
        
      
         
        <div class="github">
          <div class="text-center">
				<button id="get-feed" class="btn btn-warning" style="margin-right: 18%">Github</button>
				<a style = "margin-left: 2%" href ="<%=(String)request.getAttribute("github")%>">Github Link</a>
				<br>
				</div>
          
        </div>
        
        
        
        <div class="company">
             <div class="text-center">
				<button id="get-feed" class="btn btn-warning" style="margin-right: 10%; margin-left: -13%" >Company</button>
				 <span style="margin-left: 3%"><%=(String)request.getAttribute("company")%></span>
				 <br>
				</div>
   
        </div>
        
        <br>
        
        <div class="email">
             <div class="text-center">
				<button id="get-feed" class="btn btn-warning" style="margin-right: 10%">Email</button>
				 <span style="margin-left: 3%"><%=(String)request.getAttribute("email")%></span>
				 <br>
				</div>
         
        </div>
       
        
        <br>
    </div>
	
	<%
      if(name!=null && showRequest==true){ 
      %>
  <form class="fr" action="SendDispatcher" method="GET">
    <button type="submit" class="btn btn-outline-success me-2" id="custom" name="fr" value="fr">Send Friend Request</button>
  </form>
  <% } %>
  
  <%if(name!=null && sentRequest==true){%>
  	<div class="fr">
    	<button class="btn btn-outline-success me-2" id="custom" name="frsent" value="frsent">Sent Friend Request</button>
    </div>
  <% } %>
  
  
    
  </body>
</html>
