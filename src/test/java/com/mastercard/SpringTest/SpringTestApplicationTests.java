package com.mastercard.SpringTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mastercard.springtest.entity.Employee;
import com.mastercard.springtest.repository.EmployeeRepository;
import com.mastercard.springtest.service.EmployeeService;



@ExtendWith(MockitoExtension.class)
@RunWith(JUnit4.class)
class SpringTestApplicationTests {
	@InjectMocks
	EmployeeService employeeService;

	@Mock
	EmployeeRepository employeeRepository;

	@Test
	public void findByIdTestMustReturnNotNull() {
		Employee employee = new Employee();
		when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.of(employee));
		employee = employeeService.findEmployee(any(Long.class));
		assertNotNull(employee);
	}

	@Test
	public void getAllEmployeesTestSohuldReturnNotNull() {
		List<Employee> list = new ArrayList<Employee>();
		Employee empOne = new Employee("Johnny", "Johnny");
		Employee empTwo = new Employee("Alex", "Macarena");
		Employee empThree = new Employee("Steve", "Maul");

		list.add(empOne);
		list.add(empTwo);
		list.add(empThree);

		when(employeeService.findAll()).thenReturn(list);

		List<Employee> empList = employeeService.findAll();

		assertEquals(3, empList.size());
	}

	@Test
	public void createEmployeeTestShouldReturnNotNull() {
		Employee emp = new Employee("Loki", "God");
		employeeService.addEmployee(emp);
		assertEquals("Loki", emp.getName());
		assertEquals("God", emp.getRole());
	}
}