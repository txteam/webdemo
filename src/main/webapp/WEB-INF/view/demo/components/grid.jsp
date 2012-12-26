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
	
	1.获得当前列表行数：$("#gridid").getGridParam("reccount");
	2.获取选中行数据（json）：$("#gridid").jqGrid('getRowData', id);
	3.刷新列表:$(refreshSelector).jqGrid('setGridParam', { url: ''), postData: ''}).trigger('reloadGrid'); 
	4.选中行：$("#jqGrid").setSelection("1", true);   （Toggles a selection of the row with id = rowid; if onselectrow is true (the default) then the event onSelectRow is launched, otherwise it is not.）//true:重新加载表格数据, false:不重新加载表格数据
	5.重置选中行：$("#jqgrid").resetSelection(); //Resets (unselects) the selected row(s). Also works in multiselect mode.
	6.清除：$("#jqgrid").clearGridData();   //Clears the currently loaded data from grid. If the clearfooter parameter is set to true, the method clears the data placed on the footer row.
	7. $("#jqgrid").setCell(rowid,colname,nData,cssp,attrp); 
	//This method can change the content of particular cell and can set class or style properties. Where: 
	rowid the id of the row, 
	colname the name of the column (this parameter can be a number (the index of the column) beginning from 0 
	data the content that can be put into the cell. If empty string the content will not be changed 
	class if class is string then we add a class to the cell using addClass; if class is an array we set the new css properties via css 
	properties sets the attribute properies of the cell, 
	forceup If the parameter is set to true we perform update of the cell instead that the value is empty 
	 
	8.获取选中行ID
	 var rowid = $("#jqgrid").jqGrid('getGridParam','selrow'); 
	var rowid = $("#searchResultList").getGridParam("selrow");
	var rowData = $("#searchResultList").getRowData(rowid); /根据行ID，获取选中行的数据(根据)
	//获取选中的多行ID列表
	var selectedIds  = jQuery("#stationList").jqGrid("getGridParam","selarrrow"); 允许多行选择时使用
	
	=================重点讲解================
		1.1 prmNames选项

		prmNames是jqGrid的一个重要选项，用于设置jqGrid将要向Server传递的参数名称。其默认值为：

		 

		view plain
		prmNames : {  
		    page:"page",    // 表示请求页码的参数名称  
		    rows:"rows",    // 表示请求行数的参数名称  
		    sort: "sidx", // 表示用于排序的列名的参数名称  
		    order: "sord", // 表示采用的排序方式的参数名称  
		    search:"_search", // 表示是否是搜索请求的参数名称  
		    nd:"nd", // 表示已经发送请求的次数的参数名称  
		    id:"id", // 表示当在编辑数据模块中发送数据时，使用的id的名称  
		    oper:"oper",    // operation参数名称（我暂时还没用到）  
		    editoper:"edit", // 当在edit模式中提交数据时，操作的名称  
		    addoper:"add", // 当在add模式中提交数据时，操作的名称  
		    deloper:"del", // 当在delete模式中提交数据时，操作的名称  
		    subgridid:"id", // 当点击以载入数据到子表时，传递的数据名称  
		    npage: null,   
		    totalrows:"totalrows" // 表示需从Server得到总共多少行数据的参数名称，参见jqGrid选项中的rowTotal  
		}  
		 

		可以通过这个选项来自定义当向Server发送请求时，默认发送的参数名称。
		这个参数很重要也很有用，正是通过这个参数，可以方便的改变默认的request的参数，以符合Server端的需要。比如在prmNames中search默认的值为"_search"，这在Struts2的Action中不太方便命名成员变量和getter/ setter。因此可以使用 prmNames: {search: 'search'} 来改变这一默认值为"search"，这在Struts2的Action对象中就很好设置getter/ setter了，即getSearch()和setSearch()。当然其他名字也是可以的。

		 

		1.2 jsonReader选项

		jsonReader是jqGrid的一个重要选项，用于设置如何解析从Server端发回来的json数据。其默认值为：

		view plain
		jsonReader : {  
		    root: "rows",   // json中代表实际模型数据的入口  
		    page: "page",   // json中代表当前页码的数据  
		    total: "total", // json中代表页码总数的数据  
		    records: "records", // json中代表数据行总数的数据  
		    repeatitems: true, // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。  
		    cell: "cell",  
		    id: "id",  
		    userdata: "userdata",  
		    subgrid: {  
		        root:"rows",   
		        repeatitems: true,   
		        cell:"cell"  
		    }  
		}  
		 

		可以这样理解，prmNames设置了如何将Grid所需要的参数传给Server，而jsonReader设置了如何去解析从Server端传回来的json数据。如果没有设置jsonReader的话，jqGrid将会根据默认的设置来解析json数据，并显示在表格里。但如果传回来的json数据，不太符合默认设置（比如内部的结构名不太一样），那么就有必要修改这一设置。比如：

		view plain
		jsonReader: {  
		    root:"gridModel",     
		    page: "page",     
		    total: "total",  
		    records: "record",  
		    repeatitems : false  
		}  
		 

		注1：据其他网友的文章，如果设置repeatitems为false，不但数据可以乱序，而且不用每个数据元素都要具备，用到哪个找到哪个就可以了。实验却是如此。
		注2：cell、id在repeatitems为true时可以用到，即每一个记录是由一对id和cell组合而成，即可以适用另一种json结构。援引文档中的例子：

		repeatitems为true时：

		view plain
		jQuery("#gridid").jqGrid({  
		    ...  
		    jsonReader : {  
		        root:"invdata",  
		        page: "currpage",  
		        total: "totalpages",  
		        records: "totalrecords"  
		    },  
		    ...  
		});  
		json结构为：

		view plain
		{   
		    "totalpages": "xxx",   
		    "currpage": "yyy",  
		    "totalrecords": "zzz",  
		    "invdata" : [  
		                 {"id" :"1", "cell" :["cell11", "cell12", "cell13"]},   // cell中不需要各列的name，只要值就OK了，但是需要保持对应  
		                 {"id" :"2", "cell" :["cell21", "cell22", "cell23"]},  
		                 ...  
		    ]  
		}  
		 

		 

		repeatitems为false时：

		view plain
		jQuery("#gridid").jqGrid({  
		    ...  
		    jsonReader : {  
		        root:"invdata",  
		        page: "currpage",  
		        total: "totalpages",  
		        records: "totalrecords",  
		        repeatitems: false,  
		        id: "0"  
		    },  
		    ...  
		});  
		json结构为：

		view plain
		{   
		    "totalpages" : "xxx",   
		    "currpage" : "yyy",  
		    "totalrecords" : "zzz",  
		    "invdata" : [  
		                 {"invid" : "1","invdate":"cell11", "amount" :"cell12", "tax" :"cell13", "total" :"1234", "note" :"somenote"}, // 数据中需要各列的name，但是可以不按列的顺序  
		                 {"invid" : "2","invdate":"cell21", "amount" :"cell22", "tax" :"cell23", "total" :"2345", "note" :"some note"},  
		                 ...  
		    ]  
		} 
	*/
	
	
	
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
	   	//autoencode 当为 ture 时对url进行编码
	   	//从服务器端返回的数据类型，默认xml。可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
		datatype: "json",
		//ajax提交方式。POST或者GET，默认GET
		mtype:'POST',
		//列显示名称，是一个数组对象
	   	colNames:['选择','姓名', '登录名', '密码','BigDecimal','电子邮箱','最后更新时间'],
	   	//name 列显示的名称,index 传到服务器端用来排序用的列名称,width 列宽度
	   	//align 对齐方式；sortable  是否可以排序
	   	colModel:[
	   		{name:'id',index:'id', width:'40'},
	   		{name:'name',index:'name'},
	   		{name:'loginName',index:'name asc, invdate'},
	   		{name:'password',index:'amount', align:"right"},
	   		{name:'testBigDecimal',index:'tax', align:"right"},		
	   		{name:'email',index:'total', align:"right"},		
	   		{name:'lastUpdateDate',index:'note',sortable:false,formatter:function(obj){
	   			return typeof(obj);
	   		}}		
	   	],
	   	//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
	   	//pager: 'ajaxTablePager1',
	   	//rowNum	在grid上显示记录条数，这个参数是要被传递到后台
	   	rowNum:9999,
	   	//rowList	一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
	   	//sortname	默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
	   	//viewrecords	定义是否要显示总记录数
	   	
	   	//启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
	   	hidegrid: false,
	   	
	   	//当执行ajax请求时要干什么。disable禁用ajax执行提示；enable默认，当执行ajax请求时的提示； block启用Loading提示，但是阻止其他操作
	   	loadui: 'block',
	   	
	   	//定义是否可以多选
	   	multiselect:true,
	   	//只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用
	   	multiboxonly:true,
	   	
	   	
	   	//ajaxGridOptions	object	对ajax参数进行全局设置，可以覆盖ajax事件：error，complete 和 beforeSend	空值	是
		//ajaxSelectOptions	object	对ajax的select参数进行全局设置，设置editoptions跟searchoptions 参数的select属性值
	    sortorder: "desc",
	    //表格名称
	    caption:"JSON Example",
	    //如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。
	    //如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
	    autowidth: true,
		//表格高度，可以是数字，像素值或者百分比  height:'auto' default 150
		//如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.default : false
		rownumbers: true,
		//是否要显示总记录数 default: false
		viewrecords: true,
		
		jsonReader: {
			repeatitems: false,
			id: "id",
			records : function(obj){
				return (obj && $.isArray(obj)) ? obj.length : 0;
			},
			root: function(obj){
				return (obj && $.isArray(obj)) ? obj : [];
			}
		}
	});
	
    var resizeTimer = null;
    $(window).resize(function() {
        if (resizeTimer != null) {
            clearTimeout(resizeTimer);
        }
        resizeTimer = setTimeout(function() {
        	alert($("body").innerWidth());
        	jQuery("#ajaxTable1").jqGrid('setGridWidth',jQuery("body").innerWidth());
        }, 100);
    });
	//jQuery("#ajaxTablePager1").jqGrid('navGrid','#ajaxTablePager1',{edit:false,add:false,del:false});


	
	jQuery("#ajaxTable2").jqGrid({
		//获取数据的地址
	   	url:'${contextPath}/bestDemo/ajax/ajaxQueryDemoPagedList',
	   	//autoencode 当为 ture 时对url进行编码
	   	//从服务器端返回的数据类型，默认xml。可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
		datatype: "json",
		//ajax提交方式。POST或者GET，默认GET
		mtype:'POST',
		//列显示名称，是一个数组对象
	   	colNames:['选择','姓名', '登录名', '密码','BigDecimal','电子邮箱','最后更新时间'],
	   	//name 列显示的名称,index 传到服务器端用来排序用的列名称,width 列宽度
	   	//align 对齐方式；sortable  是否可以排序
	   	colModel:[
	   		{name:'id',index:'id', width:'40'},
	   		{name:'name',index:'name'},
	   		{name:'loginName',index:'name asc, invdate'},
	   		{name:'password',index:'amount', align:"right"},
	   		{name:'testBigDecimal',index:'tax', align:"right"},		
	   		{name:'email',index:'total', align:"right"},		
	   		{name:'lastUpdateDate',index:'note',sortable:false}		
	   	],
	   	//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
	   	//pager: 'ajaxTablePager1',
	   	//rowNum	在grid上显示记录条数，这个参数是要被传递到后台
	   	rowNum:10,
	   	//rowList	一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
	   	//sortname	默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
	   	//viewrecords	定义是否要显示总记录数
	   	
	   	//启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
	   	hidegrid: false,
	   	
	   	//当执行ajax请求时要干什么。disable禁用ajax执行提示；enable默认，当执行ajax请求时的提示； block启用Loading提示，但是阻止其他操作
	   	loadui: 'block',
	   	
	   	//定义是否可以多选
	   	multiselect:true,
	   	//只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用
	   	multiboxonly:true,
	   	
	   	sortorder: "desc",
	    caption:"pagedlist Example",
	    //如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。
	    //如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
	    autowidth: true,
		//表格高度，可以是数字，像素值或者百分比  height:'auto' default 150
		//如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.default : false
		rownumbers: true,
		//是否要显示总记录数 default: false
		viewrecords: true,
		height:'auto',
		pager: 'ajaxTablePager2',
		jsonReader: {
			root:'list',
			page:'pageIndex',
			repeatitems: false,
			total: function(){
				return 10;	
			},
			records:'count'
		}
	});
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
ajaxGrid1:<br/>
	<table id="ajaxTable1"></table>
	<div id="ajaxTablePager1"></div>
</div>
<br/>
<br/>

<div>
ajaxGrid2:<br/>
	<table id="ajaxTable2"></table>
	<div id="ajaxTablePager2"></div>
</div>
<br/>

<br/>
<br/>

<br/>
<br/>
参考<br/>
http://www.trirand.com/blog/jqgrid/jqgrid.html<br/>
http://www.cnblogs.com/younggun/archive/2012/08/27/2657922.html<br/>
http://blog.csdn.net/lpy3654321/article/details/7936301<br/>


</body>
</html>