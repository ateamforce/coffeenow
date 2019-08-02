/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "stores_extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreExtra.findAll", query = "SELECT s FROM StoreExtra s")
    , @NamedQuery(name = "StoreExtra.findById", query = "SELECT s FROM StoreExtra s WHERE s.id = :id")
    , @NamedQuery(name = "StoreExtra.findByStoreid", query = "SELECT s FROM StoreExtra s WHERE s.store.id = :storeid")
    , @NamedQuery(name = "StoreExtra.findByPrice", query = "SELECT s FROM StoreExtra s WHERE s.price = :price")})
public class StoreExtra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "storeid", referencedColumnName = "id")
    @ManyToOne
    private Store store;
    @JoinColumn(name = "extraid", referencedColumnName = "id")
    @ManyToOne
    private Extra extra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;
    @Transient
    private List<OrderProduct> orderProductList;

    public StoreExtra() {
    }

    public StoreExtra(Integer id) {
        this.id = id;
    }

    public StoreExtra(Integer id, float price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }
    
    

    @XmlTransient
    @JsonIgnore
    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
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
        if (!(object instanceof StoreExtra)) {
            return false;
        }
        StoreExtra other = (StoreExtra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StoreExtra[ id=" + id + " ]";
    }
    
}
