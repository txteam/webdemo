<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限配置帮助</title>
<%@include file="../../includes/commonHead.jsp" %>

<script type="text/javascript">

</script>
<style type="text/css">

</style>	
<body>
<div style="padding: 5px">
<pre>	
	项目中的权限使用规范：
		
	一、controller中的权限配置：
			为放置用户知道系统功能链接后直接跳过界面直接进行功能请求的方式：
			需在controller中添加对应的权限项
			该权限通过注解方式添加：
			在含有注解@Controller;@RequestMapping的类上添加
			@CheckOperateAuth(key = "organization_manage",parentKey="operator_config_center",description="组织结构管理",name="组织结构管理")
			通过该注解主要解决，该内中方法注解中的权限项，将为该权限项目的子权限
			parentKey=""主要是为权限指定其所属的父级权限，该权限对应关系建议在此处进行配置，方便迁移
			（当然也可以通过 xml的权限配置文件中添加，但不建议）
			在含有@RequestMapping的方法上配置权限检查项
			@CheckOperateAuth(key = "query_organization", name = "查询组织")
			在此处指定的权项目将被权限容器自动加载到权限容器中
参考配置：OrganizationController.java
		
	二、配置菜单中的权限项
			菜单中指定authKey=""如果对应controller中已经含有对应权，这里authKey应当与contrller.method中权限key一致
参考：menuConfig.xml		

	三、数据权限配置及使用
		在mybatis配置文件中可以通过这样的形式判断权限
		&lt;if test="!@com.tx.component.mainframe.context.WebContextUtils@hasAuth('query_all_org_post')"&gt;&lt;/if&gt;
参考PostSqlMap.xml
		对应权限项目配置于data_auth_config.xml

	四、权限配置文件中的：
			当前项目中仅存在主菜单：
	配置文件：
		src/main/resources/authcontext/data_auth_config.xml
		src/main/resources/authcontext/operate_auth_config.xml
		classpath:/authcontext/data_auth_config.xml
		classpath:/authcontext/operate_auth_config.xml
参考：operate_auth_config.xml
      data_auth_config.xml
			
	所以这里仅对主菜单配置进行说明：
		菜单项：
			key="cre_pro_queryCurrentOrg" 
			name="查询所属组织的贷款产品"
	权限项目之间具有互相包含关系
		如果嵌套配置权限项，则子权限项目的父级权限
<form class="form">
	<table>
		<tr>
			<th>key</th>
			<td>	
				权限项唯一键,允许重复,重复的权限项,<br/>
				如果优先记载的权限项目无说明,name,parentId,description<br/>
				将被后加载的存在的相应消息覆盖
			</td>
		</tr>
		<tr>
			<th>name</th>
			<td>权限项名</td>
		</tr>
		<tr>
			<th>description</th>
			<td>
				权限项目说明
			</td>
		</tr>
		<tr>
			<th>viewAble</th>
			<td>权限项目是否可见,适用于部分权限仅有超级管理员具有的情况</td>
		</tr>
		<tr>
			<th>configAble</th>
			<td>权限项是否可配置，即赋予权限,适用于部分权限仅有超级管理员具有的情况，但对于其他人可见的情况</td>
		</tr>
		<tr>
			<th>valid</th>
			<td>权限项是否有效，如果某权限项目，无须再进行鉴全，则可将对应权限项，设置为无效即可</td>
		</tr>
		<tr>
			<th>editAble</th>
			<td>权限项是是否可编辑，部分权限项，适用于：权限项目允许界面进行维护的情况</td>
		</tr>
	</table>
</form>
	
</pre>
</div>
</body>
</html>