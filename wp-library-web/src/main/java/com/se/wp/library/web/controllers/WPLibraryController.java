package com.se.wp.library.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.DocumentListingResponseDto;
import com.se.wp.library.models.LocalizedData;
import com.se.wp.library.service.impl.LocalizationService;
import com.se.wp.library.service.interfaces.IDocumentListingService;
import com.se.wp.library.utils.PropertiesUtil;
import com.se.wp.library.utils.WpLibraryUtil;

@Controller
@Primary
public class WPLibraryController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WPLibraryController.class);
	@Autowired
	LocalizationService localizationService;
	@Autowired
	WpLibraryUtil wpLibraryUtil;
	@Autowired
	PropertiesUtil propertiesUtil;
	@Autowired
	IDocumentListingService documentListingService;


	@RequestMapping(value = "{docType}/{refNum}/", method = { RequestMethod.GET })
	public String getWhitePaperDownloadLinks(@PathVariable String docType,@PathVariable String refNum,String localCode,HttpServletRequest request, Model model) throws IOException {
		LOGGER.debug("ENTER: WPLibraryController.getWhitePaperDownloadLinks()");
		String view = Constants.INDEX_VIEW;
		String reqLanguage = localCode;
		if(null==localCode || localCode.isEmpty()) {
			reqLanguage = wpLibraryUtil.getLanguage(request);
		}
		LOGGER.debug("reqLanguage: {}", reqLanguage);
		model.addAttribute("localizedData", localizationService.getLocalizedLabels(reqLanguage));
		model.addAttribute("metaConfigData", wpLibraryUtil.getMetaSpecificationData());
		String reqDocTypeId = propertiesUtil.getProperty("document-types.properties", docType.toLowerCase());
		if (reqDocTypeId == null || reqDocTypeId.isEmpty() ) {
			view = Constants.WARNING_VIEW;
		} else {
			LOGGER.debug("reqDocTypeName: {}", docType);
			LOGGER.debug("reqDocTypeId: {}", reqDocTypeId);
			LOGGER.debug("reqRefNum: {}", refNum);
			Map<String, String> filters = new TreeMap<>();
			filters.put(Constants.DOC_TYPE, reqDocTypeId);
			filters.put(Constants.REF_NUM, refNum);
			DocumentListingResponseDto documentListingResponseDto = documentListingService.getDocuments(reqLanguage,filters);
			if (documentListingResponseDto != null) {
				List<DocumentListingDto> documents = documentListingResponseDto.getDocuments();
				if (documents != null && !documents.isEmpty()) {
					String documentLanguageCodes;
					for (DocumentListingDto document 	: documents) {
						documentLanguageCodes = document.getLanguageCodes();
						if (documentLanguageCodes.contains(reqLanguage.toLowerCase()) || documentLanguageCodes.contains(Constants.SILENT_LANG_CODE)) {
							LOGGER.debug("Document available...");
							return "redirect:http:" + document.getDownloadUrl();
						}
					}
					LOGGER.debug("Other type/language documents available...");
					model.addAttribute("documents", documents);
				} else {
					view = Constants.WARNING_VIEW;
				}
			} else {
				view = Constants.WARNING_VIEW;
			}

		}
		LocalizedData localizedData = localizationService.getLocalizedLabels(reqLanguage);
		LOGGER.debug(localizedData.getLabels().toString());
		Locale locale = wpLibraryUtil.getLocale();
		wpLibraryUtil.addPageViewDataLayer(locale.getCountry(), reqLanguage,refNum,docType, model);
		wpLibraryUtil.addOneTrustConfig(locale.getLanguage(), model);
		LOGGER.debug("EXIT: WPLibraryController.getWhitePaperDownloadLinks()");
		return view;
	}

	@RequestMapping(value = "{docType}/{refNum}/{localCode}/", method = { RequestMethod.GET })
	public String getWhitePaperDownloadLinksWithLocale(@PathVariable String docType,@PathVariable String refNum,@PathVariable String localCode,HttpServletRequest request, Model model) throws IOException {
		return getWhitePaperDownloadLinks(docType.toLowerCase(), refNum, localCode, request, model);
	}

	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest request, Exception e,Model model) {
		LOGGER.error("Excepion occured {}",e.getMessage());
		model.addAttribute("localizedData", localizationService.getLocalizedLabels(wpLibraryUtil.getLanguage(request)));
		model.addAttribute("metaConfigData", wpLibraryUtil.getMetaSpecificationData());
		return Constants.WARNING_VIEW;
	}

}