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
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sonng
 */
@Repository
@Transactional
public interface LikeRepository extends JpaRepository<Likes, Integer> {

    List<Likes> findByPostId(Posts post);

    @Query("SELECT l FROM Likes l WHERE l.postId = :postId AND l.userId = :userId")
    Optional<Likes> findByPostIdAndUserId(@Param("postId") Posts postId, @Param("userId") Users userId);
}
