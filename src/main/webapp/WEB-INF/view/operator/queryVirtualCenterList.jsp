<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryVirtualCenterList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var treeGrid = null;
$(document).ready(function() {
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/virtualCenter/queryVirtualCenterListByAuth.action',
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		iconField : function(item){
			return 'folder_user';	
		},
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [ {
			title : '虚中心id',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'parentId',
			title : '上级虚中心ID',
			width : 150,
			hidden : true
		}] ],
		columns : [ [ {
			field : 'name',
			title : '名称',
			width : 50,
		},{
			field : 'remark',
			title : '备注',
			width : 150
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
					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row.id, row.name, '${contextPath}/style/images/extjs_icons/pencil_delete.png');
				</c:if>
				str += '&nbsp;';
				return str;
			}
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
	if (id == undefined) {
		var rows = treeGrid.treegrid('getSelections');
		if(!$.ObjectUtils.isEmpty(rows)){
			id = rows[0].id;
		}else{
			id = ''
		}
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"addVirtualCenter",
		"添加虚中心",
		$.formatString("${contextPath}/virtualCenter/toAddVirtualCenter.action?parentVirtualCenterId={0}",id),
		600,175,function(){
		$('#treeGrid').treegrid('reload');
	});
}
function editFun(id) {
	if (id == undefined) {
		var rows = treeGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"updateVirtualCenter",
		"编辑虚中心",
		$.formatString("${contextPath}/virtualCenter/toUpdateVirtualCenter.action?virtualCenterId={0}",id),
		600,175,function(){
			$('#treeGrid').treegrid('reload');
	});
}
function deleteFun(id,name) {
	if (id == undefined) {
		var rows = treeGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	//判断对应组织是否能被停用
	$.post(
		'${contextPath}/virtualCenter/isDeleteAble.action',
		{virtualCenterId: id},
		function(data){
			if(data){
			    DialogUtils.confirm(
			    		"确认提醒" , 
			    		$.formatString("是否确认删除虚中心:[{0}]?",name), 
			    function(data){
			    	if(data){
			    		DialogUtils.progress({
			    	        text : '数据提交中，请等待....'
			    		});
			    		//如果确认删除对应组织
			    		$.post(
					    		'${contextPath}/virtualCenter/deleteVirtualCenterById.action',
					    		{virtualCenterId:id},
					    		function(){
					    			DialogUtils.tip("删除虚中心成功");
					    			$('#treeGrid').treegrid('reload');
					    			DialogUtils.progress('close');
					    });
			    	}
			    });
			}else{
				DialogUtils.alert("提醒","该虚中心关联了其他虚中心或组织不允许被删除。","warning");
			}
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
		<c:if test="${true}">
			<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
		</c:if>
	</div>
</body>