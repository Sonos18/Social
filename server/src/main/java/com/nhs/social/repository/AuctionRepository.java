/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.repository;

import com.nhs.social.pojo.Auction;
import com.nhs.social.pojo.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sonng
 */
@Transactional
@Repository
public interface AuctionRepository extends JpaRepository<Auction, Integer>{
     List<Auction> findAllByWinnerUserIdIsNull();
     Optional<Auction> findByUserIdAndAuctionId(Users userId, Integer auctionId);
}
