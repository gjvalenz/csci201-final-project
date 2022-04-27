<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="301645777112-2rlc9gth0f5d4reimjcm9bf0kj7ahec0.apps.googleusercontent.com"
          name="google-signin-client_id">
    <title>Login / Register</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link crossorigin href="https://fonts.gstatic.com" rel="preconnect">
    <link
            href="https://fonts.googleapis.com/css2?family=Lobster&family=Roboto:wght@300&display=swap"
            rel="stylesheet">
    <script crossorigin="anonymous"
            src="https://kit.fontawesome.com/3204349982.js"></script>
            <script
  src="https://code.jquery.com/jquery-3.6.0.min.js"
  integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  crossorigin="anonymous"></script>
    <link href="index.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto"
          rel="stylesheet" type="text/css">
</head>
<body>
 <div class="register">
 <form class="blocky" method="POST" action="./api/user/register">
    <h1>Register </h1>
    <div class="pad"><p>Name:</p><input size="50" id="login-name" type="text" name="name" required /></div>
 	<div class="pad"><p>Email:</p><input size="50" id="login-email" type="text" name="email" pattern="^(.+)@(.+)$" required /></div>
 	<div class="pad"><p>Password:</p><input size="50" id="login-password" type="password" name="password" required /></div>
 	<div class="pad"><p>Confirm Password:</p><input size="50" id="login-password" type="password" name="confirmPassword" required /></div>
 	<div class="pad"><p>Github:</p><input size="50" id="login-name" type="text" name="github" /></div>
 	<div class="pad"><p>Company:</p><input size="50" id="login-email" type="text" name="company" /></div>
 	<input type="submit" value="submit" />
 </form>
 </div>
</body>
</html>