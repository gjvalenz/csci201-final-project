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
      }
      .github {
        grid-column: 1;
        grid-row: 2;
      }
      .company {
        grid-column: 2;
        grid-row: 2;
      }
      .email{
        grid-column: 3;
        grid-row: 2;
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
          <h2>Github</h2>
          <a href="<%=(String)request.getAttribute("github")%>">Github Link</a>
        </div>
        <div class="company">
          <h2>Company</h2>
          <%=(String)request.getAttribute("company")%>
        </div>
        <div class="email">
          <h2>Email</h2> 
          <%=(String)request.getAttribute("email")%>
        </div>
    </div>
	<hr style="height:2px; width:90%; border-width:0; color:grey; background-color:grey; position: center; align:center">
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
