<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>


<h1>TWEET WITH CASSANDRA</h1>
<% 
boolean loginWrong;
boolean badDetails;
try
{
 loginWrong = ((Boolean)request.getSession().getAttribute("wrongLogin"));
 badDetails = ((Boolean)request.getSession().getAttribute("badDetails"));
}
catch(Exception e)
{
	loginWrong = false;
	badDetails = false;
}
%>

<h2>Login</h2>
<% 
if(loginWrong)
{%>
  <p><font color="red">LOGIN WRONG</font>    
<%}
%>

<form name="loginform" action= "loginCheck"  method="post">
<p>Enter Email Address: <input name="email" type="text">   Enter Password: <input name="password" type="password">
<br> 
<input type="submit" value="Login">
</form>

<h2>Sign up</h2>
<%
if(badDetails)
{%>
  <p><font color="red">Used Details</font>    
<%}
%>
<form name="signupform" action= "SignUp"  method="post">
<p>Enter Username:      <input name="newName" type="text">  
<br>
<p>Enter Email Address: <input name="newEmail" type="text">   
<br>
<p>Enter Password:      <input name="newPassword" type="password">
<br> 
<input type="submit" value="Sign Up">
</form>
</body>
</html>