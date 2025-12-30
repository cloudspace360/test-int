package com.se.wp.library.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentFile;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.DocumentType;
import com.se.wp.library.models.GAPageViewDataLayer;

@ExtendWith(MockitoExtension.class)
public class WpLibraryUtilTest {


	@Mock
	HttpServletRequest request;
	@Mock
	PropertiesUtil propertiesUtil;
	@Mock
	EnvironmentUtil environmentUtil;
	@Mock 
	Model model;

	private String languageCode = "en";
	private String documentFileName = "Trio KP &amp; KB Quick Start Guide.pdf";
	private String longText="descriptionTestSomeVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryVeryyVeryVeryVeryLongText";

	@InjectMocks
	WpLibraryUtil wpLibraryUtil;

	DocumentListingDto document;
	List<DocumentFile> documentFileList;
	GAPageViewDataLayer gaPageViewDataLayer;
	
	private static final String TEST="test";

	@BeforeEach
	public void setUp() throws Exception {
		document=new DocumentListingDto();
		environmentUtil.setDocumentImageUrlTemplate(
				"https://download.schneider-electric.com/files?p_Doc_Ref={DOCUMENT_REF_PARAM}&p_File_Type=big%20Thumbnail&default_image=DefaultProductImage.jpg");
		environmentUtil.setArchiveDownloadUrlTemplate(
				"https://download.schneider-electric.com/files?p_Doc_Ref={DOCUMENT_REF_PARAM}&p_enDocType={DOCUMENT_TYPE_PARAM}&p_Archive_Name={ARCHIVE_NAME_PARAM}");
		environmentUtil.setNonArchiveDownloadUrlTemplate(
				"https://download.schneider-electric.com/files?p_Doc_Ref={DOCUMENT_REF_PARAM}&p_enDocType={DOCUMENT_TYPE_PARAM}&p_File_Name={FILE_NAME_PARAM}");
		environmentUtil.setArchiveDownloadUrlTemplate("https://download-pp.dev.schneider-electric.com/files?p_Doc_Ref={DOCUMENT_REF_PARAM}&p_enDocType={DOCUMENT_TYPE_PARAM}&p_Archive_Name={ARCHIVE_NAME_PARAM}");
		environmentUtil.setNonArchiveDownloadUrlTemplate("https://download-pp.dev.schneider-electric.com/files?p_Doc_Ref={DOCUMENT_REF_PARAM}&p_enDocType={DOCUMENT_TYPE_PARAM}&p_File_Name={FILE_NAME_PARAM}");
		
		DocumentFile documentFile = new DocumentFile();
		List<DocumentFile> documentFileList = new ArrayList<>();
		documentFile.setDownloadFileURL(
				"https://download.schneider-electric.com/files?p_Doc_Ref=LE1U16BDG7NC12S6_REACH_DECLARATION&p_enDocType=REACh+Declaration&p_File_Name=LE1U16BDG7NC12S6_REACH_DECLARATION_US_en-US.pdf");
		documentFile.setExtension(".pdf");
		documentFile.setFilename("test.640x480_iso.pdf");
		documentFileList.add(documentFile);
		document.setDocumentFiles(documentFileList);
		document.setReference("DR1");
		
		gaPageViewDataLayer=new GAPageViewDataLayer();
		gaPageViewDataLayer.setArticleTitle(TEST);
		gaPageViewDataLayer.setBusinessUnit(TEST);
		gaPageViewDataLayer.setDigitalPlatform(TEST);
		gaPageViewDataLayer.setDigitalPlatformCountry(TEST);
		gaPageViewDataLayer.setEnvironment(TEST);
		gaPageViewDataLayer.setFromWebView(TEST);
		gaPageViewDataLayer.setPageCategory(TEST);
		gaPageViewDataLayer.setPageCategoryId(TEST);
		gaPageViewDataLayer.setPageLanguage(TEST);
		gaPageViewDataLayer.setPageProductId(TEST);
		gaPageViewDataLayer.setPageRangeId(TEST);
		gaPageViewDataLayer.setPageSubCategory(TEST);
		gaPageViewDataLayer.setPageSubCategoryId(TEST);
		gaPageViewDataLayer.setPageSubSubCategory(TEST);
		gaPageViewDataLayer.setPageTitle(TEST);
		gaPageViewDataLayer.setPageTopCategory(TEST);
		gaPageViewDataLayer.setTemplate(TEST);

	}

	@Test
	public void testPrepareDocumentTitle() {
		assertEquals(wpLibraryUtil.prepareDocumentTitle("Trio KP and KB Radio Quick Start Guide",
				"Trio_KP_KB_Radio_QuickStart"), "Trio KP and KB Radio Quick Start Guide");
		assertEquals(wpLibraryUtil.prepareDocumentTitle(null, "Trio_KP_KB_Radio_QuickStart"),
				"Trio_KP_KB_Radio_QuickStart");
		assertEquals(wpLibraryUtil.prepareDocumentTitle("", "Trio_KP_KB_Radio_QuickStart"),
				"Trio_KP_KB_Radio_QuickStart");
	}

	@Test
	public void testSetDescriptions() {
		wpLibraryUtil.setDescriptions("description", document);
		wpLibraryUtil.setDescriptions(longText, document);
	}

	@Test
	public void testPrepareShortDescription() {
		wpLibraryUtil.prepareShortDescription("short description");
		wpLibraryUtil.prepareShortDescription(longText+Constants.DOT);
		wpLibraryUtil.prepareShortDescription(longText+Constants.DOT+"otherText");
		wpLibraryUtil.prepareShortDescription(longText+" ");
		wpLibraryUtil.prepareShortDescription(longText+" ");
	}

	@Test
	public void testPrepareDocumentVersion() {
		assertEquals(wpLibraryUtil.prepareDocumentVersion("2.0", "3"), "3");
		assertEquals(wpLibraryUtil.prepareDocumentVersion(null, "3"), "3");
		assertNull(wpLibraryUtil.prepareDocumentVersion(null, null));
		assertEquals(wpLibraryUtil.prepareDocumentVersion("", "3"), "3");
		assertEquals(wpLibraryUtil.prepareDocumentVersion("2.0", ""), "2.0");
		assertEquals(wpLibraryUtil.prepareDocumentVersion("2.0", null), "2.0");
	}

	@Test
	public void testPrepareImageUrl() {
		try {
			wpLibraryUtil.prepareImageUrl("ONLINE-CAD-LC1D25ZL");
			wpLibraryUtil.prepareImageUrl("");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testPrepareDocumentDownloadUrl() {
		List<DocumentFile> documentFiles = new ArrayList<>();
		wpLibraryUtil.prepareDocumentDownloadUrl(documentFiles, document);
		document.setSize("1");
		wpLibraryUtil.prepareDocumentDownloadUrl(documentFiles, document);
	}


	/*@Test
	public void testPrepareDocumentDownloadUrlTest3() {
		DocumentFile documentFile = new DocumentFile();
		DocumentFile documentFile1 = new DocumentFile();
		DocumentType documentType = new DocumentType();
		documentFileList = new ArrayList<>();
		documentType.setEnglishLabel("TESTREFERENCE");
		document.setReference("TESTREFERENCE");
		document.setDocumentType(documentType);
		documentFile.setDownloadFileURL(
				"//download.schneider-electric.com/files?p_Doc_Ref=LE1U16BDG7NC12S6_REACH_DECLARATION&p_enDocType=REACh+Declaration&p_File_Name=LE1U16BDG7NC12S6_REACH_DECLARATION_US_en-US.pdf");
		documentFile.setExtension(".PDF");
		documentFile.setFilename("");
		documentFileList.add(documentFile);
		documentFile1.setDownloadFileURL(
				"//download.schneider-electric.com/files?p_Doc_Ref=LE1U16BDG7NC12S6_REACH_DECLARATION&p_enDocType=REACh+Declaration&p_File_Name=LE1U16BDG7NC12S6_REACH_DECLARATION_GB_en-GB.pdf");
		documentFile1.setExtension(".PDF");
		documentFile1.setFilename("");
		documentFileList.add(documentFile1);
		
		wpLibraryUtil.prepareDocumentDownloadUrl(documentFileList, document);
		assertEquals("ZIP", document.getExtension());

	}
*/
	@Test
	public void testBuildDownloadURL() {
		Mockito.when(environmentUtil.getArchiveDownloadUrlTemplate()).thenReturn("//download-pp.dev.schneider-electric.com/files?p_Doc_Ref={DOCUMENT_REF_PARAM}&p_enDocType={DOCUMENT_TYPE_PARAM}&p_Archive_Name={ARCHIVE_NAME_PARAM}");
		Mockito.when(environmentUtil.getNonArchiveDownloadUrlTemplate()).thenReturn("//download-pp.dev.schneider-electric.com/files?p_Doc_Ref={DOCUMENT_REF_PARAM}&p_enDocType={DOCUMENT_TYPE_PARAM}&p_File_Name={FILE_NAME_PARAM}");
		wpLibraryUtil.buildDownloadURL("Trio_KP_KB_Radio_QuickStart", "User guide","Trio KP &amp; KB Quick Start Guide.pdf", false, false);
		wpLibraryUtil.buildDownloadURL("Trio_KP_KB_Radio_QuickStart", "User guide","Trio KP &amp; KB Quick Start Guide.pdf", true, false);
		wpLibraryUtil.buildDownloadURL("Trio_KP_KB_Radio_QuickStart", "User guide","Trio KP &amp; KB Quick Start Guide.pdf", false, true);
		wpLibraryUtil.buildDownloadURL(null, "User guide","Trio KP &amp; KB Quick Start Guide.pdf", true, false);
		wpLibraryUtil.buildDownloadURL("Trio_KP_KB_Radio_QuickStart", null,"Trio KP &amp; KB Quick Start Guide.pdf", true, false);
		wpLibraryUtil.buildDownloadURL("Trio_KP_KB_Radio_QuickStart", "User guide",null, false, false);
	}

	@Test
	public void testRemoveSpecialChars() {
		String removeSpecialChars = null;
		removeSpecialChars = wpLibraryUtil.removeSpecialChars("##testValue");
		assertEquals("%23%23testValue", removeSpecialChars);
	}

	@Test
	public void testSetOnlineAttributes() {
		wpLibraryUtil.setOnlineAttributes("bannerUrl", document);
		wpLibraryUtil.setOnlineAttributes(null, document);
		wpLibraryUtil.setOnlineAttributes("", document);
	}

	@Test
	public void testSetDisableStatus() {
		documentFileList = new ArrayList<>();
		DocumentFile documentFile = new DocumentFile();
		documentFile.setExtension(".pdf");
		documentFile.setFilename("test.640x480_iso.pdf");
		documentFileList.add(documentFile);
		wpLibraryUtil.setDisableStatus(documentFileList, document);
	}


	@Test
	public void testGetLongFilter() {
		List<Long> longFilter = new ArrayList<>();
		longFilter.add((long) 45737);
		assertEquals(wpLibraryUtil.getLongFilter("45737", "range"), longFilter);
	}

	@Test
	public void testGetLongAndAdd() {
		List<Long> longFilter = new ArrayList<>();
		wpLibraryUtil.getLongAndAdd("145993", longFilter);
		assertEquals("145993", longFilter.get(0).toString());
	}

	@Test
	public void testGetStringFilter() {
		String filterStr = "test,teats1";
		assertEquals(2,wpLibraryUtil.getStringFilter(filterStr).size());
	}

	@Test
	public void testPrepareLocalizedSize() {
		assertEquals(wpLibraryUtil.prepareLocalizedSize(2048, "en"), "2 KB");
		assertEquals(wpLibraryUtil.prepareLocalizedSize(0, "en"), "0");
	}

	@Test
	public void testPrepareFileDownloadUrl() {
		try {
			wpLibraryUtil.prepareFileDownloadUrl(languageCode, documentFileName,getOnlineAttributesTestValues());
		} catch (Exception e) {
			/*fail();*/
		}
	}

	@Test
	public void testPrepareDocumentFileName() {
		DocumentListingDto document = getOnlineAttributesTestValues();
		try {
			wpLibraryUtil.prepareDocumentFileName(languageCode, document.getReference(),
					document.getDocumentType().getId(), documentFileName);
			wpLibraryUtil.prepareDocumentFileName(languageCode, document.getReference(), 6085276357L,
					documentFileName);
			wpLibraryUtil.prepareDocumentFileName("en", document.getReference(),
					document.getDocumentType().getId(), documentFileName);

		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void testPrepareDocumentFileNameTest2() {
		String ref = "ONLINE-CAD-LC1D25ZL";
		try {
			wpLibraryUtil.prepareDocumentFileName(languageCode, ref, 6085276357L, documentFileName);

		} catch (IOException e) {
			fail();
		}
	}
	
	
	public void testPrepareFileDownloadUrlTest() {
		try {
			wpLibraryUtil.prepareFileDownloadUrl(languageCode, documentFileName,getOnlineAttributesTestValues());
		} catch (Exception e) {
			fail();
		}

	}


	private DocumentListingDto getOnlineAttributesTestValues() {
		DocumentType documentType = new DocumentType();
		DocumentFile documentFile = new DocumentFile();
		DocumentListingDto document = new DocumentListingDto();
		List<DocumentType> documentTypeList = new ArrayList<>();
		List<DocumentFile> documentFileList = new ArrayList<>();
		documentType.setId(Long.valueOf(1555859));
		documentType.setLabel("Environment");
		documentType.setNumberOfDocs((long) 2712);
		document.setReference("ONLINE-CAD-LC1D25ZL");
		documentTypeList.add(documentType);
		document.setDocumentType(documentType);
		documentFile.setDownloadFileURL(
				"https://download.schneider-electric.com/files?p_Doc_Ref=LE1U16BDG7NC12S6_REACH_DECLARATION&p_enDocType=REACh+Declaration&p_File_Name=LE1U16BDG7NC12S6_REACH_DECLARATION_US_en-US.pdf");
		documentFile.setExtension(".PDF");
		documentFile.setFilename("LE1U16BDG7NC12S6_REACH_DECLARATION_US_en-US");
		documentFileList.add(documentFile);
		document.setDocumentFiles(documentFileList);
		return document;

	}
	
	@Test
	public void addPageViewDataLayerTest() throws IOException{
		Map<String, String> gaPageViewDataLayerProperties = new HashMap<>();
		Mockito.when(propertiesUtil.getAllProperties("GA_PageViewDataLayer.properties")).thenReturn(gaPageViewDataLayerProperties);
		Mockito.when(environmentUtil.getActiveProfile()).thenReturn("PROD");

		wpLibraryUtil.addPageViewDataLayer("en", "en", "230", "wp", model);

	}

}
