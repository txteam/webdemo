<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
    //权限判定
    var idFieldName = 'id';
    var nameFieldName = 'id';
    var entityName = '分润记录';
    var clientTransferRecordGrid = null;
    var clientTransferRecordGridFitColumnsFlag = true;
    $(document).ready(function () {
        var clientAccountId = $("#clientAccountId").val();
        var clientId = $("#clientId").val();
        clientTransferRecordGrid = $('#clientTransferRecordGrid').datagrid({
            url: '${contextPath}/transferToAccountRecord/queryPagedList.action',
            queryParams: {
                clientAccountId: clientAccountId,
                clientId: clientId
            },
            fit: false,
            fitColumns: clientTransferRecordGridFitColumnsFlag,
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
                    width: 180
                },
                {
                    field: 'status',
                    title: '状态',
                    width: 100
                    , formatter: baseJsonformatter
                },
                {
                    field: 'transferToClientMobilePhoneNumber',
                    title: '电话',
                    width: 100
                },
                {
                    field: 'transferToClientUserName',
                    title: '姓名',
                    width: 80
                },
                {
                    field: 'sum',
                    title: '金额',
                    width: 80
                },
                {
                    field: 'poundageSum',
                    title: '手续费',
                    width: 80
                },
                {
                    field: 'factSum',
                    title: '实际转账金额',
                    hidden: true,
                    width: 80
                }
                ,
                {
                    field: 'firstTransfer',
                    title: '首次转账',
                    width: 100
                    , formatter: function (cellvalue, options, rowObject) {
                    var text = cellvalue ? "<font color='red'>是<font/>" : "<font color='green'>否<font/>";
                    return text;
                }
                },
                {
                    field: 'overHisMaxAmount',
                    title: '超历史金额',
                    width: 100
                    , formatter: function (cellvalue, options, rowObject) {
                    var text = cellvalue ? "<font color='red'>是<font/>" : "<font color='green'>否<font/>";
                    return text;
                }
                },
                {
                    field: 'transferToClientUserName',
                    title: '转账客户姓名',
                    width: 120
                }, {
                    field: 'transferToClientLoginName',
                    title: '转账客户登录名',
                    width: 120
                },
                {
                    field: 'transferToClientPhoneNumber',
                    title: '转账客户电话',
                    width: 120
                },
                {
                    field: 'remark',
                    title: '备注',
                    width: 200
                },
                {
                    field: 'approveRemark',
                    title: '审批备注',
                    width: 200
                },
                {
                    field: 'lastTransferAmount',
                    title: '最后转账金额',
                    width: 140
                },
                {
                    field: 'lastTransferDate',
                    title: '最后转账时间',
                    width: 200
                    , formatter: function (cellvalue, options, rowObject) {
                    var date = new Date();
                    date.setTime(cellvalue);
                    return $.ObjectUtils.isEmpty(cellvalue) ? "" : date.format('yyyy-MM-dd hh:mm:ss');
                    ;
                }
                },
                {
                    field: 'createDate',
                    title: '申请时间',
                    width: 200
                    , formatter: function (cellvalue, options, rowObject) {
                    var date = new Date();
                    date.setTime(cellvalue);
                    return date.format('yyyy-MM-dd hh:mm:ss');
                    ;
                }
                },

                {
                    field: 'transferInClientIdCardNumber',
                    title: 'transferInClientIdCardNumber',
                    hidden: true,
                    width: 200
                }
            ]],
            toolbar: '#clientTransferRecordToolbar',
            onDblClickRow: function (index, row) {
            },
            onClickRow: function (index, row) {
            },
            onLoadSuccess: function (data) {
                $(this).datagrid('unselectAll');
                $(this).datagrid('tooltip');
                //如果结果超过12行，将高度固定在380px
                var $grid = $(this);
                setTimeout(function () {
                    if (data['rows'].length > 12) {
                        $grid.datagrid('resize', {
                            height: 380
                        });
                    }
                    resetGridWidth();
                }, 200);
            }
        });
    });


</script>

<div data-options="border:false" style="width:auto">
    <div data-options="region:'center',border:false">
        <table id="clientTransferRecordGrid"></table>
    </div>
    <div id="clientTransferRecordToolbar" style="display: none;">
        <div class="nav_header datagrid-toolbar">
            <label class="panel-title panel-with-icon">付款信息</label>
        </div>
        <a onclick="clientTransferRecordGrid.datagrid('reload');return false;" href="javascript:void(0);"
           class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
    </div>
</div>
