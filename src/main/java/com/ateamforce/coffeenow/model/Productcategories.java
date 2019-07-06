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
@Table(name = "productcategories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productcategories.findAll", query = "SELECT p FROM Productcategories p")
    , @NamedQuery(name = "Productcategories.findById", query = "SELECT p FROM Productcategories p WHERE p.id = :id")
    , @NamedQuery(name = "Productcategories.findByTitle", query = "SELECT p FROM Productcategories p WHERE p.title = :title")
    , @NamedQuery(name = "Productcategories.findByParent", query = "SELECT p FROM Productcategories p WHERE p.parent = :parent")
    , @NamedQuery(name = "Productcategories.findByImage", query = "SELECT p FROM Productcategories p WHERE p.image = :image")})
public class Productcategories implements Serializable {

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
    @ManyToMany(mappedBy = "productcategoriesCollection")
    private Collection<Products> productsCollection;
    @ManyToMany(mappedBy = "productcategoriesCollection")
    private Collection<Extrascategories> extrascategoriesCollection;

    public Productcategories() {
    }

    public Productcategories(Integer id) {
        this.id = id;
    }

    public Productcategories(Integer id, String title, int parent, String image) {
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
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Extrascategories> getExtrascategoriesCollection() {
        return extrascategoriesCollection;
    }

    public void setExtrascategoriesCollection(Collection<Extrascategories> extrascategoriesCollection) {
        this.extrascategoriesCollection = extrascategoriesCollection;
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
        if (!(object instanceof Productcategories)) {
            return false;
        }
        Productcategories other = (Productcategories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Productcategories[ id=" + id + " ]";
    }
    
}
