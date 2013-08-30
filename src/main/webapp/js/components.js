$.widget("txcomponent.chooseOrganization",{
	options: {
		eventName : "chooseOrganization",
		contextPath : "",
		title : "请选择组织",
		width : 260,
		height : 400,
		openDialogHandler : window.parent.DialogUtils,
		handler : null
	},
	_create: function(){
		var _this = this;
		var _element = _this.element;
		var _option = _this.options;
		var action = _option.contextPath + '/organization/toChooseOrgnization.action?eventName=' + _option.eventName;
		$(_element).click(function(){
			var dialog_ = _option.openDialogHandler.openModalDialog("component_dialog_chooseOrganization",
					_option.title,action,
					_option.width,
					_option.height);
			return false;
		});
		//绑定响应选中事件
		$.bindge("choose_organization_" + _option.eventName,function(event,organization){
			_option.handler.call(_option.handler,organization);
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
