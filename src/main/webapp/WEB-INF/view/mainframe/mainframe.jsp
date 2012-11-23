<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainframe</title>
<%@include file="../includes/commonHead.jsp" %>

<link rel="stylesheet" type="text/css" href="${contextPath }/css/mainframe.css" />
<script type="text/javascript" src="${contextPath }/js/mainframe/mainframe.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#menu").wijmenu();

	var layout = new Layout();
	layout.init();
	
	$("#main-tabs").wijtabs();
	//$(".center-main-tools").wijtabs({
	//    alignment: "left"}
	//);
});
</script>
<style type="text/css">

</style>
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
	        <li> 
	            <h3><a href="#">常用菜单</a></h3> 
	            <div style="margin:0px;padding:0px"> 
	               Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque. 
	                    Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc. Nam a 
	                    nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. 
	                    Vestibulum a velit eu ante scelerisque vulputate. 
	            </div>
	        </li> 
	        <li> 
	            <h3> 
	                <a href="#">Second</a></h3> 
	            <div style="margin:0px;padding:0px"> 
	                <p> 
	                    Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus 
	                    hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum 
	                    tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna. 
	                </p> 
	            </div> 
	        </li> 
	        <li> 
	            <h3> 
	                <a href="#">Second33</a></h3> 
	            <div style="margin:0px;padding:0px"> 
	                <p> 
	                    Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus 
	                    hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum 
	                    tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna. 
	                </p> 
	            </div> 
	        </li> 
		</div>
		<div>
			<div class="center-main">
				<div>
				<div class="center-main-tabs" id="main-tabs">
					<ul> 
	                    <li><a href="#main-tabs-1">Nunc tincidunt</a></li> 
	                    <li><a href="#main-tabs-2">Proin dolor</a></li>
	                </ul> 
	                <div id="main-tabs-1"> 
	                    <p> 
	                        Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec 
	                        arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. 
	                        Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper 
	                        leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales 
	                        tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel 
	                        pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. 
	                        Nunc tristique tempus lectus.
	                    </p> 
	                </div> 
	                <div id="main-tabs-2" class="main-tab-content-1"> 
	                    Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec 
	                        arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. 
	                        Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper 
	                        leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales 
	                        tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel 
	                        pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. 
	                        Nunc tristique tempus lectus.
	                </div>
	           	</div> 
				</div>
				<div class="center-main-tools">
				ddd
					<!--  
					<ul> 
	                    <li><a href="#tabs2-1">记事本</a></li> 
	                    <li><a href="#tabs2-2">行事历</a></li>
	                </ul> 
	                <div id="tabs2-1" class="main_tab_item"> 
	                    <p> 
	                        Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec 
	                        arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. 
	                        Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper 
	                        leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales 
	                        tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel 
	                        pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. 
	                        Nunc tristique tempus lectus.
	                    </p> 
	                </div> 
	                <div id="tabs2-2" style="margin:0px;padding:0px"> 
	                    <iframe id="test" src="http://www.baidu.com" height="500" width="500"></iframe>
	                </div>
	                -->
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