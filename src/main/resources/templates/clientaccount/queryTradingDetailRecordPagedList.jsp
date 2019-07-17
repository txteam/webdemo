<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryTradingDetailRecordPagedList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
<c:if test='${authContext.hasAuth("add_tradingDetailRecord")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_tradingDetailRecord")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_tradingDetailRecord")}'>
	$.canUpdate = true;
</c:if>

var grid = null;
var idFieldName = 'id';
<%!//FIXME: 修改名字字段 --%>
var nameFieldName = 'id'; 
<%!//FIXME: 修改实体名称 --%>
var entityName = 'TradingDetailRecord'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/tradingDetailRecord/queryTradingDetailRecordListIncludeInvalid.action',
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
			field : 'channelInfo',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'channelInfo',
			width : 200
		},
		{
			field : 'targetInfo',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'targetInfo',
			width : 200
		},
		{
			field : 'remark',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'remark',
			width : 200
		},
		{
			field : 'accountAfterSum',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'accountAfterSum',
			width : 200
		},
		{
			field : 'sum',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'sum',
			width : 200
		},
		{
			field : 'type',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'type',
			width : 200
		},
		{
			field : 'clientAccountItemId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'clientAccountItemId',
			width : 200
		},
		{
			field : 'clientId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'clientId',
			width : 200
		},
		{
			field : 'sourceInfo',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'sourceInfo',
			width : 200
		},
		{
			field : 'amount',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'amount',
			width : 200
		},
		{
			field : 'accountBeforeSum',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'accountBeforeSum',
			width : 200
		},
		{
			field : 'clientAccountId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'clientAccountId',
			width : 200
		},
		{
			field : 'accountItemBeforeAmount',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'accountItemBeforeAmount',
			width : 200
		},
		{
			field : 'tradingRecordId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'tradingRecordId',
			width : 200
		},
		{
			field : 'accountItemAfterAmount',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'accountItemAfterAmount',
			width : 200
		},
		{
			field : 'createDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'createDate',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
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
		"addTradingDetailRecord",
		"新增" + entityName,
		$.formatString("${contextPath}/tradingDetailRecord/toAddTradingDetailRecord.action"),
		450,220,function(){
			grid.datagrid('load',$('#queryForm').serializeObject());
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
		"updateTradingDetailRecord",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/tradingDetailRecord/toUpdateTradingDetailRecord.action?tradingDetailRecordId={0}",id),
		450,220,function(){
			grid.datagrid('load',$('#queryForm').serializeObject());
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
	//判断是否确认删除指定的TradingDetailRecord
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的TradingDetailRecord
	    		$.post(
			    		'${contextPath}/tradingDetailRecord/deleteTradingDetailRecordById.action',
			    		{tradingDetailRecordId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("删除" + entityName + "成功");
			    			}else{
			    				$.formatString("删除{0}:{1}失败.指定{0}可能已经被其他管理员所删除.如果指定{0}依然存在，请联系系统管理员.",entityName,name);
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
	<!--//FIXME: 修改查询条件框体高度 -->
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>tradingRecordId</th>
					<td><input id="tradingRecordId" name="tradingRecordId"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>type</th>
					<td><input id="type" name="type"/></td>
				</tr>
				<tr>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientAccountId</th>
					<td><input id="clientAccountId" name="clientAccountId"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientAccountItemId</th>
					<td><input id="clientAccountItemId" name="clientAccountItemId"/></td>
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
		<c:if test='${authContext.hasAuth("add_tradingDetailRecord") }'>
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
		</c:if>
		<c:if test='${authContext.hasAuth("update_tradingDetailRecord") }'>
			<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
		</c:if>
		<c:if test='${authContext.hasAuth("delete_tradingDetailRecord") }'>
			<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
		</c:if>
		<c:if test='${authContext.hasAuth("enable_tradingDetailRecord") }'>
			<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a>
		</c:if>
		<c:if test='${authContext.hasAuth("disable_tradingDetailRecord") }'>
			<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>
		</c:if>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>