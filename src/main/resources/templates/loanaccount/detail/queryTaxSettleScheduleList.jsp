<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" >
var taxmentScheduleGrid = null;
var taxMap = {};
$("#taxsettle_schedule").unbind('firstSelected').bind("firstSelected",function(){
	taxmentScheduleGrid = $('#taxmentScheduleGrid').datagrid({
		url:'${contextPath}/taxSettleSchedule/queryList.action?loanAccountId=${loanAccountDetail.id}',
		fit : false,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : false,
		selectOnCheck : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		collapsible: true,
		onLoadSuccess: function(data){
			//如果结果超过12行，将高度固定在380px
			var $grid = $(this);
			setTimeout(function(){
				if(data['rows'].length > 12){
					$grid.datagrid('resize',{
						height: 380
					});
				}else{
					$grid.datagrid('resize',{
						height: 35 + 32 * data['rows'].length
					});
				}
			}, 200);
			resetGridWidth();
		},
		frozenColumns: [[ 
		{
			field : 'id',
			title : '主键',
			width : 120,
			hidden : true
		},
		{
			field : 'period',
			title : '期数',
			width : 80
		},
		{
			field : 'month',
			title : '计提月',
			width : 120
		},
		{
   			field : 'startDate',
   			title : '起始日期',
   			width : 120
   			,formatter: function(value, row, index){
   				if(value == null){
   					return "";
   				}
   	  			var date = new Date();
   	  			date.setTime(value);
   	  			return date.format('yyyy-MM-dd');
   			}
   		},
   		{
   			field : 'endDate',
   			title : '到期还款日期',
   			width : 120
   			,formatter: function(value, row, index){
   				if(value == null){
   					return "";
   				}
   	  			var date = new Date();
   	  			date.setTime(value);
   	  			return date.format('yyyy-MM-dd');
   			}
   		}
        ]],
        columns: [[
       	{
            field : 'receivableAmount',
            title : '应收金额',
            width : 115
        },
        {
            field : 'exemptAmount',
            title : '豁免金额',
            width : 115
        },
        {
            field : 'actualReceivedAmount',
            title : '实收金额',
            width : 115
        },
        {
            field : 'beforeSettleAmount',
            title : '含税金额',
            width : 115
        },
        {
            field : 'shouldSettleAmount',
            title : '应纳税金额',
            width : 115
        },
        {
            field : 'actualSettleAmount',
            title : '实际纳税金额',
            width : 115
        },
        {
            field : 'afterSettleAmount',
            title : '不含税金额',
            width : 115
        }
	 	]],
   		toolbar : '#taxmentScheduleToolbar'
	});

});

</script>
	<div data-options="region:'center',border:false">
		<table id="taxmentScheduleGrid"></table>
    </div> 
    <div id="taxmentScheduleToolbar" style="display: none;">
	    <a onclick="taxmentScheduleGrid.datagrid('reload');return false;" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
    </div>
