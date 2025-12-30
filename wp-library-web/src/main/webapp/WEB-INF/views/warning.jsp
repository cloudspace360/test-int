<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<link type="image/x-icon"
	href="<c:url value="/resources/favicon.ico" />" rel="icon">
<html>
<head>
<c:forEach var="metaConfig" items="${metaConfigData}">
	<c:set var = "metaKey" value = "${metaConfig.key}"/>
	<c:set var = "metaValue" value = "${metaConfig.value}"/>
	<c:if test="${fn:contains(metaKey, 'common') || fn:contains(metaKey, 'warning')}" >
		${metaConfig.value}		
	</c:if>
</c:forEach>
<title>${localizedData.labels.prm_wpl_brand}
	${localizedData.labels.prm_wpl_search_page_title}</title>
<link rel="stylesheet" href="<c:url value='/resources/styles.css' />"
	type="text/css">
<script type="text/javascript"
	src="<c:url value="/resources/jquery-3.7.1.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/scripts.js" />"></script>
</head>

<body>
	<jsp:include page="header.jsp" />
	<div class="warning-content">
		${localizedData.labels.prm_wpl_no_document_found}</div>
	<jsp:include page="footer.jsp" />
</body>
</html>