<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" >
    function resetGridWidth(){
        var timeoutHandler = null;
        if(timeoutHandler){
            clearTimeout(timeoutHandler);
        }
        timeoutHandler = setTimeout(function(){
// 		if(receiptRecordGrid){
// 			if($(document).innerWidth() > 1400&& !receiptRecordGridFitColumnsFlag ){
// 				receiptRecordGridFitColumnsFlag = true;
// 				receiptRecordGrid.datagrid({fitColumns:true});
// 			}else if($(document).innerWidth() <= 1400&& !receiptRecordGridFitColumnsFlag ){
// 				receiptRecordGridFitColumnsFlag = false;
// 				receiptRecordGrid.datagrid({fitColumns:false});
// 			}
// 			receiptRecordGrid.datagrid('resize',{
// 				width: $("#container").innerWidth()-17
// 			});

// 			clientProfitRecordGrid.datagrid('resize',{
// 				width: $("#container").innerWidth()-17
// 			});
// 		}
//            if(clientInvestItemGrid){
//                clientInvestItemGrid.datagrid('resize',{
//                    width: $("#container").innerWidth()-17
//                });
// 			clientInvestItemNaGrid.datagrid('resize',{
// 				width: $("#container").innerWidth()-17
// 			});
            }
            if(clientAccountItemGrid){
                clientAccountItemGrid.datagrid('resize',{
                    width: $('#container').innerWidth(),
                    height: 'auto'
                });
            }
            if(clientAccountItemGrid){
                investScheduleGrid.datagrid('resize',{
                    width: $('#container').innerWidth(),
                    height: 'auto'
                });
            }

            if(clientProfitRecordGrid){
                clientProfitRecordGrid.datagrid('resize',{
                    width: $('#container').innerWidth(),
                    height: 'auto'
                });
            }
            //充值
            if(clientRechargeRecordGrid){
                clientRechargeRecordGrid.datagrid('resize',{
                    width: $('#container').innerWidth(),
                    height: 'auto'
                });
            }
            //提现
            if(clientCashoutRecordGrid){
                clientCashoutRecordGrid.datagrid('resize',{
                    width: $('#container').innerWidth(),
                    height: 'auto'
                });
            }
            //付款
            if(clientTransferRecordGrid){
                clientTransferRecordGrid.datagrid('resize',{
                    width: $('#container').innerWidth(),
                    height: 'auto'
                });
            }
            //明细
            if(clientTradingDetailRecordGrid){
                clientTradingDetailRecordGrid.datagrid('resize',{
                    width: $('#container').innerWidth(),
                    height: 'auto'
                });
            }

        }, 500);
    }
    $(document).ready(function(){
        $(document).bind('onStopResize',function(){
            resetGridWidth();
        });
    });

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
	height: 20px;
}
.form .nowrapTable td table th { position:relative;}
.form .nowrapTable td table th .rightFloat{ position:absolute;right:2px;}
</style>
</head>
<body>
<div class="easyui-layout" 
	data-options="fit:true,border:false" style="overflow-y: auto ">
	<div data-options="region:'north',border:false" style="overflow: hidden;height: 200px;">
		<div class="form">
		  <table>
				<tr>
					<th class="narrow" width="10%">客户姓名:</th>
					<td class="narrow"  style="text-align: left;;" width="10%">
						${baseClientInfo.userName} 
					</td>
					<th class="narrow" width="10%">证件号码:</th>
					<td class="narrow"  style="text-align: left;" width="10%">
						${baseClientInfo.idCardNumber}
					</td>				
					<th class="narrow" width="10%">电话号码:</th>
					<td class="narrow"  style="text-align: left;" width="10%">
						${baseClientInfo.mobilePhoneNumber}
					</td>
					<th class="narrow" width="10%">客户类型:</th>
					<td class="narrow"  style="text-align: left;" width="10%">
						${baseClientInfo.type.name} 
					</td>	
					<th class="narrow" width="10%">账户创建时间:</th>
					<td class="narrow"  style="text-align: left;" width="10%">
							<fmt:formatDate value="${clientAccount.createDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
					</td>										
				</tr>					
		   </table>
	  	</div>
		<div class="nav_header datagrid-toolbar">
		   <div class="panel-title panel-with-icon">客户账户信息</div>
		</div>
		<div class="form">
			<input type="hidden" value="${clientAccount.id}"  id="clientAccountId"/>
			<input type="hidden" value="${clientAccount.clientId }" id="clientId">
			<table border="1" class="nowrapTable">
			<tbody>
			<tr>
					<td width="25%" style="vertical-align: top;">
				<table>
					<%--<tr>--%>
						<%--<th class="narrow" width="10%">资产总额:</th>--%>
						<%--<td class="narrow"  style="text-align: left;" width="15%">--%>
							<%--<fmt:formatNumber value="${clientAccount.assetSum} " pattern="#,##0.00#"/>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr>
						<th class="narrow" width="10%">客户账户总金额:</th>
						<td class="narrow"  style="text-align: left;"  width="15%">
							<fmt:formatNumber value="${clientAccount.sum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >冻结资金:</th>
						<td class="narrow"  style="text-align: left;">
							<fmt:formatNumber value="${clientAccount.frozenSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >近日日期:</th>
						<td>
							<fmt:formatDate value="${clientAccount.currentDayDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
						</td>
					</tr>					<tr>
						<th class="narrow" >近日日累计充值:</th>
						<td class="narrow"  style="text-align: left;">
							<fmt:formatNumber value="${clientAccount.currentDayRechargeSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
						<tr>
						<th class="narrow" >近日日累计提现:</th>
						<td class="narrow"  style="text-align: left;">
							<fmt:formatNumber value="${clientAccount.currentDayCashoutSum} " pattern="#,##0.00#"/>
						</td>
					</tr>


				</table>
			</td>

					<td width="25%" style="vertical-align: top;">
						<table>
							<tr>
								<th class="narrow"  width="10%">累计充值总额:</th>
								<td class="narrow"  style="text-align: left;" width="15%">
									<fmt:formatNumber value="${clientAccount.rechargeSum} " pattern="#,##0.00#"/>
								</td>
							</tr>
							<tr>
								<th class="narrow"  width="10%">累计提现总额:</th>
								<td class="narrow"  style="text-align: left;" width="15%">
									<fmt:formatNumber value="${clientAccount.cashoutSum} " pattern="#,##0.00#"/>
								</td>
							</tr>
							<tr>
								<th class="narrow" >累计转入金额:</th>
								<td class="narrow"  style="text-align: left;">
									<fmt:formatNumber value="${clientAccount.transferInSum} " pattern="#,##0.00#"/>
								</td>
							</tr>
							<tr>
								<th class="narrow" >累计转出金额:</th>
								<td class="narrow"  style="text-align: left;">
									<fmt:formatNumber value="${clientAccount.transferOutSum} " pattern="#,##0.00#"/>
								</td>
							</tr>
							<tr>
								<th class="narrow" >最后更新日期:</th>
								<td class="narrow"  style="text-align: left;">
									<fmt:formatDate value="${clientAccount.lastUpdateDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
								</td>
							</tr>
													</table>
					</td>
					<td width="25%" style="vertical-align: top;">
						<table>
							<tr>
								<th class="narrow" > + 可使用金额:</th>
								<td class="narrow"  style="text-align: left;">
									<fmt:formatNumber value="${clientAccount.availableSum} " pattern="#,##0.00#"/>
								</td>
							</tr>
							<tr>
								<th class="narrow" width="10%"> + 待结算金额:</th>
								<td class="narrow"  style="text-align: left;"  width="15%">
									<fmt:formatNumber value="${clientAccount.waitSettleSum} " pattern="#,##0.00#"/>
								</td>
							</tr>
							<tr>
								<th class="narrow" > - 冻结总金额:</th>
								<td class="narrow"  style="text-align: left;">
									<fmt:formatNumber value="${clientAccount.frozenSum} " pattern="#,##0.00#"/>
								</td>
							</tr>

							<tr>
								<th class="narrow">&nbsp;</th>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<th class="narrow" width="10%"> = 客户账户总金额:</th>
								<td class="narrow"  style="text-align: left;"  width="15%">
									<fmt:formatNumber value="${clientAccount.sum} " pattern="#,##0.00#"/>
								</td>
							</tr>


						</table>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
	</div>
	
	<div id="container" data-options="region:'center',border:false" 
		title="" style="overflow:hidden">
		<div class="easyui-tabs" data-options="fit:true,border:false"
			style="overflow:hidden">
			<div title="客户子账户" style="overflow-x:hidden;overflow-y:auto">
				<div>
					<%@include file="./queryClientAccountItemList.jsp" %>
				</div>
			</div>


<!-- 			<div title="客户投资账户" style="overflow-x:hidden,overflow-y:auto"> -->
<!-- 				<div> -->
<%-- 					<%@include file="./queryClientInvestItemAccountList.jsp" %> --%>
<!-- 				</div> -->
<!-- 				<div> -->
<%-- 					<%@include file="./querySettleScheduleMapList.jsp" %> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div title="分润信息" style="overflow-x:hidden,overflow-y:auto"> -->
<!-- 				<div> -->
<%-- 					<%@include file="./queryClientInvestProfitRecordList.jsp" %> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			<div title="充值信息" style="overflow-x:hidden;overflow-y:auto">
				<div>
					<%@include file="./queryRechargeRecordList.jsp" %>
				</div>
			</div>
			<div title="提现信息" style="overflow-x:hidden;overflow-y:auto">
				<div>
					<%@include file="./queryCashoutRecordList.jsp" %>
				</div>
			</div>
			<div title="转账记录" style="overflow-x:hidden;overflow-y:auto">
				<div>
					<%@include file="./queryTransferApplyRecordToUserDetailPagedList.jsp" %>
				</div>
			</div>
			<div title="资金明细" style="overflow-x:hidden;overflow-y:auto">
				<div>
					<%@include file="./queryTradingDetailRecordList.jsp" %>
				</div>
			</div>
		</div>
	</div>
</div>
</body>