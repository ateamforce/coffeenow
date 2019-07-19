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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "extrascategories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtraCategory.findAllExtraCategories", query = "SELECT e FROM ExtraCategory e")
    , @NamedQuery(name = "ExtraCategory.findExtraCategoryById", query = "SELECT e FROM ExtraCategory e WHERE e.id = :categoryId")
    , @NamedQuery(name = "ExtraCategory.findByTitle", query = "SELECT e FROM ExtraCategory e WHERE e.title = :title")
    , @NamedQuery(name = "ExtraCategory.findByParent", query = "SELECT e FROM ExtraCategory e WHERE e.parent = :parent")})
public class ExtraCategory extends _ImageCarrier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255, message = "{title.size.restriction.message}")
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parent")
    private int parent;
    @ManyToMany(mappedBy = "extracategoriesList")
    private List<Extra> extrasList;
    @JoinTable(name = "extras_products", joinColumns = {
        @JoinColumn(name = "extracategoryid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "productcategoryid", referencedColumnName = "id")})
    @ManyToMany
    private List<ProductCategory> productcategoriesList;

    public ExtraCategory() {
    }

    public ExtraCategory(Integer id) {
        this.id = id;
    }

    public ExtraCategory(Integer id, String title, int parent) {
        this.id = id;
        this.title = title;
        this.parent = parent;
    }
    
    public ExtraCategory(Integer id, String title, int parent, MultipartFile image) {
        super(image);
        this.id = id;
        this.title = title;
        this.parent = parent;
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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @XmlTransient
    @JsonIgnore
    public List<Extra> getExtrasList() {
        return extrasList;
    }

    public void setExtrasList(List<Extra> extrasList) {
        this.extrasList = extrasList;
    }

    @XmlTransient
    @JsonIgnore
    public List<ProductCategory> getProductcategoriesList() {
        return productcategoriesList;
    }

    public void setProductcategoriesList(List<ProductCategory> productcategoriesList) {
        this.productcategoriesList = productcategoriesList;
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
        if (!(object instanceof ExtraCategory)) {
            return false;
        }
        ExtraCategory other = (ExtraCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Extrascategories[ id=" + id + " ]";
    }
    
}
