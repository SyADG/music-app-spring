package com.mastercard.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercard.springtest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}