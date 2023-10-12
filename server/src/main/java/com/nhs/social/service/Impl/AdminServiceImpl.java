/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service.Impl;

import com.nhs.social.Dto.PageDto;
import com.nhs.social.Dto.PageReportDto;
import com.nhs.social.Dto.ReportDto;
import com.nhs.social.pojo.Report;
import com.nhs.social.repository.PostRepository;
import com.nhs.social.repository.PostRepository.MonthlyPostCount;
import com.nhs.social.repository.ReportRepository;
import com.nhs.social.service.AdminService;
import com.nhs.social.service.PostService;
import com.nhs.social.service.ReportService;
import com.nhs.social.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author sonng
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Long> statisticalPost(int year) {
        List<Long> countPost = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            countPost.add(0L);
        }
        List<MonthlyPostCount> monthlyPostCount = this.postRepository.getMonthlyPostCountsForYear(year);
        for (MonthlyPostCount monthlyCount : monthlyPostCount) {
            countPost.set(monthlyCount.getMonth() - 1, monthlyCount.getPostCount());
        }
        return countPost;
    }

    @Override
    public PageReportDto findAllByPosts(int page, boolean isPost) {
        PageRequest pageable = PageRequest.of(page, 4, Sort.by("reportDate").descending());
        Page<Report> reportPage;
        if (isPost) {
            reportPage = this.reportRepository.findByPostIdIsNotNull(pageable);
        } else {
            reportPage = this.reportRepository.findByPostIdIsNull(pageable);
        }
        List<ReportDto> reportDtos = new ArrayList<>();
        reportPage.getContent().forEach(u -> {
            reportDtos.add(this.reportService.toReportDto(u));
        });
        PageReportDto pageReportDto = PageReportDto.builder()
                .reportDto(reportDtos)
                .totalPage(reportPage.getTotalPages())
                .build();
        return pageReportDto;
    }

}
