/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhs.social.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhs.social.Dto.ProductsDto;
import com.nhs.social.pojo.Products;
import com.nhs.social.repository.ProductRepository;
import com.nhs.social.service.ProductService;
import java.io.IOException;
import java.util.Map;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Products createProduct(ProductsDto pro, MultipartFile file) {
        Products product = new Products();
        product.setDescription(pro.getDescription());
        if (file != null) {
            try {
                Map res = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                product.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            product.setImage(null);
        }
        product.setName(pro.getName());
        this.productRepository.save(product);
        return product;
    }

    @Override
    public ProductsDto toProductDto(Products pro) {
        ProductsDto productsDto=ProductsDto.builder()
                .name(pro.getName())
                .description(pro.getDescription())
                .image(pro.getImage())
                .build();
        return productsDto;
    }

}
