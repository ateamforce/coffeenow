/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "paymenttypes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentType.findAllPaymentTypes", query = "SELECT p FROM PaymentType p")
    , @NamedQuery(name = "PaymentType.findPaymentTypeById", query = "SELECT p FROM PaymentType p WHERE p.id = :paymentTypeId")
    , @NamedQuery(name = "PaymentType.findByTitle", query = "SELECT p FROM PaymentType p WHERE p.title = :title")})
public class PaymentType extends _ImageCarrier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 50, message = "{title.size.restriction.message}")
    @Column(name = "title")
    private String title;
    @Column(name = "hasimage")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean hasimage;
    @JoinTable(name = "stores_paymenttypes", joinColumns = {
        @JoinColumn(name = "paymenttypeid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "storeid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Store> storesCollection;

    public PaymentType() {
    }

    public PaymentType(Integer id) {
        this.id = id;
    }

    public PaymentType(Integer id, String title, MultipartFile image) {
        super(image);
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasimage() {
        return hasimage;
    }

    public void setHasimage(boolean hasimage) {
        this.hasimage = hasimage;
    }

    @Override
    public void setImage(MultipartFile image) {
        super.setImage(image);
    }

    @Override
    public MultipartFile getImage() {
        return super.getImage();
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Store> getStoresCollection() {
        return storesCollection;
    }

    public void setStoresCollection(Collection<Store> storesCollection) {
        this.storesCollection = storesCollection;
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
        if (!(object instanceof PaymentType)) {
            return false;
        }
        PaymentType other = (PaymentType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Paymenttypes[ id=" + id + " ]";
    }

}
