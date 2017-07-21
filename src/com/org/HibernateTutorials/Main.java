package com.org.HibernateTutorials;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//transient state
		Student student=new Student();
		student.setName("bhawna1");
		
		Configuration config=new Configuration();
		config.addAnnotatedClass(Student.class);
		config.configure("hibernate.cfg.xml");

		SessionFactory sessionFactory=config.configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();

		student.setName("bhawna1 modified before save");
		//here the object is in transient state so will sync
		session.save(student);//now the object is in persistent state so will sync
		student.setName("bhawna1 modified after save");
		
		session.getTransaction().commit();
		//till here the object is in persistent state
		session.close();
		student.setName("bhawna1 modified after detached");
		//here the object is in detached state so will not sync
		sessionFactory.close();
	}

}
