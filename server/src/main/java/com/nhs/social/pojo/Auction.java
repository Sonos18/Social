/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;


/**
 *
 * @author sonng
 */
@Entity
@Table(name = "auction")
@NamedQueries({
    @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a"),
    @NamedQuery(name = "Auction.findByAuctionId", query = "SELECT a FROM Auction a WHERE a.auctionId = :auctionId"),
    @NamedQuery(name = "Auction.findByStartTime", query = "SELECT a FROM Auction a WHERE a.startTime = :startTime"),
    @NamedQuery(name = "Auction.findByEndTime", query = "SELECT a FROM Auction a WHERE a.endTime = :endTime"),
    @NamedQuery(name = "Auction.findByStartingPrice", query = "SELECT a FROM Auction a WHERE a.startingPrice = :startingPrice"),
    @NamedQuery(name = "Auction.findByBuyoutPrice", query = "SELECT a FROM Auction a WHERE a.buyoutPrice = :buyoutPrice"),
    @NamedQuery(name = "Auction.findByWinningBid", query = "SELECT a FROM Auction a WHERE a.winningBid = :winningBid")})
public class Auction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "auction_id")
    private Integer auctionId;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "starting_price")
    private BigDecimal startingPrice;
    @Column(name = "buyout_price")
    private BigDecimal buyoutPrice;
    @Column(name = "winning_bid")
    private BigDecimal winningBid;
    @OneToMany(mappedBy = "auctionId")
    private Set<Auctionhistory> auctionhistorySet;
    @JoinColumns({
        @JoinColumn(name = "post_id", referencedColumnName = "post_id"),
        @JoinColumn(name = "post_id", referencedColumnName = "post_id")})
    @ManyToOne
    private Posts posts;
    @JoinColumns({
        @JoinColumn(name = "product_id", referencedColumnName = "id"),
        @JoinColumn(name = "product_id", referencedColumnName = "id")})
    @ManyToOne
    private Products products;
    @JoinColumns({
        @JoinColumn(name = "winner_user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "winner_user_id", referencedColumnName = "user_id")})
    @ManyToOne
    private Users users;

    public Auction() {
    }

    public Auction(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getBuyoutPrice() {
        return buyoutPrice;
    }

    public void setBuyoutPrice(BigDecimal buyoutPrice) {
        this.buyoutPrice = buyoutPrice;
    }

    public BigDecimal getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(BigDecimal winningBid) {
        this.winningBid = winningBid;
    }

    public Set<Auctionhistory> getAuctionhistorySet() {
        return auctionhistorySet;
    }

    public void setAuctionhistorySet(Set<Auctionhistory> auctionhistorySet) {
        this.auctionhistorySet = auctionhistorySet;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auctionId != null ? auctionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auction)) {
            return false;
        }
        Auction other = (Auction) object;
        if ((this.auctionId == null && other.auctionId != null) || (this.auctionId != null && !this.auctionId.equals(other.auctionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.social.pojo.Auction[ auctionId=" + auctionId + " ]";
    }
    
}
