package com.se.wp.library.models;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DocumentListingDtoTest{
	List<DocumentTypeGroup> docTypeGroups = new ArrayList<>();
	List<String> ranges = new ArrayList<>();
	List<String> documentScopes = new ArrayList<>();
	Set<String> alternateUrls = new HashSet<>();
	List<DocumentFile> documentFiles = new ArrayList<>();
	List<String> productReferences = new ArrayList<>();
	DocumentType documentType;

	@InjectMocks
	DocumentListingDto documentListingDto;
	@Mock
	DocumentTypeGroup documentTypeGroup;
	@Mock
	Range range;
	@Mock
	DocumentFile documentFile;

	@BeforeEach
	public void setUp() throws Exception {
		documentTypeGroup = new DocumentTypeGroup();
		documentFile = new DocumentFile();
		documentTypeGroup.setId(1000L);
		documentTypeGroup.setLabel("TEST");
		documentTypeGroup.setNumberOfDocs(100L);
		docTypeGroups.add(documentTypeGroup);
		ranges.add("Test1");
		documentScopes.add("TEST1");
		documentScopes.add("TEST2");
		alternateUrls.add("alternateUrl1");
		alternateUrls.add("alternateUrl2");
		documentFile.setExtension(".pdf");
		documentFile.setSize("1024");
		documentFile.setId("4000");
		documentFile.setFilename("TestDocument");
		documentFiles.add(documentFile);
		productReferences.add("TestProduct1");
		productReferences.add("TestProduct2");
		documentType=new DocumentType();
	}

	@Test
	public void test() {

		documentListingDto.setReference("TEST");
		documentListingDto.setEncodedDocReference("TEST");
		documentListingDto.setDocumentId(1000L);
		documentListingDto.setDownloadUrl("TEST");
		documentListingDto.setTitle("TEST");
		documentListingDto.setDescription("TEST");
		documentListingDto.setSize("TEST");
		documentListingDto.setNumberOfDoc(1000);
		documentListingDto.setVersion("TEST");
		documentListingDto.setLegacy(true);
		documentListingDto.setBannerUrl("TEST");
		documentListingDto.setOnline(true);
		documentListingDto.setOnlineCad(true);
		documentListingDto.setDocumentDate("TEST");
		documentListingDto.setCreationDate("TEST");
		documentListingDto.setLastModificationDate("TEST");
		documentListingDto.setPublicationDate("TEST");
		documentListingDto.setExpireDate("TEST");
		documentListingDto.setDisableDownload(true);
		documentListingDto.setDocumentTypeDisplayLabel("TEST");
		documentListingDto.setLocales("TEST");
		documentListingDto.setOperatingSystem("TEST");
		documentListingDto.setImageUrl("TEST");
		documentListingDto.setExtension("TEST");
		documentListingDto.setDocLanguages("TEST");
		documentListingDto.setKeywords("TEST");
		documentListingDto.setPartNumber("TEST");
		documentListingDto.setNumberOfPage(100);
		documentListingDto.setDocOwner("TEST");
		documentListingDto.setAuthor("TEST");
		documentListingDto.setRemark("TEST");
		documentListingDto.setShortKeyword("TEST");
		documentListingDto.setMultiPartFileSizeAndType("TEST");
		documentListingDto.setMetaKeywords("TEST");
		documentListingDto.setMetaTitle("TEST");
		documentListingDto.setMetaDescription("TEST");
		documentListingDto.setDocTypeGroups(docTypeGroups);
		documentListingDto.setAlternateUrls(alternateUrls);
		documentListingDto.setDocumentScopes(documentScopes);
		documentListingDto.setDocumentFiles(documentFiles);
		documentListingDto.setProductReferences(productReferences);
		documentListingDto.setShortDescription("shortDescription");
		documentListingDto.setDocumentType(documentType);

		assertEquals("TEST", documentListingDto.getReference());
		assertEquals("TEST", documentListingDto.getEncodedDocReference());
		assertEquals(1000, documentListingDto.getDocumentId());
		assertEquals("TEST", documentListingDto.getDownloadUrl());
		assertEquals("TEST", documentListingDto.getTitle());
		assertEquals("TEST", documentListingDto.getDescription());
		assertEquals("TEST", documentListingDto.getSize());
		assertEquals(1000, documentListingDto.getNumberOfDoc());
		assertEquals("TEST", documentListingDto.getVersion());
		assertEquals(true, documentListingDto.isLegacy());
		assertEquals("TEST", documentListingDto.getBannerUrl());
		assertEquals(true, documentListingDto.isOnline());
		assertEquals(true, documentListingDto.isOnlineCad());
		assertEquals("TEST", documentListingDto.getDocumentDate());
		assertEquals("TEST", documentListingDto.getCreationDate());
		assertEquals("TEST", documentListingDto.getLastModificationDate());
		assertEquals("TEST", documentListingDto.getPublicationDate());
		assertEquals("TEST", documentListingDto.getExpireDate());
		assertEquals(true, documentListingDto.isDisableDownload());
		assertEquals("TEST", documentListingDto.getDocumentTypeDisplayLabel());
		assertEquals("TEST", documentListingDto.getLocales());
		assertEquals("TEST", documentListingDto.getOperatingSystem());
		assertEquals("TEST", documentListingDto.getImageUrl());
		assertEquals("TEST", documentListingDto.getExtension());
		assertEquals("TEST", documentListingDto.getDocLanguages());
		assertEquals("TEST", documentListingDto.getKeywords());
		assertEquals("TEST", documentListingDto.getPartNumber());
		assertEquals(100, documentListingDto.getNumberOfPage());
		assertEquals("TEST", documentListingDto.getDocOwner());
		assertEquals("TEST", documentListingDto.getAuthor());
		assertEquals("TEST", documentListingDto.getRemark());
		assertEquals("TEST", documentListingDto.getShortKeyword());
		assertEquals("TEST", documentListingDto.getMultiPartFileSizeAndType());
		assertEquals("TEST", documentListingDto.getMetaKeywords());
		assertEquals("TEST", documentListingDto.getMetaTitle());
		assertEquals("TEST", documentListingDto.getMetaDescription());
		assertEquals(1, documentListingDto.getDocTypeGroups().size());
		assertEquals(2, documentListingDto.getAlternateUrls().size());
		assertEquals(2, documentListingDto.getDocumentScopes().size());
		assertEquals(1, documentListingDto.getDocumentFiles().size());
		assertEquals(2, documentListingDto.getProductReferences().size());
		assertEquals("shortDescription",documentListingDto.getShortDescription());
		assertNotNull(documentListingDto.getDocumentType());
	}
	
	@Test
	public void testCompareToTest1() {
		documentListingDto.setTitle("TestTitle");
		assertEquals(0,documentListingDto.compareTo(documentListingDto));
	}
	
	@Test
	public void testCompareToTest2() {
		DocumentListingDto documentListingDto1=new DocumentListingDto();
		DocumentListingDto documentListingDto2=new DocumentListingDto();
		documentListingDto1.setTitle("TESTTITLE");
		documentListingDto2.setTitle("TestTitle");
		assertEquals(0,documentListingDto.compare(documentListingDto1, documentListingDto2));
	}

	
	@Test
	public void testInstanceOfTest()
	{
		 Object obj = new DocumentListingDto();
		 boolean value=obj instanceof DocumentListingDto;
		 assertEquals(true,value);
	
	}
	
	@Test
	public void testEqualsTest1() {
		documentListingDto.setDocumentId(1000L);
		documentListingDto.setTitle("TESTtitle");
		 Object obj = new Object();
		 assertNotEquals(documentListingDto, obj);
		
	}
	
	@Test
	public void testEqualsTest2() {
		documentListingDto.setDocumentId(1000L);
		documentListingDto.setTitle("TESTtitle");
		assertEquals(documentListingDto, documentListingDto);
		assertEquals(1000, documentListingDto.hashCode());
		
		
	}
}
