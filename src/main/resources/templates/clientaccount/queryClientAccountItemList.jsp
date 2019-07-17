<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
var clientAccountItemGrid = null;
var idItemFieldName = 'id';
var nameItemFieldName = 'id'; 
var entityItemName = '客户子账户'; 
$(document).ready(function(){
	var clientAccountId = $("#clientAccountId").val();
	clientAccountItemGrid = $('#clientAccountItemGrid').datagrid({
		url : '${contextPath}/clientAccount/queryClientAccountItemList.action',
		queryParams : {
			clientAccountId : clientAccountId
		},
		fit : false,
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
				field : 'createDate',
				title : '创建日期',
				width : 200
				,formatter: function(cellvalue, options, rowObject){
		   			var date = new Date();
		   			date.setTime(cellvalue);
		   			return date.format('yyyy-MM-dd hh:mm:ss');;
				}
			},
            {
				field : 'accountName',
				title : '账户名称',
				width : 200
		 	},
            {
				field : 'typeName',
				title : '账户类型',
				width : 200
			},
          	{
				field : 'code',
				title : '账户编码',
				width : 200
			},
			{
				field : 'status',
				title : '账户状态',
				width : 200
				,formatter: function(cellvalue, options, rowObject){
					if(cellvalue=='WAIT_ACTIVATE'){
						return "待激活";
					}
					if(cellvalue=='ACTIVATED'){
						return "激活";
					}
					if(cellvalue=='STOP'){
						return "停用";
					}
					return cellvalue;
				},
				sortable : true
			},
			{
				field : 'amount',
				title : '账户金额 ',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '0';
					}	
					return formatterMoney(cellvalue,2);
				}
			},
			{
				field : 'availableAmount',
				title : '可用金额',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '0';
					}	
					return formatterMoney(cellvalue,2);
				}
			},
			{
				field : 'frozenAmount',
				title : '冻结金额',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '0';
					}	
					return formatterMoney(cellvalue,2);
				}
			},		
			{
				field : 'cashoutSum',
				title : '累积提现金额',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '0';
					}	
					return formatterMoney(cellvalue,2);
				}
			},
			{
				field : 'rechargeSum',
				title : '累计充值金额',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '0';
					}	
					return formatterMoney(cellvalue,2);
				}
			},
			{
				field : 'investSum',
				title : '累计投资金额',
				width : 200,
				formatter: function(cellvalue, options, rowObject){
					if($.ObjectUtils.isEmpty(cellvalue)){
						return '0';
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
		toolbar : '#clientAccountItemToolbar',
		onDblClickRow : function(index, row){
			//editClientAccountItemFun(row[idItemFieldName], row[nameItemFieldName]);
		},
		onClickRow : function(index, row){
			viewClientInvestItem(row['clientAccountId'],row[idInvestItemFieldName]);
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
		}
	});
});

function viewClientInvestItem(clientAccountId,clientAccountItemId){
	receiptRecordGrid.datagrid('load',{'clientAccountId':clientAccountId,'clientAccountItemId':clientAccountItemId});
}

/**
 * 编辑
 */
function editClientAccountItemFun(id,name) {
	if (id == undefined) {
		var rows = clientAccountItemGrid.datagrid('getSelections');
		id = rows[0][idItemFieldName];
		name = rows[0][nameItemFieldName];
	}
	if($.ObjectUtils.isEmpty(id)){
		DialogUtils.alert("没有选中的" + entityItemName);
		return ;
	}
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	DialogUtils.openModalDialog(
		"updateClientAccountItem",
		"编辑" + entityItemName + ":" + name,
		$.formatString("${contextPath}/clientAccount/toUpdateClientAccountItemPage.action?clientAccountItemId={0}",id),
		450,220,function(){
			clientAccountItemGrid.datagrid('load');
	});
	return false;
}
/*
 * 禁用
 */
function openFun(id,name){
	//判断是否确认禁用指定BaseClientInfo
	DialogUtils.confirm(
    		"确认提醒" , 
    		$.formatString("确认为用户开通子账户"), 
    function(data){
    	if(data){
    		DialogUtils.progress({
    	        text : '数据提交中，请等待....'
    		});
    		//如果确认禁用指定BaseClientInfo
    		$.post(
		    		'${contextPath}/baseClientInfo/createAccountItemClientInfoById.action',
		    		{clientInfoId:$("#clientId").val()},
		    		function(){
		    			DialogUtils.progress('close');
		    			DialogUtils.tip("创建客户子账户成功");
		    			grid.datagrid('reload',$('#queryForm').serializeObject());
		    });
    	}
    });
    return false;
}
</script>
<div>
	<div>
		<table id="clientAccountItemGrid"></table>
    </div> 
	<div id="clientAccountItemToolbar"  style="display: none;">	
		<div class="nav_header datagrid-toolbar">
				<label class="panel-title panel-with-icon">客户子账户</label>	
		</div>
		<c:if test='${!isDisable}'>
			<a id="openALink" onclick="openFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">开通子账户</a>
		</c:if>
		<a onclick="clientAccountItemGrid.datagrid('reload');return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>