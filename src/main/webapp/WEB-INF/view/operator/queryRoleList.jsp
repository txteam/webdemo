<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryRoleList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" charset="utf-8"
	src="${contextPath}/js/jquery-easyui-datagridview/datagrid-groupview.js"></script>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
$.canDisable = false;
$.canEnable = false;
$.configRoleAuth = false;
$.configRoleOperator = false;
<c:if test='${authContext.hasAuth("add_role")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_role")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_role")}'>
	$.canUpdate = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_role")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_role")}'>
	$.canEnable = true;
</c:if>
<c:if test='${authContext.hasAuth("config_role_auth")}'>
	$.configRoleAuth = true;
</c:if>
<c:if test='${authContext.hasAuth("config_role_operator")}'>
	$.configRoleOperator = true;
</c:if>

var vcTree = null;
var grid = null;
var idFieldName = 'id';
var nameFieldName = 'name'; 
var entityName = '角色'; 

$(document).ready(function(){
	vcTree = $('#virtualCenterTree').tree({
		url : '${contextPath}/virtualCenter/queryVirtualCenterList.action',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_database';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$('#virtualCenterName').val(node.name);
			$('#vcid').val(node.id);
			$('#grid').datagrid('load',{
				vcid: $("#vcid").val(),
				name: $("#name").val(),
				code: $("#code").val(),
			});
		}
	});
	
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	var  $enableALink = $("#enableALink");
	var  $disableALink = $("#disableALink");
	
	var  $configRoleAuthALink = $("#configRoleAuthALink");
	var  $configRoleOperatorALink = $("#configRoleOperatorALink");
	
	var virtualCenterMapLoaded = false;
	var virtualCenterMap = {};

	grid = $('#grid').datagrid({
		url : '${contextPath}/role/queryRoleListIncludeInvalid.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		onBeforeLoad:function(){
			if(!virtualCenterMapLoaded){
				$.post("${contextPath}/virtualCenter/queryVirtualCenterList.action", function(rows){
					virtualCenterLoaded = true;
					if($.ObjectUtils.isEmpty(rows)){
						return ;
					}	
					$.each(rows,function(index,virtualCenterTemp){
						virtualCenterMap[virtualCenterTemp.id] = virtualCenterTemp.name;
					});
				});
			}
		},
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
		{
			field : 'name',
			title : '名称',
			width : 200
		},
		{
			field : 'vcid',
			title : '所属业务中心',
			width : 200,
			formatter : function(value, row, index) {
				if(virtualCenterMap[row.vcid]){
					return virtualCenterMap[row.vcid];
				}else{
					return row.vcid;
				}
			}
		},
		{
			field : 'roleType',
			title : '角色类型',
			width : 200
		},
		{
			field : 'code',
			title : '编码',
			width : 200
		},
		{
			field : 'valid',
			title : '是否有效',
			width : 200,
			formatter : function(value, row, index) {
				if(value == true){
					return "是";
				}else{
					return "否";
				}
			}
		},
		{
			field : 'remark',
			title : '备注',
			width : 200
		},
		{
			field : 'roleKey',
			title : '角色KEY',
			width : 200
		},
		{
			field : 'action',
			title : '操作',
			width : 220,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if(row.editAble && !row.valid && $.canEnable){
					str += $.formatString('<img onclick="enableFun(\'{0}\',\'{1}\');" src="{2}" title="启用"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/control/control_play_blue.png');
					str += '&nbsp;';
				}
				if(row.editAble && $.canUpdate){
					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;';
				}
				
				if(row.editAble && $.canDelete){
					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil_delete.png');
					str += '&nbsp;';
				}
				if(row.editAble && row.valid && $.canDisable){
					str += $.formatString('<img onclick="disableFun(\'{0}\',\'{1}\');" src="{2}" title="禁用"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/control/control_stop_blue.png');
					str += '&nbsp;';
				}
				if ($.configRoleAuth) {
					str += $.formatString('<img onclick="configRoleAuth(\'{0}\',\'{1}\');" src="{2}" title="配置职位权限"/>', row.id, row.name,'${contextPath}/style/images/extjs_icons/database_key.png');
				}
				if ($.configRoleOperator) {
					str += $.formatString('<img onclick="configRoleOperator(\'{0}\',\'{1}\');" src="{2}" title="配置职位人员"/>', row.id, row.name,'${contextPath}/style/images/extjs_icons/folder/folder_user.png');
				}
				return str;
			}
		}
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(row){
			if($.canUpdate){
				editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onClickRow : function(row){
			if(!row.editAble){
				$editALink.linkbutton('disable');
				$deleteALink.linkbutton('disable');
				$enableALink.linkbutton('disable');
				$disableALink.linkbutton('disable');
				
				$configRoleAuthALink.linkbutton('enable');;
				$configRoleOperatorALink.linkbutton('enable');
			}else{
				$editALink.linkbutton('enable');
				$deleteALink.linkbutton('enable');
				$configRoleAuthALink.linkbutton('enable');;
				$configRoleOperatorALink.linkbutton('enable');
				
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
			}
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			
			$editALink.linkbutton('disable');
			$deleteALink.linkbutton('disable');

			$enableALink.show();
			$disableALink.show();
			$enableALink.linkbutton('disable');
			$disableALink.linkbutton('disable');
			
			$configRoleAuthALink.linkbutton('disable');
			$configRoleOperatorALink.linkbutton('disable');
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
		"addRole",
		"新增" + entityName,
		$.formatString("${contextPath}/role/toAddRole.action"),
		450,220,function(){
			grid.datagrid('load',$('#queryForm').serializeObject());
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
		"updateRole",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/role/toUpdateRole.action?roleId={0}",id),
		450,220,function(){
			grid.datagrid('load',$('#queryForm').serializeObject());
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
	//判断是否确认删除指定的Role
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的Role
	    		$.post(
			    		'${contextPath}/role/deleteRoleById.action',
			    		{roleId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("删除" + entityName + "成功");
			    			}else{
			    				$.formatString("删除{0}:{1}失败.指定{0}可能已经被其他管理员所删除.如果指定{0}依然存在，请联系系统管理员.",entityName,name);
			    			}
			    			grid.datagrid('load',$('#queryForm').serializeObject());
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
	//判断是否确认禁用指定Role
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认禁用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认禁用指定Role
    		$.post(
		    		'${contextPath}/role/disableRoleById.action',
		    		{roleId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("禁用" + entityName + "成功");
		    			grid.datagrid('reload',$('#queryForm').serializeObject());
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
	//判断是否确认禁用指定Role
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定Role
    		$.post(
		    		'${contextPath}/role/enableRoleById.action',
		    		{roleId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("启用" + entityName + "成功");
		    			grid.datagrid('reload',$('#queryForm').serializeObject());
		    });
    	}
    });
    return false;
}
/*
 * 配置角色权限
 */
function configRoleAuth(id,name){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的角色");
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"configRoleAuth",
		$.formatString("配置角色权限_[{0}]",name),
		$.formatString("${contextPath}/auth/toConfigRoleAuth.action?roleId={0}",id),
		450,500,function(){
			//$('#treeGrid').treegrid('reload');
			//alert('reload');
		}
	);
}
//配置职位人员
function configRoleOperator(id,name){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的职位");
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"configPostOperator",
		$.formatString("配置角色人员_[{0}]",name),
		$.formatString("${contextPath}/operatorRef/toConfigRoleOperator.action?roleId={0}",id),
		850,550,function(){
			//$('#treeGrid').treegrid('reload');
			//alert('reload');
		}
	);
}
function refreshTree(){
	var selectedNode = vcTree.tree('getSelected');
	vcTree.tree('reload');
	if(selectedNode){
		grid.datagrid('load',{
			vcid: $("#vcid").val(),
			name: $("#name").val(),
			code: $("#code").val(),
		});
	}
}
function deselect(){
	$('#virtualCenterName').val('');
	$('#vcid').val('');
	var selectedNode = vcTree.tree('getSelected');
	if(selectedNode){
		vcTree.find(".tree-node-selected").removeClass("tree-node-selected");
		grid.datagrid('load',{
			name: $("#name").val(),
			code: $("#code").val(),
		});
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
		style="width:230px;">
		<ul id="virtualCenterTree"></ul>
	</div>
	
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit : true,border : false">
			<!--//FIXME: 修改查询条件框体高度 -->
			<div data-options="region:'north',title:'查询条件',border:false" style="height: 90px; overflow: hidden;">
				<form id="queryForm" class="form">
					<table class="table table-hover table-condensed">
						<tr>
							<th>名称:</th>
							<td><input id="name" name="name"/></td>
							<th>编码:</th>
							<td><input id="code" name="code"/></td>
							<th>虚中心:</th>
							<td>
								<input id="vcid" name="vcid" type="hidden"/>
								<input id="virtualCenterName" name="virtualCenterName" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<td colspan="6" class="button operRow">
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
				<c:if test='${authContext.hasAuth("add_role") }'>
					<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
				</c:if>
				<c:if test='${authContext.hasAuth("update_role") }'>
					<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
				</c:if>
				<c:if test='${authContext.hasAuth("delete_role") }'>
					<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
				</c:if>
				<c:if test='${authContext.hasAuth("enable_role") }'>
					<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a>
				</c:if>
				<c:if test='${authContext.hasAuth("disable_role") }'>
					<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>
				</c:if>
				<c:if test='${authContext.hasAuth("config_role_auth") }'>
					<a id="configRoleAuthALink" onclick="configRoleAuth();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'database_key'">配置角色权限</a>
				</c:if>
				<c:if test='${authContext.hasAuth("config_role_operator") }'>
					<a id="configRoleOperatorALink" onclick="configRoleOperator();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'folder_user'">配置角色人员</a>
				</c:if>
				<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
					class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
			</div>
		</div>
	</div> 
</body>