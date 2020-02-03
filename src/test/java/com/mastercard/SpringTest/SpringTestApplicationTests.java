//package com.mastercard.SpringTest;
//
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//
//@ExtendWith(MockitoExtension.class)
//@RunWith(JUnit4.class)
//class SpringTestApplicationTests {
//	@InjectMocks
//	EmployeeService employeeService;
//
//	@Mock
//	EmployeeRepository employeeRepository;
//
//	@Test
//	public void findByIdTestMustReturnNotNull() {
//		Employee employee = new Employee();
//		when(employeeRepository.findById(any(Long.class))).thenReturn(Optional.of(employee));
//		employee = employeeService.findEmployee(any(Long.class));
//		assertNotNull(employee);
//	}
//
//	@Test
//	public void getAllEmployeesTestSohuldReturnNotNull() {
//		List<Employee> list = new ArrayList<Employee>();
//		Employee empOne = new Employee(1l, "Jones", "Bronson", "994494494", "jonesl@ht.com");
//		Employee empTwo = new Employee(2l, "Samanta", "ASD", "23423134", "Samanta@Samanta.com");
//		Employee empThree = new Employee(3l, "Srararararar", "Rar", "2361354", "Sara@rar.com");
//
//		list.add(empOne);
//		list.add(empTwo);
//		list.add(empThree);
//
//		when(employeeService.findAll()).thenReturn(list);
//
//		List<Employee> empList = employeeService.findAll();
//
//		assertEquals(3, empList.size());
//	}
//
//	@Test
//	public void createEmployeeTestShouldReturnNotNull() {
//		Employee emp = new Employee(1l, "Loki", "God", "12312", "Loki@hhh.com");
//		employeeService.addEmployee(emp);
//		assertEquals("Loki", emp.getFirstName());
//		assertEquals("God", emp.getLastName());
//		assertEquals("12312", emp.getPhone());
//		assertEquals("Loki@hhh.com", emp.getEmail());
//	}
//}