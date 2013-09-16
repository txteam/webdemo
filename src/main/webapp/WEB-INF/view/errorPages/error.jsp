<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.lang.*;"
	pageEncoding="UTF-8"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../includes/commonHead.jsp" %>
<html>
	<head>
		<script type="text/javascript">
			$(document).ready(function(){
				$.triggerge('hideTopDiv');//数据加载完成后,隐藏遮罩层
			});
		</script>
		<style type="text/css"></style>
	</head>
	<body style="border: 0px;">
		<h1 style="color: red;font-size: 12px">出错了</h1>
		<hr/>
		<% Exception ex = (Exception)request.getAttribute("exception"); %>
		<div style="color: red;font-size: 12px">错误原因: <%=ex.getLocalizedMessage()%></div>
	</body>
</html>
