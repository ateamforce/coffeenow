package com.ateamforce.coffeenow.util;

import com.ateamforce.coffeenow.model._ImageCarrier;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sakel
 */
@Service
public class ImageHandler implements ImageHandlerService {

    private final static Logger LOGGER = Logger.getLogger(ImageHandler.class);

    /**
     * Saves an image to path with id as filename
     *
     * @param path The path to save the image e.g.
     * "/src/main/webapp/resources/front/images/products/categories/". special
     * care to include both starting and trailing slashes
     * @param id the id of the business object, to be used as a filename
     * @param businessObj the business object that contains the image to be
     * saved to path
     * @return true if image was saved successfully, false if not
     */
    @Override
    public boolean saveImage(String path, Integer id, _ImageCarrier businessObj) {

        File localFile = new File("/");
        String realPath = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = "";
        try {
            fullPath = URLDecoder.decode(realPath, "UTF-8");

            String pathArr[] = fullPath.split("/target/");

            // convert image to jpg, if necessary, and save to disk
            if (!businessObj.getImage().isEmpty()) {
                try {

                    BufferedImage img = ImageIO.read(businessObj.getImage().getInputStream());
                    // TODO: only keep one of the two on live environment. or put a new path entirely.
                    // Copy image in both destinations, because tomcat has a delay in copying from the first to the 2nd automatically,
                    // so when the page loads, the image that is supposed to load is at the 2nd path, but tomcat, hasn't put it there yet
                    ImageIO.write(img, "jpg", new File(pathArr[0] + "/src/main/webapp" + path + id.toString() + ".jpg"));
                    ImageIO.write(img, "jpg", new File(pathArr[0] + "/target/coffeenow-1.0" + path + id.toString() + ".jpg"));
                } catch (IOException | IllegalStateException e) {
                    throw new RuntimeException("Product Image saving failed", e);
                }
            }

        } catch (UnsupportedEncodingException ex) {
            // this is the custom log4j logger (output: ${catalina_home}\logs\coffeenow_general.log) 
            LOGGER.error(ex);
        }

        return (!localFile.equals(new File("/")) && localFile.exists());
    }

    @Override
    public void deleteImage(String path, Integer id) {

        try {
            String realPath = this.getClass().getClassLoader().getResource("").getPath();
            String fullPath = "";

            fullPath = URLDecoder.decode(realPath, "UTF-8");

            String pathArr[] = fullPath.split("/target/");
            File fileToDelete = new File(pathArr[0] + "/src/main/webapp" + path + id.toString() + ".jpg");
            boolean success=fileToDelete.delete();
            
            if(!success){
            LOGGER.error("File failed to delete!!");
            }
            
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error(ex);
        }

    }

}
