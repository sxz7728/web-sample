<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="base" value="${pageContext.request.contextPath}" />

<c:choose>
	<c:when test="${properties.DEV_MODE}">
		<link href="${base}/css/bootstrap.css" rel="stylesheet"
			type="text/css"></link>
		<link href="${base}/css/bootstrap-theme.css" rel="stylesheet"
			type="text/css"></link>
		<link href="${base}/css/sb-admin-2.css" rel="stylesheet"
			type="text/css">
		<link href="${base}/font-awesome-4.1.0/css/font-awesome.css"
			rel="stylesheet" type="text/css">

		<link href="${base}/css/default.css" rel="stylesheet" type="text/css"></link>

		<script src="${base}/js/jquery-1.11.1.js" type="text/javascript"></script>
		<script src="${base}/js/jquery.validate.js" type="text/javascript"></script>
		<script src="${base}/js/jquery.form.js" type="text/javascript"></script>
		<script src="${base}/js/bootstrap.js" type="text/javascript"></script>
		<script src="${base}/js/doT.js"></script>
		<script src="${base}/js/localization/messages_zh.js"></script>

		<script src="${base}/js/utils.js"></script>
		<script src="${base}/js/default.js"></script>
	</c:when>
	<c:otherwise>
		<link href="${base}/css/bootstrap.min.css" rel="stylesheet"
			type="text/css"></link>
		<link href="${base}/css/bootstrap-theme.min.css" rel="stylesheet"
			type="text/css"></link>
		<link href="${base}/css/sb-admin-2.css" rel="stylesheet"
			type="text/css">
		<link href="${base}/font-awesome-4.1.0/css/font-awesome.min.css"
			rel="stylesheet" type="text/css">

		<link href="${base}/css/default.css" rel="stylesheet" type="text/css"></link>

		<script src="${base}/js/jquery-1.11.1.min.js" type="text/javascript"></script>
		<script src="${base}/js/jquery.validate.min.js" type="text/javascript"></script>
		<script src="${base}/js/jquery.form.js" type="text/javascript"></script>
		<script src="${base}/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${base}/js/doT.min.js"></script>
		<script src="${base}/js/localization/messages_zh.js"></script>

		<script src="${base}/js/utils.js"></script>
		<script src="${base}/js/default.js"></script>
	</c:otherwise>
</c:choose>

<script type="text/javascript">
var globals = {
	APP_NAME : '${base}'
};
</script>



