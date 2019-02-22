package com.saqib.spring.dao;

import java.util.Base64;
import java.util.List;

import com.saqib.spring.model.Person;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

@Repository
public class DAOImp {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Person> getAllPersons() {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Person.class);
		List<Person> Persons = (List<Person>) criteria.list();
		session.getTransaction().commit();
		System.out.println(Persons.size());
		return Persons;
	}
	
	public Person getPerson(long id, byte[] authHeader) {
		try {
			Person person = getSessionFactory().openSession().get(Person.class, id);
			if (authenticate(authHeader, person))
				return person;
			else
				return null;
		}
		catch (ObjectNotFoundException e) {
			return null;
		}
	}

	public Person updatePerson(Person person) {
		Session session = getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.update(person);
		transaction.commit();
		return person;
	}

	public boolean deletePerson(long personId, byte[] authHeader)
	{
		try {
			Session session = getSessionFactory().openSession();
			Object persistentInstance = session.load(Person.class, personId);
			if (persistentInstance != null && authenticate(authHeader, (Person) persistentInstance)) {
				Transaction transaction = session.beginTransaction();
				session.delete((Person) persistentInstance);
				transaction.commit();
				return true;
			}
			return false;
		}
		catch (ObjectNotFoundException e) {
			return false;
		}
	}

	public boolean insertPerson(Person person) {
		try {
			Session session = getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.save(person);
			transaction.commit();
			return true;
		}
		catch (PersistenceException e) {
			return false;
		}
	}

	private boolean authenticate(byte[] base64Auth, Person person) {
		if (person != null) {
			String decoded = new String(Base64.getDecoder().decode(base64Auth));
			String username = decoded.substring(0, decoded.indexOf('|'));
			String password = decoded.substring(decoded.indexOf('|') + 1);
			if (username.equals(person.getUsername()) && password.equals(person.getPassword()))
				return true;
		}
		return false;
	}
}
