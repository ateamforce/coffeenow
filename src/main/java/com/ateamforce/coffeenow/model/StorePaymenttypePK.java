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
public class StorePaymenttypePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "storeid")
    private int storeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paymenttypeid")
    private int paymenttypeid;

    public StorePaymenttypePK() {
    }

    public StorePaymenttypePK(int storeid, int paymenttypeid) {
        this.storeid = storeid;
        this.paymenttypeid = paymenttypeid;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getPaymenttypeid() {
        return paymenttypeid;
    }

    public void setPaymenttypeid(int paymenttypeid) {
        this.paymenttypeid = paymenttypeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) storeid;
        hash += (int) paymenttypeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StorePaymenttypePK)) {
            return false;
        }
        StorePaymenttypePK other = (StorePaymenttypePK) object;
        if (this.storeid != other.storeid) {
            return false;
        }
        if (this.paymenttypeid != other.paymenttypeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StorePaymenttypePK[ storeid=" + storeid + ", paymenttypeid=" + paymenttypeid + " ]";
    }
    
}
