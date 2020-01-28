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
var entityName = '发行仓单'; 
$(document).ready(function(){
	grid = $('#grid').datagrid({
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
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}
		]],
		columns: [[
		{
			field : 'loanAccountType',
			title : '贷款账户类型',
			//hidden : true,
			width : 200
			,formatter: function(cellvalue, options, rowObject){
				var text = "";
				if(cellvalue){
					text = cellvalue.name;
				}
				return text;
			}
		},
	    {
			field : 'effectiveDate',
			title : '生效日期',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
				if(cellvalue){
		  			var date = new Date();
		  			date.setTime(cellvalue);
		  			return date.format('yyyy-MM-dd');
				}
				return null;
			}
		},
   		{
   			field : 'contractNumber',
   			title : '合同编号',
   			width : 200
   		},
   		{
   			field : 'idCardNumber',
   			title : '证件号码',
   			width : 200
   		},
   		{
   			field : 'clientName',
   			title : '客户名称',
   			width : 200
   		},
   		{
   			field : 'loanAmount',
   			title : '融资金额',
   			width : 200
   		},
   		{
   			field : 'principalBalance',
   			title : '本金结余',
   			width : 200
   		},
   		{
   			field : 'paidPeriod',
   			title : '已付/期数',
   			width : 200
   			,formatter: function(cellvalue, rowObject, index){
      	   		return cellvalue+'/'+rowObject.totalPeriod;
      		}
   		},
   		{
   			field : 'monthlyRepayAmount',
   			title : '每月还款金额',
   			width : 200
   		},
   		{
			field : 'lastRepayDate',
			title : '上次还款日期',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
				if(cellvalue){
		  			var date = new Date();
		  			date.setTime(cellvalue);
		  			return date.format('yyyy-MM-dd');
				}
				return null;
			}
		},
		{
   			field : 'accountStatus',
   			title : '账户状态',
   			width : 200
   			,formatter: function(cellvalue, rowObject, index){
   				var accountStatus = cellvalue.key;
   				if(rowObject.collectionStatus.key != 'NA'){
   					accountStatus += '/'+rowObject.collectionStatus.key;
   				}
   				if(rowObject.died){
   					accountStatus += '/DA';
   				}
   				if(rowObject.closed){
   					accountStatus += '/XX';
   				}
   				if(rowObject.legalProcedure){
   					accountStatus += '/LP';
   				}
      	   		return accountStatus;
      		}
   		}]],
   		toolbar : '#toolbar',
   		onDblClickRow : function(index, row){
   			viewDetail(row[idFieldName],row.contractNumber,row.loanAccountType);
		}
	});

});
/*
 * 查询
 */
function queryFun() {
	grid.datagrid({queryParams:$('#queryForm').serializeObject()});
	grid.datagrid({url : '${contextPath}/loanAccountProcessListing/queryLoanAccountList.action'},'load');
	return false;
}
/*
* 查看详情
*/
function viewDetail(id,contractNumber,loanAccountType){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		contractNumber = row[0].contractNumber;
		loanAccountType = row[0].loanAccountType;
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return ;
	}
	$.triggerGE("addOrSelectTab",[{
		id: "repay_" + id,
        title : $.formatString("还款_[{0}]",contractNumber),
        href : $.formatString("${contextPath}/loanAccountDetail/toLoanAccountDetail.action?loanAccountId={1}",loanAccountType,id)
    }]);
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
					<th>合同编号：</th>
					<td><input id="contractNumber" name="contractNumber"/></td>
					<th>身份证号：</th>
					<td><input id="idCardNumber" name="idCardNumber"/></td>
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