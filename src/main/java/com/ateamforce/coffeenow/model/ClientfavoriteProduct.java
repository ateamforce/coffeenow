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
@Table(name = "clientfavoriteproducts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientfavoriteProduct.findAll", query = "SELECT c FROM ClientfavoriteProduct c")
    , @NamedQuery(name = "ClientfavoriteProduct.findByClientid", query = "SELECT c FROM ClientfavoriteProduct c WHERE c.clientfavoriteProductPK.clientid = :clientid")
    , @NamedQuery(name = "ClientfavoriteProduct.findByProductid", query = "SELECT c FROM ClientfavoriteProduct c WHERE c.clientfavoriteProductPK.productid = :productid")})
public class ClientfavoriteProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClientfavoriteProductPK clientfavoriteProductPK;

    public ClientfavoriteProduct() {
    }

    public ClientfavoriteProduct(ClientfavoriteProductPK clientfavoriteProductPK) {
        this.clientfavoriteProductPK = clientfavoriteProductPK;
    }

    public ClientfavoriteProduct(int clientid, int productid) {
        this.clientfavoriteProductPK = new ClientfavoriteProductPK(clientid, productid);
    }

    public ClientfavoriteProductPK getClientfavoriteProductPK() {
        return clientfavoriteProductPK;
    }

    public void setClientfavoriteProductPK(ClientfavoriteProductPK clientfavoriteProductPK) {
        this.clientfavoriteProductPK = clientfavoriteProductPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientfavoriteProductPK != null ? clientfavoriteProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientfavoriteProduct)) {
            return false;
        }
        ClientfavoriteProduct other = (ClientfavoriteProduct) object;
        if ((this.clientfavoriteProductPK == null && other.clientfavoriteProductPK != null) || (this.clientfavoriteProductPK != null && !this.clientfavoriteProductPK.equals(other.clientfavoriteProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ClientfavoriteProduct[ clientfavoriteProductPK=" + clientfavoriteProductPK + " ]";
    }
    
}
