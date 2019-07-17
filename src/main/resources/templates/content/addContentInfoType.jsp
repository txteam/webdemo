<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addContentInfoType</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//验证器
	$('#entityForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
			$('#entityForm').ajaxSubmit({
			    url:"${contextPath }/contentInfoType/add.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增contentInfoType成功.");
						parent.DialogUtils.closeDialogById("addContentInfoType");
					}else{
						DialogUtils.alert("错误提示","新增contentInfoType失败.","error");
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
	parent.DialogUtils.closeDialogById("addContentInfoType");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="entityForm" method="post" cssClass="form"
			modelAttribute="contentInfoType">
			<form:hidden path="id"/>
			<table class="common_table">
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">createOperatorId:</th>
					<td width="80%">
						<form:input path="createOperatorId" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">remark:</th>
					<td width="80%">
						<form:input path="remark" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">name:</th>
					<td width="80%">
						<form:input path="name" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">modifyAble:</th>
					<td width="80%">
						<form:input path="modifyAble" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">code:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="code" cssClass="text" 
							data-rule="code:required;;remote[${contextPath}/contentInfoType/validateCodeIsExist.action, code, id]" 
						/>
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">createDate:</th>
					<td width="80%">
						<form:input path="createDate" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">lastUpdateOperatorId:</th>
					<td width="80%">
						<form:input path="lastUpdateOperatorId" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">lastUpdateDate:</th>
					<td width="80%">
						<form:input path="lastUpdateDate" cssClass="text" />
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