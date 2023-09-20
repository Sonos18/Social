/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import jakarta.persistence.*;


/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "hashtags")
@NamedQueries({
    @NamedQuery(name = "Hashtags.findAll", query = "SELECT h FROM Hashtags h"),
    @NamedQuery(name = "Hashtags.findByHashtagId", query = "SELECT h FROM Hashtags h WHERE h.hashtagId = :hashtagId"),
    @NamedQuery(name = "Hashtags.findByHashtagText", query = "SELECT h FROM Hashtags h WHERE h.hashtagText = :hashtagText"),
    @NamedQuery(name = "Hashtags.findByCreatedAt", query = "SELECT h FROM Hashtags h WHERE h.createdAt = :createdAt")})
public class Hashtags implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hashtag_id")
    private Integer hashtagId;
    @Column(name = "hashtag_text")
    private String hashtagText;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToMany(mappedBy = "hashtagsSet")
    private Set<Posts> postsSet;

    public Hashtags() {
    }

    public Hashtags(Integer hashtagId) {
        this.hashtagId = hashtagId;
    }

    public Integer getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(Integer hashtagId) {
        this.hashtagId = hashtagId;
    }

    public String getHashtagText() {
        return hashtagText;
    }

    public void setHashtagText(String hashtagText) {
        this.hashtagText = hashtagText;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Posts> getPostsSet() {
        return postsSet;
    }

    public void setPostsSet(Set<Posts> postsSet) {
        this.postsSet = postsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hashtagId != null ? hashtagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hashtags)) {
            return false;
        }
        Hashtags other = (Hashtags) object;
        if ((this.hashtagId == null && other.hashtagId != null) || (this.hashtagId != null && !this.hashtagId.equals(other.hashtagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.social.pojo.Hashtags[ hashtagId=" + hashtagId + " ]";
    }
    
}
