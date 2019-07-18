/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtraDto.findAllExtras", query = "SELECT e FROM ExtraDto e")
    , @NamedQuery(name = "ExtraDto.findExtraById", query = "SELECT e FROM ExtraDto e WHERE e.id = :extraId")
    , @NamedQuery(name = "ExtraDto.findByTitle", query = "SELECT e FROM ExtraDto e WHERE e.title = :title")})
public class ExtraDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    
    @Transient
    @XmlTransient
    @JsonIgnore // excludes extraImage from json view of the product
    private MultipartFile extraImage;

    public ExtraDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getExtraImage() {
        return extraImage;
    }

    public void setExtraImage(MultipartFile extraImage) {
        this.extraImage = extraImage;
    }

}
