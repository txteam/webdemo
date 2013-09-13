<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>chooseOrganization</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var tabs = null;
var $currentAuthTreeEl = null;
var currentTree = null;
var authType2AuthItemListMap = null;
$(document).ready(function() {
	$.post('${contextPath}/auth/queryAuthType2AuthItemListMap.action',{postId:'${postId}'},function(data){
		authType2AuthItemListMap = data;
		
		//初始化tabs
		tabs = $("#authTabView").tabs({
			fit:true,
			tabPosition:'right',
			onSelect: function(title,index){
				var target = this;
				var $element = $(target);
				var $tabPannel = $element.tabs('getSelected');
				
				$currentAuthTreeEl = $tabPannel.find("ul.checkAblePostTreeNode");
				
				var isLoad = ($currentAuthTreeEl.attr('isLoad') == 'true');
				var isConfigAble = ($currentAuthTreeEl.attr('configAble') == 'true');
				var authType = $currentAuthTreeEl.attr('authType');
				
				//获取当前操作面板的TreeGrid句柄
				
				//如果当前面板树尚未加载，就在此处进行加载
				if(!isLoad){
					initAuthTree($currentAuthTreeEl,authType,isConfigAble,authType2AuthItemListMap[authType]);
					$currentAuthTreeEl.attr('isLoad','true');
				}
			}
		});
	});
	$("#confirmBtn").click(function(){
		parent.DialogUtils.closeDialogById("component_dialog_chooseOrganization");
	});
});
//如果tabs初始化
function initAuthTree($currentAuthTreeEl,authType,configAble,authItemList){
	currentTree = $currentAuthTreeEl.tree({
		idFiled : 'id',
		parentField : 'parentId',
		textFiled : 'name',
		iconFiled : function(item){
			return 'database_key';	
		},
		checkbox : true,
		cascadeCheck : true,
		lines : true,
		data: authItemList,
		onClick : function(node){
			$.triggerge("choose_organization_" + "${eventName}",[node['attributes']]);
		}
	});
	
	$("#confirmBtn_" + authType).click(function(){
		$("#form_" + authType).ajaxSubmit({
			
		});
	});
}
</script>
</head>
<body class="body">
	<!-- authTreeTabs -->
	<div id="authTabView" class="easyui-tabs">
		<c:forEach items="${authTypeList}" var="authType">
			<div title="${authType.name}" style="overflow-y:auto;overflow-x:hidden;height:fit;width:auto">
				<form id="form_${authType.authType}" method="post">
				<ul class="checkAblePostTreeNode"
					isLoad="false" authType="${authType.authType}" configAble="${authType.configAble}"
					style="padding-bottom: 50px;padding-top:2px;padding-left: 5px"></ul>
				<div class="dialog-button" style="position:absolute;bottom: 0px;width:100%">
					<a id="confirmBtn_${authType.authType}" href="#" class="easyui-linkbutton">确认</a>&nbsp;&nbsp;&nbsp;</div>
				</form>
		    </div>
		</c:forEach>
	</div>
	<div id="tab-tools">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="addPanel()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="removePanel()"></a>
    </div>
</body>