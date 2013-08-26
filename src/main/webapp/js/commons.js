/************ 用于统一页面样式 ************** */
$(document).ready(function() {
    //如果页面body的样式class没有special标志，则自动渲染页面
    if($("body.special").size() < 1){
        //统一页面风格
    	//$("body").addClass("ui-widget-content");
		//$("form").addClass("");
		//$("button,:input[type=button],:input[type=submit]").txButton();
		//if($(".form-table").size() > 0){
		//	$(".form-table").addClass("formee");
	    //	$(".form-table table").addClass("ui-widget-content").find("td,th").addClass("ui-widget-content");       
	    //}
        //处理具有header样式的自动渲染
        //if($(".header").size() > 0){
        //    $(".header").prepend('<span class="ui-icon ui-icon-gear" style="float:left;"></span>');
        //    $(".header").addClass("ui-state-default ui-corner-all ui-tabs-selected ui-state-active page-title-header");
        //}
    }
});


/** 
 * 提供跨页面的事件广播支持
 * 客户端统一事件管理器
 */

(function($, undefined) {
    //为当前页面定制，本地事件
    var $localEventHandle = null;
    //如果当前页面对象不存在，则从window中进行提取，
    //如果window中对应对象不存在，则进行创建
    if (!$localEventHandle) {
        if (!window.$localEventHandle) {
            window.$localEventHandle = $("<_localEventHandle/>");
        }
        $localEventHandle = window.$localEventHandle;
    }
    //console.log("$localEventHandle init success. $localEventHandle.size:" + $localEventHandle.size());

    /**
     * 定义全局事件管理器
     */
    var _globalEventManagerWin_ = window;
    var _$globalEventManager = null;
    while (!_$globalEventManager) {
        try {
            if (!_globalEventManagerWin_.closed && _globalEventManagerWin_.$globalEventManager) {
                _$globalEventManager = _globalEventManagerWin_.$globalEventManager;
                _$globalEventManager.registeGlobalEventListener(window);
                //console.log("registe window to global");
                break;
            }
        } catch (e) {
            // do nothing
        }

        if (!_globalEventManagerWin_.closed && _globalEventManagerWin_.parent != null && _globalEventManagerWin_.parent != _globalEventManagerWin_) {
            _globalEventManagerWin_ = _globalEventManagerWin_.parent;
        } else if (!_globalEventManagerWin_.closed && _globalEventManagerWin_.opener != null && _globalEventManagerWin_.opener != _globalEventManagerWin_) {
            _globalEventManagerWin_ = _globalEventManagerWin_.opener;
        } else {
            //定义一个子window的链表类
            var ChildWindowLinkedList = function() {
            };
            //子引用链
            ChildWindowLinkedList.prototype.childs = [];
            //用以支持抹去陈旧的window引用
            ChildWindowLinkedList.prototype._expungeStaleEntries = function() {
                var _self = this;
                var newChilds = $.grep(_self.childs, function(childWindowRefTemp, i) {
                    if (childWindowRefTemp != null && $.isWindow(childWindowRefTemp) && !childWindowRefTemp.closed) {
                        return true;
                    } else {
                        return false;
                    }
                });
                this.childs = newChilds;
            }
            //添加,能调用到该方法的子页面，应该都是统一域中的
            ChildWindowLinkedList.prototype.add = function(childWindowRef) {
                this._expungeStaleEntries();
                if (childWindowRef != null && $.isWindow(childWindowRef) && !childWindowRef.closed) {
                    this.childs.push(childWindowRef);
                }
            };
            //获取链表的遍历器
            ChildWindowLinkedList.prototype.iterator = function() {
                this._expungeStaleEntries();
                return this.childs;
            }
            //全局事件管理器
            var GlobalEventManager = function(config) {
            };
            GlobalEventManager.prototype._childWindowLinkedList = new ChildWindowLinkedList();
            GlobalEventManager.prototype.registeGlobalEventListener = function(currentWindowRef) {
                if (currentWindowRef && $.isWindow(currentWindowRef) && currentWindowRef.$localEventHandle) {
                    this._childWindowLinkedList.add(currentWindowRef);
                }
            };
            GlobalEventManager.prototype.trigger = function() {
                //console.log("triggerge:" + arguments[0]);
                var _self = this;
                var _arguments = arguments;
                var childWindowRefArr = this._childWindowLinkedList.iterator();
                $.each(childWindowRefArr, function(i, childWindowRefTemp) {
                    if (childWindowRefTemp && childWindowRefTemp.$localEventHandle) {
                        //console.log("window._$localEventHandle.trigger:" + _arguments[0]);
                        var $lehandle = childWindowRefTemp.$localEventHandle;
                        $lehandle.triggerHandler.apply($lehandle, _arguments);
                    }
                });
            };

            /**
             * 事件管理器全局对象
             */
            _$globalEventManager = new GlobalEventManager();
            //将当前页面的window对象注入容器
            _$globalEventManager.registeGlobalEventListener(window);
            //console.log("new global.and registe window to global");

            //释放对象
            GlobalEventManager = null;
            try {
                //如果跨域时
                _globalEventManagerWin_.$globalEventManager = _$globalEventManager;
            } catch(e) {
            }
        }
    }
    //释放引用
    _globalEventManagerWin_ = null;

    $.extend({
        bindGlobalEvent : function() {
            var $lehandle = window.$localEventHandle;
            $lehandle.bind.apply($lehandle, arguments);
        },
        unbindGlobalEvent : function() {
            var $lehandle = window.$localEventHandle;
            $lehandle.unbind.apply($lehandle, arguments);
        },
        oneGlobalEvent : function() {
            var $lehandle = window.$localEventHandle;
            $lehandle.one.apply($lehandle, arguments);
        },
        triggerGlobalEvent : function() {
            _$globalEventManager.trigger.apply(_$globalEventManager, arguments);
        }
    });
    $.triggerGE = $.triggerge = $.triggerGlobalEvent;
    $.bindGE = $.bindge = $.bindGlobalEvent;
    $.unbindGE = $.unbindge = $.unbindGlobalEvent;
    $.oneGE = $.onege = $.oneGlobalEvent;
})(jQuery); 


/** 
 * 浏览器扩展
 * 浏览器相关信息
 */
var browser = {
	appCodeName : navigator.appCodeName,// 浏览器代码名称
	appName : navigator.appName,// 浏览器的名称
	appVersion : navigator.appVersion,// 浏览器的平台和版本信息
	cookieEnabled : navigator.cookieEnabled,// 浏览器中是否启用cookie的布尔值
	platform : navigator.platform,// 运行浏览器的操作系统平台
	userAgent : navigator.userAgent, // 由客户机发送服务器的 user-agent 头部的值
	isIe : false,
	ieVersion : '',
	isChrome : false,
	isFirefox : false
};
if (browser.userAgent.indexOf('MSIE') > -1) {
	// IE浏览器
	browser.isIe = true;
	if (browser.userAgent.indexOf('MSIE 10') > -1) {
		// IE10
		browser.ieVersion = 10;
	} else if (browser.userAgent.indexOf('MSIE 9') > -1) {
		// IE9
		browser.ieVersion = 9;
	} else if (browser.userAgent.indexOf('MSIE 8') > -1) {
		// IE8
		browser.ieVersion = 8;
	} else if (browser.userAgent.indexOf('MSIE 7') > -1) {
		// IE7
		browser.ieVersion = 7;
	} else if (browser.userAgent.indexOf('MSIE 6') > -1) {
		// IE6
		browser.ieVersion = 6;
	} else {

	}
} else if (browser.userAgent.indexOf('Chrome') > -1) {
	// 谷歌浏览器
	browser.isChrome = true;
} else if (browser.userAgent.indexOf('Firefox') > -1) {
	// 火狐浏览器
	browser.isFirefox = true;
} else {
	// 其他浏览器
}
/** 
 * 扩展jquery 
 */
(function($, undefined){
    $.ObjectUtils = {};
    $.ObjectUtils.isEmpty = function(data){
        if(!data){
            return true;
        }else if($.type(data) == 'string' && data == $.trim(data) == ''){
            return true;
        }else if($.type(data) == 'array' && data.length == 0){
            return true;
        }
        return false;
    };
    $.TreeUtils = {};
    $.TreeUtils.defaultConverter = function(data){
        var converter = this;
        var resData = $.extend({},{
            id: data.id,
            text:null,
            formatter:null,
            state: $.ObjectUtils.isEmpty(data.childs) ? null : 'closed',//open/closed
            attributes: data,
            iconCls: null,
        },data);
        if(data.childs && !$.ObjectUtils.isEmpty(data.childs)){
            resData.children = [];
            $.each(data.childs, function(index, childTemp) {
                resData.children[index] = converter.call(converter,childTemp);
            });
        }else{
             resData.children = null;
        }
        return resData;
    };
    $.TreeUtils.transform = function(data,converter){
        converter = converter && $.isFunction(converter) ? converter : $.TreeUtils.defaultConverter;
        var resTreeData = null;
        if($.type(data) != 'array'){
            data = [data];
        }
        if($.ObjectUtils.isEmpty(data)){
            return false;
        }
        resTreeData = [];
        $.each(data,function(index,treeNodeDataTemp){
            resTreeData[index] = converter.call(converter,treeNodeDataTemp);
        });
        return resTreeData;
    };
	$.cookie = function(key, value, options) {
		if (arguments.length > 1 && (value === null || typeof value !== "object")) {
			options = $.extend({}, options);
			if (value === null) {
				options.expires = -1;
			}
			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setDate(t.getDate() + days);
			}
			return (document.cookie = [ encodeURIComponent(key), '=', options.raw ? String(value) : encodeURIComponent(String(value)), options.expires ? '; expires=' + options.expires.toUTCString() : '', options.path ? '; path=' + options.path : '', options.domain ? '; domain=' + options.domain : '', options.secure ? '; secure' : '' ].join(''));
		}
		options = value || {};
		var result, decode = options.raw ? function(s) {
			return s;
		} : decodeURIComponent;
		return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
	};

	/*
	 * 将form表单元素的值序列化成对象
	 */
	$.serializeObject = function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	};
	/*
	 * 增加formatString功能
	 * 使用方法：$.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
	 */
	$.formatString = function(str) {
		for ( var i = 0; i < arguments.length - 1; i++) {
			str = str.replace("{" + i + "}", arguments[i + 1]);
		}
		return str;
	};
	/*
	 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
	 */
	$.stringToList = function(value) {
		if (value != undefined && value != '') {
			var values = [];
			var t = value.split(',');
			for ( var i = 0; i < t.length; i++) {
				values.push('' + t[i]);/* 避免他将ID当成数字 */
			}
			return values;
		} else {
			return [];
		}
	};
	/*
	 * 改变jQuery的AJAX默认属性和方法
	 */
	$.ajaxSetup({
		type : 'POST',
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			try {
				parent.$.messager.progress('close');
				parent.$.messager.alert('错误', XMLHttpRequest.responseText);
			} catch (e) {
				alert(XMLHttpRequest.responseText);
			}
		}
	});
	/**
	 * @author 孙宇
	 * 
	 * 去字符串空格
	 * 
	 * @returns
	 */
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, '');
	};
	String.prototype.ltrim = function() {
		return this.replace(/(^\s*)/g, '');
	};
	String.prototype.rtrim = function() {
		return this.replace(/(\s*$)/g, '');
	};
	/**
	 * 将javascript数据类型转换为json字符串的方法。
	 * 
	 * @public
	 * @param {object}需转换为json字符串的对象,
	 *            一般为Json【支持object,array,string,function,number,boolean,regexp *】
	 * @return 返回json字符串
	 */
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	}
    jQuery.extend({
    	subString: function(oldStr,maxLength,suffix){
    		if(oldStr.length > maxLength){
    			oldStr = oldStr.substring(0,maxLength) + suffix;
    		}
    		return oldStr;
    	},
    	toJSONString: function(object)
        {
            var type = jQuery.type(object);
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

/** **************** 扩展js中原date对象******************** */
/*
 * 给事件对象添加format方法，用以将事件对象格式化为字符串
 */
Date.prototype.format = function(format){
    var o ={
        "M+" : this.getMonth() + 1, // month
        "d+" : this.getDate(), // day
        "h+" : this.getHours(), // hour
        "m+" : this.getMinutes(), // minute
        "s+" : this.getSeconds(), // second
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" : this.getMilliseconds()
    // millisecond
    };
    if (/(y+)/.test(format)){
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
                .substr(4 - RegExp.$1.length));
    }
    for ( var k in o){
        if (new RegExp("(" + k + ")").test(format)){
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                    : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
/*
 * 将时间字符串根据配置的format格式化为时间对象
 */
Date.parseToDate = function(str, format){
    var dateParseObj =[{
                key : "y+", value : 0
            },{
                key : "M+", value : 0
            },{
                key : "d+", value : 0
            },{
                key : "(h|H)+", value : 0
            },{
                key : "m+", value : 0
            },{
                key : "s+", value : 0
            },{
                key : "S", value : 0
            }];
    $.each(dateParseObj, function(index, k){
        var reTemp = new RegExp("(" + k.key + ")");
        if (reTemp.test(format)){
            k.value = str.substr(format.indexOf(RegExp.$1), RegExp.$1.length);
        }
    });
    var date = new Date(dateParseObj[0].value, dateParseObj[1].value - 1,
            dateParseObj[2].value, dateParseObj[3].value,
            dateParseObj[4].value, dateParseObj[5].value, dateParseObj[6].value);
    return date;
};
/** ************** date end********************** */

/** ********** dialogUtils end ************** **/
//定义dialogUtils对象
var DialogUtils = function(options){
};
/**
 * dialog默认配置
 */
DialogUtils.defaultConfigs = {  
};
/**
 * 当前页面Dialog关闭句柄
 */
DialogUtils.currentPageDialogCloserHandlers = {};
/**
 * 创建或打开dialog
 * @param config
 */
DialogUtils.createOrOpenDiaglog = function(config){
	var openWinId = "dialog_" + config.id;
	config=$.extend({id:openWinId},this.defaultConfigs, config);
	var $dialogHandle = $("#"+openWinId);
	if($("#"+openWinId).size() == 0){
		$dialogHandle = $("<div/>").attr("id",openWinId).hide();
		config.title && $dialogHandle.attr("title",config.title);
		$(document).append($dialogHandle);
		$dialogHandle.wijdialog(config);
	}else{
		config.title && $dialogHandle.attr("title",config.title)
		$dialogHandle.wijdialog(config);
	    //if(!$dialogHandle.wijdialog("isOpen")){
	    //    $dialogHandle.wijdialog("open");
	    //}
	}
	if(!DialogUtils.currentPageDialogCloserHandlers[openWinId]){
		DialogUtils.currentPageDialogCloserHandlers[openWinId] = true;
		$.bindge("dialog_" + openWinId + "_close",function(){
			//console.log("dialog_" + openWinId + "_close,happend");
			$("#" + openWinId).wijdialog("close");
		});
	}
};
//对话框工具:打开一个iframe的独立页面
/**
 *	打开openWin默认具有dialog所有按钮，未自动添加任何button
 */
DialogUtils.openDialog = function(config){
	config=$.extend({},{
	    id:'dialog_',
	    title:null,
	    autoOpen: true,
	    modal: false,
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
	DialogUtils.createOrOpenDiaglog(config);
};
/**
 *	打开openWin默认具有dialog所有按钮，未自动添加任何button
 */
DialogUtils.openSimpleDialog = function(config){
	config=$.extend({},{
	    id:'dialog_',
	    title:null,
	    autoOpen: true,
	    width: 'auto',
	    modal: false,
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
	DialogUtils.createOrOpenDiaglog(config);
};
/**
 *	打开openWin默认具有dialog所有按钮，未自动添加任何button
 */
DialogUtils.dialog = function(id,title,url,width,height,close){
	var config = $.extend({
        id:'dialog_',
        autoOpen: true,
        width: width && $.isNumeric(width) ? width : 'auto',
        height: height && $.isNumeric(height) ? height : 'auto',
        modal: false,
        captionButtons: {
            pin: {visible: false, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
            refresh: {visible: false, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
            toggle: {visible: false, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
            minimize: {visible: false, click: self.minimize, iconClassOn: 'ui-icon-minus'},
            maximize: {visible: false, click: self.maximize, iconClassOn: 'ui-icon-extlink'},
            close: {visible: true, iconClassOn: 'ui-icon-close'}
        }
	},{
	    id: id,
        title: title,
        contentUrl: url,
        close : function(){
            close && close.call(this);
        }
	});
	DialogUtils.createOrOpenDiaglog(config);
};
DialogUtils.modalDialog = function(id,title,url,width,height,close){
	var config = $.extend({
        id:'dialog_',
        autoOpen: true,
        width: width && $.isNumeric(width) ? width : 'auto',
        height: height && $.isNumeric(height) ? height : 'auto',
        modal: true,
        captionButtons: {
            pin: {visible: false, click: self.pin, iconClassOn: 'ui-icon-pin-w', iconClassOff:'ui-icon-pin-s'},
            refresh: {visible: false, click: self.refresh, iconClassOn: 'ui-icon-refresh'},
            toggle: {visible: false, click: self.toggle, iconClassOn: 'ui-icon-carat-1-n', iconClassOff:'ui-icon-carat-1-s'},
            minimize: {visible: false, click: self.minimize, iconClassOn: 'ui-icon-minus'},
            maximize: {visible: false, click: self.maximize, iconClassOn: 'ui-icon-extlink'},
            close: {visible: true, iconClassOn: 'ui-icon-close'}
        }
	},{
	    id: id,
        contentUrl: url,
        title: title,
        close : function(){
            close && close.call(this);
        }
	});
	DialogUtils.createOrOpenDiaglog(config);
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
	}else{
	   $.triggerge("dialog_" + openWinId + "_close");
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
    
    var $dialogHandle = $("<div/>").attr("id",config.id).hide();
    $dialogHandle.attr("title","提醒");
    function removeTempTipHandle(){
    	yes && yes.call(this);
        $dialogHandle.remove();
    }
    $(document).append($dialogHandle);
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
        width: (msg && ("" + msg).length < 50) ? 300 : 'auto',
        height: (msg && ("" + msg).length < 50) ? 130 : 'auto',
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
    
    var $dialogHandle = $("<div/>").attr("id",config.id).hide();
    function removeTempTipHandle(){
    	yes && yes.call(this);
        $dialogHandle.remove();
    }
    $(document).append($dialogHandle);
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
//window.alert = function(msg){
//	DialogUtils.alert(msg);
//};
/**
 * Dialog消息
 * @param {String} 消息内容
 * @param {Function} 确定按钮回调函数
 * @return {Object} 对话框操控接口
 */
DialogUtils.openMessage = function(config, msg, type, yes) {
    var openAlertId = "tempDialog_" + (DialogUtils._temp_dialog_index++) + "_" + new Date().getTime();
    config=$.extend({
    	id: openAlertId,
        autoOpen: true,
        closeOnEscape: false,
        width: (msg && ("" + msg).length < 50) ? 300 : 'auto',
        height: (msg && ("" + msg).length < 50) ? 130 : 'auto',
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
    
    var $dialogHandle = $("<div/>").attr("id",config.id).hide();
    if('info' == type){
    	$dialogHandle.addClass("formee-msg-info");
    }else if('warning' == type){
    	$dialogHandle.addClass("formee-msg-warning");
    }else if('error' == type){
    	$dialogHandle.addClass("formee-msg-error");
    }else if('success' == type){
    	$dialogHandle.addClass("formee-msg-success");
    }else{
    	$dialogHandle.addClass("formee-msg-info");
    }
    
    function removeTempTipHandle(){
    	yes && yes.call(this);
        $dialogHandle.remove();
    }
    $(document).append($dialogHandle);
    //TODO:添加警告的样式
    $dialogHandle.html(msg);
    $dialogHandle.wijdialog(config);
};
/**
 * Dialog消息
 * @param {String} 消息内容
 * @param {Function} 确定按钮回调函数
 * @return {Object} 对话框操控接口
 */
DialogUtils.message = function(msg, type, yes) {
    this.openAlert({}, msg , type , yes);
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
        width: (msg && ("" + msg).length < 50) ? 300 : 'auto',
        height: (msg && ("" + msg).length < 50) ? 130 : 'auto',
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
    
    var $dialogHandle = $("<div/>").attr("id",config.id).hide();
    function removeTempTipHandle(){
        $dialogHandle.remove();
    }
    $(document).append($dialogHandle);
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
};
