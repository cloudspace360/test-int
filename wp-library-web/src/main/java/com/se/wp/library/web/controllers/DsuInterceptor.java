package com.se.wp.library.web.controllers;

import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;


import com.se.wp.library.utils.DsuUtil;
import com.se.wp.library.utils.EnvironmentUtil;

/**
 * @author SESA404501
 *
 */
@Component
public class DsuInterceptor implements HandlerInterceptor {
	@Autowired
	protected DsuUtil DsuUtil;
	@Autowired
	Environment env;
	private static final Logger LOGGER = LoggerFactory.getLogger(DsuInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView modelAndView) throws Exception {
		DsuUtil.addSecurityHeaders( response);
		LOGGER.debug("ENTER: DsuApiInterceptor.postHandle()");
	}
	
}