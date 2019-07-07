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
public class StoresExtrasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "storeid")
    private int storeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "extraid")
    private int extraid;

    public StoresExtrasPK() {
    }

    public StoresExtrasPK(int storeid, int extraid) {
        this.storeid = storeid;
        this.extraid = extraid;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getExtraid() {
        return extraid;
    }

    public void setExtraid(int extraid) {
        this.extraid = extraid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) storeid;
        hash += (int) extraid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoresExtrasPK)) {
            return false;
        }
        StoresExtrasPK other = (StoresExtrasPK) object;
        if (this.storeid != other.storeid) {
            return false;
        }
        if (this.extraid != other.extraid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StoresExtrasPK[ storeid=" + storeid + ", extraid=" + extraid + " ]";
    }
    
}
