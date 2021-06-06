package com.demo.emp.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.emp.builder.EmployeeSessionBuilder;
import com.demo.emp.entity.Employee;


public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public Optional<Employee> getEmployee(Long id) {

		Optional<Employee> optionalEmployee = null;

		Session session = EmployeeSessionBuilder.openSession();
		try {
			Employee employee = new Employee();
			employee.setId(id);
			session.load(employee, id);
			optionalEmployee = Optional.of(employee);
		} catch (Exception e) {
			optionalEmployee = Optional.empty();
			System.out.println("Excpetion occured while fetching employee: " + e.getMessage());
		} finally {
			EmployeeSessionBuilder.closeSession(session);
		}
		return optionalEmployee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEmployee(Employee employee) {

		boolean flag = false;
		Session session = EmployeeSessionBuilder.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			System.out.println("Exception occured while adding employee: " + e.getMessage());
		} finally {
			EmployeeSessionBuilder.closeSession(session);
		}
		return flag;
	}

	@Override
	public boolean deleteEmployee(Long id) {
		boolean flag = false;
		Session session = EmployeeSessionBuilder.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Employee employee = new Employee();
			employee.setId(id);
			session.delete(employee);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			System.out.println("Exception occured while deleting employee: " + e.getMessage());
		} finally {
			EmployeeSessionBuilder.closeSession(session);
		}
		return flag;
	}

	@Override
	public boolean updateEmployee(Long id, Employee employee) {
		boolean flag = false;
		Session session = EmployeeSessionBuilder.openSession();
		try {

			Transaction transaction = session.beginTransaction();
			session.update(employee);
			transaction.commit();

			flag = true;
		} catch (Exception e) {
			System.out.println("Exception occured while updating employee: " + e.getMessage());
		} finally {
			EmployeeSessionBuilder.closeSession(session);
		}
		return flag;
	}

}
