<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>addBaseClientInfo</title>
    <%@include file="../includes/commonHead.jsp" %>

    <script type="text/javascript">
        $(document).ready(function () {
            parent.DialogUtils.progress('close');

            //验证器
            $('#form').validator({
                valid: function () {
                    //表单验证通过，提交表单到服务器
                    DialogUtils.progress({
                        text: '数据提交中，请等待....'
                    });
                    $('#form').ajaxSubmit({
                        url: "${contextPath }/clientAccount/transferBalance.action",
                        success: function (data) {
                            DialogUtils.progress('close');
                            if (data) {
                                $(".disableButton").linkbutton('enable');
                                parent.DialogUtils.tip("扎差申请处理完成--请在出金申请进行审批.");
                                parent.DialogUtils.closeDialogById("cashoutSuccess");
                            } else {
                                parent.DialogUtils.tip("提现成功处理失败。");
                            }
                        }
                    });
                    return false;
                }
            });

            $(":radio[name='needHandlingFee']").change(function () {
                caculateFactTransferAmount();
            });
            $("#handlingFeeExpendWay").change(function () {
                caculateFactTransferAmount();
            });
            $("#handlingFeeAmount").change(function () {
                caculateFactTransferAmount();
            });
        });
        function caculateFactTransferAmount() {
            var isNeedHandlingFee = $(":radio[name='needHandlingFee']:checked").val();
            var handlingFeeExpendWay = $("#handlingFeeExpendWay").val();
            var amount = parseFloat($("#amount").val()).toFixed(2);
            if (isNeedHandlingFee === 'true') {
                $("#handlingFeeAmount").removeAttr("readonly");
                if (handlingFeeExpendWay == 'TRANSFER_AMOUNT') {
                    var handlingFeeAmount = parseFloat($("#handlingFeeAmount").val()).toFixed(2);
                    ;
                    $("#factTransferAmount").val((amount - handlingFeeAmount).toFixed(2));
                } else {
                    $("#factTransferAmount").val((amount * 1).toFixed(2));
                }
            } else {
                $("#handlingFeeAmount").attr("readonly", "readonly");

                $("#handlingFeeAmount").val(0);
                $("#factTransferAmount").val((amount * 1).toFixed(2));
            }
        }
        function submitFun() {
            caculateFactTransferAmount();
            $("#form").submit();
        }
        function cancelFun() {
            parent.DialogUtils.closeDialogById("cashoutSuccess");
        }
    </script>
</head>
<body>
<div class="easyui-layout" id="container" data-options="fit:true,border:false" style="overflow-y: auto ">
    <form method="post" class="form" id="form" name="form">
        <input type="hidden" name="fromPaymentChannel" id="fromPaymentChannel" class="text"
               value="${clientAccountItem.paymentChannel }" readonly="readonly"/>
        <table class="common_table">
            <tr>
                <th class="narrow" width="30%">客户姓名:</th>
                <td width="70%">
                    <input name="accountName" id="accountName" class="text"
                           value="${clientAccountItem.accountName }" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <th class="narrow" width="30%">转出渠道:</th>
                <td width="70%">
                    ${clientAccountItem.paymentChannel.name }
                </td>
            </tr>
            <tr>
                <th class="narrow" width="30%">子账户可用金额:</th>
                <td width="70%">
                    ${clientAccountItem.availableAmount}
                </td>
            </tr>
            <tr>
                <th class="narrow" width="30%">扎差金额:</th>
                <td width="70%">
                    <input name="amount" id="amount" class="text"
                           value="" />
                </td>
            </tr>
            <tr>
                <th class="narrow" width="30%">转入的渠道:</th>
                <td width="70%">
                    <select id="toPaymentChannel" name="toPaymentChannel" class="select">
                        <c:forEach items="${paymentChannels}" var="temp">
                            <c:if test="${temp != clientAccountItem.paymentChannel}">
                                <option value="${temp.key}"> ${temp.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>

                </td>
            </tr>
            <tr>
                <th class="narrow" width="30%">备注:</th>
                <td width="70%">
                    <textarea name="remark" id="remark" class="text-area" cols="30" rows="3"></textarea>
                </td>
            </tr>
            <tr>
                <td class="rightOperRow" colspan="4" style="padding-right: 50px">
                    <a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>
                    <a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>