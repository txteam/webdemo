<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div data-options="region:'center',border:false" >
	<table id="repaymentRecordGrid"></table>
   </div> 
   <div id="paymentRecordDetail" class="form" style="display: none">
	   <table>
	   		<tr>
	   			<th class="narrow" width="10%">处理分行：</th>
	   			<td width="20%"><span id="organizationName"></span></td>
	   			<th class="narrow" width="10%">处理人：</th>
	   			<td width="20%"><span id="operatorName"></span></td>
	   			<th class="narrow" width="10%">处理时间：</th>
	   			<td width="20%"><span id="createDate"></span></td>
	   		</tr>
	   		<tr>
	   			<th class="narrow">上次更新人：</th>
	   			<td><span id="lastUpdateOperatorName"></span></td>
	   			<th class="narrow">更新时间：</th>
	   			<td><span id="lastUpdateDate"></span></td>
	   			<th class="narrow">撤销原因：</th>
	   			<td><span id="revokeResean"></span></td>
	   		</tr>
	   		<tr>
	   			<th class="narrow">银行代码：</th>
	   			<td><span id="bankAccountName"></span></td>
	   			<th class="narrow">备注：</th>
	   			<td colspan="3"><span id="remark"></span></td>
	   		</tr>
	   		<tr>
	   			<th class="narrow">本金：</th>
	   			<td><span id="bjValue"></span></td>
	   			<th class="narrow">利息：</th>
	   			<td><span id="lxValue"></span></td>
	   			<th class="narrow">管理费</th>
	   			<td><span id="glfValue"></span></td>
	   		</tr>
	   		<tr>
	   			<th class="narrow">本金结余：</th>
	   			<td><span id="principalBalance"></span></td>
	   			<th class="narrow">逾期利息</th>
	   			<td><span id="yqlxValue"></span></td>
	   			<th class="narrow">违约金：</th>
	   			<td><span id="tqjqwyjValue"></span></td>
	   		</tr>
	   </table>
   </div>
   <div id="repaymentRecordGridToolbar" style="display: none;">
   		<a onclick="repaymentRecordGrid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		<c:if test='${isProcessAble && loanAccountDetail.accountStatus.code != "ACCN" && loanAccountDetail.accountStatus.code != "RS"}'>
			<c:if test='${authContext.hasAuth("cash_repay") && !loanAccount.locked}'>
				<a id="repayALi1nk" onclick="repayFun();" href="javascript:void(0);" class="easyui-linkbutton" 
					<c:if test='${loanAccountDetail.existNotReceivedTrading}'>disabled="disabled"</c:if>
					data-options="plain:true,iconCls:'money_yen'">还款</a>
			</c:if>
			<c:if test='${authContext.hasAuth("repay_menu_revoke_btn_auth")}'>
				<a id="revokeALink" onclick="revokeRepayFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'cancel'">撤销还款</a>
			</c:if>
			<!-- 
				<c:if test='${authContext.hasAuth("delay_apply") && loanAccountDetail.accountStatus!="ACCN" && loanAccountDetail.accountStatus!="RS" && loanAccountDetail.accountStatus!="ES" && loanAccountDetail.accountStatus!="FP"}'>
					<a id="delayApplyALink" onclick="delayApplyFun();" 
						href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'time_go'"
						<c:if test='${loanAccountDetail.existNotReceivedTrading}'>
							disabled="disabled"
						</c:if>
					>展期申请</a>
				</c:if>
				<c:if test='${authContext.hasAuth("revoke_delay_trading") && loanAccountDetail.accountStatus!="ACCN" && loanAccountDetail.accountStatus!="RS"}'>
					<a id="revokeDelayTradingALink" onclick="revokeDelayTradingFun();" 
						href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'time_delete'">撤销展期交易</a>
				</c:if>
			 -->
		</c:if>
		<%-- 		<c:if test='${authContext.hasAuth("main_search_manage_menu_loan_info_account_info_print")}'> --%>
		<!-- 			<a id="printAlink" onclick="printFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'printer'">还款打印</a> -->
		<%-- 		</c:if> --%>
		<span id="showRevokedBtn" style="display:none" >
			<a onclick="showRevoked();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">所有还款</a> 
		</span>
		<span id="hideRevokedBtn">
			<a onclick="hideRevoked();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">正常还款</a> 
		</span>
   </div>
   <div data-options="region:'center',border:false">
   		<input type="hidden" id="tradingRecordId"/>
   		<input type="hidden" id="adjust"/>
   		<input type="hidden" id="relatedTradingRecordId"/>
		<table id="financeDetailGrid"></table>
   </div> 
   <div id="financeDetailGridToolbar" style="display: none;">
   	<!-- financeDetail() -->
   	<!-- <a onclick="" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'book_open'">查看财务明细</a> -->
   </div>