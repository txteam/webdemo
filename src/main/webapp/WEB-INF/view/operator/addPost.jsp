<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addPost</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	$("#organizationName").chooseOrganization({
		eventName : "chooseOrganizationForAddPost",
		contextPath : _contextPath,
		title : "请选择上级组织",
		width : 260,
		height : 400,
		handler : function(organization){
			$("#organizationName").val(organization.name);
			$("#organizationId").val(organization.id);
		}
	});
	//验证器
	$('#organizationForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
			$('#organizationForm').ajaxSubmit({
			    url:"${contextPath}/organization/addOrganization.action",
			    success: function(data) {
					if(data){
						parent.DialogUtils.tip("新增组织成功");
						parent.DialogUtils.closeDialogById("addOrganization");
					}
			    } 
			});
			return false;
	    }
	});
	
    //提交
	$("#addBtn").click(function(){
		$('#organizationForm').submit();
	});
});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="organizationForm" method="post" cssClass="form"
			modelAttribute="post">
			<table>
				<tr>
					<th class="narrow">名称:<span class="tRed">*</span></th>
					<td>
						<form:input path="name" cssClass="text"
							data-rule="名称:required;" 
							data-tip="必填"/>
					</td>
					<th class="narrow">所属组织</th>
					<td>
						<input id="organizationId" name="organization.id" type="hidden" readonly="readonly"/>
						<input id="organizationName" name="organization.name" class="selectInput" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">编号<span class="tRed">*</span></th>
					<td>
						<form:input path="code" cssClass="text"
							data-rule="编号:required;digits;remote[get:${contextPath }/organization/organizationCodeIsExist.action, code]" 
							data-tip="不能重复的数字"/>
					</td>
					<th class="narrow">&nbsp;</th>
					<td class="narrow">&nbsp;</td>
				</tr>
				<tr>
					<th>备注</th>
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