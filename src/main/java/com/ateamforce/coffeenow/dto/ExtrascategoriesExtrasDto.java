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
@Table(name = "extrascategories_extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtrascategoriesExtrasDto.findAll", query = "SELECT e FROM ExtrascategoriesExtrasDto e")
    , @NamedQuery(name = "ExtrascategoriesExtrasDto.findByCategoryid", query = "SELECT e FROM ExtrascategoriesExtrasDto e WHERE e.extrascategoriesExtrasDtoPK.categoryid = :categoryid")
    , @NamedQuery(name = "ExtrascategoriesExtrasDto.findByExtraid", query = "SELECT e FROM ExtrascategoriesExtrasDto e WHERE e.extrascategoriesExtrasDtoPK.extraid = :extraid")})
public class ExtrascategoriesExtrasDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExtrascategoriesExtrasDtoPK extrascategoriesExtrasDtoPK;

    public ExtrascategoriesExtrasDto() {
    }

    public ExtrascategoriesExtrasDto(ExtrascategoriesExtrasDtoPK extrascategoriesExtrasDtoPK) {
        this.extrascategoriesExtrasDtoPK = extrascategoriesExtrasDtoPK;
    }

    public ExtrascategoriesExtrasDto(int categoryid, int extraid) {
        this.extrascategoriesExtrasDtoPK = new ExtrascategoriesExtrasDtoPK(categoryid, extraid);
    }

    public ExtrascategoriesExtrasDtoPK getExtrascategoriesExtrasDtoPK() {
        return extrascategoriesExtrasDtoPK;
    }

    public void setExtrascategoriesExtrasDtoPK(ExtrascategoriesExtrasDtoPK extrascategoriesExtrasDtoPK) {
        this.extrascategoriesExtrasDtoPK = extrascategoriesExtrasDtoPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extrascategoriesExtrasDtoPK != null ? extrascategoriesExtrasDtoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtrascategoriesExtrasDto)) {
            return false;
        }
        ExtrascategoriesExtrasDto other = (ExtrascategoriesExtrasDto) object;
        if ((this.extrascategoriesExtrasDtoPK == null && other.extrascategoriesExtrasDtoPK != null) || (this.extrascategoriesExtrasDtoPK != null && !this.extrascategoriesExtrasDtoPK.equals(other.extrascategoriesExtrasDtoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.dto.ExtrascategoriesExtrasDto[ extrascategoriesExtrasDtoPK=" + extrascategoriesExtrasDtoPK + " ]";
    }
    
}
