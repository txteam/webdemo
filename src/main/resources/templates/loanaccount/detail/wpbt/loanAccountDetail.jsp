<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loanAccountDetail</title>
<%@include file="../../../includes/commonHead.jsp"%>
<script type="text/javascript">
var overdueInfoGrid = null;
var outsourceInfoGrid = null;
var postponeInfoGrid = null;
var exemptApplyInfoGrid = null;
var waitAccountInfoGrid = null;
var detailTabs = null;

// 用于判断是否为主查询界面跳转过来的
var isProcessAble = '${isProcessAble}';
var timeoutHandler = null;
function resetGridWidth(){
	if(timeoutHandler){
		clearTimeout(timeoutHandler);
	}
	timeoutHandler = setTimeout(function(){
		//主页面中暂缓，逾期，tabs
		if(overdueInfoGrid){
			overdueInfoGrid.datagrid('resize',{
				width: $("#center_layout_container").innerWidth() - 23
			});
		}
		if(outsourceInfoGrid){
			outsourceInfoGrid.datagrid('resize',{
				width: $("#center_layout_container").innerWidth() - 23
			});
		}
		if(postponeInfoGrid){
			postponeInfoGrid.datagrid('resize',{
				width: $("#center_layout_container").innerWidth() - 23
			});
		}
		if(exemptApplyInfoGrid){
			exemptApplyInfoGrid.datagrid('resize',{
				width: $("#center_layout_container").innerWidth() - 23
			});
		}
		if(waitAccountInfoGrid){
			waitAccountInfoGrid.datagrid('resize',{
  				width: $("#center_layout_container").innerWidth() - 23
  			});
  		}
		if(detailTabs){
			detailTabs.tabs('resize',{
				width: $("#center_layout_container").innerWidth() - 23
			});
		}
		
		/* 
		//主页面中暂缓，逾期，tabs
		//还款界面中还款记录、财务详情
		if(repaymentRecordGrid){
			if($(document).innerWidth() > 1400 && !repaymentRecordGridFitColumnsFlag){
				repaymentRecordGridFitColumnsFlag = true;
				repaymentRecordGrid.datagrid({fitColumns:true});
			}else if($(document).innerWidth() <= 1400 && repaymentRecordGridFitColumnsFlag){
				repaymentRecordGridFitColumnsFlag = false;
				repaymentRecordGrid.datagrid({fitColumns:false});
			}
			repaymentRecordGrid.datagrid('resize',{
				width: $("#payment_record").innerWidth()
			});
			if(financeDetailGrid){
				financeDetailGrid.datagrid('resize',{
					width: $("#payment_record").innerWidth()
				});
			}
		}
		//豁免记录页面
		if(exemptRecordGrid){
			exemptRecordGrid.datagrid('resize',{
				width: $("#exempt_record").innerWidth()
			});
		}
		//逾期利息记录
		if(overdueInterestRecordGrid){
			overdueInterestRecordGrid.datagrid('resize',{
				width: $("#overdule_interest_record").innerWidth()
			});
		} 
		//还款计划
		if(paymentScheduleGrid){
			paymentScheduleGrid.datagrid('resize',{
				width: $("#payment_schedule").innerWidth()
			});
			paymentScheduleNaGrid.datagrid('resize',{
				width: $("#payment_schedule").innerWidth()
			});
		}
		//结息计划
		if(taxmentScheduleGrid){
			taxmentScheduleGrid.datagrid('resize',{
				width: $("#taxment_schedule").innerWidth()
			});
		}
		*/
	}, 1500);
}

function waitrepaybtn(waitrepayid){
  	parent.DialogUtils.openModalDialog(
  		"repayLoanAccount",
  		"待入账还款登记",
  		$.formatString("${contextPath}/CQ/repay/toWaitRepay.action?loanAccountId=${loanAccountDetail.id}&waitRepayId={0}", waitrepayid),
  		1100,580,function(){			
  			window.location.reload();
  	});
  	return false;
}

$(document).ready(function() {
	//alert('loanAccountDetail show.');
	$(document).bind('onStopResize',function(){
		resetGridWidth();
	});
	
	//页底部tab
	var tabsLoader = {};
	detailTabs = $("#detailTabs").tabs({
		onSelect : function(title,index, e, ui){
			var selectTab = $("#detailTabs").tabs('getTab',index);
			var $iframe = $(selectTab).find("iframe.loanAccountIframe");
			var uipanelId = $(selectTab).attr("id");
			if($iframe.size() == 1){
				var srcTemp = $iframe.attr("src");
				if($.ObjectUtils.isEmpty(srcTemp)){
                    $iframe.attr("src",$iframe.attr("newSrc"));
                }else{
                	if(false){
                		var srcTemp = $iframe.attr("src");
                        $iframe.attr("src",srcTemp);
                	}
                }
			}else{
				if(!tabsLoader[uipanelId]){
					tabsLoader[uipanelId] = $("#" + uipanelId);
					$("#" + uipanelId).trigger("firstSelected");
				}else{
					$("#" + uipanelId).trigger("selected");
				}
			}
		}
	});
	
	//逾期资料
	overdueInfoGrid = $("#overdueInfoGrid").datagrid({
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
	
	outsourceInfoGrid = $('#outsourceInfoGrid').datagrid({
		//url : '${contextPath}/outsourceAssignRecord/queryOutsourceAssignRecordList.action',
		queryParams:{
			loanAccountId:'${loanAccountDetail.id }'
		},
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		pagination : false,
		frozenColumns: [[ 
		{
			field : 'id',
			title : 'pk',
			width : 160,
			hidden : true
		},
		{
			field : 'commissionCalculateTypeValue',
			title : '逾期天数',
			width : 160,
			formatter: function(cellvalue, rowData, rowIndex){
				return cellvalue;
			}
		},
		{
			field : 'assignDate',
			title : '委托日期',
			width : 160
			,formatter: function(cellvalue, rowData, rowIndex){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return null;
				}
				var date = new Date();
				date.setTime(cellvalue);
				return date.format('yyyy-MM-dd');
			}
		},
		{
			field : 'outsourceCompany',
			title : '外包公司',
			width : 160
		},
		{
			field : 'percentage',
			title : '佣金比率',
			width : 160,
			formatter:function(cellvalue){
            	var percentage = cellvalue * 100;
            	return percentage+"%"; 
            }
		}
		]],
		columns: [[
		{
			field : 'amount',
			title : '委托金额',
			width : 120,
			formatter:function(cellvalue){
                if($.ObjectUtils.isEmpty(cellvalue)){
                    return 0;
                } else{
                 	return formatterMoney(cellvalue, 2);  
                }
            }
		},
		{
			field : 'receivedAmount',
			title : '还款金额',
			width : 120,
			formatter: function(cellvalue){
				if($.ObjectUtils.isEmpty(cellvalue)){
					return 0;
				} else {
					return formatterMoney(cellvalue,2);
				}
			}
		}]]
	});
	
	//暂缓grid
	postponeInfoGrid = $("#postponeInfoGrid").datagrid({
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
		frozenColumns: [[ 
		{
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		},
		{
			field : 'createDate',
			title : '交易日期',
			width : 160
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd');;
			}
		},
		{
			field : 'summary',
			title : '还款摘要',
			width : 160
		},
		{
			field : 'repayDate',
			title : '还款日',
			width : 160
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd');;
			}
		}
		]],
		columns: [[
		{
			field : 'repayPeriod',
			title : '期数',
			width : 200
		},
		{
   			field : 'sum',
   			title : '交易金额',
   			width : 200
   		}
		]],
	});
	
	//待入账grid
	waitAccountInfoGrid = $("#waitAccountInfoGrid").datagrid({
  		//url : '${contextPath}/CQ/revoke/queryWaitRepayList.action',
  		queryParams:{
  				loanAccountId:'${loanAccountDetail.id }'
  			},
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		onBeforeSelect : function(){return false;},
		onLoadSuccess: function(data){
			resetGridWidth();
		},
		frozenColumns: [[ 
		{
			field : 'rowid',
			title : 'pk',
			width : 160,
			hidden : true
		},
		{
   			field : 'companyName',
   			title : '所属公司',
   			width : 160,
   			hidden : true
   		},
   		{
   			field : 'bankAccountName',
   			title : '银行账户',
   			width : 160
   		},
		{
			field : 'createDate',
			title : '处理日期',
			width : 160
			,formatter: function(cellvalue, options, rowObject){
	   			var date = new Date();
	   			date.setTime(cellvalue);
	   			return date.format('yyyy-MM-dd');;
			}
		},
		{
  			field : 'repayDate',
  			title : '还款日期',
  			width : 160
  			,formatter: function(cellvalue, options, rowObject){
  	   			var date = new Date();
  	   			date.setTime(cellvalue);
  	   			return date.format('yyyy-MM-dd');;
  			}
  		},
		{
			field : 'summary',
			title : '还款摘要',
			width : 160
		}
		]],
		columns: [[
		{
  			field : 'waitRepayAmount',
  			title : '交易金额',
  			width : 100
  		},
  		{
  			field : 'repayAmount',
  			title : '已入账金额',
  			width : 150
  		},
  		{
  			field : 'remainderAmount',
  			title : '剩余金额',
  			width : 100
  		},
  		{
  			field : 'id',
  			title : '操作',
  			width : 150
  			,formatter: function(cellvalue, row, rowObject){
    			return $.formatString('<a onclick="waitrepaybtn(\''+row.id+'\')" href="#" class="easyui-linkbutton l-btn l-btn-plain" data-options="plain:true,iconCls:\'money_yen\'" group=""><span class="l-btn-left"><span class="l-btn-text money_yen l-btn-icon-left">待入账还款</span></span></a>');
  			}
  		},
		]],
	});
	
	exemptApplyInfoGrid = $('#exemptApplyInfoGrid').datagrid({
		//url : '${contextPath}/exemptSetting/queryExemptSettingListByLoanAccountIdAndStatus.action',
		queryParams:{
				loanAccountId:'${loanAccountDetail.id}',
				exemptSettingStatus:'APPROVE_PASSED_WAIT_EFFICTIVE'
			},
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : false,
		striped : true,
		singleSelect : true,
		pagination : false,
		frozenColumns: [[ 
		{
			field : 'row.id',
			title : 'pk',
			width : 160,
			hidden : true
		},
		{
			field : 'exemptType',
			title : '豁免类型',
			width : 160,
			formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue == 'FEEITEM_EXEMPT' ){
					return "费用项豁免";
				}
				return "提前结清豁免";
			}
		},
		{
			field : 'feeItem',
			title : '费用类型',
			width : 160
		},
		{
			field : 'period',
			title : '期数',
			width : 160
		},
		{
			field : 'createDate',
			title : '豁免申请日期',
			width : 160
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue == null){
					return null;
				}
				var date = new Date();
				date.setTime(cellvalue);
				return date.format('yyyy-MM-dd');
			}
		}
		
		]],
		columns: [[
		{
			field : 'sourceAmount',
			title : '豁免前金额',
			width : 120,
			hidden : true
		},
		{
			field : 'applyAmount',
			title : '豁免金额',
			width : 120
		},
		{
			field : 'targetAmount',
			title : '豁免后金额',
			width : 120,
			hidden : true
		},
		{
			field : 'effictiveAmount',
			title : '实际豁免金额',
			width : 120,
			hidden : true
		},
		{
			field : 'operatorName',
			title : '操作人',
			width : 160,
			hidden:true
		}
		]]
	});
	
});
//提前还款日期变更
function changeSettleDate(dp){
	var change = dp.cal.getDateStr() == dp.cal.getNewDateStr();
	if(!change){
		var repayDate = dp.cal.getNewDateStr();
		var loanAccountId = $("#loanAccountId").val();
		DialogUtils.progress({
	        text : '加载中，请等待....'
		});
		$.post(
			'${contextPath}/loanAccountDetail/changeSettleDate.action',
    		{loanAccountId: loanAccountId, repayDate: repayDate},
    		function(data){
    			DialogUtils.progress('close');
    			if(data){
    				$("#settleReceivableSum_LX").text(formatterMoney(data.settleReceivableSum_LX,2));
    				$("#settleReceivableSum_GLF").text(formatterMoney(data.settleReceivableSum_GLF,2));
    				$("#settleReceivableSum_YQLX").text(formatterMoney(data.settleReceivableSum_YQLX,2));
    				$("#settleReceivableSum_KQSBSXF").text(formatterMoney(data.settleReceivableSum_KQSBSXF,2));
    				$("#settleReceivableSum_WBYJ").text(formatterMoney(data.settleReceivableSum_WBYJ,2));
    				$("#settleReceivableSum_TQHKWYJ").text(formatterMoney(data.settleReceivableSum_TQHKWYJ,2));
    				$("#settleReceivableSum").text(formatterMoney(data.settleReceivableSum,2));
    				
    				$("#settleReceivableSum_WBYJ").text(formatterMoney(data.settleReceivableSum_WBYJ,2));
    				$("#settleReceivableSum_ZXHHK").text(formatterMoney(data.settleReceivableSum_ZXHHK,2));
    				$("#total").text(formatterMoney(data.total,2));
    			}else{
    				DialogUtils.tip("计算结清金额异常.");
    			}
		});
	}
}
</script>
<style type="text/css">
.form .nowrapTable table td {
    white-space: nowrap;
    overflow: hidden;
}
.form .nowrapTable table th {
    white-space: nowrap;
    overflow: hidden;
}
.form .nowrapTable table th{
	height: 26px;
}
.form .nowrapTable td table th { position:relative;}
.form .nowrapTable td table th .rightFloat{ position:absolute;right:2px;}
</style>
</head>

<body style="padding-right: 2px;">
	<div class="easyui-layout" data-options="fit:true,border:false">
		<c:if test="${isShowHead }">
		<div data-options="region:'north',border:false" 
			style="overflow: hidden;height: 26px">
			<div class="form">
				<table>
					<tr>
						<th width="8%">主贷人：</th>
						<td width="8%">${loanAccountDetail.clientName}</td>
						<th width="8%">证件号码：</th>
						<td width="10%">${loanAccountDetail.clientIdCardNumber }</td>
						<th width="8%">贷款产品：</th>
						<td>${loanAccountDetail.loanProductName}</td>
						<th>&nbsp;贷款期限：</th>
						<td>${loanAccountDetail.totalPeriod }</td>
						<th width="8%">贷款金额：</th>
						<td>${loanAccountDetail.loanAmount }</td>
						<th>实际放款金额：</th>
						<td>${loanAccountDetail.factOutLoanAmount}</td>
					</tr>
				</table>
			</div>
		</div>
		</c:if>
	
		<div id="center_layout_container" data-options="region:'center',border:false" title=""
			style="overflow-x:hidden;overflow-y:auto;">
			<input type="hidden" id="loanAccountId" value="${loanAccountDetail.id }"/>
			<div id="loanAccountDetail" class="form">
				<div class="panel-header" style="height: 18px;">
					<font style="font-weight: bold;">账户信息</font>
					<span style="position: relative; right: 0px;float: right;" class="common_table">
						提前还款日期：<input type="text" class="date" readonly="readonly" id="earlyRepayDate" onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'<fmt:formatDate value="${today}" pattern="yyyy-MM-dd"/>',onpicking:function(dp){changeSettleDate(dp)}})" value="<fmt:formatDate value="${loanAccountSettleDetail.settleDate }" pattern="yyyy-MM-dd"/>"/>
					</span>
				</div>
				<table border="1" class="nowrapTable">
					<tbody>
						<tr valign="top">
							<td width="23%" style="vertical-align: top;">
								<table>
									<tr>
										<th class="narrow" width="55%">贷款生效日期：</th>
										<td  align="right" width="45%">
											<fmt:formatDate value="${loanAccountDetail.effectiveDate}" pattern="yyyy/MM/dd"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">贷款到期日期：</th>
										<td  align="right">
											<fmt:formatDate value="${loanAccountDetail.expiryDate}" pattern="yyyy/MM/dd"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">贷款类别：</th>
										<td  align="right">
											${loanAccountDetail.loanType.name}
										</td>
									</tr>
									<tr>
										<th class="narrow">一次性手续费：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getFeeItemRatePercent('ZX_YCXSXF') + loanAccountDetail.getFeeItemRatePercent('DK_YCXSXF')}" pattern="#,##0.00#"/>%
											|
											<fmt:formatNumber value="${loanAccountDetail.getReceivableSum('MAIN','ZX_YCXSXF') + loanAccountDetail.getReceivableSum('MAIN','DK_YCXSXF')}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">保证金：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getFeeItemRatePercent('ZX_BZJ') + loanAccountDetail.getFeeItemRatePercent('DK_BZJ')}" pattern="#,##0.00#"/>%
											|
											<fmt:formatNumber value="${loanAccountDetail.getReceivableSum('MAIN','ZX_BZJ') + loanAccountDetail.getReceivableSum('MAIN','DK_BZJ')}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">利率费率(年)：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getFeeItemRatePercent('DK_LX')}" pattern="#,##0.0000#"/>%
										</td>
									</tr>
									<tr>
									    <th class="narrow">管理费率(年)：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getFeeItemRatePercent('ZX_GLF')}" pattern="#,##0.0000#"/>%
										</td>
									</tr>
									<tr>
									    <th class="narrow">逾期利息：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getFeeItemRatePercent('DK_YQLX')}" pattern="#,##0.0000#"/>%
										</td>
									</tr>
									<tr>
										<th class="narrow">贷前|贷后展期天数：</th>
										<td  align="right">
											${loanAccountDetail.beforeDelayDays} 
											| 
											${loanAccountDetail.afterDelayDays}</td>
									</tr>
									<tr>
										<th class="narrow">贷前|贷后展期费：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','ZX_DQYQF') + loanAccountDetail.getActualReceivedSum('MAIN','DK_DQYQF')}" pattern="#,##0.00#"/>
											| 
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','ZX_DHZQF') + loanAccountDetail.getActualReceivedSum('MAIN','DK_DHZQF')}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">贷前考察费：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','ZX_DQKCF')}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">还款方式：</th>
										<td  align="right">${loanAccountDetail.repayWay.name}</td>
									</tr>
									<tr>
										<th class="narrow">期数:</th>
										<td  align="right">${loanAccountDetail.time}</td>
									</tr>
									<tr>
										<th class="narrow">单位:</th>
										<td  align="right">${loanAccountDetail.timeUnitType.name}</td>
									</tr>
									<tr>
										<th class="narrow">扣款账户信息：</th>
										<td  align="right">&nbsp;</td>
									</tr>
								</table>
							</td>
							<td width="23%" style="vertical-align: top;">
								<table>
									<tr>
										<th class="narrow">放款日|起息日：</th>
										<td  align="right">
											<fmt:formatDate value="${loanAccountDetail.factLoanDate}" pattern="yyyy/MM/dd"/>
											&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
											<fmt:formatDate value="${loanAccountDetail.interestDate}" pattern="yyyy/MM/dd"/>
										</td>
									</tr>
									<tr>
										<th class="narrow" width="55%">扣款日/每月还款日：</th>
										<td  align="right" width="45%">
											${loanAccountDetail.monthlyRepayDay}
										</td>
									</tr>
									<tr>
										<th class="narrow">首期还款日期：</th>
										<td  align="right">
											<fmt:formatDate value="${loanAccountDetail.firstRepayDate}" pattern="yyyy/MM/dd"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">每月还款额：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.monthlyRepayAmount}" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									<tr>
										<th class="narrow">已付/期数：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.paidPeriod}" pattern="#,##0.00#"></fmt:formatNumber>
											 / 
											${loanAccountDetail.totalPeriod}</td>
									</tr>
									<tr>
										<th class="narrow">账户状态：</th>
										<td  align="right">
											${loanAccountDetail.status}
										</td>
									</tr>
									<tr>
									    <th class="narrow">逾期月数：</th>
										<td  align="right">
											M${loanAccountDetail.overdueMonthes }
										</td>
									</tr>
									<tr>
									    <th class="narrow">逾期天数：</th>
										<td  align="right">${loanAccountDetail.overdueDays }</td>
									</tr>
									<tr>
									    <th class="narrow">C/C1/C2：</th>
										<td  align="right">${loanAccountDetail.c0C1C2}</td>
									</tr>
									<tr>
										<th class="narrow">LOD/AOD/TOD：</th>
										<td  align="right">${loanAccountDetail.lodAodTod}</td>
									</tr>
									<tr>
										<th class="narrow">下次还款日期：</th>
										<td  align="right">
											<fmt:formatDate value="${loanAccountDetail.nextRepayDate}" pattern="yyyy/MM/dd"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">下次还款金额：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.nextRepayAmount}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">注销日期：</th>
										<td  align="right">${loanAccountDetail.writeOffDate}</td>
									</tr>
									<tr>
										<th class="narrow">注销金额：</th>
										<td  align="right">${loanAccountDetail.writeOffAmount}</td>
									</tr>
									<tr>
										<th class="narrow">注销原因：</th>
										<td  align="right">
											${loanAccountDetail.writeOffReason}
										</td>
									</tr>
								</table>
							</td>
							<td width="30%" style="vertical-align: top;">
								<table>
									<tr>
										<th class="narrow" width="60%"></th>
										<th class="narrow" align="right" width="40%" style="text-align: right;">融资公司</th>
									</tr>
									<tr>
										<th class="narrow">已收贷款总额：</th>
										<th align="center" class="narrow" style="text-align: center;">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedTotalSum('MAIN')}" pattern="#,##0.00#"></fmt:formatNumber>
										</th>
									</tr>
									<tr>
										<th class="narrow">本金已收：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_BJ') }" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									<tr>
										<th class="narrow">利息已收/豁免：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_LX')}" pattern="#,##0.00#"></fmt:formatNumber>
											 /<fmt:formatNumber value="${loanAccountDetail.getExemptSum('MAIN','DK_LX') }" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									<tr>
										<th class="narrow">管理费已收/豁免：</th>
										<td align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_GLF')}" pattern="#,##0.00#"></fmt:formatNumber>
											 /<fmt:formatNumber value="${loanAccountDetail.getExemptSum('MAIN','DK_GLF') }" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									<tr>
										<th class="narrow">已收一次性手续费：</th>
										<td align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_YCXSXF')}" pattern="#,##0.00#"/>	
										</td>
									</tr>
									<tr>
										<th class="narrow">保证金：</th>
										<td align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_BZJ')}" pattern="#,##0.00#"/>	
										</td>
									</tr>
									<!-- 
									<tr>
										<th class="narrow">已收投资阶段分润费用：</th>
										<td align="right">&nbsp;</td>
										<td align="right">
											&nbsp;
										</td>
									</tr>
									<tr>
										<th class="narrow">结算阶段分润费用应收/已收：</th>
										<td align="right">&nbsp;</td>
										<td align="right">
											&nbsp;
										</td>
									</tr>
									-->
									<tr>
										<th class="narrow">已收贷前延期费：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_DQYQF')}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">扣款失败手续费已收/豁免：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_KQSBSXF')}" pattern="#,##0.00#"/>/
											<fmt:formatNumber value="${loanAccountDetail.getExemptSum('MAIN','ZX_KQSBSXF')}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">逾期利息已收/豁免：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_YQLX')}" pattern="#,##0.00#"/>/
											<fmt:formatNumber value="${loanAccountDetail.getExemptSum('MAIN','DK_YQLX')}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">违约金已收/豁免：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_TQHKWYJ')}" pattern="#,##0.00#"/>/
											<fmt:formatNumber value="${loanAccountDetail.getExemptSum('MAIN','DK_TQHKWYJ') }" pattern="#,##0.00#"/> 
										</td>
									</tr>
									<tr>
										<th class="narrow">外包佣金已收/豁免：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getActualReceivedSum('MAIN','DK_WBYJ')}" pattern="#,##0.00#"/>/
											<fmt:formatNumber value="${loanAccountDetail.getExemptSum('MAIN','DK_WBYJ')}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">总利息已收/豁免/应收:</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getReceivableSum('MAIN','DK_LX') }" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									<!-- 
									<tr>
										<th class="narrow">总管理费</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.getReceivableSum('MAIN','ZX_GLF') }" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									 -->
									<tr>
										<th class="narrow">本金结余/递减本金结余：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountDetail.principalBalance}" pattern="#,##0.00#"/>
											 / <fmt:formatNumber value="${loanAccountDetail.principalBalanceIrr}" pattern="#,##0.00#"/>
										</td>
									</tr>
									<tr>
										<th class="narrow">超额还款：</th>
										<td  align="right">${loanAccountDetail.overRepayAmount}</td>
									</tr>
								</table>
							</td>
							<td width="24%" style="vertical-align: top;">
								<table>
									<tr>
										<th class="narrow">贷款额：</th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountSettleDetail.loanAmount }" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									<tr>
										<th class="narrow">已收本金：<span class="rightFloat">-</span></th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountSettleDetail.loanAmount - loanAccountSettleDetail.getPrincipalBalance('MAIN')}" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									<tr>
										<th class="narrow">本金结余：<span class="rightFloat">=</span></th>
										<td  align="right">
											<fmt:formatNumber value="${loanAccountSettleDetail.getPrincipalBalance('MAIN')}" pattern="#,##0.00#"></fmt:formatNumber>
										</td>
									</tr>
									<tr>
										<th class="narrow">应收利息：<span class="rightFloat">+</span></th>
										<td  align="right">
											<span id="settleReceivableSum_LX">
												<fmt:formatNumber value="${loanAccountSettleDetail.getSum('MAIN','DK_LX')}" pattern="#,##0.00#"></fmt:formatNumber>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">应收管理费：<span class="rightFloat">+</span></th>
										<td  align="right">
											<span id="settleReceivableSum_GLF">
												<fmt:formatNumber value="${loanAccountSettleDetail.getSum('MAIN','ZX_GLF')}" pattern="#,##0.00#"></fmt:formatNumber>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">逾期利息：<span class="rightFloat">+</span></th>
										<td  align="right"  id="overdueInterest">
											<span id="settleReceivableSum_YQLX">
												<fmt:formatNumber value="${loanAccountSettleDetail.getSum('MAIN','DK_YQLX') + loanAccountSettleDetail.getSum('MAIN','ZX_YQLX')}" pattern="#,##0.00#"/>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">扣款失败手续费 ：<span class="rightFloat">+</span></th>
										<td  align="right">
											<span id="settleReceivableSum_KQSBSXF">
												<fmt:formatNumber value="${loanAccountSettleDetail.getSum('MAIN','DK_KQSBSXF') + loanAccountSettleDetail.getSum('MAIN','ZX_KQSBSXF')}" pattern="#,##0.00#"/>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">历史未缴外包佣金:<span class="rightFloat">+</span></th>
										<td  align="right">
											<span id="settleReceivableSum_WBYJ">
												<fmt:formatNumber value="${loanAccountSettleDetail.getSum('MAIN','DK_WBYJ') + loanAccountSettleDetail.getSum('MAIN','ZX_WBYJ')}" pattern="#,##0.00#"/>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">提前结清违约金：<span class="rightFloat">+</span></th>
										<td  align="right">
											<span id="settleReceivableSum_TQHKWYJ">
												<fmt:formatNumber value="${loanAccountSettleDetail.getSum('MAIN','DK_TQHKWYJ')}" pattern="#,##0.00#"/>
											</span>
										</td>
									</tr>
									<tr>
									    <th class="narrow">提前还款金额：<span class="rightFloat">=</span></th>
										<td  align="right">
											<span id="settleReceivableSum">
												<fmt:formatNumber value="${loanAccountSettleDetail.getSettleSum()}" pattern="#,##0.00#"/>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">外包佣金：<span class="rightFloat">+</span></th>
										<td  align="right">
											<span id="settleReceivableSum_WBYJ">
												<fmt:formatNumber value="${loanAccountSettleDetail.getWBYJSum()}" pattern="#,##0.00#"/>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">注销回款：<span class="rightFloat">-</span></th>
										<td  align="right">
											<span id="settleReceivableSum_ZXHHK">
												<fmt:formatNumber value="${loanAccountSettleDetail.getZXHHKSum()}" pattern="#,##0.00#"/>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">应付合计：</th>
										<td  align="right">
											<span id="total">
												<fmt:formatNumber value="${loanAccountSettleDetail.getTotal()}" pattern="#,##0.00#"/>
											</span>
										</td>
									</tr>
									<tr>
										<th class="narrow">&nbsp;</th>
										<td  align="right">&nbsp;</td>
									</tr>
									<tr>
										<th class="narrow">&nbsp;</th>
										<td  align="right">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div id="overdueInfo" style="margin-top: 12px">
		        <div class="panel-header" style="font-weight: bold; height: 18px; width: 100%">逾期资料</div>
		        <table id="overdueInfoGrid">
		        	<thead data-options="frozen:true">
						<tr>
							<th data-options="field:'period',width:160">期数</th>
				        	<th data-options="field:'expireDate',width:160">到期日期</th>
				            <th data-options="field:'overdueAmount',width:160">逾期金额</th>
				            <th data-options="field:'overdueDay',width:160">逾期天数</th>
						</tr>
					</thead>
		          	<thead>
		            	<tr>
		                	<th data-options="field:'overdueInterestAmount',width:120">逾期利息</th>
		              		<th data-options="field:'deductFailFeeAmount',width:120">扣款失败手续费</th>
		              		<th data-options="field:'totalSum',width:120">合计</th>
		            	</tr>
		            </thead>
		          <tbody>
		            <c:set var="overdueAmount" value="0"></c:set>
		            <c:forEach items="${overdueInfoMapList }" var="psTemp">
		              <tr>
		                <td>${psTemp.get('period') }</td>
		                <td>${psTemp.get("expireDate") }</td>
		                <td>
		                	<fmt:formatNumber value="${psTemp.get('overdueAmount') }" pattern="#,##0.00#"/>
		                </td>
		                <td>${psTemp.get("overdueDay") }</td>
		                <td>${psTemp.get("overdueInterestAmount") }</td>
		                <td>${psTemp.get("deductFailFeeAmount") }</td>
		                <td>
		                	<fmt:formatNumber value="${psTemp.get('totalSum') }" pattern="#,##0.00#"/>
		                </td>
		              </tr>
		              <c:set var="overdueAmount" value="${overdueAmount + psTemp.get('overdueAmount') }"></c:set>
		            </c:forEach>
		            <tr>
			          	<c:set var="totalSum" value="0"></c:set>
			            <td>小计</td>
			            <td>-</td>
			            <td>
			            	<fmt:formatNumber value="${overdueAmount}" pattern="#,##0.00#"/>
			            	<c:set var="totalSum" value="${totalSum + overdueAmount }"></c:set>
			            </td>
			            <td>-</td>
			            <td>
			            	<fmt:formatNumber value="${loanAccountDetail.getSum('MAIN','DK_YQLX') + loanAccountDetail.getSum('MAIN','DK_YQLX')}" pattern="#,##0.00#"/>
			            	<c:set var="totalSum" value="${totalSum + loanAccountDetail.getSum('MAIN','DK_YQLX') + loanAccountDetail.getSum('MAIN','DK_YQLX')}"></c:set>
			            </td>
			            <td>
			            	<fmt:formatNumber value="${loanAccountDetail.getSum('MAIN','DK_KQSBSXF') + loanAccountDetail.getSum('MAIN','ZX_KQSBSXF')}" pattern="#,##0.00#"/>
			            	<c:set var="totalSum" value="${totalSum + loanAccountDetail.getSum('MAIN','DK_KQSBSXF') + loanAccountDetail.getSum('MAIN','ZX_KQSBSXF')}"></c:set>
			            </td>
			            <td>
			            	<fmt:formatNumber value="${totalSum}" pattern="#,##0.00#"/>
			            </td>
			          </tr>
		          </tbody>
	        	</table>
			</div>
			
			<div id="outsourceInfo" style="margin-top: 12px">
				<div  class="panel-header" style="font-weight: bold;height: 18px;">委外资料</div>
				<table id="outsourceInfoGrid" ></table> 
			</div>

			<div id="postponeInfo" style="margin-top: 12px">
				<div  class="panel-header" style="font-weight: bold;height: 18px;">暂缓交易</div>
				<table id="postponeInfoGrid" ></table> 
			</div>
			
            <div id="exemptApplyInfo" style="margin-top: 12px">
				<div  class="panel-header" style="font-weight: bold;height: 18px;">豁免申请</div>
				<table id="exemptApplyInfoGrid" ></table> 
			</div>
			
			<div id="waitAccountInfo" style="margin-top: 12px">
              	<div  class="panel-header" style="font-weight: bold;height: 18px;">待入账明细</div>
              	<table id="waitAccountInfoGrid" ></table> 
            </div>
			
			<div id="paymentRecordInfo" style="margin-top: 12px;">
				<div id="detailTabs" class="easyui-tabs" style="width: auto;fit:false">
					<!-- 还款记录 -->
					<div id="payment_record"  title="还款记录" data-options="border:false" style="width:auto">
						<%@include file="../queryRepaymentRecordList.jsp"%>
					</div>
					<!-- 还款记录 -->
					<div id="trading_record"  title="交易记录" data-options="border:false" style="width:auto">
						<%@include file="../queryTradingRecordList.jsp"%>
					</div>
					<!-- 豁免记录 -->
					<div id="exempt_record" title="豁免记录" data-options="border:false">
						<%@include file="../queryExemptRecordList.jsp"%>
					</div>
					<!-- 逾期利息记录 -->
					<div id="overdule_interest_record" title="逾期利息详情" data-options="border:false">
						 <%@include file="../queryOverdueInterestRecordList.jsp"%>
					</div>
					<!-- 还款计划 -->
					<div id="payment_schedule" title="还款计划" data-options="border:false">
						<%@include file="../queryPaymentScheduleList.jsp"%>
					</div>
					<!-- 结息计划 -->
					<div id="taxsettle_schedule" title="结息计划" data-options="border:false">
						<%@include file="../queryTaxSettleScheduleList.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" >
var repaymentRecordGrid = null;
var financeDetailGrid = null;

var paymentRecordMap = {};
var tempTradingRecordId = null;
var repaymentRecordGridFitColumnsFlag = true;

var showFinanceDetail = false;
$(document).ready(function(){
	var  $repayALink = $("#repayALink");
	$repayALink.linkbutton('enable');
	
	repaymentRecordGrid = $('#repaymentRecordGrid').datagrid({
		url:"${contextPath}/loanAccountDetail/queryRepaymentRecordDetailList.action?loanAccountId=${loanAccountDetail.id}",
		fit : false,
		fitColumns : repaymentRecordGridFitColumnsFlag,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		rowStyler : function(index,row){
			if(row.revoked){
				return "color:#FF4500"
			}else if(row.category && row.category.key.indexOf('REVOKE') >= 0){
				return "color:#FF4500"
			}else if(!row.received){
				return "color:blue"
			}
		},
		onLoadSuccess: function(data){
			//如果结果超过12行，将高度固定在380px
			var $grid = $(this);
			
			var timeoutHandler = setTimeout(function(){
				if(data['rows'].length > 12){
					$grid.datagrid('resize',{
						height: 380
					});
				}else{
					$grid.datagrid('resize',{
						height: 55 + 32 * ( data['rows'].length +1)
					});
				}
			}, 200);
			resetGridWidth();
		},
		loadFilter: function(data){
			var list = new Array();
			var suspendRecordList = [];
			$.each(data,function(index,record){
				if(!record.viewAble){
					//不可见的交易记录不进行显示
					return true;
				}
				if(record.category == 'SUSPEND' && !record.revoked && !record.received){
					//暂缓交易列表
					suspendRecordList.push(record);
				}
				if(!$.ObjectUtils.isEmpty(suspendRecordList)){
					//加载暂缓信息
					postponeInfoGrid.datagrid('loadData',suspendRecordList);
				}
				if(isHideRevokedTrading){
					//如果为隐藏被撤销交易，则将交易类型为撤销，或revoked的交易进行隐藏
					if(record.type.key.indexOf('REVOKE') >= 0 || record.revoked || record.type == '退款' || record.type == '暂缓'){
						return true;
					}
				}
				//paymentRecordMap[record.id] = record; 
				//record.tradingRecordType != '现金提前结清' &&
				if(!$.ObjectUtils.isEmpty(record.paymentRecordList) &&
						record.paymentRecordList.length > 1){
					var naTemp = null;
					$.each(record.paymentRecordList,function(index,item){
						if(item.period == 'NA'){
							//如果为NA则需要将NA存放，将其中各金额放入第一期
							naTemp = item;
							return false;
						}else{
							return true;
						}
					});
					
					var tradingSumViewAble = true;
					var needUnionNAItem = true;
					$.each(record.paymentRecordList,function(index,item){
						if(item.period == 'NA'){
							//NA期还款
							return true;
						}
						//if(item.sum == 0){
						//	return true;
						//}
						var obj = {};
						obj["id"] = record.id;
						obj["tradingRecord"] = record;
						obj["category"] = record.category;
						obj["type"] = record.type;
						
						obj["received"] = record.received;
						obj["revoked"] = record.revoked;
						obj["createDate"] = record.createDate;
						obj["summary"] = record.summary;
						
						obj["period"] = item.period;
						obj["repayDate"] = item.repayDate;
						obj["expireDate"] = item.expireDate;
						obj["principalBalance"] = item.principalBalance;
						
						if(tradingSumViewAble){
							obj["sum"] = record.repaySum;
							tradingSumViewAble = false;
						};
						if(naTemp && needUnionNAItem){
							obj["paymentRecordEntryMap"] = $.extend({},item.paymentRecordEntryMap,naTemp.paymentRecordEntryMap);
							needUnionNAItem = false;
						}else{
							obj["paymentRecordEntryMap"] = $.extend({},item.paymentRecordEntryMap);
						}
						list.push(obj);
						
						paymentRecordMap[item.id] = obj;
					});
				}else{
					var obj = {};
					obj["id"] = record.id;
					obj["tradingRecord"] = record;
					obj["category"] = record.category;
					obj["type"] = record.type;
					
					obj["received"] = record.received;
					obj["revoked"] = record.revoked;
					obj["createDate"] = record.createDate;
					obj["summary"] = record.summary;
					
					obj["period"] = record.currentPeriod;
					obj["repayDate"] = record.repayDate;
					obj["expireDate"] = record.expireDate;
					obj["principalBalance"] = record.principalBalance;
					
					obj["sum"] = record.repaySum;
					obj["paymentRecordEntryMap"] = {};
					if(record.paymentRecordEntryList){
						//如果有实收记录分项
						$.each(record.paymentRecordEntryList,function(index,preTemp){
							if(!obj["paymentRecordEntryMap"][preTemp.feeItem]){
								obj["paymentRecordEntryMap"][preTemp.feeItem] = preTemp;
							}else{
								obj["paymentRecordEntryMap"][preTemp.feeItem].amount = obj["paymentRecordEntryMap"][preTemp.feeItem].amount + preTemp.amount;
							}
						});
					}
					list.push(obj);
					
					paymentRecordMap[record.id] = obj;
				};
			});
			var res = {total:0,rows:[]};
			res['total'] = list.size;
			res['rows'] = list;
			return res;
		},
		frozenColumns: [[ 
		{
			title : '主键',
			field : 'id',
			width : 160,
			hidden : true
		},
		{
			field : 'period',
			title : '期数',
			width : 80
		},
		{
			field : 'createDate',
			title : '处理日期',
			width : 80
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue){
		  			var date = new Date();
		  			date.setTime(cellvalue);
		  			return date.format('yyyy-MM-dd');
				}
				return null;
			}
		},
		{
			field : 'repayDate',
			title : '还款日期',
			width : 80
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue){
		  			var date = new Date();
		  			date.setTime(cellvalue);
		  			return date.format('yyyy-MM-dd');
				}
				return null;
			}
		},
		{
			field : 'expireDate',
			title : '到期时间',
			width : 80
			,formatter: function(cellvalue, rowData, rowIndex){
				if(cellvalue){
		  			var date = new Date();
		  			date.setTime(cellvalue);
		  			return date.format('yyyy-MM-dd');
				}
				return null;
			}
		},
   		{
   			field : 'summary',
   			title : '还款摘要',
   			width : 160
   			,formatter: function(value, row, index){
   				return value;
   			}
   		},
   		{
   			field : 'sum',
   			title : '还款金额',
   			width : 120
   			,formatter: function(value, rowData, rowIndex){
   				var summary = rowData.summary;
				if(summary && (summary.indexOf('发放贷款') >= 0 || summary.indexOf('贷款退回') >= 0)){
					var sum = 0;
					$.each(rowData.paymentRecordEntryMap,function(feeItemTemp,entry){
						sum += entry.amount;
					});
					return sum;
				}else{
					return value;
				}
   			}
   		},
  		{
			field : 'BJ',
			title : '本金',
			width : 120
			,formatter: function(value, row, index){
				var value = 0;
				if(row.paymentRecordEntryMap["DK_BJ"]){
					var entry = row.paymentRecordEntryMap["DK_BJ"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
			}
	 	},
   		{
			field : 'GLF',
			title : '管理费',
			width : 80,
			formatter: function(value, row, index){
				var value = 0;
				if(row.paymentRecordEntryMap["ZX_GLF"]){
					var entry = row.paymentRecordEntryMap["ZX_GLF"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
   			}
		},
		{
			field : 'LX',
			title : '利息',
			width : 80,
			formatter: function(value, row, index){
				var value = 0;
				if(row.paymentRecordEntryMap["DK_LX"]){
					var entry = row.paymentRecordEntryMap["DK_LX"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
   			}
		},
		{
  			field : 'principalBalance',
  			title : '本金结余',
  			width : 120
  		}
   		]],
		columns: [[
   		{
   			field : 'YCXSXF',
   			title : '一次性手续费',
   			width : 80
   			,formatter: function(value, row, index){
   				var value = 0;
				if(row.paymentRecordEntryMap["DK_YCXSXF"]){
					var entry = row.paymentRecordEntryMap["DK_YCXSXF"];
					value += entry.amount ? entry.amount : 0;
				}
				if(row.paymentRecordEntryMap["ZX_YCXSXF"]){
					var entry = row.paymentRecordEntryMap["ZX_YCXSXF"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
   			}
   		},
  		{
   			field : 'ZQF',
   			title : '展期费',
   			width : 80
   			,formatter: function(value, row, index){
   				var value = 0;
				if(row.paymentRecordEntryMap["ZX_DQYQF"]){
					var entry = row.paymentRecordEntryMap["ZX_DQYQF"];
					value += entry.amount ? entry.amount : 0;
				}
				if(row.paymentRecordEntryMap["DK_DQYQF"]){
					var entry = row.paymentRecordEntryMap["DK_DQYQF"];
					value += entry.amount ? entry.amount : 0;
				}
				if(row.paymentRecordEntryMap["ZX_DHZQF"]){
					var entry = row.paymentRecordEntryMap["ZX_DHZQF"];
					value += entry.amount ? entry.amount : 0;
				}
				if(row.paymentRecordEntryMap["DK_DHZQF"]){
					var entry = row.paymentRecordEntryMap["DK_DHZQF"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
   			}
   		},
   		{
   			field : 'KQSBSXF',
   			title : '扣款失败手续费',
   			width : 80
   			,formatter: function(value, row, index){
   				var value = 0;
				if(row.paymentRecordEntryMap["ZX_KQSBSXF"]){
					var entry = row.paymentRecordEntryMap["ZX_KQSBSXF"];
					value += entry.amount ? entry.amount : 0;
				}
				if(row.paymentRecordEntryMap["DK_KQSBSXF"]){
					var entry = row.paymentRecordEntryMap["DK_KQSBSXF"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
   			}
   		},
   		{
   			field : 'YQLX',
   			title : '逾期利息',
   			width : 80,
   			formatter: function(value, row, index){
				var value = 0;
				if(row.paymentRecordEntryMap["ZX_YQLX"]){
					var entry = row.paymentRecordEntryMap["ZX_YQLX"];
					value += entry.amount ? entry.amount : 0;
				}
				if(row.paymentRecordEntryMap["DK_YQLX"]){
					var entry = row.paymentRecordEntryMap["DK_YQLX"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
   			}
   		},
   		{
   			field : 'TQHKWYJ',
   			title : '违约金',
   			width : 80
   			,formatter: function(value, row, index){
				var value = 0;
				if(row.paymentRecordEntryMap["DK_TQHKWYJ"]){
					var entry = row.paymentRecordEntryMap["DK_TQHKWYJ"];
					value += entry.amount ? entry.amount : 0;
				}
				if(row.paymentRecordEntryMap["ZX_YQLX"]){
					var entry = row.paymentRecordEntryMap["ZX_YQLX"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
   			}
   		},
		{
  			field : 'WBYJ',
  			title : '外包佣金',
  			width : 80
  			,formatter: function(value, row, index){
				var value = 0;
				if(row.paymentRecordEntryMap["ZX_WBYJ"]){
					var entry = row.paymentRecordEntryMap["ZX_WBYJ"];
					value += entry.amount ? entry.amount : 0;
				}
				if(row.paymentRecordEntryMap["DK_WBYJ"]){
					var entry = row.paymentRecordEntryMap["DK_WBYJ"];
					value += entry.amount ? entry.amount : 0;
				}
				return value;
  			}
 		}
 		]],
   		toolbar : '#repaymentRecordGridToolbar',
   		onClickRow : function(index, row){
   			var paymentRecord = paymentRecordMap[row.id];
   			if(paymentRecord.tradingRecord != null && tempTradingRecordId != null && paymentRecord.tradingRecord.id == tempTradingRecordId){
   				return ;
   			}
   			tempTradingRecordId = paymentRecord.tradingRecord.id;
   			///*
   			DialogUtils.progress({
   		        text : '加载中，请等待....'
   			});
   			$.post('${contextPath}/loanAccountDetail/findTradingRecordDetail.action',{tradingRecordId : paymentRecord.tradingRecord.id},
	    		function(data){
		 			var tradingRecord = data.tradingRecord;
	    			DialogUtils.progress('close');
	    			if(data){
	    				$("#tradingRecordId").val(tradingRecord.id);
	    	   			$("#adjust").val(tradingRecord.adjust ? "true" : "false");
	    	   			$("#relatedTradingRecordId").val(tradingRecord.adjust ? tradingRecord.relatedTradingId : "");
	    				if(showFinanceDetail){
	    	   				var tradingRecordId = tradingRecord.id;
	    	   				if(tradingRecordId && tradingRecordId != ""){
	    	   					financeDetail();
	    	   				}
	    	   			}
	    				var organizationName = "";
	    				if(data.organizationName){
	    					organizationName = data.organizationName;
	    				}
	    				$("#organizationName").text(organizationName);
	    				$("#paymentRecordDetail").css("display","");
			   			var createDate = new Date(tradingRecord.createDate);
			   			$("#createDate").text(createDate.format("yyyy-MM-dd hh:mm:ss"));
			   			$("#operatorName").text(data.operatorName);
			   			var lastUpdateOperatorName = data.lastUpdateOperatorName;
			   			$("#lastUpdateOperatorName").text(lastUpdateOperatorName);
			   			var lastUpdateDate = new Date(tradingRecord.lastUpdateDate);
			   			$("#lastUpdateDate").text(lastUpdateDate.format("yyyy-MM-dd hh:mm:ss"));
			   			$("#bankAccountName").text(data.bankAccountName);
			   			var revokeResean = "";
			   			if(!$.ObjectUtils.isEmpty(tradingRecord.revokeResean)){
			   				revokeResean = tradingRecord.revokeResean;
			   			}
			   			$("#revokeResean").text(revokeResean);
			   			var remark = "";
			   			if(!$.ObjectUtils.isEmpty(tradingRecord.remark)){
			   				remark = tradingRecord.remark;
			   			}
			   			$("#remark").text(remark);
			   			
			   			var lxEntry = tradingRecord.feeItem2PaymentAmountMap["DK_LX"];
			   			var lxValue = 0;
			   			if(lxEntry){
			   				lxValue = lxEntry.amount;
			   			}
			   			$("#lxValue").text(lxValue);
			   			var bjEntry = tradingRecord.feeItem2paymentMapMap["DK_BJ"];
			   			var bjValue = 0;
			   			if(bjEntry){
			   				bjValue = bjEntry.amount;
			   			}
			   			$("#bjValue").text(bjValue);
			   			var glfEntry = tradingRecord.feeItem2paymentMapMap["ZX_GLF"];
			   			var glfValue = 0;
			   			if(glfEntry){
			   				glfValue = glfEntry.amount;
			   			}
			   			$("#glfValue").text(glfValue);
			   			
			   			var damageFeeEntry = tradingRecord.feeItem2paymentMapMap["提前还款违约金"];
			   			var damageFeeIrr = 0;
			   			if(damageFeeEntry){
			   				damageFeeIrr = damageFeeEntry.amountIrr;
			   			}
			   			$("#damageFeeIrr").text(damageFeeIrr);
			   			
			   			var principalBalanceIrr = 0;
			   			if(tradingRecord.principalBalanceIrr){
			   				principalBalanceIrr = tradingRecord.principalBalanceIrr;
			   			}
			   			$("#principalBalanceIrr").text(principalBalanceIrr);
					}else{
						//parent.DialogUtils.tip("还款失败");
					}
 				}
			);
   		}
	});
	
	financeDetailGrid = $('#financeDetailGrid').datagrid({
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		onLoadSuccess: function(data){
			resetGridWidth();
			$(this).datagrid('tooltip');
		},
		columns: [[ {
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		},
		{
			field : 'companyName',
			title : '公司名称',
			width : 200
		},
		{
			field : 'accountTitleCode',
			title : '科目代码',
			width : 120
		},
		{
			field : 'debitCreditType',
			title : '借贷类型',
			width : 120
		},
		{
			field : 'name',
			title : '借贷方名',
			width : 150
		},
		{
			field : 'amount',
			title : '平息金额',
			width : 120
		},
		{
			field : 'amountIrr',
			title : '递减金额',
			width : 120
		},
		{
			field : 'provinceName',
			title : '核算省',
			width : 120
		},
		{
			field : 'cityName',
			title : '核算市',
			width : 120
		},
		{
			field : 'areaName',
			title : '核算地区',
			width : 120
		},
		{
			field : 'branchName',
			title : '核算分行',
			width : 120
		},
		{
			field : 'creditProductName',
			title : '核算产品',
			width : 120
		}
		]],
		toolbar:"#financeDetailGridToolbar"
	});
	//页面加载完成后
	$showRevokedBtn = $("#showRevokedBtn");
	$hideRevokedBtn = $("#hideRevokedBtn");
});
/**
 * 现金还款
 */
function repayFun() {
	parent.DialogUtils.openModalDialog(
		"repay",
		"还款",
		$.formatString("${contextPath}/loanAccountRepay/toRepay.action?loanAccountId=${loanAccountDetail.id}"),
		1100,409,
		function(){window.location.reload();}
	);
	return false;
}
/**
 * 撤销还款
 */
function revokeRepayFun(){
	$.post("${contextPath}/loanAccountRevoke/getRevokeAbleRepayTradingRecords.action",{loanAccountId:'${loanAccountDetail.id}'},function(data){
		if($.ObjectUtils.isEmpty(data)){
			DialogUtils.alert("提醒：", "不存在可被撤销的交易记录.", "info");
		}else{
			var tradingRecordId  = data[0].id;
			parent.DialogUtils.openModalDialog(
				"revoke",
				"撤销还款",
				$.formatString("${contextPath}/loanAccountRevoke/toRevoke.action?revokeCategory=REVOKE_REPAY_TRADING&loanAccountId=${loanAccountDetail.id}&revokeTradingRecordId={0}",tradingRecordId),
				800,350,function(){
				window.location.reload();
			});
		}
	});
	return false;
}
/*
 * 豁免
 */
function exemptFun() {
	DialogUtils.openModalDialog(
		"exempt",
		"豁免",
		$.formatString("${contextPath}/loanAccountExempt/toExempt.action?loanAccountId=${loanAccountDetail.id}"),
		1100,375,
		function(){window.location.reload();}
	);
	return false;
}
/**
 * 财务明细
 */
function financeDetail(){
	showFinanceDetail = true;
	var tradingRecordId = $("#tradingRecordId").val();
	var relatedTradingRecordId = $("#relatedTradingRecordId").val();
	var adjust = $("#adjust").val();
	if(tradingRecordId == ""){
		DialogUtils.tip("没有选中还款记录");
		return false;
	}
	$.post('${contextPath}/glRecord/queryGLRecordList.action',{tradingRecordId:tradingRecordId},function(data){
		if(!$.ObjectUtils.isEmpty(data)){
			financeDetailGrid.datagrid('loadData',data);
			if(adjust == 'true' && !$.ObjectUtils.isEmpty(relatedTradingRecordId)){
				$.post('${contextPath}/glRecord/queryGLRecordList.action',{tradingRecordId:relatedTradingRecordId,'companyKeys[]':['GD_SZ_ZX']},function(otherData){
					if(!$.ObjectUtils.isEmpty(otherData)){
						$.each(otherData,function(index,rowTemp){
							financeDetailGrid.datagrid('insertRow',{index:0,row:rowTemp});
						});
					}
				});
			}
		}else{
			financeDetailGrid.datagrid('loadData',{total:0,rows:[]});
		}
	});
}
var $showRevokedBtn = null;
var $hideRevokedBtn = null;
var isHideRevokedTrading = false;
function hideRevoked(){
	isHideRevokedTrading = true;
	//
	$hideRevokedBtn.hide();
	$showRevokedBtn.show();
	repaymentRecordGrid.datagrid('reload');
}

function showRevoked(){
	isHideRevokedTrading = false;
	//
	$showRevokedBtn.hide();
	$hideRevokedBtn.show();
	repaymentRecordGrid.datagrid('reload');
}

function printFun() {
	DialogUtils.progress({
        text : '加载中，请等待....'
	});
	
	DialogUtils.openModalDialog(
		"printRepaymentRecord",
		"打印还款记录",
		$.formatString("${contextPath}/P2P/print/toPrint.action?loanAccountId=${loanAccountDetail.id}"),
		650,285,function(){
	});
	return false;
}

function delayApplyFun() {
	$.post(
   		'${contextPath}/P2P/delay/isExistWaitApproveDelayApply.action',
   		{loanAccountId:'${loanAccountDetail.id}'},
   		function(data){
   			if(data){
   				DialogUtils.alert("提醒","当前操作账户尚存在待处理的展期申请。","warning");
   			}else{
   				DialogUtils.progress({
   			        text : '加载中，请等待....'
   				});
   				DialogUtils.openModalDialog(
   						"addDelayApply",
   						"展期申请",
   						$.formatString("${contextPath}/CQ/delay/toDelayApply.action?loanAccountId=${loanAccountDetail.id}"),
   						900,285,function(){
   							window.location.reload();
   					});
   			}
    });
	return false;
}

function revokeDelayTradingFun() {
	$.post(
   		'${contextPath}/P2P/delay/isExistRevokeAbleDelayTradingRecord.action',
   		{loanAccountId:'${loanAccountDetail.id}'},
   		function(data){
   			if(data){
   				DialogUtils.progress({
   			        text : '加载中，请等待....'
   				});
   				DialogUtils.openModalDialog(
   						"revokeDelayApply",
   						"撤销展期申请交易",
   						$.formatString("${contextPath}/CQ/delay/toRevokeDelayTradingRecord.action?loanAccountId=${loanAccountDetail.id}"),
   						900,285,function(){
   							window.location.reload();
   					});
   			}else{
   				DialogUtils.alert("提醒","当前操作账户不存在可被撤销的展期交易。","warning");
   			}
    });
	return false;
}

</script>