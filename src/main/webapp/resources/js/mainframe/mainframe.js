/*
 * 定义布局器
 */
var Layout = function(config){
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
Layout.prototype.init = function(){
    this._resetCenterHeight();
    this._initSpliter();
    var resizeTimer = null;
    var _this = this;
    $(window).resize(function(){
        if(resizeTimer != null){
            clearTimeout(resizeTimer);
        }
        resizeTimer = setTimeout(function(){
            _this._resetCenterHeight();
            
            _this.$centerSpliter.trigger("refresh");
            _this.$centerMainSpliter.trigger("refresh");
            
        },100);
    });
};
/*
 *  Accordion句柄
 */
Layout.prototype.$customerMenuAccordion = null;
/*
 *  Accordion句柄
 */
Layout.prototype.$centerSpliter = null;
/*
 *  Accordion句柄
 */
Layout.prototype.$centerMainSpliter = null;
/*
 *  Accordion句柄
 */
Layout.prototype.$mainTabs = null;


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
Layout.prototype._resetCenterHeight = function(){
    var $document = $(document),
        _dh = $document.innerHeight(),
        _th = this.$top.outerHeight(),
        _fh = this.$footer.outerHeight(),
        _ch = _dh - _th - _fh;
    this._centerHeight = _ch;
    this._centerWidth = $document.innerWidth();
    this.$center.height(_ch);
};
/*
 * private: 初始化spliter
 */
Layout.prototype._initSpliter = function() {
    var _this = this;
    this.$centerSpliter = this.$center.wijsplitter({
        //showExpander: true,
        panel1 : {collapsed : true,scrollBars:"hidden"},
        panel2 : {scrollBars:"hidden",minSize:_this._centerWidth - _this.leftCenterDefaultWidth - 5},
        orientation : "vertical",
        splitterDistance: _this.leftCenterDefaultWidth,
        resizeSettings:{ghost :true},
        fullSplit : false,
        expanded : function(e) {
            _this.$centerMainSpliter.trigger("refresh");
            _this.$customerMenuAccordion.trigger("accordionResize");
        },
        collapsed : function(e) {
            _this.$centerMainSpliter.trigger("refresh");
        },
        sized : function(e) {
            _this.$centerMainSpliter.trigger("refresh");
            _this.$customerMenuAccordion.trigger("accordionResize");
        }
    });
    this.$centerSpliter.bind("refresh",function(){
        _this.$center.wijsplitter("refresh");
        
        //触发客户自定义菜单的resize事件
        _this.$customerMenuAccordion.trigger("accordionResize");
    });
    this.$centerMainSpliter = this.$centerMain.wijsplitter({
        panel1 : {scrollBars:"hidden"},
        panel2 : {scrollBars:"hidden",collapsed: true},
        splitterDistance:_this._centerHeight * 3/5,
        orientation : "horizontal",
        collapsingPanel: "panel2",
        fullSplit : true,
        expanded : function(e) {
            alert("expanded");
        },
        collapsed : function(e) {
            alert("collapsed");
        },
        sized : function(e) {
            alert("sized");
        }
    });
    this.$centerMainSpliter.bind("refresh",function(){
        _this.$centerMain.wijsplitter("refresh");
    });
    this._initCenterAccordion();
};
/*
 *private init customerMenuAccording
 */
Layout.prototype._initCenterAccordion = function() {
    var _this = this;
    _this._accordingHeight = _this.$centerLeft.innerHeight() - (_this.$centerLeft.find("h3").size() * 32);
    _this._accordingWidth = _this.$centerLeft.innerWidth();
    if(_this._accordingHeight < 0){
        _this._accordingHeight = 0;
    }
    this.$customerMenuAccordion = this.$centerLeft.wijaccordion({
        header: "h3",
        selectedIndexChanged :function(){
            _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
        }
    });
    _this.$centerLeft.find("li>div").eq(0).height(_this._accordingHeight);
    _this.$customerMenuAccordion.trigger("accordionResize");
    
    var resizeTimer = null;
    _this.$customerMenuAccordion.bind("accordionResize",function(){
        if(resizeTimer != null){
            clearTimeout(resizeTimer);
        }
        resizeTimer = setTimeout(function(){
            _this._accordingHeight = _this.$centerLeft.innerHeight() - (_this.$centerLeft.find("h3").size() * 32);
            _this._accordingWidth = _this.$centerLeft.innerWidth();
            if(_this._accordingHeight < 0){
                _this._accordingHeight = 0;
            }
            _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
            _this.$customerMenuAccordion.trigger("accordionResized");
        },100);
    });
};
/*
 * private init mainTabs
 */
Layout.prototype._initMainTabs = function(){
    //var _this = this;
    this.$mainTabs = this.$centerMainTabs.wijtabs();
    
    var resizeTimer = null;
    this.$mainTabs.bind("tabsResize",function(){
        if(resizeTimer != null){
            clearTimeout(resizeTimer);
        }
        resizeTimer = setTimeout(function(){
            
        },100);
    });
    function setIframeInTabPanelSize(){
        //this.$centerMainTabs.
        //tabpanel
    }
};

/*
 * 定义菜单对象
 */
var Menu = function(config){
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
Menu.prototype._init = function(){
    var _this = this;
    this._$menuContainer.height(this._initHeight);
    this.$menu = this._$menuContainer.menu();
    this._$menuContainer.sortable({
        items: "li:not(.placeholder)",
        sort: function() {
            $(this).removeClass( "ui-state-default" );
        }
    });
    this._$menuContainer.droppable({
        activeClass: "ui-state-default",
        hoverClass: "ui-state-hover",
        accept: ":not(.ui-sortable-helper)",
        drop: function( event, ui ) {
            //alert("drop:" + ui.draggable.text());
            //alert(ui.draggable.html());
            $(this).find( ".placeholder" ).remove();
            $("<li></li>").html( ui.draggable.html()).appendTo( this );
            _this._$menuContainer.menu("refresh");
        }
    });
};
Menu.prototype.menuResize = function(width,height){
    this._$menuContainer.height(height);
    this._$menuContainer.width(width);
};


