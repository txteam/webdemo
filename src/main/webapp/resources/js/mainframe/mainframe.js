/*
 * 定义布局器
 */
var Layout = function(){
    this.$center = $(".center");
    this.$centerMain = $(".center-main");
    this.$top = $(".top");
    this.$footer = $(".footer");
};
/*
 * 布局器初始化
 */
Layout.prototype.init = function(config){
    this._resetCenterHeight();
    this._initSpliter();
    var resizeTimer = null;
    var _this = this;
    $(window).resize(function(){
        if(resizeTimer != null){
            clearTimeout(resizeTimer,100);
        }
        resizeTimer = setTimeout(function(){
            _this._resetCenterHeight();
            _this.$center.wijsplitter("refresh");
            _this.$centerMain.wijsplitter("refresh");
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
 * public: centerMain容器句柄
 */
Layout.prototype.$centerMain = null;
/*
 * 设置layout高度
 */
Layout.prototype._resetCenterHeight = function(){
    var $document = $(document),
        _dh = $document.innerHeight(),
        _th = this.$top.outerHeight(),
        _fh = this.$footer.outerHeight(),
        _ch = _dh - _th - _fh;
    this.$center.height(_ch);
};
/*
 * private: 初始化spliter
 */
Layout.prototype._initSpliter = function() {
    this.$center.wijsplitter({
        panel1 : {collapsed : true},
        orientation : "vertical",
        fullSplit : false,
        expanded : function(e) {
            this.$centerMain.wijsplitter("refresh");
        },
        collapsed : function(e) {
            this.$centerMain.wijsplitter("refresh");
        },
        sized : function(e) {
            this.$centerMain.wijsplitter("refresh");
        }
    });
    this.$centerMain.wijsplitter({
        orientation : "horizontal",
        collapsingPanel: "panel2",
        panel2:{collapsed: true},
        fullSplit : true
    });
};