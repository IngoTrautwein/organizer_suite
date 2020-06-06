package com.example.organizer_suite.rest.messaging;

class MessagingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1684383016864204435L;

	MessagingNotFoundException(Long id) {
		super("Could not find messaging " + id);
	}
}