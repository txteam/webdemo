<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>demo系统</title>
<link rel="stylesheet" href="${contextPath }/css/mainframe/login.css" type="text/css" media="screen">
	
<script type="text/javascript" src="${contextPath }/js/jquery.min.js"></script>	
<script type="text/javascript" src="${contextPath }/js/jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${contextPath }/js/validator-0.2.1/jquery.validator.js"></script>
<script type="text/javascript" src="${contextPath }/js/validator-0.2.1/local/zh_CN.js"></script>
<script type="text/javascript">
$(function() {
    var $dialog = $('<div/>');
    var $formBody = $('#form-body');
    $dialog.dialog({
        height: 300, 
        width: 500,
        content: $formBody.show(),
        noheader: true,
        buttons: [{
                id: 'loginBtn',
                disabled: true,
                text: '登陆',
                handler:function(){
                    $.post('public/dologin',$formBody.serialize(),function(rsp){
                          if(rsp.status){
                               window.location.reload();
                          }else{
                              $.messager.alert('提示',rsp.msg);
                          }
                    },'JSON').error(function(){
                        $.messager.alert('提示','系统错误！');
                    });
                }
            }]
    });
    
    $formBody.before($('#logo').show());
    //$('.dialog-button').prepend($verifyInput).prepend($verifyImg);
    $(window).resize(function() {
        $dialog.dialog('center');
    });
});


$(document).ready(function() {
	$("#loginBtn").click(function() {
		var $form = $("form");
		$form.attr("action","${contextPath}/mainframe/login.action");
		$("form").submit();
	});
});
</script>
<style type="text/css">

</style>	
    <body style="width: 100%;height: 100%;overflow: hidden;padding: 0;margin: 0;">
        <form id="form-body" method="post" style="display: none;"
        	data-validator-option="{stopOnError:false, timely:true}">
            <ul>
                <li>
                	<input class="form-radio-other-input" type="radio" name="type" value="1" checked="checked"><label>普通用户 </label> 
                	&nbsp;&nbsp;&nbsp;
                	<input class="form-radio-other-input" type="radio" name="type" value="0"> <label>管理员</label>
                </li>
                <li><label>账	号 </label> 
                	<input class="account form-textbox" type="text" 
                		name="loginName" value="admin" required="required"
                		data-rule="required;"></li>
                <li><label>密	码 </label> 
                	<input class="password form-textbox" type="password" 
                		name="password" value="admin"
                		data-rule="required;"></li>
            </ul>
        </form>

        <div id="logo"  style="display: none;">
            <h1>演示系统</h1>
        </div>
    </body>
</html>