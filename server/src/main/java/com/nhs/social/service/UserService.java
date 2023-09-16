/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service;

import com.nhs.social.pojo.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author sonng
 */
public interface UserService extends UserDetailsService{
    Users findUserByUsername(String username);
}
