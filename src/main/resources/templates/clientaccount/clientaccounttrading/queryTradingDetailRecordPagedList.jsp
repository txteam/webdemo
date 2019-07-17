<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryTradingDetailRecordPagedList</title>
<%@include file="../../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = true;
$.canUpdate = true;
$.canDelete = true;

var grid = null;
var idFieldName = 'id';
<%!//FIXME: 修改名字字段 --%>
var nameFieldName = 'id'; 
<%!//FIXME: 修改实体名称 --%>
var entityName = 'TradingDetailRecord'; 

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/clientAccountTrading/queryTradingDetailRecordPagedList.action',
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
		columns: [[
		{
			field : 'serialNumber',
			title : '交易序列号',
			width : 200
		},
		{
			field : 'clientUserName',
			title : '用户名',
			width : 200
		},
		{
			field : 'clientMobilePhoneNumber',
			title : '电话号码',
			width : 200
		},
		{
			field : 'beforeSum',
			title : '变更前金额',
			width : 100
		},
		{
			field : 'sum',
			title : '变更金额',
			width : 100
		},
		{
			field : 'afterSum',
			title : '变更后金额',
			width : 100
		},
		{
			field : 'viewAble',
			title : '可见',
			width : 70,
			formatter : function(value, row, index) {
				if(value == true){
					return "是";
				}else{
					return "否";
				}
			}
		},
		{
			field : 'revokeAble',
			title : '可撤销',
			width : 70,
			formatter : function(value, row, index) {
				if(value == true){
					return "是";
				}else{
					return "否";
				}
			}
		},
		{
			field : 'revoked',
			title : '被退回/撤销',
			width : 120,
			formatter : function(value, row, index) {
				if(value == true){
					return "是";
				}else{
					return "否";
				}
			}
		},
		{
			field : 'revokeResean',
			title : '撤销原因',
			width : 200
		},
		{
			field : 'revokeDate',
			title : '撤销时间',
			width : 200
			,formatter: function(cellvalue, options, rowObject){
				if(cellvalue==null){return '';}
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
			field : 'summary',
			title : '交易摘要',
			width : 200
		},
		{
			field : 'remark',
			title : '备注',
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
// 			if($.canUpdate){
// 				editFun(row[idFieldName], row[nameFieldName]);
// 			}
		},
		onClickRow : function(index, row){
// 			$editALink.linkbutton('enable');
// 			$deleteALink.linkbutton('enable');
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
                    $("#queryForm").attr("action", "${contextPath}/clientAccountTrading/exportExcelOfTradingDetailRecord.action");
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
					<th>客户姓名:</th>
					<td><input id="likeUserName" name="likeUserName"/></td>
					<!-- <th>虚拟账户号:</th>
					<td><input id="clientAccountItemId" name="clientAccountItemId"/></td> -->
					<th>客户电话号码:</th>
					<td><input id="likeMobilePhone" name="likeMobilePhone"/></td>
					
				</tr>
				<tr>
					<th>流水号:</th>
					<td><input id="serialNumber" name="serialNumber"/></td>
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
					<td colspan="4" class="button operRow">
						<a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'search'">查询</a>
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td colspan="4" class="button operRow"> -->
<!-- 						<a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'search'">查询</a> -->
<!-- 					</td> -->
<!-- 				</tr> -->
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