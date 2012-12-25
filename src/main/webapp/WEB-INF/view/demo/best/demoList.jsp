<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>best dialog demo</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	/*
		由于本页面加入了special标志，所以页面需要自己进行渲染
	*/
    //给body统一加入样式
    $("body").addClass("ui-widget-content");
    
    //统一页面form元素风格
    $("button,input[type=button],:input[type=submit]").button();
    $(":input[type=text],:input[type=password],textarea").wijtextbox(); 
    $("select").wijdropdown();
    //阻止button的默认事件
    //$("button,:input[type=button],:input[type=submit]").click(function(){ return false; });
    
    //处理具有header样式的自动渲染
    if($(".header").size() > 0){
        $(".header").prepend('<span class="ui-icon ui-icon-gear" style="float:left;"></span>');
        $(".header").addClass("ui-state-default ui-corner-all ui-tabs-selected ui-state-active page-title-header");
    }
    
    if($(".form-table").size() > 0){
    	$(".form-table table").addClass("ui-widget-content");
    	$(".form-table table td,th").addClass("ui-widget-content");
    }
    	
    
  	
    
  	//利用js实现自动长度截取功能
    if($(".subStr").size() > 0){
        $.each($(".subStr"), function(index, dom) {
        	var $dom = $(this);
            var maxLength = $dom.attr("maxLength") * 1 > 0 ? $dom.attr("maxLength") * 1 : 10;
            var suffix = $dom.attr("suffix") ? $dom.attr("suffix") : "";
            if($dom.text().length > maxLength){
            	$dom.attr("title",$dom.text());
            	$dom.text($dom.text().substr(0,maxLength) + suffix);
            }
        });
    }
  	
  	/*
    if($(".queryResult").size() > 0){
  		$(".queryResult").wijgrid({ 
  			//allowColMoving: true,
  			//allowColSizing: true,
  	      	scrollMode: "auto" 
  	    }); 
  	}
  	*/
    
    $("#calendar").wijcalendar({ 
        popupMode: true, 
        dateFormat: 'yyyy-MM-dd HH:mm:ss',
        selectedDatesChanged: function () { 
            var selDate = $(this).wijcalendar("getSelectedDate"); 
            if (selDate) { 
                $("#popdate").val(selDate.toDateString()); 
            } 
        } 
    }); 
    
    $("#startCreateDate").click(function () { 
        $("#calendar").wijcalendar("popup", { 
            of: $("#startCreateDate"), 
            offset: '0 2'
        }); 
    }); 

	
	
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
<body class="special ui-widget-content">
<form:form method="post" id="demoForm" modelAttribute="demoForm">
<div class="form-table">
<table>
	<colgroup>
		<col/>
		<col/>
		<col/>
		<col/>
		<col/>
		<col width="25%"/>
	</colgroup>
	<tr>
		<th>姓名:</th>
		<td><input name="name" type="text"/></td>
		<th>数字</th>
		<td><input name="number" type="text"/></td>
		<th>选择</th>
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
			<input id="startCreateDate" name="startCreateDate" type="text"/>
		</td>
		<th>最大创建时间</th>
		<td><input name="endCreateDate" type="text"/></td>
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
</table>
</div>

<br/>


<table class="queryResult" style="height:260px;">
	<thead>
		<th>选择</th>
		<th>LoginName</th>
		<th>Name</th>
		<th>创建时间</th>
		<th>最后更新时间</th>
	</thead>
	<tbody>
		<c:forEach items="${demoList}" var="demo" varStatus="status">
			<tr>
				<td>
					<input name="demoId" type="radio" value="${demo.id }"/>
				</td>
				<td class="subStr" maxLength="20" suffix="...">${demo.loginName }</td>
				<td>${demo.name }</td>
				<td><fmt:formatDate value="${demo.createDate }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
				<td><fmt:formatDate value="${demo.lastUpdateDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="button-div">
	<button id="prepare" type="button">prepare</button>
	&nbsp;&nbsp;
	<button id="add" type="button">增加</button>
	&nbsp;&nbsp;
	<button id="add" type="button">删除</button>
</div>
</form:form>
</body>
</html>