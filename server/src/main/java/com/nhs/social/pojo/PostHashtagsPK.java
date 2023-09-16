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
@Embeddable
public class PostHashtagsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "post_id")
    private int postId;
    @Basic(optional = false)
    @Column(name = "hashtag_id")
    private int hashtagId;

    public PostHashtagsPK() {
    }

    public PostHashtagsPK(int postId, int hashtagId) {
        this.postId = postId;
        this.hashtagId = hashtagId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(int hashtagId) {
        this.hashtagId = hashtagId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) postId;
        hash += (int) hashtagId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostHashtagsPK)) {
            return false;
        }
        PostHashtagsPK other = (PostHashtagsPK) object;
        if (this.postId != other.postId) {
            return false;
        }
        if (this.hashtagId != other.hashtagId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.social.pojo.PostHashtagsPK[ postId=" + postId + ", hashtagId=" + hashtagId + " ]";
    }
    
}
