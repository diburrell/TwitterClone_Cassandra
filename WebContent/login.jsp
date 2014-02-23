<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.twitter.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link REL="StyleSheet" TYPE="text/css" HREF="css/Basic.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<%
		
			boolean loginWrong;
			boolean badDetails;
			try {
				loginWrong = ((Boolean) request.getSession().getAttribute(
						"wrongLogin"));
				badDetails = ((Boolean) request.getSession().getAttribute(
						"badDetails"));
			} catch (Exception e) {
				loginWrong = false;
				badDetails = false;
			}
		
	%>


	<div id="base">
		<h1>
			<u>BLABARAMA</u>
		</h1>
		<%
			if (loginWrong) {
		%>
		<p>
			<font color="red">USER NOT FOUND!</font>
			<%
				loginWrong = false;
				}
			%>
		
		<form name="loginform" onsubmit="return fullForm(this)"
			action="loginCheck" method="post">
			<p>
				Enter Email Address: <br> <input name="email" type="email"
					required> <br> <br> Enter Password: <br> <input
					name="password" type="password" required> <br> <input
					type="submit" value="Login" id="button">
			</p>
		</form>
	</div>




	<div id="base">
		<p>Not a member? Sign up here</p>
		<form name="signupform" action="SignUpForm.jsp">
			<input type="submit" value="Sign Up" id="button">
		</form>
	</div>

</body>
</html>