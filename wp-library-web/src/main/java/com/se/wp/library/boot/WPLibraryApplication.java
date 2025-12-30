
package com.se.wp.library.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Vikas.Kadam
 *
 */
@SpringBootApplication
@ComponentScan({ "com.se.wp.library" })
@EnableCaching(proxyTargetClass = true)
public class WPLibraryApplication extends SpringBootServletInitializer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(WPLibraryApplication.class, args);// NOSONAR
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.boot.web.servlet.support.SpringBootServletInitializer
	 * #configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WPLibraryApplication.class);
	}
}