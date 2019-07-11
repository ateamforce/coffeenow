package com.ateamforce.coffeenow.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { 
                    RootApplicationContextConfig.class, 
                    JpaConfig.class
                };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { 
                    WebApplicationContextConfig.class, 
                    TilesConfig.class, 
                    WebFlowConfig.class, 
                    WebSecurityConfig.class 
                };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}