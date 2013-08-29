<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queryOrganizationList</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$('#parentId').combotree({
	url : '${contextPath}/organization/queryOrganizationList.action',
	parentField : 'parentId',
	textFiled : 'name',
	lines : true,
	panelHeight : 'auto',
	onLoadSuccess : function() {
		$('#parentId').addClass("span3");

		parent.$.messager.progress('close');
	}
	
	$('#form').validator({
	    //关闭此开关，以便一次性显示所有消息
	    stopOnError: false,
	    msgHandler: function(msgs){
	    	alert("msgHandler");
	        var html = '';
	        $.map(msgs, function(msg){
	            html += '<p class="red">'+ msg +'</p>'
	        });
	        $('#msg_holder').html(html);
	    }
	});
});
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post" class="form">
			<table>
				<tr>
					<th class="narrow">编号<span class="tRed">*</span></th>
					<td>
						<input name="code" type="text" value="" class="text"
							data-rule="编号:required;username">
					</td>
					<th>名称</th>
					<td>
						<input name="name" type="text" value="" class="text"
							data-rule="名称:required;"></td>
				</tr>
				<tr>
					<th>资源路径</th>
					<td>
						<input name="url" type="text" placeholder="请输入资源路径" 
							class="text easyui-validatebox" value="">
					</td>
					<th>组织类型</th>
					<td>
						<select name="typeId" class="easyui-combobox select">
							<c:forEach items='' var="resourceType">
								<option value="${resourceType.id}">${resourceType.name}</option>
							</c:forEach>
							<option value="公司部门">公司部门</option>
							<option value="分公司部门">分公司部门</option>
							<option value="合作方">合作方</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>排序</th>
					<td>
						<input name="seq" value="100" class="easyui-numberspinner" 
							style="width: 140px; height: 29px;" required="required" data-options="editable:false,min:100">
					</td>
					<th>上级资源</th>
					<td>
						<select id="parentId" name="parentId" class="select"
							style="height: 29px;">
						</select>
						<img src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png" onclick="$('#pid').combotree('clear');" />
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="remark" rows="" cols="" class="span5"></textarea></td>
				</tr>
				<tr>
					<td><input type="submit" value="提交"/></td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>