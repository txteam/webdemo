/************ 用于统一页面样式 ************** */
$(document).ready(function() {
    //如果页面body的样式class没有special标志，则自动渲染页面
    if($("body.special").size() < 1){
        //统一页面风格
    	$("body").addClass("ui-widget-content");
		//$("form").addClass("");
		$("button,:input[type=button],:input[type=submit]").txButton();
		if($(".form-table").size() > 0){
			$(".form-table").addClass("formee");
	    	$(".form-table table").addClass("ui-widget-content").find("td,th").addClass("ui-widget-content");       
	    }
        //处理具有header样式的自动渲染
        if($(".header").size() > 0){
            $(".header").prepend('<span class="ui-icon ui-icon-gear" style="float:left;"></span>');
            $(".header").addClass("ui-state-default ui-corner-all ui-tabs-selected ui-state-active page-title-header");
        }
    }
});

/************ 提供跨页面的事件广播支持 ************** */
/*
 * 客户端统一事件管理器
 */
(function($, undefined){
    /**
     * 定义全局事件管理器
     */
    var _globalEventManagerWin_ = window;
    var _$globalEventManager = null;
    while (!_$globalEventManager){
        try{
            if (!_globalEventManagerWin_.closed
                    && _globalEventManagerWin_._$globalEventManager){
            	_$globalEventManager = _globalEventManagerWin_._$globalEventManager;
                break;
            }
        }catch (e) {
            // do nothing
        }

        if (!_globalEventManagerWin_.closed
                && _globalEventManagerWin_.parent != null
                && _globalEventManagerWin_.parent != _globalEventManagerWin_){
            _globalEventManagerWin_ = _globalEventManagerWin_.parent;
        }
        else if (!_globalEventManagerWin_.closed
                && _globalEventManagerWin_.opener != null
                && _globalEventManagerWin_.opener != _globalEventManagerWin_){
            _globalEventManagerWin_ = _globalEventManagerWin_.opener;
        }
        else{
        	//全局事件管理器
        	var GlobalEventManager = function(config){
        		
        	};
        	//全局事件管理器属性:
        	GlobalEventManager.prototype.$globalEventHandle = $("<_global_event_handle/>");
        	GlobalEventManager.prototype.eventTypeCallbackMapping = {};
        	
        	//全局事件管理器方法:
        	//添加事件监听器
        	GlobalEventManager.prototype.bind = function(eventType, data, callbackFn){
        		var _self = this;
        		if(!callbackFn && $.isFunction(data)){
        			callbackFn = data;
        			data = [];
        		}
        		//压入堆栈
        		if(!_self.eventTypeCallbackMapping[eventType]){
        			_self.eventTypeCallbackMapping[eventType] = $.Callbacks("unique");
        			_self.$globalEventHandle.bind(eventType, data, function(event){
        				//console.log(Array.prototype.slice.call(arguments));
        				_self.eventTypeCallbackMapping[eventType].fireWith(callbackFn,Array.prototype.slice.call(arguments));
        			});
        		}
        		var _whenExceptionRemoveAbleFunction = function(){
        			try{
        				callbackFn.apply(callbackFn,arguments);
        			}catch(e){
        				//console.log('fire bind global event exception: ' + e);
        				_self.eventTypeCallbackMapping[eventType].remove(_whenExceptionRemoveAbleFunction);
        			}
        		};
        		_self.eventTypeCallbackMapping[eventType].add(_whenExceptionRemoveAbleFunction);
        	};
        	GlobalEventManager.prototype.trigger = function(eventType, params){
        		this.$globalEventHandle.trigger(eventType, params)
        	};
        	
            /**
             * 事件管理器全局对象
             */
        	_$globalEventManager = new GlobalEventManager();
        	
        	_globalEventManagerWin_._$globalEventManager = _$globalEventManager;
        	//循环调度，用以清理全局事件管理器中，已经被回收的对象
        	//setInterval(function(){
        	//	
        	//},60 * 1000 );
        }
    }
    //释放引用
    _globalEventManagerWin_ = null;

    var _globalEventCallbacks = {};
    $.extend({
        triggerGlobalEvent : function(eventType, params){
            _$globalEventManager.trigger(eventType, params);
        }, 
        bindGlobalEvent : function(eventType, data, callbackFn){
            _$globalEventManager.bind(eventType, data, callbackFn);
        }
    });
    $.triggerGE = $.triggerge = $.triggerGlobalEvent;
    $.bindGE = $.bindge = $.bindGlobalEvent;
})(jQuery);


/** ********** 扩展jquery ************** */
(function($, undefined){
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
window.alert = function(msg){
	DialogUtils.alert(msg);
};
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

/** ********** component grid start   ************** **/
(function($, undefined){   
    $.widget("tx.txGrid",{
        options : {
        	/*
        	 * 自定义属性部分
        	 */
        	//扩展类型:
        	//允许不进行配置，如果不进行配置，将完全使用jqgrid的配置
        	//simple:"对应已经用foreach生成了页面列表，只利用该grid进行渲染的情况"
        	//ajaxList: "对ajax返回List的类型"
        	//ajaxPagedList: "对ajax返回pagedList的类型"
        	type: null,
        	/*
        	 * 主要属性部分
        	 */
        	//表格名称
            caption: null,
            hidegrid: false,
        	//rowNum	在grid上显示记录条数，这个参数是要被传递到后台
            rowNum:9999,
            //如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.default : false
            rownumbers: false,
            //数组:列显示名称，是一个数组对象
            //colNames:[],
            //常用到的属性：
        	//name 列显示的名称,
            //index 传到服务器端用来排序用的列名称,
            //width 列宽度
            //align 对齐方式；
            //sortable  是否可以排序
            //colModel:[],
            //定义是否可以多选
        	multiselect: false,
            //ajax提交方式。POST或者GET，jq默认GET,在此制定默认的grid为post提交
        	mtype: "POST",
        	//如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。
            //如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
            autowidth:true,
            /*
        	 * 次要属性部分
        	 */
            //autoencode boolean 对url进行编码
            autoencode: true,
            //启用或者禁用单元格编辑功能
            cellEdit:false,
            //cellsubmit String 定义了单元格内容保存位置
            //cellurl String 单元格提交的url
            //editurl string 定义对form编辑时的url 空值 是
            emptyrecords:'无',
            //当为ture时，调整列宽度不会改变表格的宽度。当shrinkToFit 为false时，此属性会被忽略  false 否
        	forceFit : true,
        	serializeGridData : function(postData){
        		//如果数据为纯粹的对象，则将该对象转化为数组
        		if($.isPlainObject(postData)){
        			var newArray = [];
        			var index = 0;
        			$.each(postData,function(name,value){
        				if($.isArray(value)){
        					$.each(value,function(i,o){
        						newArray[index++] = {name:name,value:o};
        					});
        				}else{
        					newArray[index++] = {name:name,value:value};
        				}
        			});
        			//alert($.toJsonString(newArray));
        			return newArray;
        		}else{
        			return postData;
        		}
        	}
        },
        _defaultJqGridOptions : {
        	//获取数据的地址
        	url: null,
        	//从服务器端返回的数据类型，默认xml。
        	//可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
        	datatype: "json",
        	//ajax提交方式。POST或者GET，jq默认GET,在此制定默认的grid为post提交
        	mtype: "POST",
        	//数组:列显示名称，是一个数组对象
            //colNames:[],
            //常用到的属性：
        	//name 列显示的名称,
            //index 传到服务器端用来排序用的列名称,
            //width 列宽度
            //align 对齐方式；
            //sortable  是否可以排序
            //colModel:[],
        	//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
            //pager: null,
            //rowNum	在grid上显示记录条数，这个参数是要被传递到后台
            rowNum: 9999,
            //array 一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
            rowList:[10,20,50,100,200,500],
            //sortname string 默认的排序列。可以是列名称或者是一个数字，这个参数会被提交到后台
            //viewrecords boolean 定义是否要显示总记录数
            viewrecords: true,
            //表格名称
            caption: null,
            //object
            //对ajax参数进行全局设置，可以覆盖ajax事件
            //ajaxGridOptions[a1]: null,
            //object
            //对ajax的select参数进行全局设置
            //ajaxSelectOptions[a2]: null,
            //altclass String 用来指定行显示的css，可以编辑自己的css文件，只有当altRows设为 ture时起作用 ui-priority-secondary
            //altRows boolean 设置表格 zebra-striped 值
            //autoencode boolean 对url进行编码
            autoencode: true,
            //如果为ture时，则当表格在首次被创建时会根据父元素比例重新调整表格宽度。
            //如果父元素宽度改变，为了使表格宽度能够自动调整则需要实现函数：setGridWidth
            autowidth:true,
            //cellLayout 定义了单元格padding + border 宽度。通常不必修改此值。初始值为 5
            //启用或者禁用单元格编辑功能
            cellEdit:false,
            //cellsubmit String 定义了单元格内容保存位置
            //cellurl String 单元格提交的url
            //datastr String xmlstring或者jsonstring
            //deselectAfterSort boolean 只有当datatype为local时起作用。当排序时不选择当前行 true 是
            //direction string 表格中文字的显示方向，从左向右（ltr）或者从右向左（rtr） ltr否
            //editurl string 定义对form编辑时的url 空值 是
            //emptyrecords string 当返回的数据行数为0时显示的信息。只有当属性 viewrecords 设置为ture时起作用
        	//启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
        	//ExpandColClick boolean 当为true时，点击展开行的文本时，treeGrid就能展开或者收缩，不仅仅是点击图片 true 否
        	//ExpandColumn string 指定那列来展开tree grid，默认为第一列，只有在treeGrid为true时起作用 空值 否
        	//footerrow[a3] boolean 当为true时，会在翻页栏之上增加一行 false 否
        	//shrinkToFit  boolean 当为ture时，调整列宽度不会改变表格的宽度。当shrinkToFit 为false时，此属性会被忽略  false 否
        	forceFit : true,
        	//string 定义当前表格的状态：'visible' or 'hidden'
        	//visible 否
        	gridstate: 'visible',
        	//boolean false 是
        	//构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用
        	gridview: false,
        	//mixed 表格高度，可以是数字，像素值或者百分比 150 否
        	height: 'auto',
        	//hiddengrid 启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
        	hiddengrid: false,
        	//hidegrid boolean 启用或者禁用控制表格显示、隐藏的按钮，只有当caption 属性不为空时起效
        	hidegrid: false,
        	//boolean当为false时mouse hovering会被禁用
        	hoverrows: true,
        	//jsonReader array 描述json 数据格式的数组
        	
        	//loadonce boolean 如果为ture则数据只从服务器端抓取一次，之后所有操作都是在客户端执行，翻页功能会被禁用
        	//loadtext string 当请求或者排序时所显示的文字内容 Loading.... 否
        	//string 当执行ajax请求时要干什么。disable禁用ajax执行提示；enable默认，当执行ajax请求时的提示； block启用Loading提示，但是阻止其他操作 enable 是
        	loadui: "enable",
        	//string 只有在multiselect设置为ture时起作用，定义使用那个key来做多选。shiftKey，altKey，ctrlKey 空值是
        	//multikey: "ctrlKey",
        	//boolean 只有当multiselect = true.起作用，当multiboxonly 为ture时只有选择checkbox才会起作用
        	multiboxonly: true,
        	//定义是否可以多选
        	multiselect: false,
        	//multiselectWidth integer 当multiselect为true时设置multiselect列宽度 20 否
        	//integer 设置初始的页码 1 是 	
        	//page: 1,      
        	//pagerpos string 指定分页栏的位置 center 否
        	//pagerpos: 'center',
        	//pgbuttons boolean 是否显示翻页按钮 true 否
        	//pgbuttons: false,
        	//pginput boolean 是否显示跳转页面的输入框 true 否
        	//pginput: false,
        	//pgtext string 当前页信息 是
        	//Default valuesprmNames: {page:“page”,rows:“rows”, sort: “sidx”,
        	//order: “sord”, search:“_search”, nd:“nd”, npage:null} 当参数为null时不会被发到服务器端
        	prmNames: {
        		page: "pageIndex",		// 表示请求页码的参数名称  
        		rows: "pageSize",		// 表示请求行数的参数名称
        		sort: "sortColumn",		// 表示用于排序的列名的参数名称  
        		search:"_search",
        		nd:"nd", 				// 表示已经发送请求的次数的参数名称
        		id:"id", 				// 表示当在编辑数据模块中发送数据时，使用的id的名称  
        		oper:"operation",    	// operation参数名称（暂时还没用到）  
        		editoper:"edit", 		// 当在edit模式中提交数据时，操作的名称
        		addoper:"add", 			// 当在add模式中提交数据时，操作的名称  
        		deloper:"del", 			// 当在delete模式中提交数据时，操作的名称
        		subgridid:"parentId", 	// 当点击以载入数据到子表时，传递的数据名称  
        		npage:null
        	},
        	//postData array 此数组内容直接赋值到url上，参数类型：{name1:value1…} 空array 是
        	//利用该参数实现：页面刷新后，保持当前页数，以及每页显示条数
        	//postData: {
        		//pageIndex: ,
        		//pageSize: ,
        	//},
        	//recordpos string 定义了记录信息的位置： left, center, right right 否
        	//recordtext string 显示记录数信息。{0} 为记录数开始，{1}为记录数结束。 viewrecords为ture时才能起效，且总记录数大于0时才会显示此信息
        	//resizeclass string 定义一个class到一个列上用来显示列宽度调整时的效果 空值 否
        	//如果为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.default : false
            rownumbers: false,
        	//rownumWidth integer 如果rownumbers为true，则可以设置column的宽度 25 否
            //scroll boolean 创建一个动态滚动的表格，当为true时，翻页栏被禁用，使用垂直滚动条加载数据，且在首次访问服务器端时将加载所有数据到客户端。当此参数为数字时，表格只控制可见的几行，所有数据都在这几行中加载 false 否
            //scrollOffset integer 设置垂直滚动条宽度 18 否
            //scrollrows boolean 当为true时让所选择的行可见 false 是
            //shrinkToFit boolean 此属性用来说明当初始化列宽度时候的计算类型，如果为ture，则按比例初始化列宽度。如果为false，则列宽度使用colModel指定的宽度 true 否
            //sortable boolean 是否可排序 false 否
            //sortname string 排序列的名称，此参数会被传到后台 空字符串 是
            //sortorder string 排序顺序，升序或者降序（asc or desc） asc 是
            //subGrid boolean 是否使用suggrid false 否
            //subGridModel array subgrid模型 array 否
            //subGridType mixed 如果为空则使用表格的dataType null 是
            //subGridUrl string 加载subgrid数据的url，jqGrid会把每行的id值加到url中 空值 是
            //subGridWidth integer subgrid列的宽度 20 否
            /*
            array 表格的工具栏。数组中有两个值，第一个为是否启用，第二个指定工具栏位置（相对于body layer），
            	如：[true,”both”] 。工具栏位置可选值：“top”,”bottom”, “both”. 
            	如果工具栏在上面，则工具栏id为“t_”+表格id；如果在下面则为 “tb_”+表格id；如果只有一个工具栏则为 “t_”+表格id [false,''] 否
            */
            toolbar: [false,'']
            //mixed 数据类型，通常情况下与datatype相同，不会变 null 否 treedatatype
            //treeGrid boolean 启用或者禁用treegrid模式 false 否
            //treeGridModel string treeGrid所使用的方法 Nested 否
			//treeIcons array 树的图标，默认值：{plus:'ui-icon-triangle-1-e',minus:'ui-icon-triangle-1-s',leaf:'ui-icon-radio-off'} 否
            //treeReader array 扩展表格的colModel且加在colModel定义的后面 否
            //tree_root_level numeric root元素的级别，0 
			//userData array 从request中取得的一些用户信息 array 否
			//erDataOnFooter boolean 当为true时把userData放到底部， 法：如果userData的值与colModel的值相同，那么此列就显示正确的值，如果不等那么此列就为空 false 是
			//array 定义排序列的外观跟行为。数据格式：[false,'vertical',true].第一个参数是说，是否都要显示排序列的图标，false就是只显示 当前排序列的图标；
			//第二个参数是指图标如何显示，vertical：排序图标垂直放置，horizontal：排序图标水平放置；
			//第三个参数指单击功 能，true：单击列可排序，false：单击图标排序。说明：如果第三个参数为false则第一个参数必须为ture否则不能排序
			//viewsortcols
			//width number 如果设置则按此设置为主，如果没有设置则按colModel中定义的宽度计算 none 否
			//width: 
			//xmlReaderarray对xml数据结构的描述否
            
            //lastsort integer 只读属性，定义了最后排序列的索引，从0开始
        	//lastpage integer 只读属性，定义了总页数 0 否
        	//lastsort integer 只读属性，定义了最后排序列的索引，从0开始 0 否
            //reccount integer 只读属性，定义了grid中确切的行数。
            //records integer  只读属性，定义了返回的记录数
            //通常情况下与records属性相同，但有一种情况例外，假如rowNum=15，
            //但是从服务器端返回的记录数是20，那么records值是20，但reccount值仍然为15，而且表格中也只显示15条记录。 0 否
            //savedRow array 只读属性，只用在编辑模式下保存数据 空值 否
            //selarrrow array 只读属性，用来存放当前选择的行 array 否
			//totaltime //integer 只读属性，计算加载数据的时间。目前支持xml跟json数据 0 否
        },
        _defaultJqGridColModel: {
        	//align string left, center, right. left
        	align: "left",
        	title: false,
        	//classes string 设置列的css。多个class之间用空格分隔，如：'class1 class2' 。表格默认的css属性是ui-ellipsis empty string
        	//datefmt string ”/”, ”-”, and ”.”都是有效的日期分隔符。y,Y,yyyy 年YY, yy 月m,mm for monthsd,dd 日. ISO Date (Y-m-d)
        	//defval string 查询字段的默认值 空
        	//editable boolean 单元格是否可编辑 false
        	//editoptions array 编辑的一系列选项。 empty
        	//editrules array empty
        	//编辑的规则{name:’age’,index:’age’, width:90,editable:true,editrules: {edithidden:true,required:true,number:true,minValue:10,maxValue:100}},
        	//设定 年龄的最大值为100，最小值为10，而且为数字类型，并且为必输字段。
        	//edittype string text 可以编辑的类型。可选值：text, textarea, select, checkbox, password, button, image and file.
        	//fixed boolean 列宽度是否要固定不可变 false
        	fixed: false,
        	//formoptions array 对于form进行编辑时的属性设置 empty
        	//formatoptions array 对某些列进行格式化的设置
        	//formatter:		//预设类型或用来格式化该列的自定义函数名。 //常用预设格式有:integer、date、currency、number等（具体参见文档 ）。
        	//hidedlg boolean 是否显示或者隐藏此列
        	//hidden:				//设置此列初始化时是否为隐藏状态，默认为false。
        	//index:"",				//设置排序时所使用的索引名称，这个index名称会作为sidx参数（prmNames中设置的）传递到Server
        	//jsonmap string 定义了返回的json数据映射 none
        	//key boolean 当从服务器端返回的数据中没有id时，将此作为唯一rowid使用只有一个列可以做这项设置。如果设置多于一个，那么只选取第一个，其他被忽略 false
        	key: false,
        	//label string 如果colNames为空则用此值来作为列的显示名称，如果都没有设置则使用name 值 none
        	//name:"",				//为Grid中的每个列设置唯一的名称，这是一个必需选项，其中保留字包括subgrid、cb、rn
        	////设置列是否可以变更尺寸，默认为true。
        	resizable:true,		    //设置列是否可以变更尺寸，默认为true。
        	search: false,			//设置该列是否可以被列为搜索条件，默认为true。
        	//searchoptions array 设置搜索参数 empty
        	//width:"",				//设置列的宽度，目前只能接受以px为单位的数值，默认为150。
        	sortable: false			//设置该列是否可以排序，默认为true。
        	/*
        	 * string text用在当datatype为local时，定义搜索列的类型，
        	 * 可选值：int/integer - 对integer排序float/number/currency - 排序数字date - 排序日期text - 排序文本
        	 * sorttype
        	 */
        	//stype string 定义搜索元素的类型 text
        	//surl string 搜索数据时的url empty
        	//width number 默认列的宽度，只能是象素值，不能是百分比 150
        	//xmlmap string 定义当前列跟返回的xml数据之间的映射关系 none
        	//unformat function ‘unformat’单元格值 null
        },
        _defaultColModel: {  
        	//label: "",			//
		    //root: "rows",   		// json中代表实际模型数据的入口  
		    //page: "page",   		// json中代表当前页码的数据  
		    //total: "total", 		// json中代表页码总数的数据  
		    records: "records" 		// json中代表数据行总数的数据  
		    //repeatitems: true, 	// 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。  
		    //id: "id",  			// 
		    //cell: "cell",  		//
		    //userdata: "userdata"	// 
		    //subgrid: {  
		    //    root:"rows",   
		    //    repeatitems: true,   
		    //    cell:"cell"  
		    //}  
		},
        _defaultJsonReader: {  
		    //root: "rows",   		// json中代表实际模型数据的入口  
		    //page: "page",   		// json中代表当前页码的数据  
		    //total: "total", 		// json中代表页码总数的数据  
		    records: "records" 		// json中代表数据行总数的数据  
		    //repeatitems: true, 	// 如果设为false，则jqGrid在解析json时，会根据name来搜索对应的数据元素（即可以json中元素可以不按顺序）；而所使用的name是来自于colModel中的name设定。  
		    //id: "id",  			// 
		    
		    //cell: "cell",  		//
		    //userdata: "userdata"	// 
		    //subgrid: {  
		    //    root:"rows",   
		    //    repeatitems: true,   
		    //    cell:"cell"  
		    //}  
		},
        _defaultQueryListJsonReader:{
        	root: function(obj){
				return (obj && $.isArray(obj)) ? obj : [];
			},
			page: function(){
				return 1;
			},
			total: function(){
				return 1;
			},
			records : function(obj){
				return (obj && $.isArray(obj)) ? obj.length : 0;
			},
        	repeatitems: false,
			id: "id"
        },
        _defaultQueryPagedListJsonReader:{
			root: 'list',
			page: 'pageIndex',
			total: function(obj){
				return (obj.count / obj.pageSize) + ((obj.count % obj.pageSize) > 0 ? 1 : 0);
			},
			records: 'count',
			repeatitems: false,
			id: "id"
        },
        _create : function()
        {
            //widget常量
            var element = this.element;
            var _this = this
            var options = this.options;
            
            var heightFunction = null;
            if($.isFunction(options.height)){
            	heightFunction = options.height;
                var heightValue = options.height();
                options = $.extend(options,{
                    height: heightValue
                });
            }
            
            //使用到的options中值
            var _contextPath = options.contextPath;
            var _themeCookieName = options.themeCookieName;
            var _skins = options.skins != null ? options.skins : _default_skins;
            var _gridElementId = $(element).attr("id");
            //支持自动添加pager//符合gridId+pager的div将被制定为pagger
            var _gridPagerId = _gridElementId + "Pager";
            var _$gridPager = $("#" + _gridPagerId);

            //清空element中内容
            //console.log("ajaxPagedList：" + options.type);
            function toModifyGridHeader(event){
            	var grid = this;
            	//alert($.toJsonString(grid.p.colNames));
            	//alert($.toJsonString(grid.p.colModel));
            	$parent = $("body");
            	var modifyGridDivId = _gridPagerId + "ModifyHeaderDiv_";
            	var $headerModifier = $parent.find("#dialog_" + modifyGridDivId);
            	if($headerModifier.size() == 0){
            		$headerModifier = $("<div>").attr("id","dialog_" + modifyGridDivId).addClass("form-table").appendTo($parent);
            		//创建table
            		var $headerModifyTable = $("<table>").appendTo($headerModifier);
            		//逐行创建
            		var colNames = grid.p.colNames;
            		$.each(grid.p.colModel,function(index,colNameTemp){
            			if(!colNameTemp || colNameTemp.name == 'rn' || colNameTemp.name == 'cb'){
            				return true;
            			}
            			var $trTemp = $("<tr>").appendTo($headerModifyTable);
            			//列名td
            			$trTemp.append($("<td>").attr("width","80%").text(colNames[index]).addClass("ui-widget-content"));
            			//是否选中td
            			var $checkBoxTemp = $('<input type="checkbox"/>').attr("name",_gridPagerId + "Header_");
            			if(!colNameTemp.hidden){
            				$checkBoxTemp.attr("checked",true);
            			}
            			$checkBoxTemp.click(function(){
            				if($(this).attr("checked") == "checked"){
            					$(element).jqGrid("showCol", colNameTemp.name);
            					$(element).jqGrid("setGridWidth", $("body").innerWidth());
            				}else{
            					$(element).jqGrid("hideCol", colNameTemp.name);
            					$(element).jqGrid("setGridWidth", $("body").innerWidth());
            				}
            			});
            			$trTemp.append($("<td>").attr("width","20%").addClass("ui-widget-content").append($checkBoxTemp));
            		});
            		//$headerModifier.hide();
            		//alert($headerModifier.html());
            	}
            	
            	DialogUtils.dialog(modifyGridDivId,'编辑',null,240,400);
            }
            
            if(options.type == 'simple'){
            	//直接用el标签生成table，将该table转换为grid的方法
            	tableToGrid("#" + $(this.element).attr("id"),options);
            }else if(options.type === 'ajaxList'){
            	//改变jqGrid的colModel中的一些默认的情况，如默认允许排序，允许根据该字段进行查询
            	if(_this.options.colModel){
            		$.each(_this.options.colModel,function(index,colModelTemp){
            			_this.options.colModel[index] = $.extend({},_this._defaultJqGridColModel,colModelTemp);
            		});
            	}
            	options = $.extend({},_this._defaultJqGridOptions,this.options,{
            		jsonReader: _this._defaultQueryListJsonReader,
            		gridComplete: function(){
            		    $(element).trigger("gridComplete",this);
            		}
            	});
            	
            	options = $.extend(options,{
	            		pgbuttons: false,
				        pginput: false,
				        rowList: []
	            });
            	//pager
            	if(_$gridPager.size() > 0){
            		options = $.extend(options,{
	            		pager: _gridPagerId
	            	});
            	}
            	
            	$(element).jqGrid(options);
            	
            	//pager
            	if(_$gridPager.size() > 0){
            		$(element).jqGrid('navGrid', '#' + _gridPagerId ,{edit:false,add:false,del:false,search:false})
            			.jqGrid('navButtonAdd', '#' + _gridPagerId , {
												caption : "",
												buttonicon : "ui-icon-gear",
												onClickButton : toModifyGridHeader,
												position : "last",
												title : "",
												cursor : "pointer"
											});
            	}
            }else if(options.type === 'ajaxPagedList'){
            	//改变jqGrid的colModel中的一些默认的情况，如默认允许排序，允许根据该字段进行查询
            	if(_this.options.colModel){
            		$.each(_this.options.colModel,function(index,colModelTemp){
            			_this.options.colModel[index] = $.extend({},_this._defaultJqGridColModel,colModelTemp);
            		});
            	}
            	options = $.extend({},_this._defaultJqGridOptions,_this.options,{
            		jsonReader: _this._defaultQueryPagedListJsonReader,
            		gridComplete: function(){
            		    $(element).trigger("gridComplete",this);
            		}
            	});
            	
            	//pager
            	if(_$gridPager.size() > 0){
            		options = $.extend(options,{
	            		pager : _gridPagerId
	            	});
            	}
            	$(element).jqGrid(options);
            	
            	//pager
            	if(_$gridPager.size() > 0){
            		$(element).jqGrid('navGrid', '#' + _gridPagerId ,{edit:false,add:false,del:false,search:false})
            			.jqGrid('navButtonAdd', '#' + _gridPagerId , {
												caption : "",
												buttonicon : "ui-icon-gear",
												onClickButton : toModifyGridHeader,
												position : "last",
												title : "",
												cursor : "pointer"
											});
            	}
            }else{
                options = $.extend({},_this.options);
                $(element).jqGrid(options);
                return ;
            }
            
            //如果高度为 function在数据加载完成后，动态运算高度
            $(element).bind("gridComplete",function(event,grid){
                //console.log("gridComplete:" + grid.p.reccount);
                if(heightFunction && $.isFunction(heightFunction)){
                    var heightValue = heightFunction(grid.p.reccount);
                    $(element).jqGrid('setGridHeight',heightValue);
                }
            });

            
            //如果设置了自动宽度，则自动监听window.resize事件，实现grid的大小随页面变化
            var resizeTimer = null;
            if(options.autowidth){
                $(window).resize(function() {
                    if (resizeTimer != null) {
                        clearTimeout(resizeTimer);
                    }
                    resizeTimer = setTimeout(function() {
                        $(element).jqGrid('setGridWidth',$("body").innerWidth());
                    }, 100);
                });
            }
            resizeTimer = setTimeout(function() {
            	$(element).jqGrid('setGridWidth',$("body").innerWidth());
            }, 500);
        }, 
        _init : function()
        {
            
        }, 
        destroy : function()
        {
            
        }
    });
    

    jQuery.extend($.fn.fmatter, {
		currencyFmatter : function(cellvalue, options, rowdata) {
			return "$" + cellvalue;
		}
	});
	jQuery.extend($.fn.fmatter.currencyFmatter, {
		unformat : function(cellvalue, options) {
			return cellvalue.replace("$", "");
		}
	});
	
})(jQuery);

