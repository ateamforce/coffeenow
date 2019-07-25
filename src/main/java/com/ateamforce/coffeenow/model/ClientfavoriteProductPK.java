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
public class ClientfavoriteProductPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "clientid")
    private int clientid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productid")
    private int productid;

    public ClientfavoriteProductPK() {
    }

    public ClientfavoriteProductPK(int clientid, int productid) {
        this.clientid = clientid;
        this.productid = productid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
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
        hash += (int) clientid;
        hash += (int) productid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientfavoriteProductPK)) {
            return false;
        }
        ClientfavoriteProductPK other = (ClientfavoriteProductPK) object;
        if (this.clientid != other.clientid) {
            return false;
        }
        if (this.productid != other.productid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ClientfavoriteProductPK[ clientid=" + clientid + ", productid=" + productid + " ]";
    }
    
}
