/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexa
 */
@Embeddable
public class ProductcategoriesProductsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "categoryid")
    private int categoryid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productid")
    private int productid;

    public ProductcategoriesProductsPK() {
    }

    public ProductcategoriesProductsPK(int categoryid, int productid) {
        this.categoryid = categoryid;
        this.productid = productid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) categoryid;
        hash += (int) productid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductcategoriesProductsPK)) {
            return false;
        }
        ProductcategoriesProductsPK other = (ProductcategoriesProductsPK) object;
        if (this.categoryid != other.categoryid) {
            return false;
        }
        if (this.productid != other.productid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ProductcategoriesProductsPK[ categoryid=" + categoryid + ", productid=" + productid + " ]";
    }
    
}
