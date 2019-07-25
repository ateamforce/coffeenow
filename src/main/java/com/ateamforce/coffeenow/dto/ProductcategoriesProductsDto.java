/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "productcategories_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductcategoriesProductsDto.findAll", query = "SELECT p FROM ProductcategoriesProductsDto p")
    , @NamedQuery(name = "ProductcategoriesProductsDto.findByCategoryid", query = "SELECT p FROM ProductcategoriesProductsDto p WHERE p.productcategoriesProductsPK.categoryid = :categoryid")
    , @NamedQuery(name = "ProductcategoriesProductsDto.findByProductid", query = "SELECT p FROM ProductcategoriesProductsDto p WHERE p.productcategoriesProductsPK.productid = :productid")})
public class ProductcategoriesProductsDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductcategoriesProductsPK productcategoriesProductsPK;

    public ProductcategoriesProductsDto() {
    }

    public ProductcategoriesProductsDto(ProductcategoriesProductsPK productcategoriesProductsPK) {
        this.productcategoriesProductsPK = productcategoriesProductsPK;
    }

    public ProductcategoriesProductsDto(int categoryid, int productid) {
        this.productcategoriesProductsPK = new ProductcategoriesProductsPK(categoryid, productid);
    }

    public ProductcategoriesProductsPK getProductcategoriesProductsPK() {
        return productcategoriesProductsPK;
    }

    public void setProductcategoriesProductsPK(ProductcategoriesProductsPK productcategoriesProductsPK) {
        this.productcategoriesProductsPK = productcategoriesProductsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productcategoriesProductsPK != null ? productcategoriesProductsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductcategoriesProductsDto)) {
            return false;
        }
        ProductcategoriesProductsDto other = (ProductcategoriesProductsDto) object;
        if ((this.productcategoriesProductsPK == null && other.productcategoriesProductsPK != null) || (this.productcategoriesProductsPK != null && !this.productcategoriesProductsPK.equals(other.productcategoriesProductsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ProductcategoriesProducts[ productcategoriesProductsPK=" + productcategoriesProductsPK + " ]";
    }
    
}
