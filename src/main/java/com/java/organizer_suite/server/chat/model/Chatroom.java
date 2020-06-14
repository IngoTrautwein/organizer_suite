package com.java.organizer_suite.server.chat.model;

public class Chatroom extends ChatModel {

	private static final long serialVersionUID = 2581164132206892146L;
	
	private String topic = "";
	
	public Chatroom() {
		super();
		this.topic = "no topic set";
	}
	
	public String getTopic() {
		return this.topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
}
