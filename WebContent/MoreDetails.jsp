<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.twitter.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="StyleSheet" TYPE="text/css" HREF="css/Details.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EDIT PROFILE</title>
</head>
<body>
	<%
		UserStore user = ((UserStore) request.getSession().getAttribute(
				"UserDetails"));
	
		String[] details = new String[5];
		details[0] = user.getName();
		details[1] = user.getBio(); 
		details[2] = user.getFirst();
		details[3] = user.getLast();
		details[4] = user.getSex();
		
		for(int i = 0; i < 4; i++)
		{
			if(details[i] == null)
			{
System.out.println("Details "+i+": "+ details[i]);
				details[i] ="";
			}
		}
		
	%>
	<div id="header">
		<h1>
			<u>BLABARRAMA</u>
		</h1>

		<form name="seeUsers" action="ListUsers" method="Post">
			<p>
				<br> <input type="submit" value="Find more people to follow"
					id="seeUsers">
		</form>
	
		<form name="ownProfile" action="ProfileGetter/<%=details[0]%>" method="get">
			<input type="submit" value="Profile" id="profile">
		</form>
		
		<form class="button" name="seeFeed" action="TweetGetter" method="get">
			<input type="submit" value="Tweet Feed" id="showfeed">
		</form>
		
		<form name="logout" action="LogOut" method="post">
			<input type="submit" value="LOG OUT!" id="logout">
		</form>
	</div>

<div id="content">
	<h1><%=user.getName()%>'s Details
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
            if(details[4] == null)
            {
            	%>
    			<input type="radio" name="sex" value="male">Male
    			<input type="radio" name="sex" value="female"> Female <br>
    			<%
            }
            else
            {
			if(details[4].equals("male"))
			{
			%>
			<input type="radio" name="sex" value="male" checked>Male
			<input type="radio" name="sex" value="female"> Female <br>
			<%} else if(details[4].equals("female")){
			%>
			<input type="radio" name="sex" value="male" >Male
			<input type="radio" name="sex" value="female" checked> Female <br>
			<%}} %>
		<p>
			Say something about your self <br>
			<textarea cols="40" rows="5" name="bio"><%=details[1]%>
</textarea>
		<p>
			PICKKK DATE!!! <br>
		<p>
			Enter First Name: <input name="FirstName" type="text" value=<%=details[2]%>> <br>
		<p>
			Enter Last Name: <input name="LastName" type="text" value=<%=details[3]%>> <br>
			<input type="submit" value="Enter Details" id="enter">
	</form>
</div>
</body>
</html>