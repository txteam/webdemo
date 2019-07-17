<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryClientAccountPagedList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
var grid = null;
var idFieldName = 'clientId';
var nameFieldName = 'id'; 
var entityName = '客户账户'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	grid = $('#grid').datagrid({
		url : '${contextPath}/clientAccount/queryClientAccountPagedListForChoose.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : true,
		selectOnCheck : true,
		remoteSort : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 20,
		pageList : [ 20, 40, 60, 80, 100 ],
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
// 			checkbox : true
		},
		{
			field : 'clientId',
			title : 'clientId',
			width : 200,
			hidden : true
		}
		]],
		columns: [[
           {
			field : 'loginName',
			title : '用户名(交易账号)',
			width : 100
		},
		{
			field : 'mobilePhone',
			title : '电话',
			width : 100
		},
		{
			field : 'userName',
			title : '姓名',
			width : 100
		},
		{
			field : 'idcardNumber',
			title : '身份证',
			width : 200
		},   
		{
			field : 'createDate',
			title : '注册时间',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');
			}
		},
		/* {
			field : 'investSum',
			title : '投资总额',
			width : 150,
			formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return '0';
				}	
				return formatterMoney(cellvalue,2);
			}
		} */
		]],
		toolbar : '#toolbar',
		onClickRow : function(index, row){
			$editALink.linkbutton('enable');
			$deleteALink.linkbutton('enable');
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			$editALink.linkbutton('disable');
			$deleteALink.linkbutton('disable');
		},
		onSelect : function(rowIndex,rowData){
            $.triggerge("choose_operator_" + "${eventName}",[rowData]);
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

</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>用户名:</th>
					<td><input id="userName" name="userName" /></td>
					
					<th>登录名称:</th>
					<td><input id="loginName" name="loginName" /></td>
					
					<th>电话号码:</th>
					<td><input id="mobilePhone" name="mobilePhone" /></td>
					
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
</body>