/**
 * 图片相册
 */
function thumbnail(canvasid) {

	var canvas = document.getElementById(canvasid); // 获得 canvas 对象
	var context = canvas.getContext('2d'); // 获得上下文对象

	var NAVPANEL_COLOR = 'rgba(100, 100, 100, 0.5)'; // 导航栏背景色
	var NAVBUTTON_BACKGROUND = 'rgba(40, 40, 40,0.5)'; // 导航栏中 button 的背景色
	var NAVBUTTON_COLOR = 'rgba(255, 255, 255,0.5)'; // button 的前景色
	var NAVBUTTON_HL_COLOR = 'rgb(100, 100, 100)'; // button 高亮时的前景色

	var NAVBUTTON_WIDTH = 20; // 导航栏按钮宽
	var MAX_IMAGE_WIDTH = 1200; // 图片最大宽度
	var MAX_THUMB_SHOW_COUNT = 5; // 显示最大的缩略图数量
	var THUMBNAIL_LENGTH = 100; // 缩略图边长(正方形)
	var THUMBNAIL_OFFSET = 10; // 缩略图间隔
	var NAVPANEL_HEIGHT = THUMBNAIL_LENGTH + THUMBNAIL_OFFSET * 2; // 导航栏高度

	var NAVBUTTON_LEFT_XOFFSET = 0; // 导航栏左边按钮起始X坐标(相对导航栏)
	var NAVBUTTON_LEFT_YOFFSET = 0;// 导航栏左边按钮起始Y坐标(相对导航栏)
	var NAVBUTTON_RIGHT_XOFFSET = (NAVBUTTON_LEFT_XOFFSET + NAVBUTTON_WIDTH)// 导航栏左按钮
			+ (MAX_THUMB_SHOW_COUNT * (THUMBNAIL_LENGTH + THUMBNAIL_OFFSET) + THUMBNAIL_OFFSET);// 导航栏右边按钮起始X坐标(相对导航栏)
	var NAVBUTTON_RIGHT_YOFFSET = NAVBUTTON_LEFT_YOFFSET;// 导航栏右边按钮起始Y坐标(相对导航栏)

	var lButtonRect = {}; // 导航栏左箭头
	var rButtonRect = {}; // 导航栏右箭头
	var currentImage = 0; // 当前图片序号
	var thumbNailCount = 0; // 当前显示的缩略图数

	var firstImageIndex = 0; // 当前缩略图中第一张图片序号
	var navRect = {
		x : 280,// 起始x坐标
		y : 450,// 起始y坐标
		width : NAVBUTTON_RIGHT_XOFFSET + NAVBUTTON_WIDTH,// 宽
		height : NAVPANEL_HEIGHT,// 高
		inLeftBtn : false,
		inRightBtn : false
	}; // 导航栏区域大小
	var isNavRectDown = false; // 导航栏是否被按下
	var isNavRectMoving = false; // 导航栏是否在移动中
	var navRectDown = {}; // 鼠标在导航栏内按下时的与导航栏左上角的坐标之差
	var isImageFlip = false; // 图片是否翻转状态
	// 定义全部图片 URL 数组，在本例中，所有图片保存在和网页同目录中
	var imageLocations = [ 'http://localhost:9090/webdemo/images/test/1.jpg',
			'http://localhost:9090/webdemo/images/test/2.jpg',
			'http://localhost:9090/webdemo/images/test/3.jpg',
			'http://localhost:9090/webdemo/images/test/4.jpg',
			'http://localhost:9090/webdemo/images/test/5.jpg',
			'http://localhost:9090/webdemo/images/test/6.jpg',
			'http://localhost:9090/webdemo/images/test/7.jpg',
			'http://localhost:9090/webdemo/images/test/8.jpg', ];
	var images = []; // 主图标签img数组
	var thumbImageLocations = [
			'http://localhost:9090/webdemo/images/test/11.jpg',
			'http://localhost:9090/webdemo/images/test/22.jpg',
			'http://localhost:9090/webdemo/images/test/33.jpg',
			'http://localhost:9090/webdemo/images/test/44.jpg',
			'http://localhost:9090/webdemo/images/test/55.jpg',
			'http://localhost:9090/webdemo/images/test/66.jpg',
			'http://localhost:9090/webdemo/images/test/77.jpg',
			'http://localhost:9090/webdemo/images/test/88.jpg', ]; // 缩略图数组
	var thumbImages = []; // 缩略图标签img数组

	var thumbImageRects = []; // 当前显示的缩略图数组

	function getNavRectY() {
		return navRect.y + document.body.scrollTop;
	}

	/** 获取主图 */
	function getImage() {
		if (currentImage < 0) {
			currentImage = 0;
		}
		if (thumbImageLocations.length - 1 < currentImage) {
			currentImage = thumbImageLocations.length - 1;
		}

		var onLoad = function(err, msg) {
			if (err) {
				console.log(msg);
			}
			paint();
		}

		try {
			var im = images[currentImage];
			if (im && im != null) {
				return im;
			}
		} catch (e) {
		}

		var img = new Image();
		img.onload = function() {
			onLoad(false);
		};
		img.onerror = function() {
			onLoad(true, e);
		};
		img.src = imageLocations[currentImage];
		images[currentImage] = img;
		// 这里返回null是为了通知调用此函数的地方 此图片是第一次加载,会自动在图片加载完成后自动调用paint()函数
		return null;
	}

	/** 加载缩略图 */
	function loadThumbImages() {
		var total = thumbImageLocations.length;
		var imageCounter = 0;
		var onLoad = function(err, msg) {
			if (err) {
				console.log(msg);
			}
			imageCounter++;
			if (imageCounter == total) {
				paint();
			}
		}

		for (var i = 0; i < thumbImageLocations.length; i++) {
			var img = new Image();
			img.onload = function() {
				onLoad(false);
			};
			img.onerror = function() {
				onLoad(true, e);
			};
			img.src = thumbImageLocations[i];
			thumbImages[i] = img;
		}
	}

	/** 绘制图片 */
	function paintImage() {
		var image = getImage(); // 图片
		if (image == null) {
			return;
		}

		// TODO 何雨 图片相册 这里需要提供一个"获取图片缩放比例"的方法
		var ratio = 1;
		var w = image.width * ratio;// 图片压缩后宽
		var h = image.height * ratio; // 图片压缩后高

		// 图片横向定死最大MAX_IMAGE_WIDTH
		if (isImageFlip) {
			if (h > MAX_IMAGE_WIDTH) {
				h = MAX_IMAGE_WIDTH;
				w = w * MAX_IMAGE_WIDTH / h;
			}

			canvas.width = h;
			canvas.height = w;

			context.save();
			context.rotate(0 - 90 * Math.PI / 180);
			context.translate(0 - w, 0);
			context.drawImage(image, 0, 0, w, h); // 平铺画图片
			context.fill();
			context.restore();
		} else {
			if (w > MAX_IMAGE_WIDTH) {
				h = h * MAX_IMAGE_WIDTH / w;
				w = MAX_IMAGE_WIDTH;
			}

			canvas.width = w;
			canvas.height = h;
			context.drawImage(image, 0, 0, w, h); // 平铺画图片
		}
	}

	/** 计算坐标是否在制定的区域内 */
	function pointIsInRect(point, rect) {
		return (rect.x < point.x && point.x < rect.x + rect.width
				&& rect.y < point.y && point.y < rect.y + rect.height);
	}

	/** 画按钮区域 */
	function paintButton() {
		context.save();
		context.fillStyle = NAVPANEL_COLOR;
		context.fillRect(navRect.x, getNavRectY(), navRect.width,
				navRect.height);
		context.restore();

		paintLeftButton();
		paintRightButton();
	}

	/** 绘制导航栏左边 button */
	function paintLeftButton() {
		// left button
		lButtonRect = {
			x : navRect.x + NAVBUTTON_LEFT_XOFFSET,
			y : getNavRectY() + NAVBUTTON_LEFT_YOFFSET,
			width : NAVBUTTON_WIDTH,
			height : navRect.height
		}

		context.save();
		context.fillStyle = NAVBUTTON_BACKGROUND;
		context.fillRect(lButtonRect.x, lButtonRect.y, lButtonRect.width,
				lButtonRect.height);

		// left arrow
		context.save();
		context.fillStyle = NAVBUTTON_COLOR;
		context.beginPath();
		context.moveTo(lButtonRect.x, lButtonRect.y + lButtonRect.height / 2);
		context.lineTo(lButtonRect.x + lButtonRect.width, lButtonRect.y
				+ NAVBUTTON_WIDTH);
		context.lineTo(lButtonRect.x + lButtonRect.width, lButtonRect.y
				+ lButtonRect.height - NAVBUTTON_WIDTH);
		context.lineTo(lButtonRect.x, lButtonRect.y + lButtonRect.height / 2);
		context.closePath();
		context.fill();
		context.restore();
	}

	/** 绘制导航栏右边 button */
	function paintRightButton() {
		// right button
		rButtonRect = {
			x : navRect.x + NAVBUTTON_RIGHT_XOFFSET,
			y : getNavRectY() + NAVBUTTON_RIGHT_YOFFSET,
			width : NAVBUTTON_WIDTH,
			height : navRect.height
		}

		context.save();
		context.fillStyle = NAVBUTTON_BACKGROUND;
		context.fillRect(rButtonRect.x, rButtonRect.y, rButtonRect.width,
				rButtonRect.height);

		// right arrow
		context.save();
		context.fillStyle = NAVBUTTON_COLOR;
		context.beginPath();
		context.moveTo(rButtonRect.x + NAVBUTTON_WIDTH, rButtonRect.y
				+ rButtonRect.height / 2);
		context.lineTo(rButtonRect.x + rButtonRect.width - NAVBUTTON_WIDTH,
				rButtonRect.y + NAVBUTTON_WIDTH);
		context.lineTo(rButtonRect.x + rButtonRect.width - NAVBUTTON_WIDTH,
				rButtonRect.y + rButtonRect.height - NAVBUTTON_WIDTH);
		context.lineTo(rButtonRect.x + NAVBUTTON_WIDTH, rButtonRect.y
				+ rButtonRect.height / 2);
		context.closePath();
		context.fill();
		context.restore();
	}

	/** 绘制导航栏缩略图 */
	function paintThumbNails(curThumbIndex) {
		if (curThumbIndex != null) {
			curThumbIndex -= firstImageIndex;
		} else {
			curThumbIndex = -1;
		}

		thumbImageRects = new Array(MAX_THUMB_SHOW_COUNT);
		for (var i = 0; i < MAX_THUMB_SHOW_COUNT; i++) {
			image = thumbImages[i + firstImageIndex];
			var x = lButtonRect.x + lButtonRect.width
					+ (THUMBNAIL_OFFSET + THUMBNAIL_LENGTH) * i; // 第i个缩略图x坐标

			thumbImageRects[i] = {
				image : image,
				rect : {
					x : x + THUMBNAIL_OFFSET,
					y : getNavRectY() + THUMBNAIL_OFFSET,
					height : THUMBNAIL_LENGTH,
					width : THUMBNAIL_LENGTH
				}
			}

			context.save();
			context.globalAlpha = 0.7; // 设置缩略图透明度
			context.translate(x + THUMBNAIL_OFFSET, getNavRectY()
					+ THUMBNAIL_OFFSET);
			context.drawImage(image, 0, 0, THUMBNAIL_LENGTH, THUMBNAIL_LENGTH);
			context.restore();
		}
	}

	/** 鼠标移动事件 */
	function onMouseMove(event) {
		point = {
			x : event.clientX,
			y : event.clientY
		};

		var r = {};
		r.x = navRect.x - 10;
		r.y = navRect.y - 10;
		r.width = navRect.width + 10;
		r.height = navRect.height + 10;
		if (isNavRectDown && pointIsInRect(point, r)) {
			navRect.x = point.x - navRectDown.x;
			navRect.y = point.y - navRectDown.y;
			isNavRectMoving = true;
		}

		paint();
	}

	/** 鼠标点击事件处理 */
	function onMouseClick(event) {
		point = {
			x : event.clientX,
			y : event.clientY
		};

		if (isNavRectMoving) {
			isNavRectMoving = false;
			return;
		}

		var r1 = {};
		r1.x = lButtonRect.x - 10;
		r1.y = lButtonRect.y - 10;
		r1.width = lButtonRect.width + 20;
		r1.height = lButtonRect.height + 20;

		var r2 = {};
		r2.x = rButtonRect.x - 10;
		r2.y = rButtonRect.y - 10;
		r2.width = rButtonRect.width + 20;
		r2.height = rButtonRect.height + 20;

		if (pointIsInRect(point, r1)) {// 左翻页
			nextPane(true);
		} else if (pointIsInRect(point, r2)) { // 右翻页
			nextPane(false);
		} else if (pointIsInRect(point, navRect)) {
			isImageFlip = false;
			var selectedIndex = findSelectImageIndex(point);
			if (selectedIndex != -1) { // 点中缩略图
				selectImage(selectedIndex);
			}
		} else { // 点中非导航栏
			var left = checkImagePartByPoint(point);
			if (left == null) { // 点中中部
				isImageFlip = isImageFlip ? false : true;
			} else if (left) {// 点中左部分
				isImageFlip = false;
				selectImage(currentImage - 1);
			} else { // 点中右部分
				isImageFlip = false;
				selectImage(currentImage + 1);
			}
			paint();
		}
	}

	/** 判断鼠标点中部分所在主图位置(null:中部|true:左部|false:右部) */
	function checkImagePartByPoint(point) {
		var w = canvas.width + 0;
		var w1 = Math.ceil(w / 3);
		var w2 = w1 * 2;

		if (0 < point.x && point.x <= w1) {
			return true;
		} else if (w1 < point.x && point.x <= w2) {
			return null;
		} else {
			return false;
		}
	}

	/** 鼠标按下 */
	function onMouseDown(event) {
		point = {
			x : event.clientX,
			y : event.clientY
		};

		if (pointIsInRect(point, navRect)) {
			isNavRectDown = true;
			navRectDown.x = point.x - navRect.x;
			navRectDown.y = point.y - navRect.y;
		}
	}

	/** 鼠标释放 */
	function onMouseUp(event) {
		point = {
			x : event.clientX,
			y : event.clientY
		};

		isNavRectDown = false;
	}

	/** 返回所点击的缩略图序号，如果没有点击缩略图则返回 -1 */
	function findSelectImageIndex(point) {
		for (var i = 0; i < thumbImageRects.length; i++) {
			if (pointIsInRect(point, thumbImageRects[i].rect)) {
				return i + firstImageIndex;
			}
		}
		return -1;
	}

	/** 将当前图片序号设为 index，重画 */
	function selectImage(index) {
		currentImage = index;
		paint();
	}

	/**
	 * 将缩略图翻页，更新缩略图中第一张图片的序号
	 * 
	 * @param previous
	 *            true:左移动|false:右移动
	 */
	function nextPane(previous) {
		if (previous) {
			firstImageIndex = firstImageIndex <= 0 ? 0 : firstImageIndex - 1
		} else {
			var imageCount = thumbImageLocations.length;
			var cha = imageCount - MAX_THUMB_SHOW_COUNT;
			firstImageIndex = (firstImageIndex >= cha ? cha
					: (firstImageIndex + 1));

		}
		paint();
	}

	/** 自动调整尺寸 */
	function resize() {
		paint();
	}

	/** 获取屏幕尺寸 */
	function getScreenSize() {
		return {
			width : document.documentElement.clientWidth,
			height : document.documentElement.clientHeight
		};
	}

	/** 获取尺寸 */
	function getSize() {
		// TODO 何雨 图片相册 以后实现根据浏览器可视区域自适应大小
		return {
			width : MAX_IMAGE_WIDTH,
			height : document.documentElement.clientHeight
		};
	}

	/** 画 */
	function paint() {
		context.clearRect(0, 0, canvas.width, canvas.height);
		paintImage(currentImage); // 画大图片
		paintButton();
		paintThumbNails(currentImage); // 导航栏缩略图
	}

	/** 对外暴露方法 上一张图片 */
	this.preImage = function() {
		isImageFlip = false;
		selectImage(currentImage - 1);
	}

	/** 对外暴掠方法 下一站图片 */
	this.nextImage = function() {
		isImageFlip = false;
		selectImage(currentImage + 1);
	}

	this.load = function() {
		// 加载缩略图
		loadThumbImages();

		// 窗口尺寸事件
		window.onresize = resize;
		window.onscroll = resize;

		// 事件绑定
		canvas.onclick = onMouseClick;
		canvas.onmousedown = onMouseDown;
		canvas.onmouseup = onMouseUp;
		canvas.onmousemove = onMouseMove;

		// 绘制
		// paint();
		selectImage(0);
	}
}

window.onload = function() {
//	var tb = new thumbnail('canvas');
//	tb.load();
//
//	// $('#canvas').click();
//
//	$("#centerDiv").click(function(event, a, b) {
//		// 一个普通的点击事件时，a和b是undefined类型
//		// 如果用下面的语句触发，那么a指向"foo",而b指向"bar"
//	}).trigger("click", [ "foo", "bar" ]);
}