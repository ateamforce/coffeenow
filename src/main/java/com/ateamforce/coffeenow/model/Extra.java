/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extra.findAllExtras", query = "SELECT e FROM Extra e")
    , @NamedQuery(name = "Extra.findExtraById", query = "SELECT e FROM Extra e WHERE e.id = :extraId")
    , @NamedQuery(name = "Extra.findByTitle", query = "SELECT e FROM Extra e WHERE e.title = :title")})
public class Extra extends _ImageCarrier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 255, message = "{title.size.restriction.message}")
    @Column(name = "title")
    private String title;
    @Column(name = "hasimage")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean hasimage;
    @Transient
    private List<ExtraCategory> extracategoriesList;
    @Transient
    private List<StoreExtra> storesExtrasList;
    @Transient
    private List<OrderProduct> ordersProductsList;

    public Extra() {
    }

    public Extra(Integer id) {
        this.id = id;
    }

    public Extra(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Extra(Integer id, String title, MultipartFile image) {
        super(image);
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasimage() {
        return hasimage;
    }

    public void setHasimage(boolean hasimage) {
        this.hasimage = hasimage;
    }

    public List<ExtraCategory> getExtracategoriesList() {
        return extracategoriesList;
    }

    public void setExtracategoriesList(List<ExtraCategory> extracategoriesList) {
        this.extracategoriesList = extracategoriesList;
    }


    public List<StoreExtra> getStoresExtrasList() {
        return storesExtrasList;
    }

    public void setStoresExtrasList(List<StoreExtra> storesExtrasList) {
        this.storesExtrasList = storesExtrasList;
    }

    public List<OrderProduct> getOrdersProductsList() {
        return ordersProductsList;
    }

    public void setOrdersProductsList(List<OrderProduct> ordersProductsList) {
        this.ordersProductsList = ordersProductsList;
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
        if (!(object instanceof Extra)) {
            return false;
        }
        Extra other = (Extra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Extras[ id=" + id + ", title=" + title + ", hasimage=" + hasimage + " ]";
    }

}
