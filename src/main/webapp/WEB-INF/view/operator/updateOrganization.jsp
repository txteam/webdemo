<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryOrganizationList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	$("#parentName").chooseOrganization({
		eventName : "chooseOrganizationForAddOrganization",
		contextPath : _contextPath,
		title : "请选择上级组织",
		width : 260,
		height : 400,
		handler : function(organization){
			$("#parentName").val(organization.name);
			$("#parentId").val(organization.id);
		}
	});
	//验证器
	$('#organizationForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
			$('#organizationForm').ajaxSubmit({
			    url:"${contextPath}/organization/updateOrganization.action",
			    success: function(data) {
					if(data){
						parent.DialogUtils.tip("更新组织成功");
						parent.DialogUtils.closeDialogById("updateOrganization");
					}
			    } 
			});
			return false;
	    }
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
			<table>
				<tr>
					<th class="narrow">名称:<span class="tRed">*</span></th>
					<td>
						<form:input path="name" cssClass="text"
							data-rule="名称:required;" 
							data-tip="必填"/>
					</td>
					<th class="narrow">别名:</th>
					<td>
						<form:input path="alias" cssClass="text"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">编号<span class="tRed">*</span></th>
					<td>
						<form:input path="code" cssClass="text"
							data-rule="编号:required;digits;remote[get:${contextPath }/organization/organizationCodeIsExist.action, id, code]" 
							data-tip="不能重复的数字"/>
					</td>
					<th class="narrow">组织类型:</th>
					<td>
						<form:select path="type" cssClass="select" >
							<option value="">--- 请选择 ---</option>
							<form:options items="${OrganizationTypes }"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<th>是否有效</th>
					<td>
						是<form:radiobutton path="valid" value="1"/>
						否<form:radiobutton path="valid" value="0"/>
					</td>
					<th>上级组织</th>
					<td>
						<input id="parentId" name="parentId" type="hidden" readonly="readonly"/>
						<input id="parentName" name="parentName" class="selectInput" readonly="readonly" 
							value="${parentOrganizationName}"/>
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