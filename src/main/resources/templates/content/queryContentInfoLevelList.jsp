<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryContentInfoLevelList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
$.canDisable = false;
$.canEnable = false;
<c:if test='${authContext.hasAuth("add_contentInfoLevel")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_contentInfoLevel")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_contentInfoLevel")}'>
	$.canUpdate = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_contentInfoLevel")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_contentinfolevel")}'>
	$.canEnable = true;
</c:if>

var typeTree = null;
var grid = null;
var idFieldName = 'id';
var nameFieldName = 'name';
var entityName = '内容等级'; 

$(document).ready(function(){
	typeTree = $('#typeTree').tree({
		url : '${contextPath}/contentInfoCategory/queryList.action?valid=true',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_database';
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$('#categoryCode').val(node.code);
			queryFun();
		}
	});
	
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	var  $enableALink = $("#enableALink");
	var  $disableALink = $("#disableALink");
	grid = $('#grid').datagrid({
		url : '${contextPath}/contentInfoLevel/queryList.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		frozenColumns: [[
		{
			field : 'id',
			title : '主键',
			width : 150,
			hidden : true
		}
		]],
		columns: [[
        {
	        field : 'name',
	        title : '名称',
	        width : 200
        },
		{
			field : 'code',
			title : '编码',
			width : 200
		},
		{
			field : 'categoryCode',
			title : '内容类型编码',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if(row['category']){
	   				text = row['category'].code;
	   			}
	   			return text;
			}
		},
		{
			field : 'categoryName',
			title : '内容类型名称',
			width : 200
			,formatter: function(value, row, index){
				var text = '';
	   			if(row['category']){
	   				text = row['category'].name;
	   			}
	   			return text;
			}
		},
		{
			field : 'modifyAble',
			title : '是否可编辑',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var text = cellvalue ? "是" : "否";
	   			return text;
			}
		},
		{
			field : 'valid',
			title : '是否有效',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var text = cellvalue ? "<font color='green'>是</font>" : "<font color='red'>否</font>";
	   			return text;
			}
		},
		{
			field : 'remark',
			title : '备注',
			width : 200
		},
		{
			field : 'createDate',
			title : '创建日期',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		},
		{
			field : 'lastUpdateDate',			
			title : '最后更新日期',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		}
		<c:if test="${show_grid_action == true}">
		,{
			field : 'action',
			title : '操作',
			width : 150,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if(row.modifyAble && !row.valid && $.canEnable){
					str += $.formatString('<img onclick="enableFun(\'{0}\',\'{1}\');" src="{2}" title="启用"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/control/control_play_blue.png');
					str += '&nbsp;';
				}
				if(row.modifyAble && $.canUpdate){
					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;';
				}
				if(row.modifyAble && $.canDelete){
					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil_delete.png');
					str += '&nbsp;';
				}
				if(row.modifyAble && row.valid && $.canDisable){
					str += $.formatString('<img onclick="disableFun(\'{0}\',\'{1}\');" src="{2}" title="禁用"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/control/control_stop_blue.png');
					str += '&nbsp;';
				}
				return str;
			}
		}
		</c:if>	
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
			if(row.modifyAble && $.canUpdate){
				editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onClickRow : function(index, row){
			if(row.modifyAble){
				$editALink.show();
				$deleteALink.show();
				$enableALink.show();
				$disableALink.show();
				
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
			}else{
				$editALink.linkbutton('disable');
				$deleteALink.linkbutton('disable');
				$enableALink.linkbutton('disable');
				$disableALink.linkbutton('disable');
				$editALink.hide();
				$deleteALink.hide();
				$enableALink.hide();
				$disableALink.hide();
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
	DialogUtils.openModalDialog(
		"addContentInfoLevel",
		"新增" + entityName,
		$.formatString("${contextPath}/contentInfoLevel/toAddContentInfoLevel.action"),
		550,220,function(){
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
	DialogUtils.openModalDialog(
		"updateContentInfoLevel",
		"编辑" + entityName,
		$.formatString("${contextPath}/contentInfoLevel/toUpdateContentInfoLevel.action?contentInfoLevelId={0}",id),
		550,220,function(){
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
	//判断是否确认删除指定的ContentInfoLevel
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的ContentInfoLevel
	    		$.post(
			    		'${contextPath}/contentInfoLevel/deleteById.action',
			    		{contentInfoLevelId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("删除" + entityName + "成功");
			    			}else{
			    				var errorMessage = $.formatString("删除{0}:{1}失败.数据可能已经被处理.如有疑问，请联系系统管理员.",entityName,name);
			    				DialogUtils.alert("");
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
	//判断是否确认禁用指定ContentInfoLevel
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认禁用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认禁用指定ContentInfoLevel
    		$.post(
		    		'${contextPath}/contentInfoLevel/disableById.action',
		    		{contentInfoLevelId:id},
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
	//判断是否确认禁用指定ContentInfoLevel
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定ContentInfoLevel
    		$.post(
		    		'${contextPath}/contentInfoLevel/enableById.action',
		    		{contentInfoLevelId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("启用" + entityName + "成功");
		    			grid.datagrid('reload',$('#queryForm').serializeObject());
		    });
    	}
    });
    return false;
}
function refreshTree(){
	var selectionsNode = typeTree.tree('getSelected');
	typeTree.tree('reload');
	if(selectionsNode){
		typeTree.find(".tree-node-selected").removeClass("tree-node-selected");
		$('#categoryCode').val('');
		queryFun();
	}
}
function deselect(){
	var selectionsNode = typeTree.tree('getSelected');
	typeTree.tree('reload');
	if(selectionsNode){
		typeTree.find(".tree-node-selected").removeClass("tree-node-selected");
		$('#categoryCode').val('');
		queryFun();
	}
}
</script>
</head>
<body class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'west',title:'内容类型',split:true,
		tools : [
			{ iconCls : 'clear',handler : function() {deselect();} } ,
			{ iconCls : 'database_refresh',handler : function() {refreshTree();} }
		]"
		style="width:230px;">
		<ul id="typeTree"></ul>
	</div>
	
	
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit : true,border : false">
			<div data-options="region:'north',title:'查询条件',border:false" style="height: 120px; overflow: hidden;">
				<form id="queryForm" class="form">
					<table class="table table-hover table-condensed">
					<tr>
						<th>名称</th>
						<td><input id="name" name="name"/></td>
						<th>编码</th>
						<td><input id="code" name="code"/></td>
					</tr>
					<tr>
						<th>是否有效</th>
						<td>
							<select id="valid" name="valid" class="select">
								<option value="">-- 不限 --</option>
								<option value="true">-- 是 --</option>
								<option value="false">-- 否 --</option>
							</select>
						</td>
						<th>内容信息类型</th>
						<td>
							<select id="categoryCode" name="categoryCode">
								<option value="">-- 不限 --</option>
								<c:forEach items="${categories }" var="category">
									<option value="${category.code }">${category.name} (${category.code})</option>
								</c:forEach>
							</select>
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
				<c:if test='${authContext.hasAuth("add_contentInfoLevel") }'>
					<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
				</c:if>
				<c:if test='${authContext.hasAuth("update_contentInfoLevel") }'>
					<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
				</c:if>
				<c:if test='${authContext.hasAuth("delete_contentInfoLevel") }'>
					<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
				</c:if>
				<c:if test='${authContext.hasAuth("enable_contentInfoLevel") }'>
					<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a>
				</c:if>
				<c:if test='${authContext.hasAuth("disable_contentInfoLevel") }'>
					<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>
				</c:if>
				<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
					class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
			</div>
		</div>
	</div>
</div>
</body>