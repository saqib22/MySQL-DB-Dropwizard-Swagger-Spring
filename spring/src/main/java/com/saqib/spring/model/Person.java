package com.saqib.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "Person" )
public class Person {
	@Id
	private long id;
	private String firstName;
	private String lastName;
	private String mobile;
	private String username;
	private String password;
	private String accessLevel;

	public Person() { }

	public Person(long id, String firstName, String lastName, String mobile,
				  String username, String password, String accessLevel) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.accessLevel = accessLevel;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public String toString() {
		return "First name: " + getFirstName() + "\nLast name: " + getLastName() +
				"\nMobile: " + getMobile() + "\nUsername: " + getUsername() +
				"\nPassword: " + getPassword() +"\nAccess level: " + getAccessLevel() + "\n";
	}
}
