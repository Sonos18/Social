/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.repository;

import com.nhs.social.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sonng
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Integer>{
    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Users findUserByUsername(String username);
}
