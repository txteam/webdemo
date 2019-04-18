var tx = {};
var $mainframe = $("<div/>");
$(function(){
    var $container = $('#container');
    $container.layout({
    	fit : true
    });
    
    var $indexTabs = $('#index_tabs');
    var euiIndexTabs = null;
    var $indexTabsMenu = $('#index_tabsMenu');
    
    /**
     * 初始化页面tabs 
     */
    euiIndexTabs = $indexTabs.tabs({
        fit : true,
        border : false,
        tabHeight: 44,
        onBeforeClose: function(title,index){
        	$.triggerge('mainframe_tab_before_close',[{title:title,index:index}]);
        },
        onSelect:function(title){
			var selectedTab = $indexTabs.tabs('getTab',title);
			var $iframe = $(selectedTab).find("iframe.mainframeTab");
			if($iframe.attr('refreshWhenSelect') && $iframe.attr('refreshWhenSelect') === 'true'){
				$iframe.attr('src',$iframe.attr('newSrc'));
			}
		},
        onContextMenu : function(e, title) {
            e.preventDefault();
	    	if($(e.target).closest('li').size() > 0 || $(e.target).is('li')){
	    		$('#mm').menu('show', {
		             left: e.pageX,
		             top: e.pageY,
		         });
	    		var subtitle = $(e.target).closest('li').size() ? $(e.target).closest('li') : $(e.target);
        		$('#mm').data("currtab",subtitle.text());
	    	}
        }
    });
    //关闭当前
    $('#mm-tabclose').click(function(){
        var currtab_title = $('#mm').data("currtab");
        var t = $(".easyui-tabs1:visible").tabs('getTab', currtab_title);
        if (t.panel('options').closable) {
        	$(".easyui-tabs1:visible").tabs('close',currtab_title);
        }
    });
    //全部关闭
    $('#mm-tabcloseall').click(function(){
        $(".easyui-tabs1:visible").find('.tabs li').each(function(i,n){
        	var currtab_title = $(n).text();
        	var t = $(".easyui-tabs1:visible").tabs('getTab', currtab_title);
            if (t.panel('options').closable) {
            	$(".easyui-tabs1:visible").tabs('close',currtab_title);
            }
        });    
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function(){
        var currtab_title = $('#mm').data("currtab");
        $(".easyui-tabs1:visible").find('.tabs li').each(function(i,n){
        	var tab_title_temp = $(n).text();
        	var t = $(".easyui-tabs1:visible").tabs('getTab', tab_title_temp);
            if (tab_title_temp != currtab_title && t.panel('options').closable) {
            	$(".easyui-tabs1:visible").tabs('close',tab_title_temp);
            }
        }); 
        $(".easyui-tabs1:visible").tabs('select', currtab_title);
    });

    $mainframe.bind("addOrSelectTab", function(event, options) {
        var options = $.extend({}, {
        	id: null,
            title: "",
            href: null,
            refreshWhenSelect: false,
            iconCls: null,
            contentText : null
        }, options);
        //如果指定tab已经存在，则直接将该tab选中即可
        if ($indexTabs.tabs('exists', options.title)) {
        	var selectedTab = $indexTabs.tabs('getTab',options.title);
 			var $iframe = $(selectedTab).find("iframe.mainframeTab");
 			var oldSrc = $iframe.attr('src');
 			if($iframe.attr('refreshWhenSelect') === 'true'){
 				$iframe.attr('newSrc',options.href);
 			}else{
 				$iframe.attr('src',options.href);
 			}
            $indexTabs.tabs('select', options.title);
            //如果已经存在直接关闭加载进度条
            DialogUtils.progress('close');
            return false;
        } else if (options.href) {
            //以iframe的形式显示tab
            var newIframe = '<iframe class="mainframeTab" src="' + options.href + '" frameborder="0" border="0" style="border:0;width:100%;height:98%;"></iframe>';
            if(options.refreshWhenSelect){
            	newIframe = '<iframe refreshWhenSelect="true" class="mainframeTab" newSrc=' + options.href + ' src="' + options.href + '" frameborder="0" border="0" style="border:0;width:100%;height:98%;"></iframe>';
            }
            $indexTabs.tabs('add', {
                title : options.title,
                closable : true,
                iconCls : options.iconCls,
                content : newIframe,
                border : false,
                fit : true
            });
        }
    });
    $mainframe.bind("refreshTab", function(event, options) {
        var options = $.extend({}, {
            title : "",
            href : null,
            iconCls : null,
            contentText : null
        }, options);
        //如果指定tab已经存在，则直接将该tab选中即可
        if ($indexTabs.tabs('exists', options.title)) {
            //TODO:后续加入，根据tab中如果存在refreshOnEverySelected属性时每次选中均刷新的逻辑<br/>
            $indexTabs.tabs('select', options.title);
            var tab = $indexTabs.tabs('getTab', options.title);
            if($(tab).find("iframe.mainframeTab") > 0){
                var $iframe = $(tab).find("iframe.mainframeTab");
                if(!$.ObjectUtils.isEmpty(options.href)){
                    $iframe.attr("src",options.href);
                }else{
                    var srcTemp = $iframe.attr("src");
                    $iframe.attr("src",srcTemp);
                }
            }
            //如果已经存在直接关闭加载进度条
            DialogUtils.progress('close');
            return false;
        }
    });
    $mainframe.bind("closeSelectedTab",function(event){
    	//console.info($indexTabs.tabs("getSelected").title);
    	var tab = $indexTabs.tabs('getSelected');  
        var index = $indexTabs.tabs('getTabIndex',tab);  
    	$indexTabs.tabs('close',index);
    });
    $mainframe.bind("closeTab",function(event, options){
    	var options = $.extend({}, {
            title : ""
        }, options);
    	console.log('closeTab:' + options.title);
    	//如果指定tab已经存在，则直接将该tab选中即可
        if ($indexTabs.tabs('exists', options.title)) {
        	$indexTabs.tabs('close', options.title);
            return false;
        }
    });
});

$.bindge("closeTab", function(event, options) {
    $mainframe.trigger("closeTab", options);
    return true;
});
$.bindge("addOrSelectTab", function(event, options) {
    var hrefValue = options.href;
    if ((hrefValue.indexOf("http://") < 0 || hrefValue.indexOf("http://") > 0 ) && hrefValue.indexOf(_contextPath) != 0) {
        options.href = _contextPath + hrefValue;
    }
    if (hrefValue.indexOf("/") == 0 && hrefValue.indexOf(_contextPath) != 0) {
        options.href = _contextPath + hrefValue;
    }
    $mainframe.trigger("addOrSelectTab", options);
    return true;
});
$.bindge("refreshTab", function(event, options) {
	if(options.href){
		var hrefValue = options.href;
	    if ((hrefValue.indexOf("http://") < 0 || hrefValue.indexOf("http://") > 0 ) && hrefValue.indexOf(_contextPath) != 0) {
	        options.href = _contextPath + hrefValue;
	    }
	    if (hrefValue.indexOf("/") == 0 && hrefValue.indexOf(_contextPath) != 0) {
	        options.href = _contextPath + hrefValue;
	    }
	}
    $mainframe.trigger("refreshTab", options);
    return true;
});
//关闭当前选中的tab
$.bindge("closeSelectedTab",function(event){
	$mainframe.trigger("closeSelectedTab");
	return true;
});

//session丢失后跳转到会话丢失页面
$.bindge("sessionLost",function(event){
    GlobalDialogUtils.openModalDialog("autoRedirectToLoginPage","登录超时",_contextPath + "/mainframe/toSessionLostError.action",400,300);
    return true;
});
//系统自动跳转到登录页面
$.bindge("returnLoginPage",function(event){
    window.location.href = _contextPath + "/mainframe/toLogin.action";
});


/**
 * 菜单控件 
 */
$(function(){
    //菜单初始化方法
    var Menu = function(options){
        var _this = this;
        _this.options = $.extend({
        	menus : []
        },options);
        _this.create();
    };
    tx.Menu = Menu;
    Menu.prototype.$top_menu_wrap = $(".pf-nav");
    Menu.prototype.$sider_menu_wrap = $(".pf-sider-wrap");
    Menu.prototype.menuItemMapping = {};
    Menu.prototype.options = {};
    Menu.getIcon = function(menu){
    	var icon = '';
    	if($.ObjectUtils.isEmpty(menu.icon)){
    		icon = '&#xe646;';
    	}else{
    		icon = menu.icon;
    	}
    	return icon;
    };
    Menu.prototype.create = function(){
    	var _this = this;
    	_this._createTopMenu(_this.options.menus);
    	_this._bindEvent();
    };
    Menu.prototype._bindEvent = function(){
    	//var _this = this;
    	$(document).on('click', '.pf-nav-prev,.pf-nav-next', function() {
    		if ($(this).hasClass('disabled')) {
    			return;
    		}
    		if ($(this).hasClass('pf-nav-next')) {
    			page++;
    			$('.pf-nav').stop().animate({
    				'margin-top' : -70 * page
    			}, 200);
    			if (page == pages) {
    				$(this).addClass('disabled');
    				$('.pf-nav-prev').removeClass('disabled');
    			} else {
    				$('.pf-nav-prev').removeClass('disabled');
    			}
    		} else {
    			page--;
    			$('.pf-nav').stop().animate({
    				'margin-top' : -70 * page
    			}, 200);
    			if (page == 0) {
    				$(this).addClass('disabled');
    				$('.pf-nav-next').removeClass('disabled');
    			} else {
    				$('.pf-nav-next').removeClass('disabled');
    			}
    		}
    	});
    	//左侧菜单收起
        $(document).on('click', '.toggle-icon', function() {
            $(this).closest("#pf-bd").toggleClass("toggle");
            $(window).resize();
        });
    };
    Menu.prototype._createTopMenu = function(menus){
    	var _this = this;
		var currentIndex = -1;
		for(var i = 0, len = menus.length; i < len; i++) {
			var top_menu_html = '<li class="pf-nav-item project" data-sort="'+ i +'" data-menu="system_menu_" + i>'+
		        	'<a href="javascript:;">'+
		        		'<span class="iconfont">'+ Menu.getIcon(menus[i]) +'</span>'+
		        		'<span class="pf-nav-title">'+ menus[i].text +'</span>'+
		            '</a>'+
	            '</li>';
			var $top_menu_html = $(top_menu_html);
			_this.$top_menu_wrap.append($top_menu_html);
			_this._createSiderMenu(menus[i], i);
	        if (menus[i].opened && currentIndex < 0){
	        	currentIndex = i;
	        }
	        $top_menu_html.click({index: i,menu: menus[i]},function(event){
	        	$('.pf-nav-item').removeClass('current');
	            $(this).addClass('current');
	            // 渲染对应侧边菜单
	            _this._showSiderMenu(event.data.index);
	            // 触发菜单选择功能
	        	_this.selectedMenuItem(event.data.menu);
	        });
		};
		if(currentIndex < 0){
			currentIndex = 0;
		}
		if(menus.length > 0){
			//渲染当前
			$('.pf-nav-item').eq(currentIndex).addClass('current');
			_this._showSiderMenu(currentIndex);
		}
	};
	Menu.prototype._showSiderMenu = function(index){
		$('.pf-sider').hide();
		if($('.pf-sider[arrindex='+ index +']').size() > 0) {
			$('.pf-sider[arrindex='+ index +']').show();
			return false;
		};
	};
	Menu.prototype._createSiderMenu = function(menu, index) {
		var _this = this;
		//$('.pf-sider').hide();// 影藏所有的silerMenu
		//this._createPageContainer(index);
		//if ($('.pf-sider[arrindex=' + index + ']').size() > 0) {
		//	return false;
		//}
		var $sider_menu = $('<div class="pf-sider" arrindex="' + index + '"></div>');
		$sider_menu.append($('<h2 class="pf-model-name">'
				+ '<span class="iconfont">' + Menu.getIcon(menu) + '</span>'
				+ '<span class="pf-name">' + menu.text + '</span>'
				+ '<span class="toggle-icon"></span>' + '</h2>')).appendTo(_this.$sider_menu_wrap);
		$sider_menu.hide();
		var $sider_child_wrap = $('<ul class="sider-nav"></ul>').appendTo($sider_menu);
		
		for (var i = 0, len = menu.childs.length; i < len; i++) {
			var m = menu.childs[i], mstr = '';
			var $sider_menu = null;
			if (m.childs && m.childs.length > 0) {
				$sider_menu = $('<li></li>');
			} else {
				$sider_menu = $('<li class="no-child" data-href="' + m.href + '"></li>');
			}
			$sider_menu.append($('<a href="javascript:;" class="pf-menu-title">'
					+ '<span class="iconfont sider-nav-icon">' + Menu.getIcon(m)
					+ '</span>' + '<span class="sider-nav-title">' + m.text
					+ '</span>' + '<i class="iconfont">&#xe642;</i>' + '</a>')).appendTo($sider_child_wrap);
			var $sider_menu_child_wrap = $('<ul class="sider-nav-s"></ul>').appendTo($sider_menu);
			if (m.childs && m.childs.length > 0) {
				_this._createSiderChildMenu(m,i,$sider_menu_child_wrap);
			}
			$sider_menu.click({index: i,menu: m},function(event){
				$(this).closest('.pf-sider').find('.sider-nav li').removeClass('current');
	            $(this).closest('li').addClass('current');
	            // 触发菜单选择功能
	        	_this.selectedMenuItem(event.data.menu);
	        });
		}
	};
	Menu.prototype._createSiderChildMenu = function(menu,index,$menu_wrap){
		//alert($.tojsonstring(menu));
		var _this = this;
        for(var j = 0, jlen = menu.childs.length; j < jlen; j++){
        	var menu_html = '';
        	var child = menu.childs[j];
        	if(child.isCurrent){
        		menu_html += '<li class="active" text="'+ child.text +'" data-href="' + child.href + '"><a href="#">'+ child.text +'</a></li>';
        	}else {
        		menu_html += '<li text="'+ child.text +'" data-href="' + child.href + '"><a href="#">'+ child.text +'</a></li>';
        	}
        	var $menu = $(menu_html).appendTo($menu_wrap);
        	
        	$menu.click({index: j,menu: child},function(event){
        		$(this).closest('.pf-sider').attr('arrindex');
            	$(this).closest('.pf-sider').find('.active').removeClass('active');
            	$(this).addClass('active');
	            // 触发菜单选择功能
	        	_this.selectedMenuItem(event.data.menu);
	        });
        }
	};
    Menu.prototype.selectedMenuItem = function(menuItem){
    	//alert($.toJsonString(menuItem));
        if(menuItem.menuTarget == "triggerGlobalEvent"){
            $.triggerge(menuItem.eventType,[menuItem]);
            return false;
        }else if(menuItem.menuTarget == "openDialog"){
            if(menuItem.isModal){
                //打开模态dialog
            }else{
                //打开非模态对话框
            }
        }else{
            if($.ObjectUtils.isEmpty(menuItem.href)){
                return false;
            }
            if(menuItem.selectRefresh){
            	$.triggerGE("addOrSelectTab",[{
            		refreshWhenSelect : true,
                    title : menuItem.text,
                    href : menuItem.href,
                    iconCls : menuItem.icon
                }]);
            }else{
            	$.triggerGE("addOrSelectTab",[{
                    title : menuItem.text,
                    href : menuItem.href,
                    iconCls : menuItem.icon
                }]);
            }
            return false;
        }
    };
});

