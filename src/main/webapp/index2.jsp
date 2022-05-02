    <style>
      body {
        height: 600px;
        background-color: #6367dd;
        background-image: linear-gradient(#d0d9dc, #6367dd);
        background-repeat: no-repeat;
      }
      .topnav {
        text-align: center;
      }
      .topnav input[type="text"] {
        width: 400px;
        font-size: 20px;
        color: rgb(0, 0, 255);
        border: solid;
        border-color: black;
        border-radius: 10px;
        background-color: transparent;
      }
      .topnav ::placeholder {
        color: rgb(0, 0, 255);
      }
      img {
        margin-top: 100px;
        display: block;
        margin-left: auto;
        margin-right: auto;
      }
      .auth {
        text-align: center;
        font-size: 30px;
        margin-top: 20px;
      }
      .auth button {
        font-size: 30px;
        color: rgb(246, 255, 0);
        text-shadow: 1px 0 0 #000, 0 -1px 0 #000, 0 1px 0 #000, -1px 0 0 #000;
        background-color: transparent;
        border: solid;
        border-color: transparent;
        margin-right: 10px;
        margin-left: 10px;
      }
      .auth button:hover {
        border-color: rgb(246, 255, 0);
        border-radius: 10px;
      }
      
       .auth {
        text-align: center;
        font-size: 30px;
        margin-top: 20px;
      }
      .auth button {
        font-size: 30px;
        color: rgb(246, 255, 0);
        text-shadow: 1px 0 0 #000, 0 -1px 0 #000, 0 1px 0 #000, -1px 0 0 #000;
        background-color: transparent;
        border: solid;
        border-color: transparent;
        margin-right: 10px;
        margin-left: 10px;
      }
      .auth button:hover {
        border-color: rgb(246, 255, 0);
        border-radius: 10px;
      }
      
      button#auth{
      	text-align: center;
        font-size: 30px;
        margin-top: 20px;
        
        font-size: 30px;
        color: rgb(246, 255, 0);
        text-shadow: 1px 0 0 #000, 0 -1px 0 #000, 0 1px 0 #000, -1px 0 0 #000;
        background-color: transparent;
        border: solid;
        border-color: transparent;
        margin-right: 10px;
        margin-left: 10px;
        
      }
      
      
      
      button#auth:hover{
       border-color: rgb(246, 255, 0);
        border-radius: 10px;
      
      }
      
       input#topnav {
        text-align: center;
      }
      input#topnav {
        width: 400px;
        font-size: 20px;
        color: rgb(0, 0, 255);
        border: solid;
        border-color: black;
        border-radius: 10px;
        background-color: transparent;
        
      }
      input#topnav ::placeholder {
        color: rgb(0, 0, 255);
      }
      
 
    </style>
  </head>
  <body>
  	<div class="header" style="background-color: black; height: 12%; margin-top: -1%; margin-left: -1%; margin-right: -1%">
	<span style="display:inline">
	<p id="gighub" style="display:inline; padding-right: 2%"><a href="http://localhost:8080/test2/home.jsp" style="text-decoration: none; color:white; margin-left: 1%; font-family: Roboto; margin-top: 1.5%; font-size: 20px">GigHub</a></p><span class="HelloUser">${HelloUser}
	</span>
	</span>
	
	<span style="display:inline">
	  
      <input id = "topnav" style="margin-top: 1.5%" type="text" placeholder="Search for a User or Post..." />
      <button id = "auth" onclick="window.location.href='login.jsp';" style="margin-top: 1.5%">Login</button>
      <button id = "auth" onclick="window.location.href='register.jsp';">Register</button>
   	
       </span>
	</span>
	


	</div>
 
    <img src="FPlogo.png" />

  </body>