<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryContentInfoCategoryList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
$.canDisable = false;
$.canEnable = false;
<c:if test='${authContext.hasAuth("add_contentInfoCategory")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_contentInfoCategory")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_contentInfoCategory")}'>
	$.canUpdate = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_contentInfoCategory")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_contentInfoCategory")}'>
	$.canEnable = true;
</c:if>

var grid = null;
var idFieldName = 'id';
var nameFieldName = 'name'; 
var entityName = '内容类型'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	var  $enableALink = $("#enableALink");
	var  $disableALink = $("#disableALink");

	grid = $('#grid').treegrid({
		url : '${contextPath}/contentInfoCategory/queryList.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		parentField : 'parentId',
		treeField : 'code',
		iconField : function(item){
			return 'folder_user';	
		},
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		frozenColumns: [[ 
		{
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}
		]],
		columns: [[
		{
			field : 'code',
			title : '编号',
			width : 200
		},
		{
			field : 'name',
			title : '名称',
			width : 200
		},
		{
			field : 'type',
			title : '类型',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var text = cellvalue ? cellvalue.name : "";
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
			field : 'orderIndex',
			title : '排序值',
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
			title : '最后更新时间',
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
			width : 220,
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
		onDblClickRow : function(row){
			if(row.modifyAble && $.canUpdate){
				editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onClickRow : function(row){
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
	grid.treegrid('load',$('#queryForm').serializeObject());
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
		"addContentInfoCategory",
		"新增" + entityName,
		$.formatString("${contextPath}/contentInfoCategory/toAddContentInfoCategory.action"),
		530,260,function(){
			queryFun();
	});
	return false;
}
/**
 * 编辑
 */
function editFun(id,name) {
	if (id == undefined) {
		var rows = grid.treegrid('getSelections');
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
		"updateContentInfoCategory",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/contentInfoCategory/toUpdateContentInfoCategory.action?contentInfoCategoryId={0}",id),
		530,260,function(){
			queryFun();
	});
	return false;
}
/*
 * 删除
 */
function deleteFun(id,name) {
	if (id == undefined) {
		var rows = grid.treegrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认删除指定的ContentInfoCategory
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的ContentInfoCategory
	    		$.post(
			    		'${contextPath}/contentInfoCategory/deleteById.action',
			    		{contentInfoCategoryId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("删除" + entityName + "成功");
			    			}else{
			    				var errorMessage = $.formatString("删除{0}:{1}失败.数据可能已经被处理.如有疑问，请联系系统管理员.",entityName,name);
			    				DialogUtils.alert("");
			    			}
			    			queryFun();
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
		var rows = grid.treegrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认禁用指定ContentInfoCategory
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认禁用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认禁用指定ContentInfoCategory
    		$.post(
		    		'${contextPath}/contentInfoCategory/disableById.action',
		    		{contentInfoCategoryId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("禁用" + entityName + "成功");
		    			queryFun();
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
		var rows = grid.treegrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认禁用指定ContentInfoCategory
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定ContentInfoCategory
    		$.post(
		    		'${contextPath}/contentInfoCategory/enableById.action',
		    		{contentInfoCategoryId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("启用" + entityName + "成功");
		    			queryFun();
		    });
    	}
    });
    return false;
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 120px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>编码:</th>
					<td><input id="code" name="code"/></td>
					<th>名称:</th>
					<td><input id="name" name="name"/></td>
					<th>类型:</th>
					<td>
						<select id="typeCode" name="typeCode">
							<option value="">-- 不限 --</option>
							<c:forEach items="${contentInfoTypes }" var="typeTemp">
								<option value="${typeTemp.code }">${typeTemp.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>是否可编辑:</th>
					<td>
						<select id="modifyAble" name="modifyAble">
							<option value="">-- 不限 --</option>
							<option value="true">-- 是 --</option>
							<option value="false">-- 否 --</option>
						</select>
					</td>
					<th>是否有效:</th>
					<td>
						<select id="valid" name="valid">
							<option value="">-- 不限 --</option>
							<option value="true">-- 是 --</option>
							<option value="false">-- 否 --</option>
						</select>
					</td>
					<th>上级分类:</th>
					<td>
						<select id="parentId" name="parentId">
							<option value="">-- 不限 --</option>
							<c:forEach items="${contentInfoCategories }" var="contentInfoCategoryTemp">
								<option value="${contentInfoCategoryTemp.id }">${contentInfoCategoryTemp.name }</option>
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
		<c:if test='${authContext.hasAuth("add_contentInfoCategory") }'>
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
		</c:if>
		<c:if test='${authContext.hasAuth("update_contentInfoCategory") }'>
			<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
		</c:if>
		<c:if test='${authContext.hasAuth("delete_contentInfoCategory") }'>
			<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
		</c:if>
		<c:if test='${authContext.hasAuth("enable_contentInfoCategory") }'>
			<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a>
		</c:if>
		<c:if test='${authContext.hasAuth("disable_contentInfoCategory") }'>
			<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>
		</c:if>
		<a onclick="grid.treegrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>