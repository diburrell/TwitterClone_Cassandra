<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="StyleSheet" TYPE="text/css" HREF="css/Details.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INTERNAL ERROR!</title>
</head>
<body>
<div id="content">
<h1>INTERNAL SERVER ERROR!</h1>
<br>
<p>PLEASE TRY AGAIN LATER!
<%		String referer = request.getHeader("Referer");
%>
		<form name="goBack" action=<%=referer%>>
			<input type="submit" value="Go back!" id="enter">
		</form>

</div>
</body>
</html>