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
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "productcategories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductCategory.findAllProductCategories", query = "SELECT p FROM ProductCategory p")
    , @NamedQuery(name = "ProductCategory.findProductCategoryById", query = "SELECT p FROM ProductCategory p WHERE p.id = :categoryId")
    , @NamedQuery(name = "ProductCategory.findByTitle", query = "SELECT p FROM ProductCategory p WHERE p.title = :title")
    , @NamedQuery(name = "ProductCategory.findByParent", query = "SELECT p FROM ProductCategory p WHERE p.parent = :parent")})
public class ProductCategory extends _ImageCarrier implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "parent")
    private int parent;
    @Column(name = "hasimage")
    @Type(type= "org.hibernate.type.NumericBooleanType")
    private boolean hasimage;
    @JoinTable(name = "productcategories_products", joinColumns = {
        @JoinColumn(name = "categoryid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "productid", referencedColumnName = "id")})
    @ManyToMany
    private List<Product> productsList;
    @JoinTable(name = "extras_products", joinColumns = {
        @JoinColumn(name = "productcategoryid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "extracategoryid", referencedColumnName = "id")})
    @ManyToMany
    private List<ExtraCategory> extrascategoriesList;

    public ProductCategory() {
    }

    public ProductCategory(Integer id) {
        this.id = id;
    }

    public ProductCategory(Integer id, String title, int parent) {
        this.id = id;
        this.title = title;
        this.parent = parent;
    }

    public ProductCategory(Integer id, String title, int parent, MultipartFile image) {
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

    public boolean isHasimage() {
        return hasimage;
    }

    public void setHasimage(boolean hasimage) {
        this.hasimage = hasimage;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsCollection) {
        this.productsList = productsCollection;
    }

    public List<ExtraCategory> getExtrascategoriesList() {
        return extrascategoriesList;
    }

    public void setExtrascategoriesList(List<ExtraCategory> extrascategoriesList) {
        this.extrascategoriesList = extrascategoriesList;
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
        if (!(object instanceof ProductCategory)) {
            return false;
        }
        ProductCategory other = (ProductCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Productcategories[ id=" + id + ", title=" + title + ", parent=" + parent + ", hasimage=" + hasimage + " ]";
    }

}
