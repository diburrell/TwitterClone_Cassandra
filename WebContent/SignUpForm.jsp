<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="StyleSheet" TYPE="text/css" HREF="css/Basic.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign up</title>
</head>
<body>
<div id="base">
<h1><u>Sign up</u></h1>
<% 
boolean loginWrong;
boolean badDetails;
try
{
  badDetails = ((Boolean)request.getSession().getAttribute("badDetails"));
}
catch(Exception e)
{
	badDetails = false;
}
%>


<%
if(badDetails)
{%>
<p><font color="red">Used Details</font></p>  
<%}
%>
<form name="signupform" action= "SignUp"  method="post">
<p>Enter Username:      
<br>
<input name="newName" type="text">  
<br>
Enter Email Address: 
<br>
<input name="newEmail" type="text">   
<br>
Enter Password: 
<br>
<input name="newPassword" type="password">
<br> 
<input type="submit" id ="button" value="Sign Up">
</p>
</form>
</div>

</body>
</html>