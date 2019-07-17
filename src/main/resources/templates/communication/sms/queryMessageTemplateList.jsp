<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryMessageTemplateList</title>
<%@include file="../../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;

var grid = null;
var idFieldName = 'id';
<%!//FIXME: 修改名字字段 --%>
var nameFieldName = 'id'; 
<%!//FIXME: 修改实体名称 --%>
var entityName = 'MessageTemplate'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/messageTemplate/queryMessageTemplateList.action?type=SMS',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
		{
			field : 'code',
			title : '编码',
			width : 80
		},
		{
			field : 'title',
			title : '标题',
			width : 100
		},
		{
			field : 'content',
			title : '内容',
			width : 350
		},
		{
			field : 'valid',
			title : '是否有效',
			width : 70,
			formatter : function(value, row, index) {
				if(value == true){
					return "是";
				}else{
					return "<span style='color:red;'>否</span>";
				}
			}
		},
		{
			field : 'modifyAble',
			title : '是否可编辑',
			width : 70,
			formatter : function(value, row, index) {
				if(value == true){
					return "是";
				}else{
					return "<span style='color:red;'>否</span>";
				}
			}
		},
		{
			field : 'remark',
			title : '备注',
			width : 100
		}
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
			if(row.modifyAble && $.canUpdate){
				editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onClickRow : function(index, row){
			if(row.modifyAble){
				$editALink.linkbutton('enable');
				$deleteALink.linkbutton('enable');
			}
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			
			$editALink.linkbutton('disable');
			$deleteALink.linkbutton('disable');
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
		"addMessageTemplate",
		"新增" + entityName,
		$.formatString("${contextPath}/messageTemplate/toAddMessageTemplate.action"),
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
		"updateMessageTemplate",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/messageTemplate/toUpdateMessageTemplate.action?messageTemplateId={0}",id),
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
	//判断是否确认删除指定的MessageTemplate
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的MessageTemplate
	    		$.post(
			    		'${contextPath}/messageTemplate/deleteMessageTemplateById.action',
			    		{messageTemplateId:id},
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
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<!--//FIXME: 修改查询条件框体高度 -->
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 115px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>标题</th>
					<td><input id="title" name="title"/></td>
					<th>编码</th>
					<td><input id="code" name="code"/></td>
				</tr>
				<tr>
					<th>内容</th>
					<td><input id="content" name="content"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>是否有效</th>
					<td>
						<select id="valid" name="valid" class="select">
							<option value="">-- 不限 --</option>
							<option value="true">-- 有效 --</option>
							<option value="false">-- 无效 --</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="button operRow">
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
</body>