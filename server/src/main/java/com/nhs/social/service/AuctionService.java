/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhs.social.Dto.AuctionDto;
import com.nhs.social.Dto.CommentDto;
import com.nhs.social.pojo.Auction;
import com.nhs.social.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
public interface AuctionService {
    List<AuctionDto> getAllAuctions();
    
    Auction getAuctionById(int id);

    Auction createAuction(Map<String, String> params,Users user, MultipartFile imgFile)throws JsonProcessingException;

    boolean deleteAuction(int auctionId,Users user);
    
    AuctionDto toAuctionDto(Auction auction);
    
    AuctionDto winningBid(Map<String, String> params,Users user);
    
    List<CommentDto> choseWinner(int auctionId);
    
    boolean winner(int auctionId,Users user);
}
