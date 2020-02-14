package com.mastercard.musicapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages= {"com.mastercard.musicapp.controller","com.mastercard.musicapp.service","com.mastercard.musicapp.config"})
public class MusicAppApplication{

	public static void main(String[] args) {
		SpringApplication.run(MusicAppApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
				;
			}
		};
	}

}
