/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.service;

import com.nhs.social.Dto.PageDto;
import com.nhs.social.Dto.PostDto;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
public interface PostService {
    
    PageDto getPostsByPage(int page);

    Posts addPost(PostDto p,Users user);

    Posts updatePost(PostDto p,Posts post, MultipartFile file);

    Posts getPostById(int id);

    boolean deletePost(int id, Users user);
    
    Posts isLocked(Posts post);

    List<PostDto> getPostsForUser(Users user);
    
    boolean checkAuth(Users user,int postId);
    
    PostDto toPostDto(Posts p);
    
    
}
