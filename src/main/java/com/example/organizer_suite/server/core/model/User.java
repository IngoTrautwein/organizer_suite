package com.example.organizer_suite.server.core.model;

public class User extends BaseModel {

	private static final long serialVersionUID = -4134034044082369218L;
	
	private String firstName = "";
	private String surname = "";
	
	public User() {
		super();
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}
