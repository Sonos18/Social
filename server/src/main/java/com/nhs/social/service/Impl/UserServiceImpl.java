/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service.Impl;

import com.nhs.social.pojo.Users;
import com.nhs.social.repository.UserRepository;
import com.nhs.social.service.ImgService;
import com.nhs.social.service.UserService;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.HashSet;
import java.util.Map;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private ImgService imgService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = UserRepository.findUserByUsername(username);

        if (users == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(users.getRole()));

        return new org.springframework.security.core.userdetails.User(
                users.getUsername(), users.getPassword(), authorities);
    }

    @Override
    public Users findUserByUsername(String username) {
        return this.UserRepository.findUserByUsername(username);
    }

    @Override
    public Users addUser(Map<String, String> params, MultipartFile file) {
        Users u = new Users();
        u.setUsername(params.get("username"));
        u.setPassword(this.passwordEncoder.encode(params.get("password")));
        u.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        u.setEmail(params.get("email"));
        u.setRole("USER");
        u.setAvatar(this.imgService.Cloudinary(file).get("secure_url").toString());
        return UserRepository.save(u);
    }

}
