package com.ateamforce.coffeenow.editor;

import java.awt.image.BufferedImage;
import java.beans.PropertyEditorSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Sakel
 */
public class StringToImageEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            try {
                String base64Image = text.split(",")[1];
                byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
                MultipartFile file = new MultipartFileImpl(imageBytes);
                setValue(file);
            } catch (IOException ex) {
                Logger.getLogger(StringToImageEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            setValue(new EmptyMultipartFileImpl());
        }
    }

    private static class MultipartFileImpl implements MultipartFile {

        private final byte[] imgContent;
        
        public MultipartFileImpl(byte[] imgContent) throws IOException {
            
            // Get a BufferedImage object from a byte array
            InputStream in = new ByteArrayInputStream(imgContent);
            BufferedImage originalImage = ImageIO.read(in);

            // Get image dimensions
            int height = originalImage.getHeight();
            int width = originalImage.getWidth();
            
            //TODO: implement specific sizes and different resizing logic per image case (e.g. product/extra categories = 350x350, products = 500x500, extras = 350x350, storemedia = {logo = 350x350, profile= 1900x400, gallery = free}, paymenttypes = 100x100)
            
            byte[] imageInByte;

            // The image is already a square
            if (height == width) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write( originalImage, "jpg", baos );
                baos.flush();
                imageInByte = baos.toByteArray();
                baos.close();
            }
            else {
                // Compute the size of the square
                int squareSize = (height > width ? width : height);

                // Coordinates of the image's middle
                int xc = width / 2;
                int yc = height / 2;

                // Crop
                BufferedImage croppedImage = originalImage.getSubimage(
                    xc - (squareSize / 2), // x coordinate of the upper-left corner
                    yc - (squareSize / 2), // y coordinate of the upper-left corner
                    squareSize,            // widht
                    squareSize             // height
                );
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write( croppedImage, "jpg", baos );
                baos.flush();
                imageInByte = baos.toByteArray();
                baos.close();
            }
            
            this.imgContent = imageInByte;
            
        }
        
        @Override
        public String getName() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getOriginalFilename() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getContentType() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isEmpty() {
            return imgContent == null || imgContent.length == 0;
        }

        @Override
        public long getSize() {
            return imgContent.length;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return imgContent;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(imgContent);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            new FileOutputStream(dest).write(imgContent);
        }
    }
    
    private static class EmptyMultipartFileImpl implements MultipartFile {
    
        private final byte[] imgContent;

        public EmptyMultipartFileImpl() {
            this.imgContent = new byte[0];
        }

        @Override
        public String getName() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getOriginalFilename() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getContentType() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isEmpty() {
            return imgContent == null || imgContent.length == 0;
        }

        @Override
        public long getSize() {
            return imgContent.length;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return imgContent;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(imgContent);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            new FileOutputStream(dest).write(imgContent);
        }
    }
    
    

}
