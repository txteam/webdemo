/*
 * 切换皮肤方法
 */
jQuery.fn.extend({
    skinSwitcher:function(){
        var skinNames = [];
        var skinValues = {};
        var isInit = false;
        //初始化
        function _init(){
            $.each(skins, function(index, skinTemp) {
                skinNames[index]=skinTemp.key;
                skinValues[skinTemp.key]=skinTemp.value;
                return true;
            });
            isInit = true;
        }
        //选择皮肤
        function _switchSkins(skinName){
            var chooseSkin = skinValues[skinName];
            if(chooseSkin != null){
                $.loadScript(function(){
                    window.location.reload();
                },chooseSkin);
            }
        }
        //如果没有初始化，这里进行初始化
        if(!isInit){
            _init();
        }
        $this = $(this);
        $this.change(function(){
            _switchSkins($this.val());
        });
        //$this.wijdropdown();
        //var skinNames = _querySupportSkins();
        $.each(skinNames, function(index,skinNameTemp) {
            $this.append($("<option/>").val(skinNameTemp).text(skinNameTemp));
        });
        
        return $this;
    },

});

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
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
/*
 * 将时间字符串根据配置的format格式化为时间对象
 */
Date.parseToDate = function(str, format)
{
    var dateParseObj = [
        {key:"y+",value:0},
        {key:"M+",value:0},
        {key:"d+",value:0},
        {key:"(h|H)+",value:0},
        {key:"m+",value:0},
        {key:"s+",value:0},
        {key:"S",value:0}
    ];
    $.each(dateParseObj, function(index, k)
    {
        var reTemp = new RegExp("(" + k.key + ")");
        if (reTemp.test(format)){
            k.value = str.substr(format.indexOf(RegExp.$1),RegExp.$1.length);
        }
    });
    var date = new Date(dateParseObj[0].value, dateParseObj[1].value - 1,
            dateParseObj[2].value, dateParseObj[3].value,
            dateParseObj[4].value, dateParseObj[5].value, dateParseObj[6].value);
    return date;
};
/**************** date end*********************/

