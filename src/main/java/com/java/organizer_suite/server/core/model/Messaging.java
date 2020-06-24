package com.java.organizer_suite.server.core.model;

import java.time.Instant;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.java.organizer_suite.server.core.messaging.MessagingInstantConverter;

@Entity
@Table(name = "messaging")
public class Messaging extends BaseModel {

	private static final long serialVersionUID = -4735512250145370871L;
	
	@Size(min=0, max=255)
	private String text = "";
	@Convert(converter = MessagingInstantConverter.class)
	private Instant creationDate = null;
	@Min(0)
	@NotNull(message = "Recipient is mandatory")
	private Long recipient = null;
	@NotNull(message = "Sender is mandatory")
	@Min(0)
	private Long sender = null;
	
	public Messaging() {
		super();
		this.creationDate = Instant.now();
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Instant getCreationDate() {
		return this.creationDate;
	}
	
	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
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
