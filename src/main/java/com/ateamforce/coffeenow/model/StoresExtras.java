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
    @NamedQuery(name = "StoresExtras.findAll", query = "SELECT s FROM StoresExtras s")
    , @NamedQuery(name = "StoresExtras.findByStoreid", query = "SELECT s FROM StoresExtras s WHERE s.storesExtrasPK.storeid = :storeid")
    , @NamedQuery(name = "StoresExtras.findByExtraid", query = "SELECT s FROM StoresExtras s WHERE s.storesExtrasPK.extraid = :extraid")
    , @NamedQuery(name = "StoresExtras.findByPrice", query = "SELECT s FROM StoresExtras s WHERE s.price = :price")})
public class StoresExtras implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoresExtrasPK storesExtrasPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @JoinColumn(name = "extraid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Extras extras;
    @JoinColumn(name = "storeid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Stores stores;

    public StoresExtras() {
    }

    public StoresExtras(StoresExtrasPK storesExtrasPK) {
        this.storesExtrasPK = storesExtrasPK;
    }

    public StoresExtras(StoresExtrasPK storesExtrasPK, float price) {
        this.storesExtrasPK = storesExtrasPK;
        this.price = price;
    }

    public StoresExtras(int storeid, int extraid) {
        this.storesExtrasPK = new StoresExtrasPK(storeid, extraid);
    }

    public StoresExtrasPK getStoresExtrasPK() {
        return storesExtrasPK;
    }

    public void setStoresExtrasPK(StoresExtrasPK storesExtrasPK) {
        this.storesExtrasPK = storesExtrasPK;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
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
        hash += (storesExtrasPK != null ? storesExtrasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoresExtras)) {
            return false;
        }
        StoresExtras other = (StoresExtras) object;
        if ((this.storesExtrasPK == null && other.storesExtrasPK != null) || (this.storesExtrasPK != null && !this.storesExtrasPK.equals(other.storesExtrasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StoresExtras[ storesExtrasPK=" + storesExtrasPK + " ]";
    }
    
}
