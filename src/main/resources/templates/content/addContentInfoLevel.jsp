<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addContentInfoLevel</title>
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
			    url:"${contextPath }/contentInfoLevel/add.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增内容类型成功");
						parent.DialogUtils.closeDialogById("addContentInfoLevel");
					}else{
						parent.DialogUtils.tip("新增内容类型失败");
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
	parent.DialogUtils.closeDialogById("addContentInfoLevel");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="entityForm" method="post" cssClass="form"
			modelAttribute="contentInfoLevel">
			<form:hidden path="id"/>
			<table class="common_table">
				<tr>
					<th class="narrow" width="20%">编码:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="code" cssClass="text" 
							data-rule="编码:required;remote[${contextPath}/contentInfoLevel/validateCodeIsExist.action, code, id]" 
						/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">内容类型:<span class="tRed">*</span></th>
					<td width="80%">
						<select id="category.code" name="category.code" style="width:300px"
							data-rule="内容类型:required;">
							<option value="">-- 请选择 --</option>
							<c:forEach items="${categories }" var="categoryTemp">
								<option value="${categoryTemp.code }">${categoryTemp.code }&nbsp;&nbsp;${categoryTemp.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">名称:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="name" cssClass="text" 
							data-rule="名称:required;"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">备注:</th>
					<td width="80%">
						<form:textarea path="remark" cssClass="textarea" rows="3" cols="55"/>
					</td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px">
						<a id="submitB	tn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>  
						<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</body>