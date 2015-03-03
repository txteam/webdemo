<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单据附件</title>

<%@include file="../includes/commonHead.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/components/fileupload/jquery.fileupload-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/components/fileupload/jquery.fileupload.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/components/fileupload/jquery.fileupload-ui.js" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	
	//文件上传
	$("#processDefFile").change(function(){
		var filePath = $("#processDefFile").val();
		if(filePath == '' || filePath == null ){
			$("#processDefFile").val("");
			DialogUtils.tip("您未选择文件！");
			return ;
		}
		 DialogUtils.progress({
	        	text : '数据提交中，请等待....'
	     });
		 $('#uploafForm').ajaxSubmit({
		 url:"${contextPath}/loanBillAttachment/doUpload.action",
		    success: function(data) {
		    	DialogUtils.progress('close');
				if(data){
					parent.DialogUtils.tip("成功");
				}else{
					parent.DialogUtils.alert('提示',"失败","info");
				}
		    } 
		});
		$("#processDefFile").val("");
		return false;
	});
	
	$("#toUploadLoanBillAttachments").click(function(){
		GlobalDialogUtils.openModalDialog(
			"uploadLoanBillAttachments",
			"上传附件",
			$.formatString("${contextPath}/loanBillAttachment/toUploadLoanBillAttachments.action?serviceType={0}&loanBillId={1}&clientInfoId={2}",$("#serviceType").val(),"111","111"),
			800,600,function(){
		});
	});
})
</script>
</head>
<body>
<form id = "exportForm"></form>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'center',border:true">
		<form id="uploafForm" class="form">
			<input type="hidden" id="loanBillId" name="loanBillId"  value="0000000000000000">
			<input type="hidden" id="clientId" name="clientId"  value="0000000000000000">
		 	<table style="border: 0px;">
		 		<tr>
		 			<td width="60%">&nbsp;</td>
		 			<td>
			    		<span id="processFileUploadView">
			    			<span class="fileupload-buttonbar" style="margin-lft: 200px" id="fileupload-buttonbar">
								<span class="fileinput-button">
									<a id="uploadALink" class="easyui-linkbutton fileinput-button">
								    	 选择文件...
						     			<input id="processDefFile" name="processDefFile" type="file"/>
						     		</a>
					     		</span>
				    		</span> 
			    		</span>
		    		</td>
		 		</tr>
		 	</table>
		</form>
		<br/>
		<div>
			<select class="select" id="serviceType" name="serviceType">
				<c:forEach items="${serviceTypes }" var="serviceTypeTemp">
					<option value="${serviceTypeTemp.code }">${serviceTypeTemp.name }</option>
				</c:forEach>
			</select>
			<a id="toUploadLoanBillAttachments" class="easyui-linkbutton">跳转到上传附件</a>
		</div>
		<br/>
		<div>
			contextPath + /fileContext/resource/imageResource?fileDefinitionId=123
			<br>
			contextPath + /fileContext/resource/thumbnailImageResource?fileDefinitionId=123
		</div>
    </div>
</div>
</body>