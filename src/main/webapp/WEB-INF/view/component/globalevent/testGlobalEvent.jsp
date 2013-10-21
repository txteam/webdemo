<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	var testFun = function(event,params){
		console.log("testGlobalEvent1 : " + $("#eventType").val() + " : " + params);
    }
    $("#bindEvent").click(function(){
    	if($("#eventType").val() != null && $("#eventType").val().length > 0){
    		$.bindge($("#eventType").val(),function(event,params){
		console.log("testGlobalEvent1 : " + $("#eventType").val() + " : " + params);
    });
    	}
    });
    $("#triggerEvent").click(function(){
    	if($("#eventType").val() != null && $("#eventType").val().length > 0){
    		$.triggerge($("#eventType").val(),"参数一");
    	}
    });
});
</script>
</head>
<body>
<div class="header">常用菜单>testGlobalEvent1</div>
	eventType: <input id="eventType" name="eventType" value="" value=""/><br/>
	<button id="bindEvent" type="button">bindEvent</button>
	<button id="triggerEvent" type="button">triggerEvent</button>
</body>
</html>