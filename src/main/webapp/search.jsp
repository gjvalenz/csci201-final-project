<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
        rel="stylesheet">
    <script src="https://kit.fontawesome.com/3204349982.js" crossorigin="anonymous"></script>
     <% //TODO iterate the cookie and check if user registered 
     	 Boolean logout;
     	 session = request.getSession(false);
	     String user = (String)session.getAttribute("name");
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
    <div id="main">
	   	<ul>
		   	<div>
		   		<li><a id = "logo" href="home.jsp">GigHub</a></li>
		   
		   	</div >
		   	<p style="text-align:left; margin-left: 15px; margin-right: 1080px; color:gray;"><%if(user != null) {
			     	out.println("Hi " + user +"!");
			     } %></p>
		   	<div id="nav">
		   		<li><a class = "other" href="home.jsp">Home</a></li>
			    <li><a class = "other" href="login.jsp">
			    <% if(user != null) {
			    	out.println("Logout");
			    	logout = true;
			    	}
			    	else {
			    		out.println("Login/Register");
			    		logout= false;
			    	}%>
			    </a></li>
		   	</div>
		</ul>
	 
	    <hr></hr>
	    
	    <form action="search.jsp" method="GET">
		 	<div>
		 		<!-- <select name="category" >
					<option>Category</option>
					<option>Restaurant Name</option>
				</select> -->
				<input id="searchbar" type="text" placeholder="american">
				<button type="submit" id="redbutton"><i class="fa fa-search"></i></button>
	
				<input type="radio" name="location" id="la" value="Los Angeles">
				<label class="rad" for="la"> Name </label>
	
				<input type="radio" name="location" id="sf" value="San Francisco">
				<label class="rad" for="sf"> Profession </label>
				<br/>
		 	</div>
		 	<div id="ratings">
				<input type="radio" name="location" value="College Park">
				<label class="rad" id = "rating" for="cp"> Company </label>
	    	</div>
	    </form>
	    <h2 style="color:gray; text-align:left;">Results for b in category</h2>
	    <hr></hr>
	    <div id="cont">
	    	<div id="pic"><img id="spec" src="first.png"></div>
	    	<div id="contt" style="margin-left:15px;">
		    	<a href="details.jsp">Benu</a>
	    		<p style="color:gray;">Price: $$$$</p>
	    		<p style="color:gray;">Review Count: 1102</p>
	    		<p style="color:gray;">Rating: <img id="star" src="star.png"><img id="star" src="star.png"><img id="star" src="star.png"><img id="star" src="star.png"></p>
	    		<p style="color:gray;">Yelp link</p>
		    </div>
	    </div>
	    <hr></hr>
	    <div id="cont">
	    	<div id="pic"><img id="spec" src="second.png"></div>
	    	<div id="contt" style="margin-left:15px;">
		    	<a href="details.jsp">House of Prime Rib</a>
	    		<p style="color:gray;">Price: $$$</p>
	    		<p style="color:gray;">Review Count: 7680</p>
	    		<p style="color:gray;">Rating: <img id="star" src="star.png"><img id="star" src="star.png"><img id="star" src="star.png"><img id="star" src="star.png"></p>
	    		<p style="color:gray;">Yelp link</p>
		    </div>
	    </div>
	    <hr></hr>
	    <div id="cont">
	    	<div id="pic"><img id="spec" src="third.png"></div>
	    	<div id="contt" style="margin-left:15px;">
	    		<a href="details.jsp">Brenda's French Soul Food</a>
	    		<p style="color:gray;">Price: $$</p>
	    		<p style="color:gray;">Review Count: 11348</p>
	    		<p style="color:gray;">Rating: <img id="star" src="star.png"><img id="star" src="star.png"><img id="star" src="star.png"><img id="star" src="star.png"></p>
	    		<p style="color:gray;">Yelp link</p>
		    </div>
	    </div>
    </div>
</body>
</html>