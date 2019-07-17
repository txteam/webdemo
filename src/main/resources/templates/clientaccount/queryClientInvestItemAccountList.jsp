<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
var clientInvestItemGrid = null;
var clientInvestItemNaGrid=null;
var idInvestItemFieldName = 'id';
var nameInvestItemFieldName = 'id'; 
var entityName = '客户投资项目'; 
$(document).ready(function(){
	var clientAccountId = $("#clientAccountId").val();
	clientInvestItemNaGrid = $('#clientInvestItemNaGrid').datagrid({
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true
	});
	clientInvestItemGrid = $('#clientInvestItemGrid').datagrid({
		url : '${contextPath}/clientAccount/queryClientInvestItemAccountList.action',
		queryParams : {
			clientAccountId :clientAccountId
		},		
		fit : false,
		fitColumns : false,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		collapsible: true,
		frozenColumns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		},
		{
			field : 'investProjectName',
			title : '项目名',
			width : 100
		},
		{
			field : 'status',
			title : '状态',
			width : 100,
			formatter: function(cellvalue, options, rowObject){
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
			field : 'investProjectProductName',
			title : '项目类型',
			width : 100
		},
		{
			field : 'investDate',
			title : '投资时间',
			width : 150
			,formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return"";
				}
				var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		},
		{
			field : 'investDate',
			title : '投资时间',
			width : 150
			,formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return"";
				}
				var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd');;
			}
		},
		{
			field : 'amount',
			title : '投资金额',
			width : 100,
			formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return '';
				}	
				return formatterMoney(cellvalue,2);
			}
		},
		{
			field : 'totalPeriod',
			title : '总期数',
			width : 100
		},
		{
			field : 'effectiveDate',
			title : '生效日期',
			width : 150
			,formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return"";
				}
				var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		},
		{
			field : 'expiredDate',
			title : '到期时间',
			width : 150
			,formatter: function(cellvalue, options, rowObject){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return"";
				}
				var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd hh:mm:ss');;
			}
		}
		]],
		columns: [[
		   		{
					field : 'clientAccountName',
					title : '客户账户名',
					width : 200
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
					field : 'receivableIncomeAmount',
					title : '应收收入金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'receivableAmount',
					title : '项目应收金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'investAmount',
					title : '投资金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'waitReceiveIncomeAmount',
					title : '待回收收入金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'investProjectProductName',
					title : '投资项目产品名',
					width : 200
				},
				{
					field : 'waitReceiveAmount',
					title : '待回收总金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'waitReceivePrincipalAmount',
					title : '待回收本金',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'investExperienceGoldAmount',
					title : '投资体验金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'actualReceivedIncomeAmount',
					title : '实收收入金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},		
				{
					field : 'actualReceivedPrincipalAmount',
					title : '实收本金金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'actualReceivedAmount',
					title : '实收总金额',
					width : 200,
					formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return '';
						}	
						return formatterMoney(cellvalue,2);
					}
				},
				{
					field : 'nextSettleDate',
					title : '下次应收计算日',
					width : 200
					,formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return"";
						}
						var date = new Date();
			   			date.setTime(cellvalue);
			   			return date.format('yyyy-MM-dd hh:mm:ss');;
					}
				},
				{
					field : 'lastUpdateDate',
					title : '最后更新时间',
					width : 200
					,formatter: function(cellvalue, options, rowObject){
						if($.ObjectUtils.isEmpty(cellvalue)){
							return"";
						}
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
				}		
		]],
		toolbar : '#clientInvestItemToolbar',
		onDblClickRow : function(index, row){
			//editClientAccountItemFun(row[idItemFieldName], row[nameItemFieldName]);
		},
		onClickRow : function(index, row){
			loadSettleScheduleMapList(row['id']);
		},
		onLoadSuccess: function(data){
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			//如果结果超过12行，将高度固定在380px
			var $grid = $(this);
			setTimeout(function(){
				if(data['rows'].length > 12){
					$grid.datagrid('resize',{
						height: 380
					});
				}
				resetGridWidth();
			}, 200);
		}
	});
});
</script>
<div data-options="border:false"  style="width:auto" >
       <div data-options="region:'center',border:false">
		<table id="clientInvestItemGrid"></table>
		<table id="clientInvestItemNaGrid">
		<!-- <thead>
				<tr>
					<th data-options="field:'investProjectName',width:100">项目名</th>
					<th data-options="field:'statusName',width:100">状态</th>
					<th data-options="field:'investProjectTypeName',width:100">项目类型</th>
					<th data-options="field:'investDate',width:150">投资时间</th>
					<th data-options="field:'amount',width:100">投资金额</th>
					<th data-options="field:'totalPeriod',width:100">总期数</th>
					<th data-options="field:'effectiveDate',width:150">生效日期</th>
					<th data-options="field:'expiredDate',width:150">到期日期</th>
				</tr>
			</thead> -->	
		</table>
    </div> 
	<div id="clientInvestItemToolbar"  style="display: none;">	
		<div class="nav_header datagrid-toolbar">
				<label class="panel-title panel-with-icon">客户投资项目</label>	
		</div>
		<a onclick="clientInvestItemGrid.datagrid('reload');return false;" href="javascript:void(0);" 
			class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>