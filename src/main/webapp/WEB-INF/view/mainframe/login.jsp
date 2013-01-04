<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainframe</title>
<%@include file="../includes/commonHead.jsp"%>

<link  rel="stylesheet" type="text/css" href="${contextPath }/css/login.css" />
<script type="text/javascript" src="${contextPath }/js/mainframe/login.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#skinSwitcher").chemeswitcher({
		contextPath : "${contextPath}",
		themeCookieName : "tx_theme"
	});
	$("#loginBtn").click(function() {
		$("#loginForm").submit();
	});
});
</script>
<style type="text/css">

</style>
<body>
<form id="loginForm" method="post" action="${contextPath}/mainframe/login">
	<div class="login-container">
		<div class="login-bar">
			<div class="login-div ui-widget-header">
        		<div class="login-info">
        			欢迎来到本系统,谢谢使用,使用过程中遇到任何疑问,欢迎拨打xxx.
        		</div>
        		<div id="skinSwitcher" class="skin-switcher"></div>
        		<div class="login">
				<table class="ui-state-default login-table">
					<colgroup>
						<col width="30%"/>
						<col width="40%"/>
						<col width="30%"/>
					</colgroup>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td align="right">登陆名：</td>
						<td><input name="loginName" type="text"
							value="<c:out value='admin'></c:out>" /></td>
						<td align="right">&nbsp;</td>
					</tr>
					<tr>
						<td align="right">密码：</td>
						<td><input name="password" type="password"
							value="<c:out value='admin'></c:out>" /></td>
						<td align="right">&nbsp;</td>
					</tr>

					<tr>
						<td align="right">布局：</td>
						<td>
							<lable></lable>
							<input name="layout" type="radio" value="mainframe1" checked="checked"/><lable>1</lable>
							<input name="layout" type="radio" value="mainframe" /><lable>2</lable>
						<td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td align="right"><input id="loginBtn" type="button"
							value="登陆" /></td>
						<td align="right">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
				</table>
				</div>
			</div>
			<div class="copy-right">&copy; 版权所有 版本号 V1.00.x</div>
		</div>
	</div>
</form>
</body>
</html>