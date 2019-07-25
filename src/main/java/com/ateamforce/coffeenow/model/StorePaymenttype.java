/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "stores_paymenttypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StorePaymenttype.findAll", query = "SELECT s FROM StorePaymenttype s")
    , @NamedQuery(name = "StorePaymenttype.findByStoreid", query = "SELECT s FROM StorePaymenttype s WHERE s.storePaymenttypePK.storeid = :storeid")
    , @NamedQuery(name = "StorePaymenttype.findByPaymenttypeid", query = "SELECT s FROM StorePaymenttype s WHERE s.storePaymenttypePK.paymenttypeid = :paymenttypeid")})
public class StorePaymenttype implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StorePaymenttypePK storePaymenttypePK;

    public StorePaymenttype() {
    }

    public StorePaymenttype(StorePaymenttypePK storePaymenttypePK) {
        this.storePaymenttypePK = storePaymenttypePK;
    }

    public StorePaymenttype(int storeid, int paymenttypeid) {
        this.storePaymenttypePK = new StorePaymenttypePK(storeid, paymenttypeid);
    }

    public StorePaymenttypePK getStorePaymenttypePK() {
        return storePaymenttypePK;
    }

    public void setStorePaymenttypePK(StorePaymenttypePK storePaymenttypePK) {
        this.storePaymenttypePK = storePaymenttypePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storePaymenttypePK != null ? storePaymenttypePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StorePaymenttype)) {
            return false;
        }
        StorePaymenttype other = (StorePaymenttype) object;
        if ((this.storePaymenttypePK == null && other.storePaymenttypePK != null) || (this.storePaymenttypePK != null && !this.storePaymenttypePK.equals(other.storePaymenttypePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StorePaymenttype[ storePaymenttypePK=" + storePaymenttypePK + " ]";
    }
    
}
