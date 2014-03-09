<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryAuthView</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var tabs = null;
var $currentAuthTreeGridEl = null;
var authType2AuthItemListMap = null;
$(document).ready(function() {
	
	$.post('${contextPath}/auth/queryAuthType2AuthItemListMap.action',{},function(data){
		authType2AuthItemListMap = data;
		
		//初始化tabs
		tabs = $("#authTabView").tabs({
			fit:true,
			onSelect: function(title,index){
				loadCurrentAuthTree();
			}
		});
		loadCurrentAuthTree();
		function loadCurrentAuthTree(){
			var $tabPannel = $("#authTabView").tabs('getSelected');
			$currentAuthTreeGridEl = $tabPannel.find("table.authTreeGrid");
			
			var isLoad = ($currentAuthTreeGridEl.attr('isLoad') === 'true');
			var isConfigAble = ($currentAuthTreeGridEl.attr('configAble') === 'true');
			var authType = $currentAuthTreeGridEl.attr('authType');

			//如果当前面板树尚未加载，就在此处进行加载
			if(!isLoad){
				initAuthTree($currentAuthTreeGridEl,authType,isConfigAble,authType2AuthItemListMap[authType]);
				$currentAuthTreeGridEl.attr('isLoad','true');
			}
		}
	});
});
//如果tabs初始化
function initAuthTree($authTreeTableEl,authType,configAble,authItemList){
	var tableToolsbarId = '#toolbar_' + authType;
	treeGrid = $authTreeTableEl.treegrid({
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		iconField : function(item){
			return 'database_key';	
		},
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [{
			field : 'parentId',
			title : '上级权限ID',
			width : 150,
			hidden : true
		},{
			field : 'name',
			title : '权限名称'
		}] ],
		columns : [ [ {
			title : '权限key',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'viewAble',
			title : '是否可见',
			width : 100,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'editAble',
			title : '是否可编辑',
			width : 100,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'configAble',
			title : '是否可配置',
			width : 100,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'valid',
			title : '是否有效',
			width : 100,
			formatter : function(value, row, index) {
				return value;
			}
		}, {
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if(!$.ObjectUtils.isEmpty(row.children)
						|| !row.configAble
						|| !row.valid){
					return str;
				}
				if (true) {
					str += $.formatString('<img onclick="configAuthOperator(\'{0}\',\'{1}\');" src="{2}" title="配置权限人员"/>', row.id, row.name,'${contextPath}/style/images/extjs_icons/folder/folder_user.png');
				}
				str += '&nbsp;';
				if (true) {
					str += $.formatString('<img onclick="configAuthPost(\'{0}\',\'{1}\');" src="{2}" title="配置权限职位"/>', row.id, row.name, '${contextPath}/style/images/extjs_icons/group/group.png');
				}
				return str;
			}
		},{
			field : 'description',
			title : '备注',
			width : 300
		} ] ],
		toolbar : tableToolsbarId,
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
		},
		data: authItemList
	});
}
function redo() {
	var node = $currentAuthTreeGridEl.treegrid('getSelected');
	if (node) {
		$currentAuthTreeGridEl.treegrid('expandAll', node.id);
	} else {
		$currentAuthTreeGridEl.treegrid('expandAll');
	}
}
function undo() {
	var node = $currentAuthTreeGridEl.treegrid('getSelected');
	if (node) {
		$currentAuthTreeGridEl.treegrid('collapseAll', node.id);
	} else {
		$currentAuthTreeGridEl.treegrid('collapseAll');
	}
}
function configAuthOperator(id,name){
	if (id == undefined) {
		var rows = $currentAuthTreeGridEl.treegrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("请选择权限项");
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"configAuthOperator",
		$.formatString("配置权限人员_[{0}]",name),
		$.formatString("${contextPath}/auth/toConfigAuthOperator.action?authItemId={0}",id),
		850,550,function(){
		}
	);
}
function configAuthPost(id,name){
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("请选择权限项");
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"configAuthPost",
		$.formatString("配置权限职位_[{0}]",name),
		$.formatString("${contextPath}/auth/toConfigAuthPost.action?authItemId={0}",id),
		850,550,function(){
		}
	);
}
</script>
</head>
<body>
<div id="authTabView" class="easyui-tabs" data-options="fit:true">
<c:forEach items="${authTypeList}" 
	var="authType" varStatus="status">
	<div title="${authType.name}">
		<table class="authTreeGrid"
				data-options="tools:'#tab_tools_${authType.authType}'"
				isLoad="false" 
				authType="${authType.authType}" 
				configAble="${authType.configAble}"></table>
    </div> 
</c:forEach>
</div> 

<c:forEach items="${authTypeList}" var="authType">
<div id="toolbar_${authType.authType}" style="display: none;">
	<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
	<a onclick="undo()" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
	<a onclick="" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>
</c:forEach>

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