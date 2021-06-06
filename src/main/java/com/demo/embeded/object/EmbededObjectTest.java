package com.demo.embeded.object;

import org.hibernate.Session;

import com.demo.emp.builder.EmployeeSessionBuilder;

public class EmbededObjectTest {

	static {
		try {
			Class.forName("com.demo.emp.builder.EmployeeSessionBuilder");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String arr[]) {
		EmbededObjectTest embededObjectTest = new EmbededObjectTest();
		embededObjectTest.insertEmbeddedObjectRecord();
//		embededObjectTest.getEmbeddedObjectRecord();
	}

	public void insertEmbeddedObjectRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();

		Address homeAddress = new Address();
		homeAddress.setStreet("Yellareddy Pally");
		homeAddress.setCity("Warangal");
		homeAddress.setState("TS");
		homeAddress.setPincode("123456");

		Address officeAddress = new Address();
		officeAddress.setStreet("BHEL");
		officeAddress.setCity("Hyderabad");
		officeAddress.setState("TS");
		officeAddress.setPincode("987654");

		User user = new User();
		user.setName("Anil Kumar");
		user.setSalary(10000);
		user.setHomeAddress(homeAddress);
		user.setOfficeAddress(officeAddress);

		session.save(user);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

	public void getEmbeddedObjectRecord() {
		Session session = EmployeeSessionBuilder.openSession();

		User user = session.load(User.class, new Long(1));
		System.out.println(user);

		EmployeeSessionBuilder.closeSession(session);

	}

}
