package com.ateamforce.coffeenow.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.ateamforce.coffeenow")
@Import({ ThymeleafConfig.class })
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// enable messages.properties
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setDefaultEncoding("UTF-8");
		resource.setBasename("messages");
		return resource;
	}

	// serve static resources. In this case serve images, on /img/* request, from
	// the src/main/webapp/resources/images/ (spring MVC copies webapp contents to
	// the root directory during build)
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// front images
		registry.addResourceHandler("/img/user/**").addResourceLocations("/resources/front/images/clients/");
                registry.addResourceHandler("/img/extra/category/**").addResourceLocations("/resources/front/images/extras/categories/");
                registry.addResourceHandler("/img/extra/**").addResourceLocations("/resources/front/images/extras/items/");
                registry.addResourceHandler("/img/payment/**").addResourceLocations("/resources/front/images/paymenttypes/");
                registry.addResourceHandler("/img/product/category/**").addResourceLocations("/resources/front/images/products/categories/");
                registry.addResourceHandler("/img/product/**").addResourceLocations("/resources/front/images/products/items/");
                registry.addResourceHandler("/img/store/**").addResourceLocations("/resources/front/images/stores/");
                
                // front css
                registry.addResourceHandler("/css/**").addResourceLocations("/resources/front/css/");
                
                // front js
                registry.addResourceHandler("/js/**").addResourceLocations("/resources/front/js/");
                
                // admin css
                registry.addResourceHandler("/admin/css/**").addResourceLocations("/resources/back_admin/css/");
                
                // admin js
                registry.addResourceHandler("/admin/js/**").addResourceLocations("/resources/back_admin/js/");
                
                // store dashboard css
                registry.addResourceHandler("/store/css/**").addResourceLocations("/resources/back_store/css/");
                
                // store dashboard js
                registry.addResourceHandler("/store/js/**").addResourceLocations("/resources/back_store/js/");
                
	}

	// enable file uploading
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(10240000);
		return resolver;
	}
        
	// add interceptors
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// for Locale change
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		registry.addInterceptor(localeChangeInterceptor);

	}

	// setting default Locale
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(new Locale("gr"));
		return resolver;
	}

	// enable JSR-303 validation and set message source
	// Java Bean Validation (JSR-303) is a Java specification that allows us to
	// express validation
	// constraints on objects via annotations. It allows the APIs to validate and
	// report violations.
	// Hibernate Validator is the reference implementation of the Bean Validation
	// specification.
	@Bean(name = "validator")
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	// needed in order to introduce validator bean to Spring MVC
	@Override
	public Validator getValidator() {
		return validator();
	}


}