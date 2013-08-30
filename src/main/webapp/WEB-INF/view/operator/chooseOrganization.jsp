<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryOrganizationList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var tree = null;
$(document).ready(function() {
	tree = $('#organizationTree').tree({
		url : '${contextPath}/organization/queryOrganizationList.action',
		idFiled : 'id',
		parentField : 'parentId',
		textFiled : 'name',
		fit : true,
		fitColumns : true,
		border : false,
		onClick : function(node){
			$.triggerge("choose_organization_" + "${eventName}",[node['attributes']]);
		}
	});
	$("#confirmBtn").click(function(){
		parent.DialogUtils.closeDialogById("component_dialog_chooseOrganization");
	});
});

</script>
</head>
<body class="body">
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" style="overflow-y:auto;"
			class="form">
			<ul id="organizationTree"></ul>
					<form id="form" method="post" class="form">
			<!-- 
			<table style="position: absolute;bottom: 5px">
				<tr>
					<td class="rightOperRow" colspan="4">
						<a id="confirmBtn" href="#" class="easyui-linkbutton">确认</a>&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			 -->
			 <div class="dialog-button" style="position: absolute;bottom: 5px;width: 100%">
			 	<a id="confirmBtn" href="#" class="easyui-linkbutton">确认</a>&nbsp;&nbsp;&nbsp;
			 </div>
		</div>
	</div>
</body>