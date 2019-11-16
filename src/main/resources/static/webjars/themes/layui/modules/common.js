/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */
 
layui.define(function(exports){
	var $ = layui.$
	,layer = layui.layer
	,laytpl = layui.laytpl
	,setter = layui.setter
	,view = layui.view
	,admin = layui.admin
  
  	//公共业务的逻辑处理可以写在此处，切换任何页面都会执行
  	//……
  	//退出
  	admin.events.logout = function(){
    	//执行退出接口
    	admin.req({
      		url: layui.setter.base + 'json/user/logout.js'
      		,type: 'get'
      		,data: {}
      		,done: function(res){ //这里要说明一下：done 是只有 response 的 code 正常才会执行。而 succese 则是只要 http 为 200 就会执行
        		//清空本地记录的 token，并跳转到登入页
        		admin.exit(function(){
          			location.href = 'user/login.html';
        		});
      		}
    	});
  	};
  
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
	            window.$localEventHandle = $("<_local/>");
	        }
	        $localEventHandle = window.$localEventHandle;
	    }
	    //定义全局事件管理器
	    var _globalEventManagerWin_ = window;
	    var _$globalEventManager = null;
	    while (!_$globalEventManager) {
	        try {
	            if (!_globalEventManagerWin_.closed && _globalEventManagerWin_['$globalEventManager']) {
	                _$globalEventManager = _globalEventManagerWin_['$globalEventManager'];
	                _$globalEventManager.registeGlobalEventListener(window);
	                break;
	            }
	        } catch (e) {
	        	//do nothing
	        }
	        if (!_globalEventManagerWin_.closed && _globalEventManagerWin_.parent != null && _globalEventManagerWin_.parent != _globalEventManagerWin_) {
	            _globalEventManagerWin_ = _globalEventManagerWin_.parent;
	        } else if (!_globalEventManagerWin_.closed && _globalEventManagerWin_.opener != null && _globalEventManagerWin_.opener != _globalEventManagerWin_) {
	            _globalEventManagerWin_ = _globalEventManagerWin_.opener;
	        } else {
	        	//定义一个子window的链表类
	            var ChildWindowLinkedSet = function() {
	            	this.init();
	            };
	            ChildWindowLinkedSet.prototype.init = function(){
	            	this.children = new Array();
	            };
	            //子引用链
	            ChildWindowLinkedSet.prototype.children = null;
	            //用以支持抹去陈旧的window引用
	            ChildWindowLinkedSet.prototype._expungeStaleEntries = function() {
	                var _self = this;
	                var newChilds = $.grep(_self.children, function(childWindowRefTemp, i) {
	                    if (childWindowRefTemp != null && $.isWindow(childWindowRefTemp) && !childWindowRefTemp.closed) {
	                        return true;
	                    } else {
	                        return false;
	                    }
	                });
	                this.children = newChilds;
	            };
	            ChildWindowLinkedSet.prototype._exist = function(childWindowRef) {
	                var _self = this;
	                var existFlag = false;
	                $.each(_self.children, function(i,childWindowRefTemp) {
	                    if (childWindowRef === childWindowRefTemp) {
	                    	//console.log("window is exist.");
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
	            	//console.log("beforeExpungeSize:" + this.children.length);
	                this._expungeStaleEntries();
	                //console.log("beforeAddSize:" + this.children.length);
	                if (childWindowRef != null && $.isWindow(childWindowRef) && !childWindowRef.closed && !this._exist(childWindowRef)) {
	                    this.children.push(childWindowRef);
	                }
	                //console.log("afterAddSize:" + this.children.length);
	            };
	            //获取链表的遍历器
	            ChildWindowLinkedSet.prototype.iterator = function() {
	                this._expungeStaleEntries();
	                //console.log("size:" + this.children.length);
	                return this.children;
	            };
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
	                var _self = this;
	                var _arguments = arguments;
	                var childWindowRefArr = this._childWindowLinkedList.iterator();
	                $.each(childWindowRefArr, function(i, childWindowRefTemp) {
	                    if (childWindowRefTemp && childWindowRefTemp.$localEventHandle) {
	                        var $lehandle = childWindowRefTemp.$localEventHandle;
	                        $lehandle.triggerHandler.apply($lehandle, _arguments);
	                    }
	                });
	            };
	            /**
	             * 事件管理器全局对象
	             */
	            var _$globalEventManager = new GlobalEventManager();
	            _$globalEventManager.registeGlobalEventListener(window);
	            try {
	                //如果跨域时
	            	window['$globalEventManager'] = _$globalEventManager;
	            } catch(e) {
	            }
	            //释放对象
	            GlobalEventManager = null;
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
	})(layui.$);
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
	/** 扩展jquery */
	(function($, undefined){
		$.toArray = function(s){
		    try{
		    	return Array.prototype.slice.call(s);
		    } catch(e){
			    var arr = [];
			    for(var i = 0,len = s.length; i < len; i++){
			    //arr.push(s[i]);
			    arr[i] = s[i];  //据说这样比push快
			    }
			    return arr;
		    }
		};
		$.StringUtils = {};
		$.StringUtils.subEndStr = function(sourceString,length,end){
			if(length == undefined){
				return sourceString;
			}
			if(sourceString.length <= length){
				return sourceString;
			}else{
				if(end == undefined){
					return sourceString.substr(0,length);
				}else{
					return sourceString.substr(0,length) + end;
				}
			}
		};
		$.StringUtils.subendstr = $.StringUtils.subEndStr;
	    $.ObjectUtils = {};
	    $.ObjectUtils.isEmpty = function(data){
	    	if(data == undefined || data == null){
	    		return true;
	    	}else if($.type(data) == 'string' && $.trim(data) == ''){
	            return true;
	        }else if($.type(data) == 'array' && data.length == 0){
	            return true;
	        }else if(!data){
	            return true;
	        }
	        return false;
	    };
	    $.TreeUtils = {};
	    $.TreeUtils.transform = function(data) {
		    var i, l, treeData = [], tmpMap = [];
	        for (i = 0, l = data.length; i < l; i++) {
	            tmpMap[data[i]['id']] = data[i];
	        }
	        for (i = 0, l = data.length; i < l; i++) {
	            if (tmpMap[data[i]['parentId']] && data[i]['id'] != data[i]['parentId']) {
	                if (!tmpMap[data[i]['parentId']]['children'])
	                    tmpMap[data[i]['parentId']]['children'] = [];
	                tmpMap[data[i]['parentId']]['children'].push(data[i]);
	                tmpMap[data[i]['parentId']]['checked'] = false;
	            } else {
	                treeData.push(data[i]);
	            }
	        }
	        return treeData;
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
	    var ajaxErrorFunction = function(XMLHttpRequest,Status,errorThrown) {
	        if (XMLHttpRequest.status == 501) {
	            $.triggerge("sessionLost");
	        } else if (XMLHttpRequest.status == 0) {
	            try {
	                alert('错误', XMLHttpRequest.responseText);
	            } catch (e) {
	                //alert(XMLHttpRequest.responseText);
	            }
	        }else if (XMLHttpRequest.status == 500) {
	        	
	            try {
	                alert('错误', XMLHttpRequest.responseText);
	            } catch (e) {
	                //alert(XMLHttpRequest.responseText);
	            }
	        } else {
	            try {
	                alert('错误', XMLHttpRequest.responseText);
	            } catch (e) {
	                //alert(XMLHttpRequest.responseText);
	            }
	        }
	    };
		/*
		 * 改变jQuery的AJAX默认属性和方法
		 */
		$.ajaxSetup({
			type : 'POST',
			dataType : "json",
			error : ajaxErrorFunction
		});
		$.ajaxSettings.error = ajaxErrorFunction;
	
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
	    $.extend({
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
	            var type = $.type(object);
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
	                        var value = $.toJSONString(object[property]);
	                        if (value !== undefined)
	                            results.push($.toJSONString(property) + ':'
	                                    + value);
	                    }
	                    return '{' + results.join(',') + '}';
	                    break;
	                case 'array':
	                    var results = [];
	                    for ( var i = 0; i < object.length; i++)
	                    {
	                        var value = $.toJSONString(object[i]);
	                        if (value !== undefined) results.push(value);
	                    }
	                    return '[' + results.join(',') + ']';
	                    break;
	            }
	        }
	    });
	    $.toJsonString = $.tojsonstring = $.toJSONString;
	})($);
	
	/** **************** 扩展js中原date对象******************** */
	/* 给事件对象添加format方法，用以将事件对象格式化为字符串 */
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
	/* 将时间字符串根据配置的format格式化为时间对象 */
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
	String.prototype.startWith=function(str){     
	  var reg=new RegExp("^"+str);     
	  return reg.test(this);        
	};
	String.prototype.endWith=function(str){     
	  var reg=new RegExp(str+"$");     
	  return reg.test(this);        
	};
	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, '');
	};
	String.prototype.ltrim = function() {
		return this.replace(/(^\s*)/g, '');
	};
	String.prototype.rtrim = function() {
		return this.replace(/(\s*$)/g, '');
	};
	
  	//对外暴露的接口
  	exports('common', {});
});