<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryViewClientTradingList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
<c:if test='${authContext.hasAuth("add_viewClientTrading")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_viewClientTrading")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_viewClientTrading")}'>
	$.canUpdate = true;
</c:if>

var grid = null;
var idFieldName = 'id';
var nameFieldName = 'id'; 
var entityName = 'ViewClientTrading'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/viewClientTrading/queryPagedList.action',
		queryParams : {
			status : 'SUCCESS'
		},
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'client,status',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		collapsible: true,
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
		}]],
		columns: [[
   		{
			field : 'clientId',
			title : '用户id',
			hidden: true,
			width : 200
		},
   		{
			field : 'loginName',
			title : '登陆账号',
			width : 200
		},
		{
			field : 'userName',
			title : '用户名',
			hidden: true,
			width : 200
		},
		{
			field : 'accountName',
			title : '开户名',
			width : 200
		},
		{
			field : 'mobilePhone',
			title : '电话',
			width : 200
		},
		{
			field : 'idcardNumber',
			title : '证件号',
			width : 200
		},
		{
			field : 'bankCardName',
			title : '开户行',
			width : 200
		},
		{
			field : 'bankCardNumber',
			title : '银行卡号',
			width : 200
		},
		{
			field : 'provinceName',
			title : '开户地',
			width : 200
		},
		{
			field : 'clientSource',
			title : '用户来源',
			width : 200
		},
		{
			field : 'rechargeCount',
			title : '入金次数',
			width : 200
		},
		{
			field : 'rechargeSum',
			title : '入金金额',
			width : 200
		},
		{
			field : 'cashoutCount',
			title : '出金次数',
			width : 200
		},
		{
			field : 'cashoutSum',
			title : '出金金额',
			width : 200
		},
		{
			field : 'transferOutCount',
			title : '转出次数',
			width : 200
		},
		{
			field : 'transferOutSum',
			title : '转出金额',
			width : 200
		},
		{
			field : 'transferInCount',
			title : '转入次数',
			width : 200
		},
		{
			field : 'transferInSum',
			title : '转入金额',
			width : 200
		},
		{
			field : 'status',
			title : '交易状态',
			width : 200,
			hidden: false,
			formatter: function(value){
	   			return value.name;
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
 * 导出到excel
 */
function exportExcel(){		
	 DialogUtils.confirm("确认提醒", "确认导出?",
            function (data) {			
                if (data) {
                    $("#queryForm").attr("action", "${contextPath}/viewClientTrading/exportExcelOfClientTrading.action");
                    $("#queryForm").attr("method", "post");
                    $("#queryForm").submit();
                }
            }
    );     

}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<!--//FIXME: 修改查询条件框体高度 -->
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 180px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>登陆账号</th>
					<td><input id="loginName" name="loginName"/></td>
					<th>用户名</th>
					<td><input id="userName" name="userName"/></td>
				</tr>
				<tr>
					<th>电话</th>
					<td><input id="mobilePhone" name="mobilePhone"/></td>
					<th>证件号</th>
					<td><input id="idcardNumber" name="idcardNumber"/></td>
				</tr>
				<tr>
					<th>银行卡号</th>
					<td><input id="bankCardNumber" name="bankCardNumber"/></td>
					<th>用户来源</th>
					<td><input id="clientSource" name="clientSource"/></td>
				<tr>
				<tr>
					<th>状态</th>
					<td><select name="status">
						<option value="">-- 请选择  --</option>
						<c:forEach items="${tradingViewStatuses}" var="st">
							<option value="${st.key}"<c:if test="${st.key == 'SUCCESS' }">selected='selected'</c:if>>${st.name}</option>
						</c:forEach>
					</td>
					<th>交易时间</th>
					<td>
						<input id="minCreateDate" name="minCreateDate" value="${lastMonth}" style="width:150px;"
							readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd 00:00:00',startDate:'%y-{%M-1}-%d'})"/>
							-
						<input id="maxCreateDate" name="maxCreateDate" value="${today }" style="width:150px;"
							readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd 23:59:59'})"/>
					</td>
				</tr>
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
		<a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton" 
			data-options="plain:true,iconCls:'database'">导出为Excel</a>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>