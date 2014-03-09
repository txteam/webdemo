<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addPostType</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//验证器
	$('#postTypeForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
			$('#postTypeForm').ajaxSubmit({
			    url:"${contextPath }/postType/addPostType.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增postType成功");
						parent.DialogUtils.closeDialogById("addPostType");
					}
			    } 
			});
			return false;
	    }
	});
	
    //提交
	$("#addBtn").click(function(){
		$('#postTypeForm').submit();
	});
});
function submitFun(){
	$('#postTypeForm').submit();
}
function cancelFun(){
	parent.DialogUtils.closeDialogById("addPostType");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="postTypeForm" method="post" cssClass="form"
			modelAttribute="postType">
			<form:hidden path="id"/>
			<table class="common_table">
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">职位类型名:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="name" cssClass="text"
							data-rule="编号:required;;remote[${contextPath}/postType/validateNameIsExist.action, name, id]" 
							/>
					</td>
				</tr>
				<tr>
					<!--//TODO:修改字段是否必填,修改其中文名-->
					<th class="narrow" width="20%">code:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="code" cssClass="text"
							data-rule="编号:required;;remote[${contextPath}/postType/validateCodeIsExist.action, code, id]" 
							/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">remark:</th>
					<td width="80%">
						<form:input path="remark" cssClass="text"
							/>
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