package com.demo.mappings;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.demo.emp.builder.EmployeeSessionBuilder;

public class OneToManyTest {

	static {
		try {
			Class.forName("com.demo.emp.builder.EmployeeSessionBuilder");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String arr[]) {
		OneToManyTest test = new OneToManyTest();
		test.insertMappingsRecord();
//		test.getMappingsRecord();
		test.deleteMappingRecord();
	}

	public void insertMappingsRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setName("BMW Car");

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setName("AUDI Car");

		Vehicle vehicle3 = new Vehicle();
		vehicle3.setName("Maruthi Car");

		List<Vehicle> vehiclesList = new ArrayList<>();
		vehiclesList.add(vehicle1);
		vehiclesList.add(vehicle2);
		vehiclesList.add(vehicle3);

		Person person = new Person();
		person.setName("Anil");
		person.setVehiclesList(vehiclesList);

		session.save(person);
		session.save(vehicle1);
		session.save(vehicle2);
		session.save(vehicle3);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

	public void getMappingsRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		Person person = session.load(Person.class, Long.valueOf(1));
		System.out.println(person.getName());
		System.out.println(person.getVehiclesList());
		EmployeeSessionBuilder.closeSession(session);

	}

	public void deleteMappingRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();
		
		Person person = new Person();
		person.setId(Long.valueOf(1));
		session.delete(person);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

}
