/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.controllers;

import com.nhs.social.Dto.CommentDto;
import com.nhs.social.pojo.Posts;
import com.nhs.social.pojo.Users;
import com.nhs.social.service.CommentService;
import com.nhs.social.service.PostService;
import com.nhs.social.service.UserService;
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

/**
 *
 * @author sonng
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    
    @PutMapping("/posts/{id}/comments/")
    public ResponseEntity<?> updateComment(@RequestParam Map<String, String> params, @PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            CommentDto com=CommentDto.builder()
                    .content(params.get("content"))
                    .commentId(Integer.valueOf(params.get("commentId")))
                    .build();
            com = this.commentService.toCommentDto(this.commentService.updateComment(com, id, currentUser));
            if(com!=null) return new ResponseEntity<>(com, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/posts/{id}/comments/")
    public ResponseEntity<?> createComment(@RequestParam Map<String, String> params, @PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Posts post = this.postService.getPostById(id);
            if (post == null) {
                return new ResponseEntity<>("Post is not found", HttpStatus.BAD_REQUEST);
            }
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            CommentDto com = this.commentService.createComment(params.get("content"), post, currentUser);
            return new ResponseEntity<>(com, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/posts/{id}/comments/")
    public ResponseEntity<?> delete(@RequestParam Map<String, String> params, @PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Users currentUser = userService.findUserByUsername(userDetails.getUsername());
            if (this.commentService.deleteComment(Integer.parseInt(params.get("commentId")), id, currentUser)) {
               return new ResponseEntity<>("Successfull", HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
    @GetMapping("/posts/{id}/comments/")
    public ResponseEntity<?> findAll(@RequestParam Map<String, String> params, @PathVariable(value = "id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Posts post=this.postService.getPostById(id);
            return new ResponseEntity<>(this.commentService.getAllCommentsForPost(post),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
