/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service.Impl;

import com.nhs.social.Dto.CommentDto;
import com.nhs.social.pojo.Comments;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import com.nhs.social.repository.CommentRepository;
import com.nhs.social.service.CommentService;
import com.nhs.social.service.PostService;
import com.nhs.social.service.UserService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sonng
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;

    @Override
    public List<CommentDto> getAllCommentsForPost(Posts post) {
        List<Comments> comments= this.commentRepository.findByPostId(post);
        List<CommentDto> commentDtos=new ArrayList<>();
        comments.forEach(com->{
            commentDtos.add(this.toCommentDto(com));
        });
        return commentDtos;
    }

    @Override
    public CommentDto createComment(String content, Posts post, Users user) {
        Comments com = new Comments();
        com.setContent(content);
        com.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
        com.setPostId(post);
        com.setUserId(user);
        this.commentRepository.save(com);
        return this.toCommentDto(com);
    }

    @Override
    public boolean deleteComment(int commentID, int postID, Users user) {
        if (checkAuth(commentID, user, this.postService.getPostById(postID))) {
            this.commentRepository.delete(this.commentRepository.findById(commentID).get());
            return true;
        }
        return false;
    }

    @Override
    public Comments updateComment(CommentDto com, int postID, Users user) {
        if (checkAuth(com.getCommentId(), user, this.postService.getPostById(postID))) {
            Comments comment=this.commentRepository.findById(com.getCommentId()).get();
            comment.setContent(com.getContent());
            this.commentRepository.save(comment);
            return comment;
        }
        return null;
    }

    @Override
    public boolean replyComment(CommentDto commentDto, int commentID, int postID, Users user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CommentDto toCommentDto(Comments comment) {
        if(comment==null) return null;
        CommentDto com = CommentDto.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .createAt(comment.getCreateAt())
                .user(this.userService.toUserDto(comment.getUserId()))
                .postId(comment.getPostId().getPostId())
                .build();
        return com;
    }

    @Override
    public boolean checkAuth(int commentId, Users user, Posts post) {
        return this.commentRepository.findByCommentIdAndUserIdAndPostId(commentId, user, post).isPresent();
    }
}
