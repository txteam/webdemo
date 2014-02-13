<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryOperatorList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$.canAdd = false;
$.canDelete = false;
$.canDisable = false;
$.canModify = false;
$.canEnable = false;
$.canUnlock = false;
<c:if test='${authContext.hasAuth("add_operator")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_operator")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_operator")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_operator")}'>
	$.canEnable = true;
</c:if>
<c:if test='${authContext.hasAuth("update_operator")}'>
	$.canModify = true;
</c:if>
<c:if test='${authContext.hasAuth("unlock_operator")}'>
	$.canUnlock = true;
</c:if>
var orgTree = null;
var dataGrid = null;
$(document).ready(function() {
	$("#postName").choosePost({
		organizationId : $("#organizationId").val(),
		eventName : "choosePostForQueryOperator",
		contextPath : _contextPath,
		title : "请选择上级组织",
		width : 750,
		height : 400,
		handler : function(post){
			if(post != null){
				$("#postName").val(post.name);
				$("#postId").val(post.id);
			}
		},
		clearHandler: function(){
			$("#postName").val('');
			$("#postId").val('');
		}
	});
	
	orgTree = $('#organizationTree').tree({
		url : '${contextPath}/organization/queryOrganizationList.action',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_user';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$("#organizationId").val(node.id);
			$('#dataGrid').datagrid('reload');
		}
	});
	function deselect(){
		var selectedNode = orgTree.tree('getSelected');
		if(selectedNode){
			orgTree.find(".tree-node-selected").removeClass("tree-node-selected");
		}
		$("#organizationId").val('');
		$('#dataGrid').datagrid('reload');
	}
	function refreshTree(){
		var selectedNode = orgTree.tree('getSelected');
		if(selectedNode){
			orgTree.find(".tree-node-selected").removeClass("tree-node-selected");
		}
		$("#organizationId").val('');
		$('#dataGrid').datagrid('reload');
	}
	
	
	dataGrid = $('#dataGrid').datagrid({
		url : '${contextPath}/operator/queryOperatorPagedListIncludeInvalid.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		/* 分页数据载入 */
		loadFilter: function(data){
			var res = {total:0,rows:[]};
			if(!$.ObjectUtils.isEmpty(data)
					&& !$.ObjectUtils.isEmpty(data.list)){
				res['total'] = data.count;
				res['rows'] = data.list;
			}
			return res;
		}, 
		frozenColumns : [ [ {
			title : '操作员id',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'loginName',
			title : '登录名',
			width : 180
		}]],
		columns : [ [{
			field : 'userName',
			title : '姓名',
			width : 180
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
		},{
			field : 'locked',
			title : '是否锁定',
			width : 100,
			formatter : function(value, row, index) {
				var str = '';
				if(value == true){
					return "是";
				}else{
					return "否";
				}
			}
		},{
			field : 'action',
			title : '操作',
			width : 220,
			formatter : function(value, row, index) {
				var str = '';
				if(!row.locked && $.canUnlock){
					str += $.formatString('<img onclick="unlockFun(\'{0}\',\'{1}\');" src="{2}" title="解锁"/>', row.id, row.name, '${contextPath}/style/images/extjs_icons/control/control_play_blue.png');
					str += '&nbsp;';
				}
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
				if (true) {
					str += $.formatString('<img onclick="configPostAuth(\'{0}\',\'{1}\');" src="{2}" title="配置职位权限"/>', row.id, row.name,'${contextPath}/style/images/extjs_icons/database_key.png');
				}
				return str;
			}
		} ] ],
		toolbar : '#toolbar',
		onContextMenu : function(e, row) {
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('select', row.id);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
		/*,
		onLoadSuccess : function() {
			parent.$.messager.progress('close');
			$(this).treegrid('tooltip');
		}
		*/
	});
	
	$("#queryBtn").click(function(){
		alert($('#queryForm').serialize());
		alert($.toJsonString($('#queryForm').serializeObject()));
		$('#dataGrid').datagrid('reload',$('#queryForm').serializeObject());
	});
});


/*
 * 打开添加操作员界面
 */
function addFun() {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	var organizationId = '';
	var selectOrgRows = orgTree.tree('getSelected');
	if(selectOrgRows && selectOrgRows.id){
		organizationId = selectOrgRows.id;
	}
	GlobalDialogUtils.openModalDialog(
		"addOperator",
		"添加人员",
		$.formatString("${contextPath}/operator/toAddOperator.action?organizationId={1}",organizationId),
		450,220,function(){
		dataGrid.datagrid('reload');
	});
}
/**
 * 打开编辑操作员页面
 */
function editFun(id) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	}
	DialogUtils.openModalDialog(
		"updateOperator",
		"编辑职位",
		$.formatString("${contextPath}/operator/toUpdateOperator.action?operatorId={0}",id),
		450,220,function(){
		dataGrid.datagrid('reload');
	});
}
/*
 * 删除职位
 */
function deleteFun(id,name) {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].loginName;
	}
	//判断对应职位是否能被停用
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除操作员_[{0}]?",name) , 
    	function(data){
	    	if(data){
	    		//如果确认删除对应职位
	    		$.post(
			    		'${contextPath}/operator/deleteOperatorById.action',
			    		{postId:id},
			    		function(){
			    			DialogUtils.tip("删除操作员成功");
			    			$('#treeGrid').treegrid('reload');
			    });
	    	}
    });
}
function disableFun(id,name){
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
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
			    		//如果确认删除对应组织
			    		$.post(
					    		'${contextPath}/post/disablePostById.action',
					    		{postId:id},
					    		function(){
					    			DialogUtils.tip("禁用 职位成功");
					    			$('#dataGrid').datagrid('reload');
					    });
			    	}
			    });
			}else{
				DialogUtils.alert("提醒","该 职位存在尚未禁用的下级 职位，不能被禁用。","warning");
			}
	});
}
function enableFun(id,name){
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
		name = rows[0].name;
	}
	//判断对应组织是否能被停用
    DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用 职位:[{0}]?",name), 
    function(data){
    	if(data){
    		//如果确认删除对应组织
    		$.post(
		    		'${contextPath}/post/enablePostById.action',
		    		{postId:id},
		    		function(){
		    			DialogUtils.tip("启用 职位成功");
		    			$('#dataGrid').datagrid('reload');
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
		400,450,function(){
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
		<ul id="organizationTree"></ul>
	</div> 
		
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit : true,border : false">
			<div data-options="region:'north',title:'查询条件',border:false" 
				style="height: 110px; overflow: hidden;">
				<form method="post" id="queryForm" name="queryForm" class="form">
				<!-- query condition -->
				<input type="hidden" id="organizationId" name="organizationId" value=""/>
				<div>
					<table>
						<tbody>
						<tr>
							<th class="narrow">登录名:</th>
							<td><input name="loginName" class="text" type="text" value=''/></td>
							<th class="narrow">姓名:</th>
							<td><input name="userName" class="text" type="text" value=''/></td>
						</tr>
						<tr>
							<th class="narrow">状态:</th>
							<td>
								<select name="state" class="select">
									<optgroup label=""> 
										<option value="">--- 不限 ---</option>
										<option value="正常">--- 正常 ---</option>
										<option value="锁定">--- 锁定 ---</option>
										<option value="禁用">--- 禁用 ---</option>
									</optgroup>
								</select>
							</td>
							<th class="narrow">职位</th>
							<td>
								<input id="postName" name="postName" class="text" type="text" value=''/ readonly="readonly">
								<input id="postId" name="postId" class="text" type="hidden" value=''/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="button operRow">
								<a id="queryBtn" href="#" class="easyui-linkbutton">查询</a>
								<a id="queryBtn" href="#" class="easyui-linkbutton">查询</a>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				</form>
			</div>
			<div data-options="region:'center',border:false">
				<table id="dataGrid"></table>
			</div>
		</div>
		
		<div id="toolbar" style="display: none;">
			<c:if test='${authContext.hasAuth("add_operator") }'>
				<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
			</c:if>
			<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
			<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
			<a onclick="dataGrid.datagrid('reload');" href="javascript:void(0);" 
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
	
	</div> 
</body>