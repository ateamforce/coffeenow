/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Pattern;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppOrder.findAll", query = "SELECT a FROM AppOrder a")
    , @NamedQuery(name = "AppOrder.findById", query = "SELECT a FROM AppOrder a WHERE a.id = :id")
    , @NamedQuery(name = "AppOrder.findByStore", query = "SELECT a FROM AppOrder a WHERE a.store.id = :storeid")
    , @NamedQuery(name = "AppOrder.findByClient", query = "SELECT a FROM AppOrder a WHERE a.client.id = :clientid")
    , @NamedQuery(name = "AppOrder.findByMode", query = "SELECT a FROM AppOrder a WHERE a.mode = :mode")
    , @NamedQuery(name = "AppOrder.findByTotal", query = "SELECT a FROM AppOrder a WHERE a.total = :total")
    , @NamedQuery(name = "AppOrder.findByDate", query = "SELECT a FROM AppOrder a WHERE a.date = :date")
    , @NamedQuery(name = "AppOrder.findByStatus", query = "SELECT a FROM AppOrder a WHERE a.status = :status")})
public class AppOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "storeid", referencedColumnName = "id")
    @ManyToOne
    private Store store;
    @JoinColumn(name = "clientid", referencedColumnName = "id")
    @ManyToOne
    private Client client;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mode")
    private String mode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private float total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Transient
    private List<OrderProduct> ordersProductsList;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "storepaymenttypeid", referencedColumnName = "id")
    @ManyToOne
    private StorePaymenttype storepaymenttypeid;
    @Transient
    private List<OrderProduct> orderProductList;

    public AppOrder() {
    }

    public AppOrder(Integer id) {
        this.id = id;
    }

    public AppOrder(Integer id, String mode, float total, Date date, String status) {
        this.id = id;
        this.mode = mode;
        this.total = total;
        this.date = date;
        this.status = status;
    }

    public AppOrder(Integer id, Store store, Client client, String mode, float total, Date date, String status, StorePaymenttype storepaymenttypeid, List<OrderProduct> orderProductList) {
        this.id = id;
        this.store = store;
        this.client = client;
        this.mode = mode;
        this.total = total;
        this.date = date;
        this.status = status;
        this.storepaymenttypeid = storepaymenttypeid;
        this.orderProductList = orderProductList;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Store getStoreid() {
        return store;
    }

    public void setStoreid(Store store) {
        this.store = store;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderProduct> getOrdersProductsList() {
        return ordersProductsList;
    }

    public void setOrdersProductsList(List<OrderProduct> ordersProductsList) {
        this.ordersProductsList = ordersProductsList;
    }

    public StorePaymenttype getStorepaymenttypeid() {
        return storepaymenttypeid;
    }

    public void setStorepaymenttypeid(StorePaymenttype storepaymenttypeid) {
        this.storepaymenttypeid = storepaymenttypeid;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }
    
    

    
}
