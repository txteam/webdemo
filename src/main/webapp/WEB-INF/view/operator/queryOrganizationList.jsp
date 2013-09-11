<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryOrganizationList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var treeGrid = null;
$(document).ready(function() {
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/organization/queryOrganizationListIncludeInvalid.action',
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		iconFiled : function(item){
			return 'folder_user';	
		},
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [ {
			title : '组织id',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'parentId',
			title : '上级组织ID',
			width : 150,
			hidden : true
		}] ],
		columns : [ [ {
			field : 'name',
			title : '名称',
			width : 80
		}, {
			field : 'code',
			title : '编号',
			width : 50
		},{
			field : 'alias',
			title : '别名',
			width : 100,
			hidden : true
		}, {
			field : 'fullName',
			title : '全称',
			width : 160
		}, {
			field : 'address',
			title : '地址',
			width : 80,
			hidden : true
		}, {
			field : 'fullAddress',
			title : '详细地址',
			width : 160,
			hidden : true
		}, {
			field : 'valid',
			title : '是否有效',
			width : 40,
			formatter : function(value, row, index){
				if(value){
					return '是';
				}else{
					return '否';
				}
			}
		}, {
			field : 'type',
			title : '组织类型',
			width : 80
		}, {
			field : 'provinceId',
			title : '所在省',
			width : 80,
			hidden : true
		},{
			field : 'cityId',
			title : '所在市',
			width : 80,
			hidden : true
		},{
			field : 'areaId',
			title : '所在地',
			width : 80
		},{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '';
				<c:if test="${true}">
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${contextPath}/style/images/extjs_icons/pencil.png');
				</c:if>
				str += '&nbsp;';
				<c:if test="${true}">
				if(row.valid){
					str += $.formatString('<img onclick="stopFun(\'{0}\');" src="{1}" title="停用"/>', row.id, '${contextPath}/style/images/extjs_icons/control/control_stop_blue.png');
				}else{
					str += $.formatString('<img onclick="stopFun(\'{0}\');" src="{1}" title="启用"/>', row.id, '${contextPath}/style/images/extjs_icons/control/control_play_blue.png');
				}
				</c:if>
				return str;
			}
		}, {
			field : 'remark',
			title : '备注',
			width : 200,
			hidden : true
		} ] ],
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
/*
 * 打开添加组织界面
 */
function addFun(id) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		if(!$.ObjectUtils.isEmpty(rows)){
			id = rows[0].id;
		}else{
			id = ''
		}
	}
	DialogUtils.openModalDialog(
		"addOrganization",
		"添加组织",
		str += $.formatString("${contextPath}/organization/toAddOrganization.action?parentOrganizationId={0}",id),
		550,265,function(){
		$('#treeGrid').treegrid('reload');
	});
}
function editFun(id) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	DialogUtils.openModalDialog(
		"updateOrganization",
		"编辑组织",
		$.formatString("${contextPath}/organization/toUpdateOrganization.action?organizationId={0}",id),
		550,265,function(){
			$('#treeGrid').treegrid('reload');
	});
}
function stopFun(id) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	//判断对应组织是否能被停用
	$.post(
		'${contextPath}/organization/isDisabled.action',
		{});
	
	DialogUtils.openModalDialog(
		"updateOrganization",
		"编辑组织",
		$.formatString("${contextPath}/organization/toUpdateOrganization.action?organizationId={0}",id),
		550,265,function(){
			$('#treeGrid').treegrid('reload');
	});
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
		<c:if test="${true}">
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
			<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
		</c:if>
	</div>
</body>