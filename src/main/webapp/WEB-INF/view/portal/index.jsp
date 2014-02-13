<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@include file="../includes/commonHead.jsp" %>

<link rel="stylesheet" href="${contextPath}/js/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="${contextPath}/js/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
	var portalLayout;
	var portal;
	$(function() {
		portalLayout = $('#portalLayout').layout({
			fit : true
		});
		$(window).resize(function() {
			portalLayout.layout('panel', 'center').panel('resize', {
				width : 1,
				height : 1
			});
		});

		panels = [ {
			id : 'p1',
			title : 'ChangeLog',
			height : 200,
			collapsible : true,
			href : '${contextPath}/portal/changeLog.action'
		}, {
			id : 'p2',
			title : 'bugView',
			height : 410,
			collapsible : true,
			href : '${contextPath}/portal/bugView.action'
		}, {
			id : 'p3',
			title : '版本计划',
			height : 200,
			collapsible : true,
			href : '${contextPath}/portal/links.action'
		}];

		portal = $('#portal').portal({
			border : false,
			fit : true,
			onStateChange : function() {
				//alert(getPortalState());
				/*
				$.cookie('portal-state', getPortalState(), {
					expires : 7
				});
				*/
			}
		});
		var state = null;//$.cookie('portal-state');
		if (!state) {
			state = 'p1,p3:p2';/*冒号代表列，逗号代表行*/
		}
		addPortalPanels(state);
		portal.portal('resize');

	});

	function getPanelOptions(id) {
		for ( var i = 0; i < panels.length; i++) {
			if (panels[i].id == id) {
				return panels[i];
			}
		}
		return undefined;
	}
	function getPortalState() {
		var aa = [];
		for ( var columnIndex = 0; columnIndex < 2; columnIndex++) {
			var cc = [];
			var panels = portal.portal('getPanels', columnIndex);
			for ( var i = 0; i < panels.length; i++) {
				cc.push(panels[i].attr('id'));
			}
			aa.push(cc.join(','));
		}
		return aa.join(':');
	}
	function addPortalPanels(portalState) {
		var columns = portalState.split(':');
		for ( var columnIndex = 0; columnIndex < columns.length; columnIndex++) {
			var cc = columns[columnIndex].split(',');
			for ( var j = 0; j < cc.length; j++) {
				var options = getPanelOptions(cc[j]);
				if (options) {
					var p = $('<div/>').attr('id', options.id).appendTo('body');
					p.panel(options);
					portal.portal('add', {
						panel : p,
						columnIndex : columnIndex
					});
				}
			}
		}
	}
</script>
</head>
<body>
	<div id="portalLayout">
		<div data-options="region:'center',border:false">
			<div id="portal" style="position: relative">
				<div></div>
				<div></div>
			</div>
		</div>
	</div>
</body>
</html>