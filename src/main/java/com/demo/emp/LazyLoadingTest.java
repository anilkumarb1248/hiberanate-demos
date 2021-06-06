package com.demo.emp;

import org.hibernate.Session;

import com.demo.emp.builder.EmployeeSessionBuilder;
import com.demo.emp.entity.Employee;

public class LazyLoadingTest {

	// Configure the Hibernate session factory before everything
	static {
		try {
			Class.forName("com.demo.emp.builder.EmployeeSessionBuilder");

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String[] args) {

		// Inserting Some data
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();
		Employee employee = new Employee();
		employee.setName("Anil Kumar");
		employee.setSalary(10000);
		Long employeeId = (Long) session.save(employee);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);

		EmployeeSessionBuilder.getSessionFacoty().getCache().evictAllRegions();

// Testing Lazy load and proxy object with load() method
		Session session1 = EmployeeSessionBuilder.openSession();
		Employee loadedEmp = session1.load(Employee.class, new Long(133));
		System.out.println(loadedEmp.getClass().getName());
		System.out.println(loadedEmp.getClass().getSuperclass().getName());
		System.out.println(loadedEmp.getName());

		EmployeeSessionBuilder.closeSession(session1);

	}

}
