/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.service;

import com.nhs.social.Dto.ReportDto;
import com.nhs.social.pojo.Report;
import com.nhs.social.pojo.Users;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sonng
 */
public interface ReportService {

    List<ReportDto> getAllReportForPost();

    List<ReportDto> getAllReportForAuction();

    Report createReport(Users user,Map<String, String> params,int id);

    ReportDto toReportDto(Report report);
}
