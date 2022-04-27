<!DOCTYPE html>
<html>
  <head>
    <title>Register</title>
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
      .email input, .name input, .password input, .confirmPassword input, .github input, .company input {
          display: block;
          margin-left: auto;
          margin-right: auto;
          margin-bottom: 15px;
          border: solid;
          border-radius: 5px;
      }
      .register button {
          font-size: 15px;
          background-color: #d0d9dc;
          border-radius: 5px;
          padding-right: 10px;
          padding-left: 10px;
      }
      .register button:hover {
          background-color: #6367dd;
      }
    </style>
  </head>
  <body>
    <div class="toppage">
        <a href="index.jsp">
            <img src="FPlogo.png">
        </a>
        <div class="loginredirect">
            <button onclick="window.location.href='login.jsp';">Login To An Existing Account</button>
        </div>>
    </div>
    <form class="register" action="" method="post">
        <div class="email">
            <label for="email">Email:</label>
            <input type="text" id="password">
        </div>
        <div class="name">
          <label for="name">Name:</label>
          <input type="text" id="name">
      </div>
        <div class="password">
            <label for="password">Password:</label>
            <input type="text" id="password">
        </div>
        <div class="confirmPassword">
          <label for="confirmPassword">Confirm Password:</label>
          <input type="text" id="confirmPassword">
      </div>
      <div class="github">
        <label for="github">Github URL:</label>
        <input type="text" id="github">
    </div>
    <div class="company">
      <label for="company">Company:</label>
      <input type="text" id="company">
  </div>
        <button type="submit">Register</button>
    </form>
  </body>
</html>
