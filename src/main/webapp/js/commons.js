/************ 用于统一页面样式 ************** */
$(document).ready(function() {
    //如果页面body的样式class没有special标志，则自动渲染页面
    if(window.parent && window.parent.DiologUtils){
        
    }
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

$(document).ready(function() {
    try{
        if(window.parent && window.parent.DiologUtils){
            window.parent.DiologUtils.progress('close');
        }
    }catch(e){}
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
        	//console.log("build global event handler center.");
        	//定义一个子window的链表类
            var ChildWindowLinkedSet = function() {
            };
            //子引用链
            ChildWindowLinkedSet.prototype.childs = [];
            //用以支持抹去陈旧的window引用
            ChildWindowLinkedSet.prototype._expungeStaleEntries = function() {
                var _self = this;
                var newChilds = $.grep(_self.childs, function(childWindowRefTemp, i) {
                    if (childWindowRefTemp != null && $.isWindow(childWindowRefTemp) && !childWindowRefTemp.closed) {
                        return true;
                    } else {
                        return false;
                    }
                });
                this.childs = newChilds;
            };
            ChildWindowLinkedSet.prototype._exist = function(childWindowRef) {
                var _self = this;
                var existFlag = false;
                $.each(_self.childs, function(i,childWindowRefTemp) {
                    if (childWindowRef === childWindowRefTemp) {
                    	console.log("window is exist.");
                    	existFlag = true;
                    	//跳出循环
                    	return false;
                    } else {
                    	//继续循环
                        return true;
                    }
                });
                return existFlag;
            }
            //添加,能调用到该方法的子页面，应该都是统一域中的
            ChildWindowLinkedSet.prototype.add = function(childWindowRef) {
                this._expungeStaleEntries();
                if (childWindowRef != null && $.isWindow(childWindowRef) && !childWindowRef.closed && !this._exist(childWindowRef)) {
                    this.childs.push(childWindowRef);
                }
            };
            //获取链表的遍历器
            ChildWindowLinkedSet.prototype.iterator = function() {
                this._expungeStaleEntries();
                return this.childs;
            }
            //全局事件管理器
            var GlobalEventManager = function(config) {
            };
            GlobalEventManager.prototype._childWindowLinkedList = new ChildWindowLinkedSet();
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
                        console.log("window._$localEventHandle.trigger:" + _arguments[0]);
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
        	//console.log("bind event" + arguments[0]);
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
        	//console.log("trigger event" + arguments[0]);
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
	
    /**
     * ajax调用错误信息处理 
     */
    var easyuiErrorFunction = function(XMLHttpRequest,Status,errorThrown) {
    	DialogUtils.progress('close');
        if (XMLHttpRequest.status == 501) {
            $.triggerge("sessionLost");
        } else if (XMLHttpRequest.status == 500) {
            try {
                GlobalDialogUtils.alert('错误', XMLHttpRequest.responseText);
            } catch (e) {
                alert(XMLHttpRequest.responseText);
            }
        } else {
            try {
                GlobalDialogUtils.alert('错误', XMLHttpRequest.responseText);
            } catch (e) {
                alert(XMLHttpRequest.responseText);
            }
        }
    }; 
    $.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
    $.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
    $.fn.tree.defaults.onLoadError = easyuiErrorFunction;
    $.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
    $.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
    $.fn.form.defaults.onLoadError = easyuiErrorFunction;
	/*
	 * 改变jQuery的AJAX默认属性和方法
	 */
	$.ajaxSetup({
		type : 'POST',
		dataType : "json",
		error : easyuiErrorFunction
	});
	$.ajaxSettings.error = easyuiErrorFunction;
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
    	    if(oldStr){
    	        if(oldStr.length > maxLength){
                    oldStr = oldStr.substring(0,maxLength) + suffix;
                }
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
//全局的dialogUtils对象
var GlobalDialogUtils = null;
(function($, undefined) {
    var _globalEventManagerWin_ = window;
    while (!GlobalDialogUtils) {
        try {
            if (!_globalEventManagerWin_.closed && _globalEventManagerWin_.GlobalDialogUtils) {
            	GlobalDialogUtils = _globalEventManagerWin_.GlobalDialogUtils;
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
            var ChildWindowLinkedList = 

            //释放对象
            GlobalEventManager = null;
            try {
                //如果跨域时
            	GlobalDialogUtils = DialogUtils;
            } catch(e) {
            }
        }
    }
    //释放引用
    _globalEventManagerWin_ = null;
    
    /*
     * showType:定义消息窗体如何显示 可用值有: null,slide,fade,show. 默认值是slide.
     * showSpeed: 定义消息窗体最终显示的毫秒数. 默认值是 600.
     * width: 定义消息窗体宽度.默认值是 250.
     * height: 定义消息窗体高度. 默认值是100.
     * msg: 显示的消息文本.
     * title: 标题文本显示到panel的头部的.
     * timeout: 如果定义为 0,消息窗体将不会关闭除非用户关闭.定义为不是0,消息窗体将在时间超时后自动关闭. 
     */
    DialogUtils._show = function(config){
    	var option = $.extend({},{
    		title:'show message title',
    		msg:'',
    		width:370,
    		height:150,
    		timeout:10000,
    		showType:'slide'
    	},config);
    	$.messager.show(option);
    };
    DialogUtils.show = function(config){
    	GlobalDialogUtils._show(option);
    };
    /**
     * 消息提醒
     */
    DialogUtils.tip = function(msg,width,height,timeout){
    	var option = $.extend({},{
    		title:'提示',
    		msg: msg,
    		width:200,
    		height:100,
    		timeout:5000,
    		showType:'slide'
    	},{
    		msg: msg,
    		width: width,
    		height: height,
    		timeout: (timeout && timeout > 0) ? timeout : 3000
    	});
    	GlobalDialogUtils._show(option);
    };
    //window.show = DialogUtils.show;
    /*
     * 显示一个alter窗体.参数:
     * title: 标题文本,显示在panel的头部的.
     * msg: 显示的消息文本.
     * icon:显示icon图片,可用值有: error,question,info,warning.
     * fn: 窗体关闭的时候触发的回调函数. 
     */
    DialogUtils._alert = function(title, msg, icon, fn){
    	$.messager.alert(title, msg, icon, fn);
    };
    DialogUtils.alert = function(title, msg, icon, fn){
    	GlobalDialogUtils._alert(title, msg, icon, fn);
    };
    //window.alert = function(msg){
    //	DialogUtils.alert("alert",msg,warning);
    //};
    /*
     * 显示一个确认消息窗体有一个OK和一个Cancel按钮,参数:
     * title:标题文本显示在panel的头部的.
     * msg:显示的消息文本.
     * fn(b): 回调函数,当用户点击OK按钮传入true值到函数,其他则传入false. 
     */
    DialogUtils._confirm = function(title, msg, fn){
    	$.messager.confirm(title, msg, fn);
    };
    DialogUtils.confirm = function(title, msg, fn){
    	GlobalDialogUtils._confirm(title, msg, fn);
    };
    //window.confirm = function(msg,fn){
    //	$.messager.confirm("confirm", msg, fn);
    //};
    /*
     * 显示一个消息窗体,一个OK和一个Cancel按钮,提示用户输入一些文本,参数:
     * title: 显示到panel头部的标题文本.
     * msg: 显示的消息文本.
     * fn(val): 回调函数,value参数是用户输入的值. 
     */
    DialogUtils._prompt = function(title, msg, fn){
    	$.messager.prompt(title, msg, fn);
    };
    DialogUtils.prompt = function(title, msg, fn){
    	GlobalDialogUtils._prompt(title, msg, fn);
    };
    //window.prompt = function(msg,fn){
    //	$.messager.confirm("prompt", msg, fn);
    //};
    /*
     * 显示一个进度消息窗体. 
     * 选项定义如下:
     * title: 显示到panel头部的标题文本,默认值''. 
     * msg: 消息框的body文本,默认值''. 
     * text: 这个文本显示到进度条上, 默认未定义. 
     * interval: 每个进度更新的毫秒值长度,默认值 300. 
     * 方法定义如下:
     * bar:得到 progressbar 对象. 
     * close: 关闭进度条窗体. 
     */
    DialogUtils._progress = function(){
    	$.messager.progress.apply($.messager,Array.prototype.slice.call(arguments));
    };
    DialogUtils.progress = function(obj){
    	if($.type(obj) === "string"){
    		DialogUtils._progress(obj);
    	}else{
    		var opt = $.extend({},{
    			interval: 100
        	},obj);
    		DialogUtils._progress(opt);
    	}
    };
    
    /*
     * 默认dialogpe配置
     */
    DialogUtils._defaultDialogConfig = {
    	title : '窗口标题',
    	collapsible : false,
    	minimizable : false,
    	maximizable : false,
    	resizable : false,
    	inline: false,
    	modal: false
    	/*
    	onOpen : function() {
    	    DialogUtils.progress({
    	        title : '提示',
    	        text : '数据处理中，请等待....'
            });
        }
        */
    	//toolbar : []
    	//buttons : []
    };
    /**
     * 当前页面Dialog关闭句柄
     */
    DialogUtils._currentDialogCloseHandlers = {
    };
    /*
     * 创建dialog 
     */
    DialogUtils._createOrOpenDialog = function(dialogHandleId,$dialogHandle,config){
    	//生成对应
    	var option = $.extend({},DialogUtils._defaultDialogConfig, config);
    	option.width = option.width != 0 ? option.width : '400';
    	option.height = option.height != 0 ? option.height : '300';
    	
    	//生成关闭句柄
    	if(!DialogUtils._currentDialogCloseHandlers[dialogHandleId]){
    		//已经绑定过全局关闭事件的对话框体则不在重复绑定
    		DialogUtils._currentDialogCloseHandlers[dialogHandleId] = true;
    		$.bindge("close_dialog_" + dialogHandleId,function(){
    			$("#" + dialogHandleId).dialog("close");
    		});
    	}
    	return $dialogHandle.dialog(option);
    };
    /*
     * 根据id关闭对话框
     * @param id
     */
    DialogUtils.closeDialogById = function(dialogId){
    	var $dialogHandle = $("#" + dialogId);
    	if($dialogHandle.size() > 0){
    	   $dialogHandle.dialog("close");
    	}else{
    	   $.triggerge("close_dialog_" + dialogId);
    	}
    };
    DialogUtils.closeById = DialogUtils.closeDialogById;
    /*
     * 打开对话框
     */
    DialogUtils.dialog = function(dialogHandleId,config,onClose){
    	var $dialogHandle = null;
    	//获取到dialog句柄
    	$dialogHandle = $("#"+dialogHandleId);
    	if($dialogHandle.size() == 0){
    		$dialogHandle = $("<div/>").attr("id",dialogHandleId);
    		config.title && $dialogHandle.attr("title",config.title);
    		$("body").append($dialogHandle);
    	}else{
    		config.title && $dialogHandle.attr("title",config.title)
    	}
    	
    	var option = $.extend({},{
    		title : title,
    	    width: width,   
    	    height: height,
    	    closed: false,   
    	    cache: false,
    	    modal: false,
    	    onClose: onClose
    	},config);
    	var _dialog = DialogUtils._createOrOpenDialog(dialogHandleId,$dialogHandle,config);
    	return _dialog;
    };
    /*
     * 打开对话框
     * notIframeDialog如果不设定值默认为iframeDialog
     * 默认为利用iframe打开
     * 由于iframe为固定大小所以这里不用考虑随着框体大小变化iframe大小变化的情况
     */
    DialogUtils.openDialog = function(dialogHandleId,title,href,width,height,onClose,buttons,cache,notIframeDialog){
    	var $dialogHandle = null;
    	//获取到dialog句柄
    	$dialogHandle = $("#"+dialogHandleId);
    	if($dialogHandle.size() == 0){
    		$dialogHandle = $("<div/>").attr("id",dialogHandleId);
    		$("body").append($dialogHandle);
    	}
    	title && $dialogHandle.attr("title",title);
    	
    	//如果是iframe类型的dialog
    	if(!notIframeDialog){
    		$dialogHandle.css("overflow","hidden");
    		$iframe = $dialogHandle.find("iframe");
    		if($iframe.size() == 0){
    			$dialogHandle.empty();
    			$iframe = $('<iframe scrolling="auto" id=dialogIframe_' + dialogHandleId + ' frameborder="0" border="0" src="" style="width:100%;height:100%;">');
    			var $iframediv = $('<div style="width:100%;height:100%;overflow:hidden"></div>').append($iframe);
    			$dialogHandle.append($iframediv);
    			$iframe.attr('src',href);
    		}else{
    	    	if(!cache){
    	    		$iframe.attr('src',href);
    	    	}else{
    	    		if($iframe.attr('src') != href){
    	    			$iframe.attr('src',href);
    	    		}
    	    	}
    		}
    		
        	var _dialog = DialogUtils._createOrOpenDialog(dialogHandleId,$dialogHandle,{
        		title : title,
        	    width: width,   
        	    height: height,
        	    closed: false,   
        	    cache: true,
        	    modal: false,
        	    buttons: buttons,
        	    onClose: onClose
        	});
        	return _dialog;
    	}else{
        	var _dialog = DialogUtils._createOrOpenDialog(dialogHandleId,$dialogHandle,{
        		title : title,
        		href: href,
        	    width: width,   
        	    height: height,
        	    closed: false,   
        	    cache: false,
        	    modal: false,
        	    buttons: buttons,
        	    onClose: onClose
        	});
        	return _dialog;
    	}
    };
    /*
     * 打开对话框
     * notIframeDialog如果不设定值默认为iframeDialog
     */
    DialogUtils.openModalDialog = function(dialogHandleId,title,href,width,height,onClose,buttons,cache,notIframeDialog){
    	var $dialogHandle = null;
    	//获取到dialog句柄
    	$dialogHandle = $("#"+dialogHandleId);
    	if($dialogHandle.size() == 0){
    		$dialogHandle = $("<div/>").attr("id",dialogHandleId);
    		$("body").append($dialogHandle);
    	}
    	title && $dialogHandle.attr("title",title);
    	
    	//如果是iframe类型的dialog
    	if(!notIframeDialog){
    		$dialogHandle.css("overflow","hidden");
    		$iframe = $dialogHandle.find("iframe");
    		if($iframe.size() == 0){
    			$dialogHandle.empty();
    			$iframe = $('<iframe scrolling="auto" id=dialogIframe_' + dialogHandleId + ' frameborder="0" border="0" src="" style="width:100%;height:100%;">');
    			var $iframediv = $('<div style="width:100%;height:100%;overflow:hidden"></div>').append($iframe);
    			$dialogHandle.append($iframediv);
    			$iframe.attr('src',href);
    		}else{
    	    	if(!cache){
    	    		$iframe.attr('src',href);
    	    	}else{
    	    		if($iframe.attr('src') != href){
    	    			$iframe.attr('src',href);
    	    		}
    	    	}
    		}
    		
    		var _dialog = DialogUtils._createOrOpenDialog(dialogHandleId,$dialogHandle,{
        		title : title,
        	    width: width,   
        	    height: height,
        	    closed: false,
        	    cache: true,
        	    modal: true,
        	    buttons: buttons,
        	    onClose: onClose
        	});
        	return _dialog;
    	}else{
    		//如果不是dialog对话框
        	var _dialog = DialogUtils._createOrOpenDialog(dialogHandleId,$dialogHandle,{
        		title : title,
        		href: href,
        	    width: width,   
        	    height: height,
        	    closed: false,
        	    cache: cache,
        	    modal: true,
        	    buttons: buttons,
        	    onClose: onClose
        	});
        	return _dialog;
    	}
    };

    /**
     * dialog默认配置
     */
    DialogUtils._defaultWindowConfigs = {
    	title : '窗口标题',
    	collapsible : true,
    	minimizable : true,
    	maximizable : true,
    	closable : true,
    	closed : false,
    	draggable : true,
    	resizable : true,
    	shadow : true,
    	inline : true,
    	modal : false
    };
    /**
     * 当前页面Dialog关闭句柄
     */
    DialogUtils._currentWinCloseHandlers = {
    };
    /**
     * 创建或打开dialog
     * @param config
     */
    DialogUtils._createOrOpenWindow = function(winHandleId,config){
    	var $winHandle = null;
    	//获取到dialog句柄
    	$winHandle = $("#"+winHandleId);
    	
    	if($winHandle.size() == 0){
    		$winHandle = $("<div/>").attr("id",winHandleId);
    		config.title && $dialogHandle.attr("title",config.title);
    		$("body").append($winHandle);
    	}else{
    		config.title && $winHandle.attr("title",config.title)
    	}
    	
    	//生成对应
    	var option = $.extend({},DialogUtils._defaultWindowConfigs, config);
    	option.width = option.width != 0 ? option.width : '800';
    	option.height = option.height != 0 ? option.height : '600';
    	
    	//生成关闭句柄
    	if(!DialogUtils._currentWinCloseHandlers[winHandleId]){
    		//已经绑定过全局关闭事件的对话框体则不在重复绑定
    		DialogUtils._currentWinCloseHandlers[winHandleId] = true;
    		$.bindge("close_window_" + winHandleId,function(){
    			$("#" + winHandleId).dialog("close");
    		});
    	}
    	return $winHandle.window(option);
    };
    /*
     * 根据id关闭窗体
     * @param id
     */
    DialogUtils.closeWindowById = function(winHandleId){
    	var $winHandle = $("#" + winHandleId);
    	if($winHandle.size() > 0){
    		$winHandle.dialog("close");
    	}else{
    	   $.triggerge("close_window_" + winHandleId);
    	}
    };
    /*
     * 打开窗体
     */
    DialogUtils.window = function(id,config){
    	var _window = DialogUtils._createOrOpenWindow(id,config);
    	return _window;
    };
    /*
     * 打开窗体
     */
    DialogUtils.openWindow = function(id,title,href,width,height,onClose){
    	var _window = DialogUtils._createOrOpenWindow(id,{
    		title : title,
    		href: href,
    	    width: width,   
    	    height: height,
    	    closed: false,   
    	    cache: false,
    	    modal: false,
    	    onClose: onClose
    	});
    	return _window;
    };
    /*
     * 打开窗体
     */
    DialogUtils.openModalWindow = function(id,title,href,width,height,close){
    	var _window = DialogUtils._createOrOpenWindow(id,{
    		title : title,
    		href: href,
    	    width: width,   
    	    height: height,
    	    closed: false,   
    	    cache: false,
    	    modal: true,
    	    onClose: close
    	});
    	return _window;
    };
})(jQuery); 


/**
 * 扩展tree 
 * 使其支持平滑数据格式
 * 支持指定几个主要字段
 */
$.fn.tree.defaults.loadFilter = function(data) {
    var opt = $(this).data().tree.options;
    var idField, textField, parentField,iconField,childrenField,checkedField;
    var notIncludeTarget =  opt.notIncludeTarget;
    if (opt.parentField) {
        idField = opt.idField || 'id';
        parentField = opt.parentField;
        textField = opt.textField || 'text';
        iconField = opt.iconField || 'iconCls';
        checkedField = opt.iconField || 'checked';
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idField]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                if(!notIncludeTarget){
                	data[i]['attributes'] = data[i];
                } 
                data[i]['text'] = data[i][textField];
                data[i]['iconCls'] = $.isFunction(iconField) ? iconField.call(iconField,data[i]) : data[i][iconField];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
                tmpMap[data[i][parentField]]['checked'] = false;
            } else {
            	if(!notIncludeTarget){
            		data[i]['attributes'] = data[i];
                }
                data[i]['text'] = data[i][textField];
                data[i]['iconCls'] = $.isFunction(iconField) ? iconField.call(iconField,data[i]) : data[i][iconField];
                data[i]['checked'] = data[i][checkedField];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }else{
        textField = opt.textField || 'text';
        iconField = opt.iconField || 'iconCls';
        childrenField = opt.childrenField || 'children';  
        function iteratorTreeData(item){
            if(item == null){
                return ;
            }
            if(!notIncludeTarget){
            	item['attributes'] = item;
            }
            item['text'] = item[textField];
            item['iconCls'] = $.isFunction(iconField) ? iconField.call(iconField,item) : item[iconField];
            item['children'] = item[childrenField];
            if(!$.ObjectUtils.isEmpty(item['children'])){
            	var k = 0;
            	var length = item['children'].length;
                for(k = 0 ; k < length ; k++){
                    iteratorTreeData(item['children'][k]);
                }
            }
        }
        for (i = 0, l = data.length; i < l; i++) {
        	if(data[i] != null){
        		iteratorTreeData(data[i]);
        	}
        }
    }
    return data;
};

/**
 * 扩展treegrid
 * 使其支持平滑数据格式
 * 支持指定几个主要字段
 */
$.fn.treegrid.defaults.loadFilter = function(data) {
    var opt = $(this).data().treegrid.options;
    var idField, textField, parentField,iconField,iconField;
    if (opt.parentField) {
        idField = opt.idField || 'id';
        textField = opt.textField || 'text';
        iconField = opt.iconField || 'iconCls';
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idField]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textField];
                data[i]['iconCls'] = $.isFunction(iconField) ? iconField.call(iconField,data[i]) : data[i][iconField];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textField];
                data[i]['iconCls'] = $.isFunction(iconField) ? iconField.call(iconField,data[i]) : data[i][iconField];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }else{
        textField = opt.textField || 'text';
        iconField = opt.iconField || 'iconCls';
        childrenField = opt.childrenField || 'children';
        function iteratorTreeData(item){
            if(item == null){
                return ;
            }
            item['text'] = item[textField];
            item['iconCls'] = $.isFunction(iconField) ? iconField.call(iconField,item) : item[iconField];
            item['children'] = item[childrenField];
            if(!$.ObjectUtils.isEmpty(item['children'])){
            	var k = 0;
            	var length = item['children'].length;
                for(k = 0 ; k < length ; k++){
                    iteratorTreeData(item['children'][k]);
                }
            }
        }
        for (i = 0, l = data.length; i < l; i++) {
            iteratorTreeData(data[i]);
        }
    }
    return data;
};

/**
 * 扩展treegrid 
 * 使其支持平滑数据格式
 * 支持指定几个主要字段
 */
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

/**
 * 修改默认的验证器设置
 */
$(document).ready(function(){
	if($.validator){
		$.validator.config({
			stopOnError: false,
		    theme: 'yellow_right'
		});
	}
});

