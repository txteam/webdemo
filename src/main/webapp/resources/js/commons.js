/*
 * 通用js
 */
var _defaultContextPath = "/webdemo";
var _contextPath = _defaultContextPath;
//系统需要统一加载的js及样式表单
var autoLoadScripts = [
    _contextPath + "/jquery-ui/js/jquery-ui-1.9.1.custom.min.js",
    _contextPath + "/wijmo/js/jquery.wijmo-complete.all.2.2.2.min.js"
];
//系统支持的皮肤
var skins = [
    {key:"cupertino",value:_contextPath + "/css/commons_cupertino.css"},
    {key:"redmond",value:_contextPath + "/css/commons_redmond.css"},
    {key:"smoothness",value:_contextPath + "/css/commons_smoothness.css"}
];
//扩展ready方法
/**
 * 通过该方法，给页面添加了
 * $.onLoad方法，代理了js的加载过程
 */
jQuery.extend({
    //消息方法
    alert:function(msg){
        alert(msg);
    },
    //加载js
    loadScript:function(fn,scriptPaths){
        if(scriptPaths == null || !jQuery.isArray(scriptPaths)){
            fn();
        }else{
            var scriptPathsLength = scriptPaths.length;
            var _triggerNum= 0;
            var _loadSuccess = 0;
            var _$loadObj = $("<loadScriptObj>"); 
            //给对象绑定事件
            _$loadObj.bind("_loadScriptPathsReady",function(event){
                _triggerNum++;
                if(_loadSuccess < scriptPathsLength){
                    if(_triggerNum == scriptPathsLength){
                        this.alert("js加载失败.请检查js路径");
                    }
                    return true;
                }else{
                    fn();
                }
            });
            //每加载一个
            $.each(scriptPaths, function(index, scriptPathTemp) {
                jQuery.getScript(scriptPathTemp, function(){
                    //如果加载完对应js，继续加载下一个js
                    _loadSuccess++;
                    _$loadObj.trigger("_loadScriptPathsReady");
                    return true;
                });
            });
            
        }
    },
    //增加onLoad方法
	loadReady:function(fn){
	    this.loadScript(function(){
	        $(document).ready(function() {
                fn();
            });
	    },autoLoadScripts);
        return this;
	}
});
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


