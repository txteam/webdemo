/*
 * 定义布局器
 */
var Layout = function(config) {
    this.leftCenterDefaultWidth = 180;
    this.$top = $(".top");
    this.$footer = $(".footer");
    this.$center = $(".center");

    this.$centerLeft = $(".center-left");
    this.$centerMain = $(".center-main");
    this.$centerMainTabs = $("#mainTabs");

    this.init();
    return this;
};
/*
 * 布局器初始化
 */
Layout.prototype.init = function() {
    this._resetCenterHeight();

    //初始化页面布局分割
    this._initCenterSpliter();
    this._initCenterMainSpliter();
    
    //初始化left页面部分according
    this._initCenterAccordion();
    
    //初始化
    //this._initCenterAccordion();
};
/*
 * public: top容器句柄
 */
Layout.prototype.$top = null;
/*
 * public: footer容器句柄
 */
Layout.prototype.$footer = null;
/*
 * public: center容器句柄
 */
Layout.prototype.$center = null;
/*
 * public: centerLeft容器句柄
 */
Layout.prototype.$centerLeft = null;
/*
 * public: centerMain容器句柄
 */
Layout.prototype.$centerMain = null;
/*
 * public: center中mainTabs的句柄
 */
Layout.prototype.$centerMainTabs = null;
/*
 * private: center的高度
 */
Layout.prototype._centerHeight = 0;
/*
 * private: center的宽度
 */
Layout.prototype._centerWidth = 0;
/*
 * according的高度
 */
Layout.prototype._accordingHeight = 0;
/*
 * according的宽度
 */
Layout.prototype._accordingWidth = 0;
/*
 * according的高度
 */
Layout.prototype._tabPanelHeight = 0;
/*
 * according的宽度
 */
Layout.prototype._tabPanelWidth = 0;
/*
 * 设置layout高度
 */
Layout.prototype._resetCenterHeight = function() {
    var $document = $(document);
    var  _dh = $document.innerHeight();
    var _th = this.$top.outerHeight();
    var _fh = this.$footer.outerHeight();
    var _ch = _dh - _th - _fh;
    this._centerHeight = _ch;
    this._centerWidth = $document.innerWidth();
    this.$center.height(_ch);
    this.$center.width(this._centerWidth);
    
    this.$centerLeft.height(this._centerHeight);
    this.$centerMain.height(this._centerHeight);
};
/*
 * 初始化主splite(center元素上的spliter)
 */
Layout.prototype._initCenterSpliter = function() {
    var _this = this;
    var $centerSpliter = this.$center.wijsplitter({
        //showExpander: true,
        barZIndex: 999,
        panel1 : {
            collapsed : true,
            scrollBars : "hidden"
        },
        panel2 : {
            scrollBars : "hidden"
        },
        orientation : "vertical",
        splitterDistance : _this.leftCenterDefaultWidth,
        sizing: function(){
            return false;
        },
        fullSplit : false
    });
    $centerSpliter.bind("refresh", function(event) {
        //这里由于已经调用了refresh方法，所以不会发生事件重复绑定的问题，这里不会出现重复绑定
        _this.$center.wijsplitter("refresh");
        _this.$centerLeft.next("div.ui-resizable-handle").mousedown(function() {
            return false;
        });
        
        //触发客户自定义菜单的resize事件
        _this.$center.trigger("centerSpliterResized");
        event.stopPropagation();
        event.preventDefault();
        return false;
    });
    var resizeTimer = null;
    $(window).resize(function() {
        if (resizeTimer != null) {
            clearTimeout(resizeTimer);
        }
        resizeTimer = setTimeout(function() {
            _this._resetCenterHeight();
            $centerSpliter.trigger("refresh");
        }, 100);
    });
    //阻止左边边线的mousedown事件不再发生resize
    _this.$centerLeft.next("div.ui-resizable-handle").mousedown(function() {
        return false;
    });
};
/*
 * private: 初始化main上的spliter
 */
Layout.prototype._initCenterMainSpliter = function() {
    var _this = this;
    $centerMainSpliter = this.$centerMain.wijsplitter({
        barZIndex: 998,
        panel1 : {
            scrollBars : "hidden"
        },
        panel2 : {
            scrollBars : "hidden",
            collapsed : true
        },
        resizeSettings: { ghost:false},
        splitterDistance : _this._centerHeight * 3 / 5,
        orientation : "horizontal",
        collapsingPanel : "panel2",
        fullSplit : false
    });
    $centerMainSpliter.bind("refresh", function(event) {
        _this.$centerMain.wijsplitter("refresh");
        _this.$centerMain.trigger("centerMainSpliterResized");

        event.stopPropagation();
        event.preventDefault();
        return false;
    });
    _this.$center.bind("centerSpliterResized",function(event) {
        $centerMainSpliter.trigger("refresh");
    });
    this.$center.wijsplitter({
        expanded : function(e) {
            $centerMainSpliter.trigger("refresh");
        },
        collapsed : function(e) {
            $centerMainSpliter.trigger("refresh");
        },
        sized : function(e) {
            $centerMainSpliter.trigger("refresh");
        }
    });
    this._initMainTabs();
};
/*
 *private init customerMenuAccording
 */
Layout.prototype._initCenterAccordion = function() {
    var _this = this;
    _this._accordingHeight = _this._centerHeight - (_this.$centerLeft.find("h3").size() * 32);
    _this._accordingWidth = _this.$centerLeft.innerWidth();
    if (_this._accordingHeight < 0) {
        _this._accordingHeight = 0;
    }
    var $customerMenuAccordion = this.$centerLeft.wijaccordion({
        header : "h3",
        selectedIndexChanged : function() {
            _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
        }
    });
    
    var resizeTimer = null;
    $customerMenuAccordion.bind("accordionResize",function(event){
        if (resizeTimer != null) {
            clearTimeout(resizeTimer);
        }
        resizeTimer = setTimeout(function() {
            _this._accordingHeight = _this._centerHeight - (_this.$centerLeft.find("h3").size() * 32);
            _this._accordingWidth = _this.$centerLeft.innerWidth();
            if (_this._accordingHeight < 0) {
                _this._accordingHeight = 0;
            }
            _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
            _this.$centerLeft.trigger("accordionResized");
        }, 100);
        
        event.stopPropagation();
        event.preventDefault();
        return false;
    });
    _this.$centerLeft.find("li>div").eq(0).height(_this._accordingHeight);
    //$customerMenuAccordion.trigger("accordionResize");
    
    _this.$center.bind("centerSpliterResized", function(event) {
        $customerMenuAccordion.trigger("accordionResize");
    });
    this.$center.wijsplitter({
        expanded : function(e) {
            $customerMenuAccordion.trigger("accordionResize");
        },
        sized : function(e) {
            $customerMenuAccordion.trigger("accordionResize");
        }
    });
    
    //初始化菜单
    this._initLCMenu();
};
/*
 * 初始化菜单
 */
Layout.prototype._initLCMenu = function(){
    var _this = this;
    var customerMenu = new Menu({
        $menu:$("#customer-menus"),
        initHeight:_this._accordingHeight - 10
    });
    this.$centerLeft.bind("accordionResized",function(){
        customerMenu.menuResize(_this._accordingWidth - 10,_this._accordingHeight - 10);
    });
}
/*
 * private init mainTabs
 */
Layout.prototype._initMainTabs = function() {
    var _this = this;
    this.$centerMainTabs.children("div").attr("roleType","tabpanel");
    var $mainTabs = this.$centerMainTabs.wijtabs();
    
    _this._tabPanelHeight = _this.$centerMainTabs.height() - 35;
    _this._tabPanelWidth = _this.$centerMainTabs.width();
    _this.$centerMainTabs.find("div[roleType=tabpanel]").height(_this._tabPanelHeight);
    _this.$centerMainTabs.find("div[roleType=tabpanel]").width(_this._tabPanelWidth);
    
    //centerMainResize响应页面大小变化
    $mainTabs.bind("centerMainResize",function(event){
         _this._tabPanelHeight = _this.$centerMainTabs.height() - 35;
        _this._tabPanelWidth = _this.$centerMainTabs.width();
        
        $.each(_this.$centerMainTabs.find("div[roleType=tabpanel]"),function(index,div){
            if($(div).children("iframe").size() > 0){
                var $iframe = $(div).children("iframe");
                $iframe.attr("height",_this._tabPanelHeight);
                $iframe.attr("width",_this._tabPanelWidth);
            }
        });
        
        event.stopPropagation();
        event.preventDefault();
        return false;
    });
    $mainTabs.trigger("centerMainResize");
    
    //委托给tab添加remove事件
    $("li>span.ui-icon-close",_this.$centerMainTabs).live("click",function(){
    	var index = $("li", _this.$centerMainTabs).index($(this).parent());
    	_this.$centerMainTabs.wijtabs('remove', index);
    });
    
    //给tab添加添加事件
    var defaultTabOption = {
    	id : "",
    	label : "",
    	isIframe : true,
    	iframeHref : "",
    	content : ""
    };
    var newTabOption = defaultTabOption;
    _this.$centerMainTabs.wijtabs({
    	tabTemplate: '<li><a href="#{href}">#{label}</a><span class="ui-icon ui-icon-close"></span></li>',
    	add : function (event, ui) {
        	$(ui.tab).attr('id',"mainTabs_" + newTabOption.id);
        	if(newTabOption.isIframe){
        		_this._tabPanelHeight = _this.$centerMainTabs.height() - 35;
        	    _this._tabPanelWidth = _this.$centerMainTabs.width();
        		
        		$iframe = $('<iframe src="' + newTabOption.iframeHref + '" scrolling="auto"></iframe>');
                $iframe.attr("height",_this._tabPanelHeight);
                $iframe.attr("width",_this._tabPanelWidth);
        		$(ui.panel).append($iframe);
        	}else{
        		$(ui.panel).append(newTabOption.content);
        	}
        	$(ui.panel).attr("roleType","tabpanel");
        	_this.$centerMainTabs.wijtabs('select',ui.index);
        }
    });
    _this.$centerMainTabs.bind("addTab",function(event,option){
    	newTabOption = $.extend({}, defaultTabOption, option);
    	//查看对应id的tab是否已经存在
    	var $uitab = $("#mainTabs_" + newTabOption.id, _this.$centerMainTabs);
    	if($uitab.size()>0){
    		var index = $("li", _this.$centerMainTabs).index($uitab.parent());
    		_this.$centerMainTabs.wijtabs('select',index);
    	}else{
    		_this.$centerMainTabs.wijtabs('add','#mainTabs-' + newTabOption.id, newTabOption.label);	
    	}
    });
    
    _this.$centerMain.bind("centerMainSpliterResized",function(event){
        $mainTabs.trigger("centerMainResize");
    });
    this.$centerMain.wijsplitter({
        expanded : function(e) {
            $mainTabs.trigger("centerMainResize");
        },
        collapsed : function(e) {
            $mainTabs.trigger("centerMainResize");
        },
        sized : function(e) {
            $mainTabs.trigger("centerMainResize");
        }
    });
};

//定义菜单点击响应函数
var menuClickFunction = function(){
	var $a = $(this);
	var $li = $(this).parent();
	$("#mainTabs").trigger("addTab",{
    	id : $li.attr("id"),
    	label : $a.text(),
    	//isIframe : true,
    	//content : "",
    	iframeHref : $a.attr("href")	
	});
	return false;
};

/*
 * 定义菜单对象
 */
var Menu = function(config) {
    this._$menuContainer = config.$menu;
    this._initHeight = config.initHeight;
    this._init();
    return this;
};
/* 初始化高度 */
Menu.prototype.$menu = 100;
/* 菜单容器句柄 */
Menu.prototype._$menuContainer = null;
/* 初始化高度 */
Menu.prototype._initHeight = 100;
/* 初始化方法 */
Menu.prototype._init = function() {
    var _this = this;
    this._$menuContainer.height(this._initHeight);
    this.$menu = this._$menuContainer.menu();
    this._$menuContainer.sortable({
        items : "li:not(.placeholder)",
        sort : function() {
            $(this).removeClass("ui-state-default");
        }
    });
    this._$menuContainer.droppable({
        activeClass : "ui-state-default",
        hoverClass : "ui-state-hover",
        accept : ":not(.ui-sortable-helper)",
        drop : function(event, ui) {
            $(this).find(".placeholder").remove();
            $("<li></li>").html(ui.draggable.html()).appendTo(this);
            _this._$menuContainer.menu("refresh");
        }
    });
    
    this._$menuContainer.find("li > a").live("click",menuClickFunction);
};
Menu.prototype.menuResize = function(width, height) {
    this._$menuContainer.height(height);
    this._$menuContainer.width(width);
};
