/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "paymenttypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paymenttypes.findAll", query = "SELECT p FROM Paymenttypes p")
    , @NamedQuery(name = "Paymenttypes.findById", query = "SELECT p FROM Paymenttypes p WHERE p.id = :id")
    , @NamedQuery(name = "Paymenttypes.findByTitle", query = "SELECT p FROM Paymenttypes p WHERE p.title = :title")
    , @NamedQuery(name = "Paymenttypes.findByImage", query = "SELECT p FROM Paymenttypes p WHERE p.image = :image")})
public class Paymenttypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "image")
    private String image;
    @JoinTable(name = "stores_paymenttypes", joinColumns = {
        @JoinColumn(name = "paymenttypeid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "storeid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Stores> storesCollection;

    public Paymenttypes() {
    }

    public Paymenttypes(Integer id) {
        this.id = id;
    }

    public Paymenttypes(Integer id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Stores> getStoresCollection() {
        return storesCollection;
    }

    public void setStoresCollection(Collection<Stores> storesCollection) {
        this.storesCollection = storesCollection;
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
        if (!(object instanceof Paymenttypes)) {
            return false;
        }
        Paymenttypes other = (Paymenttypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Paymenttypes[ id=" + id + " ]";
    }
    
}
