<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>configOperatorRole</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var operatorId = '${operatorId}';
var RefChooseManager = function(){
};
RefChooseManager.prototype._isInit = false;
RefChooseManager.prototype._choosedRefIds = {};
RefChooseManager.prototype._loadChoosedRefIds = function(loadCallback){
	var _this = this;
	$.post("${contextPath}/operatorRef/queryRoleIdSetByOperatorId.action?operatorId=${operatorId}", 
		function(data){
			$.each(data,function(i,refIdTemp){
				_this._choosedRefIds[refIdTemp] = true;
			});
			_this._isInit= true;
			loadCallback(_this._choosedRefIds);
		}
	);
};
RefChooseManager.prototype.load = function(loadCallback){
	var _this = this;
	if(_this._isInit){
		loadCallback(_this._choosedRefIds);
	}else{
		_this._loadChoosedRefIds(loadCallback);
	}
};
RefChooseManager.prototype.reset = function(){
	var _this = this;
	_this._choosedRefIds = {};
	_this._isInit= false;
};
var refChooseManager = new RefChooseManager();

var orgTree = null;
var grid = null;
var gridRows = null;
$(document).ready(function() {
	parent.DialogUtils.progress('close');
	
	/* orgTree = $('#organizationRoleTree').tree({
		url : '${contextPath}/organization/queryCompanyOrganizationList.action',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_user';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$('#organizationName').val(node.name);
			$('#organizationId').val(node.id);
			$('#grid').datagrid('load',{
				organizationId: node.id
			});
		}
	}); */
	
	//将已经选中的操作员显示为选中状态
	function selectChoosedRef(){
		refChooseManager.load(function(choosedRefIds){
			var rows = grid.datagrid('getRows');
			$.each(rows,function(index,rowTemp){
				if(choosedRefIds[rowTemp.id]){
					grid.datagrid('checkRow',index);
				}
			});
		});
	}
	
	var virtualCenterMapLoaded = false;
	var virtualCenterMap = {};
	
	grid = $('#grid').datagrid({
		url : '${contextPath}/role/queryRoleListIncludeInvalid.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : true,
		selectOnCheck : true,
		nowrap : false,
		striped : true,
		singleSelect : false,
		onBeforeLoad:function(){
			if(!virtualCenterMapLoaded){
				$.post("${contextPath}/virtualCenter/queryVirtualCenterList.action", function(rows){
					virtualCenterMapLoaded = true;
					if($.ObjectUtils.isEmpty(rows)){
						return ;
					}	
					$.each(rows,function(index,virtualCenterTemp){
						virtualCenterMap[virtualCenterTemp.id] = virtualCenterTemp.name;
					});
				});
			}
		},
		frozenColumns: [[{
			field : 'operatorIdCk',
			title : '',
			width : 180,
			checkbox : true
		}, {
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
			field : 'code',
			title : '编码',
			width : 200
		},
		{
			field : 'valid',
			title : '是否有效',
			width : 200
		},
		{
			field : 'remark',
			title : '备注',
			width : 200
		}
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
			if($.canUpdate){
				editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			
			treegridRows = grid.datagrid('getRows');
			selectChoosedRef();
		}
	});
	
	$("#queryBtn").click(function(){
		grid.datagrid('load',$('#queryForm').serializeObject());
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
function submitConfigOperatorRole(){
	DialogUtils.progress({
        text : '数据提交中，请等待....'
	});
	var checkedRows = grid.datagrid('getChecked');
	var checkedRowsMapping = {};
	$.each(checkedRows,function(index,rowTemp){
		checkedRowsMapping[rowTemp.id] = true;
	});
	var checkedRoleIds = [];
	var unCheckedRoleIds = [];
	var rows = treegridRows;
	$.each(rows,function(index,rowTemp){
		if(checkedRowsMapping[rowTemp.id]){
			checkedRoleIds.push(rowTemp.id);
		}else{
			unCheckedRoleIds.push(rowTemp.id);
		}
	});
	$.post("${contextPath}/operatorRef/configOperatorRoleId.action",
			{operatorId:operatorId,selectedRoleId:checkedRoleIds,unSelectedRoleId:unCheckedRoleIds}, 
			function(data){
		refChooseManager.reset();
		DialogUtils.tip("配置人员角色成功");
		DialogUtils.progress('close');
	});
}
function cancelConfigOperatorRole(){
	DialogUtils.closeDialogById('configOperatorRole');
}
</script>
</head>
<body class="easyui-layout">
	<%-- <c:if test="${organizationId == null || organizationId == ''}">
		<div data-options="region:'west',title:'组织结构',split:true,
			tools : [
				{ iconCls : 'clear',handler : function() {deselect();} } ,
				{ iconCls : 'database_refresh',handler : function() {refreshTree();} }
			]"
			style="width:230px;">
			<ul id="organizationRoleTree"></ul>
		</div> 
	</c:if> --%>
		
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
				<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
					class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
			</div>
		</div>
	</div> 
	
	<div data-options="region:'south',split:false" style="height:31px">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div class="datagrid-toolbar" style="text-align:right;padding-right: 59px">
				<a onclick="submitConfigOperatorRole();" href="javascript:void(0);" class="easyui-linkbutton">提交</a>
				&nbsp;
				<a onclick="cancelConfigOperatorRole()" href="javascript:void(0);" class="easyui-linkbutton">退出</a>
			</div>
		</div>
	</div>
</body>