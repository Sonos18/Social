/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service.Impl;

import com.nhs.social.pojo.Likes;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import com.nhs.social.repository.LikeRepository;
import com.nhs.social.service.LikeService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sonng
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<String> findUsernameLikePost(Posts p) {
        List<Likes> likes = likeRepository.findByPostId(p);
        List<String> usernames = new ArrayList<>();
        for (Likes like : likes) {
            usernames.add(like.getUserId().getUsername());
        }
        return usernames;
    }

    @Override
    public boolean like(Posts p, Users user) {
        Optional<Likes> likeOptional=this.likeRepository.findByPostIdAndUserId(p, user);
        if(likeOptional.isPresent()){
           this.likeRepository.delete(likeOptional.get());
        }
        else{
            Likes like=new Likes();
            like.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
            like.setPostId(p);
            like.setUserId(user);
            this.likeRepository.save(like);
        }
        return true;
    }
}
