<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryCollectionGroupList</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript" >
var grid = null;
var idFieldName = 'id';
var entityName = '客户贷款账户'; 
$(document).ready(function(){
	grid = $('#grid').datagrid({
		url : "${contextPath}/clientLoanAccount/queryClientLoanAccountPagedList.action",
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
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
			width : 120,
			hidden : true
		},
	    {
   			field : 'idCardType',
   			title : '证件号码',
   			width : 200
   			,formatter: function(value, row, index){
				var text = "";
				if(value){
					text = value.name;
				}
				return text;
			}
   		},
	    {
   			field : 'idCardNumber',
   			title : '证件号码',
   			width : 120
   		},
   		{
   			field : 'creditLineSum',
   			title : '授信额度',
   			width : 120
   		},
   		{
   			field : 'creditUsedSum',
   			title : '已使用授信额度',
   			width : 120
   		},
   		{
   			field : 'creditBanlanceSum',
   			title : '剩余授信额度',
   			width : 120
   		}
   		
		]],
		columns: [[
		{
			field : 'clientId',
			title : '客户id',
			width : 120,
			hidden : true
		},
		{
			field : 'clientAccountId',
			title : '客户账户id',
			width : 120,
			hidden : true
		},
		{
			field : 'creditInfoId',
			title : '客户信用信息id',
			width : 120,
			hidden : true
		},
		{
   			field : 'creditEffictiveDate',
   			title : '授信生效日',
   			width : 80,
   			hidden : true,
   			formatter: function(value, row, index){
				if(value){
		  			var date = new Date();
		  			date.setTime(value);
		  			return date.format('yyyy-MM-dd');
				}
				return "";
			}
   		},
   		{
   			field : 'creditExpiredDate',
   			title : '授信到期日',
   			width : 80,
   			formatter: function(value, row, index){
				if(value){
		  			var date = new Date();
		  			date.setTime(value);
		  			return date.format('yyyy-MM-dd');
				}
				return "";
			}
   		},
   		{
   			field : 'totalCount',
   			title : '累计贷款总量',
   			hidden : true,
   			width : 80
   		},
   		{
   			field : 'totalAmount',
   			title : '累计贷款额',
   			hidden : true,
   			width : 120
   		},
   		{
   			field : 'completedCount',
   			title : '完成贷款总量',
   			hidden : true,
   			width : 80
   		},
   		{
   			field : 'completedAmount',
   			title : '完成贷款总额',
   			hidden : true,
   			width : 120
   		},
   		{
   			field : 'loaningCount',
   			title : '现贷款量',
   			width : 80
   		},
   		{
   			field : 'loaningAmount',
   			title : '现贷款总额',
   			width : 120
   		},
   		{
   			field : 'totalOverdueCount',
   			title : '逾期量',
   			width : 80
   		},
   		{
   			field : 'totalOverdueAmount',
   			title : '逾期金额',
   			width : 120
   		},
   		{
   			field : 'totalPrincipalBalance',
   			title : '本金结余',
   			width : 120
   		},
   		{
   			field : 'createDate',
   			title : '创建时间',
   			width : 80,
   			hidden : true,
   			formatter: function(value, row, index){
				if(value){
		  			var date = new Date();
		  			date.setTime(value);
		  			return date.format('yyyy-MM-dd');
				}
				return "";
			}
   		},
   		{
   			field : 'lastUpdateDate',
   			title : '最后更新时间',
   			width : 80,
   			formatter: function(value, row, index){
				if(value){
		  			var date = new Date();
		  			date.setTime(value);
		  			return date.format('yyyy-MM-dd');
				}
				return "";
			}
   		}
   		]],
   		toolbar : '#toolbar',
   		onDblClickRow : function(index, row){
   			//viewDetail(row[idFieldName],row.contractNumber,row.loanAccountType);
		}
	});

});
/*
 * 查询
 */
function queryFun() {
	grid.datagrid({queryParams:$('#queryForm').serializeObject()});
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
					<th>证件号码：</th>
					<td><input id="idCardNumber" name="idCardNumber"/></td>
					<th>用户姓名：</th>
					<td><input id="clientName" name="clientName"/></td>
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
</html>