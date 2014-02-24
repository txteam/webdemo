<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addPost</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	$("#organizationName").chooseOrganization({
		eventName : "chooseOrganizationForAddPost",
		contextPath : _contextPath,
		title : "请选择上级组织",
		width : 260,
		height : 400,
		handler : function(organization){
			$("#organizationName").val(organization.name);
			$("#organizationId").val(organization.id);
		},
		clearHandler: function(){
			$("#organizationName").val('');
			$("#organizationId").val('');
		}
	});
	
	$("#parentName").choosePost({
		organizationId : $("#organizationId").val(),
		eventName : "choosePostForAddPost",
		contextPath : _contextPath,
		title : "请选择上级组织",
		width : 750,
		height : 400,
		handler : function(post){
			$("#parentName").val(post.name);
			$("#parentId").val(post.id);
		},
		clearHandler: function(){
			$("#parentName").val('');
			$("#parentId").val('');
		}
	});
	//验证器
	$('#postForm').validator({
	    valid: function(){
	    	DialogUtils.progress({
	            text : '数据提交中，请等待....'
	    	});
	        //表单验证通过，提交表单到服务器
			$('#postForm').ajaxSubmit({
			    url:"${contextPath}/post/addPost.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("新增组织成功");
						parent.DialogUtils.closeDialogById("addPost");
					}
			    } 
			});
			return false;
	    }
	});
	
    //提交
	$("#addBtn").click(function(){
		$('#postForm').submit();
	});
});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form:form id="postForm" method="post" cssClass="form"
			modelAttribute="post">
			<table class="common_table">
				<tr>
					<th class="narrow">名称:<span class="tRed">*</span></th>
					<td>
						<form:input path="name"
							data-rule="名称:required;" 
							data-tip="必填"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">编号<span class="tRed">*</span></th>
					<td>
						<form:input path="code" cssClass="text"
							data-rule="编号:required;digits;remote[get:${contextPath }/post/postCodeIsExist.action, code]" 
							data-tip="不能重复的数字"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">所属组织<span class="tRed">*</span></th>
					<td>
						<input id="organizationId" name="organization.id" type="hidden"
							value="${organization.id }"
							readonly="readonly"/>
						<input id="organizationName" name="organization.name"
							value="${organization.name }" 
							data-rule="所属组织:required;"
							class="selectInput" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">上级职位</th>
					<td>
						<input id="parentId" name="parentId" type="hidden" 
							value="${parentPost.id }"
							readonly="readonly"/>
						<input id="parentName" name="parentName" class="linkInput" 
							value="${parentPost.name }"
							readonly="readonly"/>
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
</html>