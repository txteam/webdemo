<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
//权限判定
var idFieldName = 'id';
var nameFieldName = 'id'; 
var entityName = '分润记录'; 
var clientProfitRecordGrid = null;
var clientProfitRecordGridFitColumnsFlag=false;
$(document).ready(function(){
	var clientAccountId = $("#clientAccountId").val();
	clientProfitRecordGrid = $('#clientProfitRecordGrid').datagrid({
		url : '${contextPath}/clientAccount/queryClientInvestProfitRecordList.action',
		queryParams : {
			clientAccountId : clientAccountId
		},
		fit : false,
		fitColumns : clientProfitRecordGridFitColumnsFlag,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		loadFilter: function(data){
			var res = {total:0,rows:[]};
			if(!$.ObjectUtils.isEmpty(data)
					&& !$.ObjectUtils.isEmpty(data.list)){
				res['total'] = data.count;
				res['rows'] = data.list;
			}
			return res;
		}, 
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
		  		 {
		 			field : 'investProjectName',
		 			title : '分润项目',
		 			width : 200
		 		},			           
		 {
			field : 'profitClientName',
			title : '分润客户',
			width : 200
		},		           
		{
			field : 'profitClientAccountItemAccountName',
			title : '分润提成的客户子账户',
			width : 200
		},
		{
			field : 'profitRuleName',
			title : '分润规则',
			width : 200
		},
		{
			field : 'tradingRecordTypeName',
			title : '交易记录类型',
			width : 200
		},
		{
			field : 'stage',
			title : '分润阶段',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
					if(cellvalue=='INVEST_EFFICTIVE'){
						return"投资阶段";
					}
					return "投资结算阶段";
			}
		},
		{
			field : 'amount',
			title : '分润金额',
			width : 200
		},
		{
			field : 'rate',
			title : '分润比率(%)',
			width : 200
		},
		{
			field : 'createDate',
			title : '分润时间',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		}
		]],
		toolbar : '#clientProfitRecordToolbar',
		onDblClickRow : function(index, row){
		},
		onClickRow : function(index, row){
		},
		onLoadSuccess: function(data){
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			//如果结果超过12行，将高度固定在380px
			var $grid = $(this);
			setTimeout(function(){
				if(data['rows'].length > 12){
					$grid.datagrid('resize',{
						height: 380
					});
				}
				resetGridWidth();
			}, 200);
		}
	});
});


</script>

<div data-options="border:false"  style="width:auto" >
       <div data-options="region:'center',border:false">
		<table id="clientProfitRecordGrid"></table>
    </div> 
	<div id="clientProfitRecordToolbar"  style="display: none;">	
		<div class="nav_header datagrid-toolbar">
				<label class="panel-title panel-with-icon">分润记录</label>	
		</div>
		<a onclick="clientProfitRecordGrid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
