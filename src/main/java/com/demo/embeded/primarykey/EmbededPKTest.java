package com.demo.embeded.primarykey;

import org.hibernate.Session;

import com.demo.emp.builder.EmployeeSessionBuilder;

public class EmbededPKTest {

	static {
		try {
			Class.forName("com.demo.emp.builder.EmployeeSessionBuilder");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String arr[]) {
		EmbededPKTest embededPKTest = new EmbededPKTest();
		embededPKTest.insertEmbeddedPKRecord();
		embededPKTest.getEmbeddedPKRecord();
	}

	public void insertEmbeddedPKRecord() {
		Session session = EmployeeSessionBuilder.openSession();
		session.beginTransaction();

		ProductPK productPK = new ProductPK();
		productPK.setProductId(1);
		productPK.setProductGroupId(5);

		Product product = new Product();
		product.setProductName("Bike");
		product.setPrice(100000);
		product.setProductPK(productPK);
		session.save(product);

		session.getTransaction().commit();
		EmployeeSessionBuilder.closeSession(session);
	}

	public void getEmbeddedPKRecord() {
		Session session = EmployeeSessionBuilder.openSession();

		ProductPK productPK = new ProductPK();
		productPK.setProductId(1);
		productPK.setProductGroupId(5);

		Product product = new Product();
		session.load(product, productPK);
		System.out.println(product);

		EmployeeSessionBuilder.closeSession(session);

	}

}
