<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片相册</title>
<%@include file="../includes/commonHead.jsp" %>

<link rel="stylesheet" href="${contextPath }/css/test/thumbnail.css" type="text/css" media="screen">
<script type="text/javascript" src="${contextPath}/js/test/thumbnail.js" charset="utf-8"></script>
</head>
<body>
	<div id="leftDiv"></div>
	<div id="centerDiv"></div>
	<div id="rightDiv"></div>
	<canvas id="canvas"></canvas>
</body>
</html>