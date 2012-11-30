<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	
});
</script>
</head>
<body>
<div id="bodyDiv">
	<table>
		<tr>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td>email</td>
			<td><input type="text" name="email"/></td>
		</tr>
		<tr>
			<td>createDate</td>
			<td><input type="text" name="createDate"/></td>
		</tr>
	</table>
	<input type="button" id="submitButton" value="提交">
	<input type="button" id="test" value="提交">
</div>
</body>
</html>