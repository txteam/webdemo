<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addMessageSendRecord</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//验证器
	$('#messageSendRecordForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	            text : '数据提交中，请等待....'
	    	});
			$('#messageSendRecordForm').ajaxSubmit({
			    url:"${contextPath }/messageSendRecord/updateMessageSendRecord.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("修改messageSendRecord成功");
						parent.DialogUtils.closeDialogById("updateMessageSendRecord");
					}else{
						parent.DialogUtils.tip("修改messageSendRecord失败");
					}
			    } 
			});
			return false;
	    }
	});
});
function submitFun(){
	$('#messageSendRecordForm').submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("updateMessageSendRecord");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="messageSendRecordForm" method="post" cssClass="form"
			modelAttribute="messageSendRecord">
			<form:hidden path="id"></form:hidden>
			<table class="common_table">
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">errorMessage:</th>
					<td width="80%">
						<form:input path="errorMessage" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">status:</th>
					<td width="80%">
						<form:input path="status" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">type:</th>
					<td width="80%">
						<form:input path="type" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">errorCode:</th>
					<td width="80%">
						<form:input path="errorCode" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">success:</th>
					<td width="80%">
						<form:input path="success" cssClass="text" />
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