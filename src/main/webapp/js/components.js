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
        handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/virtualCenter/toChooseVirtualCenter.action?eventName=' + _option.eventName;
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
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/post/toChoosePost.action?eventName=' + _option.eventName;
		if(_option.clearHandler && $.isFunction(_option.clearHandler)){
			var $clear = $('<span class="icon_span clear"></span>')
			$(_element).after($clear);
			$clear.click(function(){
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
		
    	$(_element).unbind("click");
    	$.unbindge("choose_post_" + _option.eventName);
    }
});

//控件：选择职位
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
    _create: function(){
        var _this = this;
        var _element = _this.element;
        var _option = _this.options;
        var action = _option.contextPath + '/operator/toSingleChooseOperator.action?eventName=' + _option.eventName + '&organizationId=' + _option.organizationId;
        if(_option.clearHandler && $.isFunction(_option.clearHandler)){
            var $clear = $('<span class="icon_span clear"></span>');
            $(_element).after($clear);
            $clear.click(function(){
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
        
        $(_element).unbind("click");
        $.unbindge("choose_operator_" + _option.eventName);
    }
});
