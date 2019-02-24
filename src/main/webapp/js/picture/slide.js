var timer = null;
var offset = 5000;
var index = 0;
//缩略图显示区域宽度
var thumbnailWidth = 0;
//��ͼ�����ֻ�
function slideImage(i){
	var id = 'thumb_'+ target[i];
    var img = $('#' + id).find('img');
    var src = img.attr('src') + '&thumb=false';
    $("#picture").find('img').attr('src',src);
}

function resetRemark(i){
	var id = target[i];
	var remark = targetRemark[id];
	$("#id").val(id);
	$("#remark").val(remark);
}
//bind thumb a
function hookThumb(){    
    $('#thumbs li a')
        .bind('click', function(){
            if (timer) {
                clearTimeout(timer);
            }                
           var id = this.id;            
            index = getIndex(id.substr(6));
            rechange(index);
            slideImage(index); 
            resetRemark(index);
            //timer = window.setTimeout(auto, offset);  
            this.blur();            
            return false; 
        });
}
//bind next/prev img
function hookBtn(){
    $('#thumbs img').filter('#play_prev,#play_next')
        .bind('click', function(){
            /*if (timer){
                clearTimeout(timer);
            }*/
            var id = this.id;
            var m = calculate();
            if (id == 'play_prev') {
                index--;
                if (index < 0) {
                	index = target.length - 1;
                	if (thumbnailWidth - m[1] < 0){
                		$("#pic_list ul").animate({"marginLeft" : thumbnailWidth - m[1]},100);
                	}
                }
                //向右滚动
                if (m[0] < 0){
                	next(1,m);
                }
            }else{
                index++;
                if (index > target.length - 1) {
                	index = 0;
                	$("#pic_list ul").animate({"marginLeft" : 0},100);
                }
                //向左滚动
            	if(m[0] + m[1] > thumbnailWidth){
            		forword(1,m);
            	}
                
            }
            rechange(index);
            slideImage(index);
            resetRemark(index);
            //timer = window.setTimeout(auto, offset);
        });
}

function bighookBtn(){
	$("#foward").bind("click",function(){ 
            if (timer){
                clearTimeout(timer);
            }
            var id = this.id;
            var m = calculate();

            index--;
            if (index < 0) {
            	index = target.length - 1;
            	if (thumbnailWidth - m[1] < 0){
            		$("#pic_list ul").animate({"marginLeft" : thumbnailWidth - m[1]},100);
            	}
            }
            //向右滚动
            if (m[0] < 0){
            	next(1,m);
            }
            rechange(index);
            slideImage(index);
            resetRemark(index);
            //timer = window.setTimeout(auto, offset);
        });
	$("#next").bind("click",function(){
		if (timer){
            clearTimeout(timer);
        }
        var id = this.id;
        var m = calculate();
		index++;
        if (index > target.length - 1) {
        	index = 0;
        	$("#pic_list ul").animate({"marginLeft" : 0},100);
        }
        //向左滚动
    	if(m[0] + m[1] > thumbnailWidth){
    		forword(1,m);
    	}
    	rechange(index);
        slideImage(index);
        resetRemark(index);
	}); 
}

//get index
function getIndex(v){
    for(var i=0; i < target.length; i++){
        if (target[i] == v) return i;
    }
}
function rechange(loop){
    var id = 'thumb_'+ target[loop];
    $('#thumbs li a.current').removeClass('current');
    $('#'+ id).addClass('current');
}
function calculate(){
	var marginLeft = parseInt($("#pic_list ul").css("margin-left"));
	var ulWidth = $("#pic_list ul").width();
	var m = [];
	m[0] = marginLeft;
	m[1] = ulWidth;
	return m;
}
function forword(step,m){
	$("#pic_list ul").animate({"marginLeft" : m[0] - 104 * step},100)
}
function next(step,m){
	$("#pic_list ul").animate({"marginLeft" : m[0] + 104 * step},100)
}
function auto(){
    index++;
    if (index > target.length - 1){
        index = 0;
    }
    rechange(index);
    slideImage(index);
    timer = window.setTimeout(auto, offset);
}
$(function(){    
    //change opacity
    $('div.word').css({opacity: 0.85});
    //auto();  
    hookThumb(); 
    hookBtn();
	bighookBtn();
	var len = $("#pic_list ul li").length;
    $("#pic_list ul").css({"width":104 * len});
    thumbnailWidth = $("#pic_list").width();
});