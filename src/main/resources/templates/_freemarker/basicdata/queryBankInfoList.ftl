<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>inspinia 2.9.2 | jqGrid</title>

    <link href="${base}/inspinia/css/bootstrap.min.css" rel="stylesheet">
    <link href="${base}/inspinia/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="${base}/inspinia/css/animate.css" rel="stylesheet">
    <link href="${base}/inspinia/css/plugins/jQueryUI/jquery-ui-1.10.4.custom.min.css" rel="stylesheet">
    <link href="${base}/inspinia/css/plugins/jqGrid/ui.jqgrid.css" rel="stylesheet">
    <link href="${base}/inspinia/css/style.css" rel="stylesheet">
    <link href="${base}/contabs/style.css" rel="stylesheet">




    <style>
        /* Additional style to fix warning dialog position */
        #alertmod_table_list_2 {
            top: 900px !important;
        }

        .roll-right.J_tabRight {
            right: 140px;
        }

        .roll-right.btn-group {
            right: 60px;
            width: 80px;
            padding: 0;
        }

        .J_menuTab.active{
            background-color:#1ab394;
            color:#fff;

        }
        .J_menuTab.active:hover{
            background-color:#18a689;
            color:#fff;

        }

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
                            <span class="block m-t-xs font-bold">王宇</span>
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
                        IN+
                    </div>
                </li>
                <li>
                    <a href="index.html"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboards</span> <span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="index.html">主页示例 v.1</a></li>
                        <li><a href="dashboard_2.html">主页示例 v.2</a></li>
                        <li><a href="dashboard_3.html">主页示例 v.3</a></li>
                        <li><a href="dashboard_4_1.html">主页示例 v.4</a></li>
                        <li><a href="dashboard_5.html">主页示例 v.5 </a></li>
                    </ul>
                </li>
                <li>
                    <a href="layouts.html"><i class="fa fa-diamond"></i> <span class="nav-label">Layouts</span></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">图表</span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="graph_flot.html">Flot Charts</a></li>
                        <li><a href="graph_morris.html">Morris.js Charts</a></li>
                        <li><a href="graph_rickshaw.html">Rickshaw Charts</a></li>
                        <li><a href="graph_chartjs.html">Chart.js</a></li>
                        <li><a href="graph_chartist.html">Chartist</a></li>
                        <li><a href="c3.html">c3 charts</a></li>
                        <li><a href="graph_peity.html">Peity Charts</a></li>
                        <li><a href="graph_sparkline.html">Sparkline Charts</a></li>
                    </ul>
                </li>
                <li>
                    <a href="mailbox.html"><i class="fa fa-envelope"></i> <span class="nav-label">邮件 </span><span
                                class="label label-warning float-right">16/24</span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="mailbox.html">收件箱</a></li>
                        <li><a href="mail_detail.html">邮件内容</a></li>
                        <li><a href="mail_compose.html">撰写邮件</a></li>
                        <li><a href="email_template.html">邮件模板</a></li>
                    </ul>
                </li>
                <li>
                    <a href="metrics.html"><i class="fa fa-pie-chart"></i> <span class="nav-label">指标</span> </a>
                </li>
                <li>
                    <a href="widgets.html"><i class="fa fa-flask"></i> <span class="nav-label">窗口小部件</span></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">表单</span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="form_basic.html">基本表单</a></li>
                        <li><a href="form_advanced.html">高级表单</a></li>
                        <li><a href="form_wizard.html">表单向导</a></li>
                        <li><a href="form_file_upload.html">文件上传</a></li>
                        <li><a href="form_editors.html">编辑器</a></li>
                        <li><a href="form_autocomplete.html">搜索自动补全</a></li>
                        <li><a href="form_markdown.html">Markdown编辑器</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">应用</span> <span
                                class="float-right label label-primary">特别</span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="contacts.html">名片</a></li>
                        <li><a href="profile.html">会员</a></li>
                        <li><a href="profile_2.html">会员 v.2</a></li>
                        <li><a href="contacts_2.html">联系人 v.2</a></li>
                        <li><a href="projects.html">项目</a></li>
                        <li><a href="project_detail.html">项目详情</a></li>
                        <li><a href="activity_stream.html">活动</a></li>
                        <li><a href="teams_board.html">团队</a></li>
                        <li><a href="social_feed.html">社交</a></li>
                        <li><a href="clients.html">客户端</a></li>
                        <li><a href="full_height.html">Outlook 视图</a></li>
                        <li><a href="vote_list.html">投票</a></li>
                        <li><a href="file_manager.html">文件管理器</a></li>
                        <li><a href="calendar.html">日历</a></li>
                        <li><a href="issue_tracker.html">问题</a></li>
                        <li><a href="blog.html">博客</a></li>
                        <li><a href="article.html">文章</a></li>
                        <li><a href="faq.html">帮助</a></li>
                        <li><a href="timeline.html">时间线</a></li>
                        <li><a href="pin_board.html">便签</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-files-o"></i> <span class="nav-label">页面</span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="search_results.html">搜索</a></li>
                        <li><a href="lockscreen.html">锁屏</a></li>
                        <li><a href="invoice.html">发票</a></li>
                        <li><a href="login.html">登录</a></li>
                        <li><a href="login_two_columns.html">登录 v.2</a></li>
                        <li><a href="forgot_password.html">忘记密码</a></li>
                        <li><a href="register.html">注册</a></li>
                        <li><a href="404.html">404 页面</a></li>
                        <li><a href="500.html">500 页面</a></li>
                        <li><a href="empty_page.html">空白页</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-globe"></i> <span class="nav-label">组件</span><span
                                class="label label-info float-right">新</span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="toastr_notifications.html">toastr 通知</a></li>
                        <li><a href="nestable_list.html">拖动排序</a></li>
                        <li><a href="agile_board.html">Agile board</a></li>
                        <li><a href="timeline_2.html">时间轴 v.2</a></li>
                        <li><a href="diff.html">Diff</a></li>
                        <li><a href="pdf_viewer.html">PDF 查看器</a></li>
                        <li><a href="i18support.html">i18 support</a></li>
                        <li><a href="sweetalert.html">弹层组件</a></li>
                        <li><a href="idle_timer.html">空闲计时器</a></li>
                        <li><a href="truncate.html">截断字符串</a></li>
                        <li><a href="password_meter.html">密码</a></li>
                        <li><a href="spinners.html">加载动画</a></li>
                        <li><a href="spinners_usage.html">Spinners usage</a></li>
                        <li><a href="tinycon.html">Live favicon</a></li>
                        <li><a href="google_maps.html">谷歌地图</a></li>
                        <li><a href="datamaps.html">数据地图</a></li>
                        <li><a href="social_buttons.html">社交按钮</a></li>
                        <li><a href="code_editor.html">代码编辑器</a></li>
                        <li><a href="modal_window.html">模态窗口</a></li>
                        <li><a href="clipboard.html">文本剪贴板</a></li>
                        <li><a href="text_spinners.html">Text spinners</a></li>
                        <li><a href="forum_main.html">论坛</a></li>
                        <li><a href="validation.html">验证</a></li>
                        <li><a href="tree_view.html">树形菜单</a></li>
                        <li><a href="loading_buttons.html">加载按钮</a></li>
                        <li><a href="chat_view.html">聊天视窗</a></li>
                        <li><a href="masonry.html">瀑布流布局</a></li>
                        <li><a href="tour.html">新手指引</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-flask"></i> <span class="nav-label">UI 元素</span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="typography.html">排版</a></li>
                        <li><a href="icons.html">图标</a></li>
                        <li><a href="draggable_panels.html">拖动排序</a></li>
                        <li><a href="resizeable_panels.html">内容缩放</a></li>
                        <li><a href="buttons.html">按钮</a></li>
                        <li><a href="video.html">视频</a></li>
                        <li><a href="tabs_panels.html">面板</a></li>
                        <li><a href="tabs.html">标签</a></li>
                        <li><a href="notifications.html">通知 & 工具</a></li>
                        <li><a href="helper_classes.html">css 辅助</a></li>
                        <li><a href="badges_labels.html">徽章 & 标签 & 进度条</a></li>
                    </ul>
                </li>

                <li>
                    <a href="grid_options.html"><i class="fa fa-laptop"></i> <span class="nav-label">栅格</span></a>
                </li>
                <li class="active">
                    <a href="#"><i class="fa fa-table"></i> <span class="nav-label">表格</span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="table_basic.html">静态表格</a></li>
                        <li><a href="table_data_tables.html">数据表格</a></li>
                        <li><a href="table_foo_table.html">Foo 表格</a></li>
                        <li class="active"><a href="jq_grid.html">jqGrid</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-shopping-cart"></i> <span class="nav-label">商城</span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="ecommerce_products_grid.html">产品图表</a></li>
                        <li><a href="ecommerce_product_list.html">产品列表</a></li>
                        <li><a href="ecommerce_product.html">产品编辑</a></li>
                        <li><a href="ecommerce_product_detail.html">产品详情</a></li>
                        <li><a href="ecommerce-cart.html">购物车</a></li>
                        <li><a href="ecommerce-orders.html">订购页</a></li>
                        <li><a href="ecommerce_payments.html">支付页</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-picture-o"></i> <span class="nav-label">相册</span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li><a href="basic_gallery.html" class="J_menuItem">基本图库</a></li>
                        <li><a href="slick_carousel.html">图片切换</a></li>
                        <li><a href="carousel.html">Bootstrap 相册</a></li>

                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap"></i> <span class="nav-label">菜单 </span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="#">三级菜单 <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="#">三级菜单</a>
                                </li>
                                <li>
                                    <a href="#">三级菜单</a>
                                </li>
                                <li>
                                    <a href="#">三级菜单</a>
                                </li>

                            </ul>
                        </li>
                        <li><a href="#">二级菜单</a></li>
                        <li>
                            <a href="#">二级菜单</a></li>
                        <li>
                            <a href="#">二级菜单</a></li>
                    </ul>
                </li>
                <li>
                    <a href="css_animation.html"><i class="fa fa-magic"></i> <span class="nav-label">CSS 动画 </span><span
                                class="label label-info float-right">62</span></a>
                </li>
                <li class="landing_link">
                    <a target="_blank" href="landing.html"><i class="fa fa-star"></i> <span class="nav-label">着陆页</span>
                        <span class="label label-warning float-right">新</span></a>
                </li>
                <li class="special_link">
                    <a href="package.html"><i class="fa fa-database"></i> <span class="nav-label">组件</span></a>
                </li>
            </ul>

        </div>
    </nav>

    <div id="page-wrapper"  class="gray-bg">

        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i> <span class="label label-warning">16</span> </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li class="m-t-xs">
                                <div class="dropdown-messages-box">
                                    <a href="profile.html" class="pull-left">
                                        <img alt="image" class="img-circle" src="http://www.zi-han.net/theme/hplus/img/a7.jpg">
                                    </a>
                                    <div class="media-body">
                                        <small class="pull-right">46小时前</small>
                                        <strong>小四</strong> 这个在日本投降书上签字的军官，建国后一定是个不小的干部吧？ <br>
                                        <small class="text-muted">3天前 2014.11.8</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="dropdown-messages-box">
                                    <a href="profile.html" class="pull-left">
                                        <img alt="image" class="img-circle" src="http://www.zi-han.net/theme/hplus/img/a4.jpg">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right text-navy">25小时前</small>
                                        <strong>国民岳父</strong> 如何看待“男子不满自己爱犬被称为狗，刺伤路人”？——这人比犬还凶 <br>
                                        <small class="text-muted">昨天</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem" href="mailbox.html" data-index="88"> <i class="fa fa-envelope"></i> <strong> 查看所有消息</strong> </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span class="label label-primary">8</span> </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="mailbox.html">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> 您有16条未读消息
                                        <span class="pull-right text-muted small">4分钟前</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="profile.html">
                                    <div><i class="fa fa-qq fa-fw"></i> 3条新回复 <span class="pull-right text-muted small">12分钟钱</span></div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem" href="notifications.html" data-index="89"> <strong>查看所有 </strong> <i class="fa fa-angle-right"></i> </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="hidden-xs">
                        <a href="index_v1.html" class="J_menuItem" data-index="0"><i class="fa fa-cart-arrow-down"></i>
                            购买
                        </a>
                    </li>
                    <li class="dropdown hidden-xs">
                        <a class="right-sidebar-toggle" aria-expanded="false"> <i class="fa fa-tasks"></i> 主题 </a>
                    </li>
                </ul>
            </nav>
        </div>

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



        <#--<div id="page-wrapper" class="gray-bg">-->
        <#--<div class="row border-bottom">-->
        <#--<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">-->
        <#--<div class="navbar-header">-->
        <#--<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i>-->
        <#--</a>-->
        <#--<form role="search" class="navbar-form-custom" action="search_results.html">-->
        <#--<div class="form-group">-->
        <#--<input type="text" placeholder="请输入搜索内容" class="form-control" name="top-search"-->
        <#--id="top-search">-->
        <#--</div>-->
        <#--</form>-->
        <#--</div>-->
        <#--<ul class="nav navbar-top-links navbar-right">-->
        <#--<li>-->
        <#--<span class="m-r-sm text-muted welcome-message">欢迎来到inspinia 2.9.2管理后台</span>-->
        <#--</li>-->
        <#--<li class="dropdown">-->
        <#--<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">-->
        <#--<i class="fa fa-envelope"></i> <span class="label label-warning">16</span>-->
        <#--</a>-->
        <#--<ul class="dropdown-menu dropdown-messages">-->
        <#--<li>-->
        <#--<div class="dropdown-messages-box">-->
        <#--<a class="dropdown-item float-left" href="profile.html">-->
        <#--<img alt="image" class="rounded-circle" src="img/a7.jpg">-->
        <#--</a>-->
        <#--<div class="media-body">-->
        <#--<small class="float-right">46 小时前</small>-->
        <#--<strong>小明</strong> 评论了 <strong>小红</strong>. <br>-->
        <#--<small class="text-muted">2017.10.06 7:58</small>-->
        <#--</div>-->
        <#--</div>-->
        <#--</li>-->
        <#--<li class="dropdown-divider"></li>-->
        <#--<li>-->
        <#--<div class="dropdown-messages-box">-->
        <#--<a class="dropdown-item float-left" href="profile.html">-->
        <#--<img alt="image" class="rounded-circle" src="img/a4.jpg">-->
        <#--</a>-->
        <#--<div class="media-body ">-->
        <#--<small class="float-right text-navy">5 小时前</small>-->
        <#--<strong>小红</strong> 打电话给了 <strong>小黑</strong>. <br>-->
        <#--<small class="text-muted">2017.10.06 7:58</small>-->
        <#--</div>-->
        <#--</div>-->
        <#--</li>-->
        <#--<li class="dropdown-divider"></li>-->
        <#--<li>-->
        <#--<div class="dropdown-messages-box">-->
        <#--<a class="dropdown-item float-left" href="profile.html">-->
        <#--<img alt="image" class="rounded-circle" src="img/profile.jpg">-->
        <#--</a>-->
        <#--<div class="media-body ">-->
        <#--<small class="float-right">23 小时前</small>-->
        <#--<strong>小黑</strong> 点赞了 <strong>小红</strong>. <br>-->
        <#--<small class="text-muted">2017.10.06 7:58</small>-->
        <#--</div>-->
        <#--</div>-->
        <#--</li>-->
        <#--<li class="dropdown-divider"></li>-->
        <#--<li>-->
        <#--<div class="text-center link-block">-->
        <#--<a href="mailbox.html" class="dropdown-item">-->
        <#--<i class="fa fa-envelope"></i> <strong>阅读所有消息</strong>-->
        <#--</a>-->
        <#--</div>-->
        <#--</li>-->
        <#--</ul>-->
        <#--</li>-->
        <#--<li class="dropdown">-->
        <#--<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">-->
        <#--<i class="fa fa-bell"></i> <span class="label label-primary">8</span>-->
        <#--</a>-->
        <#--<ul class="dropdown-menu dropdown-alerts">-->
        <#--<li>-->
        <#--<a href="mailbox.html" class="dropdown-item">-->
        <#--<div>-->
        <#--<i class="fa fa-envelope fa-fw"></i> 你有16条消息-->
        <#--<span class="float-right text-muted small">4 分钟前</span>-->
        <#--</div>-->
        <#--</a>-->
        <#--</li>-->
        <#--<li class="dropdown-divider"></li>-->
        <#--<li>-->
        <#--<a href="profile.html" class="dropdown-item">-->
        <#--<div>-->
        <#--<i class="fa fa-twitter fa-fw"></i> 3 个新的关注者-->
        <#--<span class="float-right text-muted small">12 分钟前</span>-->
        <#--</div>-->
        <#--</a>-->
        <#--</li>-->
        <#--<li class="dropdown-divider"></li>-->
        <#--<li>-->
        <#--<a href="grid_options.html" class="dropdown-item">-->
        <#--<div>-->
        <#--<i class="fa fa-upload fa-fw"></i> 重启服务器-->
        <#--<span class="float-right text-muted small">4 分钟前</span>-->
        <#--</div>-->
        <#--</a>-->
        <#--</li>-->
        <#--<li class="dropdown-divider"></li>-->
        <#--<li>-->
        <#--<div class="text-center link-block">-->
        <#--<a href="notifications.html" class="dropdown-item">-->
        <#--<strong>查看所有信息</strong>-->
        <#--<i class="fa fa-angle-right"></i>-->
        <#--</a>-->
        <#--</div>-->
        <#--</li>-->
        <#--</ul>-->
        <#--</li>-->


        <#--<li>-->
        <#--<a href="login.html">-->
        <#--<i class="fa fa-sign-out"></i> 注销-->
        <#--</a>-->
        <#--</li>-->
        <#--</ul>-->

        <#--</nav>-->
        <#--</div>-->
        <#--<div class="row wrapper border-bottom white-bg page-heading">-->
        <#--<div class="col-lg-10">-->
        <#--<h2>jqGrid</h2>-->
        <#--<ol class="breadcrumb">-->
        <#--<li class="breadcrumb-item">-->
        <#--<a href="index.html">主页</a>-->
        <#--</li>-->
        <#--<li class="breadcrumb-item">-->
        <#--<a>表格</a>-->
        <#--</li>-->
        <#--<li class="breadcrumb-item active">-->
        <#--<strong>jqGrid</strong>-->
        <#--</li>-->
        <#--</ol>-->
        <#--</div>-->
        <#--</div>-->
        <#--<div class="wrapper wrapper-content  animated fadeInRight">-->
        <#--<div class="row">-->
        <#--<div class="col-lg-12">-->
        <#--<div class="ibox ">-->
        <#--<div class="ibox-title">-->
        <#--<h5>jQuery网格插件 – jqGrid</h5>-->
        <#--</div>-->
        <#--<div class="ibox-content">-->
        <#--<p>-->
        <#--<strong>jqGrid</strong>-->
        <#--是一个支持Ajax的JavaScript控件，为Web上的表格数据提供了解决方案。由于网格是客户端解决方案通过Ajax回调动态加载数据，因此可以与任何服务器端技术集成，包括PHP，ASP，Java-->
        <#--Servlet，JSP，ColdFusion和Perl。jqGrid使用jQuery Java脚本库，并作为该包的插件编写。有关jQuery Grid的更多信息，请参考 <a-->
        <#--target="_blank" href="http://www.trirand.com/blog/"> jqGrid网站</a>-->
        <#--</p>-->

        <#--<h4>基本例子</h4>-->

        <#--&lt;#&ndash;<div class="jqGrid_wrapper">&ndash;&gt;-->
        <#--&lt;#&ndash;<div class="row">&ndash;&gt;-->
        <#--&lt;#&ndash;&lt;#&ndash;<div class="col-sm-20">&ndash;&gt;&ndash;&gt;-->
        <#--&lt;#&ndash;<form id="formSearch" class="form-horizontal">&ndash;&gt;-->
        <#--&lt;#&ndash;<div class="form-group" style="margin-top:15px">&ndash;&gt;-->
        <#--&lt;#&ndash;<label class="control-label col-sm-1" style="margin-left: 20px;" for="GOODS_ID">商品ID</label>&ndash;&gt;-->
        <#--&lt;#&ndash;<div class="col-sm-2">&ndash;&gt;-->
        <#--&lt;#&ndash;<input type="text" class="form-control" id="GOODS_ID" name="GOODS_ID">&ndash;&gt;-->
        <#--&lt;#&ndash;</div>&ndash;&gt;-->
        <#--&lt;#&ndash;<label class="control-label col-sm-1" style="width: 120px" for="GOODS_NAM">商品名称 </label>&ndash;&gt;-->
        <#--&lt;#&ndash;<div class="col-sm-2">&ndash;&gt;-->
        <#--&lt;#&ndash;<input type="text" class="form-control" id="GOODS_NAM">&ndash;&gt;-->
        <#--&lt;#&ndash;</div>&ndash;&gt;-->
        <#--&lt;#&ndash;<div class="col-sm-1" style="text-align:center;">&ndash;&gt;-->
        <#--&lt;#&ndash;<button type="button"  id="find_btn" class="btn btn-primary">查询</button>&ndash;&gt;-->
        <#--&lt;#&ndash;</div>&ndash;&gt;-->
        <#--&lt;#&ndash;</div>&ndash;&gt;-->
        <#--&lt;#&ndash;</form>&ndash;&gt;-->
        <#--&lt;#&ndash;<div class="ibox-content">&ndash;&gt;-->

        <#--&lt;#&ndash;</div>&ndash;&gt;-->
        <#--&lt;#&ndash;</div>&ndash;&gt;-->
        <#--&lt;#&ndash;</div>&ndash;&gt;-->

        <#--&lt;#&ndash;</div>&ndash;&gt;-->
        <#--<div class="jqGrid_wrapper">-->
        <#--<div id="pager_tools"></div>-->
        <#--<table id="table_list_1"></table>-->
        <#--<div id="pager_list_1"></div>-->
        <#--</div>-->
        <#--<h4>高级示例</h4>-->
        <#--<p>-->

        <#--</p>-->

        <#--<div class="jqGrid_wrapper">-->
        <#--<table id="table_list_2"></table>-->
        <#--<div id="pager_list_2"></div>-->
        <#--</div>-->
        <#--</div>-->
        <#--</div>-->
        <#--</div>-->
        <#--</div>-->
        <#--</div>-->
        <#--<div class="footer">-->
        <#--<div class="float-right">-->
        <#--<strong>2.9.2 inspinia</strong>-->
        <#--</div>-->
        <#--<div>-->
        <#--<strong>Copyright</strong> inspinia 2.9.2 &copy; 2014-2018-->
        <#--</div>-->
        <#--</div>-->

        <#--</div>-->
    </div>


    <!-- Mainly scripts -->
    <script src="${base}/inspinia/js/jquery-3.1.1.min.js"></script>
    <script src="${base}/inspinia/js/popper.min.js"></script>
    <script src="${base}/inspinia/js/bootstrap.js"></script>
    <script src="${base}/inspinia/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${base}/inspinia/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Peity -->
    <script src="${base}/inspinia/js/plugins/peity/jquery.peity.min.js"></script>

    <!-- jqGrid -->
    <script src="${base}/inspinia/js/plugins/jqGrid/i18n/grid.locale-en.js"></script>
    <script src="${base}/inspinia/js/plugins/jqGrid/jquery.jqGrid.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="${base}/inspinia/js/inspinia.js"></script>
    <script src="${base}/inspinia/js/plugins/pace/pace.min.js"></script>

    <script src="${base}/inspinia/js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <script type="text/javascript" src="${base}/contabs/contabs.js"></script>


    <script>
        $(document).ready(function () {


            // Examle data for jqGrid
            var mydata = [
                {id: "1", invdate: "2010-05-24", name: "test", note: "note", tax: "10.00", total: "2111.00"},
                {id: "2", invdate: "2010-05-25", name: "test2", note: "note2", tax: "20.00", total: "320.00"},
                {id: "3", invdate: "2007-09-01", name: "test3", note: "note3", tax: "30.00", total: "430.00"},
                {id: "4", invdate: "2007-10-04", name: "test", note: "note", tax: "10.00", total: "210.00"},
                {id: "5", invdate: "2007-10-05", name: "test2", note: "note2", tax: "20.00", total: "320.00"},
                {id: "6", invdate: "2007-09-06", name: "test3", note: "note3", tax: "30.00", total: "430.00"},
                {id: "7", invdate: "2007-10-04", name: "test", note: "note", tax: "10.00", total: "210.00"},
                {
                    id: "8",
                    invdate: "2007-10-03",
                    name: "test2",
                    note: "note2",
                    amount: "300.00",
                    tax: "21.00",
                    total: "320.00"
                },
                {
                    id: "9",
                    invdate: "2007-09-01",
                    name: "test3",
                    note: "note3",
                    amount: "400.00",
                    tax: "30.00",
                    total: "430.00"
                },
                {
                    id: "11",
                    invdate: "2007-10-01",
                    name: "test",
                    note: "note",
                    amount: "200.00",
                    tax: "10.00",
                    total: "210.00"
                },
                {
                    id: "12",
                    invdate: "2007-10-02",
                    name: "test2",
                    note: "note2",
                    amount: "300.00",
                    tax: "20.00",
                    total: "320.00"
                },
                {
                    id: "13",
                    invdate: "2007-09-01",
                    name: "test3",
                    note: "note3",
                    amount: "400.00",
                    tax: "30.00",
                    total: "430.00"
                },
                {
                    id: "14",
                    invdate: "2007-10-04",
                    name: "test",
                    note: "note",
                    amount: "200.00",
                    tax: "10.00",
                    total: "210.00"
                },
                {
                    id: "15",
                    invdate: "2007-10-05",
                    name: "test2",
                    note: "note2",
                    amount: "300.00",
                    tax: "20.00",
                    total: "320.00"
                },
                {
                    id: "16",
                    invdate: "2007-09-06",
                    name: "test3",
                    note: "note3",
                    amount: "400.00",
                    tax: "30.00",
                    total: "430.00"
                },
                {
                    id: "17",
                    invdate: "2007-10-04",
                    name: "test",
                    note: "note",
                    amount: "200.00",
                    tax: "10.00",
                    total: "210.00"
                },
                {
                    id: "18",
                    invdate: "2007-10-03",
                    name: "test2",
                    note: "note2",
                    amount: "300.00",
                    tax: "20.00",
                    total: "320.00"
                },
                {
                    id: "19",
                    invdate: "2007-09-01",
                    name: "test3",
                    note: "note3",
                    amount: "400.00",
                    tax: "30.00",
                    total: "430.00"
                },
                {
                    id: "21",
                    invdate: "2007-10-01",
                    name: "test",
                    note: "note",
                    amount: "200.00",
                    tax: "10.00",
                    total: "210.00"
                },
                {
                    id: "22",
                    invdate: "2007-10-02",
                    name: "test2",
                    note: "note2",
                    amount: "300.00",
                    tax: "20.00",
                    total: "320.00"
                },
                {
                    id: "23",
                    invdate: "2007-09-01",
                    name: "test3",
                    note: "note3",
                    amount: "400.00",
                    tax: "30.00",
                    total: "430.00"
                },
                {
                    id: "24",
                    invdate: "2007-10-04",
                    name: "test",
                    note: "note",
                    amount: "200.00",
                    tax: "10.00",
                    total: "210.00"
                },
                {
                    id: "25",
                    invdate: "2007-10-05",
                    name: "test2",
                    note: "note2",
                    amount: "300.00",
                    tax: "20.00",
                    total: "320.00"
                },
                {
                    id: "26",
                    invdate: "2007-09-06",
                    name: "test3",
                    note: "note3",
                    amount: "400.00",
                    tax: "30.00",
                    total: "430.00"
                },
                {
                    id: "27",
                    invdate: "2007-10-04",
                    name: "test",
                    note: "note",
                    amount: "200.00",
                    tax: "10.00",
                    total: "210.00"
                },
                {
                    id: "28",
                    invdate: "2007-10-03",
                    name: "test2",
                    note: "note2",
                    amount: "300.00",
                    tax: "20.00",
                    total: "320.00"
                },
                {
                    id: "29",
                    invdate: "2007-09-01",
                    name: "test3",
                    note: "note3",
                    amount: "400.00",
                    tax: "30.00",
                    total: "430.00"
                }
            ];

            // Configuration for jqGrid Example 1
            $("#table_list_1").jqGrid({
                url: "${base}/bankinfo/queryPagedList",
                datatype: "json",
                mtype: "get",
                // Setup buttons
                styleUI: 'Bootstrap',
                height: 250,
                autowidth: true,
                shrinkToFit: true,
                rownumbers: true,
                viewrecords: true,
                rowNum: 10,
                rowList: [10, 20, 30],
                colNames: ['投票号', '数据', '客户端', '合计', '税收', '总额', '记录'],
                colModel: [
                    {name: 'id', index: 'id', width: 60, sorttype: "int"},
                    {name: 'invdate', index: 'invdate', width: 90, sorttype: "date", formatter: "date"},
                    {name: 'name', index: 'name', width: 100,searchoptions:{sopt:['eq','ne','le','lt','gt','ge']}},
                    {name: 'amount', index: 'amount', width: 80, align: "right", sorttype: "float", formatter: "number"},
                    {name: 'tax', index: 'tax', width: 80, align: "right", sorttype: "float"},
                    {name: 'total', index: 'total', width: 80, align: "right", sorttype: "float"},
                    {name: 'note', index: 'note', width: 150, sortable: false}
                ],
                pager: "#pager_list_1",
                viewrecords: true,
                // caption: "示例 jqGrid 1",
                hidegrid: false,
                jsonReader: {
                    root: "list",    // json中代表实际模型数据的入口
                    page: "pageIndex",    // json中代表当前页码的数据
                    total: "pageCount",    // json中代表页码总数的数据
                    records: "count", // json中代表数据行总数的数据
                    repeatitems: true, // 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。
                    cell: "cell",
                    id: "id",
                    // userdata: "userdata",
                    // subgrid: {
                    //      root:"rows",
                    //         repeatitems: true,
                    //       cell:"cell"
                    // }
                },
                prmNames : {
                    page:"pageNumber",  // 页码（第几页）
                    rows:"pageSize",    //每页多少条数据
                    order: "order",
                    sort:"orderKey"

                }
            });

            // Setup buttons
            $("#table_list_1").jqGrid('navGrid', '#pager_list_1',
                {edit: true, add: true, del: true, search: true},
                {height: 200, reloadAfterSubmit: true}
            );

            // Configuration for jqGrid Example 2
            $("#table_list_2").jqGrid({
                data: mydata,
                datatype: "local",
                height: 450,
                autowidth: true,
                shrinkToFit: true,
                rowNum: 20,
                rowList: [10, 20, 30],
                colNames: ['投票号', '数据', '客户端', '合计', '税收', '总额', '记录'],
                colModel: [
                    {name: 'id', index: 'id', editable: true, width: 60, sorttype: "int", search: true},
                    {name: 'invdate', index: 'invdate', editable: true, width: 90, sorttype: "date", formatter: "date"},
                    {name: 'name', index: 'name', editable: true, width: 100},
                    {
                        name: 'amount',
                        index: 'amount',
                        editable: true,
                        width: 80,
                        align: "right",
                        sorttype: "float",
                        formatter: "number"
                    },
                    {name: 'tax', index: 'tax', editable: true, width: 80, align: "right", sorttype: "float"},
                    {name: 'total', index: 'total', editable: true, width: 80, align: "right", sorttype: "float"},
                    {name: 'note', index: 'note', editable: true, width: 100, sortable: false}
                ],
                pager: "#pager_list_2",
                viewrecords: true,
                caption: "示例 jqGrid 2",
                add: true,
                edit: true,
                addtext: 'Add',
                edittext: 'Edit',
                hidegrid: false
            });

            // Add selection
            // $("#table_list_2").setSelection(4, true);


            // Setup buttons
            $("#table_list_2").jqGrid('navGrid', '#pager_list_2',
                {edit: true, add: true, del: true, search: true},
                {height: 200, reloadAfterSubmit: true}
            );

            // Add responsive to jqGrid
            $(window).bind('resize', function () {
                var width = $('.jqGrid_wrapper').width();
                $('#table_list_1').setGridWidth(width);
                $('#table_list_2').setGridWidth(width);
            });


            setTimeout(function () {
                $('.wrapper-content').removeClass('animated fadeInRight');
            }, 700);

        });

    </script>


</body>

</html>
