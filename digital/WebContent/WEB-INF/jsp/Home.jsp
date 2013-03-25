<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
pageContext.setAttribute("root",request.getContextPath());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page</title>
<script type="text/javascript" src="${root}/static/js/jquery-1.4.2.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	$("#test").mouseover(function(){
		$(".test1").fadeOut(1000);
		
	});
	$("#test").mouseleave(function(){
		$(".test1").show(1000);
	});
	
	$(".test1").mouseover(function(){
		dialog();
	});
	
	
});
</script>
</head>
<body>
<center>
<strong>Admin Login Area</strong>
<form action="${root}/submit/login" method="post">

<fieldset style="border:none;">
UserName:

<input type="text" name="uname" style="background-color: lightyellow;"/>
</fieldset>
<fieldset style="border:none;">
Password:

<input type="password" name="password" style="background-color: lightyellow;"/>

</fieldset>
<fieldset style="border:none;">
<input type="button" value="Login" style="background-color: gray;"/>
</fieldset>

</form>
<div id="test">


</div><br/><br/>






</center>
</body>
</html>