<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.lang.*;"
	pageEncoding="UTF-8"%>
<!DOCTYPE div >
<%@include file="../includes/commonHead.jsp" %>
<html>
	<head>
		<script type="text/javascript">
			$(document).ready(function(){
				$.triggerge('returnHomePage');//当session失效时，让页面跳转到系统首页
			});
		</script>
		<style type="text/css"></style>
	</head>
</html>
