/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.pojo;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 *
 * @author sonng
 */
@Entity
@Table(name = "post_hashtags")
@NamedQueries({
    @NamedQuery(name = "PostHashtags.findAll", query = "SELECT p FROM PostHashtags p"),
    @NamedQuery(name = "PostHashtags.findByPostId", query = "SELECT p FROM PostHashtags p WHERE p.postHashtagsPK.postId = :postId"),
    @NamedQuery(name = "PostHashtags.findByHashtagId", query = "SELECT p FROM PostHashtags p WHERE p.postHashtagsPK.hashtagId = :hashtagId")})
public class PostHashtags implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PostHashtagsPK postHashtagsPK;
    @JoinColumns({
        @JoinColumn(name = "hashtag_id", referencedColumnName = "hashtag_id", insertable = false, updatable = false),
        @JoinColumn(name = "hashtag_id", referencedColumnName = "hashtag_id", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Hashtags hashtags;
    @JoinColumns({
        @JoinColumn(name = "post_id", referencedColumnName = "post_id", insertable = false, updatable = false),
        @JoinColumn(name = "post_id", referencedColumnName = "post_id", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private Posts posts;

    public PostHashtags() {
    }

    public PostHashtags(PostHashtagsPK postHashtagsPK) {
        this.postHashtagsPK = postHashtagsPK;
    }

    public PostHashtags(int postId, int hashtagId) {
        this.postHashtagsPK = new PostHashtagsPK(postId, hashtagId);
    }

    public PostHashtagsPK getPostHashtagsPK() {
        return postHashtagsPK;
    }

    public void setPostHashtagsPK(PostHashtagsPK postHashtagsPK) {
        this.postHashtagsPK = postHashtagsPK;
    }

    public Hashtags getHashtags() {
        return hashtags;
    }

    public void setHashtags(Hashtags hashtags) {
        this.hashtags = hashtags;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postHashtagsPK != null ? postHashtagsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostHashtags)) {
            return false;
        }
        PostHashtags other = (PostHashtags) object;
        if ((this.postHashtagsPK == null && other.postHashtagsPK != null) || (this.postHashtagsPK != null && !this.postHashtagsPK.equals(other.postHashtagsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.social.pojo.PostHashtags[ postHashtagsPK=" + postHashtagsPK + " ]";
    }
    
}
