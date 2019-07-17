<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>queryTransferApplyRecordPagedList</title>
    <%@include file="../includes/commonHead.jsp" %>
    <script type="text/javascript">
        //权限判定
        $.canAdd = false;
        $.canUpdate = false;
        $.canDelete = false;

        var grid = null;
        var idFieldName = 'id';
        var nameFieldName = 'serialNumber';
        var entityName = '转账申请';

        $(document).ready(function () {
            var $approveToReject = $("#approveToReject");
            var $approveToPass = $("#approveToPass");


            grid = $('#grid').datagrid({
                url: '${contextPath}/transferApplyRecord/queryTransferApplyRecordPagedList.action',
                queryParams: {
                    status: 'WAIT_APPROVE',
                    paymentChannel: '${paymentChannel}'
                },
                fit: true,
                fitColumns: true,
                border: false,
                idField: 'id',
                checkOnSelect: false,
                selectOnCheck: false,
                nowrap: true,
                striped: true,
                singleSelect: true,
                collapsible: true,
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
                        title: '客户名称',
                        width: 250
                    },
                    {
                        field: 'beforeSum',
                        title: '交易前金额',
                        width: 200
                        ,formatter:moneyFormatter
                    },
                    {
                        field: 'sum',
                        title: '转账金额',
                        width: 200
                        ,formatter:moneyFormatter
                    },
                    {
                        field: 'poundageSum',
                        title: '手续费',
                        width: 200,
                        hidden:true
                        ,formatter:moneyFormatter
                    },
                    {
                        field: 'factSum',
                        title: '实际金额',
                        width: 200,
                        hidden:true
                        ,formatter:moneyFormatter
                    },
                    {
                        field: 'afterSum',
                        title: '交易后金额',
                        width: 200
                        ,formatter:moneyFormatter
                    },


                    {
                        field: 'transferToClientUserName',
                        title: '转入客户名称',
                        width: 200
                    },
                    {
                        field: 'transferToClientLoginName',
                        title: '转入客户登录名',
                        width: 200
                    },


                    {
                        field: 'clientAccountId',
                        title: 'clientAccountId',
                        width: 200,
                        hidden: true
                    },
                    {
                        field: 'approveTradingRecord.id',
                        title: 'approveTradingRecord.id',
                        width: 200
                        , hidden: true
                    },
                    {
                        field: 'lastTransferAmount',
                        title: 'lastTransferAmount',
                        width: 200
                        , hidden: true
                    },


                    {
                        field: 'applyTradingRecord.id',
                        title: 'applyTradingRecord.id',
                        width: 200
                        , hidden: true
                    },

                    {
                        field: 'postTradingRecord.id',
                        title: 'postTradingRecord.id',
                        width: 200
                        , hidden: true
                    },
                    {
                        field: 'firstTransfer',
                        title: '第一次转账',
                        width: 200
                        , hidden: true
                    },

                    {
                        field: 'transferToClientAccountId',
                        title: 'transferToClientAccountId',
                        width: 200
                        , hidden: true
                    },
                    {
                        field: 'approveDate',
                        title: '审批时间',
                        width: 200
                        , formatter: dateFormatter
                    },
                    {
                        field: 'approveRemark',
                        title: '审批备注',
                        width: 200
                    },
                    {
                        field: 'overHisMaxAmount',
                        title: 'overHisMaxAmount',
                        width: 200
                        , hidden: true
                    },
                    {
                        field: 'remark',
                        title: '备注',
                        width: 200
                    },
                    {
                        field: 'postDate',
                        title: '推送时间',
                        width: 200
                        , formatter: dateFormatter
                        , hidden: true

                    },
                    {
                        field: 'attributes',
                        title: '附加属性',
                        width: 200
                        , hidden: true
                    },

                    {
                        field: 'status',
                        title: '状态',
                        width: 200
                        , formatter: baseJsonformatter
                    },
                    {
                        field: 'lastTransferDate',
                        title: 'lastTransferDate',
                        width: 200
                        , formatter: function (value, row, index) {
                        var text = '';
                        if ($.ObjectUtils.isEmpty(value)) {
                            text = '';
                        } else {
                            var date = new Date();
                            date.setTime(value);
                            text = date.format('yyyy-MM-dd hh:mm:ss');
                        }
                        return text;
                    }
                        , hidden: true
                    },

                    {
                        field: 'lastUpdateDate',
                        title: '更新时间',
                        hidden: true,
                        width: 200
                        , formatter: function (value, row, index) {
                        var text = '';
                        if ($.ObjectUtils.isEmpty(value)) {
                            text = '';
                        } else {
                            var date = new Date();
                            date.setTime(value);
                            text = date.format('yyyy-MM-dd hh:mm:ss');
                        }
                        return text;
                    }
                    },
                    {
                        field: 'applyDate',
                        title: '申请时间',
                        width: 200
                        , formatter: dateFormatter
                    }
                    /*, {
                     field: 'createDate',
                     title: '申请时间',
                     width: 200
                     , formatter: function (value, row, index) {
                     var text = '';
                     if ($.ObjectUtils.isEmpty(value)) {
                     text = '';
                     } else {
                     var date = new Date();
                     date.setTime(value);
                     text = date.format('yyyy-MM-dd hh:mm:ss');
                     }
                     return text;
                     }
                     }*/
                ]],
                toolbar: '#toolbar',
                onDblClickRow: function (index, row) {
                    if ($.canUpdate) {
                        editFun(row[idFieldName], row[nameFieldName]);
                    }
                },
                onClickRow: function (index, row) {
                    if (row['status'].key == 'WAIT_APPROVE') {
                        $approveToReject.linkbutton('enable');
                        $approveToPass.linkbutton('enable');

                    } else {
                        //alert(row['status'].key);
                        $approveToReject.linkbutton('disable');
                        $approveToPass.linkbutton('disable');

                    }

                },
                onLoadSuccess: function () {
                    $(this).datagrid('unselectAll');
                    $(this).datagrid('tooltip');

                    $approveToReject.linkbutton('disable');
                    $approveToPass.linkbutton('disable');
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
            //判断是否确认删除指定的RechargeApplyRecord
            DialogUtils.openModalDialog(
                "transferApplySuccess",
                $.formatString("转账成功[{0}]", name),
                $.formatString("${contextPath}/transferApplyRecord/toTransferApplySuccess.action?status=SUCCESS&transferApplyId={0}", id),
                600, 300, function () {
                    grid.datagrid('load', $('#queryForm').serializeObject());
                });
            return false;
// 	if (id == undefined) {
// 		var rows = grid.datagrid('getSelections');
// 		id = rows[0][idFieldName];
// 		name = rows[0][nameFieldName];
// 	}
// 	if($.ObjectUtils.isEmpty(id)){
// 		DialogUtils.alert("没有选中的" + entityName);
// 		return false;
// 	}
// 	//判断是否确认删除指定的RechargeApplyRecord
// 	DialogUtils.confirm("确认提醒" , 
//     	$.formatString("是否确认审批通过{0}:[{1}]?",entityName,name) , 
//     	function(data){
// 	    	if(data){
// 	    		DialogUtils.progress({
// 	    	        text : '数据提交中，请等待....'
// 	    		});
// 	    		//如果确认删除指定的RechargeApplyRecord
// 	    		$.post(
// 			    		'${contextPath}/transferApplyRecord/approveToPass.action',
// 			    		{transferApplyId:id},
// 			    		function(data){
// 			    			DialogUtils.progress('close');
// 			    			if(data){
// 			    				DialogUtils.tip("审批通过" + entityName + "成功");
// 			    			}else{
// 			    				var errorMessage = $.formatString("审批通过{0}:{1}失败.",entityName,name);
// 			    				DialogUtils.alert("提醒:", errorMessage, "error");
// 			    			}
// 			    			grid.datagrid('load',$('#queryForm').serializeObject());
// 			    });
// 	    	}
//     });
//     return false;
        }
        /*
         * 审批通过
         */
        function approveToRejectFun(id, name) {
            if (id == undefined) {
                var rows = grid.datagrid('getSelections');
                id = rows[0][idFieldName];
                name = rows[0][nameFieldName];
            }
            if ($.ObjectUtils.isEmpty(id)) {
                DialogUtils.alert("没有选中的" + entityName);
                return false;
            }
            //判断是否确认删除指定的RechargeApplyRecord
            DialogUtils.openModalDialog(
                "transferApplyReject",
                $.formatString("转账失败[{0}]", name),
                $.formatString("${contextPath}/transferApplyRecord/toTransferApplyReject.action?status=REJECT&transferApplyId={0}", id),
                600, 300, function () {
                    grid.datagrid('load', $('#queryForm').serializeObject());
                });
            return false;

// 	if (id == undefined) {
// 		var rows = grid.datagrid('getSelections');
// 		id = rows[0][idFieldName];
// 		name = rows[0][nameFieldName];
// 	}
// 	if($.ObjectUtils.isEmpty(id)){
// 		DialogUtils.alert("没有选中的" + entityName);
// 		return false;
// 	}
// 	//判断是否确认删除指定的RechargeApplyRecord
// 	DialogUtils.confirm("确认提醒" , 
//     	$.formatString("是否确认审批拒绝{0}:[{1}]?",entityName,name) , 
//     	function(data){
// 	    	if(data){
// 	    		DialogUtils.progress({
// 	    	        text : '数据提交中，请等待....'
// 	    		});
// 	    		//如果确认删除指定的RechargeApplyRecord
// 	    		$.post(
// 			    		'${contextPath}/transferApplyRecord/approveToReject.action',
// 			    		{transferApplyId:id},
// 			    		function(data){
// 			    			DialogUtils.progress('close');
// 			    			if(data){
// 			    				DialogUtils.tip("审批拒绝" + entityName + "成功");
// 			    			}else{
// 			    				var errorMessage = $.formatString("审批拒绝{0}:{1}失败.",entityName,name);
// 			    				DialogUtils.alert("提醒:", errorMessage, "error");
// 			    			}
// 			    			grid.datagrid('load',$('#queryForm').serializeObject());
// 			    });
// 	    	}
//     });
//     return false;
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
                    <th>付款客户电话:</th>
                    <td><input id="phoneNumber" name="phoneNumber"/></td>
                    <th>付款姓名/机构名称:</th>
                    <td><input id="userName" name="userName"/></td>
                    <th>身份证/营业执照号 :</th>
                    <td><input id="idCardNumber" name="idCardNumber"/></td>
                </tr>
                <tr>
                    <th>收款客户姓名:</th>
                    <td><input id="transferInClientUserName" name="transferInClientUserName"/></td>
                    <th>收款客户手机号码:</th>
                    <td><input id="transferInClientPhoneNumber" name="transferInClientPhoneNumber"/></td>
                    <%--<th>收款银行卡号:</th>--%>
                    <%--<td><input id="transferInClientBankCardNumber" name="transferInClientBankCardNumber"/></td>--%>
                </tr>
                <tr>
                    <th>状态:</th>
                    <td>
                        <select id="status" name="status">
                            <option value="">全部</option>
                            <c:forEach items="${transferApplyStatusEnums }" var="transferApplyStatusEnums">
                                <option value="${transferApplyStatusEnums.key }"
                                        <c:if test="${transferApplyStatusEnums.key == 'WAIT_APPROVE' }">selected='selected'</c:if>>${transferApplyStatusEnums.name }</option>
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
        <c:if test='${authContext.hasAuth("approve_to_pass") }'>
            <a id="approveToPass" onclick="approveToPassFun();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'pencil_add'">审批通过</a>
        </c:if>
        <c:if test='${authContext.hasAuth("approve_to_reject") }'>
            <a id="approveToReject" onclick="approveToRejectFun();" href="javascript:void(0);" class="easyui-linkbutton"
               data-options="plain:true,iconCls:'pencil_add'">审批驳回</a>
        </c:if>
        <%--        <c:if test='${authContext.hasAuth("modify_transfer_apply") }'>
                    <a onclick="modifyTransferApplyFun();" href="javascript:void(0);" class="easyui-linkbutton"
                       data-options="plain:true,iconCls:'pencil_add'">编辑</a>
                </c:if>--%>
        <a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);"
           class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
    </div>
</div>
</body>