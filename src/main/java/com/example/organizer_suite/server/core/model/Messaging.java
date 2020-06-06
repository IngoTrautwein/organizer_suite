package com.example.organizer_suite.server.core.model;

public class Messaging extends BaseModel {

	private static final long serialVersionUID = -4735512250145370871L;
	
	private String text = "";
	
	public Messaging() {
		super();
		this.text = "empty message";
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
