package com.se.wp.library.soap.bsl.util;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schneider.oreo.service.document.DocumentPageBean;
import com.schneider.oreo.service.document.DocumentPageBean.DocumentCounts;
import com.schneider.oreo.service.document.DocumentPageCountBean;
import com.schneider.oreo.service.document.DocumentPageCountResult;
import com.se.wp.library.models.DocumentListingResponseDto;

@Component
public class BslDocumentServiceResponseUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslDocumentServiceResponseUtil.class);
	@Autowired
	BslDocumentUtil documentUtil;

	public DocumentListingResponseDto getDocumentListingResponse(String language,DocumentPageBean documentPageBean) throws IOException {
		DocumentListingResponseDto data = null;
		if (documentPageBean != null) {
			DocumentCounts documentCounts = documentPageBean.getDocumentCounts();
			LOGGER.debug("documentCounts: {}", documentCounts);
			data = new DocumentListingResponseDto();
			if (documentCounts != null) {
				List<DocumentPageCountResult> documentCountsList = documentCounts.getDocumentCount();
				if (documentCountsList != null && !documentCountsList.isEmpty()) {
					LOGGER.debug("documentCountsList.size(): {}", documentCountsList.size());
					List<DocumentPageCountBean> countList;
					String countByFiled;
					String countByParam;
					for (DocumentPageCountResult documentCount : documentCountsList) {
						if (documentCount != null) {
							countList = documentCount.getCount();
							LOGGER.debug("countList: {}", countList);
							if (countList != null && !countList.isEmpty()) {
								countByFiled = documentCount.getField().toString();
								countByParam = documentCount.getParam();
								LOGGER.debug("countByFiled--> countByParam: {}-->{}", countByFiled, countByParam);
								setTotalCount(countByFiled, countList, data);
							}
						}
					}
				}
			} else {
				LOGGER.debug("No documentCounts recieved, setting ZERO document count...");
				data.setDocumentsCount(0);
			}
			data.setDocuments(documentUtil.prepareDocuments(language, documentPageBean.getDocuments()));
		}
		return data;
	}

	protected void setTotalCount(String countByFiled, List<DocumentPageCountBean> countList,
			DocumentListingResponseDto data) {
		if (countByFiled != null && BslContants.COUNT_BY_FIELD_ALL.equals(countByFiled)) {
			long totalDocCount = countList.get(0).getNumberOfDocs();
			data.setDocumentsCount((int) totalDocCount);
		}
	}

}
