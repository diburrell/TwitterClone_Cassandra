<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.twitter.stores.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="/TwitterClone_Cassandra/">
<link REL="StyleSheet" TYPE="text/css" HREF="css/Details.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	HttpSession s = request.getSession();

	UserStore ProfileOf = ((UserStore) request
			.getAttribute("ProfileOf"));

	UserStore currUser = ((UserStore) s.getAttribute("UserDetails"));

	boolean allreadyFollowing = false;
	boolean ownProfile = false;
	
	String userName=currUser.getName();
	
	Set<UUID> following = currUser.getFollowing();
	UUID profID = ProfileOf.getID();
	
	String bio = ProfileOf.getBio();
	if(bio.equals(null))
	{
		bio = "";
	}
				
	String name = ProfileOf.getFirst() +" "+ ProfileOf.getLast();
	if(name.equals(null))
	{
		name = "";
	}
	
	String gender = ProfileOf.getSex();
	if(gender.equals(null))
	{
		gender = "";
	}
	
	
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
<div id="header">
		<h1>
			<u>BLABARRAMA</u>
		</h1>

		<form name="seeUsers" action="ListUsers" method="Post">
			<p>
				<br> <input type="submit" value="Find more people to follow"
					id="seeUsers">
		</form>
	
		<form name="ownProfile" action="ProfileGetter/<%=userName%>" method="gett">
			<input type="submit" value="Profile" id="profile">
		</form>

		<form class="button" name="seeFeed" action="TweetGetter" method="gett">
			<input type="submit" value="Tweet Feed" id="showfeed">
		</form>

		<form name="logout" action="/TwitterClone_Cassandra/LogOut" method="post">
			<input type="submit" value="LOG OUT!" id="logout">
		</form>
	</div>

<div id="content">
	<p>
		<font Size=6><%=ProfileOf.getName()%></font> <br>
	<p>
		<font Size=4><u>Bio</u></font>
	<p>
		<font Size=4><%=bio%></font> <br> <br>
	<p>
		<font Size=4>Name: <%=name%></font>
	<p>
		<font Size=4>Birthday: TO DO!</font>
	<p>
		<font Size=4>Gender: <%=gender%></font>
	<p>
		<font Size=4>User email: <%=ProfileOf.getEmail()%></font> <br> <br>

		<%
			if (!ownProfile) {
				if (!allreadyFollowing) {
		%>
	
	<form name="followform" action="/TwitterClone_Cassandra/AddFollower"
		method="post">
		<input type="submit" value="Follow" id="enter">
	</form>
	<%
		} else {
	%>
	<form name="unfollowform"
		action="/TwitterClone_Cassandra/RemoveFollower" method="post">
		<input type="submit" value="Unfollow" id="enter">
	</form>
	<%
		}
		} else {
	%>
	<form name="editInfo" action="/TwitterClone_Cassandra/MoreDetails.jsp">
		<input type="submit" value="Edit Profile" id="enter">
	</form>
	<%
		}
	%>
</div>

<div id= feed>
<h2><u>TWEETS</u></h2>
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
	</div>
</body>
</html>