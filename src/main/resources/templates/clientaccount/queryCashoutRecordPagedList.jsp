<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>queryCashoutRecordPagedList</title>
    <%@include file="../includes/commonHead.jsp" %>
    <script type="text/javascript">
        //权限判定
        $.canAdd = false;
        $.canUpdate = false;
        $.canDelete = false;
        <c:if test='${authContext.hasAuth("add_cashoutRecord")}'>
        $.canAdd = true;
        </c:if>
        <c:if test='${authContext.hasAuth("delete_cashoutRecord")}'>
        $.canDelete = true;
        </c:if>
        <c:if test='${authContext.hasAuth("update_cashoutRecord")}'>
        $.canUpdate = true;
        </c:if>

        var grid = null;
        var idFieldName = 'id';
        <%!//FIXME: 修改名字字段 --%>
        var nameFieldName = 'clientUserName';
        <%!//FIXME: 修改实体名称 --%>
        var entityName = '提现';

        $(document).ready(function () {
            var $editALink = $("#editALink");
            var $deleteALink = $("#deleteALink");

            grid = $('#grid').datagrid({
                url: '${contextPath}/cashoutRecord/queryCashoutRecordPagedList.action',
                queryParams:{status:"WAIT_APPROVE",paymentChannel:"${paymentChannel}"},
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
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '流水号',
                        width: 200
                    },
                    {
                        field: 'clientUserName',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '客户名称',
                        width: 200
                    },
                    {
                        field: 'paymentChannel',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '渠道',
                        width: 200,
                        formatter: baseJsonformatter
                    },
                    {
                        field: 'bankInfo',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '银行卡信息',
                        width: 200
                        ,formatter:function(cellValue,index,rowObject){
                            return cellValue.name;
                        }
                    },
                    {
                        field: 'bankCardNumber',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '银行卡号',
                        width: 200
                    },
                    {
                        field: 'type',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '类型',
                        width: 200
                    },
                    {
                        field: 'status',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '状态',
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
                        title: '提现金额',
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
                        field: 'poundageSum',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '手续费',
                        width: 200
                        ,formatter:moneyFormatter
                    },
                    {
                        field: 'factSum',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '实际到账金额',
                        width: 200
                        ,formatter:moneyFormatter
                    },
                    <%--{--%>
                        <%--field: 'lastUpdateDate',--%>
                        <%--<%!//FIXME: 修改属性中文名 &ndash;%&gt;--%>
                        <%--title: 'lastUpdateDate',--%>
                        <%--width: 200--%>
                        <%--, formatter: function (cellvalue, options, rowObject) {--%>
                        <%--var date = new Date();--%>
                        <%--date.setTime(cellvalue);--%>
                        <%--return date.format('yyyy-MM-dd hh:mm:ss');--%>
                        <%--;--%>
                    <%--}--%>
                    <%--},--%>

                    {
                        field: 'approveDate',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '审批时间',
                        width: 200
                        , formatter: function (cellvalue, options, rowObject) {
                        var date = new Date();
                        date.setTime(cellvalue);
                        return date.format('yyyy-MM-dd hh:mm:ss');
                        ;
                    }
                    },
                    {
                        field: 'approveRemark',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '审批备注',
                        width: 200
                    },
                    {
                        field: 'remark',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '备注',
                        width: 200
                    },
                    {
                        field: 'applyDate',
                        <%!//FIXME: 修改属性中文名 --%>
                        title: '申请时间',
                        width: 200
                        , formatter: function (cellvalue, options, rowObject) {
                        var date = new Date();
                        date.setTime(cellvalue);
                        return date.format('yyyy-MM-dd hh:mm:ss');
                        ;
                    }
                    }
                    <c:if test="${show_grid_action == true}">
                   /* , {
                        field: 'action',
                        title: '操作',
                        width: 220,
                        formatter: function (value, row, index) {
                            var str = '&nbsp;';
                            if ($.canUpdate) {
                                str += $.formatString('<img onclick="editFun(\'{0}\',\'{1}\');" src="{2}" title="编辑"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil.png');
                                str += '&nbsp;';
                            }

                            if ($.canDelete) {
                                str += $.formatString('<img onclick="deleteFun(\'{0}\',\'{1}\');" src="{2}" title="删除"/>', row[idFieldName], row[nameFieldName], '${contextPath}/style/images/extjs_icons/pencil_delete.png');
                                str += '&nbsp;';
                            }
                            return str;
                        }
                    }*/
                    </c:if>
                ]],
                toolbar: '#toolbar',
                onDblClickRow: function (index, row) {
                    if ($.canUpdate) {
                        editFun(row[idFieldName], row[nameFieldName]);
                    }
                },
                onClickRow: function (index, row) {
                    $editALink.linkbutton('enable');
                    $deleteALink.linkbutton('enable');
                },
                onLoadSuccess: function () {
                    $(this).datagrid('unselectAll');
                    $(this).datagrid('tooltip');

                    $editALink.linkbutton('disable');
                    $deleteALink.linkbutton('disable');
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

        function approveToPassFun(id, clientUserName) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                clientUserName = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return;
            }
            DialogUtils.progress({
                text: '加载中，请等待....'
            });
            DialogUtils.openModalDialog(
                "cashoutApplySuccess",
                "同意提现:" + clientUserName,
                $.formatString("${contextPath}/cashoutRecord/toCashoutApplySuccess.action?cashoutRecordId={0}", id),
                500, 300, function () {
                    grid.datagrid('load', $('#queryForm').serializeObject());
                });
            return false;
        }

        function refuseFun(id, clientUserName) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                clientUserName = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return;
            }
            DialogUtils.progress({
                text: '加载中，请等待....'
            });
            DialogUtils.openModalDialog(
                "cashoutApplyFail",
                "拒绝提现:" + clientUserName,
                $.formatString("${contextPath}/cashoutRecord/toCashoutApplyFail.action?cashoutRecordId={0}", id),
                500, 250, function () {
                    grid.datagrid('load', $('#queryForm').serializeObject());
                });
            return false;
            return false;
        }

        function getBankStatus(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0]['serialNumber'];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认禁用指定BaseClientInfo
            DialogUtils.confirm(
                "确认提醒",
                $.formatString("是否确认获取 [{0}]最新 支付信息?", name),
                function (data) {
                    if (data) {
                        DialogUtils.progress({
                            text: '数据提交中，请等待....'
                        });
                        //如果确认启用指定BaseClientInfo
                        $.post(
                            '${contextPath}/cashoutRecord/getCashoutNewStatus.action',
                            {cashoutRecordId: id},
                            function () {
                                DialogUtils.progress('close');
                                DialogUtils.tip("获取支付信息" + entityName + "成功");
                                grid.datagrid('reload', $('#queryForm').serializeObject());
                            });
                    }
                });
            return false;
        }
        /*
         * 新增
         */
        /*function addFun() {
         DialogUtils.progress({
         text : '加载中，请等待....'
         });
         DialogUtils.openModalDialog(
         "addCashoutRecord",
         "新增" + entityName,
         $.formatString("${contextPath}/cashoutRecord/toAddCashoutRecord.action"),
         450,220,function(){
         grid.datagrid('load',$('#queryForm').serializeObject());
         });
         return false;
         }*/
        /**
         * 编辑
         */
        /*function editFun(id,name) {
         if (id == undefined) {
         var rows = grid.datagrid('getSelections');
         id = rows[0][idFieldName];
         name = rows[0][nameFieldName];
         }
         if($.ObjectUtils.isEmpty(id)){
         DialogUtils.alert("没有选中的" + entityName);
         return ;
         }
         DialogUtils.progress({
         text : '加载中，请等待....'
         });
        <%!//FIXME: 修改编辑modalDialog的width,height --%>
         DialogUtils.openModalDialog(
         "updateCashoutRecord",
         "编辑" + entityName + ":" + name,
         $.formatString("${contextPath}/cashoutRecord/toUpdateCashoutRecord.action?cashoutRecordId={0}",id),
         450,220,function(){
         grid.datagrid('load',$('#queryForm').serializeObject());
         });
         return false;
         }*/
        /*
         * 删除
         */
        /*function deleteFun(id,name) {
         if (id == undefined) {
         var rows = grid.datagrid('getSelections');
         id = rows[0][idFieldName];
         name = rows[0][nameFieldName];
         }
         if($.ObjectUtils.isEmpty(id)){
         DialogUtils.alert("没有选中的" + entityName);
         return false;
         }
         //判断是否确认删除指定的CashoutRecord
         DialogUtils.confirm("确认提醒" ,
         $.formatString("是否确认删除{0}:[{1}]?",entityName,name) ,
         function(data){
         if(data){
         DialogUtils.progress({
         text : '数据提交中，请等待....'
         });
         //如果确认删除指定的CashoutRecord
         $.post(
         '${contextPath}/cashoutRecord/deleteCashoutRecordById.action',
         {cashoutRecordId:id},
         function(data){
         DialogUtils.progress('close');
         if(data){
         DialogUtils.tip("删除" + entityName + "成功");
         }else{
         $.formatString("删除{0}:{1}失败.指定{0}可能已经被其他管理员所删除.如果指定{0}依然存在，请联系系统管理员.",entityName,name);
         }
         grid.datagrid('load',$('#queryForm').serializeObject());
         });
         }
         });
         return false;
         }*/
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

                    <%--<th hidden=true>渠道</th>--%>
                    <%--<td hidden=true><select id="paymentChannel" name="paymentChannel">--%>
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

        <c:if test='${authContext.hasAuth("approve_cashout_to_pass") }'>
            <a onclick="approveToPassFun();" href="javascript:void(0);" id="approveToPassButton"
               class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">审批至通过</a>
        </c:if>
        <c:if test='${authContext.hasAuth("refuse_cashout") }'>
            <a onclick="refuseFun();" href="javascript:void(0);" id="refuseButton" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'pencil_add'">拒绝提现</a>
        </c:if>
        <c:if test='${authContext.hasAuth("refuse_cashout") }'>
            <a onclick="getBankStatus();" href="javascript:void(0);" id="getBankStatusButton" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'pencil_add'">立即同步状态</a>
        </c:if>


        <%--<c:if test='${authContext.hasAuth("add_cashoutRecord") }'>--%>
        <%--<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">新增</a>--%>
        <%--</c:if>--%>
        <%--<c:if test='${authContext.hasAuth("update_cashoutRecord") }'>--%>
        <%--<a id="editALink" onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil'">编辑</a>--%>
        <%--</c:if>--%>
        <%--<c:if test='${authContext.hasAuth("delete_cashoutRecord") }'>--%>
        <%--<a id="deleteALink" onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_delete'">删除</a>--%>
        <%--</c:if>--%>
        <%--<c:if test='${authContext.hasAuth("enable_cashoutRecord") }'>--%>
        <%--<a id="enableALink" onclick="enableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_play_blue'">启用</a>--%>
        <%--</c:if>--%>
        <%--<c:if test='${authContext.hasAuth("disable_cashoutRecord") }'>--%>
        <%--<a id="disableALink" onclick="disableFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'control_stop_blue'">禁用</a>--%>
        <%--</c:if>--%>
        <a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);"
           class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
    </div>
</div>
</body>