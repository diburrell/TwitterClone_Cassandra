<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Users</title>
</head>
<body>
<h1><u>People Tweeting Around</u></h1>
	<%
		List<String> lUser = (List<String>) request.getAttribute("Users");
		if (lUser == null) {
	%>
	<p>No Users found</p>
	<%
		} else {
	%>


	<%
	System.out.println("Users found!!!!!!");
		Iterator<String> iterator;

			iterator = lUser.iterator();
			while (iterator.hasNext()) {
				String user = (String)iterator.next();
	%>
	<a href="ProfileGetter/<%=user%>"><font size="4"><STRONG><%=user%></STRONG></font><br /> <%
 	}
 	}
 %>
</body>
</html>