<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addBaseClientInfo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	//验证器
	$('#form').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
			$('#form').ajaxSubmit({
			    url:"${contextPath }/cashoutRecord/cashoutApplyFail.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
                    parent.DialogUtils.closeDialogById("cashoutApplyFail");
					if(data){
						$(".disableButton").linkbutton('enable');
						parent.DialogUtils.tip("处理成功.");
					}else{
						parent.DialogUtils.tip("处理失败。");
					}
			    } 
			});
			return false;
	    }
	});
	
});
function submitFun(){
	$("#form").submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("cashoutApplyFail");
}
</script>
</head>
<body>
<div class="easyui-layout" id="container"   data-options="fit:true,border:false" style="overflow-y: auto ">
	<form method="post" class="form" id="form" name="form">
		<table class="common_table">
			<tr>
				<th class="narrow"  width="30%">用户名(交易账号):</th>
				<td width="70%">
					<input type="hidden" name="serialNumber" id="serialNumber" class="text"
						value="${cashoutRecord.serialNumber }" readonly="readonly"/>
					<input type="hidden" id="cashoutRecordId" name="cashoutRecordId"
						value="${cashoutRecord.id }"/>
					<input name="clientUserName" id="clientLoginName" class="text"
						value="${cashoutRecord.clientLoginName }" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th class="narrow"  width="30%">客户姓名:</th>
				<td width="70%">
					<input name="clientUserName" id="clientUserName" class="text"
						value="${cashoutRecord.clientUserName }" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th class="narrow"  width="30%">提现金额:</th>
				<td width="70%">
					<input name="amount" id="amount" class="text"
						value="${cashoutRecord.sum }" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th class="narrow"  width="30%">备注:</th>
				<td width="70%">
					<textarea name="remark" id="remark" class="text-area"></textarea>
				</td>
			</tr>
			<tr>
				<td class="rightOperRow" colspan="4" style="padding-right: 50px">
					<a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>  
					<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
				</td>
			</tr>
		</table>
	</form>
</div>
</body>