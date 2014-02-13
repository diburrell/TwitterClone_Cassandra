<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import="com.twitter.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tweets</title>
</head>
<body>

<% 
UserStore user = ((UserStore)request.getAttribute("UserDetails"));
String userWelcome = "Welcome "+ user.getName();

String userName = user.getName();
session.setAttribute( "name", userName );
%>
<a><%=userWelcome%></a><br/>

<form name="newTweet" action="WriteTweet" method="post">
<p>Enter Tweet: <input name="tweet" type="text">
<br> 

<input type="submit">
</form>


<form name="userFilter" action="TweetGetter" method="post">
<p>Filter: <input name="tweetSender" type="text">
<br> 

<input type="submit">
</form>


<% 
List<TweetStore> lTweet = (List<TweetStore>)request.getAttribute("Tweets");
if (lTweet==null){
 %>
	<p>No Tweets</p>
	<% 
}else{
%>
<% 
Iterator<TweetStore> iterator;
iterator = lTweet.iterator();     
while (iterator.hasNext()){
	TweetStore ts = (TweetStore)iterator.next();
	%>
	<a href="/ac32007examples/Tweet/<%=ts.getUser() %>" ><%ts.getUser();%><%=ts.getTweet() %></a><br/><%
}
}
%>
</body>
</html>