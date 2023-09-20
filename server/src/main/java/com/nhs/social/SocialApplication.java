package com.nhs.social;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
public class SocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialApplication.class, args);
	}
 
        @Bean
        public Cloudinary cloudinary(){
             Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dakmujrnc",
                "api_key", "721677335269932",
                "api_secret", "ZKXhc49Ev7cB7p89qys8XsWAipI",
                "secure", true));
        return cloudinary;
        }
        
        @Bean
        public MultipartResolver multipartResolver(){
            return new StandardServletMultipartResolver();
        }
        @Bean 
        public ModelMapper modelMapper(){
            return new ModelMapper();
        }
        @Bean
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
}
