/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.controllers;

import com.nhs.social.pojo.Users;
import com.nhs.social.service.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/current_user")
    public ResponseEntity<?> currentUser(Principal user){
        if(user !=null){
            Users currentUser=this.userService.findUserByUsername(user.getName());
            return new ResponseEntity<>(currentUser,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam Map<String,String>params,MultipartFile file){
        if(userService.addUser(params, file)!=null)
            return ResponseEntity.ok("User registered successfully!");
        return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
    }
}
