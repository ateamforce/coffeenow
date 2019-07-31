/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "storemedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreMedia.findAll", query = "SELECT s FROM StoreMedia s")
    , @NamedQuery(name = "StoreMedia.findById", query = "SELECT s FROM StoreMedia s WHERE s.id = :id")
    , @NamedQuery(name = "StoreMedia.findByStore", query = "SELECT s FROM StoreMedia s WHERE s.store.id = :storeid")})
public class StoreMedia extends _ImageCarrier implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @JoinColumn(name = "storeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Store store;
    
    @Column(name = "hasimage")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean hasimage;
    
    @Column(name = "type")
    @Pattern(regexp = "gallery|logo|profile", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String type;

    public StoreMedia() {
    }

    public StoreMedia(Integer id) {
        this.id = id;
    }

    public StoreMedia(Integer id, MultipartFile image) {
        super(image);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Store getStoreid() {
        return store;
    }

    public void setStoreid(Store storeid) {
        this.store = storeid;
    }

    public boolean isHasimage() {
        return hasimage;
    }

    public void setHasimage(boolean hasimage) {
        this.hasimage = hasimage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setImage(MultipartFile image) {
        super.setImage(image);
    }

    @Override
    public MultipartFile getImage() {
        return super.getImage();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreMedia)) {
            return false;
        }
        StoreMedia other = (StoreMedia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Storemedia[ id=" + id + " ]";
    }

}
