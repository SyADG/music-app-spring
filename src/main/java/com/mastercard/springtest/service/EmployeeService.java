package com.mastercard.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mastercard.springtest.entity.Employee;
import com.mastercard.springtest.exceptions.EmployeeNotFoundException;
import com.mastercard.springtest.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public List<Employee> findAll() {
		log.info("Listing all employee");
		return repository.findAll();
	}

	public Employee addEmployee(@RequestBody Employee newEmployee) {
		log.info("Adding employee named: " + newEmployee.getName());
		log.info("Role: " + newEmployee.getRole());
		return repository.save(newEmployee);
	}

	public Employee findEmployee(@PathVariable Long id) {
		log.info("Returning the employee number: " + id);
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	public Employee updateEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return repository.findById(id).map(employee -> {
			log.info("Changing the employee: " + id);
			log.info("New name: " + newEmployee.getName());
			log.info("New role: " + newEmployee.getRole());
			employee.setName(newEmployee.getName());
			employee.setRole(newEmployee.getRole());
			return repository.save(employee);
		}).orElseGet(() -> {
			log.info("Could not found Employee " + id);
			newEmployee.setId(id);
			return repository.save(newEmployee);
		});
	}

	public void deleteEmployee(@PathVariable Long id) {
		if (repository.existsById(id)) {
		    repository.deleteById(id);
			log.info("Employee " + id + " successful deleted");
		}else {
			log.info("Could not found Employee " + id);
		}
	}
}
