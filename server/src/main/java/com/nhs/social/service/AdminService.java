/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.service;

import com.nhs.social.Dto.PageReportDto;
import com.nhs.social.Dto.ReportDto;
import com.nhs.social.pojo.Report;
import java.util.List;
/**
 *
 * @author sonng
 */
public interface AdminService {
    List<Long> statisticalPost(int year);
    PageReportDto findAllByPosts(int page,boolean isPost);
}
