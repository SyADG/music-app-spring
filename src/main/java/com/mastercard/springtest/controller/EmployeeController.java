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

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	// Aggregate root

	@GetMapping("/employees")
	public List<Employee> findAll() {
		return empService.findAll();
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee newEmployee) {
		log.info("Adding an employee: " + newEmployee.getName());
		return empService.addEmployee(newEmployee);
	}

	// Single item

	@GetMapping("/employees/{id}")
	public Employee findEmployee(@PathVariable Long id) {
		log.info("Returning the employee number: " + id);
		return empService.findEmployee(id);
	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return empService.updateEmployee(newEmployee, id);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		empService.deleteEmployee(id);
	}
}
