package com.thoughtriott.metaplay.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class MetaplaySecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {
	// 1. This class is discovered by Spring because AbstractSecurityWebApplicationInitializer implements WebApplicationInitializer.
	//	  you can override appendFilters() and insertFilters() to register filters of your own choosing
	// 2. You need not override anything to register DelegatingFilterProxy, which delegates to Spring-injected filter.

	// 3. DelegatingFilterProxy intercepts requests coming into the application and delegates them to a bean 
	//    whose ID is springSecurityFilterChain (created when you enable Web Security)
	// 4. springSecurityFilterChain is a single filter that chains together one or more additional filters.
}
