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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "clients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT c FROM Clients c")
    , @NamedQuery(name = "Clients.findById", query = "SELECT c FROM Clients c WHERE c.id = :id")
    , @NamedQuery(name = "Clients.findByFirstname", query = "SELECT c FROM Clients c WHERE c.firstname = :firstname")
    , @NamedQuery(name = "Clients.findByLastname", query = "SELECT c FROM Clients c WHERE c.lastname = :lastname")})
public class Clients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lastname")
    private String lastname;
    @JoinTable(name = "clientfavoriteproducts", joinColumns = {
        @JoinColumn(name = "clientid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "productid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Products> productsCollection;
    @JoinTable(name = "clientfavoritestores", joinColumns = {
        @JoinColumn(name = "clientid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "storeid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Stores> storesCollection;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Appusers appusers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clients")
    private Collection<Ratings> ratingsCollection;
    @OneToMany(mappedBy = "clientid")
    private Collection<Orders> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientid")
    private Collection<Clientmedia> clientmediaCollection;

    public Clients() {
    }

    public Clients(Integer id) {
        this.id = id;
    }

    public Clients(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    public Collection<Stores> getStoresCollection() {
        return storesCollection;
    }

    public void setStoresCollection(Collection<Stores> storesCollection) {
        this.storesCollection = storesCollection;
    }

    public Appusers getAppusers() {
        return appusers;
    }

    public void setAppusers(Appusers appusers) {
        this.appusers = appusers;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Ratings> getRatingsCollection() {
        return ratingsCollection;
    }

    public void setRatingsCollection(Collection<Ratings> ratingsCollection) {
        this.ratingsCollection = ratingsCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Clientmedia> getClientmediaCollection() {
        return clientmediaCollection;
    }

    public void setClientmediaCollection(Collection<Clientmedia> clientmediaCollection) {
        this.clientmediaCollection = clientmediaCollection;
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
        if (!(object instanceof Clients)) {
            return false;
        }
        Clients other = (Clients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Clients[ id=" + id + " ]";
    }
    
}
