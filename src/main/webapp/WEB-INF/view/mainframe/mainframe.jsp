<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainframe</title>
<link rel="stylesheet" type="text/css" href="${contextPath }/css/commons_cupertino.css" />
<script type="text/javascript" src="${contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath }/js/commons.js"></script>
<script type="text/javascript" src="${contextPath }/jquery-ui/js/jquery-ui-1.9.1.custom.min.js"></script>
<script type="text/javascript" src="${contextPath }/wijmo/js/jquery.wijmo-complete.all.2.2.2.min.js"></script>
<script type="text/javascript" src="${contextPath }/wijmo/js/jquery.wijmo-open.all.2.2.2.min.js"></script>

<link rel="stylesheet" type="text/css" href="${contextPath }/css/mainframe.css" />
<script type="text/javascript" src="${contextPath }/js/mainframe/mainframe.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    var menu = new Menu({
        menuBtnId:"menuBtn",
        menuItemsContainerId:"menuItmesContainer"
    });
});
</script>
<body>
<div class=".container">
	<!-- top -->
	<div class="top">
		<div class="top-header ui-widget-header ui-corner-top">
			<span class="menu">
				<button id="menuBtn">菜单</button>
			</span>
			<span>
				待我处理
			</span>
			<span>
				快捷菜单
			</span>
			<span class="choose-skin">
				<select id="skinSwitcher">
					<option>--choose skin--</option>
				</select>
			</span>
		</div>
		<!-- menu -->
		<div id="menuItmesContainer" class="menu-itmes-container">
			<ul id="menu">
			    <li><a>menuitem1</a>
			        <ul class="ui-widget-header">
			           <li class="ui-icon-gear"><a>menuitem1a</a></li>
			           <li><a>menuitem2a</a>
				           <ul class="ui-widget-header">
					           <li><a>menuitem2aa</a></li>
					           <li><a>menuitem2ab</a></li>
					        </ul>
				        </li>
			        </ul>
			    </li>
			    <li><a>menuitem2</a></li>
			    <li><a>menuitem3</a></li>
			</ul>
		</div>
		<div class="top-content ui-widget-content">
			
		</div>

		<div id="topSpliter" class="ui-widget-header top-spliter"></div>
	</div>
	
	<!-- center -->
	<div class="center">
		
	</div>
	
	<!-- footer -->
	<div class="footer ui-widget-header ui-corner-bottom">
		
	</div>
</div>
</body>
</html>