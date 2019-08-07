package com.ateamforce.coffeenow.config;

import com.ateamforce.coffeenow.filter.CurrentUserFilter;
import javax.servlet.Filter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
            RootApplicationContextConfig.class,
            WebApplicationContextConfig.class,
            JpaConfig.class,
            WebSecurityConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
            TilesConfig.class,
            WebFlowConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new CurrentUserFilter()};
    }
}
