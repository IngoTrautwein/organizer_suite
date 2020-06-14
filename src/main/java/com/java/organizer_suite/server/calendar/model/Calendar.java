package com.java.organizer_suite.server.calendar.model;

public class Calendar extends CalendarModel {

	private static final long serialVersionUID = 8953739018131185228L;

	private String name = "";
	
	public Calendar() {
		super();
		this.name = "no calendar name set";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
