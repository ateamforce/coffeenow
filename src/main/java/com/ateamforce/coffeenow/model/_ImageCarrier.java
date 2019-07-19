package com.ateamforce.coffeenow.model;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * A class mean to be extended by business objects that have an image associated with them
 *
 * @author Sakel
 */
public abstract class _ImageCarrier {

    @Transient
    @XmlTransient
    @JsonIgnore // excludes image from json view of the object
    private MultipartFile image;
    
    public _ImageCarrier() {
    }

    public _ImageCarrier(MultipartFile image) {
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    
}
