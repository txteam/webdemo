<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addOrganization</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function(){
	parent.DialogUtils.progress('close');
	$("#chiefName").chooseOperator({
		eventName : "chooseOperatorForAddOrganization",
		contextPath : _contextPath,
		title : "请选择人员",  
		width : 800,
		height : 450,
		handler : function(operator){
			if(operator != null){
				$("#chiefName").val(operator.loginName);
				$("#chiefId").val(operator.id);
			}
		},
	    clearHandler: function(){
	    	$("#chiefName").val("");
			$("#chiefId").val("");
	    }
	});
	
	//虚中心与组织存在联动关系，如果选中需中心，则组织仅能在选中的虚中心中选取
	//所以在组织与虚中心任一发生变动时应当影响到对方
	/*
	$("#virtualCenterName").chooseVirtualCenter({
		eventName : "chooseVirtualCenterForAddOrganization",
		contextPath : _contextPath,
		title : "请选择上级虚中心",  
		width : 260,
		height : 400,
		handler : function(vc){
			if(vc != null){
				$("#virtualCenterName").val(vc.name);
				$("#vcid").val(vc.id);
				
				$("#parentName").val("");
				$("#parentId").val("");
			}
		},
	    clearHandler: function(){
	    	$("#virtualCenterName").val("");
			$("#vcid").val("");
			
			$("#parentName").val("");
			$("#parentId").val("");
	    }
	});
	*/
	
	$("#parentName").chooseOrganization({
		eventName : "chooseOrganizationForAddOrganization",
		contextPath : _contextPath,
		title : "请选择上级组织",
		width : 260,
		height : 400,
		handler : function(organization){
			if(organization != null){
				$("#parentName").val(organization.name);
				$("#parentId").val(organization.id);
				
				/*
				$("#virtualCenterName").val("");
				$("#vcid").val("");
				//根据选中的虚组织，查询其虚中心信息填入虚中心中
				$.get("${contextPath}/virtualCenter/findVirtualcenterById.action",{vcid: organization.vcid}, function(data){
					$("#virtualCenterName").val(data.name);
					$("#vcid").val(data.vcid);
				});
				*/
			}
		},
	    clearHandler: function(){
	    	$("#parentName").val("");
			$("#parentId").val("");
			
			$("#virtualCenterName").val("");
			$("#vcid").val("");
	    }
	});

	//验证器
	$('#organizationForm').validator({
	    valid: function(){
	    	var flag = false;
	    	if($.ObjectUtils.isEmpty($('#parentId').val())){
            	if($('#type').val() == '公司' || $('#type').val() == '分公司'){
            		flag = true;
            	}else{
            		flag = false;
            	}
            }else{
            	flag = true;
            }
	    	if(!flag){
	    		DialogUtils.alert('警告',"非分公司类型组织上级组织不允许为空",'warning');
	    		return false;
	    	}
	    	DialogUtils.progress({
		        text : '数据提交中，请等待....'
			});
	        //表单验证通过，提交表单到服务器
			$('#organizationForm').ajaxSubmit({
			    url:"${contextPath}/organization/addOrganization.action",
			    success: function(data) {
			    	DialogUtils.progress('close');
					if(data){
						DialogUtils.tip("新增组织成功");
						parent.DialogUtils.closeDialogById("addOrganization");
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
						<form:select path="type" cssClass="select" data-rule="组织类型:required;" >
							<option value="">--- 请选择 ---</option>
							<form:options items="${OrganizationTypes }"/>
						</form:select>
					</td>
					<th>上级组织</th>
					<td>
						<input id="parentId" name="parentId" type="hidden" readonly="readonly" value="${parentOrganization.id }"/>
						<input id="parentName" name="parentName" class="selectInput" readonly="readonly" value="${parentOrganization.name }"/>
					</td>
				</tr>
				<tr>
					<th class="narrow">编号<span class="tRed">*</span></th>
					<td>
						<form:input path="code" cssClass="text"
							data-rule="编号:required;digits;length[1~16];remote[${contextPath }/organization/organizationCodeIsExist.action, code]" 
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