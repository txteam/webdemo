<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addVirtualCenter.jsp</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	$("#parentName").chooseVirtualCenter({
		eventName : "chooseVirtualCenterForAddVirtualCenter",
		contextPath : _contextPath,
		title : "请选择上级虚中心",  
		width : 260,
		height : 400,
		handler : function(vc){
			if(vc != null){
				$("#parentName").val(vc.name);
				$("#parentId").val(vc.id);
			}
		},
	    clearHandler: function(){
	    	$("#parentName").val("");
			$("#parentId").val("");
	    }
	});
	
	//验证器
	$('#virtualCenterForm').validator({
	    valid: function(){
	    	DialogUtils.progress({
		        text : '数据提交中，请等待....'
			});
	        //表单验证通过，提交表单到服务器
			$('#virtualCenterForm').ajaxSubmit({
			    url:"${contextPath}/virtualCenter/addVirtualCenter.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						DialogUtils.tip("新增虚中心成功");
						parent.DialogUtils.closeDialogById("addVirtualCenter");
					}
			    }
			});
			return false;
	    }
	});
	
    //提交
	$("#addBtn").click(function(){
		$('#virtualCenterForm').submit();
	});
});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="virtualCenterForm" method="post" cssClass="form"
			modelAttribute="virtualCenter">
			<table class="common_table">
				<tr>
					<th class="narrow">名称:<span class="tRed">*</span></th>
					<td>
						<form:input path="name" cssClass="text"
							data-rule="名称:required;length[2~16];remote[${contextPath }/virtualCenter/virtualCenterNameIsExist.action, name]" 
							data-tip="必填"/>
					</td>
					<td width="30%" colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<th>上级虚中心：</th>
					<td>
						<input id="parentId" name="parentId" type="hidden" readonly="readonly" value="${parentVirtualCenter.id }"/>
						<input id="parentName" name="parentName" class="selectInput" readonly="readonly" value="${parentVirtualCenter.name }"/>
					</td>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<th>备注：</th>
					<td colspan="3">
						<textarea name="remark" rows="" cols="" class="longText"></textarea>
					</td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px">
						<a id="addBtn" href="#" class="easyui-linkbutton">提交</a>  	
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</body>