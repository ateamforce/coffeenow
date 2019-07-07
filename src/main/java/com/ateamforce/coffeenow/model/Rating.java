/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ateamforce.coffeenow.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexa
 */
@Entity
@Table(name = "ratings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r")
    , @NamedQuery(name = "Rating.findByClientid", query = "SELECT r FROM Rating r WHERE r.ratingPK.clientid = :clientid")
    , @NamedQuery(name = "Rating.findByStoreid", query = "SELECT r FROM Rating r WHERE r.ratingPK.storeid = :storeid")
    , @NamedQuery(name = "Rating.findByRatingQuality", query = "SELECT r FROM Rating r WHERE r.ratingQuality = :ratingQuality")
    , @NamedQuery(name = "Rating.findByRatingpeed", query = "SELECT r FROM Rating r WHERE r.ratingSpeed = :ratingSpeed")
    , @NamedQuery(name = "Rating.findByRatingervice", query = "SELECT r FROM Rating r WHERE r.ratingService = :ratingService")
    , @NamedQuery(name = "Rating.findByRatingdate", query = "SELECT r FROM Rating r WHERE r.ratingdate = :ratingdate")
    , @NamedQuery(name = "Rating.findByAnswerdate", query = "SELECT r FROM Rating r WHERE r.answerdate = :answerdate")})
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RatingPK ratingPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating_quality")
    private int ratingQuality;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating_speed")
    private int ratingSpeed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating_service")
    private int ratingService;
    @Lob
    @Size(max = 65535)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratingdate")
    @Temporal(TemporalType.DATE)
    private Date ratingdate;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer")
    private String answer;
    @Column(name = "answerdate")
    @Temporal(TemporalType.DATE)
    private Date answerdate;
    @JoinColumn(name = "clientid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Client clients;
    @JoinColumn(name = "storeid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store stores;

    public Rating() {
    }

    public Rating(RatingPK ratingPK) {
        this.ratingPK = ratingPK;
    }

    public Rating(RatingPK ratingsPK, int ratingQuality, int ratingSpeed, int ratingService, Date ratingdate) {
        this.ratingPK = ratingsPK;
        this.ratingQuality = ratingQuality;
        this.ratingSpeed = ratingSpeed;
        this.ratingService = ratingService;
        this.ratingdate = ratingdate;
    }

    public Rating(int clientid, int storeid) {
        this.ratingPK = new RatingPK(clientid, storeid);
    }

    public RatingPK getRatingsPK() {
        return ratingPK;
    }

    public void setRatingsPK(RatingPK ratingsPK) {
        this.ratingPK = ratingsPK;
    }

    public int getRatingQuality() {
        return ratingQuality;
    }

    public void setRatingQuality(int ratingQuality) {
        this.ratingQuality = ratingQuality;
    }

    public int getRatingSpeed() {
        return ratingSpeed;
    }

    public void setRatingSpeed(int ratingSpeed) {
        this.ratingSpeed = ratingSpeed;
    }

    public int getRatingService() {
        return ratingService;
    }

    public void setRatingService(int ratingService) {
        this.ratingService = ratingService;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getRatingdate() {
        return ratingdate;
    }

    public void setRatingdate(Date ratingdate) {
        this.ratingdate = ratingdate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getAnswerdate() {
        return answerdate;
    }

    public void setAnswerdate(Date answerdate) {
        this.answerdate = answerdate;
    }

    public Client getClients() {
        return clients;
    }

    public void setClients(Client clients) {
        this.clients = clients;
    }

    public Store getStores() {
        return stores;
    }

    public void setStores(Store stores) {
        this.stores = stores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ratingPK != null ? ratingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.ratingPK == null && other.ratingPK != null) || (this.ratingPK != null && !this.ratingPK.equals(other.ratingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Ratings[ ratingsPK=" + ratingPK + " ]";
    }
    
}
