/*
 * 定义布局器
 */
var Layout = function(){
    this.leftCenterDefaultWidth = 230;
    this.$top = $(".top");
    this.$footer = $(".footer");
    
    this.$center = $(".center");
    this.$centerLeft = $(".center-left");
    this.$centerMain = $(".center-main");
};
/*
 * 布局器初始化
 */
Layout.prototype.init = function(config){
    this._resetCenterHeight();
    this._initSpliter();
    var resizeTimer = null;
    var _this = this;
    _this._accordingHeight = _this.$centerLeft.innerHeight() - (_this.$centerLeft.find("h3").size() * 30) - 25;
    if(_this._accordingHeight < 0){
        _this._accordingHeight = 0;
    }
    $(window).resize(function(){
        if(resizeTimer != null){
            clearTimeout(resizeTimer,100);
        }
        resizeTimer = setTimeout(function(){
            _this._resetCenterHeight();
            _this.$center.wijsplitter("refresh");
            _this.$centerMain.wijsplitter("refresh");
            
            _this._accordingHeight = _this.$centerLeft.innerHeight() - (_this.$centerLeft.find("h3").size() * 30) - 25;
            if(_this._accordingHeight < 0){
                _this._accordingHeight = 0;
            }
            _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
        },100);
    });
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
 * according的高度
 */
Layout.prototype._accordingHeight = 0;
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
    this.$center.wijsplitter({
        //showExpander: true,
        panel1 : {collapsed : true,scrollBars:"hidden", minSize:_this.leftCenterDefaultWidth - 1},
        panel2 : {scrollBars:"hidden",minSize:_this._centerWidth - _this.leftCenterDefaultWidth - 5},
        orientation : "vertical",
        splitterDistance: _this.leftCenterDefaultWidth,
        resizeSettings:{ghost :true},
        fullSplit : false,
        expanded : function(e) {
            _this.$centerMain.wijsplitter("refresh");
            _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
        },
        collapsed : function(e) {
            _this.$centerMain.wijsplitter("refresh");
        },
        sized : function(e) {
            _this.$centerMain.wijsplitter("refresh");
        }
    });
    this.$centerMain.wijsplitter({
        panel1 : {scrollBars:"hidden"},
        panel2 : {scrollBars:"hidden"},
        splitterDistance:_this._centerHeight * 3/5,
        orientation : "horizontal",
        collapsingPanel: "panel2",
        panel2:{collapsed: true},
        fullSplit : true
    });
    this._initCenterAccordion();
};
/*
 *private init
 */
Layout.prototype._initCenterAccordion = function() {
    var _this = this;
    this.$centerLeft.wijaccordion({
        header: "h3",
        selectedIndexChanged :function(){
            _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
        }
    });
    _this.$centerLeft.find("li>div:visible").height(_this._accordingHeight);
};