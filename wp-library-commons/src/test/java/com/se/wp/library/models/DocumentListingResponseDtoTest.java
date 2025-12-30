package com.se.wp.library.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class DocumentListingResponseDtoTest{

  List<DocumentListingDto> documents= new ArrayList<>();
	DocumentType documentType = new DocumentType();
	List<DocumentType> documentTypeList = new ArrayList<>();
	DocumentTypeGroup documentTypeGroupFilter = new DocumentTypeGroup();
	List<DocumentTypeGroup> docTypeGroupList = new ArrayList<>();
	Map<String, String> sortByOptions=new HashMap<>();
	Map<String, Object> criteria=new HashMap<>();
	
	@InjectMocks
	DocumentListingResponseDto documentListingResponseDto;
	@Mock
	DocumentListingDto documentListingDto;
	@Mock
	DocumentTypeGroup documentTypeGroup;

	
	@BeforeEach
	public void setUp() throws Exception {
		
		documentTypeGroup=new DocumentTypeGroup();
		documentListingDto=new DocumentListingDto();
		documentListingDto.setReference("TEST");
		documentListingDto.setEncodedDocReference("TEST");
		documentListingDto.setDocumentId(1000L);
		documentListingDto.setDownloadUrl("TEST");
		documentListingDto.setTitle("TEST");
		documentListingDto.setDescription("TEST");
		documentListingDto.setSize("TEST");
		documents.add(documentListingDto);
		documentType.setId(Long.valueOf(200));
		documentType.setLabel("CAD files");
		documentType.setNumberOfDocs((long) 87389);
		documentTypeList.add(documentType);
		documentTypeGroupFilter.setId(Long.valueOf(500));
		documentTypeGroupFilter.setLabel("Brochures");
		documentTypeGroupFilter.setNumberOfDocs((long) 2712);
		docTypeGroupList.add(documentTypeGroupFilter);
		sortByOptions.put("SORT_BY_RELEVANCE", "Relevance");
		sortByOptions.put("SORT_BY_POPULARITY", "Popularity");
		criteria.put("documentType", documentType);
		criteria.put("documentListingDto", documentListingDto);
		
	}

	@Test
	public void test() {
		documentListingResponseDto.setDocumentsCount(1000);
		documentListingResponseDto.setDocuments(documents);
		assertEquals(1000, documentListingResponseDto.getDocumentsCount());
		assertEquals(1, documentListingResponseDto.getDocuments().size());

		
	}

}
