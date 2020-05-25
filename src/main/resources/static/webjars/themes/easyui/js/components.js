//控件：选择虚中心
$.widget("txcomponent.selectVirtualCenter",{
	options: {
		contextPath : "",
		width : 600,
		height : 500,
		title : "请选择虚中心",
		eventName : "selectVirtualCenter",
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		selectHandler: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + 'virtualCenter/toSelect?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("selectVirtualCenter",
				_option.title,
				action,
				_option.width,
				_option.height,
				function(){
					//onClose doNothing
				},
				{},
				[{
					text : '确认',
					handler : function() {
					    if(_option.handler){
                            _option.handler.call(_option.handler,_option._data);
                        }
						_option.openDialogHandler.closeDialogById("selectVirtualCenter");
					}
				}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("select_virtualcenter_" + _option.eventName,function(event,virtualCenter){
		    _option._data = virtualCenter;
            if (_option.selectHandler) {
                _option.selectHandler.call(_option.selectHandler, virtualCenter);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("select_virtualcenter_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});
//控件：选择私信接收人
$.widget("txcomponent.selectReceivedOperator",{
	options: {
		contextPath : "",
		width : 600,
		height : 500,
		title : "请选择接收人",
		eventName : "selectReceivedOperator",
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		selectHandler: null,
		handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + 'operator/message/toSelectReceivedOperator?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("selectReceivedOperator",
				_option.title,
				action,
				_option.width,
				_option.height,
				function(){
					//onClose doNothing
				},
				{},
				[{
					text : '确认',
					handler : function() {
						if(_option.handler){
							_option.handler.call(_option.handler,_option._data);
						}
						_option.openDialogHandler.closeDialogById("selectReceivedOperator");
					}
				}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("select_receivedOperator_" + _option.eventName,function(event,virtualCenter){
			_option._data = virtualCenter;
			if (_option.selectHandler) {
				_option.selectHandler.call(_option.selectHandler, virtualCenter);
			}
		});
	},
	destroy : function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;

		$(_element).unbind("click");
		$.unbindge("select_receivedOperator_" + _option.eventName);

		$.Widget.prototype.destroy.call(this);
	}
});
//控件选择组织
$.widget("txcomponent.selectOrganization",{
	options: {
		eventName : "selectOrganization",
		contextPath : "",
		title : "请选择组织",
		width : 260,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		selectHandler: null,
        handler : null,
        data: null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + 'organization/toSelect?eventName=' + _option.eventName;
		
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
				_option._choosedData = null;
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("selectOrganization",
				_option.title,
				action,
				_option.width,
				_option.height,
				function(){
					//onClose doNothing
				},
				{},
				[{
					text : '确认',
					handler : function() {
					    if(_option.handler){
	                        _option.handler.call(_option.handler,_option._data);
	                    }
						_option.openDialogHandler.closeDialogById("selectOrganization");
					}
				}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("select_organization_" + _option.eventName,function(event,organization){
		    _option._data = organization;
            if (_option.selectHandler) {
                _option.selectHandler.call(_option.selectHandler, organization);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("select_organization_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});
//控件：选择职位
$.widget("txcomponent.selectPost",{
	options: {
		eventName : "selectPost",
		contextPath : "",
		title : "请选择职位",
		width : 600,
		height : 500,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_$clear: null,
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + 'post/toSelect?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			_$clear = $('<span class="icon_span clear"></span>')
			$(_element).after(_$clear);
			_$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_selectpost",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					{},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
                            _option.openDialogHandler.closeDialogById("component_dialog_selectpost");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("select_post_" + _option.eventName,function(event,post){
		    _option._choosedData = post;
		    if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, post);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
		_$clear.remove();
    	$(_element).unbind("click");
    	$.unbindge("select_post_" + _option.eventName);
    	$.Widget.prototype.destroy.call(this);
    }
});
//控件：选择操作员角色
$.widget("txcomponent.selectRoleCatalog",{
	options: {
		eventName : "selectRoleCatalog",
		contextPath : "",
		title : "请选择角色分类",
		width : 600,
		height : 500,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_$clear: null,
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + 'operatorRoleCatalog/toSelect?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			_$clear = $('<span class="icon_span clear"></span>')
			$(_element).after(_$clear);
			_$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_select_role_catalog",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					{},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
                            _option.openDialogHandler.closeDialogById("component_dialog_select_role_catalog");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("select_role_catalog_" + _option.eventName,function(event,post){
		    _option._choosedData = post;
		    if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, post);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
		_$clear.remove();
    	$(_element).unbind("click");
    	$.unbindge("select_role_catalog_" + _option.eventName);
    	$.Widget.prototype.destroy.call(this);
    }
});
//控件：选择操作员角色
$.widget("txcomponent.selectRole",{
	options: {
		eventName : "selectRole",
		contextPath : "",
		title : "请选择角色",
		width : 600,
		height : 500,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_$clear: null,
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + 'operatorRole/toSelect?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			_$clear = $('<span class="icon_span clear"></span>')
			$(_element).after(_$clear);
			_$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_select_role",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					{},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
                            _option.openDialogHandler.closeDialogById("component_dialog_select_role");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("select_role_" + _option.eventName,function(event,post){
		    _option._choosedData = post;
		    if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, post);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
		_$clear.remove();
    	$(_element).unbind("click");
    	$.unbindge("select_role_" + _option.eventName);
    	$.Widget.prototype.destroy.call(this);
    }
});

//控件：选择区域
$.widget("txcomponent.districtSelector",{
	options: {
		contextPath : "",
		provinceSelectId: null,
		citySelectId: null,
		countySelectId: null,
		onSelectProvince: null,
		onSelectCity: null,
		onSelectCounty: null,
		callback: null
	},
	_create: function(){
		var _this = this;
		var _option = _this.options;
		var _element = _this.element;
		
		_option._$provinceSelector = _element.find("#" + _option.provinceSelectId);
		_option._$citySelector = _element.find("#" + _option.citySelectId);
		_option._$countySelector = _element.find("#" + _option.countySelectId);
		
		this._loadProvince($provinceSelect);//初始化选择省
		$provinceSelect.change(function(){
			var provinceId = $(this).val();
			
			if($.ObjectUtils.isEmpty(provinceId)){
				_this._clearSelect($citySelect);
				_this._clearSelect($countySelect);
			}else{
				_this._loadCity($citySelect,provinceId);//初始化选择省
				_this._clearSelect($countySelect);
			}
		});
		$citySelect.change(function(){
			var cityId = $(this).val();
			
			if($.ObjectUtils.isEmpty(cityId)){
				_this._clearSelect($countySelect);
			}else{
				_this._loadCounty($countySelect,cityId);//初始化选择省
			}
		});
	},
	_loadCounty: function($countySelect,cityId){
		var _this = this;
		var _option = _this.options;
		var _contextPath = _option.contextPath;
		
		this._clearSelect($countySelect);
		
		if(!$.ObjectUtils.isEmpty(cityId)){
			$.post(_contextPath + "/district/queryList.action?type=COUNTY&parentId="+cityId,function(districtList){
				$.each(districtList,function(index,district){
					$countySelect.append("<option value=" + district.id + ">"+ district.name +"</option>");
				});
			});
		}
	},
	_loadCity: function($citySelect,provinceId){
		var _this = this;
		var _option = _this.options;
		var _contextPath = _option.contextPath;
		
		this._clearSelect($citySelect);
		
		if(!$.ObjectUtils.isEmpty(provinceId)){
			$.post(_contextPath + "/district/queryList.action?type=CITY&parentId="+provinceId,function(districtList){
				$.each(districtList,function(index,district){
					$citySelect.append("<option value=" + district.id + ">"+ district.name +"</option>");
				});
			});
		}
	},
	_loadProvince: function($provinceSelect){
		var _this = this;
		var _option = _this.options;
		var _contextPath = _option.contextPath;
		
		this._clearSelect($provinceSelect);
		
		$.post(_contextPath + "/district/queryList.action?type=PROVINCE",function(districtList){
			$.each(districtList,function(index,district){
				$provinceSelect.append("<option value=" + district.id + ">"+ district.name +"</option>");
			});
		});
	},
	_clearSelect: function($citySelect){
		$citySelect.empty();
		$citySelect.append("<option value=''>--- 请选择 ---</option>");
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
		var $provinceSelect = _element.find("#" + _option.provinceSelectId);
		var $citySelect = _element.find("#" + _option.citySelectId);
		var $countySelect = _element.find("#" + _option.countySelectId);
		
		this._clearSelect($provinceSelect);
		this._clearSelect($citySelect);
		this._clearSelect($countySelect);
		
		$provinceSelect.unbind("change");
		$citySelect.unbind("change");
		$countySelect.unbind("change");
    }
});
