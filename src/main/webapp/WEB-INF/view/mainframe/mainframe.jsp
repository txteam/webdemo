<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	$("#menuItmesContainer div[role=menubar]").addClass("ui-state-default");
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
	//给没有子菜单的，菜单项绑定菜单点击方法
	$("#menuItems").find("li").not($("#menuItems").find("li").has("ul")).click(menuClickFunction);
	
	//页面渲染
	var layout = new Layout();
	
	//var $tabs = $("#mainTabs").wijtabs();
	$("#mainToolTabs").wijtabs({
	    alignment: "left"
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
<body>
<div class="container">
	<!-- top -->
	<div class="top ui-widget-content">
		<div id="topHeader" class="top-header ui-widget-header ui-helper-reset ui-corner-top">
	        <ul>
	        	<c:forEach items="${toolMenuItemTreeList}" var="menuItem">
	        		<c:if test="${menuItem != null && fn:length(menuItem.childs) > 0}">
	        		<li><a href="#toolMenuTabs-${menuItem.id}">${menuItem.text }</a></li> 
	        		</c:if>
	        	</c:forEach>
		    </ul> 
		</div>
		<div class="top-content">
			<div class="top-content-left">
				<c:forEach items="${toolMenuItemTreeList}" var="menuItem">
					<c:if test="${menuItem != null && fn:length(menuItem.childs) > 0}">
	        		<div id="toolMenuTabs-${menuItem.id}" class="top-content">
	        			<c:forEach items="${menuItem.childs}" var="childMenuItem">
	        			<span>
							<span><center><span class="ui-icon ui-icon-gear"></span></center></span>
							<a id="${childMenuItem.id }" href="${childMenuItem.href}" 
								configTarget="${childMenuItem.target}" selectRefresh="${childMenuItem.selectRefresh}" openNewEveryTime="${childMenuItem.text}"
								width="${childMenuItem.width}" height="${childMenuItem.height}" isModal="${childMenuItem.modal}"
								eventType="${childMenuItem.eventType}" params='${childMenuItem.params}'>${childMenuItem.text}</a>
						</span>
	        			</c:forEach>
					</div>
					</c:if>
	        	</c:forEach>
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
					    <li><a id="demoList" href="${contextPath}/demo/queryDemoList">demo     列表</a></li>
					    <li><a id="demoAjaxList" href="${contextPath}/demo/queryDemoList">demo ajax列表</a></li>
					    <li><a id="demoAjaxPagedList" href="${contextPath}/demo/queryDemoList">demo ajax分页列表</a></li>
					    <li><a id="dialogUtils" href="${contextPath}/view/demo/dialogUtilsDemo">demo dialogUtils</a></li>
					    <li><a id="dialogUtils" href="${contextPath}/demo/queryAuthTreeDemo">queryAuthTreeDemo</a></li>
					    <li><a id="test1" href="${contextPath}/view/demo/globalevent/testGlobalEvent1">testGlobalEvent1</a></li>
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
		                    <li id="mainTabs_calendar"><a href="#mainTabs-2">行事历</a><span class="ui-icon ui-icon-close"></span></li>
		                    <li id="mainTabs_notepad"><a href="#mainTabs-3">记事本</a><span class="ui-icon ui-icon-close"></span></li>
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
		                	<iframe src="" 
		                		newStr="${contextPath}/view/notepad/notepad" 
		                		lazyLoad="true"></iframe>
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
<div id="menuItmesContainer" class="menu-itmes-container">
	<ul id="menu">
	    <li id="menuLink"><a>&nbsp;菜&nbsp;单&nbsp;</a>
	    	<ul id="menuItems" class="ui-widget-content">
    			<c:forEach items="${mainMenuItemTreeList}" var="menuItem"><li id="menuLink">
   				<a id="${menuItem.id }" href="${menuItem.href}" 
							configTarget="${menuItem.target}" selectRefresh="${menuItem.selectRefresh}" openNewEveryTime="${menuItem.text}"
							width="${menuItem.width}" height="${menuItem.height}" isModal="${menuItem.modal}"
							eventType="${menuItem.eventType}" params='${menuItem.params}'>${menuItem.text}</a>
					<c:if test="${fn:length(menuItem.childs) > 0}"><ul>
        			<c:forEach items="${menuItem.childs}" var="childMenuItem"><li id="menuLink">
        				<a id="${childMenuItem.id }" href="${childMenuItem.href}" 
								configTarget="${childMenuItem.target}" selectRefresh="${childMenuItem.selectRefresh}" openNewEveryTime="${childMenuItem.text}"
								width="${childMenuItem.width}" height="${childMenuItem.height}" isModal="${childMenuItem.modal}"
								eventType="${childMenuItem.eventType}" params='${childMenuItem.params}'>${childMenuItem.text}</a>
	        				<c:if test="${fn:length(childMenuItem.childs) > 0}"><ul>
							<c:forEach items="${childMenuItem.childs}" var="threeChildMenuItem"><li id="menuLink">
		        				<a id="${threeChildMenuItem.id }" href="${threeChildMenuItem.href}" 
									configTarget="${threeChildMenuItem.target}" selectRefresh="${threeChildMenuItem.selectRefresh}" openNewEveryTime="${threeChildMenuItem.text}"
									width="${threeChildMenuItem.width}" height="${threeChildMenuItem.height}" isModal="${threeChildMenuItem.modal}"
									eventType="${threeChildMenuItem.eventType}" params='${threeChildMenuItem.params}'>${threeChildMenuItem.text}</a>
		        			</li></c:forEach>
							</ul></c:if>
        			</li></c:forEach>
					</ul></c:if>
				</li></c:forEach>
	           <li></li>
	           <li><a>修改密码</a></li>
	           <li><a>退          出</a></li>
	        </ul>
	    </li>
	</ul>
</div>
</body>
</html>