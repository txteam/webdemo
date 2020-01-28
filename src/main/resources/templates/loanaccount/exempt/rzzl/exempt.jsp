<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>还款</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript">
var vcid = "${loanAccount.vcid}";
var loanAccountId = "${loanAccount.id}";
var loanAccountType = "${loanAccount.loanAccountType}";

function initGroupType(){
	//还款金额类型变更
	$("#groupType").change(function(){
		var value = $("#groupType").val();
		var notMonthlyRepayItems = $(".autoAssignItem[MONTHLY_REPAY_SUM=false]")
		if("MONTHLY_REPAY_SUM" == value){
			notMonthlyRepayItems.val('');
			notMonthlyRepayItems.attr("readonly","readonly");
		}else{
			notMonthlyRepayItems.removeAttr("readonly");
		}
	});
};
$(function(){
	//初始化还款金额类型
	initGroupType();
	
	$(".autoAssignItem").change(function(){
		$("#submitBtn").linkbutton("disable");
	});
	$("#tradingSum").focus(function(){
		$("#submitBtn").linkbutton("disable");
	});
	if($("#tradingSum").val() * 1 > 0){
		preAutoAssign();
	}
	$("#tradingSum").numberbox({
		onChange : function(){
			preAutoAssign();
		}
	});
});
//到期还款日 时间改变事件
function changeRepayDate(dp){
	var earlyDate =dp.cal.getDateStr();
    var newDate =  dp.cal.getNewDateStr();
	var isNotChange = (earlyDate == newDate);
	var repayType = $("#repayType").val();
	if(isNotChange){
		return ;
	}else{
		window.location.href = "${contextPath}/loanAccountExempt/toExempt.action?loanAccountId=" + $("#loanAccountId").val() 
				+"&repayDate=" + newDate + "&repayType="+ repayType;
	}
}
function preAutoAssign(){
	var $autoAssignItems = $(".autoAssignItem");
	var $assignAble = $(".assignAble");
	var $assignViews = $(".assignView");
	$autoAssignItems.val("");
	$assignViews.val("0.00");
	$assignAble.val("0.00");
	
	var tradingSum = $("#tradingSum").numberbox("getValue") * 1;
	tradingSum = tradingSum.toFixed(2);
	if(tradingSum <= 0){
		DialogUtils.alert("提醒","交易金额应大于0.");
		return ;
	}
	var feeItem2AmountMap = {};
	var loanAccountId = $("#loanAccountId").val();
	var groupType = $("#groupType").val();
	var type = $("#type").val();
	var repayDate = $("#repayDate").val();
	
	$.post('${contextPath}/loanAccountExempt/autoAssign.action', {
		loanAccountId : loanAccountId,
		repayDate : repayDate,
		amount : tradingSum,
		groupType : groupType,
		feeItem2AmountMap : feeItem2AmountMap,
		type : type
	}, function(data) {
		DialogUtils.progress('close');
		if (data) {
			$.each(data,function(feeItem,amount){
				$("#assignAble_" + feeItem).val(amount);
			});
			$("#submitBtn").linkbutton("enable");
		}
	});
	return false;
}
function autoAssign(){
	var autoAssignItems = $(".autoAssignItem");
	var autoAssignSum = 0.0;
	var tradingSum = $("#tradingSum").numberbox("getValue") * 1;
	tradingSum = tradingSum.toFixed(2);
	if(tradingSum <= 0){
		DialogUtils.alert("提醒","交易金额应大于0.");
		return ;
	}
	
	var feeItem2AmountMap = {};
	$.each(autoAssignItems,function(index,autoAssignItems){
		var amount = $(this).val();
		if(!$.ObjectUtils.isEmpty(amount)){
			amount = parseFloat(amount * 1);
			amount = amount.toFixed(2);
			autoAssignSum += amount;
			feeItem2AmountMap[$(this).attr('feeItem')] = amount;
		}
	});
	autoAssignSum = parseFloat(autoAssignSum).toFixed(2);
	tradingSum = parseFloat(tradingSum).toFixed(2);
	if(autoAssignSum * 1 > tradingSum * 1){
		DialogUtils.alert("提醒","分配金额之和应小于或等于交易金额.分配金额和:" + autoAssignSum + "交易金额:" + tradingSum,"warning");
		return ;
	}
	
	var assignSumBalance = tradingSum - autoAssignSum;
	assignSumBalance = assignSumBalance.toFixed(2);
	$("#assignBalanceAmount").val(assignSumBalance);//分配结余，自由分配金额
	
	var loanAccountId = $("#loanAccountId").val();
	var groupType = $("#groupType").val();
	var type = $("#type").val();
	var repayDate = $("#repayDate").val();
	
	var $assignViews = $(".assignView");
	$assignViews.val("0.00");
	$.post('${contextPath}/loanAccountExempt/autoAssign.action', {
		loanAccountId : loanAccountId,
		repayDate : repayDate,
		amount : tradingSum,
		groupType : groupType,
		feeItem2AmountMap : feeItem2AmountMap,
		type : type
	}, function(data) {
		DialogUtils.progress('close');
		if (data) {
			$.each(data,function(feeItem,amount){
				$("#assignView_" + feeItem).val(amount);
			});
			$("#submitBtn").linkbutton("enable");
		}
	});
	return false;
}
function submitFun(){
	var autoAssignItems = $(".autoAssignItem");
	var autoAssignSum = 0.0;
	var tradingSum = $("#tradingSum").numberbox("getValue") * 1;
	tradingSum = tradingSum.toFixed(2);
	if(tradingSum <= 0){
		DialogUtils.alert("提醒","交易金额应大于0.");
		return ;
	}
	var feeItem2AmountMap = {};
	$.each(autoAssignItems,function(index,autoAssignItems){
		var amount = $(this).val();
		if(!$.ObjectUtils.isEmpty(amount)){
			amount = parseFloat(amount * 1);
			amount = amount.toFixed(2);
			autoAssignSum += amount;
			feeItem2AmountMap[$(this).attr('feeItem')] = amount;
		}
	});
	autoAssignSum = parseFloat(autoAssignSum).toFixed(2);
	tradingSum = parseFloat(tradingSum).toFixed(2);
	if(autoAssignSum * 1 > tradingSum * 1){
		DialogUtils.alert("提醒","分配金额之和应小于或等于交易金额.分配金额和:" + autoAssignSum + "交易金额:" + tradingSum,"warning");
		return ;
	}
	
	var assignSumBalance = tradingSum - autoAssignSum;
	assignSumBalance = assignSumBalance.toFixed(2);
	$("#assignBalanceAmount").val(assignSumBalance);//分配结余，自由分配金额
	
	var loanAccountId = $("#loanAccountId").val();
	var groupType = $("#groupType").val();
	var type = $("#type").val();
	var repayDate = $("#repayDate").val();
	
	DialogUtils.progress({
    	text : '数据提交中，请等待....'
    });
	$("#submitBtn").linkbutton("enable"); 
	$.post('${contextPath}/loanAccountExempt/exempt.action', {
		amount : tradingSum,
		feeItem2AmountMap : feeItem2AmountMap,
		loanAccountId : loanAccountId,
		groupType : groupType,
		type : type,
		repayDate : repayDate
	}, function(data) {
		DialogUtils.progress('close');
		if (data) {
			parent.DialogUtils.tip("豁免成功");
			parent.DialogUtils.closeDialogById("exempt");
		} else {
			parent.DialogUtils.tip("豁免失败");
		}
	});
	return false;
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("exempt");
}
</script>
<style type="text/css">
.form .common_table .numberInput{ width: 75px;}
</style>
</head>
<body style="overflow: hidden">
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow-x: hidden;overflow-y: auto;">
		<form:form id="repayForm" method="post" modelAttribute="exemptIntention" cssClass="form">
		<form:hidden path="loanAccountId"/>
		<table id="repayTable" class="common_table">
			<tr>
				<th class="narrow">豁免类型：<span class="tRed">*</span></th>
				<td>
					费用豁免
				</td>
				<th class="narrow">金额分配类型:</th>
				<td>
					<select id="groupType">
						<option value="RECEIVABLE_REPAY_SUM" selected="selected">应还总金额</option>
						<option value="MONTHLY_REPAY_SUM">每月应还</option>
					</select>
				</td>
				<th class="narrow">当前期数:</th>
				<td>
					<input type="text"  name="currentPeriod" value='${currentPeriod}' readonly="readonly"/>
				</td>
				<th>&nbsp;</th>
			</tr>
			<tr>
				<th class="narrow">还款日期：</th>
				<td>
					<input type="text" id="repayDate" name="repayDate"
						onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'${effectiveDate}',maxDate:'${maxRepayDate}',onpicking:function(dp){changeRepayDate(dp)}})"  
						value="${repayDate}"/>
				</td>
				<th class="narrow">到期日期：</th>
				<td>
					<input type="text" id="expireDate" name="expireDate" 
						value='<fmt:formatDate value="${expireDate}" pattern="yyyy-MM-dd"/>' 
						readonly="readonly"/>
				</td>
				<th class="narrow">自动分配金额：</th>
				<td>
					<input type="text"  id="assignBalanceAmount" name="assignBalanceAmount"  value="0" readonly="readonly"/>
				</td>
				<th>&nbsp;</th>
			</tr>
			<tr>
				<th class="narrow">交易金额：</th>
				<td>
					<input type="text" class="text easyui-numberbox" value="${maxExemptAbleSum }"
						id="tradingSum" name="tradingSum" data-rule="交易金额:required;number;" 
				    	data-options="min:0.01,precision:2,max:${maxExemptAbleSum}" />
				</td>
				<th class="narrow" colspan="5">
					<a id="autoAssignBtn" onclick="autoAssign();return false;" href="#" class="easyui-linkbutton">自动分配</a>
				</th>
			</tr>
			<!-- 还款分配 -->
			<tr>
				<td colspan="2" style="vertical-align: top;">
					<table>
						<tr>
							<th class="narrow" colspan="2">自动分配:</th>
						</tr>
						<tr>
							<th class="narrow" width="50%">管理费:</th>
							<td width="50%">
								<input id="assignAble_DK_GLF" name="assignAble['DK_GLF']" value = "0.00" readonly="true" class="text numberInput assignAble"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">利息:</th>
							<td width="50%">
								<input id="assignAble_DK_LX" name="assignAble['DK_LX']" value = "0.00" readonly="true" class="text numberInput assignAble"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">扣款失败手续费:</th>
							<td width="50%">
								<input id="assignAble_DK_KQSBSXF" name="assignAble['DK_KQSBSXF']" value = "0.00" readonly="true" class="text numberInput assignAble"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">逾期利息:</th>
							<td width="50%">
								<input id="assignAble_DK_YQLX" name="assignAble['DK_YQLX']" value = "0.00" readonly="true" class="text numberInput assignAble"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">外包佣金:</th>
							<td width="50%">
								<input id="assignAble_DK_WBYJ" name="assignAble['DK_WBYJ']" value = "0.00" readonly="true" class="text numberInput assignAble"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">提前还款违约金:</th>
							<td width="50%">
								<input id="assignAble_DK_TQHKWYJ" name="assignAble['DK_TQHKWYJ']" value = "0.00" readonly="true" class="text numberInput assignAble"/>
							</td>
						</tr>
					</table>
				</td>
				<td colspan="3" style="vertical-align: top;">
					<table>
						<colgroup>
							<col width="25%"/>
							<col width="15%"/>
							<col width="60%"/>
						</colgroup>
						<tr>
							<th class="narrow" colspan="2">调整分配</th>
							<th>可豁免金额</th>
						</tr>
						<tr>
							<c:set var="sumTemp" value="${paymentScheduleEntryMap['DK_GLF']}"></c:set>
							<th class="narrow" width="60%">管理费:</th>
							<td width="40%">
								<input id="assignIntention_DK_GLF" name="feeCfgItem.feeItem" value = ""
									<c:if test="${sumTemp == 0 }">readonly='true'</c:if>
								 	feeItem="DK_GLF" data-options="min:0,max:${sumTemp},precision:2"
									MONTHLY_REPAY_SUM="true" class="text numberInput autoAssignItem easyui-numberbox"/>
							</td>
							<th>
								<fmt:formatNumber value="${sumTemp}" pattern="#,##0.00#"/>
							</th>
						</tr>
						<tr>
							<c:set var="sumTemp" value="${paymentScheduleEntryMap['DK_LX']}"></c:set>
							<th class="narrow">利息:</th>
							<td>
								<input id="assignIntention_DK_LX" name="feeCfgItem.feeItem" value = "" 
									<c:if test="${sumTemp == 0 }">readonly='true'</c:if> 
									feeItem="DK_LX" data-options="min:0,max:${sumTemp},precision:2"
									MONTHLY_REPAY_SUM="true" class="text numberInput autoAssignItem easyui-numberbox"/>
							</td>
							<th>
								<fmt:formatNumber value="${sumTemp}" pattern="#,##0.00#"/>
							</th>
						</tr>
						<tr>
							<c:set var="sumTemp" value="${paymentScheduleEntryMap['DK_KQSBSXF']}"></c:set>
							<th>扣款失败手续费:</th>
							<td>
								<input id="assignIntention_DK_KQSBSXF" name="feeCfgItem.feeItem" value = ""
									<c:if test="${sumTemp == 0 }">readonly='true'</c:if>
									class="text numberInput autoAssignItem easyui-numberbox"
									feeItem="DK_KQSBSXF" MONTHLY_REPAY_SUM="false"
									data-options="min:0,precision:2,max:${sumTemp}"/>
							</td>
							<th>
								${sumTemp}
							</th>
						</tr>
						<tr>
							<c:set var="sumTemp" value="${paymentScheduleEntryMap['DK_YQLX']}"></c:set>
							<th>逾期利息:</th>
							<td>
								<input id="assignIntention_DK_YQLX" name="feeCfgItem.feeItem" value = ""
									<c:if test="${sumTemp == 0 }">readonly='true'</c:if>
									class="text numberInput autoAssignItem easyui-numberbox"
									feeItem="DK_YQLX" MONTHLY_REPAY_SUM="false"
									data-options="min:0,precision:2,max:${sumTemp}"/>
							</td>
							<th>
								${sumTemp}
							</th>
						</tr>
						
						<tr>
							<c:set var="sumTemp" value="${paymentScheduleEntryMap['DK_WBYJ']}"></c:set>
							<th>外包佣金:</th>
							<td>
								<input id="assignIntention_DK_WBYJ" name="feeCfgItem.feeItem" value = ""
									<c:if test="${sumTemp == 0 }">readonly='true'</c:if>
									class="text numberInput autoAssignItem easyui-numberbox"
									feeItem="DK_WBYJ" MONTHLY_REPAY_SUM="false"
									data-options="min:0,precision:2,max:${sumTemp}"/>
							</td>
							<th>
								${sumTemp}
							</th>
						</tr>
						<tr>
							<c:set var="sumTemp" value="${paymentScheduleEntryMap['DK_TQHKWYJ']}"></c:set>
							<th>逾期利息:</th>
							<td>
								<input id="assignIntention_DK_TQHKWYJ" name="feeCfgItem.feeItem" value = ""
									<c:if test="${sumTemp == 0 }">readonly='true'</c:if>
									class="text numberInput autoAssignItem easyui-numberbox"
									feeItem="DK_TQHKWYJ" MONTHLY_REPAY_SUM="false"
									data-options="min:0,precision:2,max:${sumTemp}"/>
							</td>
							<th>
								${sumTemp}
							</th>
						</tr>
						<!--<tr> -->
						<!--	<th>&nbsp;</th> -->
						<!--	<td>&nbsp;</td> -->
						<!--	<th>&nbsp;</th> -->
						<!--</tr> -->
					</table>
				</td>
				<td colspan="2" style="vertical-align: top;">
					<table>
						<tr>
							<th class="narrow" colspan="2">分配结果预览</th>
						</tr>
						<tr>
							<th class="narrow" width="50%">管理费:</th>
							<td width="50%">
								<input id="assignView_DK_GLF" name="assignView['DK_GLF']" feeItem="DK_GLF" value = "0.00" readonly="true" class="text numberInput assignView"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">利息:</th>
							<td width="50%">
								<input id="assignView_DK_LX" name="assignView['DK_LX']" feeItem="DK_LX" value = "0.00" readonly="true" class="text numberInput assignView"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">扣款失败手续费:</th>
							<td width="50%">
								<input id="assignView_DK_KQSBSXF" name="assignView['DK_KQSBSXF']" feeItem="DK_KQSBSXF" value = "0.00" readonly="true" class="text numberInput assignView"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">逾期利息:</th>
							<td width="50%">
								<input id="assignView_DK_YQLX" name="assignView['DK_YQLX']" feeItem="DK_YQLX" value = "0.00" readonly="true" class="text numberInput assignView"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">外包佣金:</th>
							<td width="50%">
								<input id="assignView_DK_WBYJ" name="assignView['DK_WBYJ']" feeItem="DK_WBYJ" value = "0.00" readonly="true" class="text numberInput assignView"/>
							</td>
						</tr>
						<tr>
							<th class="narrow" width="50%">提前还款违约金:</th>
							<td width="50%">
								<input id="assignView_DK_TQHKWYJ" name="assignView['DK_TQHKWYJ']" feeItem="DK_TQHKWYJ" value = "0.00" readonly="true" class="text numberInput assignView"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rightOperRow" colspan="7" style="padding-right: 50px">
					<a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton" disabled="disabled">提交</a> 
					<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
				</td>
			</tr>
		</table>
		</form:form>
	</div>
</div>
</body>