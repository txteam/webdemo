/*
 * 定义布局器
 */
var Layout = function(config) {
    this.leftCenterDefaultWidth = 180;
    this.$top = $(".top");
    this.$center = $(".center");

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
 * according的高度
 */
Layout.prototype._accordingWidth = 0;
/*
 * 设置layout高度
 */
Layout.prototype._resetCenterHeight = function() {
    var $document = $(document), _dh = $document.innerHeight(), _th = this.$top.outerHeight(), _fh = this.$footer.outerHeight(), _ch = _dh - _th - _fh;
    this._centerHeight = _ch;
    this._centerWidth = $document.innerWidth();
    this.$center.height(_ch);
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
        _this.$center.wijsplitter("refresh");
        //触发客户自定义菜单的resize事件
        _this.$center.trigger("centerSpliterResized");
        
        _this.$centerLeft.next("div.ui-resizable-handle").mousedown(function() {
            return false;
        });
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
        resizeSettings: { ghost :false},
        splitterDistance : _this._centerHeight * 3 / 5,
        orientation : "horizontal",
        collapsingPanel : "panel2",
        fullSplit : true
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
    _this._accordingHeight = _this.$centerLeft.innerHeight() - (_this.$centerLeft.find("h3").size() * 32);
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
            _this._accordingHeight = _this.$centerLeft.innerHeight() - (_this.$centerLeft.find("h3").size() * 32);
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
    $customerMenuAccordion.trigger("accordionResize");
    
    
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
    
    $mainTabs.bind("tabsResize",function(event){
        event.stopPropagation();
        event.preventDefault();
        
        var $visiblePanel = _this.$centerMainTabs.find("div[roleType=tabpanel]:visible");
        var _height = $visiblePanel.innerHeight();
        var _width = $visiblePanel.innerWidth();
        $.each(_this.$centerMainTabs.find("div[roleType=tabpanel]"),function(index,div){
            
            if($(div).children("iframe").size() > 0){
                var $iframe = $(div).children("iframe")
                $iframe.attr("height",_height);
                $iframe.attr("width",_width);
            }
        });
        return false;
    });
    $mainTabs.trigger("tabsResize");
    
    _this.$centerMain.bind("centerMainSpliterResized",function(event){
        $mainTabs.trigger("tabsResized");
    });
    this.$centerMain.wijsplitter({
        expanded : function(e) {
            $mainTabs.trigger("tabsResize");
        },
        collapsed : function(e) {
            $mainTabs.trigger("tabsResize");
        },
        sized : function(e) {
            $mainTabs.trigger("tabsResize");
        }
    });
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
};
Menu.prototype.menuResize = function(width, height) {
    this._$menuContainer.height(height);
    this._$menuContainer.width(width);
};


$.widget("tx.styleswitch", {
    options : {
        styles : []
    },

    _create : function() {
        var progress = this.options.value + "%";
        this.element.addClass("progressbar").text(progress);
    },

    _init : function() {

    },

    destroy : function() {

    }
});