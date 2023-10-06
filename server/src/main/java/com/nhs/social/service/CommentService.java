/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.service;

import com.nhs.social.Dto.CommentDto;
import com.nhs.social.pojo.Comments;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import java.util.List;

/**
 *
 * @author sonng
 */
public interface CommentService {

    List<CommentDto> getAllCommentsForPost(Posts post);

    CommentDto createComment(String content, Posts post, Users user);

    boolean deleteComment(int commentID, int postID, Users user);

    Comments updateComment(CommentDto com, int postID, Users user);

    boolean replyComment(CommentDto commentDto, int commentID, int postID, Users user);
    
    CommentDto toCommentDto(Comments comment);
    
    boolean checkAuth(int com,Users user,Posts post);
}
