package com.ateamforce.coffeenow.config;

import com.ateamforce.coffeenow.interceptor.SeoPageDetailsInterceptor;
import com.ateamforce.coffeenow.validator.ExtraCategoryValidator;
import com.ateamforce.coffeenow.validator.ExtraValidator;
import com.ateamforce.coffeenow.validator.ImageValidator;
import com.ateamforce.coffeenow.validator.ProductCategoryValidator;
import com.ateamforce.coffeenow.validator.ProductValidator;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.CacheControl;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
@PropertySource("classpath:custom.properties")
@ComponentScan("com.ateamforce.coffeenow")
public class WebApplicationContextConfig implements WebMvcConfigurer {

    public MessageSource messageSource;
    public LocaleResolver localeResolver;

    @Autowired
    Environment env;

    public WebApplicationContextConfig() {
        this.messageSource = messageSource();
        this.localeResolver = localeResolver();
    }

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
    
    // Using smtp relay mailjet.com . Atm I have relayed coffeenow_gr@mail.com .  We can relay any other email we can also verify
    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        
        javaMailSender.setPassword("8c79e0b39da32a29580ae77829f7791f");
        javaMailSender.setUsername("21f041ee7cd6a1e537f6238b9e377bef");
        
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.transport.protocol", "smtp");
        javaMailProperties.setProperty("mail.smtp.host", "in-v3.mailjet.com");
        javaMailProperties.setProperty("mail.smtp.port", "587");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailSender.setJavaMailProperties(javaMailProperties);

        return javaMailSender;
    }

    // enable messages.properties
    @Bean
    public static MessageSource messageSource() {
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
        registry.addResourceHandler("/img/common/**").addResourceLocations(env.getProperty("common.images"))
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        // COMMON CSS
        registry.addResourceHandler("/css/common/**").addResourceLocations(env.getProperty("common.css"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // COMMON JS
        registry.addResourceHandler("/js/common/**").addResourceLocations(env.getProperty("common.js"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // COMMON ASSETS(PIXIE)
        registry.addResourceHandler("/assets/**").addResourceLocations(env.getProperty("common.assets"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        // FRONT IMAGES
        // front boilerplate (template) images (uploaded manually)
        registry.addResourceHandler("/images/**").addResourceLocations(env.getProperty("front.images"))
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        // User specific images (uploaded by clients, saved as /resources/front/images/clients/clientId/imageId-filename )
        registry.addResourceHandler("/img/user/**").addResourceLocations(env.getProperty("front.images.clients"))
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        // Exras Categories images (uploaded by admin - saved as /resources/front/images/extras/categories/extraCatId-filename)
        registry.addResourceHandler("/img/extra/category/**").addResourceLocations(env.getProperty("front.images.extras.categories"))
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));
        // Extras Items images (uploaded by admin - saved as /resources/front/images/extras/items/extraId-filename)
        registry.addResourceHandler("/img/extra/**").addResourceLocations(env.getProperty("front.images.extras.items"))
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        // Payment types images (uploaded by admin - saved as /resources/front/images/paymenttypes/paymentId-filename)
        registry.addResourceHandler("/img/payment/**").addResourceLocations(env.getProperty("front.images.paymenttypes"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // Product Categories images (uploaded by admin - saved as /resources/front/images/products/categories/productCatId-filename)
        registry.addResourceHandler("/img/product/category/**").addResourceLocations(env.getProperty("front.images.products.categories"))
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));
        // Product Items images (uploaded by admin - saved as /resources/front/images/products/items/productId-filename)
        registry.addResourceHandler("/img/product/**").addResourceLocations(env.getProperty("front.images.products.items"))
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        // Store specific images (uploaded by stores - saved as /resources/front/images/stores/storeId-filename)
        registry.addResourceHandler("/img/store/**").addResourceLocations(env.getProperty("front.images.stores"))
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS));
        // FRONT CSS
        registry.addResourceHandler("/css/**").addResourceLocations(env.getProperty("front.css"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // FRONT JS
        registry.addResourceHandler("/js/**").addResourceLocations(env.getProperty("front.js"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // FRONT FONTS
        registry.addResourceHandler("/fonts/**").addResourceLocations(env.getProperty("front.fonts"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        // BACK_ADMIN IMAGES
        // back_admin boilerplate (template) images (uploaded manually)
        registry.addResourceHandler("/img/administrator/**").addResourceLocations(env.getProperty("admin.images"))
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));
        // BACK_ADMIN CSS
        registry.addResourceHandler("/css/administrator/**").addResourceLocations(env.getProperty("admin.css"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // BACK_ADMIN JS
        registry.addResourceHandler("/js/administrator/**").addResourceLocations(env.getProperty("admin.js"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        // BACK_STORE IMAGES
        // back_store boilerplate (template) images (uploaded manually)
        registry.addResourceHandler("/img/store/**").addResourceLocations(env.getProperty("store.images"))
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));
        // BACK_STORE css
        registry.addResourceHandler("/css/store/**").addResourceLocations(env.getProperty("store.css"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
        // BACK_STORE js
        registry.addResourceHandler("/js/store/**").addResourceLocations(env.getProperty("store.js"))
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        // WEBJARS (resourceChain(false) enables version agnostic linking)
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(false);

    }

    // enable file uploading
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setResolveLazily(true);
        return resolver;
    }

    // add interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // for Locale change
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor).order(0);
        registry.addInterceptor(new SeoPageDetailsInterceptor(messageSource, localeResolver)).order(1);

    }

    // setting default Locale
    @Bean
    public static LocaleResolver localeResolver() {
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

    // bean for the spring validator ProductCategoryValidator (basically merging JSR-303 with spring validation)
    @Bean
    public ProductCategoryValidator productCategoryValidator() {
        Set<Validator> springValidators = new HashSet();
        springValidators.add(new ImageValidator());
        ProductCategoryValidator productCategoryValidator = new ProductCategoryValidator();
        productCategoryValidator.setSpringValidators(springValidators);
        return productCategoryValidator;
    }
    
    // bean for the spring validator ExtraCategoryValidator (basically merging JSR-303 with spring validation)
    @Bean
    public ExtraCategoryValidator extraCategoryValidator() {
        Set<Validator> springValidators = new HashSet();
        springValidators.add(new ImageValidator());
        ExtraCategoryValidator extraCategoryValidator = new ExtraCategoryValidator();
        extraCategoryValidator.setSpringValidators(springValidators);
        return extraCategoryValidator;
    }
    
    // bean for the spring validator ProductValidator (basically merging JSR-303 with spring validation)
    @Bean
    public ProductValidator productValidator() {
        Set<Validator> springValidators = new HashSet();
        springValidators.add(new ImageValidator());
        ProductValidator productValidator = new ProductValidator();
        productValidator.setSpringValidators(springValidators);
        return productValidator;
    }
    
    // bean for the spring validator ExtraValidator (basically merging JSR-303 with spring validation)
    @Bean
    public ExtraValidator extraValidator() {
        Set<Validator> springValidators = new HashSet();
        springValidators.add(new ImageValidator());
        ExtraValidator extraValidator = new ExtraValidator();
        extraValidator.setSpringValidators(springValidators);
        return extraValidator;
    }
    
    // TODO: DO NOT FORGET to add beans for all future validators that concern _ImageCarrier objects

}
