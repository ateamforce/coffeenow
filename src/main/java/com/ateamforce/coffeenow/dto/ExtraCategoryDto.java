/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto;

import com.ateamforce.coffeenow.model.Extra;
import com.ateamforce.coffeenow.model.ProductCategory;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @NamedQuery(name = "ExtraCategoryDto.findAllExtraCategories", query = "SELECT e FROM ExtraCategoryDto e")
    , @NamedQuery(name = "ExtraCategoryDto.findExtraCategoryDtoById", query = "SELECT e FROM ExtraCategoryDto e WHERE e.id = :categoryId")
    , @NamedQuery(name = "ExtraCategoryDto.findByTitle", query = "SELECT e FROM ExtraCategoryDto e WHERE e.title = :title")
    , @NamedQuery(name = "ExtraCategoryDto.findByParent", query = "SELECT e FROM ExtraCategoryDto e WHERE e.parent = :parent")})
public class ExtraCategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "parent")
    private int parent;
    
    @Transient
    private List<Extra> extrasList;
    @Transient
    private List<ProductCategoryDto> productcategoriesList;
    
//    @Transient
//    @XmlTransient
//    @JsonIgnore // excludes extraCategoryImage from json view of the product
//    private MultipartFile extraCategoryImage;

    public ExtraCategoryDto() {
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

//    public MultipartFile getExtraCategoryImage() {
//        return extraCategoryImage;
//    }
//
//    public void setExtraCategoryImage(MultipartFile extraCategoryImage) {
//        this.extraCategoryImage = extraCategoryImage;
//    }

    public List<Extra> getExtrasList() {
        return extrasList;
    }

    public void setExtrasList(List<Extra> extrasList) {
        this.extrasList = extrasList;
    }

    public List<ProductCategoryDto> getProductcategoriesList() {
        return productcategoriesList;
    }

    public void setProductcategoriesList(List<ProductCategoryDto> productcategoriesList) {
        this.productcategoriesList = productcategoriesList;
    }
    
    

}
