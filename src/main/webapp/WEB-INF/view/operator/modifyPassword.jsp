<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modifyPassword</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	//验证器
	$('#operatorForm').validator({
	    valid: function(){
	    	var newPassword = $("#newPassword").val();
	    	var confirmPassword = $("#confirmPassword").val();
	    	if(newPassword != confirmPassword){
	    		DialogUtils.tip("两次密码输入不一致");
	    		return false;
	    	}
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
			$('#operatorForm').ajaxSubmit({
			    url:"${contextPath}/operator/modifyPassword.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
			    	if(data['error']){
			    		DialogUtils.tip(data['error']);
			    	}else if(data['ok']){
			    		alert(data['ok']);
			    		parent.DialogUtils.closeDialogById("modifyPassword");
			    		$.triggerge("logout");
			    		window.location.href = _contextPath + "/mainframe/logout.action";
			    	}
			    } 
			});
			return false;
	    }
	});
});
//提交
function submitFun(){
	$('#operatorForm').submit();
}
 //取消
function cancelFun(){
	parent.DialogUtils.closeDialogById("modifyPassword");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="operatorForm" method="post" class="form">
			<table class="common_table">
				<tr>
					<th class="narrow" width="20%">原密码：<span class="tRed">*</span></th>
					<td width="80%">
						<input type="password"  name="password" id="password" data-rule="原密码:required;"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">新密码：<span class="tRed">*</span></th>
					<td>
						<input type="password" name="newPassword" id="newPassword" data-rule="新密码:required;"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">确认密码：<span class="tRed">*</span></th>
					<td>
						<input type="password" name="confirmPassword" id="confirmPassword" data-rule="确认密码:required;"/>
					</td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px">
						<a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>  
						<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>&nbsp;&nbsp;&nbsp;&nbsp;	
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>