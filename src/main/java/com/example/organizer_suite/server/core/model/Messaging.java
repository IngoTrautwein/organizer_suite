package com.example.organizer_suite.server.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messaging")
public class Messaging extends BaseModel {

	private static final long serialVersionUID = -4735512250145370871L;
	
	private String text = "";
	private Long recipient = null;
	private Long sender = null;
	
	public Messaging() {
		super();
		this.text = "empty message";
		this.recipient = new Long(0);
		this.sender = new Long(0);
		
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Long getRecipient() {
		return this.recipient;
	}
	
	public void setRecipient(Long recipient) {
		this.recipient = recipient;
	}
	
	public Long getSender() {
		return this.sender;
	}
	
	public void setSender(Long sender) {
		this.sender = sender;
	}
	
}
