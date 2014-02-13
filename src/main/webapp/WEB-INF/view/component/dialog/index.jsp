<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>grid demo index</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	
});
</script>
<style type="text/css">
html,body{
	overflow: hidden;
	height:100%;
	width:100%;
}
main-body{
	height:100%;
	width:100%;
}
</style>
</head>
<body>
<div class="header">component>grid</div>
<div class="main-body">
	<ul>
		<li><a href="#mainToolTabs-1">基本功能</a></li>
		<li><a href="#mainToolTabs-2">行事历</a></li>
	</ul>
	<div id="mainToolTabs-1">
		<p>mainToolTabs-1111</p>
	</div>
	<div class="component-api">
		<ul>
			<li><a href="#api-preview">预览</a></li>
			<li><a href="#api-option">属性</a></li>
			<li><a href="#api-method">方法</a></li>
			<li><a href="#api-event">事件</a></li>
		</ul>
		<div id="api-preview">
			<iframe src="${contextPath }/component/grid/preview"></iframe>
		</div>
		<div id="api-option">
			<iframe src="${contextPath }/component/grid/option"></iframe>
		</div>
		<div id="api-method">
			<iframe src="${contextPath }/component/grid/method"></iframe>
		</div>
		<div id="api-event">
			<iframe src="${contextPath }/component/grid/event"></iframe>
		</div>
	</div>
</div>
</body>
</html>