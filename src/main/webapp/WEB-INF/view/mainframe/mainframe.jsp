<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>业务系统demo</title>
<script type="text/javascript">
	var _contextPath = "${contextPath}";
	var _queryMenuUrl = "${contextPath}/mainframe/queryMenuItemTreeListDependAuthority.action";
</script>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" src="${contextPath}/view/js/mainframe/mainframe.js" charset="utf-8"></script>
<style type="text/css">
#menuAccordion .panel-title{
	font-weight:normal;
}
</style>
<script type="text/javascript">
function logoutFun(){
	DialogUtils.confirm("","是否确认退出系统",function(flag){
		if(flag){
			window.location.href = _contextPath + "/mainframe/logout.action";
		}
	});
}
$.bindge("logout",logoutFun);
</script>
</head>
<body>
	<div id="index_layout">
		<div data-options="region:'north'" style="height: 70px; overflow: hidden;" class="logo">
			<div id="sessionInfoDiv" style="position: absolute; right: 0px; top: 0px;" 
				class="alert alert-info">
				欢迎你！
			</div>
			<div style="position: absolute; right: 0px; bottom: 0px;">
				<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'cog'">更换皮肤</a> 
				<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'cog'">控制面板</a> 
				<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'cog'">注销</a>
			</div>
			<div id="layout_north_pfMenu" style="width: 120px; display: none;">
				<div onclick="changeThemeFun('default');" title="default">default</div>
				<div onclick="changeThemeFun('gray');" title="gray">gray</div>
				<div onclick="changeThemeFun('metro');" title="metro">metro</div>
				<div onclick="changeThemeFun('bootstrap');" title="bootstrap">bootstrap</div>
				<div onclick="changeThemeFun('black');" title="black">black</div>
				<div class="menu-sep"></div>
				<div onclick="changeThemeFun('default');" title="default">default</div>
				<div onclick="changeThemeFun('gray');" title="gray">gray</div>
				<div onclick="changeThemeFun('metro');" title="metro">metro</div>
				<div onclick="changeThemeFun('bootstrap');" title="bootstrap">bootstrap</div>
				<div onclick="changeThemeFun('black');" title="black">black</div>
			</div>
			<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
				<div onclick="editCurrentUserPwd();">记事本</div>
				<div onclick="editCurrentUserPwd();">行事历</div>
				<div class="menu-sep"></div>
				<div onclick="editCurrentUserPwd();">修改密码</div>
				<div onclick="editCurrentUserPwd();">修改个人信息</div>
				<div class="menu-sep"></div>
				<div onclick="currentUserRole();">我的角色</div>
				<div onclick="currentUserResource();">我的权限</div>
			</div>
			<div id="layout_north_zxMenu" style="width: 100px; display: none;">
				<div onclick="logoutFun();">锁定窗口</div>
				<div onclick="logoutFun();">重新登录</div>
				<div class="menu-sep"></div>
				<div onclick="logoutFun();">退出系统</div>
			</div>
		</div>
		<div data-options="region:'west',split:true,
				tools : [{ iconCls : 'database_refresh',handler : function() {$.triggerGE('reloadMenus');} }]" 
				title="菜单" style="width: 200px; overflow: hidden;">
			<div id="menuAccordion"></div>
		</div>
		
		<div data-options="region:'center'" title="欢迎登录" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="border:false" style="overflow: hidden;">
					<iframe src="${contextPath }/portal/index.action" frameborder="0" style="border: 0; width: 100%; height: 98%;"></iframe>
				</div>
			</div>
		</div>
		
		<div data-options="region:'south',border:false" style="height: 30px; overflow: hidden;">
			<div class="panel-header panel-title" style="text-align: center;">
				放置消息提醒，或版本版权信息。
			</div>
		</div>
	</div>

	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'transmit'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'delete'">关闭</div>
		<div title="closeOther" data-options="iconCls:'delete'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'delete'">关闭所有</div>
	</div>
	
<div id="loginDialog" title="用户登录"
	style="width: 330px; height: 220px; overflow: hidden; display: none;">
	<form id="loginForm" method="post">
		<table class="table table-hover table-condensed">
			<tr>
				<th>登录名</th>
				<td><input name="name" type="text" 
					placeholder="请输入登录名" 
					class="easyui-validatebox" 
					data-options="required:true" value="孙宇"></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="pwd" type="password" placeholder="请输入密码"
					class="easyui-validatebox" 
					data-options="required:true" 
					value="123456"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>