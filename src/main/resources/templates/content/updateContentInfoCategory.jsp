<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addContentInfoCategory</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//验证器
	$('#entityForm').validator({
	    valid: function(){
	        DialogUtils.progress({
	            text : '数据提交中，请等待....'
	    	});
			$('#entityForm').ajaxSubmit({
			    url:"${contextPath }/contentInfoCategory/update.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("修改内容类型成功");
						parent.DialogUtils.closeDialogById("updateContentInfoCategory");
					}else{
						parent.DialogUtils.alert("错误提示","修改内容类型失败","error");
					}
			    } 
			});
			return false;
	    }
	});
});
function submitFun(){
	$('#entityForm').submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("updateContentInfoCategory");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="entityForm" method="post" cssClass="form"
			modelAttribute="contentInfoCategory">
			<form:hidden path="id"></form:hidden>
			<table class="common_table">
				<tr>
					<th class="narrow" width="20%">编码:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="code" cssClass="text" readonly="true"
							data-rule="编码:required;remote[${contextPath}/contentInfoCategory/validateCodeIsExist.action, code, id]" 
						/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">名称:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="name" cssClass="text" 
							data-rule="名称:required;"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">类型:<span class="tRed">*</span></th>
					<td width="80%">
						<form:select path="type.code" data-rule="类型:required;" disabled="true">
							<form:option value="">--- 无 ---</form:option>
							<form:options items="${contentInfoTypes }" itemLabel="name" itemValue="code"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">上级类型:<span class="tRed">*</span></th>
					<td width="80%">
						<form:select path="parentId" disabled="true">
							<form:option value="">--- 无 ---</form:option>
							<form:options items="${contentInfoCategories }" itemLabel="code" itemValue="id"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">排序值:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="orderIndex" cssClass="text" 
							class="easyui-numberbox" data-options="min:0,precision:1"
							data-rule="排序值:required;digits;"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">备注:</th>
					<td width="80%">
						<form:textarea path="remark" cssClass="textarea" rows="3" cols="30"/>
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