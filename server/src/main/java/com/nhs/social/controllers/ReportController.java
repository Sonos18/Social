/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.controllers;

import com.nhs.social.Dto.ReportDto;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Report;
import com.nhs.social.pojo.Users;
import com.nhs.social.service.ReportService;
import com.nhs.social.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sonng
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;

    @PostMapping("/reports/{id}/")
    public ResponseEntity<?> createReport(@RequestParam Map<String, String> params, @PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            Report r = this.reportService.createReport(currentUser, params, id);
            if (r != null) {
                ReportDto reportDto = this.reportService.toReportDto(r);
                return new ResponseEntity<>(reportDto, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Post is not exit", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
