<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>configOperatorAuth</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
var tabs = null;
var $currentAuthTreeEl = null;
var currentTree = null;
var authType2AuthItemListMap = null;
$(document).ready(function() {
	parent.DialogUtils.progress('close');
	$.post('${contextPath}/auth/queryOperatorAuthType2AuthItemListMap.action',{operatorId:'${operatorId}'},function(data){
		authType2AuthItemListMap = data;
		//初始化tabs
		tabs = $("#authTabView").tabs({
			fit:true,
			tabPosition:'right',
			onSelect: function(title,index){
				loadCurrentPannel();
			}
		});
		//加载需要加载的tab内容
		loadCurrentPannel();
		//加载pannel内容
		function loadCurrentPannel(){
			var $tabPannel = $("#authTabView").tabs('getSelected');
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
	$("#confirmBtn").click(function(){
		parent.DialogUtils.closeDialogById("component_dialog_chooseOrganization");
	});
});
//如果tabs初始化
function initAuthTree($currentAuthTreeEl,authType,configAble,authItemList){
	//alert('init_' + authType);
	currentTree = $currentAuthTreeEl.tree({
		idField : 'id',
		parentField : 'parentId',
		textField : 'name',
		iconField : function(item){
			return 'database_key';	
		},
		checkbox : true,
		cascadeCheck : true,
		lines : true,
		onlyLeafCheck: false,
		data: authItemList,
		/*
		onCheck: function(node, checked){
			if(checked){
				alert("checked:true");
				alert(node.nodeName);
				var parentObj = $currentAuthTreeEl.tree('getParent');
				alert("parentEl == null:" + (parentObj == null));
			}else{
				alert("checked:false");
				var chidrensEls = $currentAuthTreeEl.tree('getChildren',node);
				alert("chidrensEls == null:" + (chidrensEls == null));
			}
		},
		*/
		onClick : function(node){
			$.triggerge("choose_organization_" + "${eventName}",[node['attributes']]);
		}
	});
	
	$("#confirmBtn_" + authType).click(function(){
		DialogUtils.progress({
	        text : '数据提交中，请等待....'
		});
		var $treeEL = $currentAuthTreeEl;
		var checked = $treeEL.tree('getChecked',['checked','indeterminate']);
		var authItemIds = [];
		if(checked.length > 0){
			$.each(checked,function(index,item){
				authItemIds[index] = item.id;
			});
		}
		//提交数据
		$.post("${contextPath}/auth/saveOperator2AuthItemList.action",{
			    	authType: authType,
			    	operatorId: '${operatorId}',
			    	authItemId: authItemIds
			    },
			    function(data) {
					if(data){
						parent.DialogUtils.tip("配置人员权限成功");
					}
					DialogUtils.progress('close');
			    }
		);
	});
}
function redo() {
	var node = $currentAuthTreeEl.tree('getSelected');
	if (node) {
		$currentAuthTreeEl.tree('expandAll', node.id);
	} else {
		$currentAuthTreeEl.tree('expandAll');
	}
}
function undo() {
	var node = $currentAuthTreeEl.tree('getSelected');
	if (node) {
		$currentAuthTreeEl.tree('collapseAll', node.id);
	} else {
		$currentAuthTreeEl.tree('collapseAll');
	}
}
</script>
</head>
<body class="body">
	<!-- authTreeTabs -->
	<div id="authTabView" class="easyui-tabs" data-options="fit:true">
		<c:forEach items="${authTypeList}" var="authType" varStatus="status">
			<div title="${authType.name}" 
				data-options="${status.index == 0 ? 'selected:true' : ''}"
				style="overflow-y:auto;overflow-x:hidden;">
				<div class="datagrid-toolbar">
			        <a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a>
			        <a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a>
			    </div>
				<form id="form_${authType.authType}" method="post">
					<input type="hidden" name="authType" value="${authType.authType}"/>
					<input type="hidden" name="operatorId" value="${operatorId}"/>
					<ul class="checkAblePostTreeNode"
						isLoad="false" authType="${authType.authType}" configAble="${authType.configAble}"
						style="margin-bottom: 40px;padding-top:2px;padding-left: 5px"></ul>
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