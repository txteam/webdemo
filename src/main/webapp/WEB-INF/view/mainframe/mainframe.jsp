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
	var _removeClassTimer = null;
	function _removeOpacity(){
		if(_removeClassTimer != null){
			clearTimeout(_removeClassTimer);
		}
		_removeClassTimer = setTimeout(function(){
			$("#menuItems").removeClass("opacity_20");
		},500);
	}
	$("#menuItems").find("li").not($("#menuItems").find("li").has("ul")).draggable({ 
		opacity: 0.8, 
		helper: function(event){
			return $( "<div class='ui-widget-header'>I'm a custom helper</div>" );
		},
		start: function(){
			$("#menuItems").addClass("opacity_20");
			_removeOpacity();
		},
		drag: function(){
			_removeOpacity();
		},
		stop: function(){
			_removeOpacity();
		}
	});
	
	var layout = new Layout();
	
	//var $tabs = $("#mainTabs").wijtabs();
	$("#mainToolTabs").wijtabs({
	    alignment: "left"
	});	
	
	$(".top").txtabs({
		tabsHandleContainerId:"topHeader"
	});
	
	var topContentWidth = $(".top-content").innerWidth();
	var splitterDistanceWidth = topContentWidth > 270 ? topContentWidth - 270 : 0;
	$(".top-content").wijsplitter({
	    showExpander: false,
	    collapsingPanel : "panel2",
	    splitterDistance : splitterDistanceWidth,
	    resizeSettings: { ghost :false},
	    panel1 : {
            scrollBars : "hidden"
        },
        panel2 : {
            scrollBars : "hidden"
        }
	});
	$(".top-content").find(".top-content-left").next("div.ui-resizable-handle").mousedown(function() {
        return false;
    });
	
	$("#skinSwitcher").chemeswitcher({
	    contextPath:"${contextPath}",
	    themeCookieName:"tx_theme",
	    switchSkin:function(event,id){
	    	$.triggerge("switch_skin",id);
	    }
	});
});
</script>
<style type="text/css">

</style>
<body class="ui-widget-content">
<div class="container">
	<!-- top -->
	<div class="top ui-widget-content">
		<div id="topHeader" class="top-header ui-widget-header ui-corner-top">
			<!-- menu显示占位符 
			<span class="menu"></span>
			<!--
			<li> 
	            <h3><a href="#">常用菜单</a></h3> 
	        </li> 
	        -->
	        <ul> 
	        	<li><a href="#menuTabs-1">菜单一</a></li> 
		        <li><a href="#menuTabs-2">菜单二</a></li>
		    </ul> 
		</div>
		<div class="top-content">
			<div class="top-content-left">
				<div id="menuTabs-1" class="">
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<span><center><span class="ui-icon ui-icon-gear"></span></center></span>
						<a>快捷菜单一</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<span><span class="ui-icon ui-icon-gear"></span></span>
						<a>aa</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<span><span class="ui-icon ui-icon-gear"></span></span>
						<a>aa</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<span><span class="ui-icon ui-icon-gear"></span></span>
						<a>aa</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<span><span class="ui-icon ui-icon-gear"></span></span>
						<a>aa</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<span><center><span class="ui-icon ui-icon-gear"></span></center></span>
						<a>aaaaaaaaaaaaaaaa</a>
					</span>
				</div>
				<div id="menuTabs-2" class="top-content">
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<a>11111</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<a>222222</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<a>333333</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<a>444444</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<a>aa</a>
					</span>
					<span class="top-content-menuitem ui-helper-reset ui-state-default ui-corner-all">
						<a>aa</a>
					</span>
				</div>
			</div>
			<div class="top-content-right">
				xxx
			</div>
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
					    <li id="demoList"><a href="${contextPath}/demo/queryDemoList">demo     列表</a></li>
					    <li id="demoAjaxList"><a href="${contextPath}/demo/queryDemoList">demo ajax列表</a></li>
					    <li id="demoAjaxPagedList"><a href="${contextPath}/demo/queryDemoList">demo ajax分页列表</a></li>
					    <li id="dialogUtils"><a href="${contextPath}/view/demo/dialogUtilsDemo">demo dialogUtils</a></li>
					    <li id="dialogUtils"><a href="${contextPath}/demo/queryAuthTreeDemo">queryAuthTreeDemo</a></li>
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
		                    <li id="mainTabs_welcome"><a href="#mainTabs-1">个人面板</a></li> 
		                    <li id="mainTabs_calendar"><a href="#mainTabs-2">行事历</a></li>
		                    <li id="mainTabs_notepad"><a href="#mainTabs-3">记事本</a></li>
		                    <li id="mainTabs_test4"><a href="#mainTabs-4">testGlobalEvent1</a><span class="ui-icon ui-icon-close"></span></li>
		                    <li id="mainTabs_test5"><a href="#mainTabs-5">testGlobalEvent2</a><span class="ui-icon ui-icon-close"></span></li>
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
		                	<iframe src="" 
		                		newSrc="${contextPath}/view/calendar/calendar" ></iframe>
		                </div>
		                <div id="mainTabs-3"> 
		                	<iframe src="${contextPath}/view/notepad/notepad" 
		                		newStr="${contextPath}/view/notepad/notepad" 
		                		lazyLoad="true"></iframe>
		                </div>
		                <div id="mainTabs-4"> 
		                	<iframe src="${contextPath}/view/event/testGlobalEvent1" 
		                		lazyLoad="true" refreshWhenSelect="true"></iframe>
		                </div>
		                <div id="mainTabs-5"> 
		                	<iframe src="${contextPath}/view/event/testGlobalEvent2"></iframe>
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

<!-- skinSwitcher -->
<div id="skinSwitcher" class="skin-switcher"></div>	

<!-- menu -->
<div id="menuItmesContainer" class="menu-itmes-container" style="height: 100px">
	<ul id="menu">
	    <li id="menuLink"><a>&nbsp;菜&nbsp;单&nbsp;</a>
	        <ul id="menuItems" class="ui-widget-content">
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