<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainframe</title>
<link rel="stylesheet" type="text/css" href="${contextPath }/css/commons_redmond.css" />
<script type="text/javascript" src="${contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath }/js/commons.js"></script>
<script type="text/javascript" src="${contextPath }/jquery-ui/js/jquery-ui-1.9.1.custom.min.js"></script>
<script type="text/javascript" src="${contextPath }/wijmo/js/jquery.wijmo-complete.all.2.2.2.min.js"></script>
<script type="text/javascript" src="${contextPath }/wijmo/js/jquery.wijmo-open.all.2.2.2.min.js"></script>

<link rel="stylesheet" type="text/css" href="${contextPath }/css/mainframe.css" />
<script type="text/javascript" src="${contextPath }/js/mainframe/mainframe.js"></script>
<style type="text/css">
#splitter{
	height: 300px; 
} 
</style>
<script type="text/javascript">


$(document).ready(function() {
	$("#menu").wijmenu();

	var layout = new Layout();
	layout.init();

});
</script>

<body>
<div class=".container">
	<!-- top -->
	<div class="top">
		<div class="top-header ui-widget-header ui-corner-top">
			<!-- menu显示占位符 -->
			<span class="menu"></span>
		</div>
		<div class="top-content ui-widget-content">
			
		</div>
		<div id="topSpliter" class="ui-widget-header top-spliter"></div>
	</div>
	
	<!-- center -->
	<div class="center">
	    <div class="center-left">
	        	自定义常用菜单
	   
	    </div>
	    <div>
	        <div class="center-main">
	            <div>
	            	tabs
	            </div>
	            <div>
	                	行事历，记事本，公告，便签
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- footer -->
	<div class="footer ui-widget-header ui-corner-bottom">
		
	</div>
</div>

<!-- menu -->
<div id="menuItmesContainer" class="menu-itmes-container" style="height: 100px">
	<ul id="menu">
	    <li id="menuLink"><a>&nbsp;菜&nbsp;单&nbsp;</a>
	        <ul id="menuItems">
	           <li><a>menuitem1</a></li>
	           <li><a>menuitem2</a>
		           	<ul>
		           		<li><a>menuitem21</a></li>
				        <li><a>menuitem22</a></li>
				        <li><a>menuitem23</a>
				        	<ul>
				        		<li><a>menuitem231</a></li>
				        		<li><a>menuitem232</a></li>
				        		<li><a>menuitem233</a></li>
				        	</ul>
				        </li>
			   		</ul>
	           </li>
	           <li><a>menuitem3</a></li>
	        </ul>
	    </li>
	</ul>
</div>
</body>
</html>