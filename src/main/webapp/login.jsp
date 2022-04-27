<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
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
      .regredirect {
        float: right;
      }
      .regredirect button {
          border: solid;
          border-color: transparent;
          border-radius: 5px;
          background-color: transparent;
          font-size: 15px;
          padding: 10px;
      }
      .regredirect button:hover {
          border-color: black;
      }
      .login {
          display: block;
          text-align: center;
      }
      .email input, .password input {
          display: block;
          margin-left: auto;
          margin-right: auto;
          margin-bottom: 15px;
          border: solid;
          border-radius: 5px;
      }
      .login button {
          font-size: 15px;
          background-color: #d0d9dc;
          border-radius: 5px;
          padding-right: 10px;
          padding-left: 10px;
      }
      .login button:hover {
          background-color: #6367dd;
      }
    </style>
  </head>
  <body>
    <div class="toppage">
        <a href="index.jsp">
            <img src="FPlogo.png">
        </a>
        <div class="regredirect">
            <button onclick="window.location.href='register.jsp';">Register A New Account</button>
        </div>
    </div>
    <form class="login" action="" method="post">
        <div class="email">
            <label for="email">Email:</label>
            <input type="text" id="password">
        </div>
        <div class="password">
            <label for="password">Password:</label>
            <input type="text" id="password">
        </div>
        <button type="submit">Login</button>
    </form>
  </body>
</html>
