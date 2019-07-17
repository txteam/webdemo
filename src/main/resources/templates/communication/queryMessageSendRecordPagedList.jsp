<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryMessageSendRecordList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
<c:if test='${authContext.hasAuth("add_messageSendRecord")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_messageSendRecord")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_messageSendRecord")}'>
	$.canUpdate = true;
</c:if>

var grid = null;
var idFieldName = 'id';
<%!//FIXME: 修改名字字段 --%>
var nameFieldName = 'id'; 
<%!//FIXME: 修改实体名称 --%>
var entityName = 'MessageSendRecord'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/messageSendRecord/queryMessageSendRecordPagedList.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		pagination : true,
		pageSize : 15,
		pageList : [ 15, 20, 30, 40, 50 ],
		nowrap : true,
		striped : true,
		singleSelect : true,
		loadFilter: function(data){
			var res = {total:0,rows:[]};
			if(!$.ObjectUtils.isEmpty(data)
					&& !$.ObjectUtils.isEmpty(data.list)){
				res['total'] = data.count;
				res['rows'] = data.list;
			}
			return res;
		},
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
		{
			field : 'receiver',
			title : '手机号码',
			width : 200
		},
		{
			field : 'title',
			title : '标题',
			width : 200
		},
		{
			field : 'content',
			title : '内容',
			width : 500
		},
		{
			field : 'contentTemplateCode',
			title : '短信模板',
			width : 200
		},
		{
			field : 'errorMessage',
			title : '错误消息',
			width : 200
		},
		{
			field : 'remark',
			title : '备注',
			width : 200
		},
		{
			field : 'status',
			title : '状态',
			width : 200,
			formatter: function(value){
	   			if(value=="SUCCESS"){
	   			return "成功";
	   			}
	   			else {
	   				return "<span style='color:red;'>失败</span>";
	   			}
			}
		},
		{
			field : 'contentFileId',
			title : 'contentFileId',
			width : 200
			,hidden : true
		},
		
		{
			field : 'failCount',
			title : 'failCount',
			width : 200
			,hidden : true
		},
		{
			field : 'errorCode',
			title : 'errorCode',
			width : 200
			,hidden : true
		},
		{
			field : 'sendAgainWhenFail',
			title : 'sendAgainWhenFail',
			width : 200
			,hidden : true
		},
		{
			field : 'createDate',
			title : '创建时间',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		},
		{
			field : 'success',
			title : '状态',
			width : 200,
			formatter: function(value){
	   			if(value==1){
	   			return "成功";
	   			}
	   			else if(value==0){
	   				return "<span style='color:red;'>失败</span>";
	   			}
			}	
		}
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
			if($.canUpdate){
				editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onClickRow : function(index, row){
			$editALink.linkbutton('enable');
			$deleteALink.linkbutton('enable');
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
		"addMessageSendRecord",
		"新增" + entityName,
		$.formatString("${contextPath}/messageSendRecord/toAddMessageSendRecord.action"),
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
		"updateMessageSendRecord",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/messageSendRecord/toUpdateMessageSendRecord.action?messageSendRecordId={0}",id),
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
	//判断是否确认删除指定的MessageSendRecord
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的MessageSendRecord
	    		$.post(
			    		'${contextPath}/messageSendRecord/deleteMessageSendRecordById.action',
			    		{messageSendRecordId:id},
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
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>类型</th>
					<td><input id="type" name="type"/></td>
					<th>开始时间</th>
					<td><input id="maxCreateDate" name="maxCreateDate"
							readonly="readonly"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					</td>
				</tr>
				<tr>
					<th>状态</th>
					<td><input id="status" name="status"/></td>
					<th>结束时间</th>
					<td><input id="minCreateDate" name="minCreateDate"
							readonly="readonly"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					</td>
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