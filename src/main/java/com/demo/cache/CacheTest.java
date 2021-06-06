package com.demo.cache;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.demo.emp.builder.EmployeeSessionBuilder;
import com.demo.emp.entity.Employee;

public class CacheTest {

	static {
		try {
			Class.forName("com.demo.emp.builder.EmployeeSessionBuilder");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static void main(String arr[]) {
		CacheTest test = new CacheTest();
		List<Long> generatedIds = test.insertEmployeeRecords();
//		test.testCache(generatedIds);
		test.testQueryCache(generatedIds);
	}

	public List<Long> insertEmployeeRecords() {
		List<Long> generatedIds = new ArrayList<>();

		try (Session session = EmployeeSessionBuilder.openSession();) {
			session.beginTransaction();

			Employee employee1 = new Employee();
			employee1.setName("Anil");
			employee1.setSalary(10000);

			Employee employee2 = new Employee();
			employee2.setName("Manu");
			employee2.setSalary(20000);

			Long id1 = (Long) session.save(employee1);
			Long id2 = (Long) session.save(employee2);

			generatedIds.add(id1);
			generatedIds.add(id2);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return generatedIds;
	}

	public void testCache(List<Long> generatedIds) {
		if (null == generatedIds || generatedIds.isEmpty()) {
			generatedIds = new ArrayList<>();
			generatedIds.add(Long.valueOf(1));
			generatedIds.add(Long.valueOf(2));
		}

		// Clearing the global cache from SessionFactory
		Cache cache = EmployeeSessionBuilder.getSessionFacoty().getCache();
		if (cache != null) {
			cache.evictAllRegions(); // Evict data from all query regions.
		}

		Session session1 = EmployeeSessionBuilder.openSession();
		Employee emp1 = session1.load(Employee.class, generatedIds.get(0));
		System.out.println(emp1.getName());
		Employee emp11 = session1.load(Employee.class, generatedIds.get(0));
		System.out.println(emp11.getName());
		Employee emp2 = session1.load(Employee.class, generatedIds.get(1));
		System.out.println(emp2.getName());

		EmployeeSessionBuilder.closeSession(session1);

		Session session2 = EmployeeSessionBuilder.openSession();
		Employee emp3 = session2.load(Employee.class, generatedIds.get(0));
		System.out.println(emp3.getName());
		EmployeeSessionBuilder.closeSession(session2);

	}

	public void testQueryCache(List<Long> generatedIds) {
		if (null == generatedIds || generatedIds.isEmpty()) {
			generatedIds = new ArrayList<>();
			generatedIds.add(Long.valueOf(1));
			generatedIds.add(Long.valueOf(2));
		}

		// Clearing the global cache from SessionFactory
		Cache cache = EmployeeSessionBuilder.getSessionFacoty().getCache();
		if (cache != null) {
			cache.evictAllRegions(); // Evict data from all query regions.
		}

		Session session1 = EmployeeSessionBuilder.openSession();
		Query<Employee> query = session1.createQuery("from Employee  where id=:firstId or id=:secondId",
				Employee.class);
		query.setParameter("firstId", generatedIds.get(0));
		query.setParameter("secondId", generatedIds.get(1));
		List<Employee> list = query.list();
		list.forEach(emp -> {
			System.out.println(emp);
		});

		Query<Employee> query1 = session1.createQuery("from Employee  where id=:firstId or id=:secondId",
				Employee.class);
		query1.setParameter("firstId", generatedIds.get(0));
		query1.setParameter("secondId", generatedIds.get(1));
		List<Employee> list1 = query1.list();
		System.out.println(list1.size());

		EmployeeSessionBuilder.closeSession(session1);

		Session session2 = EmployeeSessionBuilder.openSession();

		Query<Employee> query2 = session2.createQuery("from Employee  where id=:firstId or id=:secondId",
				Employee.class);
		query2.setParameter("firstId", generatedIds.get(0));
		query2.setParameter("secondId", generatedIds.get(1));
		List<Employee> list2 = query2.list();
		System.out.println(list2.size());
		EmployeeSessionBuilder.closeSession(session2);

	}

}
