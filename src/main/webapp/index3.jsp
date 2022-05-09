

<!DOCTYPE html>
<html>
  <head>
    <title>Home</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
      crossorigin="anonymous"
    />
    <style>
            body {
              background-size: contain;
              background-color: #6367dd;
              background-image: linear-gradient(#d0d9dc, #6367dd);
              background-repeat: no-repeat;
            }
            #contains-nav {
            	width:100%;
            	position:fixed;
            	top:0px;
            }
            .container-fluid {
            float:right;
            }
            #filler {
            	height:100px;
            }
            img {
              margin-top: 100px;
              display: block;
              margin-left: auto;
              margin-right: auto;
            }
            #custom {
            	margin-left:20px;
            	margin-right:20px;
            }
            #main{
            	text-align: center;
            }
            #gighub {
            	height:30%;
            	width:auto;
            	-webkit-transform: scale(1.3);
             -ms-transform: scale(1.3);
             transform: scale(1.3);
            	transition: transform 3.5s;
            	animation: pulse 1s linear infinite;

            }
            #container {
            z-index:-1000000;
            }
            @-webkit-keyframes "pulse" {
      0% {
          -webkit-transform: scale(1.1);
          transform: scale(1.5);
          transition: transform 8.5s;
      }
      50% {
         -webkit-transform: scale(0.8);
         transform: scale(1.3);
         transition: transform 7.5s;
      }
      100% {
          -webkit-transform: scale(1);
         transform: scale(1.1);
         transition: transform 3.5s;
      }
      }
            #row {
            	display:flex;
            	flex-direction:row;
            }
            #gighub:hover {
             -webkit-transform: scale(1.5);
             -ms-transform: scale(1.5);
             transform: scale(1.5);
             transition: transform 1.5s;
            }
            #gighub:hover main{
            	background-image: linear-gradient(#6367dd,#d0d9dc);
            	transition: transform 1.5s;
            }
            #footer {
            	height:50px;
            	background-color:black;
            	color:white;
            	padding-top:15px;
            	font-family: 'Courier New', Courier, monospace;

            }
            #container {
            width: 100%;
            height:590px;
            /* height: 500px; */

            -webkit-perspective: 800; /* For compatibility with iPhone 3.0, we leave off the units here */
            -webkit-perspective-origin: 50% 225px;
          }
          #stage {
            width: 100%;
            height: 100%;
            -webkit-transition: -webkit-transform 2s;
            -webkit-transform-style: preserve-3d;
          }

          #shape {
            position: relative;
            top: 160px;
            /* left:1000px; */
            margin: 0 auto;
            height: 200px;
            width: 200px;
            -webkit-transform-style: preserve-3d;
          }

          .plane {
            position: absolute;
            height: 200px;
            width: 200px;
            border: 1px solid white;
            -webkit-border-radius: 12px;
            -webkit-box-sizing: border-box;
            text-align: center;
            font-family: Times, serif;
            /* font-size: 124pt; */
            font-size: 40pt;
            text-align: center;
            box-sizing: border-box;
            padding-top:50px;
            margin-bottom; auto;
            color: black;
            background-color: rgba(255, 255, 255, 0.6);
            -webkit-transition: -webkit-transform 2s, opacity 2s;
            -webkit-backface-visibility: hidden;
          }
          #shape.backfaces .plane {
            -webkit-backface-visibility: visible;
          }

          #shape {
            -webkit-animation: spin 8s infinite linear;
          }
          @-webkit-keyframes spin {
            from { -webkit-transform: rotateY(0); }
            to   { -webkit-transform: rotateY(-360deg); }
          }
          /* ---------- cube styles ------------- */
          .cube > .one {
            -webkit-transform: scale3d(1.2, 1.2, 1.2) rotateX(90deg) translateZ(100px);
          }
          .cube > .two {
            -webkit-transform: scale3d(1.2, 1.2, 1.2) translateZ(100px);
          }
          .cube > .three {
            -webkit-transform: scale3d(1.2, 1.2, 1.2) rotateY(90deg) translateZ(100px);
          }
          .cube > .four {
            -webkit-transform: scale3d(1.2, 1.2, 1.2) rotateY(180deg) translateZ(100px);
          }
          .cube > .five {
            -webkit-transform: scale3d(1.2, 1.2, 1.2) rotateY(-90deg) translateZ(100px);
          }
          .cube > .six {
            -webkit-transform: scale3d(1.2, 1.2, 1.2) rotateX(-90deg) translateZ(100px) rotate(180deg);
          }
          .cube > .seven {
            -webkit-transform: scale3d(0.8, 0.8, 0.8) rotateX(90deg) translateZ(100px) rotate(180deg);
          }
          .cube > .eight {
            -webkit-transform: scale3d(0.8, 0.8, 0.8) translateZ(100px);
          }
          .cube > .nine {
            -webkit-transform: scale3d(0.8, 0.8, 0.8) rotateY(90deg) translateZ(100px);
          }
          .cube > .ten {
            -webkit-transform: scale3d(0.8, 0.8, 0.8) rotateY(180deg) translateZ(100px);
          }
          .cube > .eleven {
            -webkit-transform: scale3d(0.8, 0.8, 0.8) rotateY(-90deg) translateZ(100px);
          }
          .cube > .twelve {
            -webkit-transform: scale3d(0.8, 0.8, 0.8) rotateX(-90deg) translateZ(100px);
          }
          /* ---------- ring styles ------------- */
          .ring > .one {
            -webkit-transform: translateZ(380px);
          }
          .ring > .two {
            -webkit-transform: rotateY(30deg) translateZ(380px);
          }
          .ring > .three {
            -webkit-transform: rotateY(60deg) translateZ(380px);
          }
          .ring > .four {
            -webkit-transform: rotateY(90deg) translateZ(380px);
          }
          .ring > .five {
            -webkit-transform: rotateY(120deg) translateZ(380px);
          }
          .ring > .six {
            -webkit-transform: rotateY(150deg) translateZ(380px);
          }
          .ring > .seven {
            -webkit-transform: rotateY(180deg) translateZ(380px);
          }
          .ring > .eight {
            -webkit-transform: rotateY(210deg) translateZ(380px);
          }
          .ring > .nine {
            -webkit-transform: rotateY(-120deg) translateZ(380px);
          }
          .ring > .ten {
            -webkit-transform: rotateY(-90deg) translateZ(380px);
          }
          .ring > .eleven {
            -webkit-transform: rotateY(300deg) translateZ(380px);
          }
          .ring > .twelve {
            -webkit-transform: rotateY(330deg) translateZ(380px);
          }
          .controls {
            width: 80%;
            margin: 0 auto;
            padding: 5px 20px;
            -webkit-border-radius: 12px;
            background-color: rgba(255, 255, 255, 0.5);
          }
          .controls > div {
            margin: 10px;
          }
          .HelloUser{
      			color: white;
            font-size: 17px;
            text-align: center;
            margin-left: -30%;
            font-family: Roboto;
      		}
      		.loginandregister {
      			margin-left:20%;
      			float:left;
      		}
    </style>

    <%HttpSession session2 = request.getSession(false); String name = null;
    String email = null; if(session2!=null){ name =
    (String)session2.getAttribute("name"); email =
    (String)session2.getAttribute("email"); } %>
  </head>

  <body>
    <div id="main">
      <div id="contains-nav">
        <nav class="navbar navbar-dark bg-dark">
          <div class="container-fluid">
            <a class="navbar-brand" href="index3.jsp" width="30px ">GigHub</a>

            <!-- <video
              autoplay
              playsinline
              style="pointer-events: none"
              width="120"
              height="40"
              autoplay="autoplay"
              loop="loop"
              muted
            >
              <source src="G-6.mp4" type="video/mp4" />
            </video> -->

            <div class="HelloUser">${HelloUser}</div>

            <% if(name==null){ %>
            <!-- <div class="loginandregister" style="width:70%"> -->
              <div style="width:87%"></div>
              <button
                class="btn btn-outline-success me-2"
                id="custom"
                type="button"
                onclick="location.href='login.jsp'"
              >
                Login
              </button>
              <button
                class="btn btn-outline-success me-2"
                id="custom"
                type="button"
                onclick="location.href='register.jsp'"
              >
                Register
              </button>
              <form class="d-flex">
              <button
                class="btn btn-outline-success me-2"
                id="custom"
                type="button"
                onclick="location.href='search.jsp'"
              >
                Search For A User
              </button>
            </form>
            
            <!-- </div> -->
            

            <% } %> <% if(name!=null){ %>
				<div class="loginandregister" style="margin-left: 20%">
						<form class="logout" action="ProfileDispatcher"
							style='display: inline;' method="GET">
							<button type="submit" class="btn btn-outline-success me-2"
								id="custom" name="you" value="<%=email%> ">
								Profile</button>
						</form>

						<form class="logout" style='display: inline;'
							action="createpost.jsp">
							<button class="btn btn-outline-success me-2" id="custom">
								Feed</button>
						</form>

						<form class="logout" style='display: inline;'
							action="FriendDispatcher" method="GET">
							<button type="submit" class="btn btn-outline-success me-2"
								id="custom">Friends</button>
						</form>


						<form class="logout" style='display: inline;'
							action="FRDispatcher" method="GET">
							<button type="submit" class="btn btn-outline-success me-2"
								id="custom">Friend Requests</button>
						</form>

						<form class="class=logout" style='display: inline;'>
							<button class="btn btn-outline-success me-2" id="custom"
								type="button" onclick="location.href='search.jsp'">
								Search For A User</button>
						</form>

						<form class="logout" action="LogoutDispatcher" method="GET"
							style='display: inline;'>
							<button type="submit" class="btn btn-outline-success me-2"
								id="custom">Logout</button>
						</form>
					</div>

            <% } %>
            <video
              autoplay
              playsinline
              style="pointer-events: none"
              width="120"
              height="40"
              autoplay="autoplay"
              loop="loop"
              muted
            >
              <source src="G-6.mp4" type="video/mp4" />
            </video>
          </div>
        </nav>
      </div>

      <div id="rowe">
        <div id="filler"></div>
        <div id="row">
          <div id="container">
            <div id="stage" style>
              <div id="shape" class="backfaces cube">
                <div class="plane seven draggable"></div>
                <div class="plane two draggable">CSCI</div>
                <div class="plane three draggable">201</div>
                <div class="plane four draggable">Final</div>
                <div class="plane five draggable">Project</div>
                <div class="plane twelve draggable"></div>
              </div>
            </div>
          </div>

          <div id="logo">
            <img id="gighub" class="draggable" src="FPlogo.png" />
          </div>

          <div id="container">
            <div id="stage" style>
              <div id="shape" class="backfaces cube">
                <div class="plane one draggable"></div>
                <div class="plane eight draggable">Share</div>
                <div class="plane nine draggable">Connect</div>
                <div class="plane ten draggable">Chat</div>
                <div class="plane eleven draggable">Hire</div>
                <div class="plane six draggable"></div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div id="footer">
        <p>A project by Group 33</p>
      </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/draggabilly@3/dist/draggabilly.pkgd.min.js"></script>
    <script>
        var $draggable = $('.draggable').draggabilly({
        // options...
        })
        // var draggableElems = document.querySelectorAll('.draggable')
    </script>
    </div>
  </body>
</html>
