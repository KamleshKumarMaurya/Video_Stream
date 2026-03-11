package com.octal.demo.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(ObjectUtils.asMap("cloud_name", "dfo4xzfkg", "api_key", "235745199769234",
				"api_secret", "1gNsHfY44z6hIs7bjQGqZVgmTMc"));
	}
}
