<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript">
//权限判定
var idFieldName = 'id';
var nameFieldName = 'id'; 
var entityName = '分润记录'; 
var clientCashoutRecordGrid = null;
var clientCashoutRecordGridFitColumnsFlag=true;
$(document).ready(function(){
	var clientAccountId = $("#clientAccountId").val();
	clientCashoutRecordGrid = $('#clientCashoutRecordGrid').datagrid({
		url : '${contextPath}/cashoutRecord/queryCashoutRecordList.action',
		queryParams : {
			clientAccountId : clientAccountId
		},
		fit : false,
		fitColumns : clientCashoutRecordGridFitColumnsFlag,
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
			field : 'id',
			title : '主键',
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
				field : 'type',
				title : '类型',
				width : 150,
				formatter:baseJsonformatter,
				sortable : true
			},
			{
				field : 'beforeSum',
				title : '变更前金额',
				width : 100
			},

			{
				field : 'sum',
				title : '变更金额',
				width : 100
			},
			{
				field : 'poundageSum',
				title : '手续费金额',
				width : 100
			},
			{
				field : 'afterSum',
				title : '变更后金额',
				width : 100
			},
			
			{
				field : 'remark',
				title : '备注',
				width : 200
			},
			{
				field : 'approveRemark',
				title : '审批备注',
				width : 200
			},
			{
				field : 'paymentChannel',
				title : '渠道信息',
				width : 200,
                formatter:baseJsonformatter
			},

			{
				field : 'status',
				title : '状态',
				width : 150,
				formatter: baseJsonformatter,
				sortable : true
			},
			{
				field : 'clientId',
				title : '客户ID',
				hidden:true,
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
				field : 'clientAccountId',
				title : '客户账户id',
				hidden:true,
				width : 200
			},
			{
				field : 'tradingRecordId',
				title : '交易记录id',
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
			}
		]],
		toolbar : '#clientCashoutRecordToolbar',
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

<div data-options="border:false" style="width: auto">
	<div data-options="region:'center',border:false">
		<table id="clientCashoutRecordGrid"></table>
	</div>
	<div id="clientCashoutRecordToolbar" style="display: none;">
		<div class="nav_header datagrid-toolbar">
			<label class="panel-title panel-with-icon">提现记录</label>
		</div>
		<a onclick="clientCashoutRecordGrid.datagrid('reload');return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
