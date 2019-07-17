<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
var investScheduleGrid = null;
var idScheduleFieldName = 'id';
var nameScheduleFieldName = 'id'; 
var entityScheduleName = '客户投资项目账户';
function loadSettleScheduleMapList(clientInvestItemAccountId){
	investScheduleGrid.datagrid('load',{'clientInvestItemAccountId':clientInvestItemAccountId});
}
$(document).ready(function(){
	investScheduleGrid = $('#investScheduleGrid').datagrid({
		url : '${contextPath}/clientAccount/querySettleScheduleMapList.action',
		queryParams : {
			clientInvestItemAccountId:''
		},		
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
		{
			field : 'period',
			title : '期数',
			width : 200
		},
		{
			field : 'shouldSettleDate',
			title : '应结算日',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd');;
			}
		},
		{
			field : 'settleDate',
			title : '结清处理日',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return '';
				}
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd');;
			}
		},
		{
			field : 'receivableAmount',
			title : '项目应收金额',
			width : 200,
			formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return '0';
				}	
				return formatterMoney(cellvalue,2);
			}
		},
		{
			field : 'actualReceivedAmount',
			title : '实收总金额',
			width : 200,
			formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return '0';
				}	
				return formatterMoney(cellvalue,2);
			}
		},
		{
			field : 'receivablePrincipalAmount',
			title : '应收本金金额',
			width : 200,
			formatter: function(value, row){
				if(!row['feeItem2scheduleMap']
					|| !row['feeItem2scheduleMap']['BJ']
					|| $.ObjectUtils.isEmpty(row['feeItem2scheduleMap']['BJ'].receivableAmount)){
					return '0';
				}	
				return row['feeItem2scheduleMap']['BJ'].receivableAmount;
			}
		},
		{
			field : 'receivableIncomeAmount',
			title : '应收收入金额',
			width : 200,
			formatter: function(value, row){
				if(!row['feeItem2scheduleMap']
					|| !row['feeItem2scheduleMap']['LX']
					|| $.ObjectUtils.isEmpty(row['feeItem2scheduleMap']['LX'].receivableAmount)){
					return '0';
				}	
				return row['feeItem2scheduleMap']['LX'].receivableAmount;
			}
		},
		{
			field : 'actualReceivedPrincipalAmount',
			title : '实收本金金额',
			width : 200,
			formatter: function(value, row){
				if(!row['feeItem2scheduleMap']
					|| !row['feeItem2scheduleMap']['BJ']
					|| $.ObjectUtils.isEmpty(row['feeItem2scheduleMap']['BJ'].actualReceivedAmount)){
					return '0';
				}	
				return row['feeItem2scheduleMap']['BJ'].actualReceivedAmount;
			}
		},
		{
			field : 'actualReceivedIncomeAmount',
			title : '实收收入金额',
			width : 200,
			formatter: function(value, row){
				if(!row['feeItem2scheduleMap']
					|| !row['feeItem2scheduleMap']['LX']
					|| $.ObjectUtils.isEmpty(row['feeItem2scheduleMap']['LX'].actualReceivedAmount)){
					return '0';
				}	
				return row['feeItem2scheduleMap']['LX'].actualReceivedAmount;
			}
		},
		{
			field : 'settle',
			title : '是否完成结算',
			width : 200
		}
		]],
		toolbar : '#investScheduleToolbar',
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
		}
	});
	$(document).bind('onStopResize',function(){
		investScheduleGrid.datagrid('resize',{
			width: $('#container').innerWidth(),
			height: 'auto'
		});
	});
});
</script>
<div>
	<div>
		<table id="investScheduleGrid"></table>
    </div> 
	<div id="investScheduleToolbar"  style="display: none;">	
		<div class="nav_header datagrid-toolbar">
				<label class="panel-title panel-with-icon">客户投资项目结算计划</label>	
		</div>
		<a onclick="investScheduleGrid.datagrid('load');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>