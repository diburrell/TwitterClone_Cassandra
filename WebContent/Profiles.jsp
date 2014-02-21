<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.twitter.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	HttpSession s = request.getSession();

	UserStore ProfileOf = ((UserStore) request
			.getAttribute("ProfileOf"));

	UserStore currUser = ((UserStore) s.getAttribute("UserDetails"));

	boolean allreadyFollowing = false;
	boolean ownProfile = false;
	Set<UUID> following = currUser.getFollowing();
	UUID profID = ProfileOf.getID();

	if (profID.compareTo(currUser.getID()) == 0) {
		ownProfile = true;

	} else {
		for (UUID f : following) {
			if (profID.compareTo(f) == 0) {
				allreadyFollowing = true;
			}
		}
	}
%>


<title><%=ProfileOf.getName()%></title>
</head>
<body>
	<p>
		<font Size=6>User name: <%=ProfileOf.getName()%></font> <br>
	<p>
		<font Size=4><u>Bio</u></font>
	<p>
		<font Size=4><%=ProfileOf.getBio()%></font> <br> <br>
	<p>
		<font Size=4>Name: <%=ProfileOf.getFirst()%> <%=ProfileOf.getLast()%></font>
	<p>
		<font Size=4>Birthday: TO DO!</font>
	<p>
		<font Size=4>Gender: <%=ProfileOf.getSex()%></font>
	<p>
		<font Size=4>User email: <%=ProfileOf.getEmail()%></font> <br> <br>

		<%
			if (!ownProfile) {
				if (!allreadyFollowing) {
		%>
	
	<form name="followform" action="/TwitterClone_Cassandra/AddFollower"
		method="post">
		<input type="submit" value="Follow">
	</form>
	<%
		} else {
	%>
	<form name="unfollowform"
		action="/TwitterClone_Cassandra/RemoveFollower" method="post">
		<input type="submit" value="Unfollow">
	</form>
	<%
		}
		} else {
	%>
	<form name="editInfo" action="/TwitterClone_Cassandra/MoreDetails.jsp">
		<input type="submit" value="Edit Profile">
	</form>
	<%
		}
	%>
	<form name="backtofeed" action="/TwitterClone_Cassandra/TweetGetter"
		method="get">
		<input type="submit" value="Back To Feed">
	</form>
	<br>

	<%
		List<TweetStore> lTweet = (List<TweetStore>) request
				.getAttribute("ProfileTweets");

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
	<font size="4"><STRONG><%=ts.getUser()%></STRONG></font>
	</a><%=":   " + ts.getTweet()%></a>
	<br />
	<font size="2"><%=ts.getWhen()%></font>
	<br>
	<br>
	<%
		}
		}
	%>
</body>
</html>