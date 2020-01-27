package com.mastercard.springtest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mastercard.springtest.entity.Employee;
import com.mastercard.springtest.exceptions.EmployeeNotFoundException;
import com.mastercard.springtest.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	public List<Employee> all() {
		return repository.findAll();
	}

	public Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}

	public Employee one(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return repository.findById(id).map(employee -> {
			employee.setName(newEmployee.getName());
			employee.setRole(newEmployee.getRole());
			return repository.save(employee);
		}).orElseGet(() -> {
			newEmployee.setId(id);
			return repository.save(newEmployee);
		});
	}

	public void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
