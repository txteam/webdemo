<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainframe</title>
<link rel="stylesheet" type="text/css" href="${contextPath }/css/commons.css" />
<link rel="stylesheet" type="text/css" href="${contextPath }/css/mainframe.css" />
<script type="text/javascript" src="${contextPath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath }/operamasks/js/operamasks-ui.min.js"></script>
<script type="text/javascript" src="${contextPath }/jquery-ui/js/jquery-ui.js"></script>
<style type="text/css">
#navigatorTabs .om-panel-body {
	padding: 0px;
}
</style>

<script type="text/javascript">
$(document).ready(function() {	
	//生成页面布局
    $('#page').omBorderLayout({
	    panels:[{ 
	       id:"top-tools-panel", 
	       title:"工具栏",
	       region:"north",
	       height:76,
	       header:false,
	       collapsible:true 
	   },{ 
	       id:"main-panel", 
	       header:false,
	       title:"main",
	       region:"center" 
	   },{ 
	       id:"navigator-panel", 
	       title:"菜单", 
	       region:"west", 
	       resizable:false, 
	       collapsible:true, 
	       width:200 
	   },{ 
	       id:"bottom-panel", 
	       title:"菜单", 
	       region:"south", 
	       header:false,
	       resizable:false, 
	       collapsible:false,
	       height:32
	   }], 
	   
	   spacing:0,
	   fit:true
    });
	
	//顶部菜单
     $('#menu').omMenu({
         minWidth : 100,
         maxWidth : 150
      });
	$("#showMenuBtn").click(function(){
		$('#menu').omMenu('show',this);
	});
     //显示menu菜单

		
	//生成左侧菜单抽屉效果
	$('#make-accordion').omAccordion({
		height:"fit"
	});
    
    var tabElement = $('#tabs').omTabs({
    	lazyLoad : true,
    	border : false,
    	scrollable : false,
    	tabHeight : 22,
        height : "fit"
    });
    
    var ifh = tabElement.innerHeight() - tabElement.find(".om-tabs-headers").outerHeight() - 2;
    $('#tab1iframe').height(ifh);
    
    var navData = [{id:"n1",text:"menu1",expanded:true},
                   {id:"n2",text:"menu2",expanded:true},
                   {id:"n11",pid:"n1",text:"toAddDemo",url:"${contextPath}/view/demo/addDemo"},
                   {id:"n21",pid:"n2",text:"menu1-subm1",url:"${contextPath}/view/demo/addDemo"},
			       {id:"n22",pid:"n2",text:"menu2-subm2",url:"${contextPath}/view/demo/addDemo"}];
    
    $("#navTree").omTree({
        dataSource : navData,
        simpleDataModel: true,
        onClick : function(nodeData , event){
        	if(nodeData.url){
        		var tabId = tabElement.omTabs('getAlter', 'tab_'+nodeData.id);
        		if(tabId){
        			tabElement.omTabs('activate', tabId);
        		}else{
                	tabElement.omTabs('add',{
                        title : nodeData.text, 
                        tabId : 'tab_'+nodeData.id,
                        content : "<iframe id='" + nodeData.id + "' border=0 frameBorder='no' name='inner-frame' src='"+nodeData.url+"' height='"+ifh+"' width='100%'></iframe>",
                        closable : true
                    });
        		}
        	}
        }
    });
});
</script>
</head>
<body>
	<div id="page">
		<div id="top-tools-panel">
			<div id="navigatorTabs" class="layoutTop">
				<div class="menuButtonWrapper">
		    		<a id="showMenuBtn" href="javascript:void(0); return false;">
		            	<span>菜单</span>
		          </a>
		    	</div>
				
				<ul class="toolbar">
					<li class="leftCorner"></li>
		            <li id="passwordDown">
						<a href="javascript:void(0); return false;" title="修改密码">
							<img alt="修改密码" src="${contextPath}/images/iconPWvalidate.gif">
						</a>
		                <ul class="passwordSelect absLayer" style="display: none; ">
		                    <li><a href="javascript:void(0); return false;" title="修改密码">修改密码</a></li>
		                    <li><a href="javascript:void(0); return false;" title="重置密码">重置密码</a></li>
		                </ul>
			    	</li>
					<li class="spliter"></li>
		            <li id="consult">
						<a href="javascript:void(0); return false;" title="修改个人信息">
							<img alt="修改个人信息" src="${contextPath}/images/iconConsultation.gif">
						</a>
					</li>
		            <li class="spliter"></li>
					<li id="noteBook">
						<a href="javascript:void(0); return false;" title="记事本">
							<img alt="记事本" src="${contextPath}/images/iconNotebook.gif">
						</a>
					</li>
					<li class="spliter"></li>
					<li>
						<a href="javascript:void(0); return false;" title="计算器">
							<img alt="计算器" src="${contextPath}/images/iconCalculator.gif">
						</a>
					</li>
		            <li class="spliter"></li>
					<li>
						<a id="calendar" href="javascript:void(0);" title="行事历">
							<img alt="行事历" src="${contextPath}/images/iconCalendar.gif">
						</a>
					</li>
					<li class="rightCorner"></li>
				</ul>
			</div>
			<div class="layoutRibbon">
				<div class="ribbon-content-wrpper"></div>
			</div>
		</div>
		<div id="main-panel">
			<div id="tabs">
				<ul>
					<li><a href="#tab1">tab1</a></li>
				</ul>
				<div id="tab1" style="z-index: -999">
					<iframe id="tab1iframe" border=0 frameBorder='no' style="z-index:100"
						src='${contextPath}/view/calendar/calendar' width='100%'></iframe>
				</div>
			</div>
		</div>
		<div id="navigator-panel">


			<div id="navTree"></div>
		</div>
		<div id="east-panel"></div>

		<div id="bottom-panel">
			<div class="layoutBottom">
				<div id="oper-info" style="float: left">xxxx:xxx:xx</div>

				<div id="x1" style="float: right;">公告：xxxx</div>
				<div id="x2" style="float: right;">未处理信息</div>
			</div>
		</div>
	</div>

	<div id="menu" style="display: none;">
		<ul>
			<li>
				<a href="#"> <img class="icon" src="${contextPath }/images/blank.png"><span>节点0</span></a>
			</li>
			<li>
				<a href="#"> <img src="${contextPath }/images/blank.png"> <span>节点一</span></a>
			</li>
			<li>
				<a href="#"> <img class="icon" src="${contextPath }/images/calendar.gif"><span>节点二</span><span role="popup"></span></a>
				<ul>
					<li class="">
						<a href="#"><img class="icon" src="i${contextPath }/mages/calendar.gif"><span>节点一一</span></a>
					</li>
					<li class="">
						<a href="#"> <img src="${contextPath }/images/calendar.gif"><span>节点一二</span></a>
					</li>
					<li class="">
						<a href="#"> <img src="${contextPath }/images/calendar.gif"><span>节点一三</span></a>
					</li>
				</ul>
			</li>
			<li>
				<a href="#"><img class="icon" src="${contextPath }/images/blank.png"><span>节点3</span></a>
			</li>
		</ul>
	</div>
</body>
</html>