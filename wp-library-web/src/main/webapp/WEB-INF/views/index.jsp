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
	<c:if test="${fn:contains(metaKey, 'common') || fn:contains(metaKey, 'index')}" >
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
	src="<c:url value="/resources/scripts.js" />">
	
	</script>
	<script type="text/javascript"
	src="<c:url value="/resources/gaDownload.js" />">
	
	</script>
	<%-- QDS OPEN v4 CDNs --%>
                    <%-- For Desktop --%>
                    <link rel="stylesheet" type="text/css"
                        href="https://d2osz8slymlqdp.cloudfront.net/@quartzds/se-tokens/@1.0.0-beta.11/dist/websites/platform/desktop.css"
                        media="screen and (min-width: 900px)"
                        onload="document.documentElement.classList.add('qds-platform-desktop');"
                    />
         
                    <%-- For Mobile --%>
                    <link rel="stylesheet" type="text/css"
                        href="https://d2osz8slymlqdp.cloudfront.net/@quartzds/se-tokens/@1.0.0-beta.11/dist/websites/platform/mobile.css"
                        media="screen and (max-width: 900px)"
                        onload="document.documentElement.classList.add('qds-platform-mobile');"
                    />
         
                    <%-- Theme  --%>
                    <link rel="stylesheet"
                        media="(prefers-color-scheme:light)"
                        href="https://d2osz8slymlqdp.cloudfront.net/@quartzds/se-tokens/@1.0.0-beta.11/dist/core/theme/light.css"    
                        onload="document.documentElement.classList.add('qds-theme-light');
                        document.documentElement.style.cssText += '--qds-font-family-brand:inherit; --qds-font-family-system:inherit'"
                    />
         
                    <%-- Open QDS Icons --%>
                    <script type="module">
                          import { defineCustomElements } from 'https://d2osz8slymlqdp.cloudfront.net/@quartzds/core/@1.0.0-beta.42/loader/index.js';
                        defineCustomElements();
         
                        import { registerIconLibrary } from 'https://d2osz8slymlqdp.cloudfront.net/@quartzds/core/@1.0.0-beta.42/dist/esm/index.js';
                        registerIconLibrary('core', {
                            resolver: (name) =>
                                `https://d2osz8slymlqdp.cloudfront.net/@quartzds/se-icons-core/@1.0.0-beta.10/dist/${name}.svg`,
                        });
                        registerIconLibrary('default', {
                            resolver: (name) =>
                                `https://d2osz8slymlqdp.cloudfront.net/@quartzds/se-icons-general/@1.0.0-beta.13/dist/${name}.svg`,
                        });
                    </script>
                <%-- QDS OPEN v4 CDNs End--%>
<jsp:include page="pageView.jsp" />
<jsp:include page="pageViewJs.jsp" />

</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="modal-content">
		<c:forEach items="${documents}" var="document">
			<div class="doc-content">
				<img alt="" src="${document.imageUrl}">
				<div class="doc-details">
					<strong>${document.title}</strong>
					<div class="doc-ref">
						<span class='attr'>${localizedData.labels.prm_wpl_table_data_reference}:&nbsp;</span>
						<span class='attr-val'>${document.reference}</span>&nbsp;&nbsp;
					</div>
					<div class="doc-date">
						<span class='attr'>${localizedData.labels.prm_wpl_document_date}&nbsp;</span>
						<span class='attr-val'>${document.documentDate}</span>&nbsp;&nbsp;
					</div>
					<div class="doc-size">
						<span class='attr'>${localizedData.labels.prm_wpl_table_data_size}:&nbsp;</span>
						<span class='attr-val'>${document.size}</span>&nbsp;&nbsp;
					</div>
					<div class="doc-version">
						<span class='attr'>${localizedData.labels.prm_wpl_document_version}&nbsp;</span>
						<span class='attr-val'>${document.version}</span> &nbsp;&nbsp;
					</div>
					<div class="doc-lang">
						<span class='attr'>${localizedData.labels.prm_wpl_search_label_doc_languages}:&nbsp;</span>
						<span class='attr-val'>${document.localizedLanguageLabels}</span>
						&nbsp;&nbsp;
					</div>
			<%-- 		<div class="doc-btn">
						<a href="${document.downloadUrl}" target="_blank"
							onClick="callGADownload(`${document.downloadUrl}`)">${localizedData.labels.prm_wpl_document_download_label}</a>
					</div>
 --%>					
					 <qds-button importance="emphasized" text=${localizedData.labels.prm_wpl_document_download_label} href="${document.downloadUrl}" target="_blank" onClick="callGADownload(`${document.downloadUrl}`)" size= "large"></qds-button>
				</div>
			</div>
		</c:forEach>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>