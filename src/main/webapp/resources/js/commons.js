$(document).ready(function() {
	$(".header").prepend('<span class="ui-icon ui-icon-gear" style="float:left;"></span>');
	$(".header").addClass("ui-state-default ui-corner-all ui-tabs-selected ui-state-active page-title-header");
	
	
});
/************ EventManager end ************** */
/**
 * 客户端统一事件管理器
 */
(function($, undefined)
{
    /**
     * 定义全局事件管理器
     */
    var _globalEventManagerWin_ = window;
    var _globalEventElement = null;
    while (!_globalEventElement)
    {
        try
        {
            if (!_globalEventManagerWin_.closed
                    && _globalEventManagerWin_.$GlobalEventManager)
            {
            	_globalEventElement = _globalEventManagerWin_._globalEventElement;
                break;
            }
        }
        catch (e)
        {
            // do nothing
        }

        if (!_globalEventManagerWin_.closed
                && _globalEventManagerWin_.parent != null
                && _globalEventManagerWin_.parent != _globalEventManagerWin_)
        {
            _globalEventManagerWin_ = _globalEventManagerWin_.parent;
        }
        else if (!_globalEventManagerWin_.closed
                && _globalEventManagerWin_.opener != null
                && _globalEventManagerWin_.opener != _globalEventManagerWin_)
        {
            _globalEventManagerWin_ = _globalEventManagerWin_.opener;
        }
        else
        {
            /**
             * 事件管理器全局对象
             */
        	_globalEventElement = window.document.createElement('_global_event_element');
        	
        	window._globalEventElement = _globalEventElement;
        }
    }
    var _$globalEventManager = $(_globalEventElement);
    _globalEventManagerWin_ = null;
    _globalEventElement = null;
    
    

    var _globalEventCallbacks = {};
    $.fn.extend(
    {
        bindGlobalEvent : function(eventType, data, callbackFn)
        {
            if (arguments.length == 2 && $.isFunction(data))
            {
                callbackFn = data;
                data = [];
            }
            if (_globalEventCallbacks.eventType == null)
            {
                _globalEventCallbacks.eventType = $.Callbacks("unique");
            }
            var _callbacks = _globalEventCallbacks.eventType;
            var _self = this;
            _callbacks.add(callbackFn);
            _$globalEventManager.bind(eventType, data, function(event, params)
            {
                $.extend(event,
                {
                    currentTarget : _self, delegateTarget : _self
                });
                _callbacks.fire(_self,$.merge(arguments, data));
            });
        }
    });
    $.fn.bindGE = $.fn.bindge = $.fn.bindGlobalEvent;
    $.extend(
    {
        triggerGlobalEvent : function(eventType, params)
        {
            _$globalEventManager.trigger(eventType, params);
        }, bindGlobalEvent : function(eventType, data, callbackFn)
        {
            if (arguments.length == 2 && $.isFunction(data))
            {
                callbackFn = data;
                data = [];
            }
            if (_globalEventCallbacks.eventType == null)
            {
                _globalEventCallbacks.eventType = $.Callbacks("unique");
            }
            var _callbacks = _globalEventCallbacks.eventType;
            var _self = this;
            _callbacks.add(callbackFn);
            _$globalEventManager.bind(eventType, data, function(event, params)
            {
                $.extend(event,
                {
                    currentTarget : _self, delegateTarget : _self
                });
                _callbacks.fire(_self,$.merge(arguments, data));
            });
        }
    });
    $.triggerGE = $.triggerge = $.triggerGlobalEvent;
    $.bindGE = $.bindge = $.bindGlobalEvent;
})(jQuery);
/** ********** EventManager end ************** */

/**
 * 将javascript数据类型转换为json字符串的方法。
 * 
 * @public
 * @param {object}需转换为json字符串的对象,
 *            一般为Json【支持object,array,string,function,number,boolean,regexp *】
 * @return 返回json字符串
 */
(function($, undefined)
{
    jQuery.extend(
    {
        toJSONString : function(object)
        {
            var type = typeof object;
            if ('object' == type)
            {
                if (Array == object.constructor)
                    type = 'array';
                else if (RegExp == object.constructor)
                    type = 'regexp';
                else
                    type = 'object';
            }
            switch (type)
            {
                case 'undefined':
                case 'unknown':
                    return;
                    break;
                case 'function':
                case 'boolean':
                case 'regexp':
                    return object.toString();
                    break;
                case 'number':
                    return isFinite(object) ? object.toString() : 'null';
                    break;
                case 'string':
                    return '"'
                            + object.replace(/(\\|\")/g, "\\$1").replace(
                                    /\n|\r|\t/g,
                                    function()
                                    {
                                        var a = arguments[0];
                                        return (a == '\n') ? '\\n'
                                                : (a == '\r') ? '\\r'
                                                        : (a == '\t') ? '\\t'
                                                                : ""
                                    }) + '"';
                    break;
                case 'object':
                    if (object === null) return 'null';
                    var results = [];
                    for ( var property in object)
                    {
                        var value = jQuery.toJSONString(object[property]);
                        if (value !== undefined)
                            results.push(jQuery.toJSONString(property) + ':'
                                    + value);
                    }
                    return '{' + results.join(',') + '}';
                    break;
                case 'array':
                    var results = [];
                    for ( var i = 0; i < object.length; i++)
                    {
                        var value = jQuery.toJSONString(object[i]);
                        if (value !== undefined) results.push(value);
                    }
                    return '[' + results.join(',') + ']';
                    break;
            }
        }
    });
    jQuery.toJsonString = jQuery.tojsonstring = jQuery.toJSONString;
})(jQuery);
/** **************** date start******************** */
/*
 * 给事件对象添加format方法，用以将事件对象格式化为字符串
 */
Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth() + 1, // month
        "d+" : this.getDate(), // day
        "h+" : this.getHours(), // hour
        "m+" : this.getMinutes(), // minute
        "s+" : this.getSeconds(), // second
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" : this.getMilliseconds()
    // millisecond
    };
    if (/(y+)/.test(format))
    {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }
    for ( var k in o)
    {
        if (new RegExp("(" + k + ")").test(format))
        {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
/*
 * 将时间字符串根据配置的format格式化为时间对象
 */
Date.parseToDate = function(str, format)
{
    var dateParseObj =
    [
            {
                key : "y+", value : 0
            },
            {
                key : "M+", value : 0
            },
            {
                key : "d+", value : 0
            },
            {
                key : "(h|H)+", value : 0
            },
            {
                key : "m+", value : 0
            },
            {
                key : "s+", value : 0
            },
            {
                key : "S", value : 0
            }
    ];
    $.each(dateParseObj, function(index, k)
    {
        var reTemp = new RegExp("(" + k.key + ")");
        if (reTemp.test(format))
        {
            k.value = str.substr(format.indexOf(RegExp.$1), RegExp.$1.length);
        }
    });
    var date = new Date(dateParseObj[0].value, dateParseObj[1].value - 1,
            dateParseObj[2].value, dateParseObj[3].value,
            dateParseObj[4].value, dateParseObj[5].value, dateParseObj[6].value);
    return date;
};
/** ************** date end********************** */

/** *********** eventmanager start*************** */

/** *********** eventmanager end***************** */

/** ************** tabs start******************** */
/*
 * 定义tab
 */
(function($, undefined)
{
    $.widget("tx.txtabs", $.ui.tabs,
    {
        options :
        {
            tabsHandleContainerId : null
        },
        _create : function()
        {
            this._super();

            var that = this;

            this.element.css(
            {
                "padding-bottom" : "0px", "padding-left" : "0px",
                "padding-right" : "0px", "padding-top" : "0px"
            });
            this._getList().css(
                    {
                        "border-left-width" : "0px",
                        "border-top-width" : "0px",
                        "border-right-width" : "0px",
                        "border-bottom-width" : "0px"
                    });
        },
        // allow overriding how to find the list for rare usage scenarios
        // (#7715)
        _getList : function()
        {
            return this.element.find("#" + this.options.tabsHandleContainerId)
                    .find("ol,ul").eq(0);
        }
    });
})(jQuery);
/** ************** tabs end******************** */

/** ********** chemeswitcher start************** */
var _default_skins =
[
        {
            group : "jqueryui",
            hrefTemplate : "{contextPath}/jquery-ui/themes/{id}/jquery-ui.css",
            ids :
            [
                    "black-tie", "blitzer", "cupertino", "dark-hive",
                    "dot-luv", "eggplant", "excite-bike", "flick",
                    "hot-sneaks", "humanity", "le-frog", "mint-choc",
                    "overcast", "pepper-grinder", "redmond", "smoothness",
                    "south-street", "start", "sunny", "swanky-purse",
                    "trontastic", "vader","boda"
            ]
        },
        {
            group : "wijmo",
            hrefTemplate : "{contextPath}/wijmo/themes/{id}/jquery-wijmo.css",
            ids :
            [
                    "arctic", "aristo", "cobalt", "metro", "metro-dark",
                    "midnight", "rocket", "sterling"
            ]
        }
];
/*
 * 定义chemeswitcher
 */
(function($, undefined)
{   
    $.extend(
    {
        inittheme: function(themeCookieName,contextPath,skins){
            var _skins = skins != null ? skins : _default_skins;
            
            var tempalteMap = {};

            $.each(_skins, function(i, skingroup)
            {
                $.each(skingroup.ids, function(index, skin)
                {
                    tempalteMap[skin] =
                    {
                        id : skin, contextPath : _contextPath,
                        hrefTemplate : skingroup.hrefTemplate
                    };
                });
            });
            
            var cookieparamStr = $.cookie(themeCookieName);
            var paramObj = null;
            if (cookieparamStr != null)
            {
                paramObj = $.parseJSON(cookieparamStr);
            }
            
            if (paramObj && paramObj.id)
            {
                $.switchtheme(paramObj.id, _contextPath,
                                tempalteMap[paramObj.id].hrefTemplate,
                                themeCookieName);
            }
        },
        switchtheme : function(id, contextPath, template, themeCookieName)
        {
            var paramObj =
            {
                id : id, contextPath : contextPath, template : template
            };
            var $themes = $(document).find("link[title=theme]");
            if ($themes.filter("#" + id).size() > 0)
            {
                $themes.not("#" + id).attr("disabled", "dibled");
                $themes.filter("#" + id).removeAttr("disabled");
            }
            else
            {
                $themes.attr("disabled", "disabled");
                $themes.last().after(
                        $("<link/>").attr(
                                {
                                    rel : "stylesheet",
                                    type : "text/css",
                                    title : "theme",
                                    id : id,
                                    href : template.replace("{contextPath}",
                                            contextPath).replace("{id}", id)
                                }));
            }
            $.cookie(themeCookieName, "" + $.toJsonString(paramObj),
            {
                path: '/',
                expires : 365
            });
        }
    });

    $.widget("tx.chemeswitcher",
    {
        options :
        {
            themeCookieName : "tx_cheme", 
            contextPath : ".",
            skins : _default_skins
        },
        _create : function()
        {
            var element = this.element;
            var _this = this
            var options = this.options;
            var _contextPath = options.contextPath;
            var _themeCookieName = options.themeCookieName;
            var _skins = options.skins != null ? options.skins : _default_skins;

            element.empty();
            var $select = $("<select/>").appendTo($(element));
            var tempalteMap = {};

            $.each(_skins, function(i, skingroup)
            {
                var $selectGroup = $("<optgroup/>").attr("label",
                        skingroup.group).appendTo($select).attr("id",
                        skingroup.group).attr("hrefTemplate",
                        skingroup.hrefTemplate);
                $.each(skingroup.ids, function(index, skin)
                {
                    $("<option/>").attr("id", skin).val(skin).text(skin)
                            .appendTo($selectGroup).chan;
                    tempalteMap[skin] =
                    {
                        id : skin, contextPath : _contextPath,
                        hrefTemplate : skingroup.hrefTemplate
                    };
                });
            });$("#a1").chemeswitcher();
            $select.change(function()
            {
                var _$self = $(this);
                var _id = _$self.val();
                $.switchtheme(_id, _contextPath, tempalteMap[_id].hrefTemplate,
                        _themeCookieName);
            });
        }, _init : function()
        {
            $(this.element).find("select").wijdropdown({

            });
        }, destroy : function()
        {
            $(this.element).find("select").wijdropdown("destroy");
        }
    });
})(jQuery);

/** ********** chemeswitcher end ************** */

/** ********** dialogUtils end ************** **/
//定义dialogUtils对象
var DialogUtils = function(options){
};
/**
 * dialog默认配置
 */
DialogUtils.defaultConfigs = {
    autoOpen: true,
    closeOnEscape: false,
	width: 'auto',
	height: 'auto',
	zIndex: 10000,
	maxHeight: 1000,
	maxWidth: 700,
	draggable: true,
	position:'center'
};
/**
 * 创建或打开dialog
 * @param config
 */
DialogUtils.createOrOpenDiaglog = function(config){
	var openWinId = "dialog_" + config.id;
	config=$.extend({id:openWinId},this.defaultConfigs, config);
	var $dialogHandle = $("#"+openWinId);
	if($("#"+openWinId).size() == 0){
	   var $hiddenDiv = $("<div/>").attr("id",openWinId).hide();
	   $dialogHandle = $hiddenDiv;
	   $(document).append($dialogHandle);
	   $dialogHandle.wijdialog(config);
	}else{
		$dialogHandle.wijdialog(config);
	    //if(!$dialogHandle.wijdialog("isOpen")){
	    //    $dialogHandle.wijdialog("open");
	    //}
	}
};
//对话框工具:打开一个iframe的独立页面
/**
 *	打开openWin默认具有dialog所有按钮，未自动添加任何button
 */
DialogUtils.openDialog = function(config){
	config=$.extend({
	    id:'dialog_',
	    autoOpen: true,
	    width: 'auto',
        height: 'auto',
        captionButtons: {
            pin: {visible: true, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
            refresh: {visible: true, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
            toggle: {visible: true, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
            minimize: {visible: true, click: self.minimize, iconClassOn: 'ui-icon-minus'},
            maximize: {visible: true, click: self.maximize, iconClassOn: 'ui-icon-extlink'},
            close: {visible: true, iconClassOn: 'ui-icon-close'}
        },
        contentUrl: ''
	}, config);
	this.createOrOpenDiaglog(config);
};
/**
 *	打开openWin默认具有dialog所有按钮，未自动添加任何button
 */
DialogUtils.openSimpleDialog = function(config){
	config=$.extend({
	    id:'dialog_',
	    autoOpen: true,
	    width: 'auto',
        height: 'auto',
        captionButtons: {
            pin: {visible: false, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
            refresh: {visible: false, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
            toggle: {visible: false, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
            minimize: {visible: false, click: self.minimize, iconClassOn: 'ui-icon-minus'},
            maximize: {visible: false, click: self.maximize, iconClassOn: 'ui-icon-extlink'},
            close: {visible: true, iconClassOn: 'ui-icon-close'}
        },
        contentUrl: ''
	}, config);
	this.createOrOpenDiaglog(config);
};
/**
 *	打开openWin默认具有dialog所有按钮，未自动添加任何button
 */
DialogUtils.dialog = function(id,url,width,height){
	var config = $.extend({
	    id: id,
	    autoOpen: true,
	    width: width,
        height: height,
        contentUrl: url
	});
	this.openSimpleDialog(config);
};
DialogUtils.modalDialog = function(id,url,width,height){
	var config = $.extend({
	    id: id,
	    autoOpen: true,
	    modal: true,
	    width: width,
        height: height,
        contentUrl: url
	});
	this.openSimpleDialog(config);
};
/**
 * 根据id关闭对话框
 * @param id
 */
DialogUtils.closeDialogById = function(id){
	var openWinId = "dialog_" + id;
	var $dialogHandle = $("#" + openWinId);
	if($dialogHandle.size() > 0){
	   $dialogHandle.wijdialog("close");
	}
};
/**
 * 提示，并在指定时间后关闭提示
 */
DialogUtils._temp_dialog_index = 0;
DialogUtils.openTip = function(config,content,seconds,yes) {
    var openTipId = "tempDialog_" + (DialogUtils._temp_dialog_index++) + "_" + new Date().getTime();
    var autoCloseTimer = null;
    
    config=$.extend({
    	id: openTipId,
        autoOpen: true,
        closeOnEscape: false,
        width: content.length < 50 ? 300 : 'auto',
        height: content.length < 50 ? 130 : 'auto',
        captionButtons: {
            pin: {visible: false, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
            refresh: {visible: false, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
            toggle: {visible: false, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
            minimize: {visible: false, click: self.minimize, iconClassOn: 'ui-icon-minus'},
            maximize: {visible: false, click: self.maximize, iconClassOn: 'ui-icon-extlink'},
            close: {visible: true, iconClassOn: 'ui-icon-close'}
        },
        close : function(){
        	if(autoCloseTimer){
        		clearTimeout(autoCloseTimer);
        	}
        	removeTempTipHandle();
        }
    }, config);
    
    var $hiddenDiv = $("<div/>").attr("id",config.id).hide();
    function removeTempTipHandle(){
    	yes && yes.call(this);
        $hiddenDiv.remove();
    }
    $(document).append($hiddenDiv);
    $dialogHandle = $hiddenDiv;
    $dialogHandle.html(content);
    $dialogHandle.wijdialog(config);
    autoCloseTimer = window.setTimeout(removeTempTipHandle, seconds * 1000);
};
/**
 * 提示，并在指定时间后关闭提示
 */
DialogUtils._temp_dialog_index = 0;
DialogUtils.tip = function(content,seconds,yes) {
    this.openTip({}, content, seconds, yes);
};

/**
 * Dialog警告
 * @param {String} 消息内容
 * @param {Function} 确定按钮回调函数
 * @return {Object} 对话框操控接口
 */
DialogUtils.openAlert = function(config, msg, yes) {
    var openAlertId = "tempDialog_" + (DialogUtils._temp_dialog_index++) + "_" + new Date().getTime();
    config=$.extend({
    	id: openAlertId,
        autoOpen: true,
        closeOnEscape: false,
        width: msg.length < 50 ? 300 : 'auto',
        height: msg.length < 50 ? 130 : 'auto',
        modal: true,
        dialogClass: "alert",
        captionButtons: {
            pin: {visible: false, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
            refresh: {visible: false, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
            toggle: {visible: false, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
            minimize: {visible: false, click: self.minimize, iconClassOn: 'ui-icon-minus'},
            maximize: {visible: false, click: self.maximize, iconClassOn: 'ui-icon-extlink'},
            close: {visible: true, iconClassOn: 'ui-icon-close'}
        },
        close : function(){
        	removeTempTipHandle();
        },
        buttons: [{text: "确定" , click: function(){
            removeTempTipHandle();
        }}]
    }, config);
    
    var $hiddenDiv = $("<div/>").attr("id",config.id).hide();
    function removeTempTipHandle(){
    	yes && yes.call(this);
        $hiddenDiv.remove();
    }
    $(document).append($hiddenDiv);
    $dialogHandle = $hiddenDiv;
    //TODO:添加警告的样式
    $dialogHandle.html(msg);
    $dialogHandle.wijdialog(config);
}; 
/**
 * Dialog警告
 * @param {String} 消息内容
 * @param {Function} 确定按钮回调函数
 * @return {Object} 对话框操控接口
 */
DialogUtils.alert = function(msg, yes) {
    this.openAlert({}, msg, yes);
};
//改写window alert
window.alert = function(msg){
	DialogUtils.alert(msg);
};
/**
 * config
 */
DialogUtils.openConfirm = function(config, msg, yes , no) {
    var openConfirmId = "tempDialog_" + (DialogUtils._temp_dialog_index++) + "_" + new Date().getTime();
    config=$.extend({
    	id: openConfirmId,
        autoOpen: true,
        closeOnEscape: false,
        width: msg.length < 50 ? 300 : 'auto',
        height: msg.length < 50 ? 130 : 'auto',
        modal: true,
        captionButtons: {
            pin: {visible: false, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
            refresh: {visible: false, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
            toggle: {visible: false, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
            minimize: {visible: false, click: self.minimize, iconClassOn: 'ui-icon-minus'},
            maximize: {visible: false, click: self.maximize, iconClassOn: 'ui-icon-extlink'},
            close: {visible: true, iconClassOn: 'ui-icon-close'}
        },
        close : function(){
        	no && no.call(this);
        	removeTempTipHandle();
        },
        buttons: [{text: "确定" , click: function(){
        	yes && yes.call(this);
            removeTempTipHandle();
        }},{text: "取消" , click: function(){
        	no && no.call(this);
            removeTempTipHandle();
        }}]
    }, config);
    
    var $hiddenDiv = $("<div/>").attr("id",config.id).hide();
    function removeTempTipHandle(){
        $hiddenDiv.remove();
    }
    $(document).append($hiddenDiv);
    $dialogHandle = $hiddenDiv;
    $dialogHandle.html(msg);
    $dialogHandle.wijdialog(config);
}; 
/**
 * Dialog警告
 * @param {String} 消息内容
 * @param {Function} 确定按钮回调函数
 * @return {Object} 对话框操控接口
 */
DialogUtils.confirm = function(msg, yes , no) {
    this.openConfirm({},msg,yes,no);
}; 
window.confirm = function(msg, yes , no){
	DialogUtils.confirm(msg, yes , no);
};;

/** ********** dialogUtils end ************** **/

/** ********** tree    start   ************** **/
(function($, undefined)
{   
    //根据项目中树数据的特性定制tree
    $.widget("tx.txtree",
    {
        options :
        {
            adapter:{
                
            },
            data:null
        },
        _create : function()
        {
            //widget常量
            var element = this.element;
            var _this = this
            var options = this.options;
            
            //使用到的options中值
            var _contextPath = options.contextPath;
            var _themeCookieName = options.themeCookieName;
            var _skins = options.skins != null ? options.skins : _default_skins;

            //清空element中内容
            element.empty();
            
            var $select = $("<select/>").appendTo($(element));
            var tempalteMap = {};

            $.each(_skins, function(i, skingroup)
            {
                var $selectGroup = $("<optgroup/>").attr("label",
                        skingroup.group).appendTo($select).attr("id",
                        skingroup.group).attr("hrefTemplate",
                        skingroup.hrefTemplate);
                $.each(skingroup.ids, function(index, skin)
                {
                    $("<option/>").attr("id", skin).val(skin).text(skin)
                            .appendTo($selectGroup).chan;
                    tempalteMap[skin] =
                    {
                        id : skin, contextPath : _contextPath,
                        hrefTemplate : skingroup.hrefTemplate
                    };
                });
            });$("#a1").chemeswitcher();
            $select.change(function()
            {
                var _$self = $(this);
                var _id = _$self.val();
                $.switchtheme(_id, _contextPath, tempalteMap[_id].hrefTemplate,
                        _themeCookieName);
            });
        }, _init : function()
        {
            $(this.element).find("select").wijdropdown({

            });
        }, destroy : function()
        {
            $(this.element).find("select").wijdropdown("destroy");
        }
    });
})(jQuery);
/** ********** tree      end   ************** **/

