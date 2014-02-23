<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.twitter.stores.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/TwitterClone_Cassandra/">
<link REL="StyleSheet" TYPE="text/css" HREF="css/Details.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	HttpSession s = request.getSession();

	String tweetID = ((String) request.getAttribute("TweetID"));
	TweetStore tweet = ((TweetStore) request.getAttribute("Tweet"));
	
	UserStore currUser = ((UserStore) s.getAttribute("UserDetails"));
	String userName = currUser.getName();
%>


<title>Tweet: <%=tweetID%></title>
</head>
<body>


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

	<%if(tweet != null){ %>
		<a href="ProfileGetter/<%=tweet.getUser()%>"
			style="text-decoration: none;"><font size="4"><STRONG><%=tweet.getUser()%></STRONG></font></a>
		<%=":   " + tweet.getTweet()%></a> <br>
		<font size="2"><%=tweet.getWhen()%></font>

		<%
			if (currUser.getID().equals(tweet.getUserID())) {
		%>
		<form class="button" name="deleteTweet"
			action="DeleteTweet/<%=tweet.getTweetID()%>" method="get">
			<input type="submit" value="Delete" id="delete">
		</form>
		<%}%>
	<%} else {%>
	<p>TWEET DELETED!</p>
		<%} %>

	</div>


</body>
</html>