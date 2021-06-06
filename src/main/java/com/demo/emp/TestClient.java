package com.demo.emp;

public class TestClient {

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
//		new EmployeeDBAllOperations().performEmployeeDBOperations();
		new BasicsParctice().doPractice();
		

	}

}
