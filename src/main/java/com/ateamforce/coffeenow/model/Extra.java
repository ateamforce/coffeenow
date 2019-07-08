/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
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
@Table(name = "extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extra.findAll", query = "SELECT e FROM Extra e")
    , @NamedQuery(name = "Extra.findById", query = "SELECT e FROM Extra e WHERE e.id = :id")
    , @NamedQuery(name = "Extra.findByTitle", query = "SELECT e FROM Extra e WHERE e.title = :title")
    , @NamedQuery(name = "Extra.findByImage", query = "SELECT e FROM Extra e WHERE e.image = :image")})
public class Extra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image")
    private String image;
    @JoinTable(name = "extrascategories_extras", joinColumns = {
        @JoinColumn(name = "extraid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "categoryid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<ExtraCategory> extrascategoriesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "extras")
    private Collection<StoreExtra> storesExtrasCollection;
    @OneToMany(mappedBy = "extraid")
    private Collection<OrderProduct> ordersProductsCollection;

    public Extra() {
    }

    public Extra(Integer id) {
        this.id = id;
    }

    public Extra(Integer id, String title, String image) {
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
    public Collection<ExtraCategory> getExtrascategoriesCollection() {
        return extrascategoriesCollection;
    }

    public void setExtrascategoriesCollection(Collection<ExtraCategory> extrascategoriesCollection) {
        this.extrascategoriesCollection = extrascategoriesCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<StoreExtra> getStoresExtrasCollection() {
        return storesExtrasCollection;
    }

    public void setStoresExtrasCollection(Collection<StoreExtra> storesExtrasCollection) {
        this.storesExtrasCollection = storesExtrasCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<OrderProduct> getOrdersProductsCollection() {
        return ordersProductsCollection;
    }

    public void setOrdersProductsCollection(Collection<OrderProduct> ordersProductsCollection) {
        this.ordersProductsCollection = ordersProductsCollection;
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
        if (!(object instanceof Extra)) {
            return false;
        }
        Extra other = (Extra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Extras[ id=" + id + " ]";
    }
    
}