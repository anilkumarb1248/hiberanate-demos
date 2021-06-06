package com.demo.mappings;

import org.hibernate.Session;

import com.demo.emp.builder.EmployeeSessionBuilder;

public class OneToOneTest {

	static {
		try {
			Class.forName("com.demo.emp.builder.EmployeeSessionBuilder");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String arr[]) {
		OneToOneTest test = new OneToOneTest();
//		test.insertMappingsRecord();
		test.getMappingsRecord();
//		test.deleteMappingRecord();
	}

	public void insertMappingsRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();

		Vehicle vehicle = new Vehicle();
		vehicle.setName("Car");

		Person person = new Person();
		person.setName("Anil");
//		person.setVehicle(vehicle);

		session.save(person);
		session.save(vehicle);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

	public void getMappingsRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		Person person = session.load(Person.class, Long.valueOf(7));
		System.out.println(person.getName());
//		System.out.println(person.getVehicle().getId());
//		System.out.println(person.getVehicle().getName());
		EmployeeSessionBuilder.closeSession(session);

	}

	public void deleteMappingRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

}
