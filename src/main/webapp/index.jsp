<!DOCTYPE html>
<html>
  <head>
    <title>Home</title>
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
    </style>
  </head>
  <body>
    <div class="topnav">
      <input type="text" placeholder="Search for a User or Post..." />
    </div>
    <img src="FPlogo.png" />
    <div class="auth">
      <button onclick="window.location.href='login.jsp';">Login</button>
      <button onclick="window.location.href='register.jsp';">Register</button>
    </div>
  </body>
</html>
