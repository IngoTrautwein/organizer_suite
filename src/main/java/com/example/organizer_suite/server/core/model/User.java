package com.example.organizer_suite.server.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User extends BaseModel {

	private static final long serialVersionUID = -4134034044082369218L;
	
	@Column(name = "firstname", length = 40, nullable = false)
	private String firstname = "";
	@Column(name = "Surname", length = 40, nullable = false)
	private String surname = "";
	
	public User() {
		this("no first name", "no surname");
	}
	
	public User(String firstName, String surname) {
		super();
		this.firstname = firstName;
		this.surname = surname;
	}
	
	public String getFirstname() {
		return this.firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
