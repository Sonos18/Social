/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sonng
 */
@Entity
@Table(name = "auctionhistory")
@NamedQueries({
    @NamedQuery(name = "Auctionhistory.findAll", query = "SELECT a FROM Auctionhistory a"),
    @NamedQuery(name = "Auctionhistory.findByAuctionHistoryId", query = "SELECT a FROM Auctionhistory a WHERE a.auctionHistoryId = :auctionHistoryId"),
    @NamedQuery(name = "Auctionhistory.findByBidAmount", query = "SELECT a FROM Auctionhistory a WHERE a.bidAmount = :bidAmount"),
    @NamedQuery(name = "Auctionhistory.findByBidTime", query = "SELECT a FROM Auctionhistory a WHERE a.bidTime = :bidTime")})
public class Auctionhistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auction_history_id")
    private Integer auctionHistoryId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bid_amount")
    private BigDecimal bidAmount;
    @Column(name = "bid_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidTime;
    @JoinColumn(name = "auction_id", referencedColumnName = "auction_id")
    @ManyToOne
    private Auction auctionId;
    @JoinColumn(name = "bidder_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users bidderId;

    public Auctionhistory() {
    }

    public Auctionhistory(Integer auctionHistoryId) {
        this.auctionHistoryId = auctionHistoryId;
    }

    public Integer getAuctionHistoryId() {
        return auctionHistoryId;
    }

    public void setAuctionHistoryId(Integer auctionHistoryId) {
        this.auctionHistoryId = auctionHistoryId;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Auction getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Auction auctionId) {
        this.auctionId = auctionId;
    }

    public Users getBidderId() {
        return bidderId;
    }

    public void setBidderId(Users bidderId) {
        this.bidderId = bidderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auctionHistoryId != null ? auctionHistoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auctionhistory)) {
            return false;
        }
        Auctionhistory other = (Auctionhistory) object;
        if ((this.auctionHistoryId == null && other.auctionHistoryId != null) || (this.auctionHistoryId != null && !this.auctionHistoryId.equals(other.auctionHistoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.social.pojo.Auctionhistory[ auctionHistoryId=" + auctionHistoryId + " ]";
    }
    
}
