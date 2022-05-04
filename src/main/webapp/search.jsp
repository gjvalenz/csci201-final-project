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
      
		.box{
		    width: 100%;
		    text-align:center;
		}
		
		.box input {
	     height: 50px;
	     width: 800px;
	     border-radius: 5px;
	}
		.borderless td, .borderless th {border: none;}
		
		.section-wrap {
		  padding: 50px;
		  overflow: hidden;
		  text-align:center;
		}
		
		.buttonHolder{ text-align: center; }
			


    </style>
  </head>
  <body>
    <div class="toppage">
        <a href="index3.jsp">
            <img src="FPlogo.png">
        </a>
        <div class="loginredirect">
            <button onclick="window.location.href='login.jsp';">Login To An Existing Account</button>
        </div>
      </div>
    <!--  change this to do a call via ajax so we don't actually need to do form[POST] -->


      		<br>
        		
  					<div class="box">
    					<input name="input" type="text" class="form-control" id="input"  placeholder="Search by Name, Job, or Company">
  					</div>
  			
  		<div class="section-wrap">
		   <input id="name" name="radio3" type="radio" value="radio_btn"  class="flavors-radio">
		   <label for="name"><span></span>Name</label>
		        <input id="job" name="radio3" type="radio" value="radio_btn" class="flavors-radio">
		   <label for="job"><span></span>Job</label>
		        <input id="companny" name="radio3" type="radio" value="radio_btn" checked class="flavors-radio">
		   <label for="comapny"><span></span>Company</label>
		 </div>
		 
		<div class="buttonHolder">
      		<br>
  				<button type="submit" name="submit" value="Submit Form" style="height:30px; width:100px"><i class="fa fa-search">Search</i></button>
  			</div>
	      		

  </body>
</html>
