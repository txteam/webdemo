<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addBaseClientInfo</title>
<%@include file="../includes/commonHead.jsp"%>

<script type="text/javascript">
	$ (document).ready (function (){
	    parent.DialogUtils.progress ('close');
	    //验证器
	    $ ('#form').validator (
	    {
		    valid : function ()
		    {
			    //表单验证通过，提交表单到服务器
			    DialogUtils.progress (
			    {
				    text : '数据提交中，请等待....'
			    });
			    $ ('#form').ajaxSubmit (
			    {
			        url : "${contextPath }/cashoutRecord/cashoutApplySuccess.action",
			        success : function (data)
			        {
				        DialogUtils.progress ('close');
				        if (data)
				        {
					        $ (".disableButton").linkbutton ('enable');
					        parent.DialogUtils.tip ("提现成功处理完成.");
					        parent.DialogUtils.closeDialogById ("cashoutApplySuccess");
				        }
				        else
				        {
					        parent.DialogUtils.tip ("提现成功处理失败。");
				        }
			        }
			    });
			    return false;
		    }
	    });
	    //是否收取提现手续费
	    $ (":radio[name='poundageSumCheck']").change (function ()
	    {
		    caculatefactSum ();
	    });
	 	//手续费支付方
	    $ ("#handlingFeeExpendWay").change (function ()
	    {
		    caculatefactSum ();
	    });
	  	//手续费金融
	    $ ("#poundageSum").change (function ()
	    {
		    caculatefactSum ();
	    });
    });
	
    function caculatefactSum ()
    {
	    var poundageSumCheck = $ (":radio[name='poundageSumCheck']:checked").val ();
	    
	    var handlingFeeExpendWay = $ ("#handlingFeeExpendWay").val ();
	    var sum = parseFloat ($ ("#sum").val ()).toFixed (2);
	    if (poundageSumCheck === 'true')
	    {
		    $ ("#poundageSum").removeAttr ("readonly");
		    if (handlingFeeExpendWay == 'TRANSFER_AMOUNT')
		    {
			    var poundageSum = parseFloat ($ ("#poundageSum").val ()).toFixed (2);
			    $ ("#factSum").val ((sum - poundageSum).toFixed (2));
		    }
		    else
		    {
			    $ ("#factSum").val ((sum * 1).toFixed (2));
		    }
	    }
	    else
	    {
		    $ ("#commissionFeeAmount").attr ("readonly", "readonly");
		    $ ("#commissionFeeAmount").val (0);
		    $ ("#factSum").val ((sum * 1).toFixed (2));
	    }
    }
    function submitFun ()
    {
	    caculatefactSum ();
	    $ ("#form").submit ();
    }
    function cancelFun ()
    {
	    parent.DialogUtils.closeDialogById ("cashoutApplySuccess");
    }
</script>
</head>
<body>
	<div class="easyui-layout" id="container" data-options="fit:true,border:false" style="overflow-y: auto">
		<form method="post" class="form" id="form" name="form">
			<table class="common_table">
				<tr>
					<th class="narrow" width="30%">用户名(交易账号):</th>
					<td width="70%"><input type="hidden" name="serialNumber" id="serialNumber" class="text" value="${cashoutRecord.serialNumber }" readonly="readonly" />
						<input type="hidden" id="cashoutRecordId" name="cashoutRecordId" value="${cashoutRecord.id }" />
						<input name="clientLoginName" id="clientLoginName" class="text" value="${cashoutRecord.clientLoginName }" readonly="readonly" /></td>
				</tr>
				<tr>
					<th class="narrow" width="30%">客户姓名:</th>
					<td width="70%"><input name="clientUserName" id="cardName" class="text" value="${cashoutRecord.clientUserName }" readonly="readonly" /></td>
				</tr>
				<tr>
					<th class="narrow" width="30%">提现金额:</th>
					<td width="70%"><input name="sum" id="sum" class="text" value="${cashoutRecord.sum }" readonly="readonly" /></td>
				</tr>
				<tr>
					<th class="narrow" width="30%">是否收取提现手续费:</th>
					<td width="70%">
						<input type="radio" value="true" id="poundageSumCheck" name="poundageSumCheck" checked="checked" />是
						<input type="radio" value="false" id="poundageSumCheckF" name="poundageSumCheck" />否</td>
				</tr>
				<tr>
					<th class="narrow" width="30%">手续费支付方:</th>
					<td width="70%"><select id="handlingFeeExpendWay" name="handlingFeeExpendWay" class="select" readonly="true">
							<%--<option value="PLATEFORM">平台</option>--%>
							<option value="TRANSFER_AMOUNT" selected >客户提现金额扣取</option>
					</select></td>
				</tr>
				<tr>
					<th class="narrow" width="30%">提现手续费金额:</th>
					<td width="70%"><input type="text" id="poundageSum" name="poundageSum" value="0" /></td>
				</tr>
				<tr>
					<th class="narrow" width="30%">实际转账金额:</th>
					<td width="70%"><input name="factSum" id="factSum" class="text" value="${cashoutRecord.sum}" readonly="readonly" /></td>
				</tr>
				<tr>
					<th class="narrow" width="30%">备注:</th>
					<td width="70%"><textarea name="remark" id="remark" class="text-area" ></textarea></td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px"><a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a> <a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>