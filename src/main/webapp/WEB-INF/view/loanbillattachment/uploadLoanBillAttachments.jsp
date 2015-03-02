<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单据附件</title>

<%@include file="../includes/commonHead.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/components/attachmentupload/js/swfobject.js" charset="utf-8"></script>

<style type="text/css">
body{
	overflow: hidden;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	var flashvars = {
	};
	var params = {
		menu: "false",
		scale: "noScale",
		allowFullscreen: "false",
		allowScriptAccess: "always",
		bgcolor: "#fff",
		wmode: "direct"
	};
	var attributes = {
		id:"ScanUploader",
		name:"ScanUploader"
	};
	swfobject.embedSWF(
		"${pageContext.request.contextPath}/js/components/attachmentupload/ScanUploader.swf", 
		"altContent", "785", "560", "10.0.0", 
		"${pageContext.request.contextPath}/js/components/attachmentupload/expressInstall.swf", 
		flashvars, params, attributes);
	function thisMovie(movieName) 
	{
	    if (navigator.appName.indexOf("Microsoft") != -1) 
	    {
	        return window[movieName];
	    } 
	    else 
	    {
	        return document[movieName];
	    }
	}
 	function sendclick(){
 		thisMovie("ScanUploader").SetParameter("UPLOADURL", "${pageContext.request.contextPath}/loanBillAttachment/uploadLoanBillAttachments.action");
 	}
})
</script>
</head>
<body>
<form id = "exportForm"></form>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:true" style="overflow: hidden;">
		<div id="altContent">
			<h1>ScanUploader</h1>
			<p><a href="http://www.adobe.com/go/getflashplayer">Get Adobe Flash player</a></p>
		</div>
    </div>
</div>
</body>