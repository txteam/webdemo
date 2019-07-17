<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryAdjustAccountRecordPagedList</title>
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
		url : '${contextPath}/adjustAccountRecord/queryPagedList.action',
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
			field : 'hostFlw',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'hostFlw',
			width : 200
		},
		{
			field : 'receiveClientAccountItemId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'receiveClientAccountItemId',
			width : 200
		},
		{
			field : 'approveRemark',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'approveRemark',
			width : 200
		},
		{
			field : 'adjustAccountDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'adjustAccountDate',
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
			field : 'remark',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'remark',
			width : 200
		},
		{
			field : 'receiveVirtualAccountName',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'receiveVirtualAccountName',
			width : 200
		},
		{
			field : 'serialNumber',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'serialNumber',
			width : 200
		},
		{
			field : 'receiveClientId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'receiveClientId',
			width : 200
		},
		{
			field : 'type',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'type',
			width : 200
		},
		{
			field : 'hostSeq',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'hostSeq',
			width : 200
		},
		{
			field : 'receiveClientAccountId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'receiveClientAccountId',
			width : 200
		},
		{
			field : 'amount',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'amount',
			width : 200
		},
		{
			field : 'hostDate',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'hostDate',
			width : 200
		},
		{
			field : 'paymentChannel',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'paymentChannel',
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
			field : 'payVirtualAccountNumber',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'payVirtualAccountNumber',
			width : 200
		},
		{
			field : 'receiveVirtualAccountNumber',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'receiveVirtualAccountNumber',
			width : 200
		},
		{
			field : 'payVirtualAccountName',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'payVirtualAccountName',
			width : 200
		},
		{
			field : 'requestId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'requestId',
			width : 200
		},
		{
			field : 'status',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'status',
			width : 200
		},
		{
			field : 'paymentOrderId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'paymentOrderId',
			width : 200
		},
		{
			field : 'actionType',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'actionType',
			width : 200
		},
		{
			field : 'handleTradingRecordId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'handleTradingRecordId',
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
			field : 'tradingRecordId',
			<%!//FIXME: 修改属性中文名 --%>
			title : 'tradingRecordId',
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
							'${contextPath}/adjustAccountRecord/approveToPass.action',
							{adjustAccountId:id},
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
					<th>type</th>
					<td><input id="type" name="type"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>status</th>
					<td><input id="status" name="status"/></td>
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
		<c:if test='${authContext.hasAuth("approve_to_pass") }'>
			<a id="toPassBtn" onclick="approveToPassFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">审批至通过</a>
		</c:if>
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