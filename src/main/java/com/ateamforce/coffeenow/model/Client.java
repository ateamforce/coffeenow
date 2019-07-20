/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
@DiscriminatorValue("client")
@Table(name = "clients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
    , @NamedQuery(name = "Client.findClientById", query = "SELECT c FROM Client c WHERE c.id = :clientId")
    , @NamedQuery(name = "Client.findByFirstname", query = "SELECT c FROM Client c WHERE c.firstname = :firstname")
    , @NamedQuery(name = "Client.findByLastname", query = "SELECT c FROM Client c WHERE c.lastname = :lastname")})
public class Client extends AppUser {

    private static final long serialVersionUID = 1L;
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
    private Collection<Product> productsCollection;
    @JoinTable(name = "clientfavoritestores", joinColumns = {
        @JoinColumn(name = "clientid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "storeid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Store> storesCollection;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AppUser appusers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clients")
    private Collection<Rating> ratingsCollection;
    @OneToMany(mappedBy = "clientid")
    private Collection<AppOrder> ordersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientid")
    private Collection<ClientMedia> clientmediaCollection;

    public Client() {
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
    public Collection<Product> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Product> productsCollection) {
        this.productsCollection = productsCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Store> getStoresCollection() {
        return storesCollection;
    }

    public void setStoresCollection(Collection<Store> storesCollection) {
        this.storesCollection = storesCollection;
    }

    public AppUser getAppusers() {
        return appusers;
    }

    public void setAppusers(AppUser appusers) {
        this.appusers = appusers;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Rating> getRatingsCollection() {
        return ratingsCollection;
    }

    public void setRatingsCollection(Collection<Rating> ratingsCollection) {
        this.ratingsCollection = ratingsCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<AppOrder> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<AppOrder> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ClientMedia> getClientmediaCollection() {
        return clientmediaCollection;
    }

    public void setClientmediaCollection(Collection<ClientMedia> clientmediaCollection) {
        this.clientmediaCollection = clientmediaCollection;
    }

    
}
