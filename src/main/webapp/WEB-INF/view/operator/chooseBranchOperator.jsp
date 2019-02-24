<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>configPostOperator</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var dataGrid = null;
$(document).ready(function() {
	parent.DialogUtils.progress('close');
	dataGrid = $('#dataGrid').datagrid({
		url : '${contextPath}/operator/queryOperatorPagedList.action',
		queryParams : {
			organizationId : '${branchId}'
		},
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'number',
		sortOrder : 'desc',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		rownumbers : true,
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
		frozenColumns : [[ {
			title : '操作员id',
			field : 'id',
			width : 150,
			hidden : true
		}]],
		columns : [ [{
			field : 'userName',
			title : '姓名',
			width : 180
		},{
			field : 'loginName',
			title : '编号',
			width : 180
		}] ],
		toolbar : '#toolbar',
		onRowContextMenu : function(e, rowIndex, rowData) {
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow', rowIndex);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		},
		onLoadSuccess : function() {
			$('#searchForm table').show();
			parent.$.messager.progress('close');
			$(this).datagrid('tooltip');
		},
        onSelect : function(rowIndex,rowData){
            $.triggerge("choose_branchUser_" + "${eventName}",[rowData]);
        }
	});
	
});

</script>
</head>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:false">
		<table id="dataGrid"></table>
    </div> 
	<div id="toolbar" style="display: none;">		
		<a onclick="dataGrid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>