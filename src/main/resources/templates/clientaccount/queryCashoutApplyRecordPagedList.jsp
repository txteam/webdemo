<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryCashoutRecordPagedList</title>
<%@include file="../includes/commonHead.jsp"%>
<script type="text/javascript">
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
<c:if test='${authContext.hasAuth("add_cashoutRecord")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_cashoutRecord")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_cashoutRecord")}'>
	$.canUpdate = true;
</c:if>
var grid = null;
var idFieldName = 'id';
<%!//FIXME: 修改名字字段 --%>
var nameFieldName = 'id'; 
<%!//FIXME: 修改实体名称 --%>
var entityName = 'CashoutRecord'; 

$(document).ready(function(){
	var  $approveToPassButton = $("#approveToPassButton");
	var  $refuseButton = $("#refuseButton");
	var $getBankStatusButton = $("#getBankStatusButton");
	grid = $('#grid').datagrid({
		url : '${contextPath}/cashoutApplyRecord/queryCashoutApplyRecordPagedList.action',
		queryParams : {
			status : 'WAIT_APPROVE',
			paymentChannel: '${paymentChannel}'
		},
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
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
			title : '交易流水',
			width : 500
		},
		{
			field : 'clientLoginName',
			title : '客户登录名',
			width : 300
		},
		{
			field : 'clientUserName',
			title : '客户名称',
			width : 300
		},
		{
			field : 'paymentChannel',
			title : '支付渠道',
			width : 300,
			formatter: function(value, row, index){
				
				return value.name;
			}
		},
		{
			field : 'accountBeforeSum',
			title : '变更前金额',
			width : 250
		},
		{
			field : 'sum',
			title : '提现金额',
			width : 200
		},
		{
			field : 'commissionFee',
			title : '是否收手续费',
			width : 250,
			hidden:true,
			formatter: function(value, row, index){
				if(value)
				{
					return "是";					
				}
				return "否";
			}
		},
		{
			field : 'commissionFeeAmount',
			title : '手续费',
			width : 200
		},
		{
			field : 'factSum',
			title : '实际到账金额',
			width : 250
		},
		{
			field : 'accountAfterSum',
			title : '变更后金额',
			width : 200
		},
		{
			field : 'status',
			title : '状态',
			width : 200,
			formatter: function(value, row,index){
	   			return value.name;
			}
		},
		{
			field : 'type',
			title : '类型',
			width : 200,
			formatter: function(value, row,index){
	   			return value.name;
			}
		},
		{
			field : 'createDate',
			title : '申请提出时间',
			width : 320,
			formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');
			}
		},
		{
			field : 'lastUpdateDate',
			title : '初审时间',
			width : 320,
			formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');
			}
		},
		{
			field : 'clientAccountId',
			title : '客户账户Id',
			hidden:true,
			width : 200
		},
		{
			field : 'accountItemAfterAmount',
			title : '提现后子账户金额',
			hidden:true,
			width : 200
		},
		{
			field : 'accountItemBeforeAmount',
			title : '提现前子项目金额',
			hidden:true,
			width : 200
		},
		{
			field : 'targetInfo',
			title : '目标描述信息',
			hidden:true,
			width : 200
		},
		{
			field : 'tradingRecordId',
			title : '交易记录ID',
			hidden:true,
			width : 200
		},
		{
			field : 'clientAccountItemId',
			title : '客户子账户id',
			hidden:true,
			width : 200
		},
		{
			field : 'clientId',
			title : '客户Id',
			hidden:true,
			width : 200
		},
		{
			field : 'sourceInfo',
			title : 'sourceInfo',
			hidden:true,
			width : 200
		},
		{
			field : 'remark',
			title : '备注',
			width : 250
		}
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
		},
		onClickRow : function(index, row){
			if(row.status.key == "WAIT_SERVER_PROCESS")
			{
				$getBankStatusButton.linkbutton('enable');
			}
			if(row.status.key == "WAIT_APPROVE")
			{
				$approveToPassButton.linkbutton('enable');
				$refuseButton.linkbutton('enable');
			}
			
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			$approveToPassButton.linkbutton('disable');
			$getBankStatusButton.linkbutton('disable');
			$refuseButton.linkbutton('disable');
		}
	});
});

/*
 * 查询
 */
function queryFun() {
	grid.datagrid('load',$('#queryForm').serializeObject());
	return false;
}


function approveToPassFun(id,clientUserName){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		clientUserName = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"cashoutApplySuccess",
		"提现成功:" + clientUserName,
		$.formatString("${contextPath}/cashoutApplyRecord/toCashoutApplySuccess.action?cashoutRecordId={0}",id),
		450,300,function(){
			grid.datagrid('load',$('#queryForm').serializeObject());
	});
	return false;
}
/*
 * 重置密码
 */
function getBankStatus(id,name){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0]['serialNumber'];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认禁用指定BaseClientInfo
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认获取 [{0}]最新 支付信息?",name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定BaseClientInfo
    		$.post(
		    		'${contextPath}/cashoutApplyRecord/getCashoutNewStatus.action',
		    		{cashoutRecordId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("获取支付信息" + entityName + "成功");
		    			grid.datagrid('reload',$('#queryForm').serializeObject());
		    });
    	}
    });
    return false;
}

function refuseFun(id,clientUserName){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		clientUserName = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
			"cashoutApplyFail",
			"提现失败:" + clientUserName,
			$.formatString("${contextPath}/cashoutApplyRecord/toCashoutApplyFail.action?cashoutRecordId={0}",id),
			450,200,function(){
				grid.datagrid('load',$('#queryForm').serializeObject());
		});
		return false;
	return false;
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<!--//FIXME: 修改查询条件框体高度 -->
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
			<form id="queryForm" class="form">
<%-- 				<input type="hidden" value="${paymentChannel}" name="paymentChannel"/> --%>
				<table class="table table-hover table-condensed">
					<tr>
						<th>流水号</th>
						<td><input id="serialNumber" name="serialNumber" /></td>
						<th>状态</th>
						<td><select id="status" name="status">
								<option value="">全部</option>
								<c:forEach items="${cashoutRecordStatusEnums }" var="cashoutRecordStatusEnum">
									<option value="${cashoutRecordStatusEnum.key }" <c:if test="${cashoutRecordStatusEnum.key == 'WAIT_APPROVE' }">selected='selected'</c:if>>${cashoutRecordStatusEnum.name }</option>
								</c:forEach>
						</select></td>
						<th hidden=true>提现渠道</th>
						<td hidden=true><select id="paymentChannel" name="paymentChannel">
							<option value="">全部</option>
							<c:forEach items="${paymentChannelEnums }" var="temp">
								<option value="${temp.key }" <c:if test="${temp.key ==  paymentChannel}">selected='selected'</c:if>>${temp.name }</option>
							</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th>客户名称</th>
						<td><input id="clientUserName" name="clientUserName" /></td>
						<th>申请时间</th>
						<td><input id="beginDate" name="beginDate" type="text" class="easyui-datebox" required="required">至
						<input id="endDate" name="endDate" type="text" class="easyui-datebox" required="required"></td>
					<tr>
						<td colspan="6" class="button operRow"><a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'search'">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="grid"></table>
		</div>

		<div id="toolbar" style="display: none;">
			<c:if test='${authContext.hasAuth("approve_cashout_to_pass") }'>
				<a onclick="approveToPassFun();" href="javascript:void(0);" id="approveToPassButton" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">审批至通过</a>
			</c:if>
			<c:if test='${authContext.hasAuth("refuse_cashout") }'>
				<a onclick="refuseFun();" href="javascript:void(0);" id="refuseButton" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">拒绝提现</a>
			</c:if>
			<c:if test='${authContext.hasAuth("refuse_cashout") }'>
				<a onclick="getBankStatus();" href="javascript:void(0);" id="getBankStatusButton" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">获取支付信息</a>
			</c:if>
			<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
	</div>
</body>