/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.pojo;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

/**
 *
 * @author sonng
 */
@Entity
@Table(name = "report")
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r"),
    @NamedQuery(name = "Report.findByReportId", query = "SELECT r FROM Report r WHERE r.reportId = :reportId"),
    @NamedQuery(name = "Report.findByReason", query = "SELECT r FROM Report r WHERE r.reason = :reason"),
    @NamedQuery(name = "Report.findByReportDate", query = "SELECT r FROM Report r WHERE r.reportDate = :reportDate")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "report_id")
    private Integer reportId;
    @Column(name = "reason")
    private String reason;
    @Column(name = "report_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;
    @JoinColumn(name = "auction_id", referencedColumnName = "auction_id")
    @ManyToOne
    private Auction auctionId;
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @ManyToOne
    private Posts postId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Report() {
    }

    public Report(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Auction getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Auction auctionId) {
        this.auctionId = auctionId;
    }

    public Posts getPostId() {
        return postId;
    }

    public void setPostId(Posts postId) {
        this.postId = postId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reportId != null ? reportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportId == null && other.reportId != null) || (this.reportId != null && !this.reportId.equals(other.reportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.social.pojo.Report[ reportId=" + reportId + " ]";
    }
    
}
