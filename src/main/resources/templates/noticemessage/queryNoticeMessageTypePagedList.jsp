<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryNoticeMessageTypePagedList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
<c:if test='${authContext.hasAuth("add_noticeMessageType")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_noticeMessageType")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_noticeMessageType")}'>
	$.canUpdate = true;
</c:if>

var grid = null;
var idFieldName = 'id';
var nameFieldName = 'name';
var entityName = '站内消息类型'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/noticeMessageType/queryPagedList.action',
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
			field : 'code',
			title : '编码',
			width : 200
		},
		{
			field : 'name',
			title : '名称',
			width : 200
		},
		{
			field : 'valid',
			title : '是否有效',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if(value){
	   				text = '是';
	   			}else{
	   				text = '否';
	   			}
	   			return text;
			}
		},
		{
			field : 'modifyAble',
			title : '是否可编辑',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if(value){
	   				text = '是';
	   			}else{
	   				text = '否';
	   			}
	   			return text;
			}
		},
		{
			field : 'remark',
			title : '备注',
			width : 200
		},
		{
			field : 'createDate',
			title : '创建时间',
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
			field : 'lastUpdateDate',
			title : '最后修改时间',
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
		}
		<c:if test="${show_grid_action == true}">
		,{
			field : 'action',
			title : '操作',
			width : 220,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if(!row.valid && $.canEnable){
					str += $.formatString('<img onclick="enableFun(\'{0}\',\'{1}\');" src="{2}" title="启用"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/control/control_play_blue.png');
					str += '&nbsp;';
				}
				if($.canUpdate){
					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;';
				}
				if($.canDelete){
					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil_delete.png');
					str += '&nbsp;';
				}
				if(row.valid && $.canDisable){
					str += $.formatString('<img onclick="disableFun(\'{0}\',\'{1}\');" src="{2}" title="禁用"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/control/control_stop_blue.png');
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
	DialogUtils.openModalDialog(
		"addNoticeMessageType",
		"新增" + entityName,
		$.formatString("${contextPath}/noticeMessageType/toAddNoticeMessageType.action"),
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
	DialogUtils.openModalDialog(
		"updateNoticeMessageType",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/noticeMessageType/toUpdateNoticeMessageType.action?noticeMessageTypeId={0}",id),
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
	//判断是否确认删除指定的NoticeMessageType
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的NoticeMessageType
	    		$.post(
			    		'${contextPath}/noticeMessageType/deleteById.action',
			    		{noticeMessageTypeId:id},
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
 * 禁用
 */
function disableFun(id,name){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认禁用指定NoticeMessageType
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认禁用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认禁用指定NoticeMessageType
    		$.post(
		    		'${contextPath}/noticeMessageType/disableById.action',
		    		{noticeMessageTypeId:id},
		    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("禁用" + entityName + "成功");
			    			}else{
			    				var errorMessage = $.formatString("禁用{0}失败.",entityName);
			    				DialogUtils.alert("错误提示",errorMessage,"error");
			    			}
			    			queryFun();
		    });
    	}
    });
    return false;
}
/*
 * 启用
 */
function enableFun(id,name){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认禁用指定NoticeMessageType
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定NoticeMessageType
    		$.post(
		    		'${contextPath}/noticeMessageType/enableById.action',
		    		{noticeMessageTypeId:id},
		    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("启用" + entityName + "成功");
			    			}else{
			    				var errorMessage = $.formatString("启用{0}失败.",entityName);
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
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 125px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>是否有效:</th>
					<td>
						<select id="valid" name="valid" class="select">
							<option value="">-- 不限 --</option>
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
					<th>是否可编辑:</th>
					<td>
						<select id="modifyAble" name="modifyAble" class="select">
							<option value="">-- 不限 --</option>
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>编码：</th>
					<td><input id="code" name="code"/></td>
					<th>名称:</th>
					<td><input id="name" name="name"/></td>
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
		<c:if test='${authContext.hasAuth("add_noticeMessageType") }'>
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
		</c:if>
		<c:if test='${authContext.hasAuth("update_noticeMessageType") }'>
			<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
		</c:if>
		<c:if test='${authContext.hasAuth("delete_noticeMessageType") }'>
			<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
		</c:if>
		<c:if test='${authContext.hasAuth("enable_noticeMessageType") }'>
			<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a>
		</c:if>
		<c:if test='${authContext.hasAuth("disable_noticeMessageType") }'>
			<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>
		</c:if>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>