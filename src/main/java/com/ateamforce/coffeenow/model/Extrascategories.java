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
@Table(name = "extrascategories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extrascategories.findAll", query = "SELECT e FROM Extrascategories e")
    , @NamedQuery(name = "Extrascategories.findById", query = "SELECT e FROM Extrascategories e WHERE e.id = :id")
    , @NamedQuery(name = "Extrascategories.findByTitle", query = "SELECT e FROM Extrascategories e WHERE e.title = :title")
    , @NamedQuery(name = "Extrascategories.findByParent", query = "SELECT e FROM Extrascategories e WHERE e.parent = :parent")
    , @NamedQuery(name = "Extrascategories.findByImage", query = "SELECT e FROM Extrascategories e WHERE e.image = :image")})
public class Extrascategories implements Serializable {

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
    @Column(name = "parent")
    private int parent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image")
    private String image;
    @ManyToMany(mappedBy = "extrascategoriesCollection")
    private Collection<Extras> extrasCollection;
    @JoinTable(name = "extras_products", joinColumns = {
        @JoinColumn(name = "extracategoryid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "productcategoryid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Productcategories> productcategoriesCollection;

    public Extrascategories() {
    }

    public Extrascategories(Integer id) {
        this.id = id;
    }

    public Extrascategories(Integer id, String title, int parent, String image) {
        this.id = id;
        this.title = title;
        this.parent = parent;
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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Extras> getExtrasCollection() {
        return extrasCollection;
    }

    public void setExtrasCollection(Collection<Extras> extrasCollection) {
        this.extrasCollection = extrasCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Productcategories> getProductcategoriesCollection() {
        return productcategoriesCollection;
    }

    public void setProductcategoriesCollection(Collection<Productcategories> productcategoriesCollection) {
        this.productcategoriesCollection = productcategoriesCollection;
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
        if (!(object instanceof Extrascategories)) {
            return false;
        }
        Extrascategories other = (Extrascategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Extrascategories[ id=" + id + " ]";
    }
    
}
