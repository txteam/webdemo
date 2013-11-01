<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>chooseOrganization</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var tree = null;
$(document).ready(function() {
	tree = $('#organizationTree').tree({
		url : '${contextPath}/virtualCenter/queryVirtualCenterListByAuth.action',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_user';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$.triggerge("choose_virtualCenter_" + "${eventName}",[node['attributes']]);
		}
	});
});

</script>
</head>
<body class="body">
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" style="overflow-y:auto;"
			class="form">
			<ul id="organizationTree"></ul>
		</div>
	</div>
</body>