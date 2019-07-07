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
import javax.persistence.Lob;
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
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id")
    , @NamedQuery(name = "Products.findByTitle", query = "SELECT p FROM Products p WHERE p.title = :title")
    , @NamedQuery(name = "Products.findByImage", query = "SELECT p FROM Products p WHERE p.image = :image")})
public class Products implements Serializable {

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
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image")
    private String image;
    @ManyToMany(mappedBy = "productsCollection")
    private Collection<Clients> clientsCollection;
    @JoinTable(name = "productcategories_products", joinColumns = {
        @JoinColumn(name = "productid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "categoryid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Productcategories> productcategoriesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productid")
    private Collection<OrdersProducts> ordersProductsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Collection<StoresProducts> storesProductsCollection;

    public Products() {
    }

    public Products(Integer id) {
        this.id = id;
    }

    public Products(Integer id, String title, String description, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Clients> getClientsCollection() {
        return clientsCollection;
    }

    public void setClientsCollection(Collection<Clients> clientsCollection) {
        this.clientsCollection = clientsCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Productcategories> getProductcategoriesCollection() {
        return productcategoriesCollection;
    }

    public void setProductcategoriesCollection(Collection<Productcategories> productcategoriesCollection) {
        this.productcategoriesCollection = productcategoriesCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<OrdersProducts> getOrdersProductsCollection() {
        return ordersProductsCollection;
    }

    public void setOrdersProductsCollection(Collection<OrdersProducts> ordersProductsCollection) {
        this.ordersProductsCollection = ordersProductsCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<StoresProducts> getStoresProductsCollection() {
        return storesProductsCollection;
    }

    public void setStoresProductsCollection(Collection<StoresProducts> storesProductsCollection) {
        this.storesProductsCollection = storesProductsCollection;
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
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Products[ id=" + id + " ]";
    }
    
}
