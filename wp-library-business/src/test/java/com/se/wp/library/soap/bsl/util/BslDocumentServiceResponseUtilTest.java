package com.se.wp.library.soap.bsl.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.schneider.oreo.service.document.DocCountByFieldBean;
import com.schneider.oreo.service.document.DocumentPageBean;
import com.schneider.oreo.service.document.DocumentPageBean.DocumentCounts;
import com.schneider.oreo.service.document.DocumentPageBean.Documents;
import com.schneider.oreo.service.document.DocumentPageCountBean;
import com.schneider.oreo.service.document.DocumentPageCountResult;
import com.schneider.oreo.service.document.DocumentPageDocumentBean;
import com.schneider.oreo.service.document.TranslatedBean;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.DocumentListingResponseDto;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBootConfiguration
@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = { "invalid.filter.labels=-,", })
public class BslDocumentServiceResponseUtilTest  {
	private String countryCode = "US";
	private String languageCode = "en";
	DocumentPageBean documentPageBean;
	List<DocumentPageCountResult> documentPageCountResultList;
	DocumentPageCountResult documentPageCountResult;
	List<DocumentPageCountBean> documentPageCountBeanList;
	DocumentPageCountBean documentPageCountBean;
	DocCountByFieldBean field;
	DocumentCounts documentCounts;
	List<DocumentListingDto> data;
	Documents documents;
	DocumentListingDto dto;
	DocumentPageDocumentBean documentPageDocumentBean;
	TranslatedBean translatedBeanname;

	@InjectMocks
	BslDocumentServiceResponseUtil bslDocumentServiceResponseUtil;
	@Mock
	BslDocumentUtil documentUtil;

	@Value("${invalid.filter.labels}")
	String invalidFilterLabels;

	@BeforeEach
	public void setUp() throws Exception {
		translatedBeanname = new TranslatedBean();
		documentPageCountResultList = new ArrayList();
		documentPageDocumentBean = new DocumentPageDocumentBean();
		documentPageCountBeanList = new ArrayList();
		dto = new DocumentListingDto();
		data = new ArrayList<>();
		documents = new Documents();
		documentPageBean = new DocumentPageBean();
		documentCounts = new DocumentCounts();
		field = DocCountByFieldBean.ALL;
		documentPageCountBean = new DocumentPageCountBean();
		documentPageCountBean.setNumberOfDocs(104L);
		documentPageCountBean.setId("63732");
		translatedBeanname.setValue("translatedBeanvalue");
		translatedBeanname.setHighlighted("testHighlightedvalue");
		translatedBeanname.setValue("translatedBeanvalue");
		documentPageCountBean.setName(translatedBeanname);
		documentPageCountBean.setStatus("LEGACY");
		documentPageCountBean.setOid(1000909L);
		documentPageCountResult = new DocumentPageCountResult();
		documentPageCountResult.getCount().add(documentPageCountBean);
		documentPageCountResult.setField(field);
		documentCounts.getDocumentCount().add(documentPageCountResult);
		documentPageBean.setDocumentCounts(documentCounts);
		dto.setDocumentId(10000);
		dto.setReference("TestReference");
		data.add(dto);
		documentPageDocumentBean.setBannerUrl("TestBannerURL");
		documentPageDocumentBean.setReference("testReference");
		documentPageDocumentBean.setDocOid(1000L);
		
		documents.getDocument().add(documentPageDocumentBean);
		documentPageBean.setDocuments(documents);
		documentPageCountResultList.add(documentPageCountResult);
		documentPageCountBeanList.add(documentPageCountBean);

	}

	@Test
	public void testGetDocumentListingResponseTest1() {
		try {
			Mockito.when(documentUtil.prepareDocuments(languageCode, documentPageBean.getDocuments()))
					.thenReturn(data);
			bslDocumentServiceResponseUtil.getDocumentListingResponse(languageCode, documentPageBean);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetDocuemntListingResponseTest2() {
		try {
			bslDocumentServiceResponseUtil.getDocumentListingResponse(languageCode, documentPageBean);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetDocuemntListingResponseTest3() {
		try {
			DocumentPageBean documentPageBean = new DocumentPageBean();
			documentPageBean.setDocumentCounts(null);
			Mockito.when(documentUtil.prepareDocuments(languageCode, documentPageBean.getDocuments()))
					.thenReturn(data);
			bslDocumentServiceResponseUtil.getDocumentListingResponse(languageCode, documentPageBean);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSetFiltersTest() {
		try {

			DocumentListingResponseDto dto = new DocumentListingResponseDto();
			DocumentPageCountResult documentPageCountResult= new DocumentPageCountResult();
			DocumentPageCountBean documentPageCountBean = new DocumentPageCountBean();
			documentPageCountBean.setOid(1l);
			TranslatedBean translatedBean = new TranslatedBean();
			translatedBean.setHighlighted("View");
			translatedBean.setLocale("en");
			translatedBean.setValue("topView");
			documentPageCountBean.setBaseName(translatedBean);
			documentPageCountResult.getCount().add(documentPageCountBean);
			DocCountByFieldBean docCountByFieldBean= DocCountByFieldBean.ATTRIBUTE_LIST;
			documentPageCountResult.setField(docCountByFieldBean);
			documentPageCountResult.setParam("View");
			documentPageCountResultList.add(documentPageCountResult);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSetTotalCountTest() {
		try {
			DocumentListingResponseDto dto = new DocumentListingResponseDto();
			dto.setDocumentsCount(1000L);
			documentPageCountBeanList.get(0).getNumberOfDocs();
			bslDocumentServiceResponseUtil.setTotalCount("ALL", documentPageCountBeanList, dto);
			assertEquals(104, dto.getDocumentsCount());
		} catch (Exception e) {
			fail();
		}
	}

}
