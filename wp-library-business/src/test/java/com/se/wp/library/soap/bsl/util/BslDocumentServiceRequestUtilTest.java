package com.se.wp.library.soap.bsl.util;

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

import com.schneider.oreo.service.document.DocCountByBean;
import com.schneider.oreo.service.document.QueryBean;
import com.se.wp.library.constants.Constants;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BslDocumentServiceRequestUtilTest {
	Map<String, List<Long>> numericFilters = new HashMap<>();
	Map<String, List<String>> stringFiletrs = new HashMap<>();
   
	@Mock
	QueryBean queryBean;
	@InjectMocks
	BslDocumentServiceRequestUtil bslDocumentServiceRequestUtil;

	@BeforeEach
	public void setUp() throws Exception {
		queryBean = new QueryBean();
      
		List<Long> doctypeList = new ArrayList<>();
		List<String> refNum = new ArrayList<>();
		doctypeList.add(5000L);
		doctypeList.add(5001L);
		refNum.add("230");
		numericFilters.put(Constants.DOC_TYPE, doctypeList);
		stringFiletrs.put(Constants.REF_NUM, refNum);
	}

	@Test
	public void testGetQueryBeanTest() {
		bslDocumentServiceRequestUtil.getQueryBean(null,numericFilters, stringFiletrs);
	}

	
	@Test
	public void testSetCountByBeanTest()
	{
		List<DocCountByBean> countByBeanList = new ArrayList<>();
		bslDocumentServiceRequestUtil.setCountByBean(BslContants.COUNT_BY_DOC_TYPE_GROUP, BslContants.COUNT_PARAM_DEFAUTL,countByBeanList);
		assertEquals(1,countByBeanList.size());
	}
	
	
}
