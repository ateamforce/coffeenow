/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexa
 */
@Embeddable
public class StoresProductsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "storeid")
    private int storeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productid")
    private int productid;

    public StoresProductsPK() {
    }

    public StoresProductsPK(int storeid, int productid) {
        this.storeid = storeid;
        this.productid = productid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) storeid;
        hash += (int) productid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoresProductsPK)) {
            return false;
        }
        StoresProductsPK other = (StoresProductsPK) object;
        if (this.storeid != other.storeid) {
            return false;
        }
        if (this.productid != other.productid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StoresProductsPK[ storeid=" + storeid + ", productid=" + productid + " ]";
    }
    
}
