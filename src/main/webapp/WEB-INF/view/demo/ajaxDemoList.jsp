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
	
	//利用js实现自动长度截取功能
    if($(".subStr").size() > 0){
        $.each($(".subStr"), function(index, dom) {
        	var $dom = $(this);
            var maxLength = $dom.attr("maxLength") * 1 > 0 ? $dom.attr("maxLength") * 1 : 10;
            var suffix = $dom.attr("suffix") ? $dom.attr("suffix") : "";
            if($dom.text().length > maxLength){
            	$dom.attr("title",$dom.text());
            	$dom.text($dom.text().substr(0,maxLength) + suffix);
            }
        });
    }
    
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
<div class="form-table">
<table>
	<colgroup>
		<col/>
		<col/>
		<col/>
		<col/>
		<col/>
		<col/>
	</colgroup>
	<tbody>
	<tr>
		<th>姓名:</th>
		<td><input name="name" type="text" value='<c:out value="${name }"></c:out>'/></td>
		<th>数字:</th>
		<td><input name="number" type="text" value='<c:out value="${number }"></c:out>'/></td>
		<th>选择:<em class="formee-req">*</em></th>
		<td>
			<select name="select">
				<optgroup label="---请选择---"> 
					<option>---请选择---</option>
					<option>---请选择---</option>
					<option>---请选择---</option>
					<option>---请选择---</option>
				</optgroup>
			</select>
		</td>
	</tr>
	</tbody>
	<tr>
		<th>最小创建时间:</th>
		<td>
			<div id="calendar"></div> 
			<input id="startCreateDate" name="startCreateDate" type="text"
				value='<c:out value="${startCreateDate }"></c:out>'/>
		</td>
		<th>最大创建时间:</th>
		<td><input name="endCreateDate" type="text"
				value='<c:out value="${endCreateDate }"></c:out>'/></td>
		<th>&nbsp;</th>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="6" class="button operRow">
			<button type="button" id="queryBtn">查询</button>
		</td>
	</tr>
</table>
</div>
<br/>

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