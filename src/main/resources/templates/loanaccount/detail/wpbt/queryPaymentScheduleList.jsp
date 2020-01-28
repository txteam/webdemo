<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
var paymentScheduleGrid = null;
var paymentScheduleNaGrid = null;
var idFieldName = 'id';
var entityName = '贷款账户';
$("#payment_schedule").unbind('firstSelected').bind("firstSelected",function(){
	paymentScheduleNaGrid = $('#paymentScheduleNaGrid').datagrid({
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		frozenColumns: [[ 
		{
			field : 'period',
			title : '期数',
			width : 120
		},
		{
			field:'feeItem',
			title: '费用项',
			width: 200
			,formatter: function(value, row, index){
				var text = "";
				if(value == 'DK_YCXSXF'){
					text = '一次性手续费';
				}else if(value == 'DK_YDKJQJE'){
					text = '原贷款结欠金额';
				}else if(value == 'DK_DQYQF'){
					text = '贷前延期费(贷款)';
				}else if(value == 'ZX_DQYQF'){
					text = '贷前延期费(咨询)';
				}else if(value == 'DK_DQKCF'){
					text = '贷前考察费(贷款)';
				}else if(value == 'ZX_DQKCF'){
					text = '贷前考察费(咨询)';
				}else if(value == 'DK_YQLX'){
					text = '逾期利息(贷款)';
				}else if(value == 'ZX_YQLX'){
					text = '逾期利息(咨询)';
				}else if(value == 'DK_KQSBSXF'){
					text = '扣款失败手续费(贷款)';
				}else if(value == 'ZX_KQSBSXF'){
					text = '扣款失败手续费(咨询)';
				}else if(value == 'DK_WBYJ'){
					text = '外包佣金(贷款)';
				}else if(value == 'ZX_WBYJ'){
					text = '外包佣金(咨询)';
				}else if(value == 'DK_DHZQF'){
					text = '贷后展期费(贷款)';
				}else if(value == 'ZX_DHZQF'){
					text = '贷后展期费(咨询)';
				}else if(value == 'DK_TQHKWYJ'){
					text = '提前还款违约金(贷款)';
				}else if(value == 'ZX_TQHKWYJ'){
					text = '提前还款违约金(咨询)';
				}else if(value == 'DK_CEHK'){
					text = '超额还款(贷款)';
				}else if(value == 'ZX_CEHK'){
					text = '超额还款(咨询)';
				}else{
					text = value;
				}
				return text;
			}
		}
		]],
		columns: [[
		{
			field : 'receivableAmount',
			title : '应收',
			width : 100
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentScheduleEntry']){
					return "";
				}
	  			var value = row['mainPaymentScheduleEntry'].receivableAmount;
	  			return value;
			}
		},
		{
			field : 'actualReceivedSum',
			title : '实收',
			width : 100
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentScheduleEntry']){
					return "";
				}
	  			var value = row['mainPaymentScheduleEntry'].actualReceivedAmount;
	  			return value;
			}
		},
		{
			field : 'exemptSum',
			title : '豁免',
			width : 100
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentScheduleEntry']){
					return "";
				}
	  			var value = row['mainPaymentScheduleEntry'].exemptAmount;
	  			return value;
			}
		},
	 	{
			field : 'receivableAmountIrr',
			title : '递减应收',
			width : 100
			,formatter: function(value, row, index){
				if(!row['paymentScheduleEntryMap']){
					return 0;
				}
				var entry = row['paymentScheduleEntryMap']['IRR'];
				if(entry){
					return entry.receivableAmount;
				}
				return 0;
			}
	 	},
	 	{
			field : 'actualReceivedAmountIrr',
			title : '递减实收',
			width : 100
			,formatter: function(value, row, index){
				if(!row['paymentScheduleEntryMap']){
					return 0;
				}
				var entry = row['paymentScheduleEntryMap']['IRR'];
				if(entry){
					return entry.actualReceivedAmount;
				}
				return 0;
			}
	 	},
	 	{
			field : 'exemptAmountIrr',
			title : '递减豁免',
			width : 100
			,formatter: function(value, row, index){
				if(!row['paymentScheduleEntryMap']){
					return 0;
				}
				var entry = row['paymentScheduleEntryMap']['IRR'];
				if(entry){
					return entry.exemptAmount;
				}
				return 0;
			}
	 	}
		]]
	});
	paymentScheduleGrid = $('#paymentScheduleGrid').datagrid({
		url:'${contextPath}/loanAccountDetail/queryPaymentScheduleDetailList.action?loanAccountId=${loanAccountDetail.id}',
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		collapsible: true,
		onLoadSuccess: function(data){
			//如果结果超过12行，将高度固定在380px
			var $grid = $(this);
			setTimeout(function(){
				if(data['rows'].length > 12){
					$grid.datagrid('resize',{
						height: 380
					});
				}else{
					$grid.datagrid('resize',{
						height: 100 + 32 * data['rows'].length
					});
				}
			}, 200);
		},
		loadFilter: function(paymentScheduleList){
			//alert($.toJsonString(paymentScheduleList));
			var res = {'total':0,'rows':[]};
			//var rows = [];
			var naPaymentScheduleEntryList = [];
			if(!$.ObjectUtils.isEmpty(paymentScheduleList)){
				$.each(paymentScheduleList,function(index,rowTemp){
					if(rowTemp.period != 'NA'){
						res['rows'].push(rowTemp);
					}else{
						var naPaymentScheduleEntryMap = {};
						$.each(rowTemp['paymentScheduleMap'],function(index , psTemp){
							$.each(psTemp['paymentScheduleEntryMap'],function(index , pseTemp){
								if(!naPaymentScheduleEntryMap[pseTemp.feeItem]){
									naPaymentScheduleEntryMap[pseTemp.feeItem] = {};
									naPaymentScheduleEntryMap[pseTemp.feeItem]['period'] = pseTemp.period;
									naPaymentScheduleEntryMap[pseTemp.feeItem]['feeItem'] = pseTemp.feeItem;
								}
								if(pseTemp.scheduleType.key == 'MAIN'){
									naPaymentScheduleEntryMap[pseTemp.feeItem]['mainPaymentScheduleEntry'] = pseTemp;
								}
								//分组
								if(!naPaymentScheduleEntryMap[pseTemp.feeItem].paymentScheduleEntryMap){
									naPaymentScheduleEntryMap[pseTemp.feeItem].paymentScheduleEntryMap = {};
								}
								naPaymentScheduleEntryMap[pseTemp.feeItem].paymentScheduleEntryMap[pseTemp.scheduleType] = pseTemp;
							});
						});
						$.each(naPaymentScheduleEntryMap,function(feeItemTemp , psedTemp){
							naPaymentScheduleEntryList.push(psedTemp);
						});
					}
				});
				res['total'] = res['rows'].length;
				paymentScheduleNaGrid.datagrid('loadData',naPaymentScheduleEntryList);
			}
			return res;
		},
		frozenColumns: [[ 
		{
			field : 'id',
			title : '主键',
			width : 150,
			hidden : true
			,formatter: function(value, row, index){
				if(!row['mainPaymentSchedule']){
					return '';
				}
	  			var value = row['mainPaymentSchedule'].id;
	  			return value;
			}
		},
		{
			field : 'period',
			title : '期数',
			width : 75
		},
		{
			field : 'repaymentDate',
			title : '到期还款日期',
			width : 120
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
	  			var date = new Date();
	  			date.setTime(row['mainPaymentSchedule'].repaymentDate);
	  			return date.format('yyyy-MM-dd');
			}
		},
		{
			field : 'receivableSum',
			title : '应收总额',
			width : 100
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
	  			var value = row['mainPaymentSchedule'].receivableSum;
	  			return value;
			}
		},
		{
			field : 'actualReceivedSum',
			title : '实收总额',
			width : 100
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
	  			var value = row['mainPaymentSchedule'].actualReceivedSum;
	  			return value;
			}
		},
		{
			field : 'exemptSum',
			title : '豁免总额',
			width : 75
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
	  			var value = row['mainPaymentSchedule'].exemptSum;
	  			return value;
			}
		}
		]],
		columns: [[
		{
			field : 'receivableAmount_DK_BJ',
			title : '本金应收',
			width : 100
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
				var entry = row['mainPaymentSchedule'].paymentScheduleEntryMap["DK_BJ"];
				if(entry){
					return entry.receivableAmount;
				}
				return 0;
			}
	 	},
	 	{
			field : 'actualReceivedAmount_DK_BJ',
			title : '本金实收',
			width : 100
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
				var entry = row['mainPaymentSchedule'].paymentScheduleEntryMap["DK_BJ"];
				if(entry){
					return entry.actualReceivedAmount;
				}
				return 0;
			}
	 	},
	 	{
			field : 'receivableAmount_DK_LX',
			title : '利息应收',
			width : 75
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
				var entry = row['mainPaymentSchedule'].paymentScheduleEntryMap["DK_LX"];
				if(entry){
					return entry.receivableAmount;
				}
				return 0;
			}
	 	},
	 	{
			field : 'actualReceivedAmount_DK_LX',
			title : '利息实收',
			width : 75
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
				var entry = row['mainPaymentSchedule'].paymentScheduleEntryMap["DK_LX"];
				if(entry){
					return entry.actualReceivedAmount;
				}
				return 0;
			}
	 	},
	 	{
			field : 'exemptAmount_DK_LX',
			title : '利息豁免',
			width : 75
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
				var entry = row['mainPaymentSchedule'].paymentScheduleEntryMap["DK_LX"];
				if(entry){
					return entry.exemptAmount;
				}
				return 0;
			}
	 	},
	 	{
			field : 'receivableAmount_ZX_GLF',
			title : '管理费应收',
			width : 75
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
				var entry = row['mainPaymentSchedule'].paymentScheduleEntryMap["ZX_GLF"];
				if(entry){
					return entry.receivableAmount;
				}
				return 0;
			}
		},
		{
			field : 'actualReceivedAmount_ZX_GLF',
			title : '管理费实收',
			width : 75
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
				var entry = row['mainPaymentSchedule'].paymentScheduleEntryMap["ZX_GLF"];
				if(entry){
					return entry.actualReceivedAmount;
				}
				return 0;
			}
		},
		{
			field : 'exemptAmount_ZX_GLF',
			title : '管理费豁免',
			width : 75
			,formatter: function(value, row, index){
				if(row == null || !row['mainPaymentSchedule']){
					return "";
				}
				var entry = row['mainPaymentSchedule'].paymentScheduleEntryMap["ZX_GLF"];
				if(entry){
					return entry.exemptAmount;
				}
				return 0;
			}
		}
	 	]],
   		toolbar : '#paymentScheduleToolbar'
	});
});

</script>
	<div id="paymentScheduleToolbar" style="display: none;">
	    <a onclick="paymentScheduleGrid.datagrid('reload');return false;" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
    </div>
	<div data-options="region:'center',border:false">
		<table id="paymentScheduleGrid"></table>
		<br/>
		<table id="paymentScheduleNaGrid"></table>
		<br/>
		<br/>
		<br/>
		<br/>
    </div> 
    