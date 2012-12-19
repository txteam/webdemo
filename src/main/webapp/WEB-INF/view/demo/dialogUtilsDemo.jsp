<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	$("button").button();
	$("input[type=button]").button();
	
	$("#openWin").click(function(){ 
        DialogUtils.openWin({
        	id:'dialog_openWin',
        	autoOpen: true,
    	    width: '900',
            height: '450',
            captionButtons: {
            	pin: {visible: true, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
            	refresh: {visible: true, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
            	toggle: {visible: true, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
            	minimize: {visible: true, click: self.minimize, iconClassOn: 'ui-icon-minus'},
            	maximize: {visible: true, click: self.maximize, iconClassOn: 'ui-icon-extlink'},
            	close: {visible: true, iconClassOn: 'ui-icon-close'}
            },
            contentUrl: '${contextPath}/view/demo/demoList'
        });
    });
	$("#openSimpleWin").click(function(){ 
        DialogUtils.openWin({
        	id:'dialog_openSimpleWin',
        	autoOpen: true,
    	    width: '900',
            height: '450',
            contentUrl: '${contextPath}/view/demo/demoList'
        });
    });
	$("#openTip").click(function(){ 
        DialogUtils.openTip({
        	content: '提示信息! <br/> 提示信息<br/>'
        },3,function(){
        	alert("tip close");
        });
    });
	
	$("#alert").click(function(){ 
        DialogUtils.alert('警告：xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx!<br/>sssssssssssssssssssssssssssssssssssssssssssssssss');
    });
});
</script>
</head>
<body>
<div class="header">常用菜单>dialogUtils demo</div>
<br/>
<div class="header">openWin</div>
<button id="openWin" type="button">openWin</button>
<button id="openSimpleWin" type="button">openSimpleWin</button>
<br/>
<br/>
<div class="header">openTip and alert and tip</div>
<button id="openTip" type="button">openTip</button>
<button id="alert" type="button">alert</button>
<br/>
</body>
</html>