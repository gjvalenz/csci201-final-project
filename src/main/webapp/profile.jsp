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
        <div class="name">Name</div>
        <div class="github">Github</div>
        <div class="company">Company</div>
        <div>Posts</div>
    </div>
  </body>
</html>
