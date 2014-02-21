<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="com.twitter.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Tweets</title>
</head>
<body>

	<%
		UserStore user = ((UserStore) request.getSession().getAttribute(
				"UserDetails"));

		String userName = user.getName();
		UUID userID = user.getID();
	%>

	<h1>
		What's going on
		<%=userName%></h1>

	<form name="newTweet" action="WriteTweet" method="post">
		<p>
			<STRONG>New Tweet</STRONG> <br>
			<textarea cols="40" rows="5" name="tweet">
</textarea>
			<br> <input type="submit" value="Send">
	</form>
	<br>
	<form name="seeUsers" action="ListUsers" method="Post">
		<p>
			<br> <input type="submit" value="Find more people to follow">
	</form>




	<%
		System.out.println("In render");
		List<TweetStore> lTweet = (List<TweetStore>) request
				.getAttribute("Tweets");
		if (lTweet == null) {
	%>
	<p>No Tweet found</p>
	<%
		} else {
	%>


	<%
		Iterator<TweetStore> iterator;

			iterator = lTweet.iterator();
			while (iterator.hasNext()) {
				TweetStore ts = (TweetStore) iterator.next();
	%>
	<a href="ProfileGetter/<%=ts.getUser()%>"><font size="4"><STRONG><%=ts.getUser()%></STRONG></font></a><%=":   " + ts.getTweet()%></a>
	<br />
	<font size="2"><%=ts.getWhen()%></font>
	<br>
	<br>
	<%
		}
		}
	%>

	<form name="signupform" action="LogOut" method="post">
		<input type="submit" value="LOG OUT!">
	</form>

</body>
</html>