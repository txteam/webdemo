<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	$("button").button();
	$("input[type=button]").button();
	
	$("#openSimpleWin").click(function(){ 
        DialogUtils.openSimpleDialog({
        	id:'openSimpleWin',
    	    width: '900',
            height: '450',
            contentUrl: '${contextPath}/view/demo/demoList'
        });
    });
	
	$("#openWin").click(function(){ 
        DialogUtils.openDialog({
        	id:'openWin',
    	    width: '700',
            height: '300',
            contentUrl: '${contextPath}/view/demo/demoList'
        });
    });
	
	$("#closeWin").click(function(){ 
        DialogUtils.closeDialogById('openWin');
    });
	
	//常用一
	$("#dialog").click(function(){ 
        DialogUtils.dialog("newDialog",'${contextPath}/view/demo/demoList',900,300);
    });
	$("#modalDialog").click(function(){ 
        DialogUtils.modalDialog("newModelDialog",'${contextPath}/view/demo/demoList',900,300);
    });
	
	$("#openTip").click(function(){ 
        DialogUtils.openTip({
        	closeOnEscape: true,
			captionButtons: {
	            pin: {visible: true, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
	            refresh: {visible: true, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
	            toggle: {visible: true, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
	            minimize: {visible: true, click: self.minimize, iconClassOn: 'ui-icon-minus'},
	            maximize: {visible: true, click: self.maximize, iconClassOn: 'ui-icon-extlink'}
	        }
        },'提示信息! <br/> 提示信息<br/>',3,function(){
        	alert("tip close callback");
        });
    });
	
	//常用二
	$("#tip").click(function(){ 
        DialogUtils.tip('提示信息! <br/> 提示信息<br/>',3);
    });
	
	$("#openAlert").click(function(){ 
        DialogUtils.openAlert({
        	closeOnEscape: true,
			captionButtons: {
	            pin: {visible: true, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
	            refresh: {visible: true, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
	            toggle: {visible: true, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
	            minimize: {visible: true, click: self.minimize, iconClassOn: 'ui-icon-minus'},
	            maximize: {visible: true, click: self.maximize, iconClassOn: 'ui-icon-extlink'}
	        }
        },'提示信息! <br/> 提示信息<br/>',function(){
        	alert("alert close callback");
        });
    });
	
	$("#alert").click(function(){
        DialogUtils.alert('警告：xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx!<br/>sssssssssssssssssssssssssssssssssssssssssssssssss');
    });
	
	$("#openConfirm").click(function(){ 
        DialogUtils.openConfirm({
        	closeOnEscape: true,
			captionButtons: {
	            pin: {visible: true, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
	            refresh: {visible: true, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
	            toggle: {visible: true, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
	            minimize: {visible: true, click: self.minimize, iconClassOn: 'ui-icon-minus'},
	            maximize: {visible: true, click: self.maximize, iconClassOn: 'ui-icon-extlink'}
	        }
        },'openConfirm info <br/> 提示信息<br/>',function(){
        	alert("confirm 确定 callback");
        },function(){
        	alert("confirm 取消 callback");
        });
    });
	
	$("#confirm").click(function(){
        DialogUtils.confirm('confirmInfo',function(){
        	alert("confirm 确定 callback");
        },function(){
        	alert("confirm 取消 callback");
        });
    });
	
	$("#windowConfirm").click(function(){
        confirm('confirmInfo',function(){
        	alert("confirm 确定 callback");
        },function(){
        	alert("confirm 取消 callback");
        });
    });
});
</script>
</head>
<body>
<div class="header">常用菜单>dialogUtils demo</div>
<br/>
<div class="header">openWin</div>
<button id="openSimpleWin" type="button">openSimpleWin</button>
<button id="openWin" type="button">openWin</button>
<button id="closeWin" type="button">closeWin</button>
<button id="dialog" type="button">dialog</button>
<button id="modalDialog" type="button">modalDialog</button>
<br/>
<br/>
<div class="header">openTip and alert and tip</div>
<button id="openTip" type="button">openTip</button>
<button id="tip" type="button">tip</button>
<button id="openAlert" type="button">openAlert</button>
<button id="alert" type="button">alert</button>
<button id="openConfirm" type="button">openConfirm</button>
<button id="confirm" type="button">confirm</button>
<button id="windowConfirm" type="button">windowConfirm</button>
<br/>
</body>
</html>