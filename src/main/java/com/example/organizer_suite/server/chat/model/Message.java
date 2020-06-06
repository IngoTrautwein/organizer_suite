package com.example.organizer_suite.server.chat.model;

public class Message extends ChatModel {

	private static final long serialVersionUID = -1289884181275055701L;
	
	private String text = "";
	
	public Message() {
		super();
		text = "no message set";
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
