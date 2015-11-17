package com.thoughtriott.metaplay.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

public class MetaplayWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	//limit parts to be no more than 1mb, and the entire request no more than 2mb
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(
        new MultipartConfigElement("/tmp/uploads", 1097152, 2097152, 0));
	}

}
