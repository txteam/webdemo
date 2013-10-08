<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryLoginLog</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
	
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
		<form id="searchForm" class="form">
			<table class="table table-hover table-condensed" style="display: none;">
				<tr>
					<th>开始时间</th>
					<td>
						<input name="minCreateDate" readonly="readonly"
							placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
				</tr>
				<tr>
					<th>结束时间</th>
					<td>
						<input name="maxCreateDate" readonly="readonly"
							placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="overflow: hidden;height:fit;width:auto">
		<table class="serviceLog"></table>
    </div> 
</div> 

<div id="toolbar_${authType.authType}" style="display: none;"> 
	<a onclick="" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>
</body>