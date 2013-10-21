<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add demo</title>
<%@include file="../includes/commonHead.jsp" %>

<script type="text/javascript" >
$(document).ready(function() {

$("#tree1").wijtree({
	showCheckBoxes: true, 
});
$("#tree1 li").has("ul").wijtreenode("option", "collapsedIconClass", "ui-icon-folder-collapsed") 
.wijtreenode("option", "expandedIconClass", "ui-icon-folder-open"); 
$("#tree1 li").not($("#tree1 li").has("ul")).wijtreenode("option", "itemIconClass", "ui-icon-document"); 



});
</script>
</head>
<body class="ui-widget-content">
<div class="header">常用菜单>queryAuthTreeDemo</div>
<div class="header">tree1</div>
<div id="tree1Div">
<ul id="tree1" > 
<c:forEach items="${menuItemTreeList }" var="menuItem" varStatus="status">
	<li><a>${menuItem.text } ${fn:length(menuItem.childs)}</a>
	<c:if test="${menuItem.childs != null && fn:length(menuItem.childs) > 0 }">
		<ul>
		<c:forEach items="${menuItem.childs }" var="childMenuItem" varStatus="status">
			<li><a>${childMenuItem.text }</a>
			<c:if test="${childMenuItem.childs != null && fn:length(childMenuItem.childs) > 0 }">
				<ul>
				<c:forEach items="${childMenuItem.childs }" var="childSubMenuItem" varStatus="status">
					<li><a>${childSubMenuItem.text }</a></li>
				</c:forEach>
				</ul>
			</c:if>
			</li>
		</c:forEach>
		</ul>
	</c:if>
	</li>
</c:forEach>
</ul>
</div>
</body>
</html>