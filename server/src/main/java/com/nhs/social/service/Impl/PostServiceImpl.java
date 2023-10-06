/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhs.social.Dto.PostDto;
import com.nhs.social.Dto.UsersDto;
import com.nhs.social.pojo.Hashtags;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import com.nhs.social.repository.HashtagRepository;
import com.nhs.social.repository.PostRepository;
import com.nhs.social.service.LikeService;
import com.nhs.social.service.PostService;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private HashtagRepository hashtagRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikeService likeService;

    @Override
    public List<PostDto> getPosts() {
        List<Posts> posts = this.postRepository.findAllByIsLockedFalseOrderByCreatedAtDesc();
        List<PostDto> postDtos = new ArrayList<>();
        posts.forEach(p -> {
            postDtos.add(this.toPostDto(p));
        });
        return postDtos;
    }

    @Override
    public Posts addPost(PostDto postDto, Users user) {
        if (postDto.getImgFile() != null) {
            try {
                Map res = this.cloudinary.uploader().upload(postDto.getImgFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                postDto.setFile(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            postDto.setFile(null);
        }

//        imgService.Cloudinary(postDto.getImgFile());
        Posts p = new Posts();
        p.setContent(postDto.getContent());
        p.setImage(postDto.getFile());
        p.setIsLocked(Boolean.FALSE);
        p.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if (postDto.getHashtags() != null&&!postDto.getHashtags().isEmpty()) {
            Set<Hashtags> hashtagses = new HashSet<>();
            for (String hString : postDto.getHashtags()) {
                Hashtags h = new Hashtags();
                if (hashtagRepository.findByContent(hString) == null) {
                    h.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
                    h.setHashtagText(hString);
                    hashtagRepository.save(h);
                }
                hashtagses.add(hashtagRepository.findByContent(hString));
            }
            p.setHashtagsSet(hashtagses);
        } 
        p.setUserId(user);
        return this.postRepository.save(p);
    }

    @Override
    public Posts updatePost(PostDto p, Posts post, MultipartFile file) {
        post.setContent(p.getContent());
        post.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        if (!p.getHashtags().isEmpty()) {
            Set<Hashtags> hashtagses = new HashSet<>();
            for (String hString : p.getHashtags()) {
                Hashtags h = new Hashtags();
                if (hashtagRepository.findByContent(hString) == null) {
                    h.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
                    h.setHashtagText(hString);
                    hashtagRepository.save(h);
                }
                hashtagses.add(hashtagRepository.findByContent(hString));
            }
            post.setHashtagsSet(hashtagses);
        }
        if (file != null) {
            try {
                Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                post.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            post.setImage(null);
        }
        return this.postRepository.save(post);
    }

    @Override
    public Posts getPostById(int id) {
        Optional<Posts> postOptional = this.postRepository.findById(id);
        if (postOptional.isPresent()) {
            return postOptional.get();
        }
        return null;
    }

    @Override
    public boolean deletePost(int id, Users user) {
        Optional<Posts> postOptional = this.postRepository.findById(id);
        if (postOptional.isPresent()) {
            if (this.checkAuth(user, id)) {
                this.postRepository.delete(postOptional.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public Posts isLocked(Posts post) {
        post.setIsLocked(!post.getIsLocked());
        return this.postRepository.save(post);
    }

    @Override
    public List<PostDto> getPostsForUser(Users user) {
        return null;

    }

    @Override
    public boolean checkAuth(Users user, int post) {
        return this.postRepository.findByPostIdAndUserId(post, user).isPresent();
    }

    @Override
    public PostDto toPostDto(Posts p) {
        UsersDto userDto = UsersDto.builder()
                .userId(p.getUserId().getUserId())
                .username(p.getUserId().getUsername())
                .avatar(p.getUserId().getAvatar())
                .build();
        List<String> hash = new ArrayList<>();
        p.getHashtagsSet().forEach(h -> {
            hash.add(h.getHashtagText());
        });
        PostDto postDto = PostDto.builder()
                .id(p.getPostId())
                .content(p.getContent())
                .file(p.getImage())
                .isLocked(p.getIsLocked())
                .usersDto(userDto)
                .createAt(p.getCreatedAt())
                .hashtags(hash)
                .build();
        postDto.setUsernameLike(this.likeService.findUsernameLikePost(p));
        return postDto;
    }

}
