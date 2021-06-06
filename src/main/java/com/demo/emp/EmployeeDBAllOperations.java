package com.demo.emp;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.demo.emp.entity.Employee;
import com.demo.emp.service.EmployeeService;
import com.demo.emp.service.EmployeeServiceImpl;

public class EmployeeDBAllOperations {

	private Scanner scanner = null;
	EmployeeService employeeService = new EmployeeServiceImpl();
	
	public void performEmployeeDBOperations() {
		int choice = 0;
		boolean exitFlag = false;
		scanner = new Scanner(System.in);

		while (!exitFlag) {
			System.out.println("1: Add Employee\n" + 
							   "2: Get Employee\n" + 
					           "3: Delete Employee\n" +
							   "4: Update Employee\n" +
							   "5: Get All Employees\n" +
							   "6: Exit");

			System.out.println("Enter your choice:\n");
			choice = scanner.nextInt();
				switch (choice) {
				case 1: {
					addEmployee();
					break;
				}
				case 2: {
					getEmployee();
					break;
				}
				case 3: {
					deleteEmployee();
					break;
				}
				case 4: {
					updateEmployee();
					break;
				}
				case 5: {
					getAllEmployees();
					break;
				}
				case 6: {
					System.out.println("Are you sure you want to exit the Application: 'Y' or 'N' \n");
					String existChoice = scanner.next();
					if(null != existChoice && existChoice.equalsIgnoreCase("Y")) {
						exitFlag = true;
						scanner.close();
						scanner = null;
						System.out.println("Thank you, Visit again :)");
					}
					break;
				}
				default: {
					System.out.println("Wrong Choice");
					break;
				}
			}
		}
	}

	private void addEmployee() {
		employeeService.addEmployee(getEmployeeDetails());
		System.out.println("Employee saved successfully");

	}

	private void getEmployee() {
		System.out.println("Enter the employee id:\n");
		Long id = scanner.nextLong();
		Optional<Employee> optional = employeeService.getEmployee(id);
		if(optional.isPresent()) {
			System.out.println(optional.get());
		}else {
			System.out.println("No Employee found with id: " + id);
		}
		
	}
	
	private void getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		System.out.println(employees);
	}

	private void deleteEmployee() {
		System.out.println("Enter the employee id to delete:\n");
		Long id = scanner.nextLong();
		boolean flag = employeeService.deleteEmployee(id);
		if(flag) {
			System.out.println("Employee deleted successfully");
		}else {
			System.out.println("Failed to delete employee");
		}

	}

	private void updateEmployee() {
		Employee employee = getEmployeeDetails();
		boolean flag = employeeService.updateEmployee(employee.getId(), employee);
		
		if(flag) {
			System.out.println("Employee updated successfully");
		}else {
			System.out.println("Failed to update employee");
		}

	}

	private Employee getEmployeeDetails() {
		Employee employee = new Employee();

		System.out.println("Enter the employee id:\n");
		Long id = scanner.nextLong();

		System.out.println("Enter the employee name:\n");
		String name = scanner.next();

		System.out.println("Enter the employee salary:\n");
		Long salary = scanner.nextLong();

//		System.out.println("Enter the employee role:\n");
//		String role = scanner.next();
//
//		System.out.println("Enter the employee email:\n");
//		String email = scanner.next();

		employee.setId(id);
		employee.setName(name);
		employee.setSalary(salary);
//		employee.setRole(role);
//		employee.setEmail(email);
		return employee;
	}

	@Override
	protected void finalize() throws Throwable {
		if (scanner != null)
			scanner.close();
	}

}
