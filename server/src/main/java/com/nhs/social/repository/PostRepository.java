/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.repository;

import com.nhs.social.pojo.Likes;
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
@Repository
@Transactional
public interface PostRepository extends JpaRepository<Posts, Integer> {

    List<Posts> findAllByIsLockedFalseOrderByCreatedAtDesc();

    List<Posts> findAllByOrderByCreatedAtDesc();

    @Query("SELECT p FROM Posts p WHERE p.postId = :postId AND p.userId = :userId")
    Optional<Posts> findByPostIdAndUserId(@Param("postId") int postId, @Param("userId") Users userId);

}
