<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="com.twitter.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="StyleSheet" TYPE="text/css" HREF="css/HomePage.css">
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

<script src ="js/jquery-1.11.0.js"></script>


<title>Tweets</title>
</head>
<body>
		<%
			UserStore user = ((UserStore) request.getSession().getAttribute(
					"UserDetails"));

			String userName = user.getName();
			UUID userID = user.getID();
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
	
		<form name="ownProfile" action="ProfileGetter/<%=userName%>" method="get">
			<input type="submit" value="Profile" id="profile">
		</form>

		<form class="button" name="seeFeed" action="TweetGetter" method="get">
			<input type="submit" value="Tweet Feed" id="showfeed">
		</form>

		<form name="logout" action="LogOut" method="post">
			<input type="submit" value="LOG OUT!" id="logout">
		</form>
	</div>

	<div id="newTweet">
		<h1>
			What's going on
			<%=userName%></h1>
		<form class="button" name="newTweet" action="WriteTweet" method="post">

			<textarea cols="40" rows="5" name="tweet" maxlength="140">
</textarea>
			<br> <input type="submit" value="Send" id="send">
		</form>
	</div>

	<div id="feed">
		<h2>What your friends are saying</h2>
		<%
			List<TweetStore> lTweet = (List<TweetStore>) request
					.getAttribute("Tweets");
			if (lTweet == null) {
		%>
		<p>No Tweet found</p>
		<%
			} else {
		%>

		<p>

			<%
				Iterator<TweetStore> iterator;

					iterator = lTweet.iterator();
					while (iterator.hasNext()) {
						TweetStore ts = (TweetStore) iterator.next();
			%>
			<a href="ProfileGetter/<%=ts.getUser()%>"  style="text-decoration:none;"><font size="4"><STRONG><%=ts.getUser()%></STRONG></font></a>
			<a href="SingleTweet/<%=ts.getTweetID()%>" style="text-decoration:none;"><%=":   "+ ts.getTweet()%></a>
			<br> <font size="2"><%=ts.getWhen()%></font>
		</p>

		<%
			if (user.getID().equals(ts.getUserID())) {
		%>
		<form class="button" name="deleteTweet"
			action="DeleteTweet/<%=ts.getTweetID()%>" method="get"  onsubmit="return confirm('Are you sure you want to delete this tweet?')">
			<input type="submit" value="Delete" id="delete">
		</form>

		<%
			} else {
		%>
		<br> <br>
		<%
			}
				}
			}
		%>		
	</div>
</body>
</html>