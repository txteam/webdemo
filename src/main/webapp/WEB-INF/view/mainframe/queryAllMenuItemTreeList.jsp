<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryAllMenuItemTreeList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var treeGrid = null;
$(document).ready(function() {
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/mainframe/getAllMenuItemList.action',
		idField : 'id',
		treeField : 'text',
		iconField : function(item){
			if(item['icon']){
				return item['icon'];
			}else if($.ObjectUtils.isEmpty(item['childs'])){
				return 'database';
			}else{
				return 'database_gear';
			}
		},
		parentField : 'parentId',
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [ {
			title : '编号',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'parentId',
			title : '上级资源ID',
			width : 150,
			hidden : true
		} ] ],
		columns : [ [ {
			field : 'text',
			title : '菜单名称',
			width : 200
		}, {
			field : 'href',
			title : '资源路径',
			width : 230
		}, {
			field : 'target',
			title : '资源类型',
			width : 150
		}, {
			field : 'valid',
			title : '是否有效',
			width : 100,
			formatter : function(value, row, index){
				if(value){
					return '是';
				}else{
					return '否';
				}
			},
			hidden : true
		}, {
			field : 'visibal',
			title : '是否可见',
			width : 100,
			formatter : function(value, row, index){
				if(value){
					return '是';
				}else{
					return '否';
				}
			}
		},{
			field : 'selectRefresh',
			title : '选中刷新',
			width : 100,
			formatter : function(value, row, index){
				if(value){
					return '是';
				}else{
					return '否';
				}
			}
		},{
			field : 'openNewEveryTime',
			title : '存在是否新打开',
			width : 120,
			formatter : function(value, row, index){
				if(value){
					return '是';
				}else{
					return '否';
				}
			},
			hidden : true
		},{
			field : 'eventType',
			title : '事件类型',
			width : 80,
			hidden : true
		}, {
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '';
				if (true) {
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${contextPath}/style/images/extjs_icons/pencil.png');
				}
				str += '&nbsp;';
				if (false) {
					str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="删除"/>', row.id, '${contextPath}/style/images/extjs_icons/cancel.png');
				}
				return str;
			}
		},{
			field : 'remark',
			title : '备注',
			width : 200,
			hidden : true
		}] ],
		toolbar : '#toolbar',
		onContextMenu : function(e, row) {
			e.preventDefault();
			$(this).treegrid('unselectAll');
			$(this).treegrid('select', row.id);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
			$(this).treegrid('tooltip');
		}
	});
});

function editFun(){
	DialogUtils.alert("提醒","暂不支持菜单编辑.","warning");
}
function deleteFun(){
	DialogUtils.alert("提醒","暂不支持菜单删除.");
}

function redo() {
	var node = treeGrid.treegrid('getSelected');
	if (node) {
		treeGrid.treegrid('expandAll', node.id);
	} else {
		treeGrid.treegrid('expandAll');
	}
}

function undo() {
	var node = treeGrid.treegrid('getSelected');
	if (node) {
		treeGrid.treegrid('collapseAll', node.id);
	} else {
		treeGrid.treegrid('collapseAll');
	}
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
			<table id="treeGrid" style="width:fit;height:fit"></table>
		</div>
	</div>
	
	<div id="toolbar" style="display: none;">
		<c:if test="${false}">
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
		</c:if>
		<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
		<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
		<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<c:if test="${true}">
			<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
		</c:if>
		<c:if test="${true}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
		<c:if test="${true}">
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		</c:if>
	</div>
</body>