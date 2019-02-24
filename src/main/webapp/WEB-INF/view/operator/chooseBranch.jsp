<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryPostList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var treeGrid = null;
var orgTree = null;
$(document).ready(function() {
	orgTree = $('#organizationTree').tree({
		url : '${contextPath}/organization/queryBranchCompanyOrganizationList.action?vcid=${includeVcid}',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_user';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$('#treeGrid').treegrid('load',{
				companyId: node.companyId,
				excludeOrganizationId: '${excludeOrganizationId}'
			});
		}
	});
	
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/organization/queryBranchByCompanyId.action',
		queryParams : {
			companyId:'0',
			excludeOrganizationId: '${excludeOrganizationId}'
		},	
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		iconField : function(item){
			return 'group_group';	
		},
		fit : true,
		fitColumns : true,
		border : false,
		checkOnSelect : false,
		selectOnCheck : false,
		singleSelect : true,
		frozenColumns : [ [ {
			title : 'id',
			field : 'id',
			width : 150,
			hidden : true
		}
		]],
		columns : [ [{
			field : 'name',
			title : '分行名称',
			width : 180
		},{
			field : 'code',
			title : '分行编号',
			width : 100
		},{
			field : 'valid',
			title : '是否有效',
			width : 100,
			formatter : function(value, row, index) {
				var str = '';
				if(value == true){
					return "是";
				}else{
					return "否";
				}
			}
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
		},
		onSelect : function(rowData){
			$.triggerge("choose_branch_" + "${eventName}",[rowData]);
		}
	});
});
function refreshTree(){
	var selectedNode = orgTree.tree('getSelected');
	orgTree.tree('reload');
	if(selectedNode){
		treeGrid.treegrid('load',{});
	}
}
function deselect(){
	var selectedNode = orgTree.tree('getSelected');
	if(selectedNode){
		orgTree.find(".tree-node-selected").removeClass("tree-node-selected");
		treeGrid.treegrid('load',{});
	}
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
<body class="easyui-layout">
	<div data-options="region:'west',title:'组织结构',split:true,
		tools : [
			{ iconCls : 'clear',handler : function() {deselect();} } ,
			{ iconCls : 'database_refresh',handler : function() {refreshTree();} }
		]"
		style="width:240px;">
		<ul id="organizationTree"></ul>
	</div> 
	
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
				<table id="treeGrid" style="width:fit;height:fit"></table>
			</div>
		</div>
		
		<div id="toolbar" style="display: none;">
			<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
			<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
			<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
	</div> 
</body>