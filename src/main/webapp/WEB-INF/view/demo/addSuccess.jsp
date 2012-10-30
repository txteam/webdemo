<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<link rel="stylesheet" type="text/css" href="/webdemo/css/default/om-default.css" />
<style type="text/css">
html,body,div{
	margin:0px;
	border:0px;
	height: 100%
}
</style>

<script type="text/javascript" src="/webdemo/js/jquery.min.js"></script>
<script type="text/javascript" src="/webdemo/js/operamasks-ui.min.js"></script>

<script type="text/javascript" >
$(document).ready(function() {
	$("#bodyDiv").omPanel({
		header:false,
        width: 'fit',
        height: 'fit',
        title: 'webdemo mainframe',
        collapsed: false,//组件创建后为收起状态
        collapsible: false,//渲染收起与展开按钮
        closable: false //渲染关闭按钮
    });
});
</script>
</head>
<body>
<div id="bodyDiv">
	add success
</div>
</html>