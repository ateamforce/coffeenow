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
@Table(name = "storemedia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Storemedia.findAll", query = "SELECT s FROM Storemedia s")
    , @NamedQuery(name = "Storemedia.findById", query = "SELECT s FROM StoreMedia s WHERE s.id = :id")
    , @NamedQuery(name = "Storemedia.findByFilename", query = "SELECT s FROM StoreMedia s WHERE s.filename = :filename")})
public class StoreMedia implements Serializable {

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
    @JoinColumn(name = "storeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Store storeid;

    public StoreMedia() {
    }

    public StoreMedia(Integer id) {
        this.id = id;
    }

    public StoreMedia(Integer id, String filename) {
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

    public Store getStoreid() {
        return storeid;
    }

    public void setStoreid(Store storeid) {
        this.storeid = storeid;
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
        if (!(object instanceof StoreMedia)) {
            return false;
        }
        StoreMedia other = (StoreMedia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Storemedia[ id=" + id + " ]";
    }
    
}
