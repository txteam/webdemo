<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryTransferRecordPagedList</title>
<%@include file="../../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = true;
$.canUpdate = true;
$.canDelete = true;

var grid = null;
var idFieldName = 'id';
var nameFieldName = 'id';
var entityName = '转账记录'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/clientAccountTrading/queryTransferRecordPagedList.action',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
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
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [
		          [{"title":"基本信息","colspan":5}, 
		           {"title":"转出信息","colspan":5},
		           {"title":"转入信息","colspan":5}
		           ],
		 [		
		{
			field : 'type',
			title : '类型',
			width : 80,
			formatter: function(cellvalue, options, rowObject){
				
				return cellvalue.name;
			},
			sortable : true
		},
		{
			field : 'status',
			title : '状态',
			width : 150,
			formatter: function(cellvalue, options, rowObject){
				
				return cellvalue.name;
			},
			sortable : true
		},
		{
			field : 'amount',
			title : '金额',
			align:'right',
			width : 100
		},
			
		{
			field : 'commissionFeeAmount',
			title : '手续费',
			align:'right',
			width : 100
		},
		
		{
			field : 'channelInfo',
			title : '渠道信息',
			width : 200
		},
		{
			field : 'paymentChannel',
			title : '支付渠道信息',
			hidden:true,
			width : 200,
				formatter: function(cellvalue, options, rowObject){
				
				return cellvalue.name;
			},
		},
		
		{
			field : 'remark',
			title : '备注',
			hidden:true,
			width : 200
		},
		/* {
			field : 'sourceInfo',
			title : '来源信息',
			width : 200
		},
		
	
		{
			field : 'targetInfo',
			title : '目标描述信息',
			width : 200
		}, */
		
		{
			field : 'transferOutClientUserName',
			title : '用户名',			
			width : 150
		},
		{
			field : 'transferOutClientMobilePhone',
			title : '电话号码',			
			width : 200
		},
		{
			field : 'factTransferOutAmount',
			title : '实际转出金额',			
			width : 100,
			align : 'right'
		},
		{
			field : 'transferOutTradingRecordId',
			title : '转出记录ID',
			hidden :true,
			width : 200
		},
	
		{
			field : 'transferOutStatus',
			title : ' 转出状态 ',
			formatter: function(cellvalue, options, rowObject){
				
				return cellvalue.name;
			},
			width : 100
		},	
		{
			field : 'transferOutClientaccountItemId',
			title : 'transferOutClientaccountItemId',
			hidden: true,
			width : 200
		},
		{
			field : 'transferOutLastUpdateDate',
			title : '更新时间',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');
			}
		},

		{
			field : 'transferInClientUserName',
			title : '转入用户名',
			width : 150
		},
		{
			field : 'transferInClientMobilePhone',
			title : '电话号码',			
			width : 200
		},
		{
			field : 'factTransferInAmount',
			title : '实际转入金额',
			align:'right',
			width : 100
		},
		
		{
			field : 'transferInStatus',
			title : '转入状态',
			width : 100,
			formatter: function(cellvalue, options, rowObject){
				
				return cellvalue.name;
			}
		},
		{
			field : 'transferInTradingRecordId',
			title : '转入记录ID',
			hidden :true,
			width : 200
		},
		
		{
			field : 'transferInClientAccountId',
			title : 'transferInClientAccountId',
			hidden: true,
			width : 200
		},		
		
		
		
		{
			field : 'transferInLastUpdateDate',
			title : '更新时间',
// 			hidden: true,
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
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
			field : 'transferInPaymentOrderId',			
			title : 'transferInPaymentOrderId',
			hidden: true,
			width : 200
			
		},
		
	
	
	/* 	{
			field : 'lastUpdateDate',
			title : 'lastUpdateDate',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		}, */
		{
			field : 'transferOutClientAccountId',
			title : 'transferOutClientAccountId',
			hidden:true,
			width : 200
		},
		{
			field : 'refTradingRecordId',
			title : 'refTradingRecordId',
			hidden:true,
			width : 200
		},
		{
			field : 'transferInClientaccountItemId',
			title : 'transferInClientaccountItemId',
			hidden:true,
			width : 200
		},
		{
			field : 'tradingRecordId',
			title : 'tradingRecordId',
			hidden:true,
			width : 200
		},
		{
			field : 'transferOutClientId',
			title : 'transferOutClientId',
			hidden:true,
			width : 200
		}
// 		<c:if test="${show_grid_action == true}">
// 		,{
// 			field : 'action',
// 			title : '操作',
// 			width : 220,
// 			formatter : function(value, row, index) {
// 				var str = '&nbsp;';
// 				if($.canUpdate){
// 					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
// 					str += '&nbsp;';
// 				}
				
// 				if($.canDelete){
// 					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil_delete.png');
// 					str += '&nbsp;';
// 				}
// 				return str;
// 			}
// 		}
// 		</c:if>	
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


function exportExcel(){		
	 DialogUtils.confirm("确认提醒", "确认导出?",
            function (data) {			
                if (data) {
                    $("#queryForm").attr("action", "${contextPath}/clientAccountTrading/exportExcelOfTransferRecord.action");
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
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
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
					<th>转入状态:</th>
					<td>					
					<select name="transferInStatus">
					<option value="">-- 请选择  --</option>
					<c:forEach items="${transferStatuses}" var="st">
						<option value="${st.key }">${st.name}</option>
					</c:forEach>
					</select>
					</td>
					<th>转入客户姓名:</th>
					<td><input id="likeInClientUserName" name="likeInClientUserName"/></td>
					<th>转入客户电话号码:</th>
					<td><input id="likeInMobilePhone" name="likeInMobilePhone"/></td>
				</tr>
				<tr>
					<th>转出状态:</th>
					<td>					
					<select name="transferOutStatus">
					<option value="">-- 请选择  --</option>
					<c:forEach items="${transferStatuses}" var="st">
						<option value="${st.key }">${st.name}</option>
					</c:forEach>
					</select>
					<th>转出客户姓名:</th>
					<td><input id="likeOutClientUserName" name="likeOutClientUserName"/></td>
					<th>转出客户电话号码:</th>
					<td><input id="likeOutMobilePhone" name="likeOutMobilePhone"/></td>
					
				</tr>
			<!-- 	<tr>
				  
					//FIXME: 修改查询条件中文名
					<th>commissionFeeAmount</th>
					<td><input id="commissionFeeAmount" name="commissionFeeAmount"/></td>
					//FIXME: 修改查询条件中文名
					<th>serialNumber</th>
					<td><input id="serialNumber" name="serialNumber"/></td>
				</tr> -->
			<!-- 	<tr>
					//FIXME: 修改查询条件中文名
					<th>type</th>
					<td><input id="type" name="type"/></td>
					//FIXME: 修改查询条件中文名
					<th>transferOutClientaccountItemId</th>
					<td><input id="transferOutClientaccountItemId" name="transferOutClientaccountItemId"/></td>
				</tr>
				<tr>
					//FIXME: 修改查询条件中文名
					<th>amount</th>
					<td><input id="amount" name="amount"/></td>
					//FIXME: 修改查询条件中文名
					<th>transferOutClientAccountId</th>
					<td><input id="transferOutClientAccountId" name="transferOutClientAccountId"/></td>
				</tr>
				<tr>
					//FIXME: 修改查询条件中文名
					<th>refTradingRecordId</th>
					<td><input id="refTradingRecordId" name="refTradingRecordId"/></td>
					//FIXME: 修改查询条件中文名
					<th>transferInClientaccountItemId</th>
					<td><input id="transferInClientaccountItemId" name="transferInClientaccountItemId"/></td>
				</tr>
				<tr>
					//FIXME: 修改查询条件中文名
					<th>transferInClientId</th>
					<td><input id="transferInClientId" name="transferInClientId"/></td>
					//FIXME: 修改查询条件中文名
					<th>tradingRecordId</th>
					<td><input id="tradingRecordId" name="tradingRecordId"/></td>
				</tr>
				<tr>
					//FIXME: 修改查询条件中文名
					<th>transferOutClientId</th>
					<td><input id="transferOutClientId" name="transferOutClientId"/></td>
					<th>&nbsp;</th>
					<td>&nbsp;</td>
				</tr> -->
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