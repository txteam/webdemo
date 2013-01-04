<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>best dialog demo</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	$("#datetimepicker").datetimepicker();
	
	$('#timepicker').timepicker();
	
	$('#formatTime').datetimepicker({
		showSecond: true,
		dateFormat: 'yy-MM-dd',
		timeFormat: "HH:mm:ss"
	});
});
</script>
</head>
<body>
<div class="header">最佳实践>timepicker</div>
<br/>
simple datetimepicker：<input id="datetimepicker" type="text"/>
<br/>
<br/>
Add only a timepicker: <input id="timepicker" type="text"/>
<br/>
<br/>
Format the time: <input id="formatTime" type="text"/>
<br/>
<br/>
</body>
</html>