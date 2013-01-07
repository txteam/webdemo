<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>best dialog demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	
	//查询
	$("#ajaxQueryBtn").click(function(){
		$('#demoForm').ajaxSubmit({
			
		});
	});
	
	//deal query result
	function processQuerySuccess(data){
		alert("processQuerySuccess");
	};
    
	var rownumIndex = 1;
	$("#demoList").bind("gridComplete",function(){
		rownumIndex = 1;
	});
	$("#demoList").txGrid({
		type: 'ajaxPagedList',
		pager: 'demoListPager',
		caption: 'ajax demo 分页列表',
		url:'${contextPath}/bestDemo/ajax/ajaxQueryDemoPagedList',
		mtype: "GET", //如果查询逻辑与具体当前登录人员信息相关，就建议使用POST
		rownumbers: true, //显示行号
		rowNum:10,//默认刚一开始的每页显示条数
	   	multiselect: true, //支持多选
	   	multiboxonly: true, //显示多选checkbox
		height: 'auto',
		//列显示名称，是一个数组对象
	   	colNames:['id','自定义行号','登录名', '显示整数','显示为金额','电子邮箱','姓名(+登+截取)','最后更新时间(时间id排序)','自定义格式时间'],
	   	//name 列显示的名称,index 传到服务器端用来排序用的列名称,width 列宽度
	   	//align 对齐方式；sortable  是否可以排序
	   	colModel:[
	   	    //align string left, center, right.
	   	    //隐藏的id列
	   		{name:'id',index:'id', width:'40',hidden:true},
	   		//小小小
	   		{name:'rownum',width:'40',formatter:function(cellvalue, options, rowObject){
					return rownumIndex++;
		   		}},
	   		//xxxx：	
	   		{name:'loginName',index:'id asc,loginName',search:true,sortable:true},
	   		//可排序，显示为整数
	   		{name:'testIntegerObj',index:'testIntegerObj',align:"right",sortable:true,formatter:"integer",formatoptions:{thousandsSeparator:","}},
	   		//显示为金额，如果为其他字段辅助判断可以自定义formatter
	   		{name:'testBigDecimal',index:'testBigDecimal',formatter:"currency",formatoptions:{prefix:"$",thousandsSeparator:","},sortable:true},
	   		//显示在右边，并添加email的连接
	   		{name:'email',index:'email',align:"right",formatter:"email"},
	   		//var opts= {rowId: rowId, colModel:cm, gid:ts.p.id, pos:colpos };cm.formatter.call(ts,cellval,opts,rwdat,_act);
	   		//这里rowObject即返回数据的本行的json对象
	   		//截取字符的一部分
	   		{name:'name',search:true,formatter:function(cellvalue, options, rowObject){
	   			var newName = cellvalue + ":" + rowObject['loginName'];
	   			return "<span title='" + newName + "'>" + $.subString(newName,10,'...') + "</span>";
	   		},beforeFormatterTitle:true},
	   		//显示title
	   		{name:'lastUpdateDate',index:'id,lastUpdateDate',formatter:'date',width:150,sortable:true,title:true},
	   		{name:'createDate',formatter:'date',formatoptions: {newformat:'Y-m-d'},width:120}		
	   	],
	});
	$("#demoList").jqGrid('navGrid','#demoListPager',{edit:true,add:true,del:true});
});
</script>
<style type="text/css">
.select{
	height: 14px;
	padding-top: 0px;
	padding-buttom: 0px;
}
</style>
</head>
<body>
<form:form method="post" id="demoForm" modelAttribute="demoForm">

<!-- query condition -->
<div class="form-table">
	<table>
		<tbody>
		<tr>
			<th>姓名:</th>
			<td><input name="name" type="text" value=''/></td>
			<th>数字:</th>
			<td><input name="number" type="text" value=''/></td>
			<th>选择:<em class="formee-req">*</em></th>
			<td>
				<select name="select">
					<optgroup label="---请选择---"> 
						<option>---请选择---</option>
						<option>---请选择---</option>
						<option>---请选择---</option>
						<option>---请选择---</option>
					</optgroup>
				</select>
			</td>
		</tr>
		<tr>
			<th>最小创建时间:</th>
			<td>
				<div id="calendar"></div> 
				<input id="startCreateDate" name="startCreateDate" type="text"
					value=''/>
			</td>
			<th>最大创建时间:</th>
			<td><input name="endCreateDate" type="text"
					value=''/></td>
			<th>&nbsp;</th>
			<td>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="6" class="button operRow">
				<button type="button" id="queryBtn">查询</button>
			</td>
		</tr>
		</tbody>
	</table>
</div>

<!-- grid -->
<div class="list-table">
	<table id="demoList">
	</table>
	<div id="demoListPager"></div>
	<div class="operRow">
		<button id="prepare" type="button">prepare</button>
		&nbsp;&nbsp;
		<button id="add" type="button">增加</button>
		&nbsp;&nbsp;
		<button id="add" type="button">删除</button>
	</div>
</div>

</form:form>
</body>
</html>