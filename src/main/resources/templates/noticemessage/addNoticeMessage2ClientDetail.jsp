<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addNoticeMessage2ClientDetail</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//验证器
	$('#entityForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
			$('#entityForm').ajaxSubmit({
			    url:"${contextPath }/noticeMessage2ClientDetail/add.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增站内消息成功.");
						parent.DialogUtils.closeDialogById("addNoticeMessage2ClientDetail");
					}else{
						DialogUtils.alert("错误提示","新增站内消息失败.","error");
					}
			    } 
			});
			return false;
	    }
	});
});
function submitFun(){
	$('#entityForm').submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("addNoticeMessage2ClientDetail");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="entityForm" method="post" cssClass="form"
			modelAttribute="noticeMessage2ClientDetail">
			<form:hidden path="id"/>
			<table class="common_table">
				<tr>
					<th class="narrow" width="20%">标题:</th>
					<td width="80%">
						<form:input path="title" cssClass="text" />
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">内容:</th>
					<td width="80%">
						<form:input path="content" cssClass="text" />
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">消息优先级:</th>
					<td width="80%">
						<form:select path="priority" 
							items="${priorities }" itemLabel="name" itemValue="key"></form:select>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">站内消息类型:</th>
					<td width="80%">
						<form:select path="noticeMessageTypeId" 
							items="${noticeMessageTypes }" itemLabel="name" itemValue="id"></form:select>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">发送客户:</th>
					<td width="80%">
						<form:select path="clientId" 
							items="${clientInfos }" itemLabel="mobilePhone" itemValue="id"></form:select>
					</td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px">
						<a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>  
						<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</body>