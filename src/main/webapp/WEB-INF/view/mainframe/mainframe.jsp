<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainframe</title>
<link rel="stylesheet" type="text/css" href="${contextPath }/css/commons.css" />
<script type="text/javascript" src="${contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath }/js/commons.js"></script>
<script type="text/javascript" src="${contextPath }/jquery-ui/js/jquery-ui-1.9.1.custom.min.js"></script>
<script type="text/javascript" src="${contextPath }/wijmo/js/jquery.wijmo-complete.all.2.2.2.min.js"></script>

<link rel="stylesheet" type="text/css" href="${contextPath }/css/mainframe.css" />
<script type="text/javascript">
$(document).ready(function () {
	$("#menu1").wijmenu();
});
</script>
<body>
<div id="container" class="ui-widget-content">
	<span class="container-left ui-widget-header ui-corner-left"></span>
	<div class="top">
		<div class="top-header ui-widget-header ui-corner-top">
			<span class="menu">

			</span>
		
			<span class="choose-skin">
				<select id="skinSwitcher">
					<option>--choose skin--</option>
				</select>
			</span>	
		</div>
		<div id="topSpliter" class="ui-widget-header top-spliter"></div>
	</div>
	
	<div id="center">
		11
	</div>
	
	<div class="footer ui-widget-header ui-corner-bottom">
		
	</div>
	<span class="container-right ui-widget-header ui-corner-right"></span>
</div>
</body>
</html>