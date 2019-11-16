/**

 @Name：layuiAdmin iframe版主入口
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */
 
layui.extend({
  setter: 'config' //配置模块
  ,admin: 'lib/admin' //核心模块
  ,view: 'lib/view' //视图渲染模块
}).define(['setter', 'admin'], function(exports){
  var setter = layui.setter
  ,element = layui.element
  ,admin = layui.admin
  ,tabsPage = admin.tabsPage
  ,view = layui.view
  
  //打开标签页
  ,openTabsPage = function(url, text){
    //遍历页签选项卡
    var matchTo
    ,tabs = $('#LAY_app_tabsheader>li')
    ,path = url.replace(/(^http(s*):)|(\?[\s\S]*$)/g, '');
    
    tabs.each(function(index){
      var li = $(this)
      ,layid = li.attr('lay-id');
      
      if(layid === url){
        matchTo = true;
        tabsPage.index = index;
      }
    });
    
    text = text || '新标签页';
    
    if(setter.pageTabs){
      //如果未在选项卡中匹配到，则追加选项卡
      if(!matchTo){
        $(APP_BODY).append([
          '<div class="layadmin-tabsbody-item layui-show">'
            ,'<iframe src="'+ url +'" frameborder="0" class="layadmin-iframe"></iframe>'
          ,'</div>'
        ].join(''));
        tabsPage.index = tabs.length;
        element.tabAdd(FILTER_TAB_TBAS, {
          title: '<span>'+ text +'</span>'
          ,id: url
          ,attr: path
        });
      }
    } else {
      var iframe = admin.tabsBody(admin.tabsPage.index).find('.layadmin-iframe');
      iframe[0].contentWindow.location.href = url;
    }

    //定位当前tabs
    element.tabChange(FILTER_TAB_TBAS, url);
    admin.tabsBodyChange(tabsPage.index, {
      url: url
      ,text: text
    });
  }
  
  ,APP_BODY = '#LAY_app_body', FILTER_TAB_TBAS = 'layadmin-layout-tabs'
  ,$ = layui.$, $win = $(window);
  
  //初始
  if(admin.screen() < 2) admin.sideFlexible();
  
  //将模块根路径设置为 controller 目录
  layui.config({
    base: setter.base + 'modules/'
  });
  
  //扩展 lib 目录下的其它模块
  layui.each(setter.extend, function(index, item){
    var mods = {};
    mods[item] = '{/}' + setter.base + 'lib/extend/' + item;
    layui.extend(mods);
  });
  
  view().autoRender();
  
  //加载公共模块
  layui.use('common');
  
	var TopLeftMenu = function(options){
        var _this = this;
        _this.options = $.extend({
        	contextPath : '',
        	url : '',
    		element : null,
    		onClick : null
        },options);
        _this._create();
  	};
  	TopLeftMenu.prototype.options = {
  		contextPath : '',
		url : '',
		element : null,
		onClick : null
	};
  	TopLeftMenu.prototype._lastElement = null;
  	TopLeftMenu.prototype._create = function(){
  		var _this = this;
		var _options = _this.options;
		var _url = _options.url;
		var _element = _options.element;
  		
  		_this._lastElement = _element.find(".layui-nav-item:last");
  		_this._init();
  	};
  	TopLeftMenu.prototype._init = function(){
  		var _this = this;
		var _options = _this.options;
		var _url = _options.url;
		var _element = _options.element;
		var _contextPath = _options.contextPath;
		
		//移除
		_element.find(".layui-nav-item:not(.left-menu-fixed)").remove();
		//执行
		$.ajax({
		   type: "GET",
		   async: false,
		   url: _url,
		   success: function(menus){
			   //重点地方，将Layui在页面加载时渲染出来的span.layui-nav-bar提前删除掉
			   $("#layui-layout-left").find('span.layui-nav-bar').remove();
			   
			   $.each(menus,function(index,menuTemp){
					var menu = $.extend({
						href: 'javascript:void(0);',
						icon: 'layui-icon-website',
						text: '' 
					},menuTemp);
					
					var hrefValue = menu.href;
					if(hrefValue){
						if(hrefValue.startWith("http://")){
							menu.href = hrefValue;
						}else if(hrefValue.startWith(_contextPath)){
							menu.href = hrefValue;
						}else if(hrefValue.startWith("/")){
							menu.href = _contextPath + hrefValue.substr(1);
						}else{
							menu.href = _contextPath + hrefValue;
						}
					}
					
					_this._addMenu(menu);
				});
			   
			   	//再次执行渲染
				var layFilter = $("#layui-layout-left").attr('lay-filter');
	       		element.render('layui-layout-left', layFilter);
		   }
		});
	};
	TopLeftMenu.prototype._addMenu = function(menu){
  		/*<li class="layui-nav-item layui-hide-xs top_menu_item" lay-unselect><a href="javascript:void(0);"><i class="layui-icon layui-icon-website">工作台</i></a></li>*/
		var _this = this;
		var _options = _this.options;
		var _url = _options.url;
		var _element = _options.element;
		
		var $i = $("<i>").addClass("layui-icon").addClass(menu.icon).text(menu.text);
		var $a = $("<a href='javascript:void(0);'>").append($i);
		$("<li lay-unselect></li>").addClass("layui-nav-item layui-hide-xs").append($a).insertBefore(_this._lastElement);
		
		$a.click(function(){
			if(_this.options.onClick){
				_this.options.onClick(menu);
			}
		});
		
		return $a;
	};

  //对外输出
  exports('index', {
    openTabsPage: openTabsPage
  });
});
