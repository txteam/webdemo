<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryCashoutRecordPagedList</title>
<%@include file="../../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = true;
$.canUpdate = true;
$.canDelete = true;

var grid = null;
var idFieldName = 'id';
var nameFieldName = 'serialNumber';
var entityName = '出金记录';
$(document).ready(function(){
	grid = $('#grid').datagrid({
		url : '${contextPath}/clientAccountTrading/queryCashoutRecordDetailPagedList.action',
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
				width : 200
			},
			{
				field : 'clientId',
				title : '客户ID',
				hidden:true,
				width : 200
			},
			{
				field : 'clientUserName',
				title : '客户名称',
				width : 200
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
				width : 200
			},
			{
				field : 'type',
				title : '类型',
				hidden : true,
				width : 200,
				formatter: function(cellvalue, options, rowObject){					
					return cellvalue.name;
				},
				sortable : true
			},
			{
				field : 'beforeSum',
				title : '变更前金额',
				hidden : false,
				width : 200
			},
			{
				field : 'commissionFee',
				title : '是否收取手续费',
				hidden : true,
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if(!cellvalue)
					{
						return "否";
					}
					return "是";
				
				}
			},
			{
				field : 'sum',
				title : '出金金额',
				width : 200
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
				width : 200
			},		
			{
				field : 'cardNumber',
				title : '银行卡号',
				hidden:true,
				width : 200
			},
			{
				field : 'paymentChannel',
				title : '渠道信息',
				hidden: false,
				width : 200,
				formatter: function(cellvalue, options, rowObject){					
					return cellvalue.name;
				}
			},
			{
				field : 'targetInfo',
				title : '目标描述信息',
				hidden:true,
				width : 200
			},
			{
				field : 'createDate',
				title : '创建时间',
				width : 200
				,formatter: function(cellvalue, options, rowObject){
						var date = new Date();
						date.setTime(cellvalue);
						return date.format('yyyy-MM-dd hh:mm:ss');;
				}
			},
			{
				field : 'lastUpdateDate',
				title : '最后更新时间',
				hidden : false,
				width : 200
				,formatter: function(cellvalue, options, rowObject){
						var date = new Date();
						date.setTime(cellvalue);
						return date.format('yyyy-MM-dd hh:mm:ss');;
				}
			},
			{
				field : 'status',
				title : '状态',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					return cellvalue.name;
				},
				sortable : true
			},
			{
				field : 'sourceInfo',
				title : '来源描述信息',
				hidden:true,
				width : 200
			},
			{
				field : 'tradingRecordId',
				title : '交易记录id',
				hidden:true,
				width : 200
			},
			{
				field : 'remark',
				title : '备注',
				width : 200
			}	
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
		},
		onClickRow : function(index, row){
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');;
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

function exportExcel(){		
	 DialogUtils.confirm("确认提醒", "确认导出?",
            function (data) {			
                if (data) {
                    $("#queryForm").attr("action", "${contextPath}/clientAccountTrading/exportExcelOfCashoutRecord.action");
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
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 115px; overflow: hidden;">
		<form id="queryForm" class="form">
			<table class="table table-hover table-condensed">
				<tr>
					<th>客户姓名:</th>
					<td><input id="likeUserName" name="likeUserName"/></td>
					<th>客户电话号码:</th>
					<td><input id="likeMobilePhone" name="likeMobilePhone"/></td>
					<th>渠道信息:</th>
					<td>
						<select id="paymentChannel" name="paymentChannel">
							<option value="">-- 请选择  --</option>
							<c:forEach items="${paymentChannels}" var="pce">
								<option value="${pce.key }">${pce.name}</option>
							</c:forEach>
						</select>
<!-- 					<th>客户姓名:</th> -->
<!-- 					<td><input id="type" name="type"/></td> -->
<!-- 					<th>虚拟账户号:</th> -->
<!-- 					<td><input id="clientAccountItemId" name="clientAccountItemId"/></td> -->
<!-- 					<th>客户电话号码:</th> -->
<!-- 					<td><input id="clientAccountItemId" name="clientAccountItemId"/></td> -->
					</td>
				</tr>
				<tr>
<!-- 				<th>交易类型:</th> -->
<!-- 					<td> -->
<!-- 					<select name="type"> -->
<!-- 					<option value="">-- 请选择  --</option> -->
<%-- 					<c:forEach items="${types}" var="ty"> --%>
<%-- 						<option value="${ty.key }">${ty.name}</option> --%>
<%-- 					</c:forEach> --%>
<!-- 					</select> -->
<!-- 					</td> -->
					<th>流水号:</th>
					<td><input id="serialNumber" name="serialNumber"/></td>
					<th>交易状态:</th>
					<td>
					<select name="status">
					<option value="">-- 请选择  --</option>
					<c:forEach items="${statuses}" var="st">
						<option value="${st.key }">${st.name}</option>
					</c:forEach>
					</select>
					</td>
										
					<th>更新日期：</th>
					<td>
						<input id="minLastUpdateDate" name="minLastUpdateDate" value="${lastMonth}" style="width:150px;"
							readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',startDate:'%y-{%M-1}-%d'})"/>
							-
							<input id="maxLastUpdateDate" name="maxLastUpdateDate" value="${today }" style="width:150px;"
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
    
	<div id="toolbar" style="display: none;">
	<a onclick="exportExcel();" href="javascript:void(0);" class="easyui-linkbutton" 
			data-options="plain:true,iconCls:'database'">导出为Excel</a>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>