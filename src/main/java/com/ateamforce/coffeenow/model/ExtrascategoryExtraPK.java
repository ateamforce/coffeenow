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
public class ExtrascategoryExtraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "categoryid")
    private int categoryid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "extraid")
    private int extraid;

    public ExtrascategoryExtraPK() {
    }

    public ExtrascategoryExtraPK(int categoryid, int extraid) {
        this.categoryid = categoryid;
        this.extraid = extraid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getExtraid() {
        return extraid;
    }

    public void setExtraid(int extraid) {
        this.extraid = extraid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) categoryid;
        hash += (int) extraid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtrascategoryExtraPK)) {
            return false;
        }
        ExtrascategoryExtraPK other = (ExtrascategoryExtraPK) object;
        if (this.categoryid != other.categoryid) {
            return false;
        }
        if (this.extraid != other.extraid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.ExtrascategoryExtraPK[ categoryid=" + categoryid + ", extraid=" + extraid + " ]";
    }
    
}
