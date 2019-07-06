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
    @NamedQuery(name = "Ratings.findAll", query = "SELECT r FROM Ratings r")
    , @NamedQuery(name = "Ratings.findByClientid", query = "SELECT r FROM Ratings r WHERE r.ratingsPK.clientid = :clientid")
    , @NamedQuery(name = "Ratings.findByStoreid", query = "SELECT r FROM Ratings r WHERE r.ratingsPK.storeid = :storeid")
    , @NamedQuery(name = "Ratings.findByRatingQuality", query = "SELECT r FROM Ratings r WHERE r.ratingQuality = :ratingQuality")
    , @NamedQuery(name = "Ratings.findByRatingSpeed", query = "SELECT r FROM Ratings r WHERE r.ratingSpeed = :ratingSpeed")
    , @NamedQuery(name = "Ratings.findByRatingService", query = "SELECT r FROM Ratings r WHERE r.ratingService = :ratingService")
    , @NamedQuery(name = "Ratings.findByRatingdate", query = "SELECT r FROM Ratings r WHERE r.ratingdate = :ratingdate")
    , @NamedQuery(name = "Ratings.findByAnswerdate", query = "SELECT r FROM Ratings r WHERE r.answerdate = :answerdate")})
public class Ratings implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RatingsPK ratingsPK;
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
    private Clients clients;
    @JoinColumn(name = "storeid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Stores stores;

    public Ratings() {
    }

    public Ratings(RatingsPK ratingsPK) {
        this.ratingsPK = ratingsPK;
    }

    public Ratings(RatingsPK ratingsPK, int ratingQuality, int ratingSpeed, int ratingService, Date ratingdate) {
        this.ratingsPK = ratingsPK;
        this.ratingQuality = ratingQuality;
        this.ratingSpeed = ratingSpeed;
        this.ratingService = ratingService;
        this.ratingdate = ratingdate;
    }

    public Ratings(int clientid, int storeid) {
        this.ratingsPK = new RatingsPK(clientid, storeid);
    }

    public RatingsPK getRatingsPK() {
        return ratingsPK;
    }

    public void setRatingsPK(RatingsPK ratingsPK) {
        this.ratingsPK = ratingsPK;
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
        hash += (ratingsPK != null ? ratingsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ratings)) {
            return false;
        }
        Ratings other = (Ratings) object;
        if ((this.ratingsPK == null && other.ratingsPK != null) || (this.ratingsPK != null && !this.ratingsPK.equals(other.ratingsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ateamforce.coffeenow.model.Ratings[ ratingsPK=" + ratingsPK + " ]";
    }
    
}
