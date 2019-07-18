package com.ateamforce.coffeenow.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class with one method, capable of returning a folder INSIDE our project's folder structure (not tomcat's target)
 * 
 * @author Sakel
 */
public class ImagesHandler {

    /**
     * 
     * @param urlSuffix the rest of the path inside the project folder structure, starting with /src/..
     * @return 
     */
    public String getPath(String urlSuffix)  {
        
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = "";
        try {
            fullPath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ImagesHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        String pathArr[] = fullPath.split("/target/");

        return pathArr[0] + urlSuffix;
        
    }

}
