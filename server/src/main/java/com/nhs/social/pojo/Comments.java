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
@Table(name = "comments")
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findByCommentId", query = "SELECT c FROM Comments c WHERE c.commentId = :commentId"),
    @NamedQuery(name = "Comments.findByCreateAt", query = "SELECT c FROM Comments c WHERE c.createAt = :createAt"),
    @NamedQuery(name = "Comments.findByContent", query = "SELECT c FROM Comments c WHERE c.content = :content")})
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "content")
    private String content;
    @OneToMany(mappedBy = "parentCommentId")
    private Set<Comments> commentsSet;
    @JoinColumn(name = "parent_comment_id", referencedColumnName = "comment_id")
    @ManyToOne
    private Comments parentCommentId;
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    @ManyToOne
    private Posts postId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private Users userId;

    public Comments() {
    }

    public Comments(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    public Comments getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Comments parentCommentId) {
        this.parentCommentId = parentCommentId;
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
        hash += (commentId != null ? commentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.commentId == null && other.commentId != null) || (this.commentId != null && !this.commentId.equals(other.commentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhs.social.pojo.Comments[ commentId=" + commentId + " ]";
    }
    
}
