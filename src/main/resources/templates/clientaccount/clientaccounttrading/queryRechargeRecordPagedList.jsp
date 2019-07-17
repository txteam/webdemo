<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryRechargeRecordPagedList</title>
<%@include file="../../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = true;
$.canUpdate = true;
$.canDelete = true;

var grid = null;
var idFieldName = 'id';
var nameFieldName = 'id'; 
var entityName = '入金记录';
$(document).ready(function(){
	grid = $('#grid').datagrid({
		url : '${contextPath}/clientAccountTrading/queryRechargeRecordDetailPagedList.action',
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
		queryParams:{
			minCreateDate:"${lastMonth}",
			maxCreateDate:"${today}",
		},
		frozenColumns: [[ {
			field : 'id',
			title : '主键',
			width : 150,
			hidden : true
		}]],
		columns: [[
			{
				field : 'serialNumber',
				title : '流水号',
				width : 220
			},
			{
				field : 'clientUserName',
				title : '客户名称',
				width : 100
			},
			{
				field : 'clientAccountId',
				title : '客户账户',
				width : 200
			},
			{
				field : 'clientAccountItemId',
				title : '虚拟账户号',
				hidden: true,
				width : 200
			},
			{
				field : 'clientMobilePhoneNumber',
				title : '电话号码',
				hidden : false,
				width : 100
			},
			{
				field : 'type',
				title : '类型',
				width : 80,
				formatter: function(cellvalue, options, rowObject){
					if(cellvalue==null){return "";};					
					return cellvalue.name;
				},
				sortable : true
			},
			{
				field : 'beforeSum',
				title : '变更前金额',
				hidden : false,
				width : 100
			},
			{
				field : 'sum',
				title : '变更金额',
				width : 100
			},
			{
				field : 'poundageSum',
				title : '手续费金额',
				width : 200
			},
			{
				field : 'afterSum',
				title : '变更后金额',
				hidden : false,
				width : 100
			},
			{
				field : 'clientAccountItemType',
				title : '账户类型',
				hidden : true,
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if(cellvalue==null){return "";};					
					return cellvalue.name;
				},
				sortable : true
			},
			{
				field : 'paymentChannel',
				title : '渠道信息',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if(cellvalue==null){return "";};					
					return cellvalue.name;
				}
			},
			{
				field : 'createDate',
				title : '创建时间',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
		   			var date = new Date();
		   			date.setTime(cellvalue);
		   			return date.format('yyyy-MM-dd hh:mm:ss');;
				}
			},
			{
				field : 'lastUpdateDate',
				title : '更新时间',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
		   			var date = new Date();
		   			date.setTime(cellvalue);
		   			return date.format('yyyy-MM-dd hh:mm:ss');;
				}
			},
			{
				field : 'status',
				title : '状态',
				width : 100,
				formatter: function(cellvalue, options, rowObject){
					if(cellvalue==null){
						return "";
					}
					
					return cellvalue.name;
				},
				sortable : true
			},
			{
				field : 'remark',
				title : '备注',
				width : 200
			},
			{
				field : 'sourceInfo',
				title : '来源描述信息',
				hidden:true,
				width : 200
			},
			{
				field : 'tradingRecordId',
				title : '交易记录ID',
				hidden:true,
				width : 200
			}
		]],
		toolbar : '#toolbar',
// 		onDblClickRow : function(index, row){
// 			detailFun(row[idFieldName], row[nameFieldName]);
// 		},
		onClickRow : function(index, row){
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
		}
	});
});

/**
 * 详情
 */
function detailFun(id,name) {
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
	DialogUtils.openModalDialog(
		"detailRechargeRecord",
		"编辑" + entityName + ":" + name,
		$.formatString("${contextPath}/clientAccountTrading/detailRechargeRecord.action?activityInfoId={0}",id),
		450,220,function(){
			grid.datagrid('load',$('#queryForm').serializeObject());
	});
	return false;
}


/*
 * 查询
 */
function queryFun() {
	grid.datagrid('load',$('#queryForm').serializeObject());
	return false;
}

function exportExcel(){		
	 DialogUtils.confirm("确认提醒", "确认导出?",
             function (data) {			
                 if (data) {
                     $("#queryForm").attr("action", "${contextPath}/clientAccountTrading/exportExcelOfRechargeRecord.action");
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
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 145px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>交易类型:</th>
					<td>
					<select name="type">
					<option value="">-- 请选择  --</option>
					<c:forEach items="${types}" var="ty">
						<option value="${ty.key }">${ty.name}</option>
					</c:forEach>
					</select>
					</td>
					
					<th>交易状态:</th>
					<td>
					<select name="status">
					<option value="">-- 请选择  --</option>
					<c:forEach items="${statuses}" var="st">
						<option value="${st.key }">${st.name}</option>
					</c:forEach>
					</select>
					</td>
					<th>渠道信息:</th>
					<td>
						<select id="paymentChannel" name="paymentChannel">
							<option value="">-- 请选择  --</option>
							<c:forEach items="${paymentChannels}" var="pce">
								<option value="${pce.key }">${pce.name}</option>
							</c:forEach>
						</select>
					</td>			
				</tr>
				<tr>
					<th>流水号:</th>
					<td><input id="serialNumber" name="serialNumber"/></td>
					<th>客户姓名:</th>
					<td><input id="likeUserName" name="likeUserName"/></td>
					<!-- <th>虚拟账户号:</th>
					<td><input id="clientAccountItemId" name="clientAccountItemId"/></td> -->
					<th>客户电话号码:</th>
					<td><input id="likeMobilePhone" name="likeMobilePhone"/></td>
				</tr>
				<tr>
					<th>开始日期：</th>
					<td>
						<input id="minCreateDate" name="minCreateDate" value="${lastMonth}" style="width:150px;"
							readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',startDate:'%y-{%M-1}-%d'})"/>
							-
							<input id="maxCreateDate" name="maxCreateDate" value="${today }" style="width:150px;"
							readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
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
</div>
<div id="toolbar" style="display: none;">
<a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton" 
			data-options="plain:true,iconCls:'database'">导出为Excel</a>
	<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
		class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
</div>
</body>