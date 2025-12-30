<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:set var = "CDNUrl" value = "https://cdn.cookielaw.org/scripttemplates/otSDKStub.js"/>
<c:if test="${gaPageViewEnabled}">
	<script>
		var gtmId = $('#seGtmId').val();
		console.log('GTMID-> '+gtmId);
		(function (w, d, s, l, i) {		
			w[l] = w[l] || []; w[l].push({ 'gtm.start': new Date().getTime(), event: 'gtm.js' });
			var f = d.getElementsByTagName(s)[0],
			  j = d.createElement(s),
			  dl = l != 'dataLayer' ? '&l=' + l : '';
			j.async = true;
			j.src = 'https://www.googletagmanager.com/gtm.js?id=GTM-MM5LXGL';
			f.parentNode.insertBefore(j, f);
		})(window, document, 'script', 'dataLayer', gtmId);
	</script>

	<script>
		window.dataLayer = window.dataLayer || []
		window.dataLayer.push({
			"event" : $('#pageViewEventName').val(),
			"environment" : $('#environment').val(),
			"digitalPlatform" : $('#digitalPlatform').val(),
			"template" : $('#template').val(),
			"pageTitle" : $('#pageTitle').val(),
			"digitalPlatformCountry" : $('#digitalPlatformCountry').val(),
			"pageLanguage" : $('#pageLanguage').val(),
			"businessUnit" : $('#businessUnit').val(),
			"articleTitle" : $('#articleTitle').val(),
			"fromWebView" : Boolean(0),
			"pageTopCategory" : $('#pageTopCategory').val(),
			"pageCategory" : $('#pageCategory').val(),
			"pageSubCategory" : $('#pageSubCategory').val(),
			"pageSubSubCategory" : $('#pageSubSubCategory').val(),
			"pageCategoryId" : $('#pageCategoryId').val(),
			"pageSubCategoryId" : $('#pageSubCategoryId').val(),
			"pageRangeId" : $('#pageRangeId').val(),
			"pageProductId" : $('#pageProductId').val()
		});
	</script>
	<c:if test="${!ga4Enabled}">
	<%-- OneTrust Cookies Consent Notice start for myseus.schneider-electric.com --%>
	<script src="${CDNUrl}"
		type="text/javascript" charset="UTF-8"
		data-domain-script="${oneTrustId}"></script>
	<script type="text/javascript">
		function OptanonWrapper() {
		}
	</script>
	<%-- OneTrust Cookies Consent Notice end for myseus.schneider-electric.com --%>
	</c:if>
</c:if>