<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.twitter.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EDIT PROFILE</title>
</head>
<body>
	<%
		UserStore user = ((UserStore) request.getSession().getAttribute(
				"UserDetails"));
	
	    String currBio = user.getBio();       
	    String fName = user.getFirst();
	    String lName = user.getLast();
	    String gender = user.getSex();
	%>

	<h1><%=user.getName()%>'s Profile
	</h1>

	<p>
		User Name:
		<%=user.getName()%>
	<p>
		Email Address:
		<%=user.getEmail()%>
		<br> <br>
	<form name="loginform" action="EditUser" method="post">
		<p>
			Select gender:<br>
			<% 
			System.out.println(gender);
            if(gender == null)
            {
            	%>
    			<input type="radio" name="sex" value="male">Male
    			<input type="radio" name="sex" value="female"> Female <br>
    			<%
            }
            else
            {
			if(gender.equals("male"))
			{
			%>
			<input type="radio" name="sex" value="male" checked>Male
			<input type="radio" name="sex" value="female"> Female <br>
			<%} else if(gender.equals("female")){
			%>
			<input type="radio" name="sex" value="male" >Male
			<input type="radio" name="sex" value="female" checked> Female <br>
			<%}} %>
		<p>
			Say something about your self <br>
			<textarea cols="40" rows="5" name="bio"><%=currBio%>
</textarea>
		<p>
			PICKKK DATE!!! <br>
		<p>
			Enter First Name: <input name="FirstName" type="text" value=<%=fName%>> <br>
		<p>
			Enter Last Name: <input name="LastName" type="text" value=<%=lName%>> <br>
			<input type="submit" value="Enter Details">
	</form>

</body>
</html>