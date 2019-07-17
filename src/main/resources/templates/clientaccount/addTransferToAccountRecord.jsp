<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addTransferToAccountRecord</title>
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
			    url:"${contextPath }/transferToAccountRecord/add.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增transferToAccountRecord成功.");
						parent.DialogUtils.closeDialogById("addTransferToAccountRecord");
					}else{
						DialogUtils.alert("错误提示","新增transferToAccountRecord失败.","error");
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
	parent.DialogUtils.closeDialogById("addTransferToAccountRecord");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="entityForm" method="post" cssClass="form"
			modelAttribute="transferToAccountRecord">
			<form:hidden path="id"/>
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
					<th class="narrow" width="20%">transferToTradingRecord.id</th>
					<td width="80%">
						//TODO:修改其显示逻辑
						<form:input path="transferToTradingRecord" cssClass="text"/>
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
					<th class="narrow" width="20%">postDate:</th>
					<td width="80%">
						<form:input path="postDate" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">approveTradingRecord.id</th>
					<td width="80%">
						//TODO:修改其显示逻辑
						<form:input path="approveTradingRecord" cssClass="text"/>
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
					<th class="narrow" width="20%">lastTransferDate:</th>
					<td width="80%">
						<form:input path="lastTransferDate" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">lastTransferAmount:</th>
					<td width="80%">
						<form:input path="lastTransferAmount" cssClass="text" />
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
					<th class="narrow" width="20%">afterSum:</th>
					<td width="80%">
						<form:input path="afterSum" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">approveDate:</th>
					<td width="80%">
						<form:input path="approveDate" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">postTradingRecord.id</th>
					<td width="80%">
						//TODO:修改其显示逻辑
						<form:input path="postTradingRecord" cssClass="text"/>
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
					<th class="narrow" width="20%">transferToClientId:</th>
					<td width="80%">
						<form:input path="transferToClientId" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">poundageSum:</th>
					<td width="80%">
						<form:input path="poundageSum" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">overHisMaxAmount:</th>
					<td width="80%">
						<form:input path="overHisMaxAmount" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">applyTradingRecord.id</th>
					<td width="80%">
						//TODO:修改其显示逻辑
						<form:input path="applyTradingRecord" cssClass="text"/>
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
					<th class="narrow" width="20%">beforeSum:</th>
					<td width="80%">
						<form:input path="beforeSum" cssClass="text" />
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
					<th class="narrow" width="20%">applyDate:</th>
					<td width="80%">
						<form:input path="applyDate" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">factSum:</th>
					<td width="80%">
						<form:input path="factSum" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">transferToClientAccountId:</th>
					<td width="80%">
						<form:input path="transferToClientAccountId" cssClass="text" />
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">firstTransfer:</th>
					<td width="80%">
						<form:input path="firstTransfer" cssClass="text" />
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