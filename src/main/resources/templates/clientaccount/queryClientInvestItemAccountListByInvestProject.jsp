<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
var clientInvestItemGrid = null;
var idItemFieldName = 'id';
var nameItemFieldName = 'id'; 
var entityItemName = '投资项目客户明细'; 
$(document).ready(function(){
	clientInvestItemGrid = $('#clientInvestItemGrid').datagrid({
		url : '${contextPath}/clientInvestItemAccount/queryClientInvestItemAccountListByInvestProjectId.action',
		queryParams : {
			investProjectId : "${investProjectId}"
		},
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
			{
				field : 'investDate',
				title : '投资日期',
				width : 200
				,formatter: function(cellvalue, options, rowObject){
		   			var date = new Date();
		   			date.setTime(cellvalue);
		   			return date.format('yyyy-MM-dd hh:mm:ss');;
				}
			},
            {
				field : 'clientAccountName',
				title : '客户账户名',
				width : 200
		 	},
            {
				field : 'clientName',
				title : '客户姓名',
				width : 200
			},
          	{
				field : 'amount',
				title : '投资金额',
				width : 200
			},
			{
				field : 'status',
				title : '状态',
				width : 200
				,formatter: function(cellvalue, options, rowObject){
					if(cellvalue=='BIDDING'){
						return "投标中";
					}
					if(cellvalue=='BID_FAIL'){
						return "失败";
					}
					if(cellvalue=='REPAYING'){
						return "还款中";
					}
					if(cellvalue=='REPAY_COMPLETE'){
						return "已回款";
					}
					if(cellvalue=='TRANSFERED'){
						return "已转让";
					}
					return cellvalue;
				},
				sortable : true
			},
			{
				field : 'receivableAmount',
				title : '项目应收金额 ',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '0';
					}	
					return formatterMoney(cellvalue,2);
				}
			},
			{
				field : 'receivableIncomeAmount',
				title : '应收收入金额',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '0';
					}	
					return formatterMoney(cellvalue,2);
				}
			},
			{
				field : 'receivablePrincipalAmount',
				title : '应收本金金额',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '';
					}	
					return formatterMoney(cellvalue,2);
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
		toolbar : '#clientInvestItemToolbar',
		onDblClickRow : function(index, row){
		},
		onClickRow : function(index, row){
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
		}
	});
});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false">
		<table id="clientInvestItemGrid"></table>
    </div> 
</div>
<div id="clientInvestItemToolbar"  style="display: none;">	
	<div class="nav_header datagrid-toolbar">
			<label class="panel-title panel-with-icon">投资项目客户明细</label>	
	</div>
	<a onclick="clientInvestItemGrid.datagrid('reload');return false;" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>
</body>
</html>