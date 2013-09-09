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

</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'west',title:'组织结构',split:true" style="width:230px;">
		
	</div> 
	<div data-options="region:'center'" style="padding:5px;background:#eee;">
		<div class="easyui-layout" data-options="fit : true,border : false">
			<div data-options="region:'north',title:'查询条件',border:false" 
				style="height: 125px; overflow: hidden;">
				<form method="post" id="creditProductForm" name="creditProductForm" class="form">
				<!-- query condition -->
				<div class="form-table">
					<table>
						<tbody>
						<tr>
							<th class="narrow">所属组织:</th>
							<td><input name="productName" class="text" type="text" value=''/></td>
							<th class="narrow">职位:</th>
							<td>
								<input name="productName" class="text" type="text" value=''/>
							</td>
						</tr>
						<tr>
							<th class="narrow">用户编码:</th>
							<td><input name="productName" class="text" type="text" value=''/></td>
							<th class="narrow">登录名:</th>
							<td>
								<select name="productState" class="select">
									<optgroup label="---请选择---"> 
										<option>---请选择---</option>
										<option>---请选择---</option>
										<option>---请选择---</option>
										<option>---请选择---</option>
									</optgroup>
								</select>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="button operRow">
								<a id="ajaxQueryBtn" href="#" class="easyui-linkbutton">查询</a>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				</form>
			</div>
			<div data-options="region:'center',border:false">
				<table id="dataGrid"></table>
			</div>
		</div>
		
		<div id="toolbar" style="display: none;">
			<c:if test="${false}">
				<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'pencil_add'">添加</a>
			</c:if>
			<a onclick="redo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_next'">展开</a> 
			<a onclick="undo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'resultset_previous'">折叠</a> 
			<a onclick="treeGrid.treegrid('reload');" href="javascript:void(0);" 
				class="easyui-linkbutton" data-options="plain:true,iconCls:'transmit'">刷新</a>
		</div>
	
		<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
			<c:if test="${true}">
				<div onclick="addFun();" data-options="iconCls:'pencil_add'">增加</div>
			</c:if>
			<c:if test="${true}">
				<div onclick="deleteFun();" data-options="iconCls:'pencil_delete'">删除</div>
			</c:if>
			<c:if test="${true}">
				<div onclick="editFun();" data-options="iconCls:'pencil'">编辑</div>
			</c:if>
		</div>
	
	</div> 
</body>