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
$.canAdd = false;
$.canDelete = false;
$.canDisable = false;
$.canModify = false;
$.canEnable = false;
$.configPostAuth = false;
$.configPostOperator = false;
<c:if test='${authContext.hasAuth("add_post")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_post")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_post")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_post")}'>
	$.canEnable = true;
</c:if>
<c:if test='${authContext.hasAuth("update_post")}'>
	$.canModify = true;
</c:if>
<c:if test='${authContext.hasAuth("config_post_auth")}'>
	$.configPostAuth = true;
</c:if>
<c:if test='${authContext.hasAuth("config_post_operator")}'>
	$.configPostOperator = true;
</c:if>

var treeGrid = null;
var orgTree = null;
$(document).ready(function() {
	orgTree = $('#organizationPostTree').tree({
		url : '${contextPath}/organization/queryOrganizationList.action',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_user';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$('#treeGrid').treegrid('load',{
				organizationId: node.id
			});
		}
	});
	
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/post/queryPostListIncludeInvalid.action',
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		striped : true,
		iconField : function(item){
			return 'group_group';	
		},
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [ {
			title : '职位id',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'parentId',
			title : '上级职位ID',
			width : 150,
			hidden : true
		},{
			field : 'name',
			title : '职位名称',
			width : 200
		}, {
			field : 'code',
			title : '职位编号',
			width : 230
		},{
			field : 'organization',
			title : '所属组织',
			formatter : function(value, row, index) {
				if(value){
					if(value.name){
						return value.name;
					}else{
						return "";
					}
				}else{
					return "";
				}
			}
		}] ],
		columns : [ [{
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
		},{
			field : 'fullName',
			title : '职位全称',
			width : 150,
			hidden : true
		},{
			field : 'remark',
			title : '备注',
			width : 200,
		},{
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				var str = '';
				if(!row.valid && $.canEnable){
					str += $.formatString('<img onclick="enableFun(\'{0}\',\'{1}\');" src="{2}" title="启用"/>', row.id, row.name, '${contextPath}/style/images/extjs_icons/control/control_play_blue.png');
					str += '&nbsp;';
				}
				if($.canModify){
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="编辑"/>', row.id, '${contextPath}/style/images/extjs_icons/pencil.png');
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
				if ($.configPostAuth) {
					str += $.formatString('<img onclick="configPostAuth(\'{0}\',\'{1}\');" src="{2}" title="配置职位权限"/>', row.id, row.name,'${contextPath}/style/images/extjs_icons/database_key.png');
				}
				if ($.configPostOperator) {
					str += $.formatString('<img onclick="configPostOperator(\'{0}\',\'{1}\');" src="{2}" title="配置职位人员"/>', row.id, row.name,'${contextPath}/style/images/extjs_icons/folder/folder_user.png');
				}
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
function refreshTree(){
	var selectedNode = orgTree.tree('getSelected');
	orgTree.tree('reload');
	if(selectedNode){
		treeGrid.treegrid('load',{
			organizationId: '',
			parentPostId: ''
		});

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

/*
 * 打开添加职位界面
 */
function addFun(id) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	var organizationId = '';
	if (id == undefined) {
		var rows = treeGrid.treegrid('getSelections');
		if(!$.ObjectUtils.isEmpty(rows)){
			id = rows[0].id;
		}else{
			id = ''
			var selectOrgRows = orgTree.tree('getSelected');
			if(selectOrgRows && selectOrgRows.id){
				organizationId = selectOrgRows.id;
			}
		}
	}
	DialogUtils.openModalDialog(
		"addPost",
		"添加职位",
		$.formatString("${contextPath}/post/toAddPost.action?parentPostId={0}&organizationId={1}",id,organizationId),
		450,220,function(){
		treeGrid.treegrid('reload');
	});
}
/**
 * 打开编辑职位页面
 */
function editFun(id) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = treeGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	DialogUtils.openModalDialog(
		"updatePost",
		"编辑职位",
		$.formatString("${contextPath}/post/toUpdatePost.action?postId={0}",id),
		450,220,function(){
			$('#treeGrid').treegrid('reload');
	});
}
/*
 * 删除职位
 */
function deleteFun(id,name) {
	if (id == undefined) {
		var rows = treeGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	//判断对应职位是否能被停用
	$.post(
		'${contextPath}/post/isDeleteAble.action',
		{postId: id},
		function(data){
			if(data){
			    DialogUtils.confirm("确认提醒" , 
			    		$.formatString("是否确认删除职位_[{0}]?",name) , 
			    function(data){
			    	if(data){
			    		DialogUtils.progress({
			    	        text : '数据提交中，请等待....'
			    		});
			    		//如果确认删除对应职位
			    		$.post(
					    		'${contextPath}/post/deletePostById.action',
					    		{postId:id},
					    		function(){
					    			DialogUtils.progress('close');
					    			DialogUtils.tip("删除职位成功");
					    			$('#treeGrid').treegrid('reload');
					    });
			    	}
			    });
			}else{
				DialogUtils.alert("提醒",$.formatString("职位 [{0}]存在下级职位，不能被删除。",name),"warning");
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
		'${contextPath}/post/isDisableAble.action',
		{postId: id},
		function(data){
			if(data){
			    DialogUtils.confirm(
			    		"确认提醒" , 
			    		$.formatString("是否确认禁用职位:[{0}]?",name), 
			    function(data){
			    	if(data){
			    		DialogUtils.progress({
			    	        text : '数据提交中，请等待....'
			    		});
			    		//如果确认删除对应组织
			    		$.post(
					    		'${contextPath}/post/disablePostById.action',
					    		{postId:id},
					    		function(){
					    			DialogUtils.progress('close');
					    			DialogUtils.tip("禁用 职位成功");
					    			$('#treeGrid').treegrid('reload');
					    });
			    	}
			    });
			}else{
				DialogUtils.alert("提醒","该 职位存在尚未禁用的下级 职位，不能被禁用。","warning");
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
    		$.formatString("是否确认启用 职位:[{0}]?",name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认删除对应组织
    		$.post(
		    		'${contextPath}/post/enablePostById.action',
		    		{postId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("启用 职位成功");
		    			$('#treeGrid').treegrid('reload');
		    });
    	}
    });
}
/*
 * 配置职位权限
 */
function configPostAuth(id,name){
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("请选择职位");
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"configPostAuth",
		$.formatString("配置职位权限_[{0}]",name),
		$.formatString("${contextPath}/auth/toConfigPostAuth.action?postId={0}",id),
		450,500,function(){
			//$('#treeGrid').treegrid('reload');
			//alert('reload');
		}
	);
}
//配置职位人员
function configPostOperator(id,name){
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("请选择职位");
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"configPostOperator",
		$.formatString("配置职位人员_[{0}]",name),
		$.formatString("${contextPath}/Operator2Post/toConfigPostOperator.action?postId={0}",id),
		850,550,function(){
			//$('#treeGrid').treegrid('reload');
			//alert('reload');
		}
	);
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'组织结构',split:true,
		tools : [
			{ iconCls : 'clear',handler : function() {deselect();} } ,
			{ iconCls : 'database_refresh',handler : function() {refreshTree();} }
		]"
		style="width:230px;">
		<ul id="organizationPostTree"></ul>
	</div> 
	
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
				<table id="treeGrid" style="width:fit;height:fit"></table>
			</div>
		</div>
		
		<div id="toolbar" style="display: none;">
			<c:if test='${authContext.hasAuth("add_post") }'>
				<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
			</c:if>
			<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
			<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
			<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
	
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<c:if test='${authContext.hasAuth("add_post")}'>
				<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
			</c:if>
			<c:if test='${authContext.hasAuth("update_post")}'>
				<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
			</c:if>
			<c:if test='${authContext.hasAuth("delete_post")}'>
				<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
			</c:if>
		</div>
	
	</div> 
</body>
</html>