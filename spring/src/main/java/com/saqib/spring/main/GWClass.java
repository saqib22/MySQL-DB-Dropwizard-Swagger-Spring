package com.saqib.spring.main;

import com.saqib.spring.dao.DAOImp;
import com.saqib.spring.model.Person;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GWClass {
	//
	private DAOImp hibernateDaoImp = null;
	public GWClass(String springPath){
		AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(springPath);
		applicationContext.registerShutdownHook(); 
		hibernateDaoImp = (DAOImp) applicationContext.getBean("hibernateDaoImp");		
	}
	public boolean addPerson(Person person){
		return hibernateDaoImp.insertPerson(person);
	}
	public boolean delPerson(long id, byte[] authHeader) {
		return hibernateDaoImp.deletePerson(id, authHeader);
	}
	public Person getPerson(long id, byte[] authHeader){
		return hibernateDaoImp.getPerson(id, authHeader);
	}

}
