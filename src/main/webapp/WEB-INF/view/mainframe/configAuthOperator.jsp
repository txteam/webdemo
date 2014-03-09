<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>configPostOperator</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var authItemId = '${authItemId}';
var authItemName = '${authItemName}';
var addAuthItemNames = '${addAuthItemNames}';
var deleteAuthItemNames = '${deleteAuthItemNames}';
var OperatorChooseManager = function(){
};
OperatorChooseManager.prototype._loadChoosedOperatorIds = function(operatorIds,loadCallback){
	var _this = this;
	$.post("${contextPath}/auth/queryChoosedOperatorIdSetByAuthItemId.action", 
			{'authItemId' : authItemId, 'operatorIds[]': operatorIds},function(data){
		loadCallback(data);
	});
};
OperatorChooseManager.prototype.check = function(operatorIds,loadCallback){
	var _this = this;
	if(!operatorIds){
		operatorIds = [];
	}
	_this._loadChoosedOperatorIds(operatorIds,function(data){
		loadCallback(data);
	});
};
var operatorChooseManager = new OperatorChooseManager();

var orgTree = null;
var dataGrid = null;
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
			$('#dataGrid').datagrid('load',{
				organizationId: orgId,
				loginName: loginName,
				userName: userName
			});
		}
	});
	
	//将已经选中的操作员显示为选中状态
	function selectChoosedOperator(){
		var rows = dataGrid.datagrid('getRows');
		
		var operatorIds = [];
		$.each(rows,function(index,rowTemp){
			operatorIds.push(rowTemp.id);
		});
		operatorChooseManager.check(operatorIds,function(choosedOperatorIds){
			$.each(choosedOperatorIds,function(index,choosedOperatorIdTemp){
				$.each(rows,function(index,rowTemp){
					if(choosedOperatorIdTemp == rowTemp.id){
						dataGrid.datagrid('checkRow',index);
					}
				});
			});
		});
	}
	
	dataGrid = $('#dataGrid').datagrid({
		url : '${contextPath}/operator/queryOperatorPagedList.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		checkOnSelect : true,
		selectOnCheck : true,
		nowrap : false,
		striped : true,
		singleSelect : false,
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
		frozenColumns : [[{
			field : 'operatorIdCk',
			title : '',
			width : 180,
			checkbox : true
		}, {
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
		}] ],
		toolbar : '#toolbar',
		onContextMenu : function(e, row) {
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('select', row.id);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		onLoadSuccess : function() {
			selectChoosedOperator();
		}
	});
	
	$("#queryBtn").click(function(){
		$('#dataGrid').datagrid('load',$('#queryForm').serializeObject());
	});
});
function refreshTree(){
	var selectedNode = orgTree.tree('getSelected');
	orgTree.tree('reload');
	$("#organizationId").val('');
	$('#dataGrid').datagrid('load',$('#queryForm').serializeObject());
}
function deselect(){
	var selectedNode = orgTree.tree('getSelected');
	if(selectedNode){
		orgTree.find(".tree-node-selected").removeClass("tree-node-selected");
	}
	$("#organizationId").val('');
	$('#dataGrid').datagrid('load',$('#queryForm').serializeObject());
}
function submitConfigAuthOperator(){
	var checkedRows = dataGrid.datagrid('getChecked');
	var checkedRowsMapping = {};
	$.each(checkedRows,function(index,rowTemp){
		checkedRowsMapping[rowTemp.id] = true;
	});
	var checkedOperatorIds = [];
	var unCheckedOperatorIds = [];
	var rows = dataGrid.datagrid('getRows');
	
	var addOperatorName = "&nbsp;" ;
	var deleteOperatorName = "&nbsp;" ;
	$.each(rows,function(index,rowTemp){
		if(checkedRowsMapping[rowTemp.id]){
			addOperatorName = addOperatorName + (checkedOperatorIds.length == 0 ? "" : "&nbsp;、&nbsp;") + rowTemp.userName;
			checkedOperatorIds.push(rowTemp.id);
		}else{
			deleteOperatorName = deleteOperatorName + (unCheckedOperatorIds.length == 0 ? "" : "&nbsp;、&nbsp;") + rowTemp.userName;
			unCheckedOperatorIds.push(rowTemp.id);
		}
	});
	
	DialogUtils.progress({
        text : '数据提交中，请等待....'
	});
	$.post("${contextPath}/auth/saveAuthItem2OperatorIdList.action",
			{authItemId:authItemId,addRefIds:checkedOperatorIds,deleteRefIds:unCheckedOperatorIds}, 
			function(data){
		DialogUtils.tip("配置权限人员成功");
		DialogUtils.progress('close');
	});
	/*
	DialogUtils.confirm("确认提醒" , 
	    	$.formatString("<br/>请求提交后,系统将会:<br/>为:{0}<br/>添加权限:<br/>{1}<br/>为:{2}<br/>去除权限:<br/>{3}<br/>是否确认?"
	    			,addOperatorName,
	    			authItemName + "," + addAuthItemNames,
	    			deleteOperatorName,
	    			authItemName + "," + deleteAuthItemNames) , 
	    	function(data){
		    	if(data){
		    	}
	    });
	*/
	

}
function cancelConfigAuthOperator(){
	DialogUtils.closeDialogById('configAuthOperator');
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
				<table id="dataGrid"></table>
			</div>
		</div>
		
		<div id="toolbar" style="display: none;">
			<a onclick="dataGrid.datagrid('reload');" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
	</div> 
	
	<div data-options="region:'south',split:false" style="height:31px">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div class="datagrid-toolbar" style="text-align:right;padding-right: 59px">
				<a onclick="submitConfigAuthOperator();" href="javascript:void(0);" class="easyui-linkbutton">提交</a>
				&nbsp;
				<a onclick="cancelConfigAuthOperator()" href="javascript:void(0);" class="easyui-linkbutton">退出</a>
			</div>
		</div>
	</div>
</body>