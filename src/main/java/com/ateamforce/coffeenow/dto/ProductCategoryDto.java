/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "productcategories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductCategoryDto.findAllProductCategories", query = "SELECT p FROM ProductCategoryDto p")
    , @NamedQuery(name = "ProductCategoryDto.findProductCategoryDtoById", query = "SELECT p FROM ProductCategoryDto p WHERE p.id = :categoryId")
    , @NamedQuery(name = "ProductCategoryDto.findByTitle", query = "SELECT p FROM ProductCategoryDto p WHERE p.title = :title")
    , @NamedQuery(name = "ProductCategoryDto.findByParent", query = "SELECT p FROM ProductCategoryDto p WHERE p.parent = :parent")
    , @NamedQuery(name = "ProductCategoryDto.findByImage", query = "SELECT p FROM ProductCategoryDto p WHERE p.image = :image")})
public class ProductCategoryDto implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "image")
    private String image;

    public ProductCategoryDto() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
