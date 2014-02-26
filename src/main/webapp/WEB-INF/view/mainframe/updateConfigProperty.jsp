<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addPost</title>
<%@include file="../includes/commonHead.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		parent.DialogUtils.progress('close');
		//验证器
		$('#configPropertyForm').validator({
			valid : function() {
				DialogUtils.progress({
			        text : '数据提交中，请等待....'
				});
				//表单验证通过，提交表单到服务器
				$('#configPropertyForm').ajaxSubmit({
					url : "${contextPath}/configuration/updateConfigPropertyValue.action",
					success : function(data) {
						DialogUtils.progress('close');
						if (data) {
							parent.DialogUtils.tip("修改配置属性成功.");
							parent.DialogUtils.closeDialogById("updateConfigProperty");
						}
					}
				});
				return false;
			}
		});

		//提交
		$("#updateBtn").click(function() {
			$('#configPropertyForm').submit();
		});
	});
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false" title=""
			style="overflow: hidden;">
			<form id="configPropertyForm" method="post" class="form">
				<input id="hiddenConfigPropertyKey" type="hidden" name="key"
					value="${configProperty.key }" />
				<table class="common_table">
					<tr>
						<th class="narrow">名称:</th>
						<td>${configProperty.name }</td>
					</tr>
					<tr>
						<th class="narrow">配置项key:</th>
						<td>${configProperty.key }</td>
					</tr>
					<tr>
						<th class="narrow">配置项值:</th>
						<td><input name="value" value="${configProperty.value }"
							data-rule="${configProperty.validateExpression }" style="width:289px;"/></td>
					</tr>
					<tr>
						<th class="narrow">备注:</th>
						<td>${configProperty.description }</td>
					</tr>
					<tr>
						<td class="rightOperRow" colspan="4" style="padding-right: 50px">
							<a id="updateBtn" href="#" class="easyui-linkbutton">提交</a>  	
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>