<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryAuthView</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {
	$.post("${contextPath}/auth/queryAuthType2AuthItemListMap.action",
		function(authType2AuthItemListMap){
			if($.ObjectUtils.isEmpty(authType2AuthItemListMap)){
				DialogUtils.tip("当前人员无系统任何权限");
			}
			$.each(authType2AuthItemListMap,function(authType,authItemList){
				$("#authTabView").tabs('add',{   
				    title:'New Tab',   
				    content:'Tab Body',   
				    closable:true,   
				    tools:[{   
				        iconCls:'database_refresh',   
				        handler:function(){   
				            alert('refresh');   
				        }   
				    }]   
				}); 
			});
		}
	);

});
</script>
</head>
<body>
<div id="authTabView" class="easyui-tabs" style="width:500px;height:250px;">  

</div> 
</body>