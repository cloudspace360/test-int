package com.se.wp.library.soap.bsl.rest.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.schneider.rest.document.service.responses.BaseName;
import com.schneider.rest.document.service.responses.Count;
import com.schneider.rest.document.service.responses.Document;
import com.schneider.rest.document.service.responses.DocumentCount;
import com.schneider.rest.document.service.responses.DocumentPageResponse;
import com.schneider.rest.document.service.responses.Documents;
import com.schneider.rest.document.service.responses.Name;
import com.schneider.rest.document.service.responses.Return;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentListingResponseDto;
import com.se.wp.library.models.rest.DataToDisplay;
import com.se.wp.library.models.rest.DocumentFilters;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.rest.DocumentType;
import com.se.wp.library.models.rest.DocumentTypeGroup;
import com.se.wp.library.soap.bsl.util.BslContants;
import com.se.wp.library.utils.EnvironmentUtil;

@Component
public class BslDocumentServiceResponseRestUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslDocumentServiceResponseRestUtil.class);
	@Autowired
	BslDocumentUtilRest bslDocumentUtilRest;


	public DocumentListingResponseDto getDocuemntListingResponse(String languageCode,
			ResponseEntity<DocumentPageResponse> responseEntity ) throws IOException {
		DocumentListingResponseDto docuemntListingResponse = null;
		if (responseEntity != null) {
			LOGGER.debug("responseEntity received...)");
			docuemntListingResponse = new DocumentListingResponseDto();
			MediaType contentType = responseEntity.getHeaders().getContentType();
			LOGGER.debug("contentType: {}", contentType);
			HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
			LOGGER.debug("statusCode: {}", statusCode);
			DocumentPageResponse documentPageResponse = responseEntity.getBody();
			LOGGER.debug("documentPageResponse: {}", documentPageResponse);
			if (documentPageResponse != null && documentPageResponse.getGetDocumentPageResponse() != null
					&& documentPageResponse.getGetDocumentPageResponse().getReturn() != null) {
				Return documentPageReturn = documentPageResponse.getGetDocumentPageResponse().getReturn();
				if (documentPageReturn != null && documentPageReturn.getDocumentCounts() != null) {
					//setDocumentsCount(documentPageReturn.getDocumentCounts(), docuemntListingResponse);
					LOGGER.debug("documentCountsList.size(): {}", documentPageReturn.getDocumentCounts());
					setFilters(documentPageReturn.getDocumentCounts().getDocumentCount(), docuemntListingResponse);
				}
				else {
					LOGGER.debug("No documentCounts recieved, setting ZERO document count...");
					docuemntListingResponse.setDocumentsCount(0);
				}
				
				if (documentPageReturn != null && documentPageReturn.getDocuments() != null) {
					setDocuments(null, languageCode, documentPageReturn.getDocuments(), docuemntListingResponse);
				}
			}
		}
		return docuemntListingResponse;
	}
	private void setFilters(List<DocumentCount> documentCounts, DocumentListingResponseDto docuemntListingResponse) {
		LOGGER.debug("documentCounts: {}", documentCounts);
		List<Count> countList = null;
		DocumentFilters documentFilters = new DocumentFilters();
		String countByFiled = null;
		String countByParam =null;
		if (documentCounts != null) {
			for (DocumentCount documentCount : documentCounts) {
				if (documentCount != null && documentCount.getField() != null) {
					countList = documentCount.getCount();
					if (countList != null && !countList.isEmpty()) {
						countByFiled = documentCount.getField();
						countByParam = documentCount.getParam();
						LOGGER.debug("countByFiled--> {}", countByFiled);
						setTotalCount(countByFiled, countList, docuemntListingResponse);
						setDocumentTypeGroupsAndDocumentTypes(countByFiled, countList, documentFilters);
										}
				}
			}
		}
	}

	protected void setTotalCount(String countByFiled, List<Count> countList,
			DocumentListingResponseDto docuemntListingResponse) {
		if (countByFiled != null && BslContants.COUNT_BY_ALL.equals(countByFiled)) {
			long totalDocCount = countList.get(0).getNumberOfDocs();
			docuemntListingResponse.setDocumentsCount((int) totalDocCount);
		}
	}
		protected void setDocumentTypeGroupsAndDocumentTypes(String countByFiled, List<Count> countList,
				DocumentFilters documentFilters) {
			if (countByFiled != null) {
				switch (countByFiled) {
				case BslContants.COUNT_BY_DOC_TYPE_GROUP:
					documentFilters.setDocumentTypeGroups(prepareFilters(countList, DocumentTypeGroup.class));
					break;
				case BslContants.COUNT_BY_DOC_TYPE:
					documentFilters.setDocumentTypes(prepareFilters(countList, DocumentType.class));
					break;
				default:
					break;
				}
			}
		}

		public <T extends DataToDisplay> List<T> prepareFilters(List<Count> countList, Class<T> type) {
			String filterType = type.getName().substring(type.getName().lastIndexOf(".") + 1);
			LOGGER.debug("Preparing {} filters...", filterType);
			List<T> filterList = new ArrayList<>();
			DataToDisplay filter;
			Long filterId = 0L;
			BaseName baseNameTranslationBean;
			for (Count documentPageCountBean : countList) {
				try {
					filter = type.newInstance();
					filterId = documentPageCountBean.getOid();
					if (filterId != null) {
						filter.setId(filterId);
					} else if (BslContants.FILTERS_WITHOUT_ID.contains(type.getTypeName())) {
						LOGGER.debug("valid filter without id...");
						filterId = 0L;
					} else {
						LOGGER.info("filterType {} found without id", filterType);
						continue;
					}
					baseNameTranslationBean = documentPageCountBean.getBaseName();
					setDefaultLabel(baseNameTranslationBean, filter);
					setLabel(documentPageCountBean,
							new StringBuilder(filterType).append(Constants.HYPHEN).append(filterId).toString(), filter);

					filter.setNumberOfDocs(documentPageCountBean.getNumberOfDocs());
					filterList.add(type.cast(filter));
				} catch (InstantiationException | IllegalAccessException e) {
					LOGGER.error("Error while preparing filter label for {} with id {}", filterType, filterId, e);
				}
			}
			LOGGER.debug("{} filters: {}", filterType, filterList);
			return filterList;
		}
		private void setDefaultLabel(BaseName baseNameTranslationBean, DataToDisplay filter) {
			if(baseNameTranslationBean!=null && baseNameTranslationBean.getValue()!=null) {
				filter.setBaseName(baseNameTranslationBean.getValue());
			}
		}
		protected void setLabel(Count documentPageCountBean, String defaultLabel, DataToDisplay filter) {
			String invalidFilterLabels = EnvironmentUtil.getInvalidFilterLabels();
			filter.setLabel(defaultLabel);
			String label;
			if (documentPageCountBean != null) {
				label = prepareLabel(documentPageCountBean.getName());
				if (label == null || label.isEmpty()) {
					label = prepareLabelByBaseName(documentPageCountBean.getBaseName());
				}
				if (label != null && !label.trim().isEmpty() && !invalidFilterLabels.contains(label)) {
					filter.setLabel(label);
				}
			}
		}
		private String prepareLabel(Name translatedBean) {
			String label = null;
			if (translatedBean != null)
				label = translatedBean.getValue();
			return label;
		}
		private String prepareLabelByBaseName(BaseName translatedBean) {
			String label = null;
			if (translatedBean != null)
				label = translatedBean.getValue();
			return label;
		}
		protected void setDocuments(String countryCode, String languageCode, Documents documents,
				DocumentListingResponseDto docuemntListingResponse) throws IOException {
			List<Document> documentList = documents.getDocument();
			if (documentList != null) {
				LOGGER.debug("documentList size: {}", documentList.size());
				List<DocumentListingDto> docs = bslDocumentUtilRest.prepareDocuments(countryCode,languageCode,
						documentList);
				if (docs != null) {
					LOGGER.debug("docs.size(): {}", docs.size());
					docuemntListingResponse.setDocuments(docs);
				}
			}
		}
}