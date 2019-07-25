/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.util.Collection;
import java.util.List;
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
import javax.persistence.Transient;
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
    @Transient
    private List<Product> productsList;
    @Transient
    private List<Store> storesList;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AppUser appusers;
    @Transient
    private List<Rating> ratingsList;
    @Transient
    private List<AppOrder> ordersList;
    @Transient
    private List<ClientMedia> clientmediaList;

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
    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Store> getStoresList() {
        return storesList;
    }

    public void setStoresList(List<Store> storesList) {
        this.storesList = storesList;
    }

    public AppUser getAppusers() {
        return appusers;
    }

    public void setAppusers(AppUser appusers) {
        this.appusers = appusers;
    }

    @XmlTransient
    @JsonIgnore
    public List<Rating> getRatingsList() {
        return ratingsList;
    }

    public void setRatingsList(List<Rating> ratingsList) {
        this.ratingsList = ratingsList;
    }

    @XmlTransient
    @JsonIgnore
    public List<AppOrder> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<AppOrder> ordersList) {
        this.ordersList = ordersList;
    }

    @XmlTransient
    @JsonIgnore
    public List<ClientMedia> getClientmediaList() {
        return clientmediaList;
    }

    public void setClientmediaList(List<ClientMedia> clientmediaList) {
        this.clientmediaList = clientmediaList;
    }

    
}
