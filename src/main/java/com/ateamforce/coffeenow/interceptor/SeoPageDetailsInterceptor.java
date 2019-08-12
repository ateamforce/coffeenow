package com.ateamforce.coffeenow.interceptor;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is an spring interceptor responsible for adding the necessary SEO page details for every request,
 * details like page title and description
 * 
 * if you do not want to implement all 3 mandatory methods (preHandle,
 * postHandle, afterCompletion), then instead you can extend a default
 * implementation (HandlerInterceptorAdapter) of HandlerInterceptor
 * 
 * @author Sakel
 */
public class SeoPageDetailsInterceptor implements HandlerInterceptor {
    
    private static final Logger LOGGER = Logger.getLogger(SeoPageDetailsInterceptor.class);
    
    private MessageSource messageSource;
    
    private LocaleResolver localeResolver;
    
    private HashMap<String, String[]> pageDetails;

    public SeoPageDetailsInterceptor(MessageSource messageSource, LocaleResolver localeResolver) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        
        // initialize map of urls/titles/descriptions
        preSeo(request);
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
            
        String mapping = request.getRequestURI().replace(request.getContextPath(), "");
        
        // TITLE
        postSeoTitle(request, mapping);
        // OG:URL
        postSeoOgUrl(request, mapping);
        // OG:IMAGE, OG:IMAGE:WIDTH, OG:IMAGE:HEIGHT
        postSeoOgImage(request);
        // DESCRIPTION
        postSeoDescription(request, mapping);
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                    Exception exceptionIfAny) {
        // NO operation.
    }
    
    // initializes map of urls/titles/descriptions
    // take extra care to have / at the beginning AND the end of the url
    private void preSeo(HttpServletRequest request){
        
        String defaultDescription = messageSource.getMessage("seo.description.default", null, localeResolver.resolveLocale(request));
        
        pageDetails = new HashMap<>();
        pageDetails.put("/", new String[]{"HomePage | CoffeeNow",defaultDescription});
        pageDetails.put("/administrator/", new String[]{"Administrator Login | CoffeeNow",defaultDescription});
        pageDetails.put("/administrator/dashboard/", new String[]{"Administrator Dashboard | CoffeeNow",defaultDescription});
        pageDetails.put("/administrator/dashboard/stores/", new String[]{"Stores | Administrator Dashboard | CoffeeNow",defaultDescription});
        pageDetails.put("/administrator/dashboard/products/", new String[]{"Products | Administrator Dashboard | CoffeeNow",defaultDescription});
        pageDetails.put("/administrator/dashboard/productcategories/", new String[]{"Product Categories | Administrator Dashboard | CoffeeNow",defaultDescription});
        pageDetails.put("/administrator/dashboard/extras/", new String[]{"Extras | Administrator Dashboard | CoffeeNow",defaultDescription});
        pageDetails.put("/administrator/dashboard/extracategories/", new String[]{"Extras Categories | Administrator Dashboard | CoffeeNow",defaultDescription});
        pageDetails.put("/store/", new String[]{"Store Login | CoffeeNow",defaultDescription});
        pageDetails.put("/store/register/", new String[]{"Store Registration | CoffeeNow",defaultDescription});
        pageDetails.put("/store/register/resend/confirm/", new String[]{"Resend Confirmation Email | CoffeeNow",defaultDescription});
        pageDetails.put("/store/reset/password/", new String[]{"Reset Password | CoffeeNow",defaultDescription});
        pageDetails.put("/store/reset/password/change/", new String[]{"Provide a New Password | CoffeeNow",defaultDescription});
        pageDetails.put("/store/dashboard/", new String[]{"Store Dashboard | CoffeeNow",defaultDescription});
        pageDetails.put("/store/dashboard/profile/", new String[]{"Your Store Profile | CoffeeNow",defaultDescription});
        pageDetails.put("/store/dashboard/profile/products/", new String[]{"Your Products | CoffeeNow",defaultDescription});
        pageDetails.put("/store/dashboard/profile/extras/", new String[]{"Your Extras | CoffeeNow",defaultDescription});
        pageDetails.put("/store/dashboard/orders/", new String[]{"Your Orders | CoffeeNow",defaultDescription});
    }
    
    private void postSeoTitle(HttpServletRequest request, String mapping){
        //  <title>${SEO_pageTitle}</title>
        if (request.getAttribute("SEO_pageTitle") == null) {
            if ( pageDetails.containsKey(mapping) ) request.setAttribute("SEO_pageTitle", pageDetails.get(mapping)[0]);
            else if (pageDetails.containsKey(mapping + "/")) request.setAttribute("SEO_pageTitle", pageDetails.get(mapping + "/")[0]);
        }
    }
    
    private void postSeoDescription(HttpServletRequest request, String mapping){
        //  <title>${SEO_pageDescription}</title>
        if (request.getAttribute("SEO_pageDescription") == null) {
            if ( pageDetails.containsKey(mapping) ) request.setAttribute("SEO_pageDescription", pageDetails.get(mapping)[1]);
            else if (pageDetails.containsKey(mapping + "/")) request.setAttribute("SEO_pageDescription", pageDetails.get(mapping + "/")[1]);
        }
    }
    
    private void postSeoOgUrl(HttpServletRequest request, String mapping){
        // <meta property="og:url" content="${SEO_ogUrl}" />
        if (request.getAttribute("SEO_ogUrl") == null) {
            request.setAttribute("SEO_ogUrl", mapping);
        }
    }
    
    private void postSeoOgImage(HttpServletRequest request){
        // <meta property="og:image" content="${SEO_ogImage}" />
        // <meta property="og:image:width" content="${SEO_ogImageWidth}" />
        // <meta property="og:image:height" content="${SEO_ogImageHeight}" />
        if (request.getAttribute("SEO_ogImage") == null) {
            request.setAttribute("SEO_ogImage", "img/common/logo-square.png");
            request.setAttribute("SEO_ogImageWidth", "400");
            request.setAttribute("SEO_ogImageHeight", "400");
        }
        else if (request.getAttribute("SEO_ogImageWidth") == null || request.getAttribute("SEO_ogImageHeight") == null) {
            String cleanedOgImage = request.getAttribute("SEO_ogImage").toString();
            // remove potential initial slash (/)
            if (cleanedOgImage.startsWith("/")) cleanedOgImage = cleanedOgImage.substring(0, cleanedOgImage.length() - 1);
            // split the result on slash (/)
            String[] OgImageParts = cleanedOgImage.split("/");
            // get the image
            String imageFilename = OgImageParts[OgImageParts.length - 1];
            // get the path
            OgImageParts[OgImageParts.length - 1] = "";
            String path = "/" + String.join("/", OgImageParts);
            // TODO: get file and return og:image:width and height
        }
    }
    
}