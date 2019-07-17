<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="nav_header datagrid-toolbar">
   <div class="panel-title panel-with-icon">客户投资项账户信息</div>
</div>
<div class="form">
	<table border="1" class="nowrapTable">
	<tbody>
		<tr>
			<td width="25%" style="vertical-align: top;">
				<table>
					<tr>
						<th class="narrow" width="10%">资产总额:</th>
						<td class="narrow"  style="text-align: center;" width="15%">
							<fmt:formatNumber value="${clientAccount.assetSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" width="10%">客户账户总金额:</th>
						<td class="narrow"  style="text-align: center;"  width="15%">
							<fmt:formatNumber value="${clientAccount.sum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >冻结资金:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.frozenSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >可使用金额:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.availableSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >体验金额:</th>
						<td class="narrow"  style="text-align: center;">
							${clientAccount.experienceGoldSum} 
						</td>
					</tr>
					<tr>	
						<th class="narrow" >冻结体验金:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.frozenExperienceGoldSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<th class="narrow" >投资中体验金金额:</th>
					<td class="narrow"  style="text-align: center;">
						<fmt:formatNumber value="${clientAccount.investExperienceGoldSum} " pattern="#,##0.00#"/>
					</td>
					<tr>
						<th class="narrow" width="10%">可使用体验金:</th>
						<td class="narrow"  style="text-align: center;" width="15%">
							<fmt:formatNumber value="${clientAccount.availableExperienceGoldSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
			<td width="25%" style="vertical-align: top;">
				<table>
					<tr>
						<th class="narrow" >应收总金:=</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.receivableSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >应收本金金额:+</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.receivablePrincipalSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >应收收入金额:+</th>
						<td class="narrow"  style="text-align: center;" >
							<fmt:formatNumber value="${clientAccount.receivableIncomeSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >实收总金额:=</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.actualReceivedSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >实收本金金额:+</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.actualReceivedPrincipalSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >实收收入金额:+</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.actualReceivedIncomeSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >待回收总金额:=</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.waitReceiveSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >待回收本金:+</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.waitReceivePrincipalSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >待回收收入金额:+</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.waitReceiveIncomeSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
				</table>
			</td>
			<td width="25%" style="vertical-align: top;">
				<table>
					<tr>
						<th class="narrow"  width="10%">累计充值总额:</th>
						<td class="narrow"  style="text-align: center;" width="15%">
							<fmt:formatNumber value="${clientAccount.rechargeSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow"  width="10%">累计提现总额:</th>
						<td class="narrow"  style="text-align: center;" width="15%">
							<fmt:formatNumber value="${clientAccount.cashoutSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >累计投资总额:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.investSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" >下次结算计算日:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatDate value="${clientAccount.nextReceivableIncomeSettleDate}" pattern="yyyy/MM/dd"/>
						</td>					
					</tr>
					<tr>
						<th class="narrow" >最后更新日期:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatDate value="${clientAccount.lastUpdateDate}" pattern="yyyy/MM/dd HH:mm:ss"/>
						</td>
					</tr>
					<tr>
						<th class="narrow">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th class="narrow">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th class="narrow">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th class="narrow">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
			<td width="25%" style="vertical-align: top;">
				<table>
					<tr>
						<th class="narrow" width="10%"> + 客户账户总金额:</th>
						<td class="narrow"  style="text-align: center;"  width="15%">
							<fmt:formatNumber value="${clientAccount.sum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" > - 冻结资金:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.frozenSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" > = 可使用金额:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.availableSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th class="narrow" width="10%"> + 客户账户总金额:</th>
						<td class="narrow"  style="text-align: center;"  width="15%">
							<fmt:formatNumber value="${clientAccount.sum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" > + 待回收本金:</th>
						<td class="narrow"  style="text-align: center;">
							<fmt:formatNumber value="${clientAccount.waitReceivePrincipalSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow" width="10%"> = 资产总额:</th>
						<td class="narrow"  style="text-align: center;" width="15%">
							<fmt:formatNumber value="${clientAccount.assetSum} " pattern="#,##0.00#"/>
						</td>
					</tr>
					<tr>
						<th class="narrow">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th class="narrow">&nbsp;</th>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</tbody>
	</table>	
</div>