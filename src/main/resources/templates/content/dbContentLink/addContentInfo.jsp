<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addContentInfo</title>
<%@include file="../../includes/commonHead.jsp" %>

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
			    url:"${contextPath }/contentInfo/add.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增内容信息成功.");
						parent.DialogUtils.closeDialogById("addContentInfo");
					}else{
						DialogUtils.alert("错误提示","新增内容信息失败.","error");
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
	parent.DialogUtils.closeDialogById("addContentInfo");
}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="entityForm" method="post" cssClass="form"
			modelAttribute="contentInfo">
			<form:hidden path="id"/>
			<table class="common_table">
				<tr>
					<th class="narrow" width="20%">类型:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="type.name" cssClass="text" cssStyle="width:200px" disabled="true" data-rule="类型:required;"/>
						<form:hidden path="type.code" cssClass="text" cssStyle="width:200px" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">分类:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="category.name" cssClass="text" cssStyle="width:200px" disabled="true" data-rule="分类:required;"/>
						<form:hidden path="category.code" cssClass="text" cssStyle="width:200px" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">名称:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="name" cssClass="text" cssStyle="width:200px" data-rule="名称:required;"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">排序值:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="orderIndex" cssClass="text" cssStyle="width:200px" data-rule="排序值:required;digits;"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">等级:</th>
					<td width="80%">
						<form:select path="level.code" cssClass="select" cssStyle="width:205px">
							<form:option value="">-- 请选择 --</form:option>
							<form:options items="${levels }" itemLabel="name" itemValue="code"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">内容:<span class="tRed">*</span></th>
					<td width="80%">
						<form:input path="content" cssClass="text" cssStyle="width:200px" data-rule="内容:required;"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">链接:</th>
					<td width="80%">
						<form:input path="linkUrl" rows="3" cssClass="text" cssStyle="width:400px"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">鼠标悬停内容:</th>
					<td width="80%">
						<form:input path="title" rows="3" cssClass="text" cssStyle="width:400px"/>
					</td>
				</tr>
				<tr>
					<th class="narrow" width="20%">备注:</th>
					<td width="80%">
						<form:textarea path="remark" rows="3" cssStyle="width:400px"/>
					</td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="2" style="padding-right: 50px">
						<a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>  
						<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</body>