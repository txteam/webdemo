<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addRole</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//验证器
	$('#roleForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
			$('#roleForm').ajaxSubmit({
			    url:"${contextPath }/role/addRole.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增role成功");
						parent.DialogUtils.closeDialogById("addRole");
					}else{
						parent.DialogUtils.tip("新增role失败");
					}
			    } 
			});
			return false;
	    }
	});
});
function submitFun(){
	$('#roleForm').submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("addRole");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="roleForm" method="post" cssClass="form"
			modelAttribute="role">
			<form:hidden path="id"/>
			<table class="common_table">
				<tr>
					<th class="narrow" width="30%">所属虚中心:<span class="tRed">*</span></th>
					<td>
						<form:select path="vcid" cssClass="select" data-rule="所属业务中心:required;" >
							<form:option value="">-- 请选择 --</form:option>
							<form:options items="${virtualCenterList}" itemLabel="name" itemValue="id" />
						</form:select>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="30%">角色类型:<span class="tRed">*</span></th>
					<td>
						<form:select path="roleType" cssClass="select" data-rule="角色类型:required;" >
							<form:option value="">-- 请选择 --</form:option>
							<form:options items="${roleTypeList}" itemLabel="name" itemValue="key" />
						</form:select>
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="30%">编码:<span class="tRed">*</span></th>
					<td width="70%">
						<form:input path="code" cssClass="text" 
							data-rule="编码:required;;remote[${contextPath}/role/validateVcidAndCodeIsExist.action, vcid, code, id]" 
						/>
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="30%">名称:<span class="tRed">*</span></th>
					<td width="70%">
						<form:input path="name" cssClass="text" 
							data-rule="名称:required;;remote[${contextPath}/role/validateVcidAndNameIsExist.action, vcid, name, id]" 
						/>
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="30%">备注:</th>
					<td width="70%">
						<form:input path="remark" cssClass="text" />
					</td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px">
						<a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>  
						<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</body>