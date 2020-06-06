package com.example.organizer_suite.server.addressbook.model;

public class ContactList extends AddressBookModel {

	private static final long serialVersionUID = -2207244154426088416L;
	
	private String name = "";
	
	public ContactList() {
		this.name = "empty contact list";
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
