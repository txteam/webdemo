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
var $tabs = null;
var $easyuitabs = null;
$(document).ready(function(){
	var $tabs = $("#tabs");
	$easyuitabs = $tabs.tabs({
        fit : true,
        border : false,
        onSelect:function(title){
			var selectedTab = $tabs.tabs('getTab',title);
			var $iframe = $(selectedTab).find("iframe.tabiframe");
			if($iframe.attr('src') == ''){
				$iframe.attr('src',$iframe.attr('newsrc'));
			}
		}
    });
});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<div id="tabs" style="overflow:hidden">
			<div title="入金明细" style="overflow-x:hidden,overflow-y:auto">
				<iframe class="tabiframe" src="${contextPath }/clientAccountTrading/toQueryRechargeRecordPagedList.action" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
			</div>
			<div title="出金明细" style="overflow-x:hidden,overflow-y:auto">
				<iframe class="tabiframe" src="" newsrc="${contextPath }/clientAccountTrading/toQueryCashoutRecordPagedList.action" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
			</div>
			<div title="转账明细" style="overflow-x:hidden,overflow-y:auto">
				<iframe class="tabiframe" src="" newsrc="${contextPath }/clientAccountTrading/toQueryTransferRecordPagedList.action" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
			</div>
			<div title="资金明细" style="overflow-x:hidden,overflow-y:auto">
				<iframe class="tabiframe" src="" newsrc="${contextPath }/clientAccountTrading/toQueryTradingDetailPagedList.action" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
			</div>
		</div>
	</div>
</div>
</body>