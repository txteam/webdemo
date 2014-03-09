<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryOrganizationList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$.canAdd = false;
$.canDelete = false;
$.canDisable = false;
$.canModify = false;
$.canEnable = false;
<c:if test='${authContext.hasAuth("add_organization")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_organization")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_organization")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_organization")}'>
	$.canEnable = true;
</c:if>
<c:if test='${authContext.hasAuth("update_organization")}'>
	$.canModify = true;
</c:if>

var virtualCenterTree = null;
var treeGrid = null;
$(document).ready(function() {
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	var  $enableALink = $("#enableALink");
	var  $disableALink = $("#disableALink");
	/*
	virtualCenterTree = $('#virtualCenterTree').tree({
		url : '${contextPath}/virtualCenter/queryVirtualCenterListByAuth.action',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_user';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$('#treeGrid').treegrid('load',{
				virtualCenterId: node.id
			});
		}
	});
	*/
	
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/organization/queryOrganizationListIncludeInvalid.action',
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		striped : true,
		iconField : function(item){
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
			field : 'type',
			title : '组织类型',
			width : 80
		}, {
			field : 'valid',
			title : '是否有效',
			width : 80,
			formatter : function(value, row, index) {
				var str = '';
				if(value == '1'){
					return "有效";
				}else{
					return "无效";
				}
			}
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
		}
		<c:if test="${show_grid_action == true}">
		,{
			field : 'action',
			title : '操作',
			width : 50,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if(!row.valid && $.canEnable){
					str += $.formatString('<img onclick="enableFun(\'{0}\',\'{1}\');" src="{2}" title="启用"/>', row.id, row.name, '${contextPath}/style/images/extjs_icons/control/control_play_blue.png');
					str += '&nbsp;';
				}
				if($.canModify){
					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row.id, row.name, '${contextPath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;';
				}
				if($.canDelete){
					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row.id, row.name, '${contextPath}/style/images/extjs_icons/pencil_delete.png');
					str += '&nbsp;';
				}
				if(row.valid && $.canDisable){
					str += $.formatString('<img onclick="disableFun(\'{0}\',\'{1}\');" src="{2}" title="禁用"/>', row.id, row.name, '${contextPath}/style/images/extjs_icons/control/control_stop_blue.png');
					str += '&nbsp;';
				}
				return str;
			}
		}
		</c:if>
		, {
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
		onDblClickRow : function(row){
			editFun(row.id, row.name);
		},
		onClickRow: function(row){
			$editALink.linkbutton('enable');
			$deleteALink.linkbutton('enable');
			
			if(row.valid){
				$enableALink.linkbutton('disable');
				$enableALink.hide();
				$disableALink.show();
				$disableALink.linkbutton('enable');
			}else{
				$disableALink.linkbutton('disable');
				$disableALink.hide();
				$enableALink.show();
				$enableALink.linkbutton('enable');
			}
		},
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
			$(this).treegrid('tooltip');
			
			$editALink.linkbutton('disable');
			$deleteALink.linkbutton('disable');
			
			$enableALink.show();
			$disableALink.show();
			$enableALink.linkbutton('disable');
			$disableALink.linkbutton('disable');
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
		var rows = treeGrid.treegrid('getSelections');
		if(!$.ObjectUtils.isEmpty(rows)){
			id = rows[0].id;
		}else{
			id = ''
		}
	}
	DialogUtils.openModalDialog(
		"addOrganization",
		"添加组织",
		$.formatString("${contextPath}/organization/toAddOrganization.action?parentOrganizationId={0}",id),
		600,275,function(){
		$('#treeGrid').treegrid('reload');
	});
}
function editFun(id) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = treeGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	DialogUtils.openModalDialog(
		"updateOrganization",
		"编辑组织",
		$.formatString("${contextPath}/organization/toUpdateOrganization.action?organizationId={0}",id),
		600,275,function(){
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
		'${contextPath}/organization/isDeleteAble.action',
		{organizationId: id},
		function(data){
			if(data){
			    DialogUtils.confirm(
			    		"确认提醒" , 
			    		$.formatString("是否确认删除组织:[{0}]?",name), 
			    function(data){
			    	if(data){
			    		DialogUtils.progress({
			    	        text : '数据提交中，请等待....'
			    		});
			    		//如果确认删除对应组织
			    		$.post(
					    		'${contextPath}/organization/deleteOrganizationById.action',
					    		{organizationId:id},
					    		function(){
					    			DialogUtils.progress('close');
					    			DialogUtils.tip("删除组织成功");
					    			$('#treeGrid').treegrid('reload');
					    });
			    	}
			    });
			}else{
				DialogUtils.alert("提醒","该组织存在下级组织，不能被删除。","warning");
			}
	});
}
function disableFun(id,name){
	if (id == undefined) {
		var rows = treeGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	//判断对应组织是否能被停用
	$.post(
		'${contextPath}/organization/isDisableAble.action',
		{organizationId: id},
		function(data){
			if(data){
			    DialogUtils.confirm(
			    		"确认提醒" , 
			    		$.formatString("是否确认禁用组织:[{0}]?",name), 
			    function(data){
			    	if(data){
			    		DialogUtils.progress({
			    	        text : '数据提交中，请等待....'
			    		});
			    		//如果确认删除对应组织
			    		$.post(
					    		'${contextPath}/organization/disableOrganizationById.action',
					    		{organizationId:id},
					    		function(){
					    			DialogUtils.progress('close');
					    			DialogUtils.tip("禁用组织成功");
					    			$('#treeGrid').treegrid('reload');
					    });
			    	}
			    });
			}else{
				DialogUtils.alert("提醒","该组织存在尚未禁用的下级组织，不能被禁用。","warning");
			}
	});
}
function enableFun(id,name){
	if (id == undefined) {
		var rows = treeGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	//判断对应组织是否能被停用
    DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用组织:[{0}]?",name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认删除对应组织
    		$.post(
		    		'${contextPath}/organization/enableOrganizationById.action',
		    		{organizationId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("启用组织成功");
		    			$('#treeGrid').treegrid('reload');
		    });
    	}
    });
}
function deselect(){
	var selectedNode = virtualCenterTree.tree('getSelected');
	if(selectedNode){
		virtualCenterTree.find(".tree-node-selected").removeClass("tree-node-selected");
		treeGrid.treegrid('load',{});
	}
}
function refreshTree(){
	var selectedNode = virtualCenterTree.tree('getSelected');
	virtualCenterTree.tree('reload');
	if(selectedNode){
		treeGrid.treegrid('load',{
			organizationId: '',
			parentPostId: ''
		});
	}
}

</script>
</head>
<body>

</body>

<body class="easyui-layout">	
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
				<table id="treeGrid" style="width:fit;height:fit"></table>
			</div>
		</div>
		
		<div id="toolbar" style="display: none;">
			<c:if test='${authContext.hasAuth("add_organization") }'>
				<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
			</c:if>
			<c:if test='${authContext.hasAuth("update_organization") }'>
				<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
			</c:if>
			<c:if test='${authContext.hasAuth("delete_organization") }'>
				<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
			</c:if>
			<c:if test='${authContext.hasAuth("enable_organization") }'>
				<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a>
			</c:if>
			<c:if test='${authContext.hasAuth("disable_organization") }'>
				<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>
			</c:if>
			<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
			<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
			<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
	</div> 
</body>