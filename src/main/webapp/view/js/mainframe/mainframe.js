var $mainframe = $("<div/>");
$(function(){
    var $indexLayout = $('#index_layout');
    var euiIndexLayout = null;
    var $indexTabs = $('#index_tabs');
    var euiIndexTabs = null;
    var $indexTabsMenu = $('#index_tabsMenu');
    var euiIndexTabsMenu = null;
    
    /**
     *初始化mainframe页面布局 
     */
    euiIndexLayout = $indexLayout.layout({
        fit : true
    });
    /**
     * 初始化页面tabs 
     */
    euiIndexTabs = $indexTabs.tabs({
        fit : true,
        border : false,
        onContextMenu : function(e, title) {
            e.preventDefault();
            euiIndexTabsMenu.menu('show', {
                left : e.pageX,
                top : e.pageY
            }).data('tabTitle', title);
        },
        tools : [{
            iconCls : 'database_refresh',
            handler : function() {
                var href = euiIndexTabs.tabs('getSelected').panel('options').href;
                if (href) {
                    /*说明tab是以href方式引入的目标页面*/
                    var index = euiIndexTabs.tabs('getTabIndex', euiIndexTabs.tabs('getSelected'));
                    euiIndexTabs.tabs('getTab', index).panel('refresh');
                } else {
                    /*说明tab是以content方式引入的目标页面*/
                    var panel = euiIndexTabs.tabs('getSelected').panel('panel');
                    var frame = panel.find('iframe');
                    try {
                        if (frame.length > 0) {
                            for (var i = 0; i < frame.length; i++) {
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
                var index = euiIndexTabs.tabs('getTabIndex', euiIndexTabs.tabs('getSelected'));
                var tab = euiIndexTabs.tabs('getTab', index);
                if (tab.panel('options').closable) {
                    euiIndexTabs.tabs('close', index);
                } else {
                    $.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
                }
            }
        }]
    });
    /**
     * 设置euiIndexTabsMenu工具菜单
     */
    euiIndexTabsMenu = $indexTabsMenu.menu({
        onClick : function(item) {
            var curTabTitle = $(this).data('tabTitle');
            var type = $(item.target).attr('title');

            if (type === 'refresh') {
                euiIndexTabs.tabs('getTab', curTabTitle).panel('refresh');
                return;
            }else if (type === 'close') {
                var t = euiIndexTabs.tabs('getTab', curTabTitle);
                if (t.panel('options').closable) {
                    euiIndexTabs.tabs('close', curTabTitle);
                }
                return;
            }else{
                var allTabs = euiIndexTabs.tabs('tabs');
                var closeTabsTitle = [];
                $.each(allTabs, function() {
                    var opt = $(this).panel('options');
                    if (opt.closable && opt.title != curTabTitle && type === 'closeOther') {
                        closeTabsTitle.push(opt.title);
                    } else if (opt.closable && type === 'closeAll') {
                        closeTabsTitle.push(opt.title);
                    }
                });
                for (var i = 0; i < closeTabsTitle.length; i++) {
                    euiIndexTabs.tabs('close', closeTabsTitle[i]);
                }
            }

        }
    });

    $mainframe.bind("addOrSelectTab", function(event, options) {
        /*
        DialogUtils.progress({
            title :'提示',
            text : '加载中，请等待....'
        });
        */
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
            //如果已经存在直接关闭加载进度条
            DialogUtils.progress('close');
            return false;
        } else if (options.href) {
            //以iframe的形式显示tab
            var newIframe = '<iframe class="mainframeTab" src="' + options.href + '" frameborder="0" border="0" style="border:0;width:100%;height:98%;"></iframe>';
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
            /*
            DialogUtils.progress({
                title :'提示',
                text : '加载中，请等待....'
            });
            */
            var tab = $indexTabs.tabs('getTab', options.title);
            if($(tab).find("iframe.mainframeTab") > 0){
                var $iframe = $(tab).find("iframe.mainframeTab");
                if($.ObjectUtils.isEmpty(options.href)){
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
    var hrefValue = options.href;
    if ((hrefValue.indexOf("http://") < 0 || hrefValue.indexOf("http://") > 0 ) && hrefValue.indexOf(_contextPath) != 0) {
        options.href = _contextPath + hrefValue;
    }
    if (hrefValue.indexOf("/") == 0 && hrefValue.indexOf(_contextPath) != 0) {
        options.href = _contextPath + hrefValue;
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
    GlobalDialogUtils.openModalDialog("autoRedirectToLoginPage","登录超时",_contextPath + "/mainframe/toLogin.action");
    return true;
});
//系统自动跳转到登录页面
$.bindge("returnLoginPage",function(event){
    window.location.href = _contextPath + "/mainframe/toLogin.action";
});


/**
 * 首页更换皮肤方法 
 */
function changeThemeFun(themeName) {
    if ($.cookie('easyuiThemeName')) {
        $('#layout_north_pfMenu').menu('setIcon', {
            target : $('#layout_north_pfMenu div[title=' + $.cookie('easyuiThemeName') + ']')[0],
            iconCls : 'emptyIcon'
        });
    }
    $('#layout_north_pfMenu').menu('setIcon', {
        target : $('#layout_north_pfMenu div[title=' + themeName + ']')[0],
        iconCls : 'tick'
    });

    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    $easyuiTheme.attr('href', href);

    var $iframe = $('iframe');
    if ($iframe.length > 0) {
        for (var i = 0; i < $iframe.length; i++) {
            var ifr = $iframe[i];
            try {
                $(ifr).contents().find('#easyuiTheme').attr('href', href);
            } catch (e) {
                try {
                    ifr.contentWindow.document.getElementById('easyuiTheme').href = href;
                } catch (e) {
                }
            }
        }
    }

    $.cookie('easyuiThemeName', themeName, {
        expires : 7
    });

};
/**
 * 菜单控件 
 */
$(function(){
    //菜单初始化方法
    var Menu = function(options){
        var _this = this;
        _this.options = $.extend(_this.options,{
            url: _queryMenuUrl,
            onSelected: null
        },options);
        this._init();
    };
    Menu.prototype.options = {};
    Menu.prototype.menuItemMapping = {};
    Menu.prototype.$menuAccordion = $("#menuAccordion");
    Menu.prototype._init = function(){
        //加载菜单
        var _this = this;
        _this.menuItemMapping = {};
        _this.$menuAccordion.accordion({
            fit : true,
            border : false,
            onSelect : function(title, index) {
                //得到选中的面板
                if (_this.menuItemMapping && _this.menuItemMapping[title]) {
                    var menuItem = _this.menuItemMapping[title];
                    if (_this.options && _this.options.onSelected && $.isFunction(_this.options.onSelected)) {
                        _this.options.onSelected.call(_this, menuItem);
                    }
                    var selectedPanel = _this.$menuAccordion.accordion('getPanel',title);
                    if(!selectedPanel._isInit){
                        var $menuTree = $('<ul name="menuTree"></ul>');
                        //var $treeContentDiv = $('<div title="系统菜单" style="padding: 5px;"></div>');
                        var $treeParent = $('<div class="well well-small"></div>');
                        $treeParent.append($menuTree);
                        selectedPanel.css({"padding-left":"5px","padding-right":"5px","padding-top":"3px"}).append($treeParent);
                        if(menuItem.childs && menuItem.childs.length > 0){
                            $menuTree.tree({
                            	idField: 'id',
                            	childrenField: 'childs',
                            	iconField: function(row){
                                    if($.ObjectUtils.isEmpty(row.childs)){
                                        return "database";
                                    }else{
                                        return "database_gear";
                                    }
                            	},
                                data: menuItem.childs,
                                onClick: function(node){
                                    if (_this.options && _this.options.onSelected && $.isFunction(_this.options.onSelected)) {
                                        _this.options.onSelected.call(_this, node.attributes);
                                    }
                                }
                            });
                        }
                        selectedPanel._isInit = true;
                    }
                }
                
                return false;
            }
        }); 

        this._loadMenuItem();
    };
    //重新加载菜单
    Menu.prototype.reload = function(){
        var _this = this;
        //取得父级对象
        var $menuAccordionParent = _this.$menuAccordion.parent();
        //销毁当前对象
        _this.$menuAccordion.remove();
        _this.$menuAccordion = $('<div id="menuAccordion"></div>');
        _this.$menuAccordion.prependTo($menuAccordionParent);

        //加载菜单
        this._init();
    };
    //加载菜单项
    Menu.prototype._createMenuItemContent = function(menuItem){
        var $ulEl = '<ul name="menuTree"></ul>';
        //$ulEl.
    };
    Menu.prototype._loadMenuItem = function(){
        var _this = this;
        $.post(_this.options.url, function(data) {
            if ($.ObjectUtils.isEmpty(data)) {
                return false;
            }
            /** 加载主菜单 */
            $.each(data, function(index, menuItem) {
                //有效菜单项
                _this.$menuAccordion.accordion('add', {
                    title : menuItem.text,
                    closable : false,
                    selected : index == 0 ? true : false,
                    content : '',
                    iconCls: $.ObjectUtils.isEmpty(menuItem.icon) ? "database_gear" :  menuItem.icon
                });
                //如果菜单项无效，或不可见，均不进行显示<br/>
                _this.menuItemMapping[menuItem.text] = menuItem;
            });

            var pannels = _this.$menuAccordion.accordion("panels");
        });
    };    
    
    /**
     * 
     * @param {Object} menuItem
     */
    function onSelectedMenuItem(menuItem){
        if(menuItem.target == "mainTabs"){
            if($.ObjectUtils.isEmpty(menuItem.href)){
                return false;
            }
            $.triggerGE("addOrSelectTab",[{
                title : menuItem.text,
                href : menuItem.href,
                iconCls : menuItem.icon
            }]);
            return false;
        }else if(menuItem.target == "triggerGlobalEvent"){
            $.triggerge(menuItem.eventType,[menuItem]);
            return false;
        }else if(menuItem.target == "openDialog"){
            if(menuItem.isModal){
                //打开模态dialog
            }else{
                //打开非模态对话框
            }
        }else{
            if($.ObjectUtils.isEmpty(menuItem.href)){
                return false;
            }
        	//系统默认为打开对应tab
        	$.triggerGE("addOrSelectTab",[{
                title : menuItem.text,
                href : menuItem.href,
                iconCls : menuItem.icon
            }]);
            return false;
        }
    }
    $(document).ready(function(){
        var menu = new Menu({
            url: _queryMenuUrl,
            onSelected: function(menuItem){
            	//alert(menuItem['attributes']	);
                onSelectedMenuItem(menuItem);
            }
        });
        $.bindGE('reloadMenus', function(event) {
            menu.reload();
        });
    });    
});

