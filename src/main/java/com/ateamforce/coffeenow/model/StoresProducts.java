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
    @NamedQuery(name = "StoresProducts.findAll", query = "SELECT s FROM StoresProducts s")
    , @NamedQuery(name = "StoresProducts.findByStoreid", query = "SELECT s FROM StoresProducts s WHERE s.storesProductsPK.storeid = :storeid")
    , @NamedQuery(name = "StoresProducts.findByProductid", query = "SELECT s FROM StoresProducts s WHERE s.storesProductsPK.productid = :productid")
    , @NamedQuery(name = "StoresProducts.findByPrice", query = "SELECT s FROM StoresProducts s WHERE s.price = :price")
    , @NamedQuery(name = "StoresProducts.findByDiscount", query = "SELECT s FROM StoresProducts s WHERE s.discount = :discount")})
public class StoresProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoresProductsPK storesProductsPK;
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
    private Products products;
    @JoinColumn(name = "storeid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Stores stores;

    public StoresProducts() {
    }

    public StoresProducts(StoresProductsPK storesProductsPK) {
        this.storesProductsPK = storesProductsPK;
    }

    public StoresProducts(StoresProductsPK storesProductsPK, float price, int discount, String description) {
        this.storesProductsPK = storesProductsPK;
        this.price = price;
        this.discount = discount;
        this.description = description;
    }

    public StoresProducts(int storeid, int productid) {
        this.storesProductsPK = new StoresProductsPK(storeid, productid);
    }

    public StoresProductsPK getStoresProductsPK() {
        return storesProductsPK;
    }

    public void setStoresProductsPK(StoresProductsPK storesProductsPK) {
        this.storesProductsPK = storesProductsPK;
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

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storesProductsPK != null ? storesProductsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoresProducts)) {
            return false;
        }
        StoresProducts other = (StoresProducts) object;
        if ((this.storesProductsPK == null && other.storesProductsPK != null) || (this.storesProductsPK != null && !this.storesProductsPK.equals(other.storesProductsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StoresProducts[ storesProductsPK=" + storesProductsPK + " ]";
    }
    
}
