<!DOCTYPE html>
<html>
  <head>
    <title>Register</title>
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
      
      		
		.rerror, .lerror{
			margin-top: 1%;
			background-color: #ffcccc;
			padding-top: 2%;
			padding-bottom: 2%;
			text-align: center;
			
			
		}
		
		.rerror:empty, .lerror:empty{
			display:none;
		}
    </style>
  </head>
  <body>
 
  

    <div class="toppage">
        <a href="index3.jsp">
            <img src="FPlogo.png">
        </a>
        <div class="loginredirect">
            <button onclick="window.location.href='login.jsp';">Login To An Existing Account</button>
        </div>>
    </div>
    <!--  change this to do a call via ajax so we don't actually need to do form[POST] -->
   
     <div class="rerror" style="color:#7F7F7F; text-align: center;, font-family:Roboto">${register_error}</div>
     
    
    <form class="register" id = "register-modal">
 	 
        <div class="email">
            <label for="email">Email:</label>
            <input name="email" type="text" id="email">
        </div>
        <div class="name">
          <label for="name">Name:</label>
          <input name="name" type="text" id="name">
      </div>
        <div class="password">
            <label for="password">Password:</label>
            <input name="passkey" type="text" id="password">
        </div>
        <div class="confirmPassword">
          <label for="confirmPassword">Confirm Password:</label>
          <input name="confirmpasskey" type="text" id="confirmPassword">
      </div>
      <div class="github">
        <label for="github">Github URL:</label>
        <input name="github" type="text" id="github">
    </div>
    <div class="company">
      <label for="company">Company:</label>
      <input name="company" type="text" id="company">
  </div>
  

        <button type="submit">Register</button>
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
    $('#register-modal').submit(function(e)
    {
  	  e.preventDefault();
  	  var dt = getFormData($(this));
  	  console.log(dt);
  	  $.get('./RegisterDispatcher', dt, function(data){
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
