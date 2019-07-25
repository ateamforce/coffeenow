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
@Table(name = "extras_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtrasCategoriesProductsCategoriesDto.findAll", query = "SELECT e FROM ExtrasCategoriesProductsCategoriesDto e")
    , @NamedQuery(name = "ExtrasCategoriesProductsCategoriesDto.findByExtracategoryid", query = "SELECT e FROM ExtrasCategoriesProductsCategoriesDto e WHERE e.extrasCategoriesProductsCategoriesPK.extracategoryid = :extracategoryid")
    , @NamedQuery(name = "ExtrasCategoriesProductsCategoriesDto.findByProductcategoryid", query = "SELECT e FROM ExtrasCategoriesProductsCategoriesDto e WHERE e.extrasCategoriesProductsCategoriesPK.productcategoryid = :productcategoryid")})
public class ExtrasCategoriesProductsCategoriesDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExtrasCategoriesProductsCategoriesPK extrasCategoriesProductsCategoriesPK;

    public ExtrasCategoriesProductsCategoriesDto() {
    }

    public ExtrasCategoriesProductsCategoriesDto(ExtrasCategoriesProductsCategoriesPK extrasCategoriesProductsCategoriesPK) {
        this.extrasCategoriesProductsCategoriesPK = extrasCategoriesProductsCategoriesPK;
    }

    public ExtrasCategoriesProductsCategoriesDto(int extracategoryid, int productcategoryid) {
        this.extrasCategoriesProductsCategoriesPK = new ExtrasCategoriesProductsCategoriesPK(extracategoryid, productcategoryid);
    }

    public ExtrasCategoriesProductsCategoriesPK getExtrasCategoriesProductsCategoriesPK() {
        return extrasCategoriesProductsCategoriesPK;
    }

    public void setExtrasCategoriesProductsCategoriesPK(ExtrasCategoriesProductsCategoriesPK extrasCategoriesProductsCategoriesPK) {
        this.extrasCategoriesProductsCategoriesPK = extrasCategoriesProductsCategoriesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extrasCategoriesProductsCategoriesPK != null ? extrasCategoriesProductsCategoriesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtrasCategoriesProductsCategoriesDto)) {
            return false;
        }
        ExtrasCategoriesProductsCategoriesDto other = (ExtrasCategoriesProductsCategoriesDto) object;
        if ((this.extrasCategoriesProductsCategoriesPK == null && other.extrasCategoriesProductsCategoriesPK != null) || (this.extrasCategoriesProductsCategoriesPK != null && !this.extrasCategoriesProductsCategoriesPK.equals(other.extrasCategoriesProductsCategoriesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ExtrasCategoriesProductsCategories[ extrasCategoriesProductsCategoriesPK=" + extrasCategoriesProductsCategoriesPK + " ]";
    }
    
}
