<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退回</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript" >
var $tkBankAccountSpans = null;
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	$tkBankAccountSpans = $("span[name=tkBank]");
	var $revokeType = $("#revokeType");
	//验证器
	$('#revokeForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
	        if($revokeType.val() == 'REVOKE'){
	        	$('#revokeForm').ajaxSubmit({
				    url:"${contextPath}/loanAccountRevoke/revoke.action",
				    success: function(data) {
				    	DialogUtils.progress('close');
						if(data){
							parent.DialogUtils.tip("撤销成功.");
							parent.DialogUtils.closeDialogById("revoke");
						}else{
							parent.DialogUtils.tip("撤销失败.");
						}
				    } 
				});
			}else if($revokeType.val() == 'REFUND'){
				$('#revokeForm').ajaxSubmit({
				    url:"${contextPath}/loanAccountRevoke/refund.action",
				    success: function(data) {
				    	DialogUtils.progress('close');
						if(data){
							parent.DialogUtils.tip("退款成功.");
							parent.DialogUtils.closeDialogById("revoke");
						}else{
							parent.DialogUtils.tip("退款失败.");
						}
				    } 
				});
			}else if($revokeType.val() == 'REVOKE_TO_WA'){
				$('#revokeForm').ajaxSubmit({
				    url:"${contextPath}/loanAccountRevoke/revokeToWaitAccount.action",
				    success: function(data) {
				    	DialogUtils.progress('close');
						if(data){
							parent.DialogUtils.tip("撤销至待入账成功.");
							parent.DialogUtils.closeDialogById("revoke");
						}else{
							parent.DialogUtils.tip("撤销至待入账失败.");
						}
				    } 
				});
			}else{
				
			}
	    }
	});
	//退款代码选择
	$("#revokeTradingRecordType").change(function(){
		var _this = $(this);
		var loanAccountId = $("#loanAccountId").val();
		var revokeAbleTradingRecordIds = $("#revokeAbleTradingRecordIds").val();
		window.location.href = "${contextPath}/ZS/revoke/toRevoke.action?loanAccountId=" + loanAccountId + "&revokeTradingRecordId=" + _this.val() + "&revokeAbleTradingRecordIds=" + revokeAbleTradingRecordIds;
	});
	//退回类型选择
	initTKBankAccount();
	$("#revokeType").change(function(){
		initTKBankAccount();
	});
});
function initTKBankAccount(){
	if($tkBankAccountSpans.val() == 'REFUND'){
		$tkBankAccountSpans.show();
	}else{
		$tkBankAccountSpans.hide();
	}
}
function submitFun(){
	$('#revokeForm').submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("revoke");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="revokeForm" method="post" cssClass="form"  modelAttribute="tradingRecord">
			<form:hidden path="id"/>
			<form:hidden path="loanAccountId"/>
			<table id="revoke_table" class="common_table">
				<tr>
					<th class="narrow" width="20%">撤销类型：</th>
					<td width="30%" >
						<select id="revokeType" name ="revokeType">
						<c:forEach  items="${revokeTypes}" var ="revokeTypeTemp">
					    	<option value="${revokeTypeTemp.key}">${revokeTypeTemp.name}</option>
					    </c:forEach>
						</select>
					</td>
					<th id="loanBankTh" class="narrow" width="20%" ><span name="tkBank" style="display: none">退款银行：</span>&nbsp;</th>
					<td width="30%"  id="loanBankTd">
						<span name="tkBank" style="display: none">
						<form:select path="bankAccountId">
							<c:forEach items="${bankAccountList}" var ="bankAccount">
								<form:option value="${bankAccount.id}" label="${bankAccount.name}"></form:option>
							</c:forEach>
						</form:select>
						</span>&nbsp;
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">退款代码：<span class="tRed">*</span></th>
					<td width="30%" >
						<c:choose>
							<c:when test="${revokeAbleTradingRecords.size() > 1}">
								<select id="revokeTradingRecordType" name="revokeTradingRecordType">
									<c:forEach items="${revokeAbleTradingRecords}" var="revokeTradingRecord">
										<option value="${revokeTradingRecord.id}" <c:if test="${tradingRecord.id ==revokeTradingRecord.id }" >selected="selectd"</c:if>>
										${revokeTradingRecord.tradingRecordType.revokeTradingRecordType}_${revokeTradingRecord.sum}</option>
									</c:forEach>
								</select>
							</c:when>
							<c:otherwise>
								<input type="text" id="revokeTradingRecordType" name ="revokeTradingRecordType" 
									value="${revokeTradingRecordType}"  readonly="readonly"/>
							</c:otherwise>
						</c:choose>
					</td>
					<th class="narrow" width="20%">交易金额：</th>
					<td width="30%">
						<input type="text" id="sum" name="sum" readonly="readonly"
							value='<fmt:formatNumber value="${tradingRecord.sum }" pattern="#,##0.00#"/>'/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">还款日期：</th>
					<td width="30%" >
						<form:input path="repayDate" readonly="true"/>
					</td>
					<th class="narrow" width="20%">处理日期：</th>
					<td width="30%" >
						<form:input path="createDate" readonly="true" />
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">当前期数：</th>
					<td width="30%" >
						<form:input path="currentPeriod" readonly="true"/>
					</td>
					<th class="narrow" width="20%">到期日期：</th>
					<td width="30%" >
						<form:input path="expireDate" readonly="true"/>
					</td>
				</tr>
				<c:forEach items="${tradingRecord.feeItem2PaymentAmountMap.entrySet() }" var="entry"  varStatus="status">
					<c:if test="${status.index % 2 == 0 }">
						<tr>
					</c:if>
							<th class="narrow" width="20%">${entry.getKey().getName() }</th>
							<td width="30%" >
								<input type="text" id="${entry.getKey().getCode() }" name="feeItemAmountMap_${entry.getKey().getCode() }" 
									value='<fmt:formatNumber value="${entry.getValue() }" pattern="#,##0.00#"/>' 
									readonly="readonly" />
							</td>
					<c:if test="${status.index % 2 != 0 }">
						</tr>
					</c:if>
					<c:if test="${status.last && status.index > 0 && status.index % 2 == 0 }">
							<th class="narrow" width="20%">&nbsp;</th>
							<td width="30%" >&nbsp;</td>
						</tr>
					</c:if>
				</c:forEach>
				<tr>
					<th class="narrow" width="20%">撤销原因：<span class="tRed">*</span></th>
					<td width="80%"  colspan="3" >
						<form:input path="revokeResean" data-rule="撤销原因:required;" cssStyle="width:450px;"/>
					</td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px">
						<a id="submitBtn" onclick="submitFun();return false;"  href="#" class="easyui-linkbutton">提交</a>  
						<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</body>