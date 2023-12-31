/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service;

import com.nhs.social.Dto.PageDto;
import com.nhs.social.Dto.PageUserDto;
import com.nhs.social.Dto.UsersDto;
import com.nhs.social.pojo.Users;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
public interface UserService extends UserDetailsService{
    Users findUserByUsername(String username);
    Users addUser(Map<String,String>params, MultipartFile file);
    Users loadUserByGoogle(Map<String, String> params);
    UsersDto toUserDto(Users user);
    PageUserDto findAll(int page);
    void deleteUser(Users user);
}
