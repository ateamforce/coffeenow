/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author alexa
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@DiscriminatorColumn(name = "approle")
@Table(name = "appusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AppUser.findAll", query = "SELECT a FROM AppUser a")
    , @NamedQuery(name = "AppUser.findAppUserById", query = "SELECT a FROM AppUser a WHERE a.id = :appUserId")
    , @NamedQuery(name = "AppUser.findByEmail", query = "SELECT a FROM AppUser a WHERE a.email = :email")
    , @NamedQuery(name = "AppUser.findByPassword", query = "SELECT a FROM AppUser a WHERE a.password = :password")})
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull(message = "{email.notempty.restriction.message}")
    @NotEmpty(message = "{email.notempty.restriction.message}")
    @NotBlank(message = "{email.notempty.restriction.message}")
    @Size(min = 1, max = 255)
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "{email.wrongformat.restriction.message}")
    private String email;
    
    @Basic(optional = false)
    @NotNull(message = "{password.notempty.restriction.message}")
    @NotEmpty(message = "{password.notempty.restriction.message}")
    @NotBlank(message = "{password.notempty.restriction.message}")
    @Size(min = 8, max = 255, message = "{password.size.restriction.message}")
    @Pattern( regexp = "(?=.*[0-9]).+", message = "{password.number.restriction.message}" )
    @Pattern( regexp = "(?=.*[a-z]).+", message = "{password.small.restriction.message}" )
    @Pattern( regexp = "(?=.*[A-Z]).+", message = "{password.capital.restriction.message}" )
    @Pattern( regexp = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+", message = "{password.special.restriction.message}" )
    @Pattern( regexp = "(?=\\S+$).+", message = "{password.nospaces.restriction.message}" )
    @Column(name = "password")
    private String password;
    
    @NotNull(message = "{passwordRepeat.notempty.restriction.message}")
    @NotEmpty(message = "{passwordRepeat.notempty.restriction.message}")
    @NotBlank(message = "{passwordRepeat.notempty.restriction.message}")
    @Transient
    private String passwordRepeat;
    
    @JoinColumn(name = "approle", referencedColumnName = "approle", insertable = false, updatable = false)
    @ManyToOne
    private AppRole approle;
    
    @Column(name = "enabled")
    @Type(type= "org.hibernate.type.NumericBooleanType")
    private boolean enabled;

    public AppUser() {
    }

    public AppUser(Integer id) {
        this.id = id;
    }

    public AppUser(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public AppRole getApprole() {
        return approle;
    }

    public void setApprole(AppRole approle) {
        this.approle = approle;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    
}
