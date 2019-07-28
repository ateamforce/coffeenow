/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@DiscriminatorValue("store")
@Table(name = "stores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
    , @NamedQuery(name = "Store.findById", query = "SELECT s FROM Store s WHERE s.id = :id")
    , @NamedQuery(name = "Store.findByVat", query = "SELECT s FROM Store s WHERE s.vat = :vat")
    , @NamedQuery(name = "Store.findByStorename", query = "SELECT s FROM Store s WHERE s.storename = :storename")
    , @NamedQuery(name = "Store.findByContactname", query = "SELECT s FROM Store s WHERE s.contactname = :contactname")
    , @NamedQuery(name = "Store.findByLogo", query = "SELECT s FROM Store s WHERE s.logo = :logo")
    , @NamedQuery(name = "Store.findByPhone", query = "SELECT s FROM Store s WHERE s.phone = :phone")
    , @NamedQuery(name = "Store.findByState", query = "SELECT s FROM Store s WHERE s.state = :state")
    , @NamedQuery(name = "Store.findByZip", query = "SELECT s FROM Store s WHERE s.zip = :zip")
    , @NamedQuery(name = "Store.findByLongitude", query = "SELECT s FROM Store s WHERE s.longitude = :longitude")
    , @NamedQuery(name = "Store.findByLatitude", query = "SELECT s FROM Store s WHERE s.latitude = :latitude")})
public class Store extends AppUser {

    private static final long serialVersionUID = 1L;

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

    @Transient
    private List<PaymentType> paymenttypesList;

    @Transient
    private List<StoreMedia> storemediaList;

    @Transient
    private List<Client> clientsList;

    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AppUser appusers;

    @Transient
    private List<StoreExtra> storesExtrasList;

    @Transient
    private List<StoreProduct> storesProductsList;

    @Transient
    private List<Rating> ratingsList;

    @Transient
    private List<AppOrder> ordersList;

    public Store() {
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
    public List<Client> getClientsList() {
        return clientsList;
    }

    public void setClientsList(List<Client> clientsList) {
        this.clientsList = clientsList;
    }

    @XmlTransient
    @JsonIgnore
    public List<PaymentType> getPaymenttypesList() {
        return paymenttypesList;
    }

    public void setPaymenttypesList(List<PaymentType> paymenttypesList) {
        this.paymenttypesList = paymenttypesList;
    }

    public AppUser getAppusers() {
        return appusers;
    }

    public void setAppusers(AppUser appusers) {
        this.appusers = appusers;
    }

    @XmlTransient
    @JsonIgnore
    public List<StoreExtra> getStoresExtrasList() {
        return storesExtrasList;
    }

    public void setStoresExtrasList(List<StoreExtra> storesExtrasList) {
        this.storesExtrasList = storesExtrasList;
    }

    @XmlTransient
    @JsonIgnore
    public List<StoreMedia> getStoremediaList() {
        return storemediaList;
    }

    public void setStoremediaList(List<StoreMedia> storemediaList) {
        this.storemediaList = storemediaList;
    }

    @XmlTransient
    @JsonIgnore
    public List<StoreProduct> getStoresProductsList() {
        return storesProductsList;
    }

    public void setStoresProductsList(List<StoreProduct> storesProductsList) {
        this.storesProductsList = storesProductsList;
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
}
