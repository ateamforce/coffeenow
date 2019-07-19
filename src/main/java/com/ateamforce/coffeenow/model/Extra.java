/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extra.findAllExtras", query = "SELECT e FROM Extra e")
    , @NamedQuery(name = "Extra.findExtraById", query = "SELECT e FROM Extra e WHERE e.id = :extraId")
    , @NamedQuery(name = "Extra.findByTitle", query = "SELECT e FROM Extra e WHERE e.title = :title")})
public class Extra extends _ImageCarrier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 3, max = 255, message = "{title.size.restriction.message}")
    @Column(name = "title")
    private String title;
    @JoinTable(name = "extracategories_extras", joinColumns = {
        @JoinColumn(name = "extraid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "categoryid", referencedColumnName = "id")})
    @ManyToMany
    private List<ExtraCategory> extracategoriesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "extras")
    private Collection<StoreExtra> storesExtrasCollection;
    @OneToMany(mappedBy = "extraid")
    private Collection<OrderProduct> ordersProductsCollection;

    public Extra() {
    }

    public Extra(Integer id) {
        this.id = id;
    }

    public Extra(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    
    public Extra(Integer id, String title, MultipartFile image) {
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

    @XmlTransient
    @JsonIgnore
    public List<ExtraCategory> getExtraCategoriesList() {
        return extracategoriesList;
    }

    public void setExtracategoriesList(List<ExtraCategory> extrascategoriesList) {
        this.extracategoriesList = extrascategoriesList;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<StoreExtra> getStoresExtrasCollection() {
        return storesExtrasCollection;
    }

    public void setStoresExtrasCollection(Collection<StoreExtra> storesExtrasCollection) {
        this.storesExtrasCollection = storesExtrasCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<OrderProduct> getOrdersProductsCollection() {
        return ordersProductsCollection;
    }

    public void setOrdersProductsCollection(Collection<OrderProduct> ordersProductsCollection) {
        this.ordersProductsCollection = ordersProductsCollection;
    }

    @Override
    public void setImage(MultipartFile image) {
        super.setImage(image);
    }

    @Override
    public MultipartFile getImage() {
        return super.getImage();
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
        if (!(object instanceof Extra)) {
            return false;
        }
        Extra other = (Extra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Extras[ id=" + id + " ]";
    }
    
}
