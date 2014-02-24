<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>updateOrganization</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	$('#organizationForm').bind('valid.form', function(){
			DialogUtils.progress({
		        text : '数据提交中，请等待....'
			});
		    //ajax提交
			$(this).ajaxSubmit({
			    url:"${contextPath}/organization/updateOrganization.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						DialogUtils.tip("更新组织成功");
						parent.DialogUtils.closeDialogById("updateOrganization");
					}
			    } 
			});
	});
    //提交
	$("#addBtn").click(function(){
		$('#organizationForm').submit();
	});
});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="organizationForm" method="post" cssClass="form"
			modelAttribute="organization">
			<form:hidden path="id"/>
			<table class="common_table">
				<tr>
					<th class="narrow">名称:<span class="tRed">*</span></th>
					<td>
						<form:input path="name" cssClass="text"
							data-rule="名称:required;length[2~16]" 
							data-tip="必填"/>
					</td>
					<th class="narrow">&nbsp;</th>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<th class="narrow">组织类型:<span class="tRed">*</span></th>
					<td>
						<c:out value="${organization.type}"></c:out>
					</td>
					<th>上级组织</th>
					<td>
						<input id="parentId" name="parentId" type="hidden" readonly="readonly"
							value="${organization.parentId}"/>
						<input id="parentName" name="parentName" class="selectInput" readonly="readonly" 
							value="${parentOrganizationName}"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">编号<span class="tRed">*</span></th>
					<td>
						<form:input path="code" cssClass="text"
							data-rule="编号:required;digits;length[1~16];remote[${contextPath }/organization/organizationCodeIsExist.action, code, id]" 
							data-tip="不能重复的数字"/>
					</td>
					<th class="narrow">别名:</th>
					<td>
						<form:input path="alias" cssClass="text"
							data-rule="别名:length[~16]"/>
					</td>
				</tr>

				<tr>
					<th>主管类型</th>
					<td>
						<form:radiobuttons path="chiefType" items="${chiefTypes }"/>
					</td>
					<th>主管</th>
					<td>
						<input id="chiefId" name="chiefId" type="hidden" readonly="readonly"/>
						<input id="chiefName" name="chiefName" class="selectInput" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>所属区域</th>
					<td colspan="3">
						<input name="fullAddress" type="text" class="longText" value=""/>
					</td>
				</tr>
				<tr>
					<th>地址</th>
					<td colspan="3">
						<input name="address" type="text" class="longText" value=""/>
						
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3">
						<textarea name="remark" rows="" cols="" class="longText"></textarea>
					</td>
				</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px">
						<a id="addBtn" href="#" class="easyui-linkbutton">提交</a>  	
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</body>