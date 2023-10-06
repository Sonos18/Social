/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.service;

import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import java.util.List;

/**
 *
 * @author sonng
 */
public interface LikeService {
    List<String> findUsernameLikePost(Posts p);
    boolean like(Posts p,Users user);
}
