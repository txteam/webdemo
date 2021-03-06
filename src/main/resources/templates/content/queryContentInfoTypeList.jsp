<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryContentInfoTypeList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
$.canDisable = false;
$.canEnable = false;
<c:if test='${authContext.hasAuth("add_contentInfoType")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_contentInfoType")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_contentInfoType")}'>
	$.canUpdate = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_contentInfoType")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_contentInfoType")}'>
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

	grid = $('#grid').datagrid({
		url : '${contextPath}/contentInfoType/queryList.action',
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
	        width : 100
        },
		{
			field : 'code',
			title : '编码',
			width : 70
		},
		{
			field : 'modifyAble',
			title : '是否可编辑',
			width : 70
			,formatter: function(cellvalue, options, rowObject){
	   			var text = cellvalue ? "是" : "否";
	   			return text;
			}
		},
		{
			field : 'valid',
			title : '是否有效',
			width : 70
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
			width : 100
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		},
		{
			field : 'lastUpdateDate',			
			title : '最后更新日期',
			width : 100
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		}
		/*
		<c:if test="${show_grid_action == true}">
		,{
			field : 'action',
			title : '操作',
			width : 220,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if(!row.valid && $.canEnable){
					str += $.formatString('<img onclick="enableFun(\'{0}\',\'{1}\');" src="{2}" title="启用"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/control/control_play_blue.png');
					str += '&nbsp;';
				}
				if($.canUpdate){
					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;';
				}
				
				if($.canDelete){
					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil_delete.png');
					str += '&nbsp;';
				}
				if(row.valid && $.canDisable){
					str += $.formatString('<img onclick="disableFun(\'{0}\',\'{1}\');" src="{2}" title="禁用"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/control/control_stop_blue.png');
					str += '&nbsp;';
				}
				return str;
			}
		}
		</c:if>	
		*/
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
			if($.canUpdate){
				//editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onClickRow : function(index, row){
			if(row.modifyAble){
				$editALink.show();
				$deleteALink.show();
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
	<%!//FIXME: 修改新增modalDialog的width,height --%>
	DialogUtils.openModalDialog(
		"addContentInfoType",
		"新增" + entityName,
		$.formatString("${contextPath}/contentInfoType/toAddContentInfoType.action"),
		450,220,function(){
			queryFun();
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
		"updateContentInfoType",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/contentInfoType/toUpdateContentInfoType.action?contentInfoTypeId={0}",id),
		450,220,function(){
			queryFun();
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
	//判断是否确认删除指定的ContentInfoType
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的ContentInfoType
	    		$.post(
			    		'${contextPath}/contentInfoType/deleteById.action',
			    		{contentInfoTypeId:id},
			    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("删除" + entityName + "成功");
			    			}else{
			    				var errorMessage = $.formatString("删除{0}失败.可能已被处理.如有疑问,请联系管理员.",entityName);
			    				DialogUtils.alert("错误提示",errorMessage,"error");
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
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认禁用指定ContentInfoType
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认禁用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认禁用指定ContentInfoType
    		$.post(
		    		'${contextPath}/contentInfoType/disableById.action',
		    		{contentInfoTypeId:id},
		    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("禁用" + entityName + "成功");
			    			}else{
			    				var errorMessage = $.formatString("禁用{0}失败.",entityName);
			    				DialogUtils.alert("错误提示",errorMessage,"error");
			    			}
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
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认禁用指定ContentInfoType
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定ContentInfoType
    		$.post(
		    		'${contextPath}/contentInfoType/enableById.action',
		    		{contentInfoTypeId:id},
		    		function(data){
			    			DialogUtils.progress('close');
			    			if(data){
			    				DialogUtils.tip("启用" + entityName + "成功");
			    			}else{
			    				var errorMessage = $.formatString("启用{0}失败.",entityName);
			    				DialogUtils.alert("错误提示",errorMessage,"error");
			    			}
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
	<!--//FIXME: 修改查询条件框体高度 -->
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
					<th>是否可编辑</th>
					<td>
						<select id="modifyAble" name="modifyAble" class="select">
							<option value="">-- 不限 --</option>
							<option value="true">-- 是 --</option>
							<option value="false">-- 否 --</option>
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
		<%-- 		<c:if test='${authContext.hasAuth("add_contentInfoType") }'> --%>
		<!-- 			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a> -->
		<%-- 		</c:if> --%>
		<%-- 		<c:if test='${authContext.hasAuth("update_contentInfoType") }'> --%>
		<!-- 			<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a> -->
		<%-- 		</c:if> --%>
		<c:if test='${authContext.hasAuth("delete_contentInfoType") }'>
			<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
		</c:if>
		<%-- 		<c:if test='${authContext.hasAuth("enable_contentInfoType") }'> --%>
		<!-- 			<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a> -->
		<%-- 		</c:if> --%>
		<%-- 		<c:if test='${authContext.hasAuth("disable_contentInfoType") }'> --%>
		<!-- 			<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a> -->
		<%-- 		</c:if> --%>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>