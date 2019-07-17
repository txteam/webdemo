<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryNoticeMessage2ClientDetailPagedList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
$.canDisable = false;
$.canEnable = false;
<c:if test='${authContext.hasAuth("add_noticeMessage2ClientDetail")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_noticeMessage2ClientDetail")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_noticeMessage2ClientDetail")}'>
	$.canUpdate = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_noticeMessage2ClientDetail")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_noticeMessage2ClientDetail")}'>
	$.canEnable = true;
</c:if>

var grid = null;
var idFieldName = 'id';
var nameFieldName = 'titile';
var entityName = '站内消息'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	var  $enableALink = $("#enableALink");
	var  $disableALink = $("#disableALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/noticeMessage2ClientDetail/queryPagedList.action',
		fit : true,
		fitColumns : false,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
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
		},
		{
			field : 'title',
			title : '标题',
			width : 140
		},
		{
			field : 'content',
			title : '内容',
			width : 200
		},
		{
			field : 'priority',
			title : '优先级',
			hidden : true,
			width : 100
		},
		{
			field : 'noticeMessageTypeId',
			title : '站内消息类型id',
			hidden : true,
			width : 200
		},
		{
			field : 'noticeMessageTypeCode',
			title : '站内消息类型编码',
			hidden : true,
			width : 200
		},
		{
			field : 'noticeMessageTypeName',
			title : '消息类型',
			width : 200
		}]],
		columns: [[
		{
			field : 'receiveDate',
			title : '接收时间',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'readDate',
			title : '阅读时间',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'revokeFlag',
			title : '是否撤销',
			width : 100
			,formatter: function(value, row, index){
	   			var text = '';
	   			if(value){
	   				text = '是';
	   			}else{
	   				text = '否';
	   			}
	   			return text;
			}
		},
		{
			field : 'revokeDate',
			title : 'revokeDate',
			hidden : true,
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'deleteFlag',
			title : '是否删除',
			width : 100
			,formatter: function(value, row, index){
	   			var text = '';
	   			if(value){
	   				text = '是';
	   			}else{
	   				text = '否';
	   			}
	   			return text;
			}
		},
		{
			field : 'deleteDate',
			title : 'deleteDate',
			hidden : true,
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		
		{
			field : 'clientUserName',
			title : '客户名',
			width : 200
		},
		
		{
			field : 'clientLoginName',
			title : '客户登录名',
			width : 200
		},
		{
			field : 'clientPhoneNumber',
			title : '客户联系电话',
			width : 200
		},
		{
			field : 'clientId',
			title : '客户id',
			hidden : true,
			width : 200
		},
		{
			field : 'readFlag',
			title : 'readFlag',
			width : 200
		},
		
		{
			field : 'lastUpdateDate',
			title : 'lastUpdateDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'createDate',
			title : 'createDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		
		{
			field : 'publishDate',
			title : 'publishDate',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
		{
			field : 'clientType',
			title : 'clientType',
			width : 200
		}
		<c:if test="${show_grid_action == true}">
		,{
			field : 'action',
			title : '操作',
			width : 220,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if(!row.readFlag && $.canEnable){
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
				if(row.readFlag && $.canDisable){
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
			if($.canUpdate){
				editFun(row[idFieldName], row[nameFieldName]);
			}
		},
		onClickRow : function(index, row){
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
		"addNoticeMessage2ClientDetail",
		"新增" + entityName,
		$.formatString("${contextPath}/noticeMessage2ClientDetail/toAddNoticeMessage2ClientDetail.action"),
		450,320,function(){
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
		"updateNoticeMessage2ClientDetail",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/noticeMessage2ClientDetail/toUpdateNoticeMessage2ClientDetail.action?noticeMessage2ClientDetailId={0}",id),
		450,320,function(){
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
	//判断是否确认删除指定的NoticeMessage2ClientDetail
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的NoticeMessage2ClientDetail
	    		$.post(
			    		'${contextPath}/noticeMessage2ClientDetail/deleteById.action',
			    		{noticeMessage2ClientDetailId:id},
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
	//判断是否确认禁用指定NoticeMessage2ClientDetail
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认禁用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认禁用指定NoticeMessage2ClientDetail
    		$.post(
		    		'${contextPath}/noticeMessage2ClientDetail/disableById.action',
		    		{noticeMessage2ClientDetailId:id},
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
	//判断是否确认禁用指定NoticeMessage2ClientDetail
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定NoticeMessage2ClientDetail
    		$.post(
		    		'${contextPath}/noticeMessage2ClientDetail/enableById.action',
		    		{noticeMessage2ClientDetailId:id},
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
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 170px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>标题:</th>
					<td><input id="title" name="title"/></td>
					<th>最小创建时间:</th>
					<td><input id="minCreateDate" name="minCreateDate"
							readonly="readonly"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					</td>
					<th>最大创建时间:</th>
					<td><input id="maxCreateDate" name="maxCreateDate"
							readonly="readonly"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					</td>
				</tr>
				<tr>
					<th>是否阅读标记:</th>
					<td>
						<select id="readFlag" name="readFlag" class="select">
							<option value="">-- 不限 --</option>
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
					<th>最小发布时间:</th>
					<td><input id="minPublishDate" name="minPublishDate"
							readonly="readonly"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					</td>
					<th>最大发布时间:</th>
					<td><input id="maxPublishDate" name="maxPublishDate"
							readonly="readonly"
							onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
					</td>
				</tr>
				<tr>
					<th>电话号码:</th>
					<td><input id="clientPhoneNumber" name="clientPhoneNumber"/></td>
					<th>用户姓名:</th>
					<td><input id="clientUserName" name="clientUserName"/></td>
					<th>用户登录名:</th>
					<td><input id="clientLoginName" name="clientLoginName"/></td>
				</tr>
				<tr>
					<th>deleteFlag</th>
					<td><input id="deleteFlag" name="deleteFlag"/></td>
					<th>revokeFlag</th>
					<td><input id="revokeFlag" name="revokeFlag"/></td>
					<th>clientType</th>
					<td><input id="clientType" name="clientType"/></td>
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
		<c:if test='${authContext.hasAuth("add_noticeMessage2ClientDetail") }'>
			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
		</c:if>
		<c:if test='${authContext.hasAuth("update_noticeMessage2ClientDetail") }'>
			<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
		</c:if>
		<c:if test='${authContext.hasAuth("delete_noticeMessage2ClientDetail") }'>
			<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
		</c:if>
		<c:if test='${authContext.hasAuth("enable_noticeMessage2ClientDetail") }'>
			<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">标记为已读</a>
		</c:if>
		<c:if test='${authContext.hasAuth("disable_noticeMessage2ClientDetail") }'>
			<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">标记为未读</a>
		</c:if>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>