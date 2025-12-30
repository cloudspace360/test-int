<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<div class="footer">
	<div class="container">
		<div class="row">
		<div class="col-md-6 col-sm-12">
		<qds-inline-link
                importance="subdued"
                size="standard"
                href="${fn:split(localizedData.footerLabels.prm_dlw_footer_legal_info, ';')[1]}"
					class="footer-link" target="_blank">
                <span class= "footer-text" > ${fn:split(localizedData.footerLabels.prm_dlw_footer_legal_info, ';')[0]}
                </span>                        
    </qds-inline-link>
<qds-inline-link
                importance="subdued"
                size="standard"
                href="${fn:split(localizedData.footerLabels.prm_dlw_footer_privacy_policy, ';')[1]}"
					class="footer-link" target="_blank">
                <span class= "footer-text" >${fn:split(localizedData.footerLabels.prm_dlw_footer_privacy_policy, ';')[0]}  
               </span>   
    </qds-inline-link>
    </div>
			<%-- <div class="col-md-6 col-sm-12">
				<a href="${fn:split(localizedData.footerLabels.prm_dlw_footer_legal_info, ';')[1]}"
					class="footer-link" target="_blank">${fn:split(localizedData.footerLabels.prm_dlw_footer_legal_info, ';')[0]}</a>
				<a href="${fn:split(localizedData.footerLabels.prm_dlw_footer_privacy_policy, ';')[1]}"
					class="footer-link" target="_blank">${fn:split(localizedData.footerLabels.prm_dlw_footer_privacy_policy, ';')[0]}</a>
			</div>
 --%>		<%-- 	<div class="col-md-6 col-sm-12 custom-call" >
				<a href="${fn:split(localizedData.footerLabels.prm_dlw_footer_copyright, ';')[1]}"
					target="_blank">${fn:split(localizedData.footerLabels.prm_dlw_footer_copyright, ';')[0]}</a>
			</div> --%>
			<div class="col-md-6 col-sm-12 custom-call" >
			<qds-inline-link  
			 importance="subdued"
              size="standard" 
              href="${fn:split(localizedData.footerLabels.prm_dlw_footer_copyright, ';')[1]}" target="_blank" >
        <span class= "footer-text" >${fn:split(localizedData.footerLabels.prm_dlw_footer_copyright, ';')[0]}
    </span>
    </qds-inline-link>
    </div>
		</div>
	</div>
</div>