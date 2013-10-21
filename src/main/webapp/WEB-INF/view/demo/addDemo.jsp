<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	 //alert($("button,:input[type=button],:input[type=submit]").size());
	 //$("form").omForm();
});
</script>
</head>
<body>
<form id="demo" action="/demo/addDemo1" method="post">

<div id="bodyDiv" class="form-table">
<div class="ui-widget-shadow">必填项</div>
<div>
	<table>
		<tr>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="password" name="password"/></td>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>email</td>
			<td><input type="text" name="email"/></td>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
		</tr>
		<tr>
			<td>createDate</td>
			<td><input type="text" name="createDate"/></td>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
		</tr>
	</table>
</div>
<div class="ui-widget-overlay">选填项</div>
<div>
	<table>
		<tr>
			<td>username${contextPath }</td>
			<td><input type="text" name="username"/></td>
			<td>password</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="password" name="password"/></td>
			<td>password</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td>email</td>
			<td><input type="text" name="email"/></td>
			<td>password</td>
			<td><input type="password" name="password"/></td>
		</tr>
		<tr>
			<td>createDate</td>
			<td><input type="text" name="createDate"/></td>
			<td>password</td>
			<td><input type="password" name="password"/></td>
		</tr>
	</table>
</div>
<div>其他</div>
<div>
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
</div>

	<input type="button" id="submitButton" value="提交">
	<input type="button" id="test" value="提交">
</div>

</form>
</body>
</html>