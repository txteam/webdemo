<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>mainframe</title>
<link rel="stylesheet" type="text/css" href="/webdemo/css/default/om-default.css" />
<style type="text/css">
html,body,div{
	margin:0px;
	border:0px;
	height: 100%
}
.om-panel-body{
	padding: 0;
}
</style>

<script type="text/javascript" src="/webdemo/js/jquery.min.js"></script>
<script type="text/javascript" src="/webdemo/js/operamasks-ui.min.js"></script>

<script type="text/javascript" >
$(document).ready(function() {
	/*
	$("#bodyDiv").omPanel({
        width: 'fit',
        height: 'fit',
        title: 'webdemo mainframe',
        collapsed: false,//组件创建后为收起状态
        collapsible: false,//渲染收起与展开按钮
        closable: false //渲染关闭按钮
    });
	*/
    $('#page').omBorderLayout({
	    panels:[{ 
	       id:"top-tools-panel", 
	       title:"工具栏",
	       region:"north",
	       height:60,
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
	       width:150 
	   }], 
	   spacing:1,
	   fit:true
    });	
    
    var tabElement = $('#tabs').omTabs({
        height : "fit"
    });
    
    var ifh = tabElement.innerHeight() - tabElement.find(".om-tabs-headers").outerHeight() - 2;
    
    $('#tab1iframe').height(ifh);
    
    var navData = [{id:"n1",text:"menu1",expanded:true},
                   {id:"n2",text:"menu2",expanded:true},
                   {id:"n11",pid:"n1",text:"toAddDemo",url:"/webdemo/view/demo/addDemo.jsp"},
                   {id:"n21",pid:"n2",text:"menu1-subm1",url:"http://www.baidu.com"},
			       {id:"n22",pid:"n2",text:"menu2-subm2",url:"http://www.taobao.com"}];
    
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
	<div id="top-tools-panel"></div>
	<div id="main-panel">
		<div id="tabs">
			<ul>
				<li><a href="#tab1">tab1</a></li>
			</ul>
			<div id="tab1">
				<iframe id='tab1iframe' border=0 frameBorder='no' src='http://www.baidu.com' width='100%'></iframe>
			</div>
		</div>
	</div>
	<div id="navigator-panel">
		<div id="navTree"></div>
	</div>
	<div id="east-panel"></div>
</div>
</body>
</html>