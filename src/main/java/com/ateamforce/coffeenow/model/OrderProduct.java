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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "orders_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderProduct.findAll", query = "SELECT o FROM OrderProduct o")
    , @NamedQuery(name = "OrderProduct.findById", query = "SELECT o FROM OrderProduct o WHERE o.id = :id")
    , @NamedQuery(name = "OrderProduct.findByProductprice", query = "SELECT o FROM OrderProduct o WHERE o.productprice = :productprice")
    , @NamedQuery(name = "OrderProduct.findByProductdiscount", query = "SELECT o FROM OrderProduct o WHERE o.productdiscount = :productdiscount")
    , @NamedQuery(name = "OrderProduct.findByExtraprice", query = "SELECT o FROM OrderProduct o WHERE o.extraprice = :extraprice")})
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productprice")
    private float productprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productdiscount")
    private int productdiscount;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "extraprice")
    private Float extraprice;
    @JoinColumn(name = "orderid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AppOrder appOrder;
    @JoinColumn(name = "storeextraid", referencedColumnName = "id")
    @ManyToOne
    private StoreExtra storeextraid;
    @JoinColumn(name = "stroreproductid", referencedColumnName = "id")
    @ManyToOne
    private StoreProduct stroreproductid;

    public OrderProduct() {
    }

    public OrderProduct(Integer id) {
        this.id = id;
    }

    public OrderProduct(Integer id, float productprice, int productdiscount) {
        this.id = id;
        this.productprice = productprice;
        this.productdiscount = productdiscount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getProductprice() {
        return productprice;
    }

    public void setProductprice(float productprice) {
        this.productprice = productprice;
    }

    public int getProductdiscount() {
        return productdiscount;
    }

    public void setProductdiscount(int productdiscount) {
        this.productdiscount = productdiscount;
    }

    public Float getExtraprice() {
        return extraprice;
    }

    public void setExtraprice(Float extraprice) {
        this.extraprice = extraprice;
    }

    public AppOrder getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(AppOrder appOrder) {
        this.appOrder = appOrder;
    }

    public StoreExtra getStoreextraid() {
        return storeextraid;
    }

    public void setStoreextraid(StoreExtra storeextraid) {
        this.storeextraid = storeextraid;
    }

    public StoreProduct getStroreproductid() {
        return stroreproductid;
    }

    public void setStroreproductid(StoreProduct stroreproductid) {
        this.stroreproductid = stroreproductid;
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
        if (!(object instanceof OrderProduct)) {
            return false;
        }
        OrderProduct other = (OrderProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.OrderProduct[ id=" + id + " ]";
    }
    
}
