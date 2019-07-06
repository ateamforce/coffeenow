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
public class RatingsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "clientid")
    private int clientid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storeid")
    private int storeid;

    public RatingsPK() {
    }

    public RatingsPK(int clientid, int storeid) {
        this.clientid = clientid;
        this.storeid = storeid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clientid;
        hash += (int) storeid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RatingsPK)) {
            return false;
        }
        RatingsPK other = (RatingsPK) object;
        if (this.clientid != other.clientid) {
            return false;
        }
        if (this.storeid != other.storeid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.RatingsPK[ clientid=" + clientid + ", storeid=" + storeid + " ]";
    }
    
}
