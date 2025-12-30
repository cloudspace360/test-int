package com.se.wp.library.soap.bsl.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.schneider.oreo.service.document.DocumentPageAttributeListBean;
import com.schneider.oreo.service.document.DocumentPageBean.Documents;
import com.schneider.oreo.service.document.DocumentPageDocumentBean;
import com.schneider.oreo.service.document.DocumentPageDocumentBean.AttributeLists;
import com.schneider.oreo.service.document.DocumentPageDocumentBean.Locales;
import com.schneider.oreo.service.document.DocumentPageDocumentBean.ProductReferences;
import com.schneider.oreo.service.document.DocumentTypeBean;
import com.schneider.oreo.service.document.DocumentTypeGroupBean;
import com.schneider.oreo.service.document.FileBean;
import com.schneider.oreo.service.document.LocaleBean;
import com.schneider.oreo.service.document.TranslatedBean;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentFile;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.DocumentType;
import com.se.wp.library.utils.PropertiesUtil;
import com.se.wp.library.utils.WpLibraryUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BslDocumentUtilTest  {
	private String countryCode = "US";
	private String languageCode = "en";

	@InjectMocks
	BslDocumentUtil bslDocumentDetailsUtil;
	@Mock
	WpLibraryUtil wpLibraryUtil;
	@Mock
	PropertiesUtil propertiesUtil;

	Documents documents;
	DocumentPageDocumentBean documentPageDocumentBean;
	DocumentPageDocumentBean.Files files = new DocumentPageDocumentBean.Files();
	DocumentListingDto documentDto;
	FileBean fileBean = new FileBean();
	GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
	XMLGregorianCalendar xgcal;
	DocumentType documentType = new DocumentType();
	LocaleBean localeBean = new LocaleBean();
	Locales locales = new Locales();
   ProductReferences productReferences=new ProductReferences();
	DocumentTypeBean documentTypeBean = new DocumentTypeBean();
	List<DocumentTypeGroupBean> docTypeGroup=new ArrayList<>();
	DocumentTypeGroupBean documentTypeGroupBean=new DocumentTypeGroupBean();
	
	@BeforeEach
	public void setUp() throws Exception {
		xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		documents = new Documents();
		documentDto = new DocumentListingDto();
		
		documentPageDocumentBean = new DocumentPageDocumentBean();
		documentPageDocumentBean.setBannerUrl("TestBannerURL");
		documentPageDocumentBean.setReference("testReference");
		documentPageDocumentBean.setDocOid(1000L);
		documentPageDocumentBean.setTitle("TestTitle");
		documentPageDocumentBean.setVersion("2.0");
		documentPageDocumentBean.setRevision("3.0");
		localeBean.setIsoCountry(countryCode);
		localeBean.setIsoLanguage(languageCode);
		locales.getLocale().add(localeBean);
		documentPageDocumentBean.setLocales(locales);
		fileBean.setExtension(".PDF");
		fileBean.setFilename("TESTFILE");
		fileBean.setId("5000");
		fileBean.setSize(1024L);
		files.getFile().add(fileBean);
		documentPageDocumentBean.setFiles(files);
		documentPageDocumentBean.setProductReferences(productReferences);
		documentPageDocumentBean.setNumberOfPage(10);
		documentPageDocumentBean.setReference("Ref");
		documents.getDocument().add(documentPageDocumentBean);
		documentDto.setReference("TESTREFEREENCE");
		documentDto.setBannerUrl("TestBannerURL");
		documentDto.setDescription("TestDescription");
		documentDto.setTitle("TestTitle");
		documentDto.setVersion("2.0");
		documentType.setEnglishLabel("User guide");
		documentType.setId(10608902270L);
		documentType.setName("User guide");
		documentType.setNumberOfDocs(100L);
		documentDto.setDocumentType(documentType);
		documentTypeBean.setEnglishLabel("End of life manual");
		documentTypeBean.setId(1555858L);
		documentTypeBean.setTranslation("End of Life Information");
		documentTypeBean.setName("End of life manual");

	}

	@Test
	public void testPrepareDocumentsTest1() {
		try {
			bslDocumentDetailsUtil.prepareDocuments(languageCode, documents);
		} catch (Exception e) {
//			fail();
		}
	}

	@Test
	public void testPrepareDocumentsTest2() {
		try {
			Documents documentsempty = null;
			assertEquals(true,bslDocumentDetailsUtil.prepareDocuments(languageCode, documentsempty).isEmpty());
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testPrepareDocumentTest1() {
		try {
			DocumentListingDto document = bslDocumentDetailsUtil.prepareDocument(languageCode,documentPageDocumentBean);
			assertEquals(1000L, document.getDocumentId());
		} catch (Exception e) {
//			fail();
		}
	}
	
	@Test
	public void testPrepareDocumentTest2() {
		try {
			documentPageDocumentBean=null;
			DocumentListingDto document = bslDocumentDetailsUtil.prepareDocument(languageCode,documentPageDocumentBean);
			assertNull(document);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testPopulateDocumentFilesTest() {
		try {
			List<DocumentFile> documentFiles;
			documentFiles = bslDocumentDetailsUtil.populateDocumentFiles(languageCode,
					documentPageDocumentBean.getFiles().getFile(), documentDto);
			assertEquals(".PDF", documentFiles.get(0).getExtension());
		} catch (Exception e) {
			fail();
		}

	}

	@Test
	public void testSetDocumentDateTest() {
		try {
			Mockito.when(propertiesUtil.getProperty(languageCode, Constants.GLOBAL_CONFIG_FILE_NAME,
					"prm-results-list-date-format")).thenReturn(Constants.DEFAULT_DATE_FORMAT);
			bslDocumentDetailsUtil.setDocumentDate(xgcal, documentDto);
			bslDocumentDetailsUtil.setDocumentDate(null, documentDto);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSetExpireDateTest() {
		try {
			Mockito.when(propertiesUtil.getProperty(languageCode, Constants.GLOBAL_CONFIG_FILE_NAME,
					"prm-results-list-date-format")).thenReturn(Constants.DEFAULT_DATE_FORMAT);
			bslDocumentDetailsUtil.setExpireDate(xgcal, documentDto);
			bslDocumentDetailsUtil.setExpireDate(null, documentDto);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSetPublicationDateTest() {
		try {
			Mockito.when(propertiesUtil.getProperty(languageCode, Constants.LABELS_FILE_NAME,
					"prm-results-list-date-format")).thenReturn(Constants.DEFAULT_DATE_FORMAT);
			bslDocumentDetailsUtil.setPublicationDate(xgcal, documentDto);
			bslDocumentDetailsUtil.setPublicationDate(null, documentDto);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSetLastModificationDateTest() {
		try {
			Mockito.when(propertiesUtil.getProperty(languageCode, Constants.LABELS_FILE_NAME,
					"prm-results-list-date-format")).thenReturn(Constants.DEFAULT_DATE_FORMAT);
			bslDocumentDetailsUtil.setLastModificationDate(xgcal, documentDto);
			bslDocumentDetailsUtil.setLastModificationDate(null, documentDto);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testSetCreationDateTest() {
		try {
			Mockito.when(propertiesUtil.getProperty(languageCode, Constants.LABELS_FILE_NAME,
					"prm-results-list-date-format")).thenReturn(Constants.DEFAULT_DATE_FORMAT);
			bslDocumentDetailsUtil.setCreationDate(countryCode, languageCode, xgcal, documentDto);
			bslDocumentDetailsUtil.setCreationDate(countryCode, languageCode, null, documentDto);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAdjustTimeZoneTest() {
		try {
			bslDocumentDetailsUtil.adjustTimeZone(xgcal);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetFormattedDateTest() {
		try {
			Mockito.when(propertiesUtil.getProperty(languageCode, Constants.LABELS_FILE_NAME,"prm-results-list-date-format")).thenReturn("dd/MM/yyyy");
			bslDocumentDetailsUtil.getFormattedDate(new Date());
			assertNull(bslDocumentDetailsUtil.getFormattedDate(null));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetDateObjectTest() {
		try {
			bslDocumentDetailsUtil.getDateObject(xgcal);
		} catch (Exception e) {
			fail();
		}
	}


	@Test
	public void testGetCountryFormattedDateStringTest() {
		try {
			Mockito.when(propertiesUtil.getProperty(languageCode, Constants.LABELS_FILE_NAME,
					"prm-results-list-date-format")).thenReturn(Constants.DEFAULT_DATE_FORMAT);
			bslDocumentDetailsUtil.getCountryFormattedDateString(new Date());
		} catch (IOException e) {
			fail();
		}
	}


	@Test
	public void testSetNumberOfPageTest() {
		bslDocumentDetailsUtil.setNumberOfPage(500, documentDto);
		assertEquals(500, documentDto.getNumberOfPage());
	}

	@Test
	public void testsetDocumentTypesTest() {
		try{
		DocumentListingDto documentDtoDocType = new DocumentListingDto();
		DocumentTypeBean documentTypeEmptyBean = null;
		bslDocumentDetailsUtil.setDocumentTypes(documentTypeEmptyBean, documentDtoDocType);
		bslDocumentDetailsUtil.setDocumentTypes(documentTypeBean, documentDtoDocType);
		assertEquals("1555858-End of Life Information", documentDtoDocType.getDocumentTypeDisplayLabel());
		}
		catch(Exception e)
		{
			fail();
		}

	}
	
	@Test
	public void testGetDocumentTypeFromDocumentTypeBeanTest()
	{
		DocumentType docType=bslDocumentDetailsUtil.getDocumentTypeFromDocumentTypeBean(documentTypeBean);
		assertEquals("End of life manual", docType.getEnglishLabel());
	}
	
	@Test
	public void testSetDocTypeGroupsTest()
	{   
		DocumentListingDto documentDtoDocTypeGroup = new DocumentListingDto();
		List<DocumentTypeGroupBean> docTypeGroupList = new ArrayList<>();
		DocumentTypeGroupBean documentTypeGroupBean = new DocumentTypeGroupBean();
		documentTypeGroupBean.setId(500L);
		documentTypeGroupBean.setTranslation("DocumentTypeGroupBean Translation");
		docTypeGroupList.add(documentTypeGroupBean);
		bslDocumentDetailsUtil.setDocTypeGroups(docTypeGroupList, documentDtoDocTypeGroup);
		assertEquals("DocumentTypeGroupBean Translation", documentDtoDocTypeGroup.getDocTypeGroups().get(0).getLabel());
	}
	
	
	@Test
	public void testGetAttributeListValuesTest1()
	{
		TranslatedBean translatedBean=new TranslatedBean();
		translatedBean.setHighlighted("HighlightedValue");
		translatedBean.setValue("Operating_System");
		DocumentPageAttributeListBean attributeListBean = new DocumentPageAttributeListBean();
		AttributeLists attributeLists = new AttributeLists();
		attributeListBean.setId("TestAttributeId");
		attributeListBean.setName("Operating_System");
		attributeListBean.getValues().add(translatedBean);
		attributeLists.getAttributeList().add(attributeListBean);
		assertEquals(1,bslDocumentDetailsUtil.getAttributeListValues(attributeLists,"Operating_System").size());
	}
	
	@Test
	public void testGetAttributeListValuesTest2()
	{
		TranslatedBean translatedBean=new TranslatedBean();
		translatedBean.setHighlighted("HighlightedValue");
		translatedBean.setValue("Operating_System");
		DocumentPageAttributeListBean attributeListBean = new DocumentPageAttributeListBean();
		attributeListBean.setId("TestAttributeId");
		attributeListBean.setName("Operating_System");
		attributeListBean.getValues().add(translatedBean);
		assertEquals(1,bslDocumentDetailsUtil.getAttributeListValues(attributeListBean).size());
	}
	
}
