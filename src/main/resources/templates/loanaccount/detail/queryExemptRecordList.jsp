<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
var exemptRecordGrid = null;
var idFieldName = 'id';
var entityName = '豁免'; 
$("#exempt_record").bind("selected",function(){
	exemptRecordGrid.datagrid('reload');
});
$("#exempt_record").bind("firstSelected",function(){
	exemptRecordGrid = $('#grid').datagrid({
		//url:'${contextPath}/exemptSetting/queryExemptSettingListByLoanAccountIdAndStatus.action?loanAccountId=${loanAccountDetail.id}',
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		onLoadSuccess: function(data){
			resetGridWidth();
		},
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
		{
			field : 'exemptType',
			title : '豁免类型',
			width : 150,
			formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue == 'FEEITEM_EXEMPT' ){
					return "费用项豁免";
				}
				return "提前结清豁免";
			}
		},
		{
			field : 'period',
			title : '期数',
			width : 140
		},
		{
			field : 'feeItem',
			title : '费用类型',
			width : 200
		},
		{
			field : 'repayDate',
			title : '到期还款日期',
			width : 200
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue == null){
					return null;
				}
	  			var date = new Date();
	  			date.setTime(cellvalue);
	  			return date.format('yyyy-MM-dd');
			}
		},
		{
			field : 'createDate',
			title : '豁免日期',
			width : 200
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue == null){
					return null;
				}
	  			var date = new Date();
	  			date.setTime(cellvalue);
	  			return date.format('yyyy-MM-dd');
			}
		},
		{
			field : 'applyAmount',
			title : '申请豁免金额',
			width : 200
		},
		{
			field : 'sourceAmount',
			title : '豁免前金额',
			width : 200
		},
		{
			field : 'effictiveAmount',
			title : '实际豁免金额',
			width : 200
		},
		{
			field : 'targetAmount',
			title : '豁免后金额',
			width : 200
		},
		{
			field : 'operatorName',
			title : '操作人',
			width : 200
		},
		{
			field : 'createDate',
			title : '操作时间',
			width : 200
		}]],
   		toolbar : '#toolbar'
	});
});
</script>
	<div data-options="region:'center',border:false">
		<table id="grid"></table>
    </div> 
    <div id="toolbar" style="display: none;">
	    <a onclick="exemptRecordGrid.datagrid('reload');return false;" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
    </div>