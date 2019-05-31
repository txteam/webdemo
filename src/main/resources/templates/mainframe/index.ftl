<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>web demo |cqtianxin console</title>

    <link href="${base}/inspinia/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${base}/inspinia/css/animate.css" rel="stylesheet">
    <link href="${base}/inspinia/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
    <link href="${base}/inspinia/css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">
    <link href="${base}/inspinia/css/style.css" rel="stylesheet">
    <link href="${base}/contabs/style.css" rel="stylesheet">


    <style>


    </style>
</head>

<body>


<div id="wrapper">

    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <img alt="image" class="rounded-circle" src="${base}/inspinia/img/profile_small.jpg"/>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="block m-t-xs font-bold">重庆添馨</span>
                            <span class="text-muted text-xs block">管理员 <b class="caret"></b></span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="dropdown-item" href="profile.html">个人信息</a></li>
                            <li><a class="dropdown-item" href="contacts.html">好友</a></li>
                            <li><a class="dropdown-item" href="mailbox.html">信箱</a></li>
                            <li class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="login.html">登出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">
                        TX
                    </div>
                </li>

                <#list menuList as topMenu>
                    <#if topMenu.href?if_exists>
                        <li>
                        <a href="${base}/${topMenu.href}" target="_blank"><i class="fa fa-laptop"></i> <span
                                class="nav-label">${topMenu.text}</span></a>
                        </li>
                    <#else>
                        <li>
                        <a href="#"><i class="fa fa-th-large"></i>
                        <span class="nav-label">${topMenu.text} </span> <span
                            class="fa arrow"></span></a>
                        <#assign link = topMenu.attributes["link"]?upper_case />
                        <ul class="nav nav-second-level collapse">
                        <#list menuNodeMap["${link}"] as menuNode>
                            <#if menuNode.descendants?size gt 0>
                                <li>
                                <a href="#">${menuNode.text} <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                <#list menuNode.descendants as threeMenu>
                                    <li><a href="${base}${threeMenu.href}" class="J_menuItem" >${threeMenu.text}</a></li>
                                </#list>
                                </ul>
                                </li>
                            <#else >
                                <li><a href="${base}${menuNode.href}" class="J_menuItem" >${menuNode.text}</a></li>
                            </#if>

                        </#list>
                        </ul>
                        </li>

                    </#if>

                </#list>


            </ul>

        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg">

        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i>
                    </a>
                    <#--<form role="search" class="navbar-form-custom" method="post" action="search_results.html">-->
                    <#--<div class="form-group">-->
                    <#--<input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search"-->
                    <#--id="top-search">-->
                    <#--</div>-->
                    <#--</form>-->
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> <i
                                    class="fa fa-bell"></i> <span class="label label-primary">8</span> </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="mailbox.html" class="J_menuItem" data-menu-name="未读消息">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> 您有16条未读消息
                                        <span class="pull-right text-muted small">4分钟前</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="profile.html" class="J_menuItem" data-menu-name="新回复">
                                    <div><i class="fa fa-qq fa-fw"></i> 3条新回复 <span class="pull-right text-muted small">12分钟钱</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem" href="notifications.html" data-index="89">
                                        <strong>查看所有 </strong> <i class="fa fa-angle-right"></i> </a>
                                </div>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="login.html">
                            <i class="fa fa-sign-out"></i> 注销
                        </a>
                    </li>
                    <li class="dropdown hidden-xs">
                        <a class="right-sidebar-toggle" aria-expanded="false"> <i class="fa fa-tasks"></i> 主题 </a>
                    </li>

                    <li class="dropdown hidden-xs">
                        <a href="http://cn.inspinia.cn/index.html" target="_blank"> <i class="fa fa-tasks"></i> inspinia
                            官网 </a>
                    </li>

                </ul>
            </nav>
        </div>

        <div id="right-sidebar" class="animated ">
            <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
                <div class="sidebar-container" style="overflow: hidden; width: auto; height: 100%;">
                    <ul class="nav nav-tabs navs-3">
                        <li class="active">
                            <a data-toggle="tab" href="#tab-1" aria-expanded="true">
                                记录
                            </a>
                        </li>
                        <li>
                            <a data-toggle="tab" href="#tab-2">
                                项目
                            </a>
                        </li>
                        <li class="">
                            <a data-toggle="tab" href="#tab-3" aria-expanded="false">
                                <i class="fa fa-gear"></i>
                            </a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="sidebar-title">
                                <h3>
                                    <i class="fa fa-comments-o"></i> 最新记录</h3>
                                <small>
                                    <i class="fa fa-tim"></i> 你有10条新消息。
                                </small>
                            </div>
                            <div>
                                <div class="sidebar-message">
                                    <a href="#">
                                        <div class="pull-left text-center">
                                            <img alt="image" class="img-circle message-avatar"
                                                 src="http://cn.inspinia.cn/html/inspiniaen/img/a1.jpg">
                                            <div class="m-t-xs">
                                                <i class="fa fa-star text-warning"></i>
                                                <i class="fa fa-star text-warning"></i>
                                            </div>
                                        </div>
                                        <div class="media-body">
                                            人生就像钟表，可以回到起点，却已不是昨天！
                                            <br>
                                            <small class="text-muted">今天 4:21</small>
                                        </div>
                                    </a>
                                </div>
                                <div class="sidebar-message">
                                    <a href="#">
                                        <div class="pull-left text-center">
                                            <img alt="image" class="img-circle message-avatar"
                                                 src="http://cn.inspinia.cn/html/inspiniaen/img/a2.jpg">
                                        </div>
                                        <div class="media-body">
                                            人生就像一本书，出生是封面，归去是封底，内容要靠自己填。
                                            <br>
                                            <small class="text-muted">昨天 2:45</small>
                                        </div>
                                    </a>
                                </div>
                                <div class="sidebar-message">
                                    <a href="#">
                                        <div class="pull-left text-center">
                                            <img alt="image" class="img-circle message-avatar"
                                                 src="http://cn.inspinia.cn/html/inspiniaen/img/a3.jpg">
                                            <div class="m-t-xs">
                                                <i class="fa fa-star text-warning"></i>
                                                <i class="fa fa-star text-warning"></i>
                                                <i class="fa fa-star text-warning"></i>
                                            </div>
                                        </div>
                                        <div class="media-body">
                                            人生就像是一盘沙子，你永远也无法全盘抓起，但也不会一无所获。
                                            <br>
                                            <small class="text-muted">昨天 1:10</small>
                                        </div>
                                    </a>
                                </div>
                                <div class="sidebar-message">
                                    <a href="#">
                                        <div class="pull-left text-center">
                                            <img alt="image" class="img-circle message-avatar"
                                                 src="http://cn.inspinia.cn/html/inspiniaen/img/a4.jpg">
                                        </div>
                                        <div class="media-body">
                                            人生就像候车月台，有人上车，也有人下车，来来往往，你谁也抓不住！
                                            <br>
                                            <small class="text-muted">星期一 8:37</small>
                                        </div>
                                    </a>
                                </div>
                                <div class="sidebar-message">
                                    <a href="#">
                                        <div class="pull-left text-center">
                                            <img alt="image" class="img-circle message-avatar"
                                                 src="http://cn.inspinia.cn/html/inspiniaen/img/a8.jpg">
                                        </div>
                                        <div class="media-body">
                                            人生就像卫生纸，没事的时候尽量少扯！
                                            <br>
                                            <small class="text-muted">今天 4:21</small>
                                        </div>
                                    </a>
                                </div>
                                <div class="sidebar-message">
                                    <a href="#">
                                        <div class="pull-left text-center">
                                            <img alt="image" class="img-circle message-avatar"
                                                 src="http://cn.inspinia.cn/html/inspiniaen/img/a7.jpg">
                                        </div>
                                        <div class="media-body">
                                            人生就像一只钟摆，永远在渴望的痛苦和满足的厌倦之间摆动！
                                            <br>
                                            <small class="text-muted">昨天 2:45</small>
                                        </div>
                                    </a>
                                </div>
                                <div class="sidebar-message">
                                    <a href="#">
                                        <div class="pull-left text-center">
                                            <img alt="image" class="img-circle message-avatar"
                                                 src="http://cn.inspinia.cn/html/inspiniaen/img/a3.jpg">
                                            <div class="m-t-xs">
                                                <i class="fa fa-star text-warning"></i>
                                                <i class="fa fa-star text-warning"></i>
                                                <i class="fa fa-star text-warning"></i>
                                            </div>
                                        </div>
                                        <div class="media-body">
                                            人生就像是一片海洋，里面的许多的奥秘要让我们去探究。
                                            <br>
                                            <small class="text-muted">昨天 1:10</small>
                                        </div>
                                    </a>
                                </div>
                                <div class="sidebar-message">
                                    <a href="#">
                                        <div class="pull-left text-center">
                                            <img alt="image" class="img-circle message-avatar"
                                                 src="http://cn.inspinia.cn/html/inspiniaen/img/a4.jpg">
                                        </div>
                                        <div class="media-body">
                                            人生就像一个空荡荡的巨大的坑，我们把时间丢进去，丢完了就完了！
                                            <br>
                                            <small class="text-muted">星期一 8:37</small>
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div id="tab-2" class="tab-pane">
                            <div class="sidebar-title">
                                <h3>
                                    <i class="fa fa-cube"></i> 最新项目</h3>
                                <small>
                                    <i class="fa fa-tim"></i> 你有14个项目。 10没有完成
                                </small>
                            </div>
                            <ul class="sidebar-list">
                                <li>
                                    <a href="#">
                                        <div class="small pull-right m-t-xs">
                                            9小时前
                                        </div>
                                        <h4>新时尚</h4>
                                        人生如一杯茶，不会苦一辈子，但要苦一阵子。
                                        <div class="small">
                                            完成度: 22%
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 22%;" class="progress-bar progress-bar-warning">
                                            </div>
                                        </div>
                                        <div class="small text-muted m-t-xs">
                                            时间: 2017.12.12 4:00
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="small pull-right m-t-xs">
                                            9小时前
                                        </div>
                                        <h4>新游戏 </h4>
                                        人生如一杯茶，不会苦一辈子，但要苦一阵子。
                                        <div class="small">
                                            完成度: 48%
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 48%;" class="progress-bar">
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="small pull-right m-t-xs">
                                            9小时前
                                        </div>
                                        <h4>新房产</h4>
                                        人生如一杯茶，不会苦一辈子，但要苦一阵子。
                                        <div class="small">
                                            完成度: 14%
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 14%;" class="progress-bar progress-bar-info">
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <span class="label label-primary pull-right">NEW</span>
                                        <h4>新世界</h4>
                                        人生如一杯茶，不会苦一辈子，但要苦一阵子。
                                        <div class="small">
                                            完成度: 22%
                                        </div>
                                        <div class="small text-muted m-t-xs">
                                            时间: 2017.12.12 4:00
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="small pull-right m-t-xs">
                                            9小时前
                                        </div>
                                        <h4>新密码</h4>
                                        人生如一杯茶，不会苦一辈子，但要苦一阵子。
                                        <div class="small">
                                            完成度: 22%
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 22%;" class="progress-bar progress-bar-warning">
                                            </div>
                                        </div>
                                        <div class="small text-muted m-t-xs">
                                            时间: 2017.12.12 4:00
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="small pull-right m-t-xs">
                                            9小时前
                                        </div>
                                        <h4>新数据 </h4>
                                        人生如一杯茶，不会苦一辈子，但要苦一阵子。
                                        <div class="small">
                                            完成度: 48%
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 48%;" class="progress-bar">
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <div class="small pull-right m-t-xs">
                                            9小时前
                                        </div>
                                        <h4>新会议</h4>
                                        人生如一杯茶，不会苦一辈子，但要苦一阵子。
                                        <div class="small">
                                            完成度: 14%
                                        </div>
                                        <div class="progress progress-mini">
                                            <div style="width: 14%;" class="progress-bar progress-bar-info">
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <span class="label label-primary pull-right">NEW</span>
                                        <h4>新业务</h4>
                                        <!--<div class="small pull-right m-t-xs">9小时前
                                    </div>
                                    -->
                                        人生如一杯茶，不会苦一辈子，但要苦一阵子。
                                        <div class="small">
                                            完成度: 22%
                                        </div>
                                        <div class="small text-muted m-t-xs">
                                            时间: 2017.12.12 4:00
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div id="tab-3" class="tab-pane">
                            <div class="sidebar-title">
                                <h3>
                                    <i class="fa fa-gears"></i> 设置</h3>
                                <small>
                                    <i class="fa fa-tim"></i> 你有13个项目，10个没有完成
                                </small>
                            </div>
                            <div class="setings-item">
					<span>
                                显示通知
					</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox"
                                               id="example">
                                        <label class="onoffswitch-label" for="example">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
					<span>
                                即时聊天
					</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" checked=""
                                               class="onoffswitch-checkbox" id="example2">
                                        <label class="onoffswitch-label" for="example2">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
					<span>
                                历史记录
					</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox"
                                               id="example3">
                                        <label class="onoffswitch-label" for="example3">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
					<span>
                                显示图表
					</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox"
                                               id="example4">
                                        <label class="onoffswitch-label" for="example4">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
					<span>
                                离线用户
					</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" checked="" name="collapsemenu"
                                               class="onoffswitch-checkbox" id="example5">
                                        <label class="onoffswitch-label" for="example5">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
					<span>
                                网站搜索
					</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" checked="" name="collapsemenu"
                                               class="onoffswitch-checkbox" id="example6">
                                        <label class="onoffswitch-label" for="example6">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
					<span>
                                每天更新
					</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox"
                                               id="example7">
                                        <label class="onoffswitch-label" for="example7">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="sidebar-content">
                                <h4>设置</h4>
                                <div class="small">
                                    设置描述设置描述设置描述设置描述设置描述设置描述设置描述设置描述设置描述设置描述设置描述设置描述设置描述
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="slimScrollBar"
                     style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 61px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 938px;"></div>
                <div class="slimScrollRail"
                     style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.4; z-index: 90; right: 1px;"></div>
            </div>
        </div>
        <div class="theme-config">
            <div class="theme-config-box">
                <div class="spin-icon">
                    <i class="fa fa-cogs fa-spin"></i>
                </div>
                <div class="skin-settings">
                    <div class="title">
                        配置
                        <br>
                        <small style="text-transform: none;font-weight: 400">
                            配置框设计用户演示目的，所有选项课通过代码获得。
                        </small>
                    </div>
                    <div class="setings-item">
			<span>
                        折叠菜单
			</span>
                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox"
                                       id="collapsemenu">
                                <label class="onoffswitch-label" for="collapsemenu">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
			<span>
                        固定侧边栏
			</span>
                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="fixedsidebar" class="onoffswitch-checkbox"
                                       id="fixedsidebar">
                                <label class="onoffswitch-label" for="fixedsidebar">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
			<span>
                        顶级导航栏
			</span>
                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                <label class="onoffswitch-label" for="fixednavbar">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
			<span>
                        顶部导航栏V.2
			<br>
			<small>*主要布局</small>
			</span>
                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="fixednavbar2" class="onoffswitch-checkbox"
                                       id="fixednavbar2">
                                <label class="onoffswitch-label" for="fixednavbar2">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
			<span>
                        盒子布局
			</span>
                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                <label class="onoffswitch-label" for="boxedlayout">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="setings-item">
			<span>
                        固定页脚
			</span>
                        <div class="switch">
                            <div class="onoffswitch">
                                <input type="checkbox" name="fixedfooter" class="onoffswitch-checkbox" id="fixedfooter">
                                <label class="onoffswitch-label" for="fixedfooter">
                                    <span class="onoffswitch-inner"></span>
                                    <span class="onoffswitch-switch"></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="title">
                        换肤
                    </div>
                    <div class="setings-item default-skin">
			<span class="skin-name ">
			<a href="#" class="s-skin-0">
                            默认
			</a>
			</span>
                    </div>
                    <div class="setings-item blue-skin">
			<span class="skin-name ">
			<a href="#" class="s-skin-1">
                            蓝色
			</a>
			</span>
                    </div>
                    <div class="setings-item yellow-skin">
			<span class="skin-name ">
			<a href="#" class="s-skin-3">
                            黄色
			</a>
			</span>
                    </div>
                    <div class="setings-item ultra-skin">
			<span class="skin-name ">
			<a href="#" class="md-skin">
                            绿色（没用）
			</a>
			</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- iframe -->
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span></button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive">
                        <a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll">
                        <a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther">
                        <a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="login.html" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>

        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="index_v1.html?v=4.1.0"
                    frameborder="0" data-id="index_v1.html" seamless></iframe>
        </div>


        <div class="footer">
            <div class="float-right">
                <strong>web-demo version1.0</strong>
            </div>
            <div>
                <strong>Copyright</strong> web-demo version1.0 &copy; 2014-2018
            </div>
        </div>


    </div>


    <!-- Mainly scripts -->
    <script src="${base}/inspinia/js/jquery-3.1.1.min.js"></script>
    <script src="${base}/inspinia/js/popper.min.js"></script>
    <script src="${base}/inspinia/js/bootstrap.js"></script>
    <script src="${base}/inspinia/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${base}/inspinia/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Peity -->
    <script src="${base}/inspinia/js/plugins/peity/jquery.peity.min.js"></script>

    <#--<!-- jqGrid &ndash;&gt;-->
    <#--<script src="${base}/inspinia/js/plugins/jqGrid/i18n/grid.locale-en.js"></script>-->
    <#--<script src="${base}/inspinia/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>-->

    <!-- Custom and plugin javascript -->
    <script src="${base}/inspinia/js/inspinia.js"></script>
    <script src="${base}/inspinia/js/plugins/pace/pace.min.js"></script>

    <script src="${base}/inspinia/js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <script type="text/javascript" src="${base}/contabs/contabs.js"></script>


    <script>

        // Config box
        // Enable/disable fixed top navbar
        $('#fixednavbar').click(function () {
            if ($('#fixednavbar').is(':checked')) {
                $(".navbar-static-top").removeClass('navbar-static-top').addClass('navbar-fixed-top');
                $("body").removeClass('boxed-layout');
                $("body").addClass('fixed-nav');
                $('#boxedlayout').prop('checked', false);
                if (localStorageSupport) {
                    localStorage.setItem("boxedlayout", 'off');
                }
                if (localStorageSupport) {
                    localStorage.setItem("fixednavbar", 'on');
                }
            } else {
                $(".navbar-fixed-top").removeClass('navbar-fixed-top').addClass('navbar-static-top');
                $("body").removeClass('fixed-nav');
                $("body").removeClass('fixed-nav-basic');
                $('#fixednavbar2').prop('checked', false);
                if (localStorageSupport) {
                    localStorage.setItem("fixednavbar", 'off');
                }
                if (localStorageSupport) {
                    localStorage.setItem("fixednavbar2", 'off');
                }
            }
        });
        // Enable/disable fixed top navbar
        $('#fixednavbar2').click(function () {
            if ($('#fixednavbar2').is(':checked')) {
                $(".navbar-static-top").removeClass('navbar-static-top').addClass('navbar-fixed-top');
                $("body").removeClass('boxed-layout');
                $("body").addClass('fixed-nav').addClass('fixed-nav-basic');
                $('#boxedlayout').prop('checked', false);
                if (localStorageSupport) {
                    localStorage.setItem("boxedlayout", 'off');
                }
                if (localStorageSupport) {
                    localStorage.setItem("fixednavbar2", 'on');
                }
            } else {
                $(".navbar-fixed-top").removeClass('navbar-fixed-top').addClass('navbar-static-top');
                $("body").removeClass('fixed-nav').removeClass('fixed-nav-basic');
                $('#fixednavbar').prop('checked', false);
                if (localStorageSupport) {
                    localStorage.setItem("fixednavbar2", 'off');
                }
                if (localStorageSupport) {
                    localStorage.setItem("fixednavbar", 'off');
                }
            }
        });
        // Enable/disable fixed sidebar
        $('#fixedsidebar').click(function () {
            if ($('#fixedsidebar').is(':checked')) {
                $("body").addClass('fixed-sidebar');
                $('.sidebar-collapse').slimScroll({
                    height: '100%',
                    railOpacity: 0.9
                });
                if (localStorageSupport) {
                    localStorage.setItem("fixedsidebar", 'on');
                }
            } else {
                $('.sidebar-collapse').slimscroll({
                    destroy: true
                });
                $('.sidebar-collapse').attr('style', '');
                $("body").removeClass('fixed-sidebar');
                if (localStorageSupport) {
                    localStorage.setItem("fixedsidebar", 'off');
                }
            }
        });
        // Enable/disable collapse menu
        $('#collapsemenu').click(function () {
            if ($('#collapsemenu').is(':checked')) {
                $("body").addClass('mini-navbar');
                SmoothlyMenu();
                if (localStorageSupport) {
                    localStorage.setItem("collapse_menu", 'on');
                }
            } else {
                $("body").removeClass('mini-navbar');
                SmoothlyMenu();
                if (localStorageSupport) {
                    localStorage.setItem("collapse_menu", 'off');
                }
            }
        });
        // Enable/disable boxed layout
        $('#boxedlayout').click(function () {
            if ($('#boxedlayout').is(':checked')) {
                $("body").addClass('boxed-layout');
                $('#fixednavbar').prop('checked', false);
                $('#fixednavbar2').prop('checked', false);
                $(".navbar-fixed-top").removeClass('navbar-fixed-top').addClass('navbar-static-top');
                $("body").removeClass('fixed-nav');
                $("body").removeClass('fixed-nav-basic');
                $(".footer").removeClass('fixed');
                $('#fixedfooter').prop('checked', false);
                if (localStorageSupport) {
                    localStorage.setItem("fixednavbar", 'off');
                }
                if (localStorageSupport) {
                    localStorage.setItem("fixednavbar2", 'off');
                }
                if (localStorageSupport) {
                    localStorage.setItem("fixedfooter", 'off');
                }
                if (localStorageSupport) {
                    localStorage.setItem("boxedlayout", 'on');
                }
            } else {
                $("body").removeClass('boxed-layout');
                if (localStorageSupport) {
                    localStorage.setItem("boxedlayout", 'off');
                }
            }
        });
        // Enable/disable fixed footer
        $('#fixedfooter').click(function () {
            if ($('#fixedfooter').is(':checked')) {
                $('#boxedlayout').prop('checked', false);
                $("body").removeClass('boxed-layout');
                $(".footer").addClass('fixed');
                if (localStorageSupport) {
                    localStorage.setItem("boxedlayout", 'off');
                }
                if (localStorageSupport) {
                    localStorage.setItem("fixedfooter", 'on');
                }
            } else {
                $(".footer").removeClass('fixed');
                if (localStorageSupport) {
                    localStorage.setItem("fixedfooter", 'off');
                }
            }
        });
        // SKIN Select
        $('.spin-icon').click(function () {
            $(".theme-config-box").toggleClass("show");
        });
        // Default skin
        $('.s-skin-0').click(function () {
            $("body").removeClass("skin-1");
            $("body").removeClass("skin-2");
            $("body").removeClass("skin-3");
        });
        // Blue skin
        $('.s-skin-1').click(function () {
            $("body").removeClass("skin-2");
            $("body").removeClass("skin-3");
            $("body").addClass("skin-1");
        });
        // Inspinia ultra skin
        $('.s-skin-2').click(function () {
            $("body").removeClass("skin-1");
            $("body").removeClass("skin-3");
            $("body").addClass("skin-2");
        });
        // Yellow skin
        $('.s-skin-3').click(function () {
            $("body").removeClass("skin-1");
            $("body").removeClass("skin-2");
            $("body").addClass("skin-3");
        });
        if (localStorageSupport) {
            var collapse = localStorage.getItem("collapse_menu");
            var fixedsidebar = localStorage.getItem("fixedsidebar");
            var fixednavbar = localStorage.getItem("fixednavbar");
            var fixednavbar2 = localStorage.getItem("fixednavbar2");
            var boxedlayout = localStorage.getItem("boxedlayout");
            var fixedfooter = localStorage.getItem("fixedfooter");
            if (collapse == 'on') {
                $('#collapsemenu').prop('checked', 'checked')
            }
            if (fixedsidebar == 'on') {
                $('#fixedsidebar').prop('checked', 'checked')
            }
            if (fixednavbar == 'on') {
                $('#fixednavbar').prop('checked', 'checked')
            }
            if (fixednavbar2 == 'on') {
                $('#fixednavbar2').prop('checked', 'checked')
            }
            if (boxedlayout == 'on') {
                $('#boxedlayout').prop('checked', 'checked')
            }
            if (fixedfooter == 'on') {
                $('#fixedfooter').prop('checked', 'checked')
            }
        }

    </script>


</body>

</html>
