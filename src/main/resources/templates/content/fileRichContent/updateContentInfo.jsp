<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<%@include file="../../includes/commonHead.jsp" %>
<%@include file="../../includes/uploaderHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${contextPath}/ueditor/ueditor.config.js"></script>
<script>
window.UEDITOR_CONFIG.serverUrl = '${contextPath}/contentUEditor/execute.action';
</script>
<script type="text/javascript" charset="utf-8" src="${contextPath}/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" >
var ue = null;
$(document).ready(function(){
	//实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	ue = UE.getEditor('ueditor', {
		initialFrameWidth : $('#container').width() - 6,
		initialFrameHeight : 490,
		autoClearinitialContent:true,//focus时自动清空初始化时的内容
		autoHeightEnabled:false
	});
	ue.ready(function(){
        ue.execCommand('serverparam', {
        	'entityAction': 'update',
        	'categoryCode': $("#categoryCode").val(),
			'entityId': $("#id").val()
        });
        ue.setContent($('#content').val());
    });
	
	//验证器
	$('#contentInfoForm').validator({
	    valid: function(){
	        //表单验证通过，提交表单到服务器
	        DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	        });
			$('#contentInfoForm').ajaxSubmit({
				url:"${contextPath }/contentInfo/update.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						parent.DialogUtils.tip("更新内容信息成功.");
						cancelFun();
					}else{
						parent.DialogUtils.tip("更新内容信息失败.");
					}
			    } 
			});
			return false;
	    }
	});
	
	//logo图片上传器
	$("#contentFileUploader").imageUploader({
		contextPath: _contextPath,//contextPath
		imageWidth:300,
		formData: {contentInfoId:'${contentInfo.id}',categoryCode:'${contentInfo.category.code}',contentFileId:$("#contentFileId").val()},
		server: _contextPath + "/contentInfo/saveContentFile.action",
        initFilter: function($wrap){
			var fileId = $('#fileId').val();
			var viewUrl = $('#fileUrl').val();
			
			var fileDefinitions = new Array({id:fileId,viewUrl:viewUrl});
			return fileDefinitions;
		},
		deleteSuccess: function(event,fd){
			$('#fileId').val('');
			$('#fileUrl').val('');
		},
		uploadSuccess: function(event,fd){
			$('#fileId').val(fd.id);
			$('#fileUrl').val(fd.viewUrl);
		}
	});
});
function submitFun(){
 	$('#content').val(ue.getContent());
	$('#contentInfoForm').submit();
}
function cancelFun(){
	$.triggerGE("addOrSelectTab",[{
		title : '内容管理',
		refreshWhenSelect : true,
        href : '${contextPath}/contentInfo/toQueryContentInfoPagedList.action'
    }]);
	$.triggerGE("closeTab",[{
        title : '更新内容信息'
    }]);
}
</script>
</head>
<body style="text-align: center;">
<div id="container" class="easyui-panel" style="width:1024px;margin:0 0 0 -512px;position:absolute;left:50%;height:auto;padding:4px;background:#fafafa;">
	<form:form id="contentInfoForm" method="post" cssClass="form"
		modelAttribute="contentInfo" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<table class="common_table">
			<tr>
				<th class="narrow" width="10%">类型:<span class="tRed">*</span></th>
				<td width="40%">
					<form:input path="type.name" cssClass="text" cssStyle="width:200px" disabled="true" data-rule="类型:required;"/>
					<form:hidden path="type.code" cssClass="text" cssStyle="width:200px" readonly="true"/>
				</td>
				<th class="narrow" width="10%">分类:<span class="tRed">*</span></th>
				<td width="40%">
					<form:input path="category.name" cssClass="text" cssStyle="width:200px" disabled="true" data-rule="分类:required;"/>
					<form:hidden id="categoryCode" path="category.code" cssClass="text" cssStyle="width:200px" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th class="narrow">名称:<span class="tRed">*</span></th>
				<td>
					<form:input path="name" cssClass="text" cssStyle="width:200px" data-rule="名称:required;"/>
				</td>
				<th class="narrow">标题:<span class="tRed">*</span></th>
				<td>
					<form:input path="title" cssClass="text" cssStyle="width:200px" data-rule="标题:required;"/>
				</td>
			</tr>
			<tr>
				<th class="narrow">排序值:<span class="tRed">*</span></th>
				<td>
					<form:input path="orderIndex" cssClass="text" cssStyle="width:200px" data-rule="排序值:required;digits;"/>
				</td>
				<th class="narrow">等级:</th>
				<td>
					<form:select path="level.code" cssClass="select" cssStyle="width:200px">
						<form:option value="">-- 请选择 --</form:option>
						<form:options items="${levels }" itemLabel="name" itemValue="code"/>
					</form:select>
				</td>
			</tr>		
			<tr>
				<td colspan="4">
					<textarea id="ueditor" rows="5"></textarea>
					<form:hidden path="content"/>			
				</td>
			</tr>
			<tr>
				<th class="narrow" width="20%">链接:</th>
				<td width="80%" colspan="3">
					<form:input path="linkUrl" cssClass="text" cssStyle="width:750px"/>
				</td>
			</tr>
			<tr>
				<th class="narrow" width="20%">备注:</th>
				<td width="80%" colspan="3">
					<form:input path="remark" cssClass="text" cssStyle="width:750px"/>
				</td>
			</tr>
			<tr>
				<td class="rightOperRow" colspan="4" style="padding-right: 50px">
					<a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a>  
					<a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a>	
				</td>
			</tr>
			<tr>
				<th class="narrow" width="20%">图片:</th>
				<td width="80%" colspan="3">
					<div id="contentFileUploader" style="min-height: 160px;width: 750px"></div>
					<form:hidden path="fileId"/>
					<form:hidden path="fileUrl"/>
				</td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>