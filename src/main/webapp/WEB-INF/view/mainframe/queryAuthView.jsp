<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryAuthView</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var tabs = null;
var $currentAuthTreeGridEl = null;
var authType2AuthItemListMap = null;
$(document).ready(function() {
	
	$.post('${contextPath}/auth/queryAuthType2AuthItemListMap.action',{},function(data){
		authType2AuthItemListMap = data;
		
		//初始化tabs
		tabs = $("#authTabView").tabs({
			fit:true,
			onSelect: function(title,index){
				var target = this;
				var $element = $(target);
				var $tabPannel = $element.tabs('getSelected');
				
				$currentAuthTreeGridEl = $tabPannel.find("table.authTreeGrid");
				
				var isLoad = ($currentAuthTreeGridEl.attr('isLoad') == 'true');
				var isConfigAble = ($currentAuthTreeGridEl.attr('configAble') == 'true');
				var authType = $currentAuthTreeGridEl.attr('authType');
				
				//获取当前操作面板的TreeGrid句柄
				
				
				//如果当前面板树尚未加载，就在此处进行加载
				if(!isLoad){
					initAuthTree($currentAuthTreeGridEl,authType,isConfigAble,authType2AuthItemListMap[authType]);
					$currentAuthTreeGridEl.attr('isLoad','true');
				}
			}
		});
	});
});
//如果tabs初始化
function initAuthTree($authTreeTableEl,authType,configAble,authItemList){
	var tableToolsbarId = '#toolbar_' + authType;
	treeGrid = $authTreeTableEl.treegrid({
		idField : 'id',
		parentField : 'parentId',
		treeField : 'name',
		iconFiled : function(item){
			return 'database_key';	
		},
		fit : true,
		fitColumns : true,
		border : false,
		frozenColumns : [ [ {
			title : '权限id',
			field : 'id',
			width : 150,
			hidden : true
		},{
			field : 'parentId',
			title : '上级权限ID',
			width : 150,
			hidden : true
		},{
			field : 'name',
			title : '权限名称'
		}] ],
		columns : [ [ {
			field : 'viewAble',
			title : '是否可见',
			width : 100,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'editAble',
			title : '是否可编辑',
			width : 100,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'configAble',
			title : '是否可配置',
			width : 100,
			formatter : function(value, row, index) {
				return value;
			}
		},{
			field : 'valid',
			title : '是否有效',
			width : 100,
			formatter : function(value, row, index) {
				return value;
			}
		}, {
			field : 'action',
			title : '操作',
			width : 100,
			formatter : function(value, row, index) {
				var str = '';
				if (true) {
					str += $.formatString('<img onclick="editFun(\'{0}\');" src="{1}" title="授权给组织"/>', row.id, '${contextPath}/style/images/extjs_icons/folder/folder_key.png');
				}
				str += '&nbsp;';
				if (true) {
					str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="授权给职位"/>', row.id, '${contextPath}/style/images/extjs_icons/group/group_key.png');
				}
				str += '&nbsp;';
				if (true) {
					str += $.formatString('<img onclick="deleteFun(\'{0}\');" src="{1}" title="授权给人员"/>', row.id, '${contextPath}/style/images/extjs_icons/photoAndPic/picture_key.png');
				}
				return str;
			}
		},{
			field : 'description',
			title : '备注',
			width : 300
		} ] ],
		toolbar : tableToolsbarId,
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
			parent.$.messager.progress('close');
			$(this).treegrid('tooltip');
		},
		data: authItemList
	});
}
</script>
</head>
<body>
<div id="authTabView" class="easyui-tabs">
<c:forEach items="${authTypeList}" var="authType">
	<div title="${authType.name}" style="overflow: hidden;height:fit;width:auto">
		<table class="authTreeGrid"
				isLoad="false" authType="${authType.authType}" configAble="${authType.configAble}"></table>
    </div> 
</c:forEach>
</div> 

<c:forEach items="${authTypeList}" var="authType">
<div id="toolbar_${authType.authType}" style="display: none;">
	<c:if test="${false}">
		<a onclick="" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
	</c:if>
	<a onclick="" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
	<a onclick="" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
	<a onclick="" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>
</c:forEach>

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
</body>