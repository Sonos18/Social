/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhs.social.Dto.AuctionDto;
import com.nhs.social.Dto.CommentDto;
import com.nhs.social.Dto.ProductsDto;
import com.nhs.social.pojo.Auction;
import com.nhs.social.pojo.Products;
import com.nhs.social.pojo.Users;
import com.nhs.social.repository.AuctionRepository;
import com.nhs.social.service.AuctionService;
import com.nhs.social.service.ProductService;
import com.nhs.social.service.UserService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Override
    public List<AuctionDto> getAllAuctions() {
        List<Auction> auctions = this.auctionRepository.findAllByWinnerUserIdIsNull();
        List<AuctionDto> auctionDtos = new ArrayList<>();
        auctions.forEach(auction -> {
            auctionDtos.add(this.toAuctionDto(auction));
        });

        return auctionDtos;
    }

    @Override
    public Auction createAuction(Map<String, String> params, Users user, MultipartFile imgFile) throws JsonProcessingException {
        Auction auction = new Auction();
        ProductsDto productsDto = ProductsDto.builder()
                .name(params.get("name"))
                .description(params.get("description"))
                .build();
        Products pro = this.productService.createProduct(productsDto, imgFile);
//        String time = params.get("startTime");
//        auction.setStartTime(new Date(Long.parseLong(time)));
//        String time2 = params.get("endTime");
//        auction.setEndTime(new Date(Long.parseLong(time2)));
        auction.setBuyoutPrice(new BigDecimal(params.get("startingPrice")));
        auction.setStartingPrice(new BigDecimal(params.get("buyoutPrice")));
        auction.setProductId(pro);
        auction.setWinningBid(new BigDecimal(0));
        auction.setUserId(user);
        return this.auctionRepository.save(auction);
    }

    @Override
    public boolean deleteAuction(int auctionId, Users user) {
        Optional<Auction> auction = this.auctionRepository.findByUserIdAndAuctionId(user, auctionId);
        if (auction.isPresent()) {
            this.auctionRepository.delete(auction.get());
            return true;
        }
        return false;
    }

    @Override
    public AuctionDto toAuctionDto(Auction auction) {
        if (auction == null) {
            return null;
        }
        AuctionDto auctionDto = AuctionDto.builder()
                .auctionId(auction.getAuctionId())
                .startTime(auction.getStartTime())
                .endTime(auction.getEndTime())
                .startingPrice(auction.getStartingPrice())
                .buyoutPrice(auction.getBuyoutPrice())
                .winningBid(auction.getWinningBid())
                .build();
        auctionDto.setProductsDto(this.productService.toProductDto(auction.getProductId()));
        if (auction.getWinnerUserId() != null) {
            auctionDto.setWinnerUser(this.userService.toUserDto(auction.getWinnerUserId()));
            auctionDto.setWinningBid(auction.getWinningBid());
        }
        return auctionDto;
    }

    @Override
    public AuctionDto winningBid(Map<String, String> params, Users user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CommentDto> choseWinner(int auctionId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean winner(int auctionId, Users user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Auction getAuctionById(int id) {
        Optional<Auction> optional = this.auctionRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

}
