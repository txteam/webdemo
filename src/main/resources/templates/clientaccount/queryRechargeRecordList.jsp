<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
    //权限判定
    var idFieldName = 'id';
    var nameFieldName = 'id';
    var entityName = '充值记录';
    var clientRechargeRecordGrid = null;
    var clientRechargeRecordGridFitColumnsFlag=false;
    $(document).ready(function(){
        var clientAccountId = $("#clientAccountId").val();
        clientRechargeRecordGrid = $('#clientRechargeRecordGrid').datagrid({
            url : '${contextPath}/rechargeRecord/queryRechargeRecordList.action',
            queryParams : {clientAccountId : clientAccountId},
            fit : false,
            fitColumns : clientRechargeRecordGridFitColumnsFlag,
            border : false,
            idField : 'id',
            checkOnSelect : false,
            selectOnCheck : false,
            nowrap : false,
            striped : true,
            singleSelect : true,
            pagination : true,
            pageSize : 10,
            pageList : [ 10, 20, 30, 40, 50 ],
            loadFilter: function(data){
                var res = {total:0,rows:[]};
                if(!$.ObjectUtils.isEmpty(data)
                    && !$.ObjectUtils.isEmpty(data.list)){
                    res['total'] = data.count;
                    res['rows'] = data.list;
                }
                return res;
            },
            frozenColumns: [[ {
                field : 'id',
                title : '主键',
                width : 150,
                hidden : true
            }]],
            columns: [[
                {
                    field : 'serialNumber',
                    title : '流水号',
                    width : 220
                },
                {
                    field : 'type',
                    title : '类型',
                    width : 80,
                    formatter: function(cellvalue, options, rowObject){
                        if(cellvalue=='RECHARGE'){
                            return "充值";
                        }
                        if(cellvalue=='CASHOUT'){
                            return "提现";
                        }
                        return cellvalue.name;
                    },
                    sortable : true
                },
                {
                    field : 'beforeSum',
                    title : '变更前金额',
                    width : 100
                },
                {
                    field : 'sum',
                    title : '充值金额',
                    width : 100
                },
                {
                    field : 'afterSum',
                    title : '变更后金额',
                    width : 100
                },
                {
                    field : 'paymentChannel',
                    title : '账户类型',
                    width : 200,
                    formatter: baseJsonformatter,
                    sortable : true
                },
                {
                    field : 'status',
                    title : '状态',
                    width : 100,
                    formatter: baseJsonformatter,
                    sortable : true
                },

                {
                    field : 'remark',
                    title : '备注',
                    width : 200
                },
                {
                    field : 'approveRemark',
                    title : '审批备注',
                    hidden:false,
                    width : 200
                },
                {
                    field : 'clientAccountId',
                    title : '客户账户ID',
                    hidden:true,
                    width : 200
                },
                {
                    field : 'tradingRecordId',
                    title : '交易记录ID',
                    hidden:true,
                    width : 200
                },
                {
                    field : 'createDate',
                    title : '创建时间',
                    width : 200,
                    formatter: function(cellvalue, options, rowObject){
                        var date = new Date();
                        date.setTime(cellvalue);
                        return date.format('yyyy-MM-dd hh:mm:ss');;
                    }
                }
            ]],
            toolbar : '#clientRechargeRecordToolbar',
            onDblClickRow : function(index, row){
            },
            onClickRow : function(index, row){
            },
            onLoadSuccess: function(data){
                $(this).datagrid('unselectAll');
                $(this).datagrid('tooltip');
                //如果结果超过12行，将高度固定在380px
                var $grid = $(this);
                setTimeout(function(){
                    if(data['rows'].length > 12){
                        $grid.datagrid('resize',{
                            height: 380
                        });
                    }
                    resetGridWidth();
                }, 200);
            }
        });
    });


</script>

<div data-options="border:false"  style="width:auto" >
	<div data-options="region:'center',border:false">
		<table id="clientRechargeRecordGrid"></table>
	</div>
	<div id="clientRechargeRecordToolbar"  style="display: none;">
		<div class="nav_header datagrid-toolbar">
			<label class="panel-title panel-with-icon">充值记录</label>
		</div>
		<a onclick="clientRechargeRecordGrid.datagrid('reload');return false;" href="javascript:void(0);"
		   class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
	</div>
</div>
