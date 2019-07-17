	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryAdjustAccountRecordPagedList</title>
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
//权限判定
$.canAdd = false;
$.canUpdate = false;
$.canDelete = false;
<c:if test='${authContext.hasAuth("add_adjustAccountRecord")}'>
	$.canAdd = true;
</c:if>
<c:if test='${authContext.hasAuth("delete_adjustAccountRecord")}'>
	$.canDelete = true;
</c:if>
<c:if test='${authContext.hasAuth("update_adjustAccountRecord")}'>
	$.canUpdate = true;
</c:if>

var grid = null;
var idFieldName = 'id';
<%!//FIXME: 修改名字字段 --%>
var nameFieldName = 'id'; 
<%!//FIXME: 修改实体名称 --%>
var entityName = 'AdjustAccountRecord'; 

var  $approveToPassButton = $("#approveToPassButton");
var  $refuseButton = $("#refuseButton");
var $getBankStatusButton = $("#getBankStatusButton");

$(document).ready(function(){
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");

	grid = $('#grid').datagrid({
		url : '${contextPath}/adjustAccountRecord/queryPagedList.action',
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
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
		{
			field : 'hostFlw',
			title : '调账柜员交易号',
			width : 200
		},
		{
			field : 'hostSeq',
			title : '调账柜员交易流水',
			width : 200
		},
		
		{
			field : 'hostDate',
			title : '调账柜员日期',
			width : 200
		},
		{
			field : 'serialNumber',
			title : '交易流水',
			width : 200
		},
		{
			field : 'clientLoginName',
			title : '客户登录名',
			width : 200
		},
		{
			field : 'clientUserName',
			title : '客户名称',
			width : 200
		},
		{
			field : 'paymentChannel',
			title : '支付渠道',
			width : 200,
			formatter: function(value, row, index){
				
				return value;
			}
		},
		{
			field : 'accountBeforeSum',
			title : '变更前金额',
			width : 200
		},
		{
			field : 'sum',
			title : '调账金额',
			width : 200
		},
		/*{
			field : 'commissionFee',
			title : '是否收手续费',
			width : 250,
			hidden:true,
			formatter: function(value, row, index){
				if(value)
				{
					return "是";					
				}
				return "否";
			}
		},
		{
			field : 'commissionFeeAmount',
			title : '手续费',
			width : 200
		},
		{
			field : 'factSum',
			title : '实际到账金额',
			width : 250
		},
		{
			field : 'accountAfterSum',
			title : '变更后金额',
			width : 200
		},*/
		{
			field : 'status',
			title : '状态',
			width : 200,
			formatter: function(value, row,index){
	   			return value.name;
			}
		},
		{
			field : 'type',
			title : '类型',
			width : 200,
			formatter: function(value, row,index){
	   			return value;
			}
		},

		{
			field : 'clientAccountId',
			title : '客户账户Id',
			hidden:true,
			width : 200
		},
		{
			field : 'sum',
			title : '调账金额',
			width : 200
		},
		{
			field : 'targetInfo',
			title : '目标描述信息',
			hidden:true,
			width : 200
		},
		{
			field : 'tradingRecordId',
			title : '交易记录ID',
			hidden:true,
			width : 200
		},
		{
			field : 'clientAccountItemId',
			title : '客户子账户id',
			hidden:true,
			width : 200
		},
		{
			field : 'clientId',
			title : '客户Id',
			hidden:true,
			width : 200
		},
		{
			field : 'sourceInfo',
			title : 'sourceInfo',
			hidden:true,
			width : 200
		},
		{
			field : 'remark',
			title : '备注',
			width : 250
		},
		{
			field : 'createDate',
			title : '申请提出时间',
			width : 200
			,formatter: function(value, row, index){
	   			var text = '';
	   			if($.ObjectUtils.isEmpty(value)){
	   				text = '';
	   			}else{
	   				var date = new Date();
	   				date.setTime(value);
	   				text = date.format('yyyy-MM-dd hh:mm:ss');
	   			}
	   			return text;
			}
		},
			{
				field : 'lastUpdate',
				title : '初审时间',
				width : 200
				,formatter: function(value, row, index){
				var text = '';
				if($.ObjectUtils.isEmpty(value)){
					text = '';
				}else{
					var date = new Date();
					date.setTime(value);
					text = date.format('yyyy-MM-dd hh:mm:ss');
				}
				return text;
			}
			}
		<c:if test="${show_grid_action == true}">
		,{
			field : 'action',
			title : '操作',
			width : 220,
			formatter : function(value, row, index) {
				var str = '&nbsp;';
				if($.canUpdate){
					str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
					str += '&nbsp;';
				}
				
				if($.canDelete){
					str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil_delete.png');
					str += '&nbsp;';
				}
				return str;
			}
		}
		</c:if>	
		]],
		toolbar : '#toolbar',
		onDblClickRow : function(index, row){
		},
		onClickRow : function(index, row){
			if(row.status.key == "WAIT_SERVER_PROCESS")
			{
				$getBankStatusButton.linkbutton('enable');
			}
			if(row.status.key == "WAIT_APPROVE")
			{
				$approveToPassButton.linkbutton('enable');
				$refuseButton.linkbutton('enable');
			}
			
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			$approveToPassButton.linkbutton('disable');
			$getBankStatusButton.linkbutton('disable');
			$refuseButton.linkbutton('disable');
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


function approveToPassFun(id,clientUserName){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		clientUserName = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return ;
	}
	DialogUtils.confirm("确认提醒" ,
			$.formatString("是否确认审批通过{0}:[{1}]?",entityName,name) ,
			function(data){
				if(data){
					DialogUtils.progress({
						text : '数据提交中，请等待....'
					});
					//如果确认删除指定的RechargeApplyRecord
					$.post(
							'/adjustAccountApplyRecord/adjustAccountApplySuccess.action',
							{adjustAccountRecordId:id},
							function(data){
								DialogUtils.progress('close');
								if(data){
									DialogUtils.tip("审批通过" + entityName + "成功");
								}else{
									var errorMessage = $.formatString("审批通过{0}:{1}失败.",entityName,name);
									DialogUtils.alert("提醒:", errorMessage, "error");
								}
								grid.datagrid('load',$('#queryForm').serializeObject());
							});
				}
			});


	return false;
}
/*
 * 重置密码
 */
function getAdjustStatus(id,name){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		name = rows[0]['serialNumber'];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return false;
	}
	//判断是否确认禁用指定BaseClientInfo
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("是否确认获取 [{0}]最新调账入款信息?",name), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认启用指定BaseClientInfo
    		$.post(
		    		'${contextPath}/adjustAccountApplyRecord/synAdjustAccountStatus.action',
		    		{cashoutRecordId:id},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("获取调账入款信息" + entityName + "成功");
		    			grid.datagrid('reload',$('#queryForm').serializeObject());
		    });
    	}
    });
    return false;
}

function refuseFun(id,clientUserName){
	if (id == undefined) {
		var rows = grid.datagrid('getSelections');
		id = rows[0][idFieldName];
		clientUserName = rows[0][nameFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityName);
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});

	DialogUtils.confirm("确认提醒" ,
			$.formatString("是否确认审批通过{0}:[{1}]?",entityName,name) ,
			function(data){
				if(data){
					DialogUtils.progress({
						text : '数据提交中，请等待....'
					});
					//如果确认删除指定的RechargeApplyRecord
					$.post(
							'/adjustAccountApplyRecord/adjustAccountApplyFail.action',
							{adjustAccountRecordId:id},
							function(data){
								DialogUtils.progress('close');
								if(data){
									DialogUtils.tip("审批通过" + entityName + "成功");
								}else{
									var errorMessage = $.formatString("审批通过{0}:{1}失败.",entityName,name);
									DialogUtils.alert("提醒:", errorMessage, "error");
								}
								grid.datagrid('load',$('#queryForm').serializeObject());
							});
				}
			});

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
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientAccountId</th>
					<td><input id="clientAccountId" name="clientAccountId"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>status</th>
					<td><input id="status" name="status"/></td>
				</tr>
				<tr>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>serialNumber</th>
					<td><input id="serialNumber" name="serialNumber"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>tradingRecordId</th>
					<td><input id="tradingRecordId" name="tradingRecordId"/></td>
				</tr>
				<tr>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientAccountItemType</th>
					<td><input id="clientAccountItemType" name="clientAccountItemType"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>type</th>
					<td><input id="type" name="type"/></td>
				</tr>
				<tr>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientAccountItemId</th>
					<td><input id="clientAccountItemId" name="clientAccountItemId"/></td>
					<!--//FIXME: 修改查询条件中文名 -->
					<th>clientId</th>
					<td><input id="clientId" name="clientId"/></td>
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
			<c:if test='${authContext.hasAuth("approve_cashout_to_pass") }'>
				<a onclick="approveToPassFun();" href="javascript:void(0);" id="approveToPassButton" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">审批至通过</a>
			</c:if>
			<c:if test='${authContext.hasAuth("refuse_cashout") }'>
				<a onclick="refuseFun();" href="javascript:void(0);" id="refuseButton" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">拒绝</a>
			</c:if>
			<c:if test='${authContext.hasAuth("refuse_cashout") }'>
				<a onclick="getAdjustStatus();" href="javascript:void(0);" id="getBankStatusButton" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">获取调账信息</a>
			</c:if>

	
<%-- 		<c:if test='${authContext.hasAuth("add_adjustAccountRecord") }'> --%>
<!-- 			<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a> -->
<%-- 		</c:if> --%>
<%-- 		<c:if test='${authContext.hasAuth("update_adjustAccountRecord") }'> --%>
<!-- 			<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a> -->
<%-- 		</c:if> --%>
<%-- 		<c:if test='${authContext.hasAuth("delete_adjustAccountRecord") }'> --%>
<!-- 			<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a> -->
<%-- 		</c:if> --%>
		<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
</body>