<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" >

var overdueInterestRecordGrid = null;
var idFieldName = 'id';
var entityName = 'OverdueInterestRecord'; 

$("#overdule_interest_record").unbind('firstSelected').bind("firstSelected",function(){
	overdueInterestRecordGrid = $('#overdueInterestRecordGrid').datagrid({
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		onLoadSuccess: function(data){
			resetGridWidth();
		}
	});
});
</script>
<div data-options="region:'center',border:false" >
	<table id="overdueInterestRecordGrid">
		<thead>
			<th data-options="field:'currentPeriod',width:120">期数</th>
			<th data-options="field:'feeItem',width:120">费用项</th>
			<th data-options="field:'processDate',width:120">处理日期</th>
			<th data-options="field:'repayDate',width:120">还款日期</th>
			<th data-options="field:'interestAccrualDate',width:120">计息日</th>
			<th data-options="field:'expireDate',width:120">到期日</th>
			<th data-options="field:'overdueAmount',width:120">逾期金额</th>
			<th data-options="field:'principalBalance',width:120">本金结余</th>
			<th data-options="field:'principalBalance',width:120">贷款金额</th>
			<th data-options="field:'days',width:120">逾期天数</th>
			<th data-options="field:'overdueInterestRate',width:120">逾期利率</th>
			<th data-options="field:'overdueInterestAmount',width:120">逾期利息</th>
		</thead>
		<tbody>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
	<br/>
	<br/>
	<div class="form">
		<table>
			<tr>
				<th width="15%">贷款公司逾期利息合计：</th>
				<td width="10%" id ="overdueInterestSum">${loanAccountDetail.getReceivableSum('MAIN','DK_YQLX')}</td>
				<th width="10%">逾期利息豁免合计：</th>
				<td width="15%" id="exemptOverdueInterestSum">${loanAccountDetail.getExemptSum('MAIN','DK_YQLX')}</td>
				<th width="10%">已收逾期利息：</th>
				<td width="15%" id="receivedOverdueInterestSum">${loanAccountDetail.getActualReceivedSum('MAIN','DK_YQLX')}</td>
				<th width="10%">余额：</th>
				<td width="15%" id ="subTotalOverdueInterestSum">${loanAccountDetail.getSum('MAIN','DK_YQLX')}</td>
			</tr>
			<tr>
				<th width="15%">咨询公司逾期利息合计：</th>
				<td width="10%" id ="overdueInterestSum">${loanAccountDetail.getReceivableSum('MAIN','ZX_YQLX')}</td>
				<th width="10%">逾期利息豁免合计：</th>
				<td width="15%" id="exemptOverdueInterestSum">${loanAccountDetail.getExemptSum('MAIN','ZX_YQLX')}</td>
				<th width="10%">已收逾期利息：</th>
				<td width="15%" id="receivedOverdueInterestSum">${loanAccountDetail.getActualReceivedSum('MAIN','ZX_YQLX')}</td>
				<th width="10%">余额：</th>
				<td width="15%" id ="subTotalOverdueInterestSum">${loanAccountDetail.getSum('MAIN','ZX_YQLX')}</td>
			</tr>
			<tr>
				<th width="15%">逾期利息合计：</th>
				<td width="10%" id ="overdueInterestSum">${loanAccountDetail.getReceivableSum('MAIN','ZX_YQLX') + loanAccountDetail.getReceivableSum('MAIN','DK_YQLX')}</td>
				<th width="10%">逾期利息豁免合计：</th>
				<td width="15%" id="exemptOverdueInterestSum">${loanAccountDetail.getExemptSum('MAIN','ZX_YQLX') + loanAccountDetail.getExemptSum('MAIN','DK_YQLX')}</td>
				<th width="10%">已收逾期利息：</th>
				<td width="15%" id="receivedOverdueInterestSum">${loanAccountDetail.getActualReceivedSum('MAIN','ZX_YQLX') + loanAccountDetail.getActualReceivedSum('MAIN','DK_YQLX')}</td>
				<th width="10%">余额：</th>
				<td width="15%" id ="subTotalOverdueInterestSum">${loanAccountDetail.getSum('MAIN','ZX_YQLX') + loanAccountDetail.getSum('MAIN','DK_YQLX')}</td>
			</tr>
		</table>
	</div>
   </div> 
   
<div id="overdueInterestRecordToolbar" style="display: none;">		
	<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
		class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>
