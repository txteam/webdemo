/**

 @Name：layuiAdmin 公共业务
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */
 
layui.define(function(exports){
	var $ = layui.$
	,element = layui.element
	,layer = layui.layer
	,laytpl = layui.laytpl
	,setter = layui.setter
	,view = layui.view
	,admin = layui.admin;
	
	var LeftMenu = function(options){
		var _this = this;
        _this.options = $.extend({
        	contextPath: '',
        	url : '',
        	element : null,
        	defaultCatalog : null,
    		onClick : null
        },options);
        _this._create();
	};
	LeftMenu.prototype._accordionMap = {};
	LeftMenu.prototype.options = {
		contextPath: '',
		url : '',
		element : null,
		defaultCatalog : null,
		onClick : null
	};
	LeftMenu.prototype._create = function(){
  		var _this = this;
  		_this._init();
  	};
  	LeftMenu.prototype._init = function(){
  		var _this = this;
  		var _options = _this.options;
  		var _element = _options.element;
	};
	LeftMenu.prototype.accordion = function(catalog){
		var _this = this;
		var _options = _this.options;
		var _element = _options.element;
		
		if($.ObjectUtils.isEmpty(catalog)){
			return ;
		}
		if(_this._accordionMap[catalog] != null){
			$.each(_this._accordionMap,function(cat,$accordion){
				$accordion.hide();
			});
			_this._accordionMap[catalog].show();
			return true;
		}
		
		//如果没有则进行动态加载
		var _url = _options.url + "?catalog=" + catalog;
		//同步执行
		$.ajax({
		   	type: "GET",
		   	url: _url,
		   	async: false,
		   	success: function(menuMapList){
		   		if($.ObjectUtils.isEmpty(menuMapList)){
					return ;
				}
			  	//隐藏已显示的菜单
				$.each(_this._accordionMap,function(cat,$accordionTemp){
					$accordionTemp.hide();
				});
			  	
				var $accordion = $('<ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="layadmin-system-side-menu">').attr("id",catalog);
				_element.append($accordion);
				_this._accordionMap[catalog] = $accordion;
				
			   	$.each(menuMapList,function(index,menuMapTemp){
					var menu = $.extend({
						icon: 'layui-icon-auz',
						text: '' 
					},menuMapTemp.menu);
					//写入变量
					$accordion.data('menu_' + index,menu);
					//绑定事件
					$accordion.bind('_select_' + menu.id,{menu: menu},function(event){
						var menu = event.data.menu;
						
						//_this.options.onSelect(menu);
					});
					var $li = _this._addAccordionItem($accordion,menu,index);
					if(!$.ObjectUtils.isEmpty(menuMapTemp.menuList)){
						var roots = $.TreeUtils.transform(menuMapTemp.menuList);
						
						$.each(roots,function(index,menuNode){
							_this._addAccordionChildItem($li,menuNode,index);
						});
					}
				});
			   	
			  	//再次执行渲染
				var layFilter = $accordion.attr('lay-filter');
				element.render($accordion.attr('id'), layFilter);
		   }
		});
	};
	LeftMenu.prototype._addAccordionChildItem = function($parent,menu,index){
		var _this = this;
		var _options = _this.options;
		var _url = _options.url;
		var _element = _options.element;
		/*<dl class="layui-nav-child">
			<dd data-name="forum">
			  <a href="javascript:;">社区系统</a>
			  <dl class="layui-nav-child">
			    <dd data-name="list"><a lay-href="app/forum/list.html">帖子列表</a></dd>
			    <dd data-name="replys"><a lay-href="app/forum/replys.html">回帖列表</a></dd>
			  </dl>
			</dd>
			<dd>
			  <a lay-href="app/message/index.html">消息中心</a>
			</dd>
		</dl>*/
        var $dl = $('<dl class="layui-nav-child"></dl>');
		var $dd = $('<dd></dd>').appendTo($dl);
        var $a = $('<a href="javascript:;"></a>').text(menu.text).appendTo($dd);//.attr('lay-tips',menu.name)
        _element.bind('_select_' + menu.id,{'menu': menu,'$a':$a},function(event){
			var menu = event.data.menu;
			
			if(_this.options.onClick){
				_this.options.onClick(menu);
			}
		});
		$a.click({'menu': menu},function(event){
			var menu = event.data.menu;
			_element.trigger('_select_' + menu.id);
		});
        $parent.append($dl);
        
        if(!$.ObjectUtils.isEmpty(menu.children)){
			$.each(menu.children,function(index,menuNode){
				_this._addAccordionChildItem($dd,menuNode,index);
			});
		}
	};
	LeftMenu.prototype._addAccordionItem = function($accordion,menu,index){
		var _this = this;
		var _options = _this.options;
		var _url = _options.url;
		var _element = _options.element;
		/*<li data-name="get" class="layui-nav-item">
        	<a href="javascript:;" lay-href="//www.layui.com/admin/#get" lay-tips="授权" lay-direction="2">
          		<i class="layui-icon layui-icon-auz"></i><cite>授权</cite>
        	</a>
      	</li>*/
        var $li = $('<li data-name="get" class="layui-nav-item"></li>').attr('data-name',menu.id);
        var $a = $('<a href="javascript:;" lay-direction="2">').appendTo($li);//.attr('lay-tips',menu.name)
        var $i = $('<i class="layui-icon"></i>').addClass(menu.icon).appendTo($a);
        var $cite = $("<cite></cite>").text(menu.text).appendTo($a);
        _element.bind('_select_' + menu.id,{'menu': menu,'$a':$a},function(event){
			var menu = event.data.menu;
			
			if(_this.options.onClick){
				_this.options.onClick(menu);
			}
		});
		$a.click({'menu': menu},function(event){
			var menu = event.data.menu;
			_element.trigger('_select_' + menu.id);
		});
        $accordion.append($li);
        return $li;
	};
	
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
  	TopLeftMenu.prototype._firstMenu = null;
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
						icon: 'layui-icon-website',
						text: '' 
					},menuTemp);
					if(!_this._firstMenu){
						_this._firstMenu = menu;
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
  		/*<li class="layui-nav-item layui-hide-xs top_menu_item" lay-unselect><a href="javascript:;"><i class="layui-icon layui-icon-website">工作台</i></a></li>*/
		var _this = this;
		var _options = _this.options;
		var _url = _options.url;
		var _element = _options.element;
		
		var $i = $("<i>").addClass("layui-icon").addClass(menu.icon).text(menu.text);
		var $a = $("<a href='javascript:;'>").append($i);
		$("<li lay-unselect></li>").addClass("layui-nav-item layui-hide-xs").append($a).insertBefore(_this._lastElement);
		
		_element.bind('_select_' + menu.id,{'menu': menu,'$a':$a},function(event){
			var menu = event.data.menu;
			
			if(_this.options.onClick){
				_this.options.onClick(menu);
			}
		});
		$a.click({'menu': menu},function(event){
			var menu = event.data.menu;
			_element.trigger('_select_' + menu.id);
		});
		return $a;
	};
	//服务于默认选中菜单时调用的方法
	TopLeftMenu.prototype.select = function(id){
		var _this = this;
		var _options = _this.options;
		var _element = _options.element;
		
		_element.triggerge('_select_' + id);
	};
	TopLeftMenu.prototype.selectFirst = function(){
		var _this = this;
		var _options = _this.options;
		var _element = _options.element;
		
		if(_this._firstMenu){
			_element.trigger('_select_' + _this._firstMenu.id);
		}
	};
	
	$.bindge("addTab", function(event, options) {
	    var router = layui.router();
		var hrefValue = options.href;
	    if(hrefValue.startWith("http://")){
	    	options.href = hrefValue;
	    }else if(hrefValue.startWith(_contextPath)){
	    	options.href = hrefValue;
	    }else if(hrefValue.startWith("/")){
	    	options.href = _contextPath + hrefValue.substr(1);
	    }else{
	    	options.href = _contextPath + hrefValue;
	    }
		var options = $.extend({
	    	id: null,
	        title: "",
	        title: null,
	        href: null,
	        iconCls: null
	    }, options);
	    
	    //执行跳转
	    var topLayui = parent === self ? layui : top.layui;
	    topLayui.index.openTabsPage(options.href, options.title || othis.title());
	    return true;
	});
	
	var mainframe = {
		lMenu: null,
		tlMenu: null,
		init: function(contextPath){
			var onTabMenuClick = function(menu){
				if($.ObjectUtils.isEmpty(menu.href)){
					return ;
				}
				var hrefValue = menu.href;
				if(hrefValue){
					if(hrefValue.startWith("http://")){
						menu.href = hrefValue;
					}else if(hrefValue.startWith(contextPath)){
						menu.href = hrefValue;
					}else if(hrefValue.startWith("/")){
						menu.href = contextPath + hrefValue.substr(1);
					}else{
						menu.href = contextPath + hrefValue;
					}
				}
				$.triggerge("addTab",{
		    		id: menu.id,
		            title : menu.text,
		            href : menu.href,
		            iconCls : menu.icon
		        });
			};
			var onNavMenuClick = function(menu){
				if(!menu || !menu.attributes || !menu.attributes.link){
					return ;
				}
				mainframe.lMenu.accordion(menu.attributes.link);
			};
			var onMenuSelect = function(menu){
				if(menu.type == 'tab'){
					onTabMenuClick(menu);
				}else if(menu.type == 'dialog'){
					//onDialogMenuClick(menu);
				}else if(menu.type == 'nav'){
					onNavMenuClick(menu);
				}else if(menu.type == 'event'){
					//onEventMenuClick(menu);
				}else{
					//onDefaultMenuClick(menu);
				}
			};
			
			//定义顶部左侧菜单
			mainframe.tlMenu = new TopLeftMenu({
				contextPath: contextPath,
		  		url : contextPath + 'menu/queryMenuListBySecurity?catalog=client_nav_catalog',
		  		element : $(".layui-layout-left"),
				onClick : onMenuSelect
		  	});
			mainframe.lMenu = new LeftMenu({
				contextPath: contextPath,
		  		url : contextPath + 'menu/queryMenuMapListBySecurity',
		  		element : $(".layui-side-scroll"),
				onClick : onMenuSelect
			});
			mainframe.tlMenu.selectFirst();
		}
	};
	
  	//对外暴露的接口
  	exports('mainframe', mainframe);
});