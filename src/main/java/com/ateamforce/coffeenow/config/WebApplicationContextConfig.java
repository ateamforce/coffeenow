package com.ateamforce.coffeenow.config;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.ateamforce.coffeenow")
public class WebApplicationContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        TilesViewResolver viewResolver = new TilesViewResolver();
        registry.viewResolver(viewResolver);
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
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

        // COMMON IMAGES
        // Common boilerplate (template) images (uploaded manually)
        registry.addResourceHandler("/img/common/**").addResourceLocations("/resources/common/images/boilerplate/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // COMMON CSS
        registry.addResourceHandler("/css/common/**").addResourceLocations("/resources/common/css/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS)).setCachePeriod(604800);
        // COMMON JS
        registry.addResourceHandler("/js/common/**").addResourceLocations("/resources/common/js/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        // FRONT IMAGES
        // front boilerplate (template) images (uploaded manually)
        registry.addResourceHandler("/img/front/**").addResourceLocations("/resources/front/images/boilerplate/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // User specific images (uploaded by clients, saved as /resources/front/images/clients/clientId/imageId-filename )
        registry.addResourceHandler("/img/user/**").addResourceLocations("/resources/front/images/clients/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // Exras Categories images (uploaded by admin - saved as /resources/front/images/extras/categories/extraCatId-filename)
        registry.addResourceHandler("/img/extra/category/**").addResourceLocations("/resources/front/images/extras/categories/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // Extras Items images (uploaded by admin - saved as /resources/front/images/extras/items/extraId-filename)
        registry.addResourceHandler("/img/extra/**").addResourceLocations("/resources/front/images/extras/items/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // Payment types images (uploaded by admin - saved as /resources/front/images/paymenttypes/paymentId-filename)
        registry.addResourceHandler("/img/payment/**").addResourceLocations("/resources/front/images/paymenttypes/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // Product Categories images (uploaded by admin - saved as /resources/front/images/products/categories/productCatId-filename)
        registry.addResourceHandler("/img/product/category/**").addResourceLocations("/resources/front/images/products/categories/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // Product Items images (uploaded by admin - saved as /resources/front/images/products/items/productId-filename)
        registry.addResourceHandler("/img/product/**").addResourceLocations("/resources/front/images/products/items/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // Store specific images (uploaded by stores - saved as /resources/front/images/stores/storeId-filename)
        registry.addResourceHandler("/img/store/**").addResourceLocations("/resources/front/images/stores/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // FRONT CSS
        registry.addResourceHandler("/css/front/**").addResourceLocations("/resources/front/css/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // FRONT JS
        registry.addResourceHandler("/js/front/**").addResourceLocations("/resources/front/js/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        // BACK_ADMIN IMAGES
        // back_admin boilerplate (template) images (uploaded manually)
        registry.addResourceHandler("/img/administrator/**").addResourceLocations("/resources/back_admin/images/boilerplate/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // BACK_ADMIN CSS
        registry.addResourceHandler("/css/administrator/**").addResourceLocations("/resources/back_admin/css/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // BACK_ADMIN JS
        registry.addResourceHandler("/js/administrator/**").addResourceLocations("/resources/back_admin/js/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        // BACK_STORE IMAGES
        // back_store boilerplate (template) images (uploaded manually)
        registry.addResourceHandler("/img/store/**").addResourceLocations("/resources/back_store/images/boilerplate/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // BACK_STORE css
        registry.addResourceHandler("/css/store/**").addResourceLocations("/resources/back_store/css/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // BACK_STORE js
        registry.addResourceHandler("/js/store/**").addResourceLocations("/resources/back_store/js/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        // WEBJARS
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(false);

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
        resolver.setDefaultLocale(new Locale("el"));
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
