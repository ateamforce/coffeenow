package com.ateamforce.coffeenow.util;

import java.io.File;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Sakel
 */
public class CacheBuster {
    
    public static String bust(String path, String type, String realPath){
        
        if ( type.equals("css") || type.equals("js") ) {
            
            String v;
            v = StringUtils.substringAfterLast(StringUtils.substringBeforeLast(path, "/"), "/");
            System.out.println(realPath);
            System.out.println(v);
            File file = new File(realPath + v);

            switch (type) {
                case "css":
                    return buildStyle(path + "?" + file.lastModified());
                case "js":
                    return buildScript(path + "?" + file.lastModified());
                default:
                    return "";
            }
        }
        else {
            return "";
        }

    }
    
    private static String buildScript(String path)
    {
            return "<script type=\"text/javascript\" src=\"" + path + "\"></script>";
    }

    private static String buildStyle(String path)
    {
            return "<link rel=\"stylesheet\" type=\"text/css\" href=\"" + path + "\"></link>";
    }

}
