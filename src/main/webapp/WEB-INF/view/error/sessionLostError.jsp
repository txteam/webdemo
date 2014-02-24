<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.lang.*;"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<%@include file="../includes/commonHead.jsp" %>
<html>
<head>
<script type="text/javascript">
var seconds = 3;
$(document).ready(function(){
	var $seconds = $("#seconds");
	$seconds.text(seconds);
	var intervalHandler = null;
	function autoReturnLoginPath(){
		if(seconds <= 0){
			if(intervalHandler != null){
				clearInterval(intervalHandler);
			}
			$.triggerge('returnLoginPage');
		}else{
			seconds--;
			$seconds.text(seconds);
		}
	}
	intervalHandler = setInterval(autoReturnLoginPath,1000);
});
</script>
<style type="text/css"></style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
<center>
	<img src="${pageContext.request.contextPath}/style/images/blue_face/bluefaces_35.png"/>
	<div id="errorMessage">登录超时，系统将在[<span id="seconds"></span>]秒后自动跳转到登录页面。</div>
</center>
</div>
</body>
</html>
