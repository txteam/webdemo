/*
 * 定义菜单对象
 * config{id:"menuItemsContainerId"}
 */
var Menu = function(config)
{
    // 获取参数
    this.menuBtnId = config.menuBtnId ? config.menuBtnId : this.menuBtnId;
    this.menuItemsContainerId = config.menuItemsContainerId ? config.menuItemsContainerId
            : this.menuItemsContainerId;

    this.init();
};
/*
 * param: 菜单按钮id
 */
Menu.prototype.menuBtnId = "menuBtn";
/*
 * param: 菜单对象对应容器id
 */
Menu.prototype.menuItemsContainerId = "menuItmesContainer";
/*
 * public: 菜单对象容器jquery对象
 */
Menu.prototype.$menuBtn = null;
/*
 * public: 菜单对象容器jquery对象
 */
Menu.prototype.$menuItemsContainer = null;
/*
 * public: 菜单对象jquery对象
 */
Menu.prototype.$menuItems = null;
/*
 * private: 关闭延时器
 */
Menu.prototype._closeTimer = null;
/*
 * 菜单对象初始化方法
 */
Menu.prototype.init = function()
{
    this.$menuBtn = jQuery("#" + this.menuBtnId);
    this.$menuItemsContainer = jQuery("#" + this.menuItemsContainerId);
    this.$menuItems = this.$menuItemsContainer.children("ul");

    // 初始化菜单按钮
    this._menuBtnInit();
    // 初始化容器
    this._menuContainerInit();
};
/*
 * 私有方法清除关闭句柄
 */
Menu.prototype._clearTimer = function()
{
    var _this = this;
    if (_this._closeTimer != null)
    {
        clearTimeout(_this._closeTimer);
    }
};
/*
 * 私有方法显示
 */
Menu.prototype._show = function()
{
    var _this = this;
    if (_this._closeTimer != null)
    {
        clearTimeout(_this._closeTimer);
    }
    _this.$menuItemsContainer.show();
    _this._hide();
};
/*
 * 私有方法隐藏
 */
Menu.prototype._hide = function()
{
    var _this = this;
    if (_this._closeTimer != null)
    {
        clearTimeout(_this._closeTimer);
    }
    this._closeTimer = setTimeout(function()
    {
        _this.$menuItemsContainer.hide();
    }, 1000);
}
/*
 * 菜单初始化
 */
Menu.prototype._menuBtnInit = function()
{
    var _this = this;
    this.$menuBtn.button(
    {
        icons :
        {
            secondary : "ui-icon-triangle-1-s"
        }
    });

    this.$menuBtn.click(function(event)
    {
        // 这里this作用域发生了变化，所以不能用this
        _this._show();
        event.preventDefault();
        event.stopPropagation();
    });
};
/*
 * 容器初始化
 */
Menu.prototype._menuContainerInit = function()
{
    var _this = this;
    this.$menuItems.wijmenu(
    {
        orientation : "vertical",
        blur : function()
        {
            _this._hide();
        },
        focus : function()
        {
            _this._clearTimer();
        },
        select : function()
        {
            _this.$menuItemsContainer.hide();
        }
    });

    this.$menuItemsContainer.hover(function()
    {
        if (_this._closeTimer != null)
        {
            clearTimeout(_this._closeTimer);
        }
    }, function()
    {
        if (_this._closeTimer != null)
        {
            clearTimeout(_this._closeTimer);
        }
        this._closeTimer = setTimeout(function()
        {
            _this.$menuItemsContainer.hide();
        }, 500);
    });
};
