<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单配置帮助</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript">

</script>
<style type="text/css">

</style>	
<body>
<div style="padding: 5px">
<pre>
	配置文件：
		src/main/resources/context/menuConfig.xml
		classpath:/context/menuConfig.xml
	
	当前项目中仅存在主菜单：
	所以这里仅对主菜单配置进行说明：
		菜单项：
			menu 
				id="xxx" 
				text="菜单管理"
				href="/mainframe/toQueryAllMenuItemTreeList.action" 
				authKey="menu_config"
				target="mainTabs" 
				selectRefresh="false"
<form class="form">
	<table>
		<tr>
			<th>id</th>
			<td>菜单项唯一键,不允许重复，如果重复启动期间将会出现异常</td>
		</tr>
		<tr>
			<th>text</th>
			<td>菜单名称，直接关系到菜单中显示的内容</td>
		</tr>
		<tr>
			<th>target</th>
			<td>
				菜单弹出目标：合法值（ mainTabs,openDialog,triggerGlobalEvent）<br/>
				mainTabs:将在页面center中显示href的链接
				openDialog:将打开一个id=menu.id的dialog对象，如果该对象已经存在，则显示该dialog.根据menu.modal决定弹出的dialog是否为模态
				triggerGlobalEvent:出发一个menu.eventType的全局事件
			</td>
		</tr>
		<tr>
			<th>href</th>
			<td>菜单名称，直接关系到菜单中显示的内容</td>
		</tr>
		<tr>
			<th>tips</th>
			<td>鼠标悬停菜单时显示信息（暂未进行支持）</td>
		</tr>
		<tr>
			<th>authKey</th>
			<td>菜单项，所需权限，如果菜单项不需要权限则不设置该值即可。如果父级菜单需要权限，对应菜单项目，则默认为应该具有父级菜单权限才可以显示</td>
		</tr>
		<tr>
			<th>valid</th>
			<td>菜单项是否有效(暂时未进行支持)</td>
		</tr>
		<tr>
			<th>visible</th>
			<td>菜单项是否可见(暂时未进行支持)</td>
		</tr>
		<tr>
			<th>selectRefresh</th>
			<td>如果target为mainTabs时：点选菜单时是否需要刷新tabs(暂时未进行支持)</td>
		</tr>
		<tr>
			<th>openNewEveryTime</th>
			<td>如果target为mainTabs时：点选菜单时是否每次都打开一个新的tab(暂时未进行支持)</td>
		</tr>
		<tr>
			<th>width</th>
			<td>如果target为mainTabs时：点选菜单时是否每次都打开一个新的tab(暂时未进行支持)</td>
		</tr>
		<tr>
			<th>height</th>
			<td>如果target为mainTabs时：点选菜单时是否每次都打开一个新的tab(暂时未进行支持)</td>
		</tr>
		<tr>
			<th>param</th>
			<td>传递值</td>
		</tr>
		<tr>
			<th>data</th>
			<td>传递值</td>
		</tr>
	</table>
</form>
		
</pre>
</div>
</body>
</html>