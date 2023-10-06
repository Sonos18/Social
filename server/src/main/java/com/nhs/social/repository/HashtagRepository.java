/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.repository;

import com.nhs.social.pojo.Hashtags;
import java.util.List;
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
public interface HashtagRepository extends JpaRepository<Hashtags, Integer> {

    @Query("SELECT h FROM Hashtags h WHERE h.hashtagText =:hashtagText")
    Hashtags findByContent(@Param("hashtagText") String hashtagText);
}
