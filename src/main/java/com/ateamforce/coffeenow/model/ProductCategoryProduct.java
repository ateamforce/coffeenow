/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

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
    @NamedQuery(name = "ProductcategoryProduct.findAll", query = "SELECT p FROM ProductCategoryProduct p")
    , @NamedQuery(name = "ProductCategoryProduct.findAllByProductCategoryid", query = "SELECT p FROM ProductCategoryProduct p WHERE p.productCategoryProductPK.categoryid = :productcategoryid")
    , @NamedQuery(name = "ProductCategoryProduct.findByProductid", query = "SELECT p FROM ProductCategoryProduct p WHERE p.productCategoryProductPK.productid = :productid")})
public class ProductCategoryProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductCategoryProductPK productCategoryProductPK;

    public ProductCategoryProduct() {
    }

    public ProductCategoryProduct(ProductCategoryProductPK productcategoryProductPK) {
        this.productCategoryProductPK = productCategoryProductPK;
    }

    public ProductCategoryProduct(int categoryid, int productid) {
        this.productCategoryProductPK = new ProductCategoryProductPK(categoryid, productid);
    }

    public ProductCategoryProductPK getProductCategoryProductPK() {
        return productCategoryProductPK;
    }

    public void setProductcategoryProductPK(ProductCategoryProductPK productCategoryProductPK) {
        this.productCategoryProductPK = productCategoryProductPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productCategoryProductPK != null ? productCategoryProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductCategoryProduct)) {
            return false;
        }
        ProductCategoryProduct other = (ProductCategoryProduct) object;
        if ((this.productCategoryProductPK == null && other.productCategoryProductPK != null) || (this.productCategoryProductPK != null && !this.productCategoryProductPK.equals(other.productCategoryProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ProductcategoryProduct[ productcategoryProductPK=" + productCategoryProductPK + " ]";
    }
    
}
