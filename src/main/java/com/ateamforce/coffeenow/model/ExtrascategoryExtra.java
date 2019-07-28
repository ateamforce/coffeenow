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
@Table(name = "extrascategories_extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtrascategoryExtra.findAll", query = "SELECT e FROM ExtrascategoryExtra e")
    , @NamedQuery(name = "ExtrascategoryExtra.findByExtraCategoryid", query = "SELECT e FROM ExtrascategoryExtra e WHERE e.extrascategoryExtraPK.categoryid = :extraCategoryId")
    , @NamedQuery(name = "ExtrascategoryExtra.findByExtraid", query = "SELECT e FROM ExtrascategoryExtra e WHERE e.extrascategoryExtraPK.extraid = :extraId")})
public class ExtrascategoryExtra implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ExtrascategoryExtraPK extrascategoryExtraPK;

    public ExtrascategoryExtra() {
    }

    public ExtrascategoryExtra(ExtrascategoryExtraPK extrascategoryExtraPK) {
        this.extrascategoryExtraPK = extrascategoryExtraPK;
    }

    public ExtrascategoryExtra(int categoryid, int extraid) {
        this.extrascategoryExtraPK = new ExtrascategoryExtraPK(categoryid, extraid);
    }

    public ExtrascategoryExtraPK getExtrascategoryExtraPK() {
        return extrascategoryExtraPK;
    }

    public void setExtrascategoryExtraPK(ExtrascategoryExtraPK extrascategoryExtraPK) {
        this.extrascategoryExtraPK = extrascategoryExtraPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (extrascategoryExtraPK != null ? extrascategoryExtraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtrascategoryExtra)) {
            return false;
        }
        ExtrascategoryExtra other = (ExtrascategoryExtra) object;
        if ((this.extrascategoryExtraPK == null && other.extrascategoryExtraPK != null) || (this.extrascategoryExtraPK != null && !this.extrascategoryExtraPK.equals(other.extrascategoryExtraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ExtrascategoryExtra[ extrascategoryExtraPK=" + extrascategoryExtraPK + " ]";
    }
    
}
