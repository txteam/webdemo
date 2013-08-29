<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
});

$('#form').validator(fun);
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post" class="form-table">
			<table class="table table-striped table-condensed table-hover">
				<tr>
					<th>编号<span class="color:red">*</span></th>
					<td>
						<input name="code" type="text" value="" class="span2"
							data-rule="编号:required;username">
					</td>
					<th>名称</th>
					<td>
						<input name="name" type="text" value="" class="span2"
							data-rule="名称:required;"></td>
				</tr>
				<tr>
					<th>资源路径</th>
					<td>
						<input name="url" type="text" placeholder="请输入资源路径" 
							class="easyui-validatebox span2" value="">
					</td>
					<th>组织类型</th>
					<td>
						<select name="typeId" class="easyui-combobox span3">
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
						<select id="parentId" name="parentId" class="span3"
							style="height: 29px;">
						</select>
						<img src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png" onclick="$('#pid').combotree('clear');" />
					</td>
				</tr>
				<tr>
					<th>备注</th>
					<td colspan="3"><textarea name="remark" rows="" cols="" class="span5"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>