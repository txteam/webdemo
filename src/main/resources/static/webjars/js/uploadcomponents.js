//控件：图片上传
$.widget("txcomponent.imageUploader",{
	options: {
		contextPath: null,//contextPath
		initFilter: null,
		formData: null,
		server: null,
        imageWidth:null,
        deleteUrl: null,
        deleteFun: null,
        viewUrl: function(fileDefinition){
        	return fileDefinition.viewUrl;
        },
        imageHeight:null,
        dnd: '.imageQueueList',
        fileNumLimit: 1,//{int} [可选] [默认值：undefined] 验证文件总数量, 超出则不允许加入队列。
        fileSizeLimit: 50 * 1024 * 1024,//50 M {int} [可选] [默认值：undefined] 验证文件总大小是否超出限制, 超出则不允许加入队列。
        fileSingleSizeLimit: 50 * 1024 * 1024,//50 M  {int} [可选] [默认值：undefined] 验证单个文件大小是否超出限制, 超出则不允许加入队列。
        disableGlobalDnd: true,//[可选][默认值：false]是否禁掉整个页面的拖拽功能，如果不禁用，图片拖进来的时候会默认被浏览器打开。
		paste: document.body,//[可选][默认值：undefined]指定监听paste事件的容器，如果不指定，不启用此功能。此功能为通过粘贴来添加截屏的图片。建议设置为document.body
		compress: false,
        /*compress: {
            width: 1600,
            height: 1600,
            quality: 90,// 图片质量，只有type为`image/jpeg`的时候才有效。
            allowMagnify: false,// 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            crop: false,// 是否允许裁剪。
            preserveHeaders: true,// 是否保留头部meta信息。
            noCompressIfLarger: false,// 如果发现压缩后文件大小比原来还大，则使用原来图片,// 此属性可能会影响图片自动纠正功能
            compressSize: 0// 单位字节，如果图片大小小于此值，不会采用压缩。
        },*/
		accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpg,image/jpeg,image/png'
        },
        pick: {
            id: 'filePicker',
            label: '点击选择图片',
            tip: '或将图片拖到这里'
        }
	},
	webuploader: null,// WebUploader实例
	_fileCount: 0,// 添加的文件数量
	_percentages: {},// 所有文件的进度信息，key为file id
	_height: 110,
	_width: 110,
	$imageQueueList: null,
	$placeHolder: null,
	_create: function(){
		var _this = this;
		var _option = _this.options;
		var _element = _this.element;
		var $wrap = _element;
		_this.$imageQueueList = $('<div class="imageQueueList"></div>');
		_this.$placeHolder = $('<div class="placeHolder"><img src=""/><br/><div name="picker"></div><br/><p></p></div>');
		
		//判断兼容性
		_this._width = $wrap.width();
		_this._height = $wrap.height();
		if (!WebUploader.Uploader.support()) {
	        alert('Web Uploader不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器.');
	        throw new Error( 'WebUploader does not support the browser you are using.' );
	    }
		
		//读取原始值
		var _fileDefinitions = new Array();
		if(_option.initFilter){
			_fileDefinitions = _option.initFilter($wrap);
		}
		
		//初始化控件
		$wrap.empty();
		if(!$wrap.is('imageUploader')){
			$wrap.addClass('imageUploader');
		}
		_this.$imageQueueList.css('width',_this._width).css('height',_this._height).appendTo($wrap);
		_option.imageWidth = _option.imageWidth ? _option.imageWidth : _this._width;
		_option.imageHeight = _option.imageHeight ? _option.imageHeight : _this._height;
		
		_this.$placeHolder.appendTo(_this.$imageQueueList);
		_this.$placeHolder.css('width',_option.imageWidth).css('height',_option.imageHeight);
		_this.$placeHolder.find("img").css('width',_option.imageHeight * 0.45).css('height',_option.imageHeight * 0.4)
    		.attr("src",_option.contextPath + "/js/webuploader/images/image.png");
		_this.$placeHolder.find("div[name=picker]").attr("id",_option.pick.id);
		_this.$placeHolder.find("p").text(_option.pick.tip);
		//实例化
	    _this.webuploader = WebUploader.create({
	    	auto: true,
	    	swf: _option.contextPath + '/webuploader/js/Uploader.swf',//swf文件路径
	    	disableGlobalDnd: _option.disableGlobalDnd,
	        dnd: '#' + $wrap.attr("id") + ' ' + _option.dnd,
	        paste: _option.paste,
	        accept: _option.accept,
	        fileNumLimit: _option.fileNumLimit,
	        fileSizeLimit: _option.fileSizeLimit,
	        fileSingleSizeLimit: _option.fileSingleSizeLimit,
	        server: _option.server,
	        formData: _option.formData,
	        compress: _option.compress,
	        pick: {
	            id: '#' + _option.pick.id,
	            label: _option.pick.label
	        }
	    });
	    _this._processPlaceHolder();
	   
	    if( !$.ObjectUtils.isEmpty(_fileDefinitions) ){
	    	$.each(_fileDefinitions,function(index,_fileDefinition){
	    		if(!$.ObjectUtils.isEmpty(_fileDefinition.id)){
	    			_this._addImageView(_fileDefinition);
	    		}
	    	});
	    }
	    _this.webuploader.onFileQueued = function(file) {
	    };
	    _this.webuploader.onFileDequeued = function(file) {
	    };
	    _this.webuploader.onStartUpload = function() {
	    	//当开始上传流程时触发。
	    	//某个文件开始上传前触发，一个文件只会触发一次。
	    	DialogUtils.progress({
	            text : '文件上传中，请等待....'
	    	});
	    };
	    _this.webuploader.onUploadFinished = function() {
	    	//当所有文件上传结束时触发。
	    	DialogUtils.progress('close');
	    };
	    _this.webuploader.onUploadSuccess = function(file,response) {
	    	if(!response.result){
	    		_this._showError( response.errorMessage );
	    		_this.webuploader.removeFile(file.id);
	    		return ;
	    	}
	    	var _fileDefinition = response.data;
	    	//alert(JSON.stringify(response.data));
	    	_fileDefinition['localFile'] = file;
	    	_this._addImageView(_fileDefinition);
	    	_this._trigger('uploadSuccess',null,_fileDefinition);
	    };
	    _this.webuploader.onUploadError = function(file,reason) {
	    	//file {File} File对象
	    	//reason {String} 出错的code
	    	_this.webuploader.removeFile(file.id);
	    	_this._showError( reason );
	    };
	    _this.webuploader.onError = function(code) {
	    	//type {String}错误类型。
	    	_this._showError( code );
	    };
	},
    destroy: function(){
    },
    _processPlaceHolder: function(){
    	var _this = this;
    	var _option = _this.options;
    	
    	//填入filePicker
		if(_this._fileCount >= _option.fileNumLimit){
			_this.$placeHolder.hide();
		}else{
			_this.$placeHolder.show();
		}
    },
    _showError: function(code){
    	var text = '';
    	switch(code) {
	        case 'exceed_size':
	            text = '文件大小超出';
	            break;
	        case 'interrupt':
	            text = '上传暂停';
	            break;
	        default:
	        	text = '上传失败,code:' + code;
	            break;
	    }
    	DialogUtils.alert('错误', text, 'error');
    },
    _addImageView: function(_fileDefinition){
    	var _this = this;
    	var _option = _this.options;
    	if(!_fileDefinition){
    		return ;
    	}
    	
    	var fileId = _fileDefinition.id;
    	var viewUrl = _option.viewUrl(_fileDefinition);
    	
    	if(_fileDefinition['localFile']){
    		_this._percentages[_fileDefinition['localFile'].id] = _fileDefinition['localFile'];
    	}
    	_this._fileCount++;
    	_this._processPlaceHolder();
    	
    	var $imageView = $('<div id="' + fileId + '" class="imageView">' +
           		 '<p class="imgWrap"></p>'+
                '<p class="title">' + viewUrl + '</p>' +
                '</div>');
    	$imageView.css('width',_option.imageWidth).css('height',_option.imageHeight).prependTo(_this.$imageQueueList);

        var $wrap = $imageView.find( 'p.imgWrap' ),
            $btns = $('<div class="file-panel"><span class="cancel">删除</span></div>').appendTo($imageView);
        var $img = $('<img src="'+viewUrl+'">');
        
        $img.viewer({zIndex: 10000});

        $img.width(_option.imageWidth).height(_option.imageHeight);
        $img.attr("width",_option.imageWidth).attr("height",_option.imageHeight);
        $img.appendTo($wrap);

	    $imageView.on('mouseenter', function() {
		    $btns.stop().animate({height: 30});
		});
	    $imageView.on('mouseleave', function() {
		    $btns.stop().animate({height: 0});
		});
	    $btns.on('click', 'span', function() {
            var index = $(this).index();
            switch (index) {
                case 0:
                	if(_option.deleteUrl){
                		var deleteUrl = jQuery.type(_option.deleteUrl) == "function" ? _option.deleteUrl(_fileDefinition) : _option.deleteUrl;
                		$.post(deleteUrl, function(data){
                    		if(data.result){
                    			if(_fileDefinition['localFile']){
                    				_this.webuploader.removeFile(_fileDefinition['localFile'].id);
                    	    		delete _this._percentages[_fileDefinition['localFile'].id];
                    	    	}
                    			//删除陈功
                    			_this._fileCount--;
                    			_this._processPlaceHolder();
                    			$imageView.off().find('.file-panel').off().end().remove();
                    			_this._trigger('deleteSuccess',null,_fileDefinition);
                    		}else if(data.errorMessage){
                    			DialogUtils.alert('错误', data.errorMessage, 'error');
                    		}else{
                    			DialogUtils.alert('错误', '删除文件失败.', 'error');
                    		}
                    	});	
                	}else if(_option.deleteFun){
                		deleteFun(_fileDefinition,function(data){
                			if(data){
                    			if(_fileDefinition['localFile']){
                    				_this.webuploader.removeFile(_fileDefinition['localFile'].id);
                    	    		delete _this._percentages[_fileDefinition['localFile'].id];
                    	    	}
                    			//删除陈功
                    			_this._fileCount--;
                    			_this._processPlaceHolder();
                    			$imageView.off().find('.file-panel').off().end().remove();
                    			_this._trigger('deleteSuccess',null,_fileDefinition);
                    		}
                		});
                	}else{
                		DialogUtils.progress({
            	            text : '文件删除中，请等待....'
            	    	});
                		console.log('' + _fileDefinition.id);
                		_option.formData["fileId"] = _fileDefinition.id;
                		$.post(_option.server, _option.formData, function(data){
                	    	DialogUtils.progress('close');
                    		if(data.result){
                    			if(_fileDefinition['localFile']){
                    				_this.webuploader.removeFile(_fileDefinition['localFile'].id);
                    	    		delete _this._percentages[_fileDefinition['localFile'].id];
                    	    	}else if(_fileDefinition.id){
                    				_this.webuploader.removeFile(_fileDefinition.id);
                    	    		delete _this._percentages[_fileDefinition.id];
                    	    	}
                    			//删除陈功
                    			_this._fileCount--;
                    			_this._processPlaceHolder();
                    			$imageView.off().find('.file-panel').off().end().remove();
                    			_this._trigger('deleteSuccess',null,_fileDefinition);
                    		}else if(data.errorMessage){
                    			DialogUtils.alert('错误', data.errorMessage, 'error');
                    		}else{
                    			DialogUtils.alert('错误', '删除文件失败.', 'error');
                    		}
                    	});
                	}
                    return;
            }
        });
    }
});