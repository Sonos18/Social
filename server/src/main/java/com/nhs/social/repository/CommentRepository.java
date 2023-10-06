/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.repository;

import com.nhs.social.pojo.Comments;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sonng
 */
@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {

    Optional<Comments> findByCommentIdAndUserIdAndPostId(Integer commentId, Users userId, Posts postId);

    @Query("SELECT c FROM Comments c WHERE c.postId = :postId")
    List<Comments> findByPostId(@Param("postId") Posts postId);
}
