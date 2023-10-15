/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.repository;

import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Report;
import com.nhs.social.pojo.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public interface ReportRepository extends JpaRepository<Report, Integer> {

    List<Report> findByPostIdIsNull();

    List<Report> findByAuctionIdIsNull();

    Page<Report> findByPostIdIsNotNull(Pageable pageable);

    Page<Report> findByPostIdIsNull(Pageable pageable);
    
    @Query("SELECT r FROM Report r WHERE r.userId = :userId AND r.postId = :postId")
    Optional<Report> findReportByUserAndPost(@Param("userId") Users userId, @Param("postId") Posts postId);

}
