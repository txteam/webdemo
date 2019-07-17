<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryTransferToAccountRecordList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
<c:if test='${authContext.hasAuth("add_transferToAccountRecord")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_transferToAccountRecord")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_transferToAccountRecord")}'>
	$.canUpdate = true;
</c:if>

var grid = null;
var idFieldName = 'id';
<%!//FIXME: 修改名字字段 --%>
var nameFieldName = 'id'; 
<%!//FIXME: 修改实体名称 --%>
var entityName = 'TransferToAccountRecord'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/transferToAccountRecord/queryList.action',
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
			field : 'approveRemark',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'approveRemark',
			width : 200
		},
		{
			<%!//FIXME: 修改属性需要显示的属性字段 --%>
			field : 'transferToTradingRecord.id',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'transferToTradingRecord.id',
			width : 200
		},
		{
			field : 'remark',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'remark',
			width : 200
		},
		{
			field : 'postDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'postDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			<%!//FIXME: 修改属性需要显示的属性字段 --%>
			field : 'approveTradingRecord.id',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'approveTradingRecord.id',
			width : 200
		},
		{
			field : 'sum',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'sum',
			width : 200
		},
		{
			field : 'lastTransferDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'lastTransferDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'lastTransferAmount',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'lastTransferAmount',
			width : 200
		},
		{
			field : 'clientAccountId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'clientAccountId',
			width : 200
		},
		{
			field : 'afterSum',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'afterSum',
			width : 200
		},
		{
			field : 'approveDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'approveDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			<%!//FIXME: 修改属性需要显示的属性字段 --%>
			field : 'postTradingRecord.id',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'postTradingRecord.id',
			width : 200
		},
		{
			field : 'createDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'createDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'transferToClientId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'transferToClientId',
			width : 200
		},
		{
			field : 'poundageSum',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'poundageSum',
			width : 200
		},
		{
			field : 'overHisMaxAmount',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'overHisMaxAmount',
			width : 200
		},
		{
			<%!//FIXME: 修改属性需要显示的属性字段 --%>
			field : 'applyTradingRecord.id',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'applyTradingRecord.id',
			width : 200
		},
		{
			field : 'status',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'status',
			width : 200
		},
		{
			field : 'beforeSum',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'beforeSum',
			width : 200
		},
		{
			field : 'clientId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'clientId',
			width : 200
		},
		{
			field : 'lastUpdateDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'lastUpdateDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'applyDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'applyDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'factSum',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'factSum',
			width : 200
		},
		{
			field : 'transferToClientAccountId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'transferToClientAccountId',
			width : 200
		},
		{
			field : 'firstTransfer',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'firstTransfer',
			width : 200
		}
		<c:if test="${show_grid_action == true}">
		,{
			field : 'action',
			title : '操作',
			width : 220,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if($.canUpdate){
					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;';
				}
				
				if($.canDelete){
					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil_delete.png');
					str += '&nbsp;';
				}
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
		"addTransferToAccountRecord",
		"新增" + entityName,
		$.formatString("${contextPath}/transferToAccountRecord/toAddTransferToAccountRecord.action"),
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
		"updateTransferToAccountRecord",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/transferToAccountRecord/toUpdateTransferToAccountRecord.action?transferToAccountRecordId={0}",id),
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
	//判断是否确认删除指定的TransferToAccountRecord
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的TransferToAccountRecord
	    		$.post(
			    		'${contextPath}/transferToAccountRecord/deleteById.action',
			    		{transferToAccountRecordId:id},
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
					<th>transferToClientId</th>
					<td><input id="transferToClientId" name="transferToClientId"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>transferToClientAccountId</th>
					<td><input id="transferToClientAccountId" name="transferToClientAccountId"/></td>
				</tr>
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
					<th>clientId</th>
					<td><input id="clientId" name="clientId"/></td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
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
		<c:if test='${authContext.hasAuth("add_transferToAccountRecord") }'>
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
		</c:if>
		<c:if test='${authContext.hasAuth("update_transferToAccountRecord") }'>
			<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
		</c:if>
		<c:if test='${authContext.hasAuth("delete_transferToAccountRecord") }'>
			<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
		</c:if>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>