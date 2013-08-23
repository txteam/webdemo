<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>业务系统demo</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${contextPath}/js/bootstrap-2.3.1/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link id="easyuiTheme" rel="stylesheet" href="${contextPath}/js/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" type="text/css">
<link id="easyuiTheme" rel="stylesheet" href="${contextPath}/js/jquery-easyui-1.3.3/themes/icon.css" type="text/css">
<link rel="stylesheet" href="${contextPath}/js/jquery-easyui-portal/portal.css" type="text/css">

<script src="${contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-easyui-1.3.3/plugins/jquery.layout.js" charset="utf-8"></script>
<script type="text/javascript" src="${contextPath}/js/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
<script type="text/javascript">
	var index_tabs;
	var index_tabsMenu;
	var index_layout;
	$(function() {
		index_layout = $('#index_layout').layout({
			fit : true
		});
		/*index_layout.layout('collapse', 'east');*/

		index_tabs = $('#index_tabs').tabs({
			fit : true,
			border : false,
			onContextMenu : function(e, title) {
				e.preventDefault();
				index_tabsMenu.menu('show', {
					left : e.pageX,
					top : e.pageY
				}).data('tabTitle', title);
			},
			tools : [ {
				iconCls : 'database_refresh',
				handler : function() {
					var href = index_tabs.tabs('getSelected').panel('options').href;
					if (href) {/*说明tab是以href方式引入的目标页面*/
						var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
						index_tabs.tabs('getTab', index).panel('refresh');
					} else {/*说明tab是以content方式引入的目标页面*/
						var panel = index_tabs.tabs('getSelected').panel('panel');
						var frame = panel.find('iframe');
						try {
							if (frame.length > 0) {
								for ( var i = 0; i < frame.length; i++) {
									frame[i].contentWindow.document.write('');
									frame[i].contentWindow.close();
									frame[i].src = frame[i].src;
								}
								if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
									try {
										CollectGarbage();
									} catch (e) {
									}
								}
							}
						} catch (e) {
						}
					}
				}
			}, {
				iconCls : 'delete',
				handler : function() {
					var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
					var tab = index_tabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						index_tabs.tabs('close', index);
					} else {
						$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
					}
				}
			} ]
		});

		index_tabsMenu = $('#index_tabsMenu').menu({
			onClick : function(item) {
				var curTabTitle = $(this).data('tabTitle');
				var type = $(item.target).attr('title');

				if (type === 'refresh') {
					index_tabs.tabs('getTab', curTabTitle).panel('refresh');
					return;
				}

				if (type === 'close') {
					var t = index_tabs.tabs('getTab', curTabTitle);
					if (t.panel('options').closable) {
						index_tabs.tabs('close', curTabTitle);
					}
					return;
				}

				var allTabs = index_tabs.tabs('tabs');
				var closeTabsTitle = [];

				$.each(allTabs, function() {
					var opt = $(this).panel('options');
					if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
						closeTabsTitle.push(opt.title);
					} else if (opt.closable && type === 'closeAll') {
						closeTabsTitle.push(opt.title);
					}
				});

				for ( var i = 0; i < closeTabsTitle.length; i++) {
					index_tabs.tabs('close', closeTabsTitle[i]);
				}
			}
		});
	});
</script>
</head>
<body>
	<div id="index_layout">
		<div data-options="region:'north'" style="height: 70px; overflow: hidden;" class="logo">
			<div id="sessionInfoDiv" style="position: absolute; right: 0px; top: 0px;" class="alert alert-info">
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
				<div onclick="changeThemeFun('cupertino');" title="cupertino">cupertino</div>
				<div onclick="changeThemeFun('dark-hive');" title="dark-hive">dark-hive</div>
				<div onclick="changeThemeFun('pepper-grinder');" title="pepper-grinder">pepper-grinder</div>
				<div onclick="changeThemeFun('sunny');" title="sunny">sunny</div>
				<div class="menu-sep"></div>
				<div onclick="changeThemeFun('metro-blue');" title="metro-blue">metro-blue</div>
				<div onclick="changeThemeFun('metro-gray');" title="metro-gray">metro-gray</div>
				<div onclick="changeThemeFun('metro-green');" title="metro-green">metro-green</div>
				<div onclick="changeThemeFun('metro-orange');" title="metro-orange">metro-orange</div>
				<div onclick="changeThemeFun('metro-red');" title="metro-red">metro-red</div>
			</div>
			<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
				<div onclick="editCurrentUserPwd();">修改密码</div>
				<div class="menu-sep"></div>
				<div onclick="currentUserRole();">我的角色</div>
				<div class="menu-sep"></div>
				<div onclick="currentUserResource();">我的权限</div>
			</div>
			<div id="layout_north_zxMenu" style="width: 100px; display: none;">
				<div onclick="logoutFun();">锁定窗口</div>
				<div class="menu-sep"></div>
				<div onclick="logoutFun();">重新登录</div>
				<div onclick="logoutFun(true);">退出系统</div>
			</div>
		</div>
		<div data-options="region:'west',split:true" title="模块导航" style="width: 200px; overflow: hidden;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
			<div title="系统菜单" style="padding: 5px;" data-options="border:false,isonCls:'anchor',tools : [ {
						iconCls : 'database_refresh',
						handler : function() {
							$('#layout_west_tree').tree('reload');
						}
					}, {
						iconCls : 'resultset_next',
						handler : function() {
							var node = $('#layout_west_tree').tree('getSelected');
							if (node) {
								$('#layout_west_tree').tree('expandAll', node.target);
							} else {
								$('#layout_west_tree').tree('expandAll');
							}
						}
					}, {
						iconCls : 'resultset_previous',
						handler : function() {
							var node = $('#layout_west_tree').tree('getSelected');
							if (node) {
								$('#layout_west_tree').tree('collapseAll', node.target);
							} else {
								$('#layout_west_tree').tree('collapseAll');
							}
						}
					} ]">
				<div class="well well-small">
					<ul id="layout_west_tree"></ul>
				</div>
			</div>
			<div title="其他示例" data-options="border:false,iconCls:'anchor'">
				<ul>
					<li>菜单</li>
					<li>菜单</li>
					<li>菜单</li>
				</ul>
			</div>
		</div>
		</div>
		<div data-options="region:'center',border:false" title="欢迎使用!改行可去掉" style="overflow: hidden;">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="首页" data-options="border:false" style="overflow: hidden;">
					<iframe src="../portal/index.htm" style="border: 0; width: 100%; height: 98%;"></iframe>
				</div>
			</div>
		</div>
		
	</div>

	<div id="index_tabsMenu" style="width: 120px; display: none;">
		<div title="refresh" data-options="iconCls:'icon-reload'">刷新</div>
		<div class="menu-sep"></div>
		<div title="close" data-options="iconCls:'icon-cancel'">关闭</div>
		<div title="closeOther" data-options="iconCls:'icon-cancel'">关闭其他</div>
		<div title="closeAll" data-options="iconCls:'icon-cancel'">关闭所有</div>
	</div>

</body>
</html>