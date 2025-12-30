<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html; charset=UTF-8"	errorPage="showError.jsp" pageEncoding="UTF8"%>

<%-- <c:if test="${not empty pageViewDataLayer}">
	<input type="hidden" name="seGtmId" id="seGtmId" value="${gtmId}"/>
	<input type="hidden" name="pageViewEventName" id="pageViewEventName" value="${pageViewDataLayer.event}"/>
	<input type="hidden" name="environment" id="environment" value="${pageViewDataLayer.environment}"/>
	<input type="hidden" name="articleTitle" id="articleTitle" value="${pageViewDataLayer.articleTitle}"/>
	<input type="hidden" name="businessUnit" id="businessUnit" value="${pageViewDataLayer.businessUnit}"/>
	<input type="hidden" name="digitalPlatform" id="digitalPlatform" value="${pageViewDataLayer.digitalPlatform}"/>
	<input type="hidden" name="fromWebView" id="fromWebView" value="${pageViewDataLayer.fromWebView}"/>
	<input type="hidden" name="template" id="template" value="${pageViewDataLayer.template}"/>
	<input type="hidden" name="digitalPlatformCountry" id="digitalPlatformCountry" value="${pageViewDataLayer.digitalPlatformCountry}"/>
	<input type="hidden" name="pageLanguage" id="pageLanguage" value="${pageViewDataLayer.pageLanguage}"/>
	<input type="hidden" name="pageTitle" id="pageTitle" value="${pageViewDataLayer.pageTitle}"/>
	<input type="hidden" name="pageTopCategory" id="pageTopCategory" value="${pageViewDataLayer.pageTopCategory}"/>
	<input type="hidden" name="pageCategory" id="pageCategory" value="${pageViewDataLayer.pageCategory}"/>
	<input type="hidden" name="pageSubCategory" id="pageSubCategory" value="${pageViewDataLayer.pageSubCategory}"/>
	<input type="hidden" name="pageSubSubCategory" id="pageSubSubCategory" value="${pageViewDataLayer.pageSubSubCategory}"/>
	<input type="hidden" name="pageCategoryId" id="pageCategoryId" value="${pageViewDataLayer.pageCategoryId}"/>
	<input type="hidden" name="pageSubCategoryId" id="pageSubCategoryId" value="${pageViewDataLayer.pageSubCategoryId}"/>
	<input type="hidden" name="pageRangeId" id="pageRangeId" value="${pageViewDataLayer.pageRangeId}"/>
	<input type="hidden" name="pageProductId" id="pageProductId" value="${pageViewDataLayer.pageProductId}"/>
</c:if>
<input type="hidden" name="event" id="event" value="${gaDownloadDataLayer.event}"/>
 --%>
 
 <c:if test="${not empty pageViewDataLayer}">
    <script type="text/javascript">
        var pageViewData = {
            seGtmId: '${gtmId}',
            pageViewEventName: '${pageViewDataLayer.event}',
            environment: '${pageViewDataLayer.environment}',
            articleTitle: '${pageViewDataLayer.articleTitle}',
            businessUnit: '${pageViewDataLayer.businessUnit}',
            digitalPlatform: '${pageViewDataLayer.digitalPlatform}',
            fromWebView: '${pageViewDataLayer.fromWebView}',
            template: '${pageViewDataLayer.template}',
            digitalPlatformCountry: '${pageViewDataLayer.digitalPlatformCountry}',
            pageLanguage: '${pageViewDataLayer.pageLanguage}',
            pageTitle: '${pageViewDataLayer.pageTitle}',
            pageTopCategory: '${pageViewDataLayer.pageTopCategory}',
            pageCategory: '${pageViewDataLayer.pageCategory}',
            pageSubCategory: '${pageViewDataLayer.pageSubCategory}',
            pageSubSubCategory: '${pageViewDataLayer.pageSubSubCategory}',
            pageCategoryId: '${pageViewDataLayer.pageCategoryId}',
            pageSubCategoryId: '${pageViewDataLayer.pageSubCategoryId}',
            pageRangeId: '${pageViewDataLayer.pageRangeId}',
            pageProductId: '${pageViewDataLayer.pageProductId}'
        };
    </script>
</c:if>
<script type="text/javascript">
    var gaDownloadData = {
        event: '${gaDownloadDataLayer.event}'
    };
</script>
 