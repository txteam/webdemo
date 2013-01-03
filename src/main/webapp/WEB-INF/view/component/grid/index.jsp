<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
.main-body{
	float:right;
	width:100%;
	height:100%;
	margin-left: -300px;
}
.main-body .main{
	margin-left: 180px;
}
.main-body .left-menu{
	width:180px; 
	float:left;
}
/*
.main-body .left-menu .menus{
	float: left;
	width: 146px;
}
*/
</style>
</head>
<body>
<div class="main-body">
	<div class="main">
		<iframe id="component_mainframe" name="component_mainframe" 
			scrolling="no" frameborder="0" 
			src=""></iframe>
	</div>
	<div class="left-menu">
		<ul id="menus" class="menus">
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">基本功能</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">隔行斑纹</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">列渲染</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">显示多选框</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">双击事件</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">排序功能</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">展开详情</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">基本行编辑</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">单击触发行编辑</a></li>
			<li><a target="component_mainframe"
				href="${contextPath }/component/grid/common">复杂场景</a></li>
			<li></li>
			<li><a href="#mainTabs-11">属性</a></li>
			<li><a href="#mainTabs-11">方法</a></li>
			<li><a href="#mainTabs-11">事件</a></li>
		</ul>
	</div>
</div>
</body>
</html>