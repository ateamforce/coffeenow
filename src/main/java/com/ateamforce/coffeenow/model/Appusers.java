/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "appusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appusers.findAll", query = "SELECT a FROM Appusers a")
    , @NamedQuery(name = "Appusers.findById", query = "SELECT a FROM Appusers a WHERE a.id = :id")
    , @NamedQuery(name = "Appusers.findByEmail", query = "SELECT a FROM Appusers a WHERE a.email = :email")
    , @NamedQuery(name = "Appusers.findByPassword", query = "SELECT a FROM Appusers a WHERE a.password = :password")
    , @NamedQuery(name = "Appusers.findByKey", query = "SELECT a FROM Appusers a WHERE a.key = :key")
    , @NamedQuery(name = "Appusers.findByRole", query = "SELECT a FROM Appusers a WHERE a.role = :role")})
public class Appusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "key")
    private String key;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "role")
    private String role;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "appusers")
    private Clients clients;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "appusers")
    private Stores stores;

    public Appusers() {
    }

    public Appusers(Integer id) {
        this.id = id;
    }

    public Appusers(Integer id, String email, String password, String key, String role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.key = key;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appusers)) {
            return false;
        }
        Appusers other = (Appusers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Appusers[ id=" + id + " ]";
    }
    
}
