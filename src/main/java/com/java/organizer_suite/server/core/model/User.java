package com.java.organizer_suite.server.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "user")
public class User extends BaseModel {

	private static final long serialVersionUID = -4134034044082369218L;
	
	@NotBlank(message = "Firstname is mandatory")
	@Size(min=3, max=40)
	@Column(name = "firstname", length = 40, nullable = false)
	private String firstname = "";
	@NotBlank(message = "Surname is mandatory")
	@Size(min=3, max=40)
	@Column(name = "Surname", length = 40, nullable = false)
	private String surname = "";
	
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
