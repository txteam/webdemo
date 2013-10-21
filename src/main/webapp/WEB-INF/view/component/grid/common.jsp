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
	
	$("#menus").menu();
	
	$("#main").txiframe();
});
</script>
<style type="text/css">
html,body{
	overflow: hidden;
	height:100%;
	width:100%;
}
.main-body{
	overflow:hidden;
	height:100%;
	width:100%;
}
.main-body .left-menu{
	margin-left: 3px;
	margin-top: 3px;
	float: left;
	width: 150px;
}
.main-body .left-menu .menus{
	float: left;
	width: 146px;
}
.main-body .main{
	float: left;
	height: 100%;
	width: 100%;
	position: relative;
}
</style>
</head>
<body>
<div class="main-body">
	<div class="left-menu">
		<ul id="menus" class="menus">
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">基本功能</a></li>
			<li><a href="#mainTabs-2" target="component_mainframe">隔行斑纹</a></li>
			<li><a href="#mainTabs-3" target="component_mainframe">列渲染</a></li>
			<li><a href="#mainTabs-4" target="component_mainframe">显示多选框</a></li>
			<li><a href="#mainTabs-5" target="component_mainframe">双击事件</a></li>
			<li><a href="#mainTabs-6" target="component_mainframe">排序功能</a></li>
			<li><a href="#mainTabs-7">展开详情</a></li>
			<li><a href="#mainTabs-8">基本行编辑</a></li>
			<li><a href="#mainTabs-9">单击触发行编辑</a></li>
			<li><a href="#mainTabs-10">复杂场景</a></li>
			<li><a href="#mainTabs-11">行编辑增删改</a></li>
			<li></li>
			<li><a href="#mainTabs-11">属性</a></li>
			<li><a href="#mainTabs-11">方法</a></li>
			<li><a href="#mainTabs-11">事件</a></li>
		</ul>
	</div>
	<div class="main">
		<iframe id="component_mainframe" name="component_mainframe" 
			height="100%" width="100%" scrolling="no" frameborder="0" 
			src=""></iframe>
	</div>
</div>
</body>
</html>