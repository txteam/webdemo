<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" >
var tradingRecordGrid = null;
var idFieldName = 'id';
var entityName = '贷款账户'; 
$("#trading_record").unbind('firstSelected').bind("firstSelected",function(){
	tradingRecordGrid = $('#tradingRecordGrid').datagrid({
		url:"${contextPath}/loanAccountDetail/queryTradingRecordDetailList.action?loanAccountId=${loanAccountDetail.id}",
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		rowStyler : function(index,row){
			if(row.revoked){
				return "color:#FF4500"
			}else if(row.category && row.category.key.indexOf('REVOKE') >= 0){
				return "color:#FF4500"
			}else if(!row.received){
				return "color:blue"
			}
		},
		onLoadSuccess: function(data){
			//如果结果超过12行，将高度固定在380px
			var $grid = $(this);
			var timeoutHandler = setTimeout(function(){
				if(data['rows'].length > 12){
					$grid.datagrid('resize',{
						height: 380
					});
				}else{
					$grid.datagrid('resize',{
						height: 55 + 32 * ( data['rows'].length +1)
					});
				}
			}, 200);
			resetGridWidth();
		},
		frozenColumns: [[ 
		{
			title : '主键',
			field : 'id',
			width : 160,
			hidden : true
		},
		{
			field : 'currentPeriod',
			title : '期数',
			width : 80
		},
		{
			field : 'createDate',
			title : '处理日期',
			width : 80
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue){
		  			var date = new Date();
		  			date.setTime(cellvalue);
		  			return date.format('yyyy-MM-dd');
				}
				return null;
			}
		},
		{
			field : 'repayDate',
			title : '还款日期',
			width : 80
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue){
		  			var date = new Date();
		  			date.setTime(cellvalue);
		  			return date.format('yyyy-MM-dd');
				}
				return null;
			}
		},
		{
			field : 'expireDate',
			title : '到期时间',
			width : 80
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue){
		  			var date = new Date();
		  			date.setTime(cellvalue);
		  			return date.format('yyyy-MM-dd');
				}
				return null;
			}
		},
		{
   			field : 'summary',
   			title : '还款摘要',
   			width : 160
   			,formatter: function(value, row, index){
   				return value;
   			}
   		},
   		{
   			field : 'sum',
   			title : '交易金额',
   			width : 120
   		},
   		{
   			field : 'repaySum',
   			title : '还款金额',
   			width : 120
   			,formatter: function(value, rowData, rowIndex){
   				return value;
   			}
   		},
   		{
			field : 'BJ',
			title : '本金',
			width : 120
			,formatter: function(value, row, index){
				var value = 0;
				if(row.feeItem2PaymentAmountMap["DK_BJ"]){
					var entry = row.feeItem2PaymentAmountMap["DK_BJ"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
			}
	 	},
   		{
			field : 'GLF',
			title : '管理费',
			width : 80,
			formatter: function(value, row, index){
				var value = 0;
				if(row.category.key == 'EXEMPT' || row.category.key == 'REVOKE_EXEMPT'){
					value += row.feeItem2ExemptAmountMap["ZX_GLF"] ? row.feeItem2ExemptAmountMap["ZX_GLF"] : 0;
				}else if(row.category.key == 'CHARGE' || row.category.key == 'REVOKE_CHARGE'){
					value += row.feeItem2ChargeAmountMap["ZX_GLF"] ? row.feeItem2ChargeAmountMap["ZX_GLF"] : 0;
				}else{
					value += row.feeItem2PaymentAmountMap["ZX_GLF"] ? row.feeItem2PaymentAmountMap["ZX_GLF"] : 0;
				}
				return value;
   			}
		},
		{
			field : 'LX',
			title : '利息',
			width : 80,
			formatter: function(value, row, index){
				var value = 0;
				if(row.category.key == 'EXEMPT' || row.category.key == 'REVOKE_EXEMPT'){
					value += row.feeItem2ExemptAmountMap["DK_LX"] ? row.feeItem2ExemptAmountMap["DK_LX"] : 0;
				}else if(row.category.key == 'CHARGE' || row.category.key == 'REVOKE_CHARGE'){
					value += row.feeItem2ChargeAmountMap["DK_LX"] ? row.feeItem2ChargeAmountMap["DK_LX"] : 0;
				}else{
					value += row.feeItem2PaymentAmountMap["DK_LX"] ? row.feeItem2PaymentAmountMap["DK_LX"] : 0;
				}
				return value;
   			}
		},
		{
  			field : 'principalBalance',
  			title : '本金结余',
  			width : 120
  		}
		]],
		columns: [[
		{
   			field : 'YCXSXF',
   			title : '一次性手续费',
   			width : 80
   			,formatter: function(value, row, index){
   				var value = 0;
   				if(row.category.key == 'EXEMPT' || row.category.key == 'REVOKE_EXEMPT'){
					value += row.feeItem2ExemptAmountMap["DK_YCXSXF"] ? row.feeItem2ExemptAmountMap["DK_YCXSXF"] : 0;
					value += row.feeItem2ExemptAmountMap["ZX_YCXSXF"] ? row.feeItem2ExemptAmountMap["ZX_YCXSXF"] : 0;
				}else if(row.category.key == 'CHARGE' || row.category.key == 'REVOKE_CHARGE'){
					value += row.feeItem2ChargeAmountMap["DK_YCXSXF"] ? row.feeItem2ChargeAmountMap["DK_YCXSXF"] : 0;
					value += row.feeItem2ChargeAmountMap["ZX_YCXSXF"] ? row.feeItem2ChargeAmountMap["ZX_YCXSXF"] : 0;
				}else{
					value += row.feeItem2PaymentAmountMap["DK_YCXSXF"] ? row.feeItem2PaymentAmountMap["DK_YCXSXF"] : 0;
					value += row.feeItem2PaymentAmountMap["ZX_YCXSXF"] ? row.feeItem2PaymentAmountMap["ZX_YCXSXF"] : 0;
				}
				return value;
   			}
   		},
   		{
   			field : 'ZQF',
   			title : '展期费',
   			width : 80
   			,formatter: function(value, row, index){
   				var value = 0;
				if(row.feeItem2PaymentAmountMap["ZX_DQYQF"]){
					value += row.feeItem2PaymentAmountMap["ZX_DQYQF"];
				}
				if(row.feeItem2PaymentAmountMap["DK_DQYQF"]){
					value += row.feeItem2PaymentAmountMap["DK_DQYQF"];
				}
				if(row.feeItem2PaymentAmountMap["ZX_DHZQF"]){
					value += row.feeItem2PaymentAmountMap["ZX_DHZQF"];
				}
				if(row.feeItem2PaymentAmountMap["DK_DHZQF"]){
					value += row.feeItem2PaymentAmountMap["DK_DHZQF"];
				}
				return value;
   			}
   		},
   		{
   			field : 'KQSBSXF',
   			title : '扣款失败手续费',
   			width : 80
   			,formatter: function(value, row, index){
   				var value = 0;
				if(row.feeItem2PaymentAmountMap["ZX_KQSBSXF"]){
					value += row.feeItem2PaymentAmountMap["ZX_KQSBSXF"];
				}
				if(row.feeItem2PaymentAmountMap["DK_KQSBSXF"]){
					value += row.feeItem2PaymentAmountMap["DK_KQSBSXF"];
				}
				return value;
   			}
   		},
   		{
   			field : 'YQLX',
   			title : '逾期利息',
   			width : 80,
   			formatter: function(value, row, index){
				var value = 0;
				if(row.feeItem2PaymentAmountMap["ZX_YQLX"]){
					value += row.feeItem2PaymentAmountMap["ZX_YQLX"];
				}
				if(row.feeItem2PaymentAmountMap["DK_YQLX"]){
					value += row.feeItem2PaymentAmountMap["DK_YQLX"];
				}
				return value;
   			}
   		},
   		{
   			field : 'TQHKWYJ',
   			title : '违约金',
   			width : 80
   			,formatter: function(value, row, index){
				var value = 0;
				if(row.feeItem2PaymentAmountMap["DK_TQHKWYJ"]){
					value += row.feeItem2PaymentAmountMap["DK_TQHKWYJ"];
				}
				if(row.feeItem2PaymentAmountMap["ZX_TQHKWYJ"]){
					value += row.feeItem2PaymentAmountMap["ZX_TQHKWYJ"];
				}
				return value;
   			}
   		},
		{
  			field : 'WBYJ',
  			title : '外包佣金',
  			width : 80
  			,formatter: function(value, row, index){
				var value = 0;
				if(row.feeItem2PaymentAmountMap["ZX_WBYJ"]){
					value += row.feeItem2PaymentAmountMap["ZX_WBYJ"];
				}
				if(row.feeItem2PaymentAmountMap["DK_WBYJ"]){
					value += row.feeItem2PaymentAmountMap["DK_WBYJ"];
				}
				return value;
  			}
 		}
   		]],
   		toolbar : '#tradingRecordGridToolbar',
	});
});
</script>
<div data-options="region:'center',border:false">
	<table id="tradingRecordGrid"></table>
</div>
<div id="tradingRecordGridToolbar" style="display: none;">
	<a onclick="tradingRecordGrid.datagrid('reload');return false;" href="javascript:void(0);"
		class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	<c:if test='${isProcessAble && loanAccountDetail.accountStatus.code != "ACCN" && loanAccountDetail.accountStatus.code != "RS"}'>
		<c:if test='${authContext.hasAuth("exempt") && !loanAccount.locked}'>
			<a id="repayALi1nk" onclick="exemptFun();" href="javascript:void(0);" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'money_yen'">豁免</a>
		</c:if>
		<c:if test='${authContext.hasAuth("repay_menu_revoke_btn_auth")}'>
			<a id="revokeALink" onclick="revokeFun();" href="javascript:void(0);" class="easyui-linkbutton" 
				data-options="plain:true,iconCls:'cancel'">撤销</a>
		</c:if>
	</c:if>
</div>