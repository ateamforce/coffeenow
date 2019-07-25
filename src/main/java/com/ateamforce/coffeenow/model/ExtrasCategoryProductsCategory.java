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
@Table(name = "extras_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtrasCategoryProductsCategory.findAll", query = "SELECT e FROM ExtrasCategoryProductsCategory e")
    , @NamedQuery(name = "ExtrasCategoryProductsCategory.findByExtracategoryid", query = "SELECT e FROM ExtrasCategoryProductsCategory e WHERE e.extrasCategoryProductsCategoryPK.extracategoryid = :extracategoryid")
    , @NamedQuery(name = "ExtrasCategoryProductsCategory.findByProductcategoryid", query = "SELECT e FROM ExtrasCategoryProductsCategory e WHERE e.extrasCategoryProductsCategoryPK.productcategoryid = :productcategoryid")})
public class ExtrasCategoryProductsCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExtrasCategoryProductsCategoryPK extrasCategoryProductsCategoryPK;

    public ExtrasCategoryProductsCategory() {
    }

    public ExtrasCategoryProductsCategory(ExtrasCategoryProductsCategoryPK extrasCategoryProductsCategoryPK) {
        this.extrasCategoryProductsCategoryPK = extrasCategoryProductsCategoryPK;
    }

    public ExtrasCategoryProductsCategory(int extracategoryid, int productcategoryid) {
        this.extrasCategoryProductsCategoryPK = new ExtrasCategoryProductsCategoryPK(extracategoryid, productcategoryid);
    }

    public ExtrasCategoryProductsCategoryPK getExtrasCategoryProductsCategoryPK() {
        return extrasCategoryProductsCategoryPK;
    }

    public void setExtrasCategoryProductsCategoryPK(ExtrasCategoryProductsCategoryPK extrasCategoryProductsCategoryPK) {
        this.extrasCategoryProductsCategoryPK = extrasCategoryProductsCategoryPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extrasCategoryProductsCategoryPK != null ? extrasCategoryProductsCategoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtrasCategoryProductsCategory)) {
            return false;
        }
        ExtrasCategoryProductsCategory other = (ExtrasCategoryProductsCategory) object;
        if ((this.extrasCategoryProductsCategoryPK == null && other.extrasCategoryProductsCategoryPK != null) || (this.extrasCategoryProductsCategoryPK != null && !this.extrasCategoryProductsCategoryPK.equals(other.extrasCategoryProductsCategoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ExtrasCategoryProductsCategory[ extrasCategoryProductsCategoryPK=" + extrasCategoryProductsCategoryPK + " ]";
    }
    
}
