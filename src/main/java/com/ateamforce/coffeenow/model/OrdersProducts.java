/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "orders_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersProducts.findAll", query = "SELECT o FROM OrdersProducts o")
    , @NamedQuery(name = "OrdersProducts.findById", query = "SELECT o FROM OrdersProducts o WHERE o.id = :id")})
public class OrdersProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "extraid", referencedColumnName = "id")
    @ManyToOne
    private Extras extraid;
    @JoinColumn(name = "orderid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Orders orderid;
    @JoinColumn(name = "productid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Products productid;

    public OrdersProducts() {
    }

    public OrdersProducts(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Extras getExtraid() {
        return extraid;
    }

    public void setExtraid(Extras extraid) {
        this.extraid = extraid;
    }

    public Orders getOrderid() {
        return orderid;
    }

    public void setOrderid(Orders orderid) {
        this.orderid = orderid;
    }

    public Products getProductid() {
        return productid;
    }

    public void setProductid(Products productid) {
        this.productid = productid;
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
        if (!(object instanceof OrdersProducts)) {
            return false;
        }
        OrdersProducts other = (OrdersProducts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.OrdersProducts[ id=" + id + " ]";
    }
    
}
