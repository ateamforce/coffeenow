/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "stores_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreProduct.findAll", query = "SELECT s FROM StoreProduct s")
    , @NamedQuery(name = "StoreProduct.findByStoreid", query = "SELECT s FROM StoreProduct s WHERE s.storeProductPK.storeid = :storeid")
    , @NamedQuery(name = "StoreProduct.findByProductid", query = "SELECT s FROM StoreProduct s WHERE s.storeProductPK.productid = :productid")
    , @NamedQuery(name = "StoreProduct.findByPrice", query = "SELECT s FROM StoreProduct s WHERE s.price = :price")
    , @NamedQuery(name = "StoreProduct.findByDiscount", query = "SELECT s FROM StoreProduct s WHERE s.discount = :discount")})
public class StoreProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreProductPK storeProductPK;
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
    @JoinColumn(name = "productid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product products;
    @JoinColumn(name = "storeid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store stores;

    public StoreProduct() {
    }

    public StoreProduct(StoreProductPK storeProductPK) {
        this.storeProductPK = storeProductPK;
    }

    public StoreProduct(StoreProductPK storeProductPK, float price, int discount, String description) {
        this.storeProductPK = storeProductPK;
        this.price = price;
        this.discount = discount;
        this.description = description;
    }

    public StoreProduct(int storeid, int productid) {
        this.storeProductPK = new StoreProductPK(storeid, productid);
    }

    public StoreProductPK getStoreProductPK() {
        return storeProductPK;
    }

    public void setStoreProductPK(StoreProductPK storeProductPK) {
        this.storeProductPK = storeProductPK;
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

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Store getStores() {
        return stores;
    }

    public void setStores(Store stores) {
        this.stores = stores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeProductPK != null ? storeProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreProduct)) {
            return false;
        }
        StoreProduct other = (StoreProduct) object;
        if ((this.storeProductPK == null && other.storeProductPK != null) || (this.storeProductPK != null && !this.storeProductPK.equals(other.storeProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StoreProduct[ storeProductPK=" + storeProductPK + " ]";
    }
    
}
