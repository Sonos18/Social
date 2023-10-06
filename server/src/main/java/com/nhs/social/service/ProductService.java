/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nhs.social.service;

import com.nhs.social.Dto.ProductsDto;
import com.nhs.social.pojo.Products;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonng
 */
public interface ProductService {
    Products createProduct(ProductsDto pro,MultipartFile file);
    ProductsDto toProductDto(Products pro);
}
