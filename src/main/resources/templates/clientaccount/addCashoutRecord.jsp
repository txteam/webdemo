<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addCashoutRecord</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//验证器
	$('#cashoutRecordForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
			$('#cashoutRecordForm').ajaxSubmit({
			    url:"${contextPath }/cashoutRecord/addCashoutRecord.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增cashoutRecord成功");
						parent.DialogUtils.closeDialogById("addCashoutRecord");
					}else{
						parent.DialogUtils.tip("新增cashoutRecord失败");
					}
			    } 
			});
			return false;
	    }
	});
});
function submitFun(){
	$('#cashoutRecordForm').submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("addCashoutRecord");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="cashoutRecordForm" method="post" cssClass="form"
			modelAttribute="cashoutRecord">
			<form:hidden path="id"/>
			<table class="common_table">
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">channelInfo:</th>
					<td width="80%">
						<form:input path="channelInfo" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">targetInfo:</th>
					<td width="80%">
						<form:input path="targetInfo" cssClass="text" />
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
					<th class="narrow" width="20%">status:</th>
					<td width="80%">
						<form:input path="status" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">commissionFeeAmount:</th>
					<td width="80%">
						<form:input path="commissionFeeAmount" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">accountAfterSum:</th>
					<td width="80%">
						<form:input path="accountAfterSum" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">serialNumber:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="serialNumber" cssClass="text" 
							data-rule="serialNumber:required;;remote[${contextPath}/cashoutRecord/validateSerialNumberIsExist.action, serialNumber, id]" 
						/>
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">sum:</th>
					<td width="80%">
						<form:input path="sum" cssClass="text" />
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
					<th class="narrow" width="20%">clientAccountItemId:</th>
					<td width="80%">
						<form:input path="clientAccountItemId" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">clientId:</th>
					<td width="80%">
						<form:input path="clientId" cssClass="text" />
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
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">sourceInfo:</th>
					<td width="80%">
						<form:input path="sourceInfo" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">amount:</th>
					<td width="80%">
						<form:input path="amount" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">accountBeforeSum:</th>
					<td width="80%">
						<form:input path="accountBeforeSum" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">clientAccountId:</th>
					<td width="80%">
						<form:input path="clientAccountId" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">commissionFee:</th>
					<td width="80%">
						<form:input path="commissionFee" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">accountItemBeforeAmount:</th>
					<td width="80%">
						<form:input path="accountItemBeforeAmount" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">tradingRecordId:</th>
					<td width="80%">
						<form:input path="tradingRecordId" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">accountItemAfterAmount:</th>
					<td width="80%">
						<form:input path="accountItemAfterAmount" cssClass="text" />
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