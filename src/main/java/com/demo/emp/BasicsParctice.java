package com.demo.emp;

import org.hibernate.Session;

import com.demo.emp.builder.EmployeeSessionBuilder;
import com.demo.emp.entity.Employee;

public class BasicsParctice {

	public void doPractice() {
//		this.saveAndPersistDiff();
		insertORUpdateData();
//		getAndLoadDiff();
//		deleteTest();
		updateDirtyObjectChecking();

	}

	public void saveAndPersistDiff() {

		Session session = EmployeeSessionBuilder.openSession();

		// Not opening any transaction and performing save() & persist

		// Inserting the employee with save() method
		Employee emp1 = new Employee(new Long(111), "AAA", 1111);
		long newId = (long) session.save(emp1);
		System.out.println(newId); // Always fetching the new Id
		// Hibernate: select next_val as id_val from hibernate_sequence for update
		// Hibernate: update hibernate_sequence set next_val= ? where next_val=?
		// 7

		// Inserting the employee with persist() method
		Employee emp2 = new Employee(new Long(222), "BBB", 2222);
		session.persist(emp2);
		// PersistentObjectException: detached entity passed to persist:
		// com.demo.emp.entity.Employee

		EmployeeSessionBuilder.closeSession(session);
	}

	private void getAndLoadDiff() {
		Session session = EmployeeSessionBuilder.openSession();

		System.out.println("Calling get() method");
		Employee getEmployee = session.get(Employee.class, 111l);
		System.out.println("Calling getName() on getEmployee");
		System.out.println(getEmployee.getName());

		System.out.println("Calling load() method");
		Employee loadEmployee = session.load(Employee.class, 222l);
		System.out.println("Calling getName() on loadEmployee");
		System.out.println(loadEmployee.getName()); // loading the data here

		EmployeeSessionBuilder.closeSession(session);

	}

	// Insert the records if not available or else update the records
	private void insertORUpdateData() {
		Session session = EmployeeSessionBuilder.openSession();

		session.beginTransaction();
		Employee emp1 = new Employee(new Long(111), "AAA", 1111);
		session.saveOrUpdate(emp1);

		Employee emp2 = new Employee(new Long(222), "BBB", 2222);
		session.saveOrUpdate(emp2);

		session.getTransaction().commit();

		EmployeeSessionBuilder.closeSession(session);
	}

	private void deleteTest() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();

		Employee emp = new Employee();
		emp.setId(new Long(222));
		session.delete(emp);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

	private void updateDirtyObjectChecking() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();
		Employee employee = new Employee();
		session.load(employee, 111l);
		employee.setName("Anil Kumar Bandari");

		session.getTransaction().commit();
		// As the object is modified in the first level cache, it will be saved to
		// database
		EmployeeSessionBuilder.closeSession(session);
	}

}



