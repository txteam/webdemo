//控件：选择区域
$.widget("txcomponent.selectDistrict",{
	options: {
		eventName : "selectDistrict",
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
		
		var $provinceSelect = _element.find("#" + _option.provinceSelectId);
		var $citySelect = _element.find("#" + _option.citySelectId);
		var $countySelect = _element.find("#" + _option.countySelectId);
		
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
//控件：选择虚中心
$.widget("txcomponent.chooseVirtualCenter",{
	options: {
		eventName : "chooseVirtualCenter",
		contextPath : "",
		title : "请选择虚中心",
		width : 600,
		height : 500,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
		authKey: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/virtualCenter/toChooseVirtualCenter.action?eventName='+_option.eventName+'&authKey='+_option.authKey;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseVirtualCenter",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseVirtualCenter");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_virtualCenter_" + _option.eventName,function(event,virtualCenter){
		    _option._choosedData = virtualCenter;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, virtualCenter);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_virtualCenter_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});
$.widget("txcomponent.selectVirtualCenter",{
	options: {
		eventName : "chooseVirtualCenter",
		contextPath : "",
		authKey: null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_virtualCenter_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});

//控件：选择财务科目
$.widget("txcomponent.chooseAccountTitle",{
	options: {
		eventName : "chooseAccountTitle",
		companyId : "",
		contextPath : "",
		title : "请选择财务科目",
		width : 600,
		height : 500,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/accountTitle/toChooseAccountTitle.action?eventName=' + _option.eventName + "&companyId=" + _option.companyId;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
				_option._choosedData = null;
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseAccountTitle",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseAccountTitle");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_accountTitle_" + _option.eventName,function(event,accountTitle){
		    _option._choosedData = accountTitle;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, accountTitle);
            }
		});
	},
	_init : function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/accountTitle/toChooseAccountTitle.action?eventName=' + _option.eventName + "&companyId=" + _option.companyId;
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseAccountTitle",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseAccountTitle");
						}
					}]);
			return false;
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_accountTitle_" + _option.eventName);
    	$("span").remove(".clear");
    	
    	$.Widget.prototype.destroy.call(this);
    }
});

//控件：选择分行人员
$.widget("txcomponent.chooseBranchUser",{
	options: {
		eventName : "chooseBranchUser",
		contextPath : "",
		title : "请选择分行",
		width : 600,
		height : 500,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null,
        branchId : ''
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/operator/toChooseBranchOperator.action?eventName=' + _option.eventName + '&branchId=' + _option.branchId;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseBranchUser",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseBranchUser");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_branchUser_" + _option.eventName,function(event,user){
		    _option._choosedData = user;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler,user);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_branchUser_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});




//控件选择组织
$.widget("txcomponent.chooseOrganization",{
	options: {
		eventName : "chooseOrganization",
		contextPath : "",
		title : "请选择组织",
		width : 260,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/organization/toChooseOrgnization.action?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseOrganization",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseOrganization");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_organization_" + _option.eventName,function(event,organization){
		    _option._choosedData = organization;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, organization);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_organization_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});

//控件选择行业
$.widget("txcomponent.chooseIndustry",{
	options: {
		eventName : "chooseIndustry",
		contextPath : "",
		title : "请选择行业",
		width : 260,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/industry/toChooseIndustry.action?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseIndustry",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseIndustry");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_industry_" + _option.eventName,function(event,industry){
		    _option._choosedData = industry;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, industry);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_industry_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});
//控件选择地区
$.widget("txcomponent.chooseDistrict",{
	options: {
		eventName : "chooseDistrict",
		contextPath : "",
		title : "请选择行业",
		width : 260,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/district/toChooseDistrict.action?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseDistrict",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseDistrict");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_district_" + _option.eventName,function(event,district){
		    _option._choosedData = district;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, district);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_district_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});

//控件选择职位
$.widget("txcomponent.choosePosition",{
	options: {
		eventName : "choosePosition",
		contextPath : "",
		title : "请选择职位",
		width : 260,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/position/toChoosePosition.action?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_choosePosition",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_choosePosition");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_position_" + _option.eventName,function(event,position){
		    _option._choosedData = position;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, position);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_position_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});

//控件：选择职位
$.widget("txcomponent.choosePost",{
	options: {
		eventName : "choosePost",
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
		var action = _option.contextPath + '/post/toChoosePost.action?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			_$clear = $('<span class="icon_span clear"></span>')
			$(_element).after(_$clear);
			_$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_choosePost",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
                            _option.openDialogHandler.closeDialogById("component_dialog_choosePost");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_post_" + _option.eventName,function(event,post){
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
    	$.unbindge("choose_post_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});

//控件选择职位
$.widget("txcomponent.chooseOperatorPost",{
	options: {
		eventName : "chooseOperatorPost",
		contextPath : "",
		title : "请选择职位",
		width : 260,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null,
        operatorId : ''
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/Operator2Post/toChooseOperatorPost.action?eventName=' + _option.eventName + '&operatorId=' + _option.operatorId;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseOperatorPost",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseOperatorPost");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_operator_post_" + _option.eventName,function(event,position){
		    _option._choosedData = position;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, position);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_operator_post_" + _option.eventName);
    }
});


//控件：选择人员
$.widget("txcomponent.chooseOperator",{
    options: {
        eventName : "chooseOperator",
        contextPath : "",
        title : "请选择人员",
        width : 600,
        height : 500,
        openDialogHandler : window.parent.DialogUtils,
        clearHandler: null,
        chooseHandler: null,
        handler : null,
        organizationId : ''
    },
    _$clear:null,
    _create: function(){
        var _this = this;
        var _element = _this.element;
        var _option = _this.options;
        var action = _option.contextPath + '/operator/toSingleChooseOperator.action?eventName=' + _option.eventName + '&organizationId=' + _option.organizationId;
        if(_option.clearHandler && $.isFunction(_option.clearHandler)){
        	_$clear = $('<span class="icon_span clear"></span>');
            $(_element).after(_$clear);
            _$clear.click(function(){
                _option.clearHandler();
            });
        }
        $(_element).click(function(){
            var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseOperator",
                    _option.title,
                    action,
                    _option.width,
                    _option.height,
                    function(){
                        //onClose doNothing
                    },
                    [{
                        text : '确认',
                        handler : function() {
                            if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
                            _option.openDialogHandler.closeDialogById("component_dialog_chooseOperator");
                        }
                    }]);
            return false;
        });
        $.bindge("choose_operator_" + _option.eventName, function(event, operator) {
            _option._choosedData = operator;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, operator);
            }
        }); 
        //绑定响应选中事件
    },
    destroy : function(){
        var _this = this;
        var _element = _this.element;
        var _option = _this.options;
        
        _$clear.remove();
        $(_element).unbind("click");
        $.unbindge("choose_operator_" + _option.eventName);
        
        $.Widget.prototype.destroy.call(this);
    }
});
//控件选择开户行
$.widget("txcomponent.chooseOpenAccountBank",{
	options: {
		eventName : "chooseOpenAccountBank",
		contextPath : "",
		title : "请选择开户行",
		width : 450,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/openAccountBank/toChooseOpenAccountBank.action?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseOpenAccountBank",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseOpenAccountBank");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_openAccountBank_" + _option.eventName,function(event,openAccountBank){
		    _option._choosedData = openAccountBank;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, openAccountBank);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_openAccountBank_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});


//控件：选择处理分行
$.widget("txcomponent.chooseBranch",{
    options: {
        eventName : "chooseBranch",
        contextPath : "",
        title : "请选择分行",
        width : 600,
        height : 500,
        openDialogHandler : window.parent.DialogUtils,
        clearHandler: null,
        chooseHandler: null,
        handler : null,
        excludeOrganizationId : '',
        includeVcid : ''
    },
    _create: function(){
        var _this = this;
        var _element = _this.element;
        var _option = _this.options;
        var action = _option.contextPath + '/organization/toChooseBranch.action?eventName=' + _option.eventName + '&excludeOrganizationId=' + _option.excludeOrganizationId + '&includeVcid=' + _option.includeVcid;
        if(_option.clearHandler && $.isFunction(_option.clearHandler)){
            var $clear = $('<span class="icon_span clear"></span>');
            $(_element).after($clear);
            $clear.click(function(){
                _option.clearHandler();
            });
        }
        $(_element).click(function(){
            var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseBranch",
                    _option.title,
                    action,
                    _option.width,
                    _option.height,
                    function(){
                        //onClose doNothing
                    },
                    [{
                        text : '确认',
                        handler : function() {
                            if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
                            _option.openDialogHandler.closeDialogById("component_dialog_chooseBranch");
                        }
                    }]);
            return false;
        });
        $.bindge("choose_branch_" + _option.eventName, function(event, branch) {
            _option._choosedData = branch;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, branch);
            }
        }); 
        //绑定响应选中事件
    },
    destroy : function(){
        var _this = this;
        var _element = _this.element;
        var _option = _this.options;
        
        $(_element).unbind("click");
        $.unbindge("choose_branch_" + _option.eventName);
        
        $.Widget.prototype.destroy.call(this);
    }
});


//控件：选择人员
$.widget("txcomponent.chooseClientAccount",{
    options: {
        eventName : "chooseClientAccount",
        contextPath : "",
        title : "请选择客户",
        width : 600,
        height : 500,
        openDialogHandler : window.parent.DialogUtils,
        clearHandler: null,
        chooseHandler: null,
        handler : null,
        organizationId : ''
    },
    _$clear:null,
    _create: function(){
        var _this = this;
        var _element = _this.element;
        var _option = _this.options;
        var action = _option.contextPath + '/clientAccount/toChooseClientAccount.action?eventName=' + _option.eventName;
        if(_option.clearHandler && $.isFunction(_option.clearHandler)){
        	_$clear = $('<span class="icon_span clear"></span>');
            $(_element).after(_$clear);
            _$clear.click(function(){
                _option.clearHandler();
            });
        }
        $(_element).click(function(){
            var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseClientAccount",
                    _option.title,
                    action,
                    _option.width,
                    _option.height,
                    function(){
                        //onClose doNothing
                    },
                    [{
                        text : '确认',
                        handler : function() {
                            if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
                            _option.openDialogHandler.closeDialogById("component_dialog_chooseClientAccount");
                        }
                    }]);
            return false;
        });
        $.bindge("choose_operator_" + _option.eventName, function(event, operator) {
            _option._choosedData = operator;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, operator);
            }
        }); 
        //绑定响应选中事件
    },
    destroy : function(){
        var _this = this;
        var _element = _this.element;
        var _option = _this.options;
        
        _$clear.remove();
        $(_element).unbind("click");
        $.unbindge("choose_operator_" + _option.eventName);
        
        $.Widget.prototype.destroy.call(this);
    }
});

//控件选择商品分类
$.widget("txcomponent.chooseCategory",{
	options: {
		eventName : "chooseCategory",
		contextPath : "",
		title : "请选择分类",
		width : 260,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		clearHandler: null,
		chooseHandler: null,
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/category/toChooseCategory.action?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
				_option.clearHandler();
			});
		}
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseCategory",
					_option.title,
					action,
					_option.width,
					_option.height,
					function(){
						//onClose doNothing
					},
					[{
						text : '确认',
						handler : function() {
						    if(_option.handler){
                                _option.handler.call(_option.handler,_option._choosedData);
                            }
							_option.openDialogHandler.closeDialogById("component_dialog_chooseCategory");
						}
					}]);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_category_" + _option.eventName,function(event,organization){
		    _option._choosedData = organization;
            if (_option.chooseHandler) {
                _option.chooseHandler.call(_option.chooseHandler, organization);
            }
		});
	},
    destroy : function(){
    	var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		
    	$(_element).unbind("click");
    	$.unbindge("choose_category_" + _option.eventName);
    	
    	$.Widget.prototype.destroy.call(this);
    }
});