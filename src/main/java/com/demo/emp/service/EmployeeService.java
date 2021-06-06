package com.demo.emp.service;

import java.util.List;
import java.util.Optional;

import com.demo.emp.entity.Employee;

public interface EmployeeService {

	public boolean addEmployee(Employee employee);

	public Optional<Employee> getEmployee(Long id);

	public List<Employee> getAllEmployees();

	public boolean deleteEmployee(Long id);

	public boolean updateEmployee(Long id, Employee employee);

}
