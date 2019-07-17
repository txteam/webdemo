<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryContentInfoPagedList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
$.canDisable = false;
$.canEnable = false;
<c:if test='${authContext.hasAuth("add_contentInfo")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_contentInfo")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_contentInfo")}'>
	$.canUpdate = true;
</c:if>
<c:if test='${authContext.hasAuth("disable_contentInfo")}'>
	$.canDisable = true;
</c:if>
<c:if test='${authContext.hasAuth("enable_contentInfo")}'>
	$.canEnable = true;
</c:if>

var typeTree = null;
var grid = null;
var idFieldName = 'id';
var nameFieldName = 'name';
var entityName = '内容信息'; 

$(document).ready(function(){
	var $addALink = $("#addALink");
	typeTree = $('#typeTree').tree({
		url : '${contextPath}/contentInfoCategory/queryList.action?valid=true',
		idField : 'id',
		parentField : 'parentId',
		iconField : function(){
			return 'folder_database';
		},
		onLoadSuccess : function() {
			$addALink.hide();
			$addALink.linkbutton('disable');
		},
		textField : 'name',
		border : false,
		onClick : function(node){
			$addALink.show();
			$addALink.linkbutton('enable');
			
			$('#categoryCode').val(node.code);
			queryFun();
		}
	});
	
	
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	var  $enableALink = $("#enableALink");
	var  $disableALink = $("#disableALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/contentInfo/queryPagedList.action',
		fit : true,
		fitColumns : false,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 15,
		pageList : [ 15, 20, 30, 40, 50 ],
		checkOnSelect : false,
		selectOnCheck : false,
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
		frozenColumns: [[
		{
			field : 'id',
			title : '主键',
			width : 150,
			hidden : true
		},
		{
			field : 'createOperatorId',
			title : 'createOperatorId',
			width : 200,
			hidden : true
		},
		{
			field : 'lastUpdateOperatorId',
			title : 'lastUpdateOperatorId',
			width : 200,
			hidden : true
		},
		{
			field : 'typeName',
			title : '类型',
			width : 130
			,formatter: function(value, row, index){
				var text = '';
				if(row['type']){
					text = row['type'].name;
				}
				return text;
			},
			hidden : true
		},
		{
			field : 'categoryName',
			title : '分类',
			width : 100
			,formatter: function(value, row, index){
				var text = '';
				if(row['category']){
					text = row['category'].name;
				}
				return text;
			}
		},
		{
			field : 'name',
			title : '名称',
			width : 150
		},
		{
			field : 'content',
			title : '内容',
			width : 150,
			formatter: ignoreTagFormatter
		},
		{
			field : 'fileId',
			title : '内容文件',
			width : 150,
			align : 'center',
			formatter : function(value, row, index) {
				if($.ObjectUtils.isEmpty(value)){
					return "";
				}else if('dbImageContentLink' == row['type'].code){
					var img = $.formatString('<img class="imgViewer" data-original="${contextPath}/filecontext/resource/{0}" src="${contextPath}/filecontext/resource/thumbnail/{1}?height=18"/>', value,value);
					return img;
				}else{
					return value;
				}
			}
		}
		]],
		columns: [[
		
		{
			field : 'linkUrl',
			title : '超链接',
			width : 150
		},
		{
			field : 'title',
			title : '标题',
			width : 100
		},
		{
			field : 'valid',
			title : '是否有效',
			width : 100
			,formatter: function(cellvalue, options, rowObject){
	   			var text = cellvalue ? "<font color='green'>是</font>" : "<font color='red'>否</font>";
	   			return text;
			}
		},
		{
			field : 'orderIndex',
			title : '排序索引',
			width : 100
		},
		{
			field : 'levelName',
			title : '等级',
			width : 100
			,formatter: function(value, row, index){
				var text = '';
				if(row['level']){
					text = row['level'].name;
				}
				return text;
			}
		},
		{
			field : 'keywords',
			title : '关键字',
			width : 120
		},
		{
			field : 'remark',
			title : '备注',
			width : 200
		},
		{
			field : 'fileUrl',
			title : '文件Url',
			width : 200
		},
		{
			field : 'lastUpdateDate',
			title : '更新时间',
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
			title : '创建时间',
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
		}
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
			
			$(".imgViewer").viewer({url: 'data-original'});
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
/**
 * 新增
 */
function addFun() {
	var category = typeTree.tree('getSelected');
	if(!category){
		DialogUtils.alert("请在左侧内容分类树中，选择类容分类.");
		return ;
	}
	
	if('dbImageContentLink' == category.type.code){
		DialogUtils.progress({
	        text : '加载中，请等待....'
		});
		DialogUtils.openModalDialog(
			"addContentInfo",
			"新增" + entityName + " (" + category.name + ")",
			$.formatString("${contextPath}/contentInfo/toAddContentInfo.action?categoryCode={0}",category.code),
			800,540,function(){
				queryFun();
		});
	}else if('dbContentLink' == category.type.code){
		DialogUtils.progress({
	        text : '加载中，请等待....'
		});
		DialogUtils.openModalDialog(
			"addContentInfo",
			"新增" + entityName + " (" + category.name + ")",
			$.formatString("${contextPath}/contentInfo/toAddContentInfo.action?categoryCode={0}",category.code),
			650,340,function(){
				queryFun();
		});
	}else if('fileRichContent' == category.type.code){
		$.triggerge("addOrSelectTab",[{
			id : "addContentInfo",
			title :  "新增" + entityName,
	        href : $.formatString("${contextPath}/contentInfo/toAddContentInfo.action?categoryCode={0}",category.code)
	    }]);
	}else{
		alert(category.type.code);
		//dbRichContent|default
		$.triggerge("addOrSelectTab",[{
			id : "addContentInfo",
			title :  "新增" + entityName,
	        href : $.formatString("${contextPath}/contentInfo/toAddContentInfo.action?categoryCode={0}",category.code)
	    }]);
	}
	return false;
}
/**
 * 编辑
 */
function editFun() {
	var rows = grid.datagrid('getSelections');
	
	if($.ObjectUtils.isEmpty(rows)){
		DialogUtils.alert("没有选中的" + entityName);
		return ;
	}
	var id = rows[0][idFieldName];
	var name = rows[0][nameFieldName];
	var category = rows[0]['category'];
	
	if('dbImageContentLink' == category.type.code){
		DialogUtils.progress({
	        text : '加载中，请等待....'
		});
		DialogUtils.openModalDialog(
			"updateContentInfo",
			"更新" + entityName + ":" + name + " (" + category.name + ")",
			$.formatString("${contextPath}/contentInfo/toUpdateContentInfo.action?categoryCode={0}&contentInfoId={1}",category.code,id),
			800,540,function(){
				queryFun();
		});
	}else if('dbContentLink' == category.type.code){
		DialogUtils.progress({
	        text : '加载中，请等待....'
		});
		DialogUtils.openModalDialog(
			"updateContentInfo",
			"更新" + entityName + ":" + name + " (" + category.name + ")",
			$.formatString("${contextPath}/contentInfo/toUpdateContentInfo.action?categoryCode={0}&contentInfoId={1}",category.code,id),
			650,340,function(){
				queryFun();
		});
	}else if('fileRichContent' == category.type.code){
		$.triggerGE("addOrSelectTab",[{
			id : "updateContentInfo",
	        title : "更新" + entityName,
	        href : $.formatString("${contextPath}/contentInfo/toUpdateContentInfo.action?categoryCode={0}&contentInfoId={1}",category.code,id)
	    }]);
	}else{
		$.triggerGE("addOrSelectTab",[{
			id : "updateContentInfo",
	        title : "更新" + entityName,
	        href : $.formatString("${contextPath}/contentInfo/toUpdateContentInfo.action?categoryCode={0}&contentInfoId={1}",category.code,id)
	    }]);
	}
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
	//判断是否确认删除指定的ContentInfo
	DialogUtils.confirm("确认提醒" , 
    	$.formatString("是否确认删除{0}:[{1}]?",entityName,name) , 
    	function(data){
	    	if(data){
	    		DialogUtils.progress({
	    	        text : '数据提交中，请等待....'
	    		});
	    		//如果确认删除指定的ContentInfo
	    		$.post(
		    		'${contextPath}/contentInfo/deleteById.action',
		    		{contentInfoId:id},
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
	//判断是否确认禁用指定ContentInfo
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认禁用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认禁用指定ContentInfo
    		$.post(
	    		'${contextPath}/contentInfo/disableById.action',
	    		{contentInfoId:id},
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
	//判断是否确认禁用指定ContentInfo
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认启用{0}:[{1}]?",entityName,name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定ContentInfo
    		$.post(
	    		'${contextPath}/contentInfo/enableById.action',
	    		{contentInfoId:id},
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
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'west',title:'内容分类',split:true,
		tools : [
			{ iconCls : 'clear',handler : function() {deselect();} } ,
			{ iconCls : 'database_refresh',handler : function() {refreshTree();} }
		]"
		style="width:230px;">
		<ul id="typeTree"></ul>
	</div>
	
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit : true,border : false">
			<!--//FIXME: 修改查询条件框体高度 -->
			<div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
				<form id="queryForm" class="form">
					<table class="table table-hover table-condensed">
						<tr>
							<th>内容等级</th>
							<td>
								<select id="levelCode" name="levelCode">
									<option value="">-- 不限 --</option>
									<c:forEach items="${levels }" var="level">
										<option value="${level.code }">${level.name} (${level.code})</option>
									</c:forEach>
								</select>
							</td>
							<th>内容分类</th>
							<td>
								<select id="categoryCode" name="categoryCode">
									<option value="">-- 不限 --</option>
									<c:forEach items="${categories }" var="category">
										<option value="${category.code }">${category.name} (${category.code})</option>
									</c:forEach>
								</select>
							</td>
							<th>内容类型</th>
							<td>
								<select id="typeCode" name="typeCode">
									<option value="">-- 不限 --</option>
									<c:forEach items="${types }" var="type">
										<option value="${type.code }">${type.name} (${type.code })</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th>名称:</th>
							<td>
								<input id="name" name="name" value=""/>
							</td>
							<th>标题:</th>
							<td>
								<input id="title" name="title" value=""/>
							</td>
							<th>关键字:</th>
							<td>
								<input id="keywords" name="keywords" value=""/>
							</td>
						</tr>
						<tr>
							<th>创建时间(从):</th>
							<td><input id="minCreateDate" name="minCreateDate"
									readonly="readonly"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
							</td>
							<th>创建时间(到):</th>
							<td><input id="maxCreateDate" name="maxCreateDate"
									readonly="readonly"
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
							</td>
							<th>是否有效</th>
							<td>
								<select id="valid" name="valid">
									<option value="">-- 不限 --</option>
									<option value="true">是</option>
									<option value="false">否</option>
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
				<c:if test='${authContext.hasAuth("add_contentInfo") }'>
					<a id="addALink" onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>
				</c:if>
				<c:if test='${authContext.hasAuth("update_contentInfo") }'>
					<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>
				</c:if>
				<c:if test='${authContext.hasAuth("delete_contentInfo") }'>
					<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>
				</c:if>
				<c:if test='${authContext.hasAuth("enable_contentInfo") }'>
					<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a>
				</c:if>
				<c:if test='${authContext.hasAuth("disable_contentInfo") }'>
					<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>
				</c:if>
				<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
					class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
			</div>
		</div>
	</div>
</div>
</body>