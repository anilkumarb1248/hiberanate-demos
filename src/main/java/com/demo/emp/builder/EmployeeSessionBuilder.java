package com.demo.emp.builder;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class EmployeeSessionBuilder {

	private static StandardServiceRegistry serviceRegistry = null;
	private static SessionFactory sessionFactory = null;
	private static List<Session> sessionsList = new ArrayList<>();

	static {
		String hibConfigFile = "";
		if (DatabaseType.IS_MYSQL_DB) {
			hibConfigFile = DatabaseType.MYSQL_DB_HIB_CONFIG_FILE;
		} else {
			hibConfigFile = DatabaseType.ORACLE_DB_HIB_CONFIG_FILE;
		}

		// It check for "hibernate.cfg.xml" only
		// new StandardServiceRegistryBuilder().configure().build();

		// If the hibernate configuration file is other than "hibernate.cfg.xml", we
		// need to specify the name
		serviceRegistry = new StandardServiceRegistryBuilder().configure(hibConfigFile).build();

		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();

	}

	private EmployeeSessionBuilder() {
	}
	
	public static SessionFactory getSessionFacoty() {
		return sessionFactory;
	}

	public static Session openSession() {
		if (null == sessionFactory) {
			throw new RuntimeException("SessionFactory object is not found for MySql");
		}
		Session session = sessionFactory.openSession();
		sessionsList.add(session);
		return session;
	}

	public static void closeSession(Session session) {
		if (null != session) {
			sessionsList.remove(session);
			session.close();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		sessionsList.forEach(session -> {
			session.close();
		});

		if (null != sessionFactory) {
			sessionFactory.close();
		}

		if (null != serviceRegistry) {
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
		}

	}

}
