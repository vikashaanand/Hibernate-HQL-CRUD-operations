package com.learn.HQL;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

public class HQL_App {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

//		createDB(session);

//		readDB(session);
		
//		updateDB(session);
		
		delete(session);

		session.close();
		factory.close();

	}

	private static void delete(Session session) {
		
		Transaction transaction = session.beginTransaction();
		
		double newMarks = 78.10;
		Query query = session.createQuery("delete from Student where city =: name");
		query.setParameter("name", "Patna");
		
		int updatedRows = query.executeUpdate();
		
		System.out.println(updatedRows + " row(s) deleted.");
		
		transaction.commit();
		
	}

	private static void updateDB(Session session) {
		
		Transaction transaction = session.beginTransaction();
		
		double newMarks = 78.10;
		Query query = session.createQuery("update Student set marks =: newMarks where city =: name");
		query.setParameter("newMarks", newMarks);
		query.setParameter("name", "Ranchi");
		
		int updatedRows = query.executeUpdate();
		
		System.out.println(updatedRows + " rows updated.");
		
		transaction.commit();
		
	}

	private static void readDB(Session session) {
		
		double x = 82.4;
		Query query = session.createQuery("from Student where marks =: x");
		query.setParameter("x", x);
		List<Student> students = query.list();
		
		students.forEach(s -> System.out.println(s));

	}

	private static void createDB(Session session) {

		List<Student> students = Arrays.asList(

				new Student("Vikash", "Ranchi", 82.4), new Student("Ritesh", "Ranchi", 81),
				new Student("Raju", "Ranchi", 81.5), new Student("Neha Nanhi", "Munger", 92.4),
				new Student("Shivam", "Patna", 86.8), new Student("Shubham", "Deoghar", 72.6),
				new Student("Bhawna", "Deoghar", 62.4), new Student("Anushree", "Ranchi", 58.2),
				new Student("Sukriti", "Patna", 83.4), new Student("Puja", "Patna", 82.4),
				new Student("Ankit", "Jaypur", 82.4));

		Transaction transaction = session.beginTransaction();

		students.stream().forEach(s -> {
			session.save(s);
		});

		transaction.commit();

	}
}
