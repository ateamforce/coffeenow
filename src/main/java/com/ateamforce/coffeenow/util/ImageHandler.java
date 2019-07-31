package com.ateamforce.coffeenow.util;

import com.ateamforce.coffeenow.model._ImageCarrier;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sakel
 */
@Service
public class ImageHandler implements ImageHandlerService {

    private final static Logger LOGGER = Logger.getLogger(ImageHandler.class);

    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

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
            // TODO: process image. compress, resize etc
            try {

                BufferedImage originalImage = ImageIO.read(businessObj.getImage().getInputStream());
                int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

                // TODO: only keep one of the two on live environment. or put a new path entirely.
                // Copy image in both destinations, because tomcat has a delay in copying from the first to the 2nd automatically,
                // so when the page loads, the image that is supposed to load is at the 2nd path, but tomcat, hasn't put it there yet
                BufferedImage newImageJpg = new BufferedImage(originalImage.getWidth(),
                        originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                newImageJpg.createGraphics().drawImage(originalImage, 0, 0,Color.WHITE, null);

                localFile = new File(pathArr[0] + "/src/main/webapp" + path + id.toString() + ".jpg");
                ImageIO.write(newImageJpg,
                        "jpg", localFile);
                BufferedImage resizeImageJpg = ImageIO.read(localFile);
                resizeImageJpg=resizeImage(resizeImageJpg,type);
                
                
                localFile = new File(pathArr[0] + "/src/main/webapp" + path + id.toString() + ".jpg");
                ImageIO.write(resizeImageJpg,
                        "jpg", localFile);
                ImageIO.write(resizeImageJpg,
                        "jpg", new File(pathArr[0] + "/target/coffeenow-1.0" + path + id.toString() + ".jpg"));
            } catch (IOException | IllegalStateException e) {
                throw new RuntimeException("Product Image saving failed", e);
            }

        } catch (UnsupportedEncodingException ex) {
            // this is the custom log4j logger (output: ${catalina_home}\logs\coffeenow_general.log) 
            LOGGER.error(ex);
        }

        return (!localFile.equals(new File("/")) && localFile.exists());
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT,Color.WHITE, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    @Override
    public void deleteImage(String path, Integer id) {

        try {
            String realPath = this.getClass().getClassLoader().getResource("").getPath();
            String fullPath = "";

            fullPath = URLDecoder.decode(realPath, "UTF-8");

            String pathArr[] = fullPath.split("/target/");
            File fileToDelete = new File(pathArr[0] + "/src/main/webapp" + path + id.toString() + ".jpg");
            boolean success = fileToDelete.delete();

            if (!success) {
                LOGGER.error("File failed to delete!!");
            }

        } catch (UnsupportedEncodingException ex) {
            LOGGER.error(ex);
        }

    }

}
