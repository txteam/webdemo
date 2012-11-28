/**************** date start*********************/
/*
 * 给事件对象添加format方法，用以将事件对象格式化为字符串
 */
Date.prototype.format = function(format) {
    var o = {
        "M+" : this.getMonth() + 1, // month
        "d+" : this.getDate(), // day
        "h+" : this.getHours(), // hour
        "m+" : this.getMinutes(), // minute
        "s+" : this.getSeconds(), // second
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" : this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
/*
 * 将时间字符串根据配置的format格式化为时间对象
 */
Date.parseToDate = function(str, format) {
    var dateParseObj = [{
        key : "y+",
        value : 0
    }, {
        key : "M+",
        value : 0
    }, {
        key : "d+",
        value : 0
    }, {
        key : "(h|H)+",
        value : 0
    }, {
        key : "m+",
        value : 0
    }, {
        key : "s+",
        value : 0
    }, {
        key : "S",
        value : 0
    }];
    $.each(dateParseObj, function(index, k) {
        var reTemp = new RegExp("(" + k.key + ")");
        if (reTemp.test(format)) {
            k.value = str.substr(format.indexOf(RegExp.$1), RegExp.$1.length);
        }
    });
    var date = new Date(dateParseObj[0].value, dateParseObj[1].value - 1, dateParseObj[2].value, dateParseObj[3].value, dateParseObj[4].value, dateParseObj[5].value, dateParseObj[6].value);
    return date;
};
/**************** date end*********************/

/**************** tabs start*********************/
/*
 * 定义tab
 */
(function($, undefined) {
    $.widget("tx.txtabs", $.ui.tabs, {
        options : {
            tabsHandleContainerId : null
        },
        _create : function() {
            this._super();

            var that = this;

            this.element.css({
                "padding-bottom" : "0px",
                "padding-left" : "0px",
                "padding-right" : "0px",
                "padding-top" : "0px"
            });
            this._getList().css({
                "border-left-width": "0px",
                "border-top-width": "0px",
                "border-right-width": "0px",
                "border-bottom-width": "0px"
            });
        },
        // allow overriding how to find the list for rare usage scenarios (#7715)
        _getList : function() {
            return this.element.find("#" + this.options.tabsHandleContainerId).find("ol,ul").eq(0);
        }
    });
})(jQuery);
/**************** tabs end*********************/

/*
 * 定义tab
 */
(function($, undefined) {
    $(document).ready(function(){
        
    });
    $.extend({
        switchcheme: function(id,contextPath,template){
            var $themes = $(document).find("link[title=theme]");
            if($themes.filter("#"+id).size() > 0){
                $themes.not("#"+id).attr("disabled","disabled");
                $themes.filter("#"+id).removeAttr("disabled");
            }else{
                $themes.attr("disabled","disabled");
                $themes.last().after(
                    $("<link/>").attr({
                        rel:"stylesheet",
                        type:"text/css",
                        title:"theme",
                        id:id,
                        href:template.replace("{contextPath}",contextPath).replace("{id}",id)
                    })
                );
            }
        }    
    });
    $.widget("tx.chemeswitcher",{
        options : {
            contextPath : ".",
            skins:null
        },
        _create : function() {
            
        }
    });
})(jQuery);

