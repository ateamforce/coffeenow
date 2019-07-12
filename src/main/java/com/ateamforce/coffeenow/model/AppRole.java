/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "approles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppRole.findAll", query = "SELECT a FROM AppRole a")
    , @NamedQuery(name = "AppRole.findByApprole", query = "SELECT a FROM AppRole a WHERE a.approle = :approle")})
public class AppRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "approle")
    private String approle;
    
    //Maybe delete permanently
//    @OneToMany(mappedBy = "approle")
//    private Collection<AppUsers> appUsersCollection;

    public AppRole() {
    }

    public AppRole(String approle) {
        this.approle = approle;
    }

    public String getApprole() {
        return approle;
    }

    public void setApprole(String approle) {
        this.approle = approle;
    }

//    @XmlTransient
//    @JsonIgnore
//    public Collection<AppUsers> getAppUsersCollection() {
//        return appUsersCollection;
//    }
//
//    public void setAppUsersCollection(Collection<AppUsers> appUsersCollection) {
//        this.appUsersCollection = appUsersCollection;
//    }

   
    
}
