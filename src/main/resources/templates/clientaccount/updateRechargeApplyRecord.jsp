<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addRechargeApplyRecord</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//验证器
	$('#rechargeApplyRecordForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	            text : '数据提交中，请等待....'
	    	});
			$('#rechargeApplyRecordForm').ajaxSubmit({
			    url:"${contextPath }/rechargeApplyRecord/updateRechargeApplyRecord.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("修改rechargeApplyRecord成功");
						parent.DialogUtils.closeDialogById("updateRechargeApplyRecord");
					}else{
						parent.DialogUtils.tip("修改rechargeApplyRecord失败");
					}
			    } 
			});
			return false;
	    }
	});
});
function submitFun(){
	$('#rechargeApplyRecordForm').submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("updateRechargeApplyRecord");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="rechargeApplyRecordForm" method="post" cssClass="form"
			modelAttribute="rechargeApplyRecord">
			<form:hidden path="id"></form:hidden>
			<table class="common_table">
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">approveRemark:</th>
					<td width="80%">
						<form:input path="approveRemark" cssClass="text" />
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
					<th class="narrow" width="20%">handleTradingRecordId:</th>
					<td width="80%">
						<form:input path="handleTradingRecordId" cssClass="text" />
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