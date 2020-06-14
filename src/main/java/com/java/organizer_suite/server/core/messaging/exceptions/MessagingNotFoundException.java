package com.java.organizer_suite.server.core.messaging.exceptions;

public class MessagingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1684383016864204435L;

	public MessagingNotFoundException(Long id) {
		super("Could not find messaging " + id);
	}
}