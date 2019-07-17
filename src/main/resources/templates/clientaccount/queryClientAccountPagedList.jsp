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
		url : '${contextPath}/clientAccount/queryClientAccountPagedList.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
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
		},
		{
			field : 'clientId',
			title : '客户id',
			width : 200,
			hidden : true
		}
		]],
		columns: [[
            {
                field : 'clientType',
                title : '用户类型',
                width : 100,
                formatter:baseJsonformatter
            },
            {
			field : 'loginName',
			title : '用户名(交易账号)',
			width : 150
		},
            {
			field : 'mobilePhoneNumber',
			title : '电话',
			width : 130
		},
            {
			field : 'userName',
			title : '姓名/机构名称',
			width : 200
		},
		{
			field : 'idCardNumber',
			title : '身份证/营业执照号',
			width : 200
		},
		{
			field : 'sum',
			title : '账户总金额',
			width : 150,
			formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return '0.00';
				}	
				return formatterMoney(cellvalue,2);
			}
		},
		{
			field : 'frozenSum',
			title : '冻结总金额',
			width : 150,
			formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return '0.00';
				}	
				return formatterMoney(cellvalue,2);
			}
		},
		{
			field : 'availableSum',
			title : '可使用金额',
			width : 150,
			formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return '0.00';
				}	
				return formatterMoney(cellvalue,2);
			}
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
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
				editFun(row[idFieldName], row[nameFieldName]);
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
		},
		onSelect : function(rowData){
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
    var url = $.formatString("${contextPath}/clientAccount/toClientAccountDetail.action?clientId={0}",id);
    $.triggerge("addOrSelectTab", {
	    title : '客户账户信息详情' ,
	    href : url
	});
	return false;
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 145px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>姓名|机构名称:</th>
					<td><input id="userName" name="userName"/></td>
					<th>客户类型:</th>
					<td>
						<select name="type" name="type">
							<option value="">-- 不限 --</option>
							<c:forEach items="${clientTypes }"  var="type">
								<option value="${type.key }">${type.name }</option>
							</c:forEach>
						</select>
					</td>
					<th>客户状态</th>
					<td>
						<select id="status" name="status">
							<option value="">-- 不限 --</option>
							<c:forEach items="${clientInfoStatusList}"  var="clientInfoStatus">
								<option value="${clientInfoStatus.key }">${clientInfoStatus.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>手机号码:</th>
					<td><input id="mobilePhoneNumber" name="mobilePhoneNumber"/></td>
					<th>身份证/营业执照号 :</th>
					<td><input id="idCardNumber" name="idCardNumber"/></td>
					<th>用户名(交易账号):</th>
					<td><input id="loginName" name="loginName"/></td>
				</tr>
				<tr>
					<th>是否实名认证:</th>
					<td>
						<select name="realNameBinding" name="realNameBinding">
							<option value="">-- 不限 --</option>
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</td>
					<th>客户来源:</th>
					<td>
						<select id="clientSourceId" name="clientSourceId">
							<option value="">-- 不限 --</option>
							<c:forEach items="${clientSourceList}"  var="clientSource">
								<option value="${clientSource.id }">${clientSource.name }</option>
							</c:forEach>
						</select>
					</td>
					<th>推广渠道:</th>
					<td>
						<select id="promotionChannelId" name="promotionChannelId">
							<option value="">-- 不限 --</option>
							<c:forEach items="${promotionChannelList}"  var="promotionChannel">
								<option value="${promotionChannel.id }">${promotionChannel.name }</option>
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

			<%--<table class="table table-hover table-condensed">--%>
				<%--<tr>--%>
					<%--<th>登陆名:</th>--%>
					<%--<td><input id="loginName" name="loginName"/></td>--%>
					<%--<th>电话号码:</th>--%>
					<%--<td><input id="mobilePhone" name="mobilePhone"/></td>--%>
					<%--<th>姓名/机构名称 :</th>--%>
					<%--<td><input id="userName" name="userName"/></td>--%>
				<%--<tr>--%>
					<%--<td colspan="6" class="button operRow">--%>
						<%--<a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'search'">查询</a>--%>
					<%--</td>--%>
				<%--</tr>--%>
			<%--</table>--%>
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