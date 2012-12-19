<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {

	$("#testDemo").wijgrid({ 
        allowSorting: true, 
        allowPaging: true
    }); 
	
	$("#queryCondition").wijgrid({ 
		showRowHeader: false
    }); 
});
</script>
</head>
<body>
<div class="header">常用菜单>demo列表</div>
<br/>
<table id="queryCondition">
	<tr>
		<td>查询条件一</td>
		<td><input id="" value="" type="text"/></td>
		<td>查询条件二</td>
		<td><input id="" value="" type="text"/></td>
	</tr>
</table>
<br/>

<table id="demo"> 
</table>

<div class="header">demo列表</div>
<table id="testDemo">
  <thead>
    <th>column0</th><th>columnN</th>
  </thead>
  <tbody>
    <tr>
      <td>cell00</td><td>cell0N</td>
    </tr>
    <tr>
      <td>cellN0</td><td>cellNN</td>
    </tr>
  </tbody>
</table>
</body>
</html>