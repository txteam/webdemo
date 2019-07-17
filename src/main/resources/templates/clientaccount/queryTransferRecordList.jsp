<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
//权限判定
var idFieldName = 'id';
var nameFieldName = 'id'; 
var entityName = '分润记录'; 
var clientTransferRecordGrid = null;
var clientTransferRecordGridFitColumnsFlag=false;
$(document).ready(function(){
	var clientAccountId = $("#clientAccountId").val();
	var clientId = $("#clientId").val();
	clientTransferRecordGrid = $('#clientTransferRecordGrid').datagrid({
		url : '${contextPath}/transferRecord/queryTransferRecordList.action',
		queryParams : {
			clientAccountId : clientAccountId,
			clientId:clientId
		},
		fit : false,
		fitColumns : clientTransferRecordGridFitColumnsFlag,
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
					field : 'serialNumber',
					title : '流水号',
					width : 200
				},
				{
					field : 'serialNumber',
					title : '流水号',
					width : 200
				},
				{
					field : 'transferOutTradingRecordId',
					title : '转出交易记录id',
					hidden:true,
					width : 200
				},
				{
					field : 'channelInfo',
					title : '渠道信息',
					width : 200
				},
				{
					field : 'targetInfo',
					title : '目标描述信息',
					width : 200
				},
				{
					field : 'transferInTradingRecordId',
					title : '转入交易记录Id',
					width : 200
				},
				{
					field : 'remark',
					title : '备注',
					width : 200
				},
				{
					field : 'transferInClientAccountId',
					title : '转入客户账户id',
					width : 200
				},
				{
					field : 'commissionFeeAmount',
					title : '手续费',
					width : 200
				},
				{
					field : 'refId',
					title : 'refId',
					hidden:true,
					width : 200
				},
				
				{
					field : 'transferOutStatus',
					title : '转出状态',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if(cellvalue=='WAIT_PROCESS'){
							return "处理中";
						}
						if(cellvalue=='WAIT_SERVER_PROCESS'){
							return "银行处理中";
						}
						if(cellvalue=='SUCCESS'){
							return "成功";
						}
						if(cellvalue=='FAIL'){
							return "失败";
						}
						return cellvalue;
					},
					sortable : true
				},
				{
					field : 'type',
					title : '类型',
					width : 200
				},
				{
					field : 'factTransferOutAmount',
					title : 'factTransferOutAmount',
					width : 200
				},
				{
					field : 'amount',
					title : '转账金额',
					width : 200
				},
				{
					field : 'commissionFee',
					title : '是否收取手续费',
					width : 200
				},
				{
					field : 'transferInClientId',
					title : '转入客户Id',
					hidden:true,
					width : 200
				},
				{
					field : 'createDate',
					title : '创建时间',
					width : 200
					,formatter: function(cellvalue, options, rowObject){
							var date = new Date();
							date.setTime(cellvalue);
							return date.format('yyyy-MM-dd hh:mm:ss');;
					}
				},
// 				{
// 					field : 'transferInClientAccountItemId',
// 					title : 'transferInClientAccountItemId',
// 					width : 200
// 				},
				{
					field : 'refType',
					title : 'refType',
					width : 200
				},
				{
					field : 'status',
					title : '状态',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if(cellvalue=='WAIT_PROCESS'){
							return "处理中";
						}
						if(cellvalue=='WAIT_SERVER_PROCESS'){
							return "银行处理中";
						}
						if(cellvalue=='SUCCESS'){
							return "成功";
						}
						if(cellvalue=='FAIL'){
							return "失败";
						}
						return cellvalue;
					},
					sortable : true
				},
// 				{
// 					field : 'transferOutClientAccountItemId',
// 					title : 'transferOutClientAccountItemId',
// 					width : 200
// 				},
				{
					field : 'transferInStatus',
					title : 'transferInStatus',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if(cellvalue=='WAIT_PROCESS'){
							return "处理中";
						}
						if(cellvalue=='WAIT_SERVER_PROCESS'){
							return "银行处理中";
						}
						if(cellvalue=='SUCCESS'){
							return "成功";
						}
						if(cellvalue=='FAIL'){
							return "失败";
						}
						return cellvalue;
					},
					sortable : true
				},
				{
					field : 'sourceInfo',
					title : '来源描述信息',
					width : 200
				},
				{
					field : 'lastUpdateDate',
					title : '最后更新时间',
					width : 200
					,formatter: function(cellvalue, options, rowObject){
							var date = new Date();
							date.setTime(cellvalue);
							return date.format('yyyy-MM-dd hh:mm:ss');;
					}
				},
				{
					field : 'transferOutClientAccountId',
					title : '转出客户账户id',
					width : 200
				},
				{
					field : 'tradingRecordId',
					title : '交易记录Id',
					width : 200
				},
				{
					field : 'transferOutClientId',
					title : '转出客户Id',
					width : 200
				},
				{
					field : 'factTransferInAmount',
					title : '实际转入金额',
					width : 200
				}
		]],
		toolbar : '#clientTransferRecordToolbar',
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
		<table id="clientTransferRecordGrid"></table>
    </div> 
	<div id="clientTransferRecordToolbar"  style="display: none;">	
		<div class="nav_header datagrid-toolbar">
				<label class="panel-title panel-with-icon">付款信息</label>	
		</div>
		<a onclick="clientTransferRecordGrid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
