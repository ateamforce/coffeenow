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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "stores_extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreExtra.findAll", query = "SELECT s FROM StoreExtra s")
    , @NamedQuery(name = "StoreExtra.findByStoreid", query = "SELECT s FROM StoreExtra s WHERE s.storeExtraPK.storeid = :storeid")
    , @NamedQuery(name = "StoreExtra.findByExtraid", query = "SELECT s FROM StoreExtra s WHERE s.storeExtraPK.extraid = :extraid")
    , @NamedQuery(name = "StoreExtra.findByPrice", query = "SELECT s FROM StoreExtra s WHERE s.price = :price")})
public class StoreExtra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreExtraPK storeExtraPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @JoinColumn(name = "extraid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Extra extras;
    @JoinColumn(name = "storeid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store stores;

    public StoreExtra() {
    }

    public StoreExtra(StoreExtraPK storeExtraPK) {
        this.storeExtraPK = storeExtraPK;
    }

    public StoreExtra(StoreExtraPK storeExtraPK, float price) {
        this.storeExtraPK = storeExtraPK;
        this.price = price;
    }

    public StoreExtra(int storeid, int extraid) {
        this.storeExtraPK = new StoreExtraPK(storeid, extraid);
    }

    public StoreExtraPK getStoreExtraPK() {
        return storeExtraPK;
    }

    public void setStoreExtraPK(StoreExtraPK storeExtraPK) {
        this.storeExtraPK = storeExtraPK;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Extra getExtras() {
        return extras;
    }

    public void setExtras(Extra extras) {
        this.extras = extras;
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
        hash += (storeExtraPK != null ? storeExtraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreExtra)) {
            return false;
        }
        StoreExtra other = (StoreExtra) object;
        if ((this.storeExtraPK == null && other.storeExtraPK != null) || (this.storeExtraPK != null && !this.storeExtraPK.equals(other.storeExtraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StoresExtra[ storeExtraPK=" + storeExtraPK + " ]";
    }
    
}
