/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.Dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DELL
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionDto {

    private Integer auctionId;

    private Date startTime;

    private Date endTime;

    private BigDecimal startingPrice;

    private BigDecimal buyoutPrice;

    private BigDecimal winningBid;

    private ProductsDto productsDto;
    
    private UsersDto winnerUser;

}
