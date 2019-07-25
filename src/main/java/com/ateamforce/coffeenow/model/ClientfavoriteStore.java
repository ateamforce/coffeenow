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
@Table(name = "clientfavoritestores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientfavoriteStore.findAll", query = "SELECT c FROM ClientfavoriteStore c")
    , @NamedQuery(name = "ClientfavoriteStore.findByClientid", query = "SELECT c FROM ClientfavoriteStore c WHERE c.clientfavoriteStorePK.clientid = :clientid")
    , @NamedQuery(name = "ClientfavoriteStore.findByStoreid", query = "SELECT c FROM ClientfavoriteStore c WHERE c.clientfavoriteStorePK.storeid = :storeid")})
public class ClientfavoriteStore implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClientfavoriteStorePK clientfavoriteStorePK;

    public ClientfavoriteStore() {
    }

    public ClientfavoriteStore(ClientfavoriteStorePK clientfavoriteStorePK) {
        this.clientfavoriteStorePK = clientfavoriteStorePK;
    }

    public ClientfavoriteStore(int clientid, int storeid) {
        this.clientfavoriteStorePK = new ClientfavoriteStorePK(clientid, storeid);
    }

    public ClientfavoriteStorePK getClientfavoriteStorePK() {
        return clientfavoriteStorePK;
    }

    public void setClientfavoriteStorePK(ClientfavoriteStorePK clientfavoriteStorePK) {
        this.clientfavoriteStorePK = clientfavoriteStorePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientfavoriteStorePK != null ? clientfavoriteStorePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientfavoriteStore)) {
            return false;
        }
        ClientfavoriteStore other = (ClientfavoriteStore) object;
        if ((this.clientfavoriteStorePK == null && other.clientfavoriteStorePK != null) || (this.clientfavoriteStorePK != null && !this.clientfavoriteStorePK.equals(other.clientfavoriteStorePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ClientfavoriteStore[ clientfavoriteStorePK=" + clientfavoriteStorePK + " ]";
    }
    
}
