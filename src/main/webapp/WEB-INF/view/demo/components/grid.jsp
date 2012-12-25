<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>best dialog demo</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	/*
		为测试准备数据
		由于使用hsql暂不知道怎么把数据持久在文件中的配置
		先弄一个这个功能顶上
	*/
	$("#prepare").click(function(){
		jQuery.post("${contextPath}/bestDemo/batchInsertDemoForUse",function(data){
			alert(data);
		},"json")
		//batchInsertDemoForUse
	});
	
	
	//foreachTable1
	tableToGrid("#foreachTable1",{
		height:'auto'
	});

	//foreachTable2
	tableToGrid("#foreachTable2");
	
	
	jQuery("#ajaxTable1").jqGrid({
		//获取数据的地址
	   	url:'${contextPath}/bestDemo/ajax/ajaxQueryDemoList',
	   	//从服务器端返回的数据类型，默认xml。可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
		datatype: "json",
		//ajax提交方式。POST或者GET，默认GET
		mtype:'POST',
		//列显示名称，是一个数组对象
	   	colNames:['选择','姓名', '登录名', '密码','BigDecimal','电子邮箱','最后更新时间'],
	   	colModel:[
	   		{name:'id',jsonmap:'id',index:'id', width:'10%'},
	   		{name:'name',jsonmap:'name',index:'name', width:'20%'},
	   		{name:'loginName',index:'name asc, invdate', width:'20%'},
	   		{name:'password',index:'amount', width:'20%', align:"right"},
	   		{name:'testBigDecimal',index:'tax', width:'20%', align:"right"},		
	   		{name:'email',index:'total', width:80, align:"right"},		
	   		{name:'lastUpdateDate',index:'note', width:150, sortable:false}		
	   	],
	   	sortname: 'id',
	   	//定义是否要显示总记录数
	    viewrecords: true,
	    sortorder: "desc",
	    //表格名称
	    caption:"JSON Example",
	    //如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。
	    //如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
	    autowidth: true,
		//表格高度，可以是数字，像素值或者百分比  height:'auto' default 150
		//如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.default : false
		rownumbers: true,
		jsonReader: {
			id: "id",
			cell: null,
			total : "total",
			page : "page",
			userdata: "userdata",
			repeatitems: true,
			records : function(obj){
				return (obj && $.isArray(obj)) ? obj.length : 0;
			},
			root: function(obj){
				return (obj && $.isArray(obj)) ? obj : [];
			}
		}
	});
	jQuery("#ajaxTablePager1").jqGrid('navGrid','#ajaxTablePager1',{edit:false,add:false,del:false});
});
</script>
</head>
<body>
<div class="header">component>dialog</div>
<br/>
准备测试数据：<button id="prepare" type="button">prepare</button>
<br/>
<div>
query c:foreach table 1<br/>
	<table id="foreachTable1" border="1">
		<thead>
			<tr>
				<th>Header1</th>
				<th>Header2</th>
				<th>Header3</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Cell 11</td><td>Cell 12</td><td>Cell 13</td>
			</tr>
			<tr>
				<td>Cell 21</td><td>Cell 22</td><td>Cell 23</td>
			</tr>
			<tr>
				<td>Cell 31</td><td>Cell 32</td><td>Cell 33</td>
			</tr>
		</tbody>
	</table>
</div>
<br/>
<br/>

<div>
query c:foreach table 2<br/>
	<table id="foreachTable2" border="1">
		<thead>
			<tr>
				<th>Header1</th><th>Header2</th><th>Header3</th>
				<th>Header4</th><th>Header5</th><th>Header6</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items='a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z' var="item" varStatus="status">
			<tr>
				<td>Cell ${item }.1</td><td>Cell ${item }2</td><td>Cell ${item }3</td><td>Cell ${item }4</td><td>Cell ${item }5</td><td>Cell ${item }6</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<br/>
<br/>

<div>
ajaxGrid:<br/>
	<table id="ajaxTable1"></table>
	<div id="ajaxTablePager1"></div>
</div>
<br/>
<div>



</body>
</html>