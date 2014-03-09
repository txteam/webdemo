<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>configOperatorPost</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var operatorId = '${operatorId}';
var PostChooseManager = function(){
};
PostChooseManager.prototype._isInit = false;
PostChooseManager.prototype._choosedPostds = {};
PostChooseManager.prototype._loadChoosedPostIds = function(loadCallback){
	var _this = this;
	$.post("${contextPath}/Operator2Post/queryPostIdSetByOperatorId.action?operatorId=${operatorId}", 
		function(data){
			$.each(data,function(i,postIdTemp){
				_this._choosedPostds[postIdTemp] = true;
			});
			_this._isInit= true;
			loadCallback(_this._choosedPostds);
		}
	);
};
PostChooseManager.prototype.load = function(loadCallback){
	var _this = this;
	if(_this._isInit){
		loadCallback(_this._choosedPostds);
	}else{
		_this._loadChoosedPostIds(loadCallback);
	}
};
PostChooseManager.prototype.reset = function(){
	var _this = this;
	_this._choosedPostds = {};
	_this._isInit= false;
};
var postChooseManager = new PostChooseManager();

var orgTree = null;
var treeGrid = null;
var treegridRows = null;
$(document).ready(function() {
	parent.DialogUtils.progress('close');
	
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
			$('#organizationId').val(node.id);
			var orgId = $('#organizationId').val();
			var loginName = $('#loginName').val();
			var userName =  $('#userName').val();
			$('#treeGrid').treegrid('load',{
				organizationId: orgId,
				loginName: loginName,
				userName: userName
			});
		}
	});
	
	//将已经选中的操作员显示为选中状态
	function selectChoosedPost(){
		postChooseManager.load(function(choosedOperatorIds){
			var rows = treegridRows;
			$.each(rows,function(index,rowTemp){
				if(choosedOperatorIds[rowTemp.id]){
					treeGrid.treegrid('checkRow',rowTemp.id);
				}
			});
		});
	}
	
	treeGrid = $('#treeGrid').treegrid({
		url : '${contextPath}/post/queryPostList.action',
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : false,
		striped : true,
		iconField : function(item){
			return 'group_group';	
		},
		loadFilter : function(data){
			if($.ObjectUtils.isEmpty(data)){
				treegridRows = [];
			}else{
				treegridRows = data;
			}
			return $.fn.treegrid.defaults.loadFilter.call(this,data);
		},
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [ {
			field : 'operatorIdCk',
			title : '',
			width : 180,
			checkbox : true
		},{
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
			treeGrid.treegrid('tooltip');
			selectChoosedPost();
		}
	});
	
	$("#queryBtn").click(function(){
		$('#treeGrid').treegrid('load',$('#queryForm').serializeObject());
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

function refreshTree(){
	var selectedNode = orgTree.tree('getSelected');
	orgTree.tree('reload');
	$("#organizationId").val('');
	$('#treeGrid').treegrid('load',$('#queryForm').serializeObject());
}
function deselect(){
	var selectedNode = orgTree.tree('getSelected');
	if(selectedNode){
		orgTree.find(".tree-node-selected").removeClass("tree-node-selected");
	}
	$("#organizationId").val('');
	$('#treeGrid').treegrid('load',$('#queryForm').serializeObject());
}
function submitConfigOperatorPost(){
	DialogUtils.progress({
        text : '数据提交中，请等待....'
	});
	var checkedRows = treeGrid.treegrid('getChecked');
	var checkedRowsMapping = {};
	$.each(checkedRows,function(index,rowTemp){
		checkedRowsMapping[rowTemp.id] = true;
	});
	var checkedPostIds = [];
	var unCheckedPostIds = [];
	var rows = treegridRows;
	$.each(rows,function(index,rowTemp){
		if(checkedRowsMapping[rowTemp.id]){
			checkedPostIds.push(rowTemp.id);
		}else{
			unCheckedPostIds.push(rowTemp.id);
		}
	});
	$.post("${contextPath}/Operator2Post/configOperatorPostId.action",
			{operatorId:operatorId,selectedPostId:checkedPostIds,unSelectedPostId:unCheckedPostIds}, 
			function(data){
		postChooseManager.reset();
		DialogUtils.tip("配置人员职位成功");
		DialogUtils.progress('close');
	});
}
function cancelConfigOperatorPost(){
	DialogUtils.closeDialogById('configOperatorPost');
}
</script>
</head>
<body class="easyui-layout">
	<c:if test="${organizationId == null || organizationId == ''}">
		<div data-options="region:'west',title:'组织结构',split:true,
			tools : [
				{ iconCls : 'clear',handler : function() {deselect();} } ,
				{ iconCls : 'database_refresh',handler : function() {refreshTree();} }
			]"
			style="width:180px;">
			<ul id="organizationTree"></ul>
		</div>
	</c:if>
		
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit : true,border : false">
			<div data-options="region:'north',title:'查询条件',border:false" 
				style="height: 85px; overflow: hidden;">
				<form method="post" id="queryForm" name="queryForm" class="form">
				<input id="organizationId" type="hidden" value="" name="organizationId" />
				<!-- query condition -->
				<div>
					<table>
						<tbody>
						<tr>
							<th class="narrow">登录名:</th>
							<td><input id="loginName" name="loginName" class="text" type="text" value=''/></td>
							<th class="narrow">姓名:</th>
							<td><input id="userName" name="userName" class="text" type="text" value=''/></td>
						</tr>
						<tr>
							<td colspan="4" class="button operRow">
								<a id="queryBtn" href="#" class="easyui-linkbutton">查询</a>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				</form>
			</div>
			<div data-options="region:'center',border:false">
				<table id="treeGrid"></table>
			</div>
		</div>
		
		<div id="toolbar" style="display: none;">
			<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
			<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
			<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
	</div> 
	
	<div data-options="region:'south',split:false" style="height:31px">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div class="datagrid-toolbar" style="text-align:right;padding-right: 59px">
				<a onclick="submitConfigOperatorPost();" href="javascript:void(0);" class="easyui-linkbutton">提交</a>
				&nbsp;
				<a onclick="cancelConfigOperatorPost()" href="javascript:void(0);" class="easyui-linkbutton">退出</a>
			</div>
		</div>
	</div>
</body>