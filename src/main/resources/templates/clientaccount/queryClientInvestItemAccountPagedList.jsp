<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易明细</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
var grid = null;
var idFieldName = 'id';
var nameFieldName = 'id'; 
var entityName = 'ClientInvestItemAccount'; 
$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	grid = $('#grid').datagrid({
		url : '${contextPath}/clientInvestItemAccount/queryClientInvestItemAccountPagedList.action',
		fit : true,
		fitColumns : true,
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
			title : '项目名称',
			width : 200
		},
		{
			field : 'clientName',
			title : '客户登录名 ',
			width : 200
		},
		
		{
			field : 'investDate',
			title : '投资时间',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return "<span title='"+date.format('yyyy-MM-dd hh:mm:ss')+"'>"+date.format('yyyy-MM-dd')+"</span>";
			}
		},
		{
			field : 'expiredDate',
			title : '到期时间',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return "<span title='"+date.format('yyyy-MM-dd hh:mm:ss')+"'>"+date.format('yyyy-MM-dd')+"</span>";
			}
		},
		{
			field : 'effectiveDate',
			title : '生效日期',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return "<span title='"+date.format('yyyy-MM-dd hh:mm:ss')+"'>"+date.format('yyyy-MM-dd')+"</span>";
			}
		},
		{
			field : 'investAmount',
			title : '投资金额',
			width : 200
		},
		{
			field : 'receivableAmount',
			title : '项目应收金额',
			width : 200
		},
		{
			field : 'actualReceivedAmount',
			title : '实收总金额',
			width : 200
		},
		{
			field : 'waitReceiveAmount',
			title : '待回收总金额',
			width : 200
		},
		{
			field : 'actualReceivedPrincipalAmount',
			title : '实收本金金额',
			width : 200
		},
		{
			field : 'receivablePrincipalAmount',
			title : '应收本金金额',
			width : 200
		},
		{
			field : 'waitReceivePrincipalAmount',
			title : '待回收本金',
			width : 200
		},
		{
			field : 'actualReceivedIncomeAmount',
			title : '实收收入金额',
			width : 200
		},
		{
			field : 'waitReceiveIncomeAmount',
			title : '待回收入金额 ',
			width : 200
		},
	
		{
			field : 'receivableIncomeAmount',
			title : '应收收入金额',
			width : 200
		},
		{
			field : 'statusName',
			title : '投资状态',
			width : 200
		},
		{
			field : 'totalPeriod',
			title : '总期数',
			width : 200
		},
		{
			field : 'nextSettleDate',
			title : '下次应收计算日',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return "<span title='"+date.format('yyyy-MM-dd hh:mm:ss')+"'>"+date.format('yyyy-MM-dd')+"</span>";
			}
		}	]],
		toolbar : '#toolbar',
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			$editALink.linkbutton('disable');
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
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<!--//FIXME: 修改查询条件框体高度 -->
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>项目名称</th>
					<td><input id="investProjectName" name="investProjectName"/></td>
					<th>用户登录</th>
					<td><input id="clientName" name="clientName"/></td>
				<tr>
					<td colspan="4" class="button operRow">
						<a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'search'">查询</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="grid"></table>
    </div> 
    
	<div id="toolbar" style="display: none;">		
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>