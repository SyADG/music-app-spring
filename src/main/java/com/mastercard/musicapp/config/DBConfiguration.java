package com.mastercard.musicapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {

	@Profile("dev")
	@Bean
	public String  devDataBaseConnection() {
		return "DB Connection to Dev mode"; 
	}
	@Profile("prod")
	@Bean
	public String  prodDataBaseConnection() {
		return "DB Connection to Prod mode"; 
	}
}