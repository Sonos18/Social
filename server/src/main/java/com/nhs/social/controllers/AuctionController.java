/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhs.social.Dto.AuctionDto;
import com.nhs.social.pojo.Users;
import com.nhs.social.service.AuctionService;
import com.nhs.social.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private UserService userService;

    @GetMapping("/auctions/")
    public ResponseEntity<?> listAuctions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return new ResponseEntity<>(this.auctionService.getAllAuctions(), HttpStatus.OK);
        }
        return new ResponseEntity<>("You do not have permission to this action", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(path = "/auction/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<?> createAuction(@RequestParam Map<String, String> params, @RequestPart("imgFile") MultipartFile imgFile) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            AuctionDto auctionDto = this.auctionService.toAuctionDto(this.auctionService.createAuction(params, currentUser, imgFile));
            if (auctionDto != null) {
                return new ResponseEntity<>(auctionDto, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("RequestRight", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/auction/{id}/")
    @CrossOrigin
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            if(this.auctionService.deleteAuction(id, currentUser))
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
