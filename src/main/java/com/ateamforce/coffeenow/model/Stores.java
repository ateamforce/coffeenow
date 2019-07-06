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
import javax.persistence.Lob;
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
@Table(name = "stores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stores.findAll", query = "SELECT s FROM Stores s")
    , @NamedQuery(name = "Stores.findById", query = "SELECT s FROM Stores s WHERE s.id = :id")
    , @NamedQuery(name = "Stores.findByVat", query = "SELECT s FROM Stores s WHERE s.vat = :vat")
    , @NamedQuery(name = "Stores.findByStorename", query = "SELECT s FROM Stores s WHERE s.storename = :storename")
    , @NamedQuery(name = "Stores.findByContactname", query = "SELECT s FROM Stores s WHERE s.contactname = :contactname")
    , @NamedQuery(name = "Stores.findByLogo", query = "SELECT s FROM Stores s WHERE s.logo = :logo")
    , @NamedQuery(name = "Stores.findByPhone", query = "SELECT s FROM Stores s WHERE s.phone = :phone")
    , @NamedQuery(name = "Stores.findByState", query = "SELECT s FROM Stores s WHERE s.state = :state")
    , @NamedQuery(name = "Stores.findByZip", query = "SELECT s FROM Stores s WHERE s.zip = :zip")
    , @NamedQuery(name = "Stores.findByLongitude", query = "SELECT s FROM Stores s WHERE s.longitude = :longitude")
    , @NamedQuery(name = "Stores.findByLatitude", query = "SELECT s FROM Stores s WHERE s.latitude = :latitude")})
public class Stores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vat")
    private long vat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "storename")
    private String storename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "contactname")
    private String contactname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "logo")
    private String logo;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zip")
    private int zip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @ManyToMany(mappedBy = "storesCollection")
    private Collection<Clients> clientsCollection;
    @ManyToMany(mappedBy = "storesCollection")
    private Collection<Paymenttypes> paymenttypesCollection;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Appusers appusers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stores")
    private Collection<StoresExtras> storesExtrasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeid")
    private Collection<Storemedia> storemediaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stores")
    private Collection<StoresProducts> storesProductsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stores")
    private Collection<Ratings> ratingsCollection;
    @OneToMany(mappedBy = "storeid")
    private Collection<Orders> ordersCollection;

    public Stores() {
    }

    public Stores(Integer id) {
        this.id = id;
    }

    public Stores(Integer id, long vat, String storename, String contactname, String logo, String phone, String address, String state, int zip, double longitude, double latitude) {
        this.id = id;
        this.vat = vat;
        this.storename = storename;
        this.contactname = contactname;
        this.logo = logo;
        this.phone = phone;
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getVat() {
        return vat;
    }

    public void setVat(long vat) {
        this.vat = vat;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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
    public Collection<Paymenttypes> getPaymenttypesCollection() {
        return paymenttypesCollection;
    }

    public void setPaymenttypesCollection(Collection<Paymenttypes> paymenttypesCollection) {
        this.paymenttypesCollection = paymenttypesCollection;
    }

    public Appusers getAppusers() {
        return appusers;
    }

    public void setAppusers(Appusers appusers) {
        this.appusers = appusers;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<StoresExtras> getStoresExtrasCollection() {
        return storesExtrasCollection;
    }

    public void setStoresExtrasCollection(Collection<StoresExtras> storesExtrasCollection) {
        this.storesExtrasCollection = storesExtrasCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Storemedia> getStoremediaCollection() {
        return storemediaCollection;
    }

    public void setStoremediaCollection(Collection<Storemedia> storemediaCollection) {
        this.storemediaCollection = storemediaCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<StoresProducts> getStoresProductsCollection() {
        return storesProductsCollection;
    }

    public void setStoresProductsCollection(Collection<StoresProducts> storesProductsCollection) {
        this.storesProductsCollection = storesProductsCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stores)) {
            return false;
        }
        Stores other = (Stores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Stores[ id=" + id + " ]";
    }
    
}
