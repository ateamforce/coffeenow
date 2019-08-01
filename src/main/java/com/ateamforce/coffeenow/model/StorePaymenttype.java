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
@Table(name = "stores_paymenttypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StorePaymenttype.findAll", query = "SELECT s FROM StorePaymenttype s")
    ,@NamedQuery(name = "StorePaymenttype.findByStoreid", query = "SELECT s FROM StorePaymenttype s WHERE s.store.id = :storeid")
    , @NamedQuery(name = "StorePaymenttype.findById", query = "SELECT s FROM StorePaymenttype s WHERE s.id = :id")})
public class StorePaymenttype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "stroreid", referencedColumnName = "id")
    @ManyToOne
    private Store store;
    @JoinColumn(name = "paymenttypeid", referencedColumnName = "id")
    @ManyToOne
    private PaymentType paymentType;
    @Transient
    private List<AppOrder> appOrderList;

    public StorePaymenttype() {
    }

    public StorePaymenttype(Integer id) {
        this.id = id;
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    
    

    @XmlTransient
    @JsonIgnore
    public List<AppOrder> getAppOrderList() {
        return appOrderList;
    }

    public void setAppOrderList(List<AppOrder> appOrderList) {
        this.appOrderList = appOrderList;
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
        if (!(object instanceof StorePaymenttype)) {
            return false;
        }
        StorePaymenttype other = (StorePaymenttype) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.StorePaymenttype[ id=" + id + " ]";
    }
    
}
