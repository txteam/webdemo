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
    this._initMainFrame();
};
/*
 * public: top容器句柄
 */
Layout.prototype.$top = null;
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
    var $document = $(document);
    var _dh = $document.innerHeight();
    var _th = this.$top.outerHeight();
    var _ch = _dh - _th;
    this._centerHeight = _ch;
    this._centerWidth = $document.innerWidth();
    this.$center.height(_ch);
    this.$centerLeft.height(_ch);
    this.$centerMain.height(_ch);
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
        fullSplit : true
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
    _this._accordingHeight = _this._centerHeight - (_this.$centerLeft.find("h3").size() * 32);
    _this._accordingWidth = _this.$centerLeft.innerWidth();
    if (_this._accordingHeight < 0) {
        _this._accordingHeight = 0;
    }
    var $customerMenuAccordion = this.$centerLeft.wijaccordion({
        header : "h3",
        selectedIndexChanged : function() {
            if(_this.$centerLeft.find("li>div:visible").height() > _this._accordingHeight){
                 _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
            }
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
            if(_this.$centerLeft.find("li>div:visible").height() > _this._accordingHeight + 10){
                 _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight - 10);
            }
            _this.$centerLeft.trigger("accordionResized");
        }, 100);
        
        event.stopPropagation();
        event.preventDefault();
        return false;
    });
    
    if(_this.$centerLeft.find("li>div").eq(0).innerHeight() > _this._accordingHeight){
        _this.$centerLeft.find("li>div").eq(0).height(_this._accordingHeight);
    }
    
 
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
    $(".center-left-menu").menu();
    if($(".center-left-menu:visible").height() > _this._accordingHeight + 10){
        $(".center-left-menu:visible").height(_this._accordingHeight - 10);
    }
    $(".center-left-menu:visible").width(_this._accordingWidth - 10);
    
    this.$centerLeft.bind("accordionResized",function(){
        if($(".center-left-menu:visible").height() > _this._accordingHeight + 10){
            $(".center-left-menu:visible").height(_this._accordingHeight - 10);
        }
        $(".center-left-menu:visible").width(_this._accordingWidth - 10);
    });
    this.$centerLeft.wijaccordion({
        selectedIndexChanged : function() {
            if($(".center-left-menu:visible").height() > _this._accordingHeight + 10){
                $(".center-left-menu:visible").height(_this._accordingHeight - 10);
            }
            $(".center-left-menu:visible").width(_this._accordingWidth - 10);
        }
    });
}
/*
 * private init mainTabs
 */
Layout.prototype._initMainFrame = function() {
    var _this = this;
    
    _this.$centerMain.bind("centerMainResize",function(event){
        var _height = _this.$centerMain.innerHeight();
        var _width = _this.$centerMain.innerWidth();
        var $iframe = _this.$centerMain.find("#main");
        $iframe.attr("height",_height);
        $iframe.attr("width",_width);
        
        event.stopPropagation();
        event.preventDefault();
        return false;
    });
    _this.$centerMain.trigger("centerMainResize");
    
    _this.$center.bind("centerSpliterResized",function(event){
        _this.$centerLeft.trigger("centerMainResize");
    });
};