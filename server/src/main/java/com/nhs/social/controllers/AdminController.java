/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.controllers;

import com.nhs.social.service.ReportService;
import com.nhs.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sonng
 */
@RestController
@RequestMapping("/api/admin/")
@CrossOrigin
public class AdminController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @GetMapping("reports/posts/")
    public ResponseEntity<?> listReportPost() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return new ResponseEntity<>(this.reportService.getAllReportForPost(), HttpStatus.OK);
        }
        return new ResponseEntity<>("You do not have permission to this action", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("reports/auctions/")
    public ResponseEntity<?> listReportAuction() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return new ResponseEntity<>(this.reportService.getAllReportForAuction(), HttpStatus.OK);
        }
        return new ResponseEntity<>("You do not have permission to this action", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/users/")
    public ResponseEntity<?> findAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>("You do not have permission to this action", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/users/")
    public ResponseEntity<?> deleteUser(@RequestParam String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            this.userService.deleteUser( this.userService.findUserByUsername(username));
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>("You do not have permission to this action", HttpStatus.UNAUTHORIZED);
    }
}
