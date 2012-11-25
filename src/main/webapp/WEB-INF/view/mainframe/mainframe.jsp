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
<script type="text/javascript" src="${contextPath }/js/mainframe/mainframe.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#menu").wijmenu({
	    //position : {my: "top right", at: "buttom right"}
	});
	$("#menuItems").find("li").not($("#menuItems").find("li").has("ul")).draggable({ opacity: 0.7, helper: "clone" });
	
	var layout = new Layout();

	var customerMenu = new Menu({
	    $menu:$("#customer-menus"),
	    initHeight:layout._accordingHeight - 10
	});
	layout.$customerMenuAccordion.bind("accordionResized",function(){
	    customerMenu.menuResize(layout._accordingWidth - 10,layout._accordingHeight - 10);
	});

	
	var $tabs = $("#mainTabs").wijtabs();
	$("#mainToolTabs").wijtabs({
	    alignment: "left"
	});
	
	$.widget("tx.styleswitch",{
	    // default options
	    options: {
	    	styles : []
	    },

	    _create: function() {
	        var progress = this.options.value + "%";
	        this.element
	            .addClass( "progressbar" )
	            .text( progress );
	    },
	    
	    _init: function(){
	    	
	    },
	    
	    destroy: function(){
	    	
	    }
	});
	
});
</script>
<style type="text/css">
.customer-menus{
	width:96%;
	height:100;
	overflow:auto;
}
</style>
<body>
<div class=".container">
	<!-- top -->
	<div class="top">
		<div class="top-header ui-widget-header ui-corner-top">
			<!-- menu显示占位符 
			<span class="menu"></span>
			<!--
			<li> 
	            <h3><a href="#">常用菜单</a></h3> 
	        </li> 
	        -->
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
	            <div style="margin:0px;padding:0px;overflow:hidden;"> 
	               <ul id="customer-menus" class="customer-menus">
					    <li><a href="#">禅道</a></li>
					    <li><a href="#">Wiki</a></li>
					    <li><a href="#">hudson</a></li>
					    <li><a href="#">sonar</a></li>
					</ul>
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
				<div class="center-main-tabs">
					<div id="mainTabs">
						<ul> 
		                    <li><a href="#mainTabs-1">Nunc tincidunt</a></li> 
		                    <li><a href="#mainTabs-2">Proin dolor</a></li>
		                </ul> 
		                <div id="mainTabs-1"> 
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
		                <div id="mainTabs-2"> 
		                		tabs2Content
		                </div>
		           	</div> 
				</div>
				<div class="center-main-tools">
					<div id="mainToolTabs" style="overflow: hidden;">
						<ul> 
		                    <li><a href="#mainToolTabs-1">记事本</a></li> 
		                    <li><a href="#mainToolTabs-2">行事历</a></li>
		                </ul> 
		                <div id="mainToolTabs-1"> 
		                    <p> 
		                        mainToolTabs-1111
		                    </p> 
		                </div> 
		                <div id="mainToolTabs-2">
		                		mainToolTabs-2222
		                </div>
	                </div>
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
	           <li><a>禅道</a></li>
	           <li><a>wiki</a></li>
	           <li><a>menuitem1</a>
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
	           <li><a>hudson</a></li>
	           <li><a>sonar</a></li>
	           <li><a>wiki</a>
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
	           <li></li>
	           <li><a>修改密码</a></li>
	           <li><a>退          出</a></li>
	        </ul>
	    </li>
	</ul>
</div>
</body>
</html>