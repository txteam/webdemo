<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>queryRechargeRecordPagedList</title>
    <%@include file="../includes/commonHead.jsp" %>
    <script type="text/javascript">
        //权限判定


        var grid = null;
        var idFieldName = 'id';
        <%!//FIXME: 修改名字字段 --%>
        var nameFieldName = 'serialNumber';
        <%!//FIXME: 修改实体名称 --%>
        var entityName = '充值记录';

        $(document).ready(function () {
            var $toPassBtn = $("#toPassBtn");
            var $toDistributionBtn = $("#toDistributionBtn");
            var $toLedger = $("#toLedger");
            var $ledgerToDistribution = $("#ledgerToDistribution");
            var $ledgerToRevoked = $("#ledgerToRevoked");

            grid = $('#grid').datagrid({
                url: '${contextPath}/rechargeRecord/queryRechargeRecordPagedList.action',
                queryParams: {status: "WAIT_APPROVE",paymentChannel:"${paymentChannel}"},
                fit: true,
                fitColumns: true,
                border: false,
                idField: 'id',
                checkOnSelect: false,
                selectOnCheck: false,
                nowrap: false,
                striped: true,
                singleSelect: true,
                pagination: true,
                pageSize: 10,
                pageList: [10, 20, 30, 40, 50],
                loadFilter: function (data) {
                    var res = {total: 0, rows: []};
                    if (!$.ObjectUtils.isEmpty(data)
                        && !$.ObjectUtils.isEmpty(data.list)) {
                        res['total'] = data.count;
                        res['rows'] = data.list;
                    }
                    return res;
                },
                frozenColumns: [[{
                    field: 'row.id',
                    title: 'pk',
                    width: 150,
                    hidden: true
                }]],
                columns: [[
                    {
                        field: 'serialNumber',
                        title: '流水号',
                        width: 200
                    },
                    {
                        field: 'clientUserName',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '用户名',
                        width: 200
                    },

                    {
                        field: 'paymentChannel',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '渠道类型',
                        width: 200
                        , formatter: baseJsonformatter
                    },
                    {
                        field: 'beforeSum',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '交易前金额',
                        width: 200
                        ,formatter:moneyFormatter
                    },
                    {
                        field: 'sum',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '交易金额',
                        width: 200
                        ,formatter:moneyFormatter
                    },
                    {
                        field: 'afterSum',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '交易后金额',
                        width: 200
                        ,formatter:moneyFormatter
                    },
                    {
                        field: 'status',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '状态',
                        width: 200
                        , formatter: baseJsonformatter
                    },


                    {
                        field: 'remark',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '备注',
                        width: 200

                    },
                    {
                        field: 'approveDate',
                        title: '审批时间',
                        width: 200
                        , formatter: function (cellvalue, options, rowObject) {
                        var date = new Date();
                        date.setTime(cellvalue);
                        return date.format('yyyy-MM-dd hh:mm:ss');
                    }
                    },

                    {
                        field: 'approveRemark',
                        title: '审批备注',
                        hidden: true,
                        width: 200

                    }, {
                        field: 'attributes',
                        title: '入金属性',
                        hidden: true,
                        width: 200

                    },
                    {
                        field: 'createDate',
                        title: '申请时间',
                        width: 200
                        , formatter: function (cellvalue, options, rowObject) {
                        var date = new Date();
                        date.setTime(cellvalue);
                        return date.format('yyyy-MM-dd hh:mm:ss');
                    }
                    }
                ]],
                toolbar: '#toolbar',
                onDblClickRow: function (index, row) {
//                    if ($.canUpdate) {
//                        editFun(row[idFieldName], row[nameFieldName]);
//                    }
                },
                onClickRow: function (index, row) {
                    if (row['status'].key == 'WAIT_APPROVE') {
                        $toPassBtn.linkbutton('enable');
                        $toDistributionBtn.linkbutton('enable');
                        $toLedger.linkbutton('enable');
                        $ledgerToDistribution.linkbutton('disable');
//                        $ledgerToRevoked.linkbutton('disable');
                    } else if (row['status'].key == 'LEDGER') {
                        $toPassBtn.linkbutton('disable');
                        $toDistributionBtn.linkbutton('disable');
                        $toLedger.linkbutton('disable');
                        $ledgerToDistribution.linkbutton('enable');
//                        $ledgerToRevoked.linkbutton('enable');
                    } else {
                        //alert(row['status'].key);
                        $toPassBtn.linkbutton('disable');
                        $toDistributionBtn.linkbutton('disable');
                        $toLedger.linkbutton('disable');
                        $ledgerToDistribution.linkbutton('disable');
//                        $ledgerToRevoked.linkbutton('disable');
                    }

                },
                onLoadSuccess: function () {
                    $(this).datagrid('unselectAll');
                    $(this).datagrid('tooltip');

                    $toPassBtn.linkbutton('disable');
                    $toDistributionBtn.linkbutton('disable');
                    $toLedger.linkbutton('disable');
                    $ledgerToDistribution.linkbutton('disable');
//                    $ledgerToRevoked.linkbutton('disable');
                }
            });
        });
        /*
         * 查询
         */
        function queryFun() {
            grid.datagrid('load', $('#queryForm').serializeObject());
            return false;
        }
        /*
         * 审批通过
         */
        function approveToPassFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认删除指定的rechargeRecord
            DialogUtils.confirm("确认提醒",
                $.formatString("是否确认审批通过{0}:[{1}]?", entityName, name),
                function (data) {
                    if (data) {
                        DialogUtils.progress({
                            text: '数据提交中，请等待....'
                        });
                        //如果确认删除指定的rechargeRecord
                        $.post(
                            '${contextPath}/rechargeRecord/approveToPass.action',
                            {rechargeApplyId: id},
                            function (data) {
                                DialogUtils.progress('close');
                                if (data) {
                                    DialogUtils.tip("审批通过" + entityName + "成功");
                                } else {
                                    $.formatString("审批通过{0}:{1}失败.指定{0}可能已经被其他管理员所删除.如果指定{0}依然存在，请联系系统管理员.", entityName, name);
                                }
                                grid.datagrid('load', $('#queryForm').serializeObject());
                            });
                    }
                });
            return false;
        }
        /*
         * 审批至挂账
         */
        function approveToLedgerFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认删除指定的rechargeRecord
            DialogUtils.confirm("确认提醒",
                $.formatString("是否确认审批{0}至挂账:[{1}]?", entityName, name),
                function (data) {
                    if (data) {
                        DialogUtils.progress({
                            text: '数据提交中，请等待....'
                        });
                        //如果确认删除指定的rechargeRecord
                        $.post(
                            '${contextPath}/rechargeRecord/approveToLedger.action',
                            {rechargeApplyId: id},
                            function (data) {
                                DialogUtils.progress('close');
                                if (data) {
                                    DialogUtils.tip("审批至挂账" + entityName + "成功");
                                } else {
                                    $.formatString("审批至挂账{0}失败.记录可能已经被处理.如果有疑问，请联系系统管理员.", name);
                                }
                                grid.datagrid('load', $('#queryForm').serializeObject());
                            });
                    }
                });
            return false;
        }
        /*
         * 审批挂账至分配
         */
        function approveLedgerToDistributeFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认删除指定的rechargeRecord
            DialogUtils.openModalDialog(
                "distribute",
                $.formatString("充值挂账申请单至分配[{0}]", name),
                $.formatString("${contextPath}/rechargeRecord/toQueryClientInfoForDistribute.action?status=LEDGER&rechargeApplyId={0}", id),
                1000, 520, function () {
                    grid.datagrid('load', $('#queryForm').serializeObject());
                });
            return false;
        }
        /*
         * 审批至分配
         */
        function approveToDistributeFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认删除指定的rechargeRecord
            DialogUtils.openModalDialog(
                "distribute",
                $.formatString("充值申请单审批至分配[{0}]", name),
                $.formatString("${contextPath}/rechargeRecord/toQueryClientInfoForDistribute.action?status=WAIT_APPROVE&rechargeApplyId={0}", id),
                1000, 520, function () {
                    grid.datagrid('load', $('#queryForm').serializeObject());
                });
            return false;
        }


        function approveLedgerToRevokedFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认删除指定的rechargeRecord
            DialogUtils.confirm("确认提醒",
                $.formatString("是否确认撤销挂账申请{0}?", name),
                function (data) {
                    if (data) {
                        DialogUtils.progress({
                            text: '数据提交中，请等待....'
                        });
                        //如果确认删除指定的rechargeRecord
                        $.post(
                            '${contextPath}/rechargeRecord/approveLedgerToRevoke.action',
                            {rechargeApplyId: id},
                            function (data) {
                                DialogUtils.progress('close');
                                if (data) {
                                    DialogUtils.tip("撤销挂账申请" + name + "成功");
                                } else {
                                    $.formatString("撤销挂账申请{0}失败.可能已经被处理.如有疑问，请联系系统管理员.", name);
                                }
                                grid.datagrid('load', $('#queryForm').serializeObject());
                            });
                    }
                });
            return false;
        }

    </script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
    <!--//FIXME: 修改查询条件框体高度 -->
    <div data-options="region:'north',title:'查询条件',border:false" style="height: 140px; overflow: hidden;">
        <form id="queryForm" class="form">
            <input type="hidden" name="paymentChannel" value="${paymentChannel}">
            <table class="table table-hover table-condensed">
                <tr>
                    <th>客户电话:</th>
                    <td><input id="phoneNumber" name="phoneNumber"/></td>
                    <th>姓名/机构名称:</th>
                    <td><input id="userName" name="userName"/></td>
                    <th>身份证/营业执照号 :</th>
                    <td><input id="idCardNumber" name="idCardNumber"/></td>
                </tr>

                <tr>
                    <th>状态:</th>
                    <td>
                        <select id="status" name="status">
                            <option value="">全部</option>
                            <c:forEach items="${statusEnums }" var="temp">
                                <option value="${temp.key }"
                                        <c:if test="${temp.key == 'WAIT_APPROVE' }">selected='selected'</c:if>>${temp.name }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>申请时间:</th>
                    <td><input style="width: 150px;" id="minCreateDate" name="minCreateDate" readonly="readonly"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
                        - <input style="width: 150px;" id="maxCreateDate" name="maxCreateDate" readonly="readonly"
                                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>

                    <%--<th >渠道</th>--%>
                    <%--<td ><select id="paymentChannel" name="paymentChannel">--%>
                        <%--<option value="">全部</option>--%>
                        <%--<c:forEach items="${paymentChannelEnums }" var="temp">--%>
                            <%--<option value="${temp.key }"--%>
                                    <%--<c:if test="${temp.key ==  paymentChannel}">selected='selected'</c:if>>${temp.name }</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select></td>--%>
                </tr>
                <tr>
                    <td colspan="6" class="button operRow">
                        <a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);"
                           class="easyui-linkbutton" data-options="iconCls:'search'">查询</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false">
        <table id="grid"></table>
    </div>

    <div id="toolbar" style="display: none;">
        <c:if test='${authContext.hasAuth("approve_to_pass") }'>
            <a id="toPassBtn" onclick="approveToPassFun();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'pencil_add'">审批至通过</a>
        </c:if>
        <c:if test='${authContext.hasAuth("approve_to_distribution") }'>
            <a id="toDistributionBtn" onclick="approveToDistributeFun();" href="javascript:void(0);"
               class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">审批至分配</a>
        </c:if>
        <c:if test='${authContext.hasAuth("approve_to_redger") }'>
            <a id="toLedger" onclick="approveToLedgerFun();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'pencil_add'">审批至挂账</a>
        </c:if>
        <c:if test='${authContext.hasAuth("approve_ledger_to_distribution") }'>
            <a id="ledgerToDistribution" onclick="approveLedgerToDistributeFun();" href="javascript:void(0);"
               class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">挂账分配</a>
        </c:if>
        <%--<c:if test='${authContext.hasAuth("approve_ledger_to_revoke") }'>--%>
        <%--<a id="ledgerToRevoked" onclick="approveLedgerToRevokedFun();" href="javascript:void(0);"--%>
        <%--class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">挂账撤销</a>--%>
        <%--</c:if>--%>
        <a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);"
           class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
    </div>
</div>
</body>