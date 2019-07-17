<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryAdjustAccountRecordList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
<c:if test='${authContext.hasAuth("add_adjustAccountRecord")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_adjustAccountRecord")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_adjustAccountRecord")}'>
	$.canUpdate = true;
</c:if>

var grid = null;
var idFieldName = 'id';
<%!//FIXME: 修改名字字段 --%>
var nameFieldName = 'id'; 
<%!//FIXME: 修改实体名称 --%>
var entityName = 'AdjustAccountRecord'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/adjustAccountRecord/queryWaitAdjustAccountPagedList.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
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
			field : 'subAccNo',
			title : '附属账号',
			width : 200
		},
		{
			field : 'TRANTYPE',
			title : '交易类型',
			width : 200
		},
		{
			field : 'TRANDATE',
			title : '交易日期',
			width : 200
		},
		{
			field : 'TRANTIME',
			title : '交易时间',
			width : 200
		},
		{
			field : 'HOSTFLW',
			title : '柜员交易号',
			width : 200
		},
		{
			field : 'HOSTSEQ',
			title : '交易序号',
			width : 200
		},
		{
			field : 'OPPACCNO',
			title : '对方账户名称',
			width : 200
		},
		{
			field : 'OPPACCNO',
			title : '对方账户名称',
			width : 200
		},
		{
			field : 'OPPBRANCHNAME',
			title : '对方开户行名称',
			width : 200
		},
		{
			field : 'CDFG',
			title : 'sum',
			width : 200
		},
		{
			field : 'clientMobilePhone',
			title : 'clientMobilePhone',
			width : 200
		},
		{
			field : 'type',
			title : '借贷标志',
			width : 200
		},
		{
			field : 'TRANAMT',
			title : '交易金额',
			width : 200
		},
		{
			field : 'ACCBAL',
			title : '账户余额',
			width : 200
		},
		{
			field : 'XTSFAM',
			title : '手续费金额',
			width : 200
		},
		{
			field : 'RESUME',
			title : '摘要',
			width : 200
		},
		{
			field : 'OPPBANKNO',
			title : '对方支付联行号',
			width : 200
		}
		<c:if test="${show_grid_action == true}">
		,{
			field : 'action',
			title : '操作',
			width : 220,
			formatter : function(value, row, index) {
// 				var str = '&nbsp;';
// 				if($.canUpdate){
					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="调账入款"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;';
// 				}
				
				
				return str;
			}
		}
		</c:if>	

		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
			if($.canUpdate){
				editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onClickRow : function(index, row){
			$editALink.linkbutton('enable');
			$deleteALink.linkbutton('enable');
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			
			$editALink.linkbutton('disable');
			$deleteALink.linkbutton('disable');
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
 * 新增
 */
function addFun() {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	<%!//FIXME: 修改新增modalDialog的width,height --%>
	DialogUtils.openModalDialog(
		"addAdjustAccountRecord",
		"新增" + entityName,
		$.formatString("${contextPath}/adjustAccountRecord/toAddAdjustAccountRecord.action"),
		450,220,function(){
			queryFun();
	});
	return false;
}
/**
 * 编辑
 */
function editFun(id,name) {
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	<%!//FIXME: 修改编辑modalDialog的width,height --%>
	DialogUtils.openModalDialog(
		"updateAdjustAccountRecord",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/adjustAccountRecord/toUpdateAdjustAccountRecord.action?adjustAccountRecordId={0}",id),
		450,220,function(){
			queryFun();
	});
	return false;
}
/*
 * 删除
 */
function deleteFun(id,name) {
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认删除指定的AdjustAccountRecord
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的AdjustAccountRecord
	    		$.post(
			    		'${contextPath}/adjustAccountRecord/deleteById.action',
			    		{adjustAccountRecordId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("删除" + entityName + "成功");
			    			}else{
			    				var errorMessage = $.formatString("删除{0}失败.可能已被处理.如有疑问,请联系管理员.",entityName);
			    				DialogUtils.alert("错误提示",errorMessage,"error");
			    			}
			    			queryFun();
			    });
	    	}
    });
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
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientAccountId</th>
					<td><input id="clientAccountId" name="clientAccountId"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>status</th>
					<td><input id="status" name="status"/></td>
				</tr>
				<tr>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>serialNumber</th>
					<td><input id="serialNumber" name="serialNumber"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>tradingRecordId</th>
					<td><input id="tradingRecordId" name="tradingRecordId"/></td>
				</tr>
				<tr>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientAccountItemType</th>
					<td><input id="clientAccountItemType" name="clientAccountItemType"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>type</th>
					<td><input id="type" name="type"/></td>
				</tr>
				<tr>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientAccountItemId</th>
					<td><input id="clientAccountItemId" name="clientAccountItemId"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientId</th>
					<td><input id="clientId" name="clientId"/></td>
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
		<c:if test='${authContext.hasAuth("add_adjustAccountRecord") }'>
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
		</c:if>
		<c:if test='${authContext.hasAuth("update_adjustAccountRecord") }'>
			<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
		</c:if>
		<c:if test='${authContext.hasAuth("delete_adjustAccountRecord") }'>
			<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
		</c:if>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>