/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhs.social.Dto.PostDto;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import com.nhs.social.service.LikeService;
import com.nhs.social.service.PostService;
import com.nhs.social.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.PutMapping;
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
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @GetMapping("/posts/{id}/")
    public ResponseEntity<?> post(@PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return new ResponseEntity<>(this.postService.toPostDto(this.postService.getPostById(id)), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/posts/{id}/")
    public ResponseEntity<?> update(@PathVariable(value = "id") int id, @RequestParam Map<String, String> params, @RequestParam("imgFile") MultipartFile imgFile) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            if (!this.postService.checkAuth(currentUser, id)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            Posts post = this.postService.getPostById(id);
            ObjectMapper mapper = new ObjectMapper();
            String hashtagsJson = params.get("hashtags");
            List<String> hashtagList = Arrays.asList(mapper.readValue(hashtagsJson, String[].class));
            System.out.println(hashtagList.toString());
            PostDto postDto = PostDto.builder()
                    .content(params.get("content"))
                    .hashtags(hashtagList)
                    .imgFile(imgFile)
                    .build();
            postDto = this.postService.toPostDto(this.postService.updatePost(postDto, post, imgFile));
            if (postDto != null) {
                return new ResponseEntity<>(postDto, HttpStatus.OK);
            }
            return new ResponseEntity<>("Server is not active", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/posts/{id}/")
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            if (this.postService.getPostById(id) == null) {
                return new ResponseEntity<>("Post is not exit", HttpStatus.BAD_REQUEST);
            }
            if (this.postService.deletePost(id, currentUser)) {
                return new ResponseEntity<>("Successfull", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>("You do not have Authen to delete this post", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/posts/{id}/like/")
    public ResponseEntity<?> like(@PathVariable(value = "id") int id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            Posts post = this.postService.getPostById(id);
            if (post != null) {
                return new ResponseEntity<>(this.likeService.like(post, currentUser), HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Post is not exit", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/posts/")
    public ResponseEntity<?> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return new ResponseEntity<>(this.postService.getPosts(), HttpStatus.OK);
        }
        return new ResponseEntity<>("You do not have permission to this action", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/posts/")
    @CrossOrigin
    public ResponseEntity<?> add(@RequestParam Map<String, String> params, @RequestParam("imgFile") MultipartFile imgFile) throws JsonProcessingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            List<String> hashtagList=new ArrayList<>();
            if (params.get("hashtags") != null) {
                ObjectMapper mapper = new ObjectMapper();
                String hashtagsJson = params.get("hashtags");
                hashtagList = Arrays.asList(mapper.readValue(hashtagsJson, String[].class));
            }
            PostDto postDto = PostDto.builder()
                    .content(params.get("content"))
                    .hashtags(hashtagList)
                    .imgFile(imgFile)
                    .build();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            PostDto post = this.postService.toPostDto(this.postService.addPost(postDto, currentUser));
            if (post != null) {
                return new ResponseEntity<>(post, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Server is not active", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/posts/{id}/lock/")
    public ResponseEntity<?> lock(@PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            Posts post = this.postService.getPostById(id);
            if (post == null) {
                return new ResponseEntity<>("Post is not exit", HttpStatus.BAD_REQUEST);
            }
            if (this.postService.checkAuth(currentUser, id)) {
                return new ResponseEntity<>(this.postService.toPostDto(this.postService.isLocked(post)), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
