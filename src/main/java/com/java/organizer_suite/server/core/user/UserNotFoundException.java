package com.java.organizer_suite.server.core.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1684383016864204435L;

	UserNotFoundException() {
        super();
    }
	
	UserNotFoundException(Long id) {
		super("Could not find user " + id);
	}
	
    UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}