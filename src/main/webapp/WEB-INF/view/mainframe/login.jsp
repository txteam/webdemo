<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo系统</title>
		
<link rel="stylesheet" href="${contextPath }/css/easyui/easyui.css" type="text/css" media="screen">
<link rel="stylesheet" href="${contextPath }/css/mainframe/login.css" type="text/css" media="screen">
		
<script type="text/javascript" src="${contextPath }/js/jquery.min.js"></script>

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
		<div style="display: block; width: 488px; left: 433px; top: 139.5px; z-index: 9001;" class="panel window">
			<div class="panel-body panel-body-noheader panel-body-noborder window-body window-body-noheader" title="" style="overflow: hidden; width: 486px; height: 286px;">
				<div style="display: block; width: 486px;" class="panel">
					<div style="width: 486px; height: 249px;" class="panel-body panel-body-noheader panel-body-noborder dialog-content" title="">
						<form id="form-body" style="">
							<ul>
								<li>
									<input class="form-radio-other-input" name="type" value="1" checked="checked" type="radio">
									<label>普通 </label> &nbsp;&nbsp;&nbsp;
									<input class="form-radio-other-input" name="type" value="0" type="radio">
									<label>管理员</label>
								</li>
								<li>
									<label>账	号 </label>
									<input class="easyui-validatebox account form-textbox validatebox-text" name="account" value="member" required="required" type="text">
								</li>
								<li>
									<label>密	码 </label>
									<input class="easyui-validatebox  password form-textbox validatebox-text" name="password" value="123456" required="required" type="password">
								</li>
							</ul>
						</form>
						<div id="logo" style="">
							<h1>业务系统开发版</h1>
						</div>
					</div>
				</div>
				<div class="dialog-button">
					<a id="loginBtn" class="l-btn l-btn-disabled" href="javascript:void(0)"><span class="l-btn-left"><span class="l-btn-text">登陆</span></span></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</div><div style="display: block; left: 433px; top: 139.5px; z-index: 9000; width: 500px; height: 300px;" class="window-shadow"></div>
	</body>
</html>