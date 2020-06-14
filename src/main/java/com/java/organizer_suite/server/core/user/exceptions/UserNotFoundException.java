package com.java.organizer_suite.server.core.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1684383016864204435L;

	public UserNotFoundException() {
        super();
    }
	
	public UserNotFoundException(Long id) {
		super("Could not find user " + id);
	}
	
	public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}