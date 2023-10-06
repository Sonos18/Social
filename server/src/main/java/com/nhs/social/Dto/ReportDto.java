/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.Dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sonng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDto {

    private Integer reportId;

    private String reason;

    private Date reportDate;

    private int auctionId;

    private int postId;

    private UsersDto userId;
    
    private boolean isPost;
}
