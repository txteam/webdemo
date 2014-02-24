<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryConfigProperty</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$.canModify = false;
<c:if test='${authContext.hasAuth("update_configProperty")}'>
	$.canModify = true;
</c:if>

var treeGrid = null;
var configGroupTree = null;
$(document).ready(function() {
	//配置属性组树
	configGroupTree = $('#configGroupTree').tree({
		url : '${contextPath}/configuration/getAllConfigPropertyGroup.action',
		idField : 'name',
		parentField : 'parentName',
		iconField : function(){
			return 'folder_wrench';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			//点击配置属性组节点，刷新页面仅加载对应配置属性组下存在的配置属性
			//onlyExpandChild(node.name);
			//configPropertyGroupName
			$('#treeGrid').treegrid('load',{
				configPropertyGroupName: node.name
			});
		}
	});

	
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/configuration/getConfigPropertyTreeNodeList.action',
		idField : 'id',
		childrenField : 'childs',
		treeField : 'name',
		iconField : function(item){
			if(item.type == '0'){
				return 'folder_wrench';
			}else{
				return 'cog';
			}	
		},
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [ {
			title : '配置节点id',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'parentId',
			title : '配置节点上级节点id',
			width : 150,
			hidden : true
		},{
			field : 'name',
			title : '配置项名称',
			width : 200
		}, {
			field : 'type',
			title : '配置项类型',
			width : 230,
			formatter : function(value, row, index) {
				if(row.type == '0'){
					return '&nbsp;';
				}else{
					return row.target.configPropertyType;
				}	
			}
		}, {
			field : 'key',
			title : '配置项key',
			width : 230,
			formatter : function(value, row, index) {
				if(row.type == '0'){
					return '&nbsp;';
				}else{
					return row.target.key;
				}	
			}
		},{
			field : 'value',
			title : '配置项目值',
			formatter : function(value, row, index) {
				if(row.type == '0'){
					return '&nbsp;';
				}else{
					return row.target.value;
				}	
			}
		}]],
		columns : [[{
			field : 'valid',
			title : '是否有效',
			width : 80,
			formatter : function(value, row, index) {
				if(row.type == '0'){
					return '&nbsp;';
				}else{
					if(row.target.valid){
						return "有效";
					}else{
						return "无效";
					}
				}
			}
		},{
			field : 'modifyAble',
			title : '是否可编辑',
			width : 80,
			formatter : function(row, row, index) {
				if(row.type == '0'){
					return '&nbsp;';
				}else{
					if(row.target.modifyAble){
						return "可编辑";
					}else{
						return "不可编辑";
					}
				}
			}
		},{
			field : 'description',
			title : '备注',
			width : 200,
			formatter : function(value, row, index) {
				if(row.type == '0'){
					return '&nbsp;';
				}else{
					return row.target.description;
				}	
			}
		},{
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				if(row.type == '0'){
					return '&nbsp;';
				}else{
					var str = '';
					if($.canModify && row.target.modifyAble){
						str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${contextPath}/style/images/extjs_icons/pencil.png');
						str += '&nbsp;';
					}
					return str;
				}
			}
		} ] ],
		toolbar : '#toolbar',
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
			$(this).treegrid('tooltip');
		}
	});
});

function refreshTree(){
	var selectedNode = configGroupTree.tree('getSelected');
	configGroupTree.tree('reload');
	if(selectedNode){
		treeGrid.treegrid('load',{});
	}
}
function deselect(){
	var selectedNode = configGroupTree.tree('getSelected');
	if(selectedNode){
		configGroupTree.find(".tree-node-selected").removeClass("tree-node-selected");
		treeGrid.treegrid('load',{});
	}
}
function redo() {
	var node = treeGrid.treegrid('getSelected');
	if (node) {
		treeGrid.treegrid('expand', node.id);
	} else {
		treeGrid.treegrid('expandAll');
	}
}
function undo() {
	var node = treeGrid.treegrid('getSelected');
	if (node) {
		treeGrid.treegrid('collapse', node.id);
	} else {
		treeGrid.treegrid('collapseAll');
	}
}
/**
 * 打开编辑职位页面
 */
function editFun(key) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (key == undefined) {
		var rows = treeGrid.datagrid('getSelections');
		key = rows[0].key;
	}
	DialogUtils.openModalDialog(
		"updateConfigProperty",
		"修改配置属性",
		$.formatString("${contextPath}/configuration/toUpdateConfigProperty.action?key={0}",key),
		600,210,function(){
			$('#treeGrid').treegrid('reload');
	});
}
</script>
</head>
<body class="easyui-layout">
	<!-- 配置属性组 -->
	<div data-options="region:'west',title:'配置属性组',split:true,
		tools : [
			{ iconCls : 'clear',handler : function() {deselect();} } ,
			{ iconCls : 'database_refresh',handler : function() {refreshTree();} }
		]"
		style="width:230px;">
		<ul id="configGroupTree"></ul>
	</div>
	
	<!-- 配置属性 -->
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
</html>