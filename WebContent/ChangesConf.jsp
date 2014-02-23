<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.twitter.stores.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="StyleSheet" TYPE="text/css" HREF="css/Details.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Changes Saved</title>
</head>
<body>
	<%
		HttpSession s = request.getSession();
		UserStore currUser = ((UserStore) s.getAttribute("UserDetails"));
		String userName = currUser.getName();
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

		<form name="ownProfile" action="ProfileGetter/<%=userName%>"
			method="get">
			<input type="submit" value="Profile" id="profile">
		</form>

		<form class="button" name="seeFeed" action="TweetGetter" method="get">
			<input type="submit" value="Tweet Feed" id="showfeed">
		</form>

		<form name="logout" action="/TwitterClone_Cassandra/LogOut"
			method="post">
			<input type="submit" value="LOG OUT!" id="logout">
		</form>
	</div>
	<div id="content">
		CHANGES MADE!


		<form name="backtofeed" action="/TwitterClone_Cassandra/TweetGetter"
			method="get">
			<input type="submit" value="Continue" id="enter">
		</form>
	</div>
</body>
</html>