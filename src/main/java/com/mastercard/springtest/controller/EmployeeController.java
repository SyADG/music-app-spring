package com.mastercard.springtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.springtest.entity.Employee;
import com.mastercard.springtest.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	// Aggregate root

	@GetMapping("/employees")
	List<Employee> all() {
		return empService.all();
	}

	@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return empService.newEmployee(newEmployee);
	}

	// Single item

	@GetMapping("/employees/{id}")
	Employee one(@PathVariable Long id) {
		return empService.one(id);
	}

	@PutMapping("/employees/{id}")
	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return empService.replaceEmployee(newEmployee, id);
	}

	@DeleteMapping("/employees/{id}")
	void deleteEmployee(@PathVariable Long id) {
		empService.deleteEmployee(id);
	}
}
