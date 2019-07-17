<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryRechargeApplyRecordList</title>
<%@include file="../../includes/commonHead.jsp"%>
<script type="text/javascript">
//权限判定

var grid = null;
var idFieldName = 'id';
var nameFieldName = 'serialNumber';
var entityName = '充值申请记录'; 

$(document).ready(function(){
	var $toPassBtn = $("#toPassBtn");
	var $toDistributionBtn = $("#toDistributionBtn");
	var $toLedger = $("#toLedger");
	var $ledgerToDistribution = $("#ledgerToDistribution");
	var $ledgerToRevoked = $("#ledgerToRevoked");

	grid = $('#grid').datagrid({
		url : '${contextPath}/rechargeApplyRecord/queryWaitHandleRechargeApplyRecordList.action',
		queryParams : {
			status : '${status}',
			paymentChannel : '${paymentChannel}'
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
			title : '序列号',
			width : 280
		},
		{
			field : 'virtualAccountName',
			title : '虚拟账号名称',
			width : 280
		},
		{
			field : 'virtualAccountNumber',
			title : '虚拟账号',
			width : 200
		},
		{
			field : 'clientUserName',
			title : '客户名称',
			width : 280
		},
		{
			field : 'paymentOrderId',
			title : '充值订单id',
			hidden:true,
			width : 200
		},
		{
			field : 'handleTradingRecordId',
			title : '审批后处理交易记录id ',
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
			field : 'lastUpdateDate',
			title : '最后更新时间',
			hidden:true,
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		},
		{
			field : 'amount',
			title : '金额',
			width : 100
		},
		{
			field : 'clientPhoneNumber',
			title : '客户手机号码',
			width : 150
		},
		{
			field : 'approveRemark',
			title : '审批备注',
			width : 200
		},
		{
			field : 'remark',
			title : '备注信息',
			width : 200
		},
		{
			field : 'status',
			title : '状态',
			width : 100,
			formatter: function(value){
	   			return value.name;
			}
		},
		{
			field : 'clientAccountId',
			title : '客户账户ID',
			hidden:true,
			width : 200
		},
		{
			field : 'paymentChannel',
			title : '支付渠道',
			width : 200,
			formatter: function(value){
	   			return value.name;
			}
		},
		{
			field : 'tradingRecordId',
			title : '交易记录id',
			hidden:true,
			width : 200
		},
		{
			field : 'rechargeDate',
			title : '充值时间',
			hidden: true,
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		},
		{
			field : 'createDate',
			title : '申请时间',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		}
		]],
		toolbar : '#toolbar',
		onClickRow : function(index, row){
			if(row['status'].key == 'WAIT_APPROVE'){
				$toPassBtn.linkbutton('enable');
				$toDistributionBtn.linkbutton('enable');
				$toLedger.linkbutton('enable');
				$ledgerToDistribution.linkbutton('disable');
				$ledgerToRevoked.linkbutton('disable');
			}else if(row['status'].key == 'APPROVE_TO_LEDGER'){
				$toPassBtn.linkbutton('disable');
				$toDistributionBtn.linkbutton('disable');
				$toLedger.linkbutton('disable');
				$ledgerToDistribution.linkbutton('enable');
				$ledgerToRevoked.linkbutton('enable');
			}else{
				//alert(row['status'].key);
				$toPassBtn.linkbutton('disable');
				$toDistributionBtn.linkbutton('disable');
				$toLedger.linkbutton('disable');
				$ledgerToDistribution.linkbutton('disable');
				$ledgerToRevoked.linkbutton('disable');
			}
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			
			$toPassBtn.linkbutton('disable');
			$toDistributionBtn.linkbutton('disable');
			$toLedger.linkbutton('disable');
			$ledgerToDistribution.linkbutton('disable');
			$ledgerToRevoked.linkbutton('disable');
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
/*
 * 审批通过
 */
function approveToPassFun(id,name) {
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认删除指定的RechargeApplyRecord
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认审批通过{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的RechargeApplyRecord
	    		$.post(
			    		'${contextPath}/rechargeApplyRecord/approveToPass.action',
			    		{rechargeApplyId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("审批通过" + entityName + "成功");
			    			}else{
			    				$.formatString("审批通过{0}:{1}失败.指定{0}可能已经被其他管理员所删除.如果指定{0}依然存在，请联系系统管理员.",entityName,name);
			    			}
			    			grid.datagrid('load',$('#queryForm').serializeObject());
			    });
	    	}
    });
    return false;
}
/*
 * 审批至挂账
 */
function approveToLedgerFun(id,name) {
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认删除指定的RechargeApplyRecord
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认审批{0}至挂账:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的RechargeApplyRecord
	    		$.post(
			    		'${contextPath}/rechargeApplyRecord/approveToLedger.action',
			    		{rechargeApplyId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("审批至挂账" + entityName + "成功");
			    			}else{
			    				$.formatString("审批至挂账{0}失败.记录可能已经被处理.如果有疑问，请联系系统管理员.",name);
			    			}
			    			grid.datagrid('load',$('#queryForm').serializeObject());
			    });
	    	}
    });
    return false;
}
/*
 * 审批挂账至分配
 */
function approveLedgerToDistributeFun(id,name) {
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认删除指定的RechargeApplyRecord
	DialogUtils.openModalDialog(
		"distribute",
		$.formatString("充值挂账申请单至分配[{0}]",name),
		$.formatString("${contextPath}/rechargeApplyRecord/toQueryClientInfoForDistribute.action?status=APPROVE_TO_LEDGER&rechargeApplyId={0}",id),
		1000,520,function(){
			grid.datagrid('load',$('#queryForm').serializeObject());
	});
    return false;
}
/*
 * 审批至分配
 */
function approveToDistributeFun(id,name) {
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认删除指定的RechargeApplyRecord
	DialogUtils.openModalDialog(
		"distribute",
		$.formatString("充值申请单审批至分配[{0}]",name),
		$.formatString("${contextPath}/rechargeApplyRecord/toQueryClientInfoForDistribute.action?status=WAIT_APPROVE&rechargeApplyId={0}",id),
		1000,520,function(){
			grid.datagrid('load',$('#queryForm').serializeObject());
	});
    return false;
}




function approveLedgerToRevokedFun(id,name){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认删除指定的RechargeApplyRecord
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认撤销挂账申请{0}?",name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的RechargeApplyRecord
	    		$.post(
			    		'${contextPath}/rechargeApplyRecord/approveLedgerToRevoke.action',
			    		{rechargeApplyId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("撤销挂账申请" + name + "成功");
			    			}else{
			    				$.formatString("撤销挂账申请{0}失败.可能已经被处理.如有疑问，请联系系统管理员.",name);
			    			}
			    			grid.datagrid('load',$('#queryForm').serializeObject());
			    });
	    	}
    });
    return false;
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false">
		<div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
			<form id="queryForm" class="form">
				<input type="hidden" value="${paymentChannel}" name="paymentChannel"/>
				<table class="table table-hover table-condensed">
					<tr>
						<th width="20%">状态：</th>
						<td width="30%">
							<select id="status" name="status">
								<option value="">全部</option>
								<c:forEach items="${rechargeApplyStatusEnums }" var="rechargeApplyStatusEnums">
									<option value="${rechargeApplyStatusEnums.key }" <c:if test="${rechargeApplyStatusEnums.key == status }">selected='selected'</c:if>>${rechargeApplyStatusEnums.name }</option>
								</c:forEach>
							</select>
						</td>
						<th width="20%">&nbsp;</th>
						<td width="30%">&nbsp;</td>

					<%--<th>充值渠道:</th>
						<td>
							<select id="paymentChannel" name="paymentChannel">
								<option value="">--- 不限 ---</option>
								<c:forEach items="${paymentChannelEnums }" var="temp">
									<option value="${temp.key }" >--- ${temp.name } ---</option>
								</c:forEach>
								&lt;%&ndash;<option value="CMB">--- 招商银行 ---</option>&ndash;%&gt;
								&lt;%&ndash;<option value="CNCB">--- 中信银行 ---</option>&ndash;%&gt;
							</select>
						</td>--%>
					</tr>
					<tr>
						<td colspan="4" class="button operRow"><a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'search'">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false">
			<table id="grid"></table>
		</div>

		<div id="toolbar" style="display: none;">

			<c:if test='${authContext.hasAuth("approve_to_pass") }'>
				<a id="toPassBtn" onclick="approveToPassFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">审批至通过</a>
			</c:if>
			<c:if test='${authContext.hasAuth("approve_to_distribution") }'>
				<a id="toDistributionBtn" onclick="approveToDistributeFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">审批至分配</a>
			</c:if>
			<c:if test='${authContext.hasAuth("approve_to_redger") }'>
				<a id="toLedger" onclick="approveToLedgerFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">审批至挂账</a>
			</c:if>
			<c:if test='${authContext.hasAuth("approve_ledger_to_distribution") }'>
				<a id="ledgerToDistribution" onclick="approveLedgerToDistributeFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">挂账分配</a>
			</c:if>
			<c:if test='${authContext.hasAuth("approve_ledger_to_revoke") }'>
				<a id="ledgerToRevoked" onclick="approveLedgerToRevokedFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">挂账撤销</a>
			</c:if>
			<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
	</div>
</body>