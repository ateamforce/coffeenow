/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

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
public class ExtrasCategoryProductsCategoryPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "extracategoryid")
    private int extracategoryid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productcategoryid")
    private int productcategoryid;

    public ExtrasCategoryProductsCategoryPK() {
    }

    public ExtrasCategoryProductsCategoryPK(int extracategoryid, int productcategoryid) {
        this.extracategoryid = extracategoryid;
        this.productcategoryid = productcategoryid;
    }

    public int getExtracategoryid() {
        return extracategoryid;
    }

    public void setExtracategoryid(int extracategoryid) {
        this.extracategoryid = extracategoryid;
    }

    public int getProductcategoryid() {
        return productcategoryid;
    }

    public void setProductcategoryid(int productcategoryid) {
        this.productcategoryid = productcategoryid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) extracategoryid;
        hash += (int) productcategoryid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtrasCategoryProductsCategoryPK)) {
            return false;
        }
        ExtrasCategoryProductsCategoryPK other = (ExtrasCategoryProductsCategoryPK) object;
        if (this.extracategoryid != other.extracategoryid) {
            return false;
        }
        if (this.productcategoryid != other.productcategoryid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ExtrasCategoryProductsCategoryPK[ extracategoryid=" + extracategoryid + ", productcategoryid=" + productcategoryid + " ]";
    }
    
}
