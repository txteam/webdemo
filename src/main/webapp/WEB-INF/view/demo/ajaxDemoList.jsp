<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>best dialog demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	
	//查询
	$("#ajaxQueryBtn").click(function(){
		$('#demoForm').attr("action","${contextPath}/bestDemo/queryDemoList");
		$('#demoForm').submit();
	});
	
	//deal query result
	function processQuerySuccess(data){
		alert("processQuerySuccess");
	};
    
	$("#demoList").txGrid({
		type: 'simple',
		caption: 'demo列表',
	   	multiselect: true,
	   	multiboxonly: true,
		height: $("#listData").find("tr").size() < 10 ? 'auto' : 255
	});
});
</script>
<style type="text/css">
.select{
	height: 14px;
	padding-top: 0px;
	padding-buttom: 0px;
}
</style>
</head>
<body>
<form:form method="post" id="demoForm" modelAttribute="demoForm">

<!-- query condition -->
<div class="ui-widget-content form-table">
	<div class="grid-12-12">
		<div class="grid-1-12">姓名:</div>
		<div class="grid-3-12"><input name="name" type="text" value='<c:out value="${name }"></c:out>'/></div>
		<div class="grid-1-12">数字:</div>
		<div class="grid-3-12"><input name="number" type="text" value='<c:out value="${number }"></c:out>'/></div>
		<div class="grid-4-12">
			<button type="button" id="queryBtn">查询</button>
		</div>
	</div>
</div>
<br/>

<!-- grid -->
<div class="list-table">
	<table id="demoList">
	</table>
	<div class="operRow">
		<button id="prepare" type="button">prepare</button>
		&nbsp;&nbsp;
		<button id="add" type="button">增加</button>
		&nbsp;&nbsp;
		<button id="add" type="button">删除</button>
	</div>
</div>

</form:form>
</body>
</html>