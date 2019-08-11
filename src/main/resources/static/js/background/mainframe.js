var $mainframe = $("<div/>");
$(function(){
	$(document).ready(function(){
		var $index_layout = $('#index_layout');
		$index_layout.layout({
			fit : true
		});
	    
		var $index_tabs = $('#index_tabs');
		var $index_tabs_menu = $('#index_tabs_menu');
	    var euimenu = $index_tabs_menu.menu();
		/** 初始化页面tabs */
		var euitabs = $index_tabs.tabs({
		    fit : true,
		    border : false,
		    //tabHeight: 44,
		    onBeforeClose:function(title,index){
	        	$.triggerge('mainframe_tab_before_close',[{title:title,index:index}]);
	        },
	        onSelect:function(title,index){
				var selectedTab = $index_tabs.tabs('getTab',title);
				
				var $iframe = $(selectedTab).find("iframe.mainframe_tab");
				if($iframe.attr('refresh') && $iframe.attr('refresh') === 'true'){
					$iframe.attr('src',$iframe.attr('newsrc'));
				}
			},
	        onContextMenu:function(e,title,index) {
	            e.preventDefault();
	            $index_tabs_menu.menu('show', {
	                left : e.pageX,
	                top : e.pageY
	            });
	        }
			,tools:'#tabs-tool'
		});
		//关闭当前
		$('#close_current').click(function(){
	        var currentTab = $index_tabs.tabs('getSelected');
	        if (currentTab.panel('options').closable) {
	            $index_tabs.tabs('close', currentTab.panel('options').title);
	        }
		});
		//关闭其他
		$('#close_other').click(function(){
			var currentTab = $index_tabs.tabs('getSelected');
			var currentTabTitle = currentTab.panel('options').title;
			var tabs = $index_tabs.tabs('tabs');
			var closableTabTitles = [];
			$.each(tabs,function(i,tab){
				var tabTitleTemp = tab.panel('options').title
				if (tab.panel('options').closable && currentTabTitle != tabTitleTemp) {
					closableTabTitles.push(tabTitleTemp);
		        }
		    });
			$.each(closableTabTitles,function(i,title){
				$index_tabs.tabs('close', title);
		    }); 
		});
		//全部关闭
		$('#close_all').click(function(){
			var tabs = $index_tabs.tabs('tabs');
			var closableTabTitles = [];
			$.each(tabs,function(i,tab){
				if (tab.panel('options').closable) {
					closableTabTitles.push(tab.panel('options').title);
		        }
		    });
		    $.each(closableTabTitles,function(i,title){
				$index_tabs.tabs('close', title);
		    }); 
		});
		$('#tabs_tool_menu').click(function(e){
			e.preventDefault();
			var _$this = $(this);
			//var X = $('#img').offset().top;var Y = $('#img').offset().left;
	    	var x = _$this.offset().left + _$this.width() - 50;
	    	var y = _$this.offset().top + _$this.height();
	    	//alert(x + " : " + y);
	    	//var x =  e.pageX;
	    	//var y = e.pageY;
	    	$index_tabs_menu.menu('show', {
	            left : x,
	            top : y
	        });
		});
		function refreshSelectedTab(){
			var tab = $index_tabs.tabs('getSelected');
	        if (tab.panel('options').href) {
	            /*说明tab是以href方式引入的目标页面*/
	            tab.panel('refresh');
	        } else {
	            /*说明tab是以content方式引入的目标页面*/
	            var frames = tab.panel('panel').find('iframe.mainframe_tab');
	            try {
	                if (frames.length > 0) {
	                    for (var i = 0; i < frames.length; i++) {
	                    	var $frame = $(frames[i]);
	                    	//alert('$frame.size()=' + $frame.size());
	                    	if($frame.attr('newsrc')){
	                    		$frame.attr('src',$frame.attr('newsrc'));
	                    	}else{
	                    		$frame.attr('src',$frame.attr('src'));
	                    	}
	                    	//$frames[i].attr('src',$frames[i].attr('newsrc'));
	                        //$frames[i].contentWindow.document.write('');
	                        //$frames[i].contentWindow.close();
	                        //$frames[i].src = $frames[i].src;
	                    }
	                    if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
	                        try {CollectGarbage();} catch (e) {}
	                    }
	                }
	            } catch (e) {}
	        }
		}
		$('#tabs_tool_refresh').click(function(){
			refreshSelectedTab();
		});
		$('#refresh').click(function(){
			refreshSelectedTab();
		});
		var addTab = function(event, options){
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
		        href: null,
		        refresh: false,
		        iconCls: null,
		        contentText : null
		    }, options);
		    
		    //如果指定tab已经存在，则直接将该tab选中即可
		    if ($index_tabs.tabs('exists', options.title)) {
		    	var selectedTab = $index_tabs.tabs('getTab',options.title);
					var $iframe = $(selectedTab).find("iframe.mainframe_tab");
					var oldSrc = $iframe.attr('src');
					if($iframe.attr('refresh') === 'true'){
						$iframe.attr('newsrc',options.href);
					}else{
						$iframe.attr('src',options.href);
					}
		        $index_tabs.tabs('select', options.title);
		        //如果已经存在直接关闭加载进度条
		        DialogUtils.progress('close');
		        return false;
		    } else if (options.href) {
		        //以iframe的形式显示tab
		        var newIframe = '<iframe src="' + options.href + '" width="100%" height="98%" frameborder="0" scrolling="auto" style="border:0;width:100%;height:98%;" class="mainframe_tab"></iframe>';
		        if(options.refreshWhenSelect){
		        	newIframe = '<iframe src=' + options.href + ' newsrc="' + options.href + '" refresh="true" width="100%" height="98%" frameborder="0" scrolling="auto" style="border:0;width:100%;height:98%;" class="mainframe_tab"></iframe>';
		        }
		        $index_tabs.tabs('add', {
		            title : options.title,
		            closable : true,
		            iconCls : options.iconCls,
		            content : newIframe,
		            border : false,
		            fit : true
		        });
		    }
		};
		var refreshTab = function(event, options) {
		    var options = $.extend({
		        id: null,
		        title: "",
		        href: null,
		        refresh: false,
		        iconCls: null,
		        contentText : null
		    }, options);
		    
		    //如果指定tab已经存在，则直接将该tab选中即可
		    if ($index_tabs.tabs('exists', options.title)) {
		        //TODO:后续加入，根据tab中如果存在refreshOnEverySelected属性时每次选中均刷新的逻辑<br/>
		        $index_tabs.tabs('select', options.title);
		        var tab = $index_tabs.tabs('getTab', options.title);
		        if($(tab).find("iframe.mainframe_tab") > 0){
		            var $iframe = $(tab).find("iframe.mainframe_tab");
		            if(!$.ObjectUtils.isEmpty(options.href)){
		                $iframe.attr("src",options.href);
		            }else{
		                var srcTemp = $iframe.attr("src");
		                $iframe.attr("src",srcTemp);
		            }
		        }
		        //如果已经存在直接关闭加载进度条
		        DialogUtils.progress('close');
		        return false;
		    }
		}
		$mainframe.bind("addTab", addTab);
		$mainframe.bind("addOrSelectTab", addTab);
		$mainframe.bind("refreshTab", refreshTab);
		$mainframe.bind("closeSelectedTab",function(event){
			var tab = $index_tabs.tabs('getSelected')
	        if (tab.panel('options').closable) {
	            $index_tabs.tabs('close', tab.panel('options').title);
	        }
		});
		$mainframe.bind("closeTab",function(event, options){
			var options = $.extend({}, {
		        title : ""
		    }, options);
		    
			console.log('closeTab:' + options.title);
			//如果指定tab已经存在，则直接将该tab选中即可
		    if ($index_tabs.tabs('exists', options.title)) {
		    	$index_tabs.tabs('close', options.title);
		        return false;
		    }
		});
	});
	
	$.bindge("closeTab", function(event, options) {
	    $mainframe.trigger("closeTab", options);
	    return true;
	});
	$.bindge("addOrSelectTab", function(event, options) {
	    $mainframe.trigger("addTab", options);
	    return true;
	});
	$.bindge("addTab", function(event, options) {
	    $mainframe.trigger("addTab", options);
	    return true;
	});
	$.bindge("refreshTab", function(event, options) {
		if(options.href){
			var hrefValue = options.href;
		    if ((hrefValue.indexOf("http://") < 0 || hrefValue.indexOf("http://") > 0 ) && hrefValue.indexOf(_contextPath) != 0) {
		        options.href = _contextPath + hrefValue;
		    }
		    if (hrefValue.indexOf("/") == 0 && hrefValue.indexOf(_contextPath) != 0) {
		        options.href = _contextPath + hrefValue;
		    }
		}
	    $mainframe.trigger("refreshTab", options);
	    return true;
	});
	//关闭当前选中的tab
	$.bindge("closeSelectedTab",function(event){
		$mainframe.trigger("closeSelectedTab");
		return true;
	});
	
	//session丢失后跳转到会话丢失页面
	$.bindge("sessionLost",function(event){
	    GlobalDialogUtils.openModalDialog("autoRedirectToLoginPage","登录超时",_contextPath + "/mainframe/toSessionLostError.action",400,300);
	    return true;
	});
	//系统自动跳转到登录页面
	$.bindge("returnLoginPage",function(event){
	    window.location.href = _contextPath + "/mainframe/toLogin.action";
	});

	//定义菜单控件
	$.widget("txcomponent.top_menu",{
		options: {
			url : "",
			onSelect: null
		},
		_create: function(){
			var _this = this;
			var _options = _this.options;
			var _element = _this.element;
			
			_this._load();
		},
		_load: function(){
			var _this = this;
			var _options = _this.options;
			var _url = _options.url;
			
			//同步执行
			$.ajax({
			   type: "GET",
			   async: false,
			   url: _url,
			   success: function(menus){
				   $.each(menus,function(index,menuTemp){
						var menu = $.extend({
							href: 'javascript:void(0);',
							icon: '&#xe60e;',
							text: '' 
						},menuTemp);
						
						_this._addMenu(menu);
					});
			   }
			});
		},
		_addMenu: function(menu){
			var _this = this;
			var _options = _this.options;
			var _url = _options.url;
			var _element = _this.element;

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


			
			var $a = $('<a>').attr('href',menu.href).attr("target","_blank").append('<i class="iconfont">' + menu.icon + '</i>' + menu.text);
			var $li = $('<li>').append($a);
			$(_element).append($li);
			
			_this.element.bind('_select_' + menu.id,{'menu': menu,'$el':$li},function(event){
				var menu = event.data.menu;
				var $el = event.data.$el;
				
				$(_element).children("li").children("a").removeClass("selected");
				$el.children("a").addClass("selected");
				
				_this.options.onSelect(menu);
			});
			$li.click({'menu': menu},function(event){
				var menu = event.data.menu;
				_this.element.trigger('_select_' + menu.id);
			});
		},
		//服务于默认选中菜单时调用的方法
		select: function(id){
			var _this = this;
			var _element = _this.element;
			
			_this.element.trigger('_select_' + id);
		},
	    destroy : function(){
	    	var _this = this;
			var _element = _this.element;
			
			//移除内部内容
			$(_element).empty();
			//调用默认实现
			$.Widget.prototype.destroy.call(this);
	    }
	});
	$.widget("txcomponent.left_menu",{
		options: {
			url: "",
			default_catalog: "",
			onSelect: null
		},
		_catalog2accordionMap: {},
		_create: function(){
			var _this = this;
			var _options = _this.options;
			var _element = _this.element;
			
			_this._load();
		},
		_load: function(){
			var _this = this;
			var _options = _this.options;
			
			_this.createAccordion(_options.default_catalog);
		},
		createAccordion: function(catalog){
			var _this = this;
			var _options = _this.options;
			var _element = _this.element;
			
			if($.ObjectUtils.isEmpty(catalog)){
				return ;
			}
			if(_this._catalog2accordionMap[catalog] != null){
				$.each(_this._catalog2accordionMap,function(cat,$accordion){
					$accordion.hide();
				});
				_this._catalog2accordionMap[catalog].show();
				return ;
			}
			
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
					$.each(_this._catalog2accordionMap,function(cat,$accordionTemp){
						$accordionTemp.hide();
					});
					var $accordion = $('<div style="width:100%; height:750px;"></div>').attr("id",catalog).appendTo(_this.element);
					_this._catalog2accordionMap[catalog] = $accordion;
					$accordion.accordion({
			            fit : true,
			            border : false,
			            onSelect : function(title, index) {
			                //得到选中的面板
			                var menu = $accordion.data('menu_' + index);
			            	$accordion.trigger('_select_' + menu.id);
			            }
			        });
					
				   	$.each(menuMapList,function(index,menuMapTemp){
						var menu = $.extend({
							href: 'javascript:void(0);',
							icon: 'icon-Components',
							text: '' 
						},menuMapTemp.menu);
						//写入变量
						$accordion.data('menu_' + index,menu);
						//绑定事件
						$accordion.bind('_select_' + menu.id,{menu: menu},function(event){
							var menu = event.data.menu;
							
							_this.options.onSelect(menu);
						});
						_this._addAccordionItem($accordion,menu,index);
						
						//树节点
						var $tree = $accordion.find("#" + menu.id + '_tree');
						_this._createTree(menu.id,$tree,menuMapTemp.menuList);
						
						_element.bind('_unselect_other_tree',{menu: menu,$tree: $tree},function(event,accordingItemMenuId){
							var menu = event.data.menu;
							var $tree = event.data.$tree;
							if(menu.id == accordingItemMenuId ){
								return true;
							}
							$tree.find(".tree-node-selected").removeClass("tree-node-selected");
						});
					});
			   }
			});
		},
		_addAccordionItem: function($accordion,menu,index){
			var _this = this;
			var _options = _this.options;
			var _url = _options.url;
			var _element = _this.element;
            
            $accordion.accordion('add', {
                title : menu.text,
                //collapsible : false,
                selected : index == 0 ? true : false,
                iconCls: $.ObjectUtils.isEmpty(menu.icon) ? "icon-Components" :  menu.icon,
                content : '<ul id="' + menu.id + '_tree"></ul>'
            });
		},
		_createTree: function(accordingItemMenuId,$tree,menuList){
			var _this = this;
			var _options = _this.options;
			var _element = _this.element;
			
			if($.ObjectUtils.isEmpty(menuList)){
				return ;
			}
			$tree.tree({
            	idField: 'id',
            	parentField: 'parentId',
            	childrenField: 'childs',
            	/*
            	iconField: function(row){
                    if($.ObjectUtils.isEmpty(row.childs)){
                        return "database";
                    }else{
                        return "database_gear";
                    }
            	},
            	*/
                data: menuList,
                onClick: function(node){
                	$tree.trigger('_select_' + node.id);
                }
            });
            $.each(menuList,function(index,menuTemp){
				$tree.data('menu_' + menuTemp.id,menuTemp);
				
				//绑定事件
				$tree.bind('_select_' + menuTemp.id,{menu: menuTemp},function(event){
					var menu = event.data.menu;
					
					//触发取消其他树节点选中效果
					_element.trigger('_unselect_other_tree',accordingItemMenuId);
					//触发菜单选中方法
					_this.options.onSelect(menu);
				});
			});
		},
	    destroy : function(){
	    	var _this = this;
			var _element = _this.element;
			
			//移除内部内容
			$(_element).empty();
			//调用默认实现
			$.Widget.prototype.destroy.call(this);
	    }
	});
});


$(document).ready(function() {
	function onTabMenuClick(menu){
		onDefaultMenuClick(menu);
	}
	function onDialogMenuClick(menu){
		if($.ObjectUtils.isEmpty(menu.href)){
            return false;
        }
	}
	function onNavMenuClick(menu){
		if(!menu || !menu.attributes || !menu.attributes.link){
			return ;
		}
		$(".left").left_menu('createAccordion',menu.attributes.link);
	}
	function onEventMenuClick(menu){
		if($.ObjectUtils.isEmpty(menu.event)){
            return false;
        }
		$.triggerge(menu.event,menu);
	}
	function onDefaultMenuClick(menu){
		if($.ObjectUtils.isEmpty(menu.href)){
            return false;
        }
        if(menu.refresh){
        	$.triggerge("addTab",{
        		refresh : true,
        		id: menu.id,
                title : menu.text,
                href : menu.href,
                iconCls : menu.icon
            });
        }else{
        	$.triggerge("addTab",{
        		id: menu.id,
                title : menu.text,
                href : menu.href,
                iconCls : menu.icon
            });
        }
        return false;
	}
	var onMenuSelect = function(menu){
		//alert($.toJsonString(menu));
		if(menu.type == 'tab'){
			onTabMenuClick(menu);
		}else if(menu.type == 'dialog'){
			onDialogMenuClick(menu);
		}else if(menu.type == 'nav'){
			onNavMenuClick(menu);
		}else if(menu.type == 'event'){
			onEventMenuClick(menu);
		}else{
			onDefaultMenuClick(menu);
		}
	}
	$(".nav").top_menu({
		//url: /*[[@{/menu/queryMenuListBySecurity?catalog=nav_catalog}]]*/'',
		url: _contextPath + 'menu/queryMenuListBySecurity?catalog=operator_nav_catalog',
		onSelect: onMenuSelect
	});
	$(".left").left_menu({
		default_catalog: 'workbench_catalog',
		//url: /*[[@{/menu/queryMenuMapListBySecurity}]]*/'',
		url: _contextPath + 'menu/queryMenuMapListBySecurity',
		onSelect: onMenuSelect
	});
	//默认选中菜单工作台: workbench_nav_menu
	$(".nav").top_menu('select','operator_workbench_nav_menu');
	
	/* 最大化窗口 */
	$("#screen_handle").on("click", function (e) {
		e.preventDefault();
		
		if (!$(this).find('i').hasClass("icon-screen-restore")) {
			var docElm = document.documentElement;
			var full = docElm.requestFullScreen || docElm.webkitRequestFullScreen ||
			docElm.mozRequestFullScreen || docElm.msRequestFullscreen;
			"undefined" !== typeof full && full && full.call(docElm);
			
			$(this).find('i').removeClass("icon-screen-full");
			$(this).find('i').addClass("icon-screen-restore");
		} else {
			document.exitFullscreen ? document.exitFullscreen()
					: document.mozCancelFullScreen ? document.mozCancelFullScreen()
					: document.webkitCancelFullScreen ? document.webkitCancelFullScreen()
					: document.msExitFullscreen && document.msExitFullscreen();
					
			$(this).find('i').removeClass("icon-screen-restore");
			$(this).find('i').addClass("icon-screen-full");
	    }
	});
});

