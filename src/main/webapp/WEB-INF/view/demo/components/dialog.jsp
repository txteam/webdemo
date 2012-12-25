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
		常用: 弹出指定链接的对话框<br/>
	*/
	$("#urlDialog").click(function(){ 
        DialogUtils.dialog("urlDialog","url dialog title",'${contextPath}/view/demo/demo',900,300,function(){
        	DialogUtils.alert("urlDialog close.");
        });
        DialogUtils.dialog("urlDialogNoTitle",null,'${contextPath}/view/demo/demo',900,300,function(){
        	DialogUtils.alert("urlDialog close.");
        });
    });
    
    /*
    	常用: 将页面中某hiddenDiv作为弹出框内容弹出.
		这里是内容。
		使用方法，将对应的div指定为隐藏，并指定其id为 "dialog_" + 方法中使用id,并将url指定为null
		1、id 为"dialog_"
 		2、url 指定为 null 
 		3、div 为隐藏
    */
	$("#divDialog").click(function(){ 
		DialogUtils.dialog("testDivDialog",'divDialog divDialog',null,900,300,function(){
        	DialogUtils.alert("testDivDialog close.");
        });
    });
    
	/*
		常用：弹出模态窗口
		模态窗口如果想用当前页面的某隐藏div内容时方法同dialog
	*/
	$("#modalDialog").click(function(){ 
        DialogUtils.modalDialog("modalDialog",'modalDialog title','${contextPath}/view/demo/demo',900,300,function(){
        	DialogUtils.alert("这个东东可以不要.");
        });
        DialogUtils.modalDialog("modalDialog",null,'${contextPath}/view/demo/demo',900,300,function(){
        	DialogUtils.alert("这个东东可以不要.");
        });
    });
	$("#divModalDialog").click(function(){ 
        DialogUtils.modalDialog("testDivDialog",null,null,900,300);
    });
	
	//常用 alert
	$("#tip").click(function(){ 
        DialogUtils.tip('提示信息! <br/> 提示信息<br/>',3);
    });
	
	//常用 alert
	$("#alert").click(function(){
        DialogUtils.alert('警告：xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx!<br/>sssssssssssssssssssssssssssssssssssssssssssssssss');
    });
	
	//常用 confirm
	$("#confirm").click(function(){
        DialogUtils.confirm('confirmInfo',function(){
        	alert("confirm 确定 callback");
        },function(){
        	alert("confirm 取消 callback");
        });
    });
	

	$("#openDialog").click(function(){ 
        DialogUtils.openDialog({
        	id:'openDialog',
    	    width: '700',
            height: '300',
            contentUrl: '${contextPath}/view/demo/demo'
        });
    });
	$("#closeDialog").click(function(){ 
        DialogUtils.closeDialogById('openDialog');
    });
	$("#openSimpleDialog").click(function(){ 
        DialogUtils.openSimpleDialog({
        	id:'openSimpleWin',
    	    width: '900',
            height: '450',
            contentUrl: '${contextPath}/view/demo/demo'
        });
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
});
</script>
</head>
<body>
<div class="header">component>dialog</div>
<br/>
弹出指定链接的对话框：<button id="urlDialog" type="button">urlDialog</button>
<br/>
<br/>
将页面中某hiddenDiv作为弹出框内容弹出：<button id="divDialog" type="button">divDialog</button>
<br/>
<br/>
弹出指定链接的模态对话框，如果需要弹出本页面内部内容，方法同dialog：<button id="modalDialog" type="button">modalDialog</button>  
<button id="divModalDialog" type="button">divModalDialog</button>
<br/>
<br/>
提示信息，可以指定过一段时间自动关闭的信息框体: <button id="tip" type="button">tip</button>
<br/>
<br/>
框架内alert的弹出方式: <button id="alert" type="button">alert</button>
<br/>
<br/>
框架内confirm的弹出方式: <button id="confirm" type="button">confirm</button>
<br/>
<br/>
<button id="openDialog" type="button">openDialog</button>
<button id="closeDialog" type="button">closeDialog</button>
<button id="openSimpleDialog" type="button">openSimpleDialog</button>
<button id="openTip" type="button">openTip</button>
<br/>
<br/>
参考<br/>
/webdemo/src/main/webapp/WEB-INF/view/demo/best/dialog.jsp<br/>
更多dialog的使用，请参考：<br/>  
参考demo > dialogDemo<br/>
http://wijmo.com<br/>
<div style="display: none;" id="dialog_testDivDialog">
	将页面中某hiddenDiv作为弹出框内容弹出.<br/>
	这里是内容。<br/>
	使用方法，将对应的div指定为隐藏，并指定其id为 "dialog_" + 方法中使用id,并将url指定为null<br/>
	1、id 为"dialog_"  <br/>
 	2、url 指定为 null
 	3、div 为隐藏
 	4、如果不想通过入参指定title这里可以t
</div>
</body>
</html>