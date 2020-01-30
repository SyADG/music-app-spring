package com.mastercard.springtest.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mastercard.springtest.repository.EmployeeRepository;

@Configuration
public class LoadDatabase {

	
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
		};
	}
	
}