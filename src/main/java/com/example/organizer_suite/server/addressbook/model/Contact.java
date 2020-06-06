package com.example.organizer_suite.server.addressbook.model;

public class Contact extends AddressBookModel {

	private static final long serialVersionUID = -7761143804504466412L;
	
	private String name = "";
	private String address = "";

	public Contact() {
		super();
		this.name = "no name";
		this.address = "no address";
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
