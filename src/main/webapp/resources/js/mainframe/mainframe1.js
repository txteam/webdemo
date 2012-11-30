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
 * private: center的高度
 */
Layout.prototype._centerHeight = 0;
/*
 * private: center的宽度
 */
Layout.prototype._centerWidth = 0;
/*
 * 设置layout高度
 */
Layout.prototype._resetCenterHeight = function() {
    var $document = $(document);
    var _dh = $document.innerHeight();
    var _th = this.$top.outerHeight();
    var _ch = _dh - _th;
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
            scrollBars : "auto"
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
 *private init customerMenuAccording
 */
Layout.prototype._initCenterAccordion = function() {
    var _this = this;
    var $customerMenuAccordion = this.$centerLeft.wijaccordion({
        header : "h3"
    });
    //初始化菜单
    this._initLCMenu();
};
/*
 * 初始化菜单
 */
Layout.prototype._initLCMenu = function(){
    var _this = this;
    _this.$centerLeft.find("li > div").menu();
    
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