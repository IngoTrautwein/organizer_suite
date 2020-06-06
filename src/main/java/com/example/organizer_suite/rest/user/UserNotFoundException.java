package com.example.organizer_suite.rest.user;

class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1684383016864204435L;

	UserNotFoundException(Long id) {
		super("Could not find user " + id);
	}
}