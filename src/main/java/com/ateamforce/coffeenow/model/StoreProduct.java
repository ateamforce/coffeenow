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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "stores_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreProduct.findAll", query = "SELECT s FROM StoreProduct s")
    , @NamedQuery(name = "StoreProduct.findById", query = "SELECT s FROM StoreProduct s WHERE s.id = :id")
    , @NamedQuery(name = "StoreProduct.findByStoreid", query = "SELECT s FROM StoreProduct s WHERE s.storeid = :storeid")
    , @NamedQuery(name = "StoreProduct.findByProductid", query = "SELECT s FROM StoreProduct s WHERE s.productid = :productid")
    , @NamedQuery(name = "StoreProduct.findByPrice", query = "SELECT s FROM StoreProduct s WHERE s.price = :price")
    , @NamedQuery(name = "StoreProduct.findByDiscount", query = "SELECT s FROM StoreProduct s WHERE s.discount = :discount")
    , @NamedQuery(name = "StoreProduct.findByHasimage", query = "SELECT s FROM StoreProduct s WHERE s.hasimage = :hasimage")})
public class StoreProduct extends _ImageCarrier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storeid")
    private int storeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productid")
    private int productid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private int discount;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "hasimage")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean hasimage;
    @Transient
    private List<OrderProduct> orderProductList;

    public StoreProduct() {
    }

    public StoreProduct(Integer id) {
        this.id = id;
    }

    public StoreProduct(Integer id, int storeid, int productid, float price, int discount, String description, boolean hasimage) {
        this.id = id;
        this.storeid = storeid;
        this.productid = productid;
        this.price = price;
        this.discount = discount;
        this.description = description;
        this.hasimage = hasimage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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


    @XmlTransient
    @JsonIgnore
    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
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
        if (!(object instanceof StoreProduct)) {
            return false;
        }
        StoreProduct other = (StoreProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StoreProduct[ id=" + id + " ]";
    }
    
}
