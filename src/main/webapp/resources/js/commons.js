/*
 * 通用js
 */
var _defaultContextPath = "/webdemo";

var _contextPath = _defaultContextPath;
if(typeof(contextPath) != undefined){
    
}
var jqueryUIJsPath = _contextPath + "/jquery-ui/js/jquery-ui-1.9.1.custom.min.js";
var wijmoJsPath = _contextPath + "/wijmo/js/jquery.wijmo-complete.all.2.2.2.min.js";

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
                jQuery.getScript(jqueryUIJsPath, function(){
                    //如果加载完对应js，继续加载下一个js
                    _loadSuccess++;
                    _$loadObj.trigger("_loadScriptPathsReady");
                    return true;
                });
            });
            
        }
    },
    //增加onLoad方法
	loadCommonJsAndDocumentReady:function(fn){
	    this.loadScript(function(){
	        $(document).ready(function() {
                fn();
            });
	    },[jqueryUIJsPath,wijmoJsPath]);
        return this;
	}
});


