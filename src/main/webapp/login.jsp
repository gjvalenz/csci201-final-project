<!DOCTYPE html>
<html>
  <head>
    <title>Login</title>
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
        <a href="index3.jsp">
            <img src="FPlogo.png">
        </a>
        <div class="regredirect">
            <button onclick="window.location.href='register.jsp';">Register A New Account</button>
        </div>
    </div>
    <form class="login" id="login-modal">
        <div class="email">
            <label for="email">Email:</label>
            <input name="email" type="text" id="email">
        </div>
        <div class="password">
            <label for="password">Password:</label>
            <input name="password" type="text" id="password">
        </div>
        <button type="submit">Login</button>
    </form>
    <script>
    // https://stackoverflow.com/questions/11338774/serialize-form-data-to-json
    function getFormData($form){
  	    var unindexed_array = $form.serializeArray();
  	    var indexed_array = {};
  	    $.map(unindexed_array, function(n, i){
  	        indexed_array[n['name']] = n['value'];
  	    });
  	    return indexed_array;
  	}
    $('#login-modal').submit(function(e)
    {
  	  e.preventDefault();
  	  var dt = getFormData($(this));
  	  console.log(dt);
  	  $.get('./LoginDispatcher', dt, function(data){
  		if(data.success)
		  	 {
  				localStorage.setItem('userInfo', JSON.stringify(data));
		  		window.location.replace("index3.jsp");
		  	  }
		  	 else
		     {
		  		 alert("Error: " + data.error);
		     }
  		 });
    });
    </script>
  </body>
</html>
