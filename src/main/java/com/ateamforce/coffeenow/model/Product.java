/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAllProducts", query = "SELECT p FROM Product p")
    , @NamedQuery(name = "Product.findProductById", query = "SELECT p FROM Product p WHERE p.id = :productId")
    , @NamedQuery(name = "Product.findByTitle", query = "SELECT p FROM Product p WHERE p.title = :title")})
public class Product extends _ImageCarrier implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Column(name = "hasimage")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean hasimage;
    @Transient
    private List<Client> clientsList;
    @Transient
    private List<ProductCategory> productcategoriesList;
    @Transient
    private List<OrderProduct> ordersProductsList;
    @Transient
    private List<StoreProduct> storesProductsList;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    
    public Product(Integer id, String title, String description, boolean hasimage) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hasimage = hasimage;
    }

    public Product(Integer id, String title, String description, MultipartFile image) {
        super(image);
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasimage() {
        return hasimage;
    }

    public void setHasimage(boolean hasimage) {
        this.hasimage = hasimage;
    }

    public List<Client> getClientsList() {
        return clientsList;
    }

    public void setClientsList(List<Client> clientsList) {
        this.clientsList = clientsList;
    }

    public List<ProductCategory> getProductcategoriesList() {
        return productcategoriesList;
    }

    public void setProductcategoriesList(List<ProductCategory> productcategoriesList) {
        this.productcategoriesList = productcategoriesList;
    }


    public List<OrderProduct> getOrdersProductsList() {
        return ordersProductsList;
    }

    public void setOrdersProductsList(List<OrderProduct> ordersProductsList) {
        this.ordersProductsList = ordersProductsList;
    }

    public List<StoreProduct> getStoresProductsList() {
        return storesProductsList;
    }

    public void setStoresProductsList(List<StoreProduct> storesProductsList) {
        this.storesProductsList = storesProductsList;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Products[ id=" + id + ", title=" + title + ", description=" + description + ", hasimage=" + hasimage + " ]";
    }

}
