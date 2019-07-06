/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "clientmedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientmedia.findAll", query = "SELECT c FROM Clientmedia c")
    , @NamedQuery(name = "Clientmedia.findById", query = "SELECT c FROM Clientmedia c WHERE c.id = :id")
    , @NamedQuery(name = "Clientmedia.findByFilename", query = "SELECT c FROM Clientmedia c WHERE c.filename = :filename")})
public class Clientmedia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "filename")
    private String filename;
    @JoinColumn(name = "clientid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Clients clientid;

    public Clientmedia() {
    }

    public Clientmedia(Integer id) {
        this.id = id;
    }

    public Clientmedia(Integer id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Clients getClientid() {
        return clientid;
    }

    public void setClientid(Clients clientid) {
        this.clientid = clientid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientmedia)) {
            return false;
        }
        Clientmedia other = (Clientmedia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Clientmedia[ id=" + id + " ]";
    }
    
}
