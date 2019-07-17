<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryClientAccountPagedList</title>
<%@include file="../../includes/commonHead.jsp" %>
<script type="text/javascript" >
var grid = null;
var idFieldName = 'id';
var nameFieldName = 'userName'; 
var entityName = '客户信息';
var status = '${status}';
var rechargeApplyId = '${rechargeApplyId}';

$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	var  $editALink = $("#editALink");
	var  $deleteALink = $("#deleteALink");
	grid = $('#grid').datagrid({
		url : '${contextPath}/rechargeRecord/queryClientInfoPagedList.action',
		queryParams :{rechargeApplyStatus:status,rechargeApplyId:rechargeApplyId},
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'id',
		checkOnSelect : true,
		selectOnCheck : true,
		remoteSort : false,
		nowrap : true,
		striped : true,
		singleSelect : true,
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 50, 100],
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
			field : 'row.id',
			title : 'pk',
			width : 150,
			hidden : true
		}]],
		columns: [[
		  		 {
		 			field : 'mobilePhoneNumber',
		 			title : '移动电话',
		 			width : 90
		 		 },
		 		 {
		 			field : 'loginName',
		 			title : '用户名(交易账号)',
		 			width :110
		 		 },
		 		 {
		 			field : 'realNameAuthentication',
		 			title : '是否认证',
		 			width : 90,
		 			formatter : function(value, row, index) {
		 				if(value == true){
		 					return "已认证";
		 				}else{
		 					return "<span style='color:red;'>未认证</span>";
		 				}
		 			}					
		 		 },				
		 		 {
		 			field : 'bankCardBinding',
		 			title : '是否绑卡',
		 			width : 90,
		 			formatter : function(value, row, index) {
		 				if(value == true){
		 					return "是";
		 				}else{
		 					return "否";
		 				}
		 			}					
		 		 },
		 		 {
		 			field : 'userName',
		 			title : '客户姓名',
		 			width : 110
		 		 },
		 		 {
		 			field : 'idCardNumber',
		 			title : '身份证号码',
		 			width : 140
		 		 },
		 		 {
		 			field : 'createDate',
		 			title : '创建时间',
		 			sortable : true,
		 			width : 140
		 			,formatter: function(cellvalue, options, rowObject){
		 	   			var date = new Date();
		 	   			date.setTime(cellvalue);
		 	   			return date.format('yyyy-MM-dd hh:mm:ss');;
		 			}
		 		}
		]],
		toolbar : '#toolbar',
		onClickRow : function(index, row){
			$editALink.linkbutton('enable');
			$deleteALink.linkbutton('enable');
		},
		onLoadSuccess : function() {
			$(this).datagrid('unselectAll');
			$(this).datagrid('tooltip');
			$editALink.linkbutton('disable');
			$deleteALink.linkbutton('disable');
			
			var rows = $('#grid').datagrid("getRows");
			$.each(rows,function(index,row){
//				if(checkedClientSet.contains(row.clientId)){
//					$('#grid').datagrid("checkRow",index);
//				}
			});
		}
	});
});

/*
 * 查询
 */
function queryFun() {
	grid.datagrid('load',$('#queryForm').serializeObject());
	return false;
}
/**
 * 提交
 */
function submitFun(){
	var rows = grid.datagrid('getSelections');
	if(rows == null || rows.length == 0){
		DialogUtils.alert("没有选中的记录.");
		return false;
	}
	var transferInClientId = rows[0]['id'];
	var requestUrl = null;
	if(status == 'WAIT_APPROVE'){
		requestUrl = "${contextPath }/rechargeRecord/approveToDistribute.action";
	}else if(status == 'LEDGER'){
		requestUrl = "${contextPath }/rechargeRecord/approveLedgerToDistribute.action";
	}
	DialogUtils.progress({
    	text : '数据提交中，请等待....'
    });
	$('#rechargeApplyForm').ajaxSubmit({
	    url: requestUrl,
	    data:{rechargeApplyId:rechargeApplyId,status:status,transferInClientId:transferInClientId},
	    success: function(data) {
	    	DialogUtils.progress('close');
			if(data){
				parent.DialogUtils.tip("操作成功.");
				parent.DialogUtils.closeDialogById("distribute");
			}else{
				DialogUtils.alert("操作失败.");
			}
	    } 
	});
}
/**
 * 取消
 */
function cancelFun(){
	parent.DialogUtils.closeDialogById("distribute");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'north',border:false" style="height: 100px; overflow: hidden;">
		<form:form id="queryForm" method="post" cssClass="form"
			modelAttribute="redPacket">
			<input type="hidden" name="rechargeApplyStatus" value="${status}"/>
			<input type="hidden" name="rechargeApplyId" value="${rechargeApplyId }"/>
			<table class="table table-hover table-condensed">
				<tr>
					<th>手机号码:</th>
					<td><input id="mobilePhone" name="mobilePhone"/></td>
					<th>姓名/机构名称:</th>
					<td><input id="userName" name="userName"/></td>
					<th>身份证/营业执照号 :</th>
					<td><input id="idcardNumber" name="idcardNumber"/></td>
				</tr>
				<tr>
					<th>实名信息:</th>
					<td>
						<select id="realNameAuthentication" name="realNameAuthentication" class="select">
							<option value="">-- 不限 --</option>
							<option value="true">-- 已实名认证 --</option>
							<option value="false">-- 未实名认证 --</option>
						</select>
					</td>
					<td>
						<select id="bankCardBinding" name="bankCardBinding" class="select">
							<option value="">-- 不限 --</option>
							<option value="true">-- 已绑卡 --</option>
							<option value="false">-- 未绑卡 --</option>
						</select>
					</td>
					<th>注册时间:</th>
					<td>
						<input id="minCreateDate" name="minCreateDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>-
					</td>
					<td>
						<input id="maxCreateDate" name="maxCreateDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</td>
				</tr>
 				<tr>
					<td colspan="6" class="button operRow">
						<a id="queryBtn" onclick="queryFun();return false;" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'search'">查询</a>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="grid"></table>
		<div id="toolbar" style="display: none;">		
			<a onclick="grid.datagrid('reload');return false;" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
    </div>
	
	<div data-options="region:'south',border:false" style="height: 30px; overflow: hidden;">
		<form class="form" id="rechargeApplyForm">
			<table class="table table-hover table-condensed">
				<tr>
					<td class="button operRow">
						<td class="rightOperRow" colspan="6" style="padding-right: 50px">
							<a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>  
							<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
						</td>
					</td>
				</tr>
			</table>
		</form>
    </div>
</div>
</body>