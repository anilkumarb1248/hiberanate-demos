package com.demo.embeded.collection;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.demo.emp.builder.EmployeeSessionBuilder;

public class EmbededCollectionTest {

	static {
		try {
			Class.forName("com.demo.emp.builder.EmployeeSessionBuilder");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String arr[]) {
		EmbededCollectionTest test = new EmbededCollectionTest();
//		test.insertEmbeddedCollectionRecord();
//		test.getEmbeddedCollectionRecord();
		test.checkAddressDeleteWhenUserDelete();
	}

	public void insertEmbeddedCollectionRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();

		List<Address> addressList = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			Address homeAddress = new Address();
			homeAddress.setStreet("Stread_" + i);
			homeAddress.setCity("City_" + i);
			homeAddress.setState("State_" + i);
			homeAddress.setPincode("Pinoce_" + i);

			addressList.add(homeAddress);
		}

		UserDetails user = new UserDetails();
		user.setName("Anil Kumar");
		user.setSalary(10000);
		user.setAddressList(addressList);

		session.save(user);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

	public void getEmbeddedCollectionRecord() {
		Session session = EmployeeSessionBuilder.openSession();

		UserDetails user = session.get(UserDetails.class, new Long(3));
		System.out.println(user.getName());
		System.out.println(user.getSalary());
		System.out.println("Printing addressess");
		System.out.println(user.getAddressList());

		EmployeeSessionBuilder.closeSession(session);

	}

	// When we delete the User data, the address details for that user data will be
	// automatically deleted.
	public void checkAddressDeleteWhenUserDelete() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();

		UserDetails user = session.get(UserDetails.class, new Long(3));
		session.delete(user);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

}
