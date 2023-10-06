/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDto {
    
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String avatar;
}