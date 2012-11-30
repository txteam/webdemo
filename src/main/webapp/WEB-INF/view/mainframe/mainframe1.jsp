<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainframe</title>
<%@include file="../includes/commonHead.jsp" %>

<link  rel="stylesheet" type="text/css" href="${contextPath }/css/mainframe.css" />
<script type="text/javascript" src="${contextPath }/js/mainframe/mainframe1.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    var layout = new Layout();

	$("#skinSwitcher").chemeswitcher({
	    contextPath:"${contextPath}",
	    themeCookieName:"tx_theme"
	});
});
</script>
<body>
<div class="container">
	<!-- top -->
	<div class="top">
		<div>log</div>
		
		<div id="topSpliter" class="ui-widget-header top-spliter"></div>
	</div>
	
	<!-- center -->
	<div class="center">
		<div class="center-left">
	        <li> 
	            <h3><a href="#">菜单一</a></h3> 
	            <div style="margin:0px;padding:0px;"> 
	               <ul class="center-left-menu">
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					</ul>
	            </div>
	        </li> 
	        <li> 
	            <h3><a href="#">菜单二</a></h3> 
	            <div style="margin:0px;padding:0px"> 
	                <ul class="center-left-menu">
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar22222</a></li>
					</ul>
	            </div> 
	        </li> 
	        <li> 
	            <h3> 
	                <a href="#">菜单三</a></h3> 
	            <div style="margin:0px;padding:0px"> 
	                <ul class="center-left-menu">
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar</a></li>
					    <li><a href="#">sonar11111</a></li>
					</ul>
	            </div> 
	        </li> 
		</div>
		<div>
			<div class="center-main">
				<iframe id="main" src="${contextPath}/view/event/testGlobalEvent2"></iframe>
			</div>
		</div>
	</div>
</div>

<!-- skinSwitcher -->
<div id="skinSwitcher" class="skin-switcher"></div>	
</body>
</html>