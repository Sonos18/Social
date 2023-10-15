/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service.Impl;

import com.nhs.social.Dto.AuctionDto;
import com.nhs.social.Dto.PostDto;
import com.nhs.social.Dto.ReportDto;
import com.nhs.social.Dto.UsersDto;
import com.nhs.social.pojo.Auction;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Report;
import com.nhs.social.pojo.Users;
import com.nhs.social.repository.ReportRepository;
import com.nhs.social.service.AuctionService;
import com.nhs.social.service.PostService;
import com.nhs.social.service.ReportService;
import com.nhs.social.service.UserService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sonng
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private UserService userService;

    @Override
    public List<ReportDto> getAllReportForPost() {
        List<Report> reports=this.reportRepository.findByAuctionIdIsNull();
        List<ReportDto>reportDtos=new ArrayList<>();
        reports.forEach(r->{
            reportDtos.add(this.toReportDto(r));
        });
        return reportDtos;
    }
    

    @Override
    public Report createReport(Users user,Map<String, String> params,int id) {
        Report report=new Report();
        if(Boolean.parseBoolean(params.get("isPost")))
        {
            Posts p=this.postService.getPostById(id);
            Optional<Report> reOptional = reportRepository.findReportByUserAndPost(user, p);
            if(p==null|| reOptional.isPresent()) return null;
            report.setPostId(p);
        }else{
            Auction a=this.auctionService.getAuctionById(id);
            if(a==null) return null;
            report.setAuctionId(a);
        }
        report.setUserId(user);
        report.setReason(params.get("reason"));
        report.setReportDate(Timestamp.valueOf(LocalDateTime.now()));
        this.reportRepository.save(report);
        return report;
    }

    @Override
    public ReportDto toReportDto(Report report) {
        UsersDto usersDto = this.userService.toUserDto(report.getUserId());
        ReportDto reportDto = ReportDto.builder()
                .reason(report.getReason())
                .reportDate(report.getReportDate())
                .userId(usersDto)
                .build();
        if (report.getPostId() != null) {
            reportDto.setPostId(report.getPostId().getPostId());
        } else {
            reportDto.setAuctionId(report.getAuctionId().getAuctionId());
        }

        return reportDto;
    }

    @Override
    public List<ReportDto> getAllReportForAuction() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
