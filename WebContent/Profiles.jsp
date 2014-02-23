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
<script src="/js/jquery-1.11.0.js"></script>

<script src ="js/jquery-1.11.0.js"></script>

<%
	HttpSession s = request.getSession();

	UserStore ProfileOf = ((UserStore) request
			.getAttribute("ProfileOf"));

	UserStore currUser = ((UserStore) s.getAttribute("UserDetails"));

	boolean allreadyFollowing = false;
	boolean ownProfile = false;

	String userName = currUser.getName();

	Set<UUID> following = currUser.getFollowing();
	UUID profID = ProfileOf.getID();

	int followCount = ProfileOf.getFollowCount();
	int followerCount = ProfileOf.getFollowerCount();
				
	String bio = ProfileOf.getBio();
	

	String name = ProfileOf.getFirst() + " " + ProfileOf.getLast();

	String gender = ProfileOf.getSex();
	
	String birthdate = ProfileOf.getBirthday();

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
		<p>
			<font Size=6><%=ProfileOf.getName()%></font> <br>
		<p>
			<font Size=4>Following: <%=followCount%> 
			<br>
			<br> 
			Followers:<%=followerCount%> </font>
		<p>

			<font Size=4><u>Bio</u></font>
		
		<p>
			<font Size=4><%=bio%></font> <br> <br>
		</p>
		<p>
			<font Size=4>Name: <%=name%></font>
		<p>
			<font Size=4>Birthday: <%=birthdate%></font>
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
		<br> <br>
		<form name="deleteProfile" action="DeleteProfile/<%=ProfileOf.getID()%>"  onsubmit="return confirm('Are you sure you want delete your profile?')">
			<input type="submit" value="Delete Profile" id="enter">
		</form>
		<%
			}
		%>
	</div>

	<div id=feed>
		<h2>
			<u>TWEETS</u>
		</h2>
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
		
			<a href="SingleTweet/<%=ts.getTweetID()%>"
			style="text-decoration: none;"><%=":   " + ts.getTweet()%></a> <br />
			
		<font size="2"><%=ts.getWhen()%></font> <br> <br>
				<%if(ownProfile) {%>
		<form class="button" name="deleteTweet"
			action="DeleteTweet/<%=ts.getTweetID()%>" method="get" onsubmit="return confirm('Are you sure you want to delete this tweet?')">		
			<input type="submit" value="Delete" id="delete">
		</form>
		<%}
			}
			}
		%>
	</div>
</body>
</html>