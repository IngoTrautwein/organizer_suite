package com.example.organizer_suite.server.calendar.model;

import java.time.LocalDateTime;

public class Appointment extends CalendarModel {

	private static final long serialVersionUID = 8646524616320679624L;
	
	private String title = "";
	private LocalDateTime date = null;
	
	public Appointment() {
		super();
		this.title = "no title";
		this.date = LocalDateTime.now();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public LocalDateTime getDate() {
		return this.date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
