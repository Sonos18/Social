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
 * @author sonng
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role"),
    @NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(mappedBy = "userId")
    private Set<Comments> commentsSet;
    @OneToMany(mappedBy = "bidderId")
    private Set<Auctionhistory> auctionhistorySet;
    @OneToMany(mappedBy = "userId")
    private Set<Posts> postsSet;
    @OneToMany(mappedBy = "userId")
    private Set<Notifications> notificationsSet;
    @OneToMany(mappedBy = "userId")
    private Set<Auction> auctionSet;
    @OneToMany(mappedBy = "winnerUserId")
    private Set<Auction> auctionSet1;
    @OneToMany(mappedBy = "userId")
    private Set<Likes> likesSet;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    public Set<Auctionhistory> getAuctionhistorySet() {
        return auctionhistorySet;
    }

    public void setAuctionhistorySet(Set<Auctionhistory> auctionhistorySet) {
        this.auctionhistorySet = auctionhistorySet;
    }

    public Set<Posts> getPostsSet() {
        return postsSet;
    }

    public void setPostsSet(Set<Posts> postsSet) {
        this.postsSet = postsSet;
    }

    public Set<Notifications> getNotificationsSet() {
        return notificationsSet;
    }

    public void setNotificationsSet(Set<Notifications> notificationsSet) {
        this.notificationsSet = notificationsSet;
    }

    public Set<Auction> getAuctionSet() {
        return auctionSet;
    }

    public void setAuctionSet(Set<Auction> auctionSet) {
        this.auctionSet = auctionSet;
    }

    public Set<Auction> getAuctionSet1() {
        return auctionSet1;
    }

    public void setAuctionSet1(Set<Auction> auctionSet1) {
        this.auctionSet1 = auctionSet1;
    }

    public Set<Likes> getLikesSet() {
        return likesSet;
    }

    public void setLikesSet(Set<Likes> likesSet) {
        this.likesSet = likesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.social.pojo.Users[ userId=" + userId + " ]";
    }
    
}
