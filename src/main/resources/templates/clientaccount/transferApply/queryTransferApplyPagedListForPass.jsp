<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addBaseClientInfo</title>
<%@include file="../../includes/commonHead.jsp"%>

<script type="text/javascript">
	$ (document).ready (function (){
	    parent.DialogUtils.progress ('close');
	    //验证器
	    $ ('#form').validator (
	    {
		    valid : function ()
		    {
			    //表单验证通过，提交表单到服务器
			    DialogUtils.progress (
			    {
				    text : '数据提交中，请等待....'
			    });
			    $ ('#form').ajaxSubmit (
			    {
			        url : "${contextPath }/transferApplyRecord/approveToPass.action",
			        success : function (data)
			        {
				        DialogUtils.progress ('close');
				        if (data)
				        {
					        $ (".disableButton").linkbutton ('enable');
					        parent.DialogUtils.tip ("转账通过处理完成.");
					        parent.DialogUtils.closeDialogById ("transferApplySuccess");
				        }
				        else
				        {
					        parent.DialogUtils.tip ("转账通过处理失败。");
				        }
			        }
			    });
			    return false;
		    }
	    });
    });
	
    function submitFun ()
    {
	    $ ("#form").submit ();
    }
    function cancelFun ()
    {
	    parent.DialogUtils.closeDialogById ("transferApplySuccess");
    }
</script>
</head>
<body>
	<div class="easyui-layout" id="container" data-options="fit:true,border:false" style="overflow-y: auto">
		<form method="post" class="form" id="form" name="form">
			<input type="hidden" id="transferApplyId" name="transferApplyId" value="${transferApplyRecord.id }" />
			<table class="common_table">
				<tr>
					<th class="narrow" width="20%">备注:</th>
					<td colspan="3"><textarea name="approveRemark"  rows="8" style="width: 90%"
											  id="approveRemark" >${transferApplyRecord.remark} </textarea>
					</td>
					</tr>
				<tr>
					<td class="rightOperRow" colspan="4" style="padding-right: 50px"><a id="submitBtn" onclick="submitFun();return false;" href="#" class="easyui-linkbutton">提交</a> <a id="cancelBtn" onclick="cancelFun();return false;" href="#" class="easyui-linkbutton">取消</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>