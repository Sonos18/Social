/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
public interface ImgService {
    Map Cloudinary(MultipartFile file);
}
