package com.example.organizer_suite.examples.rest.order;

public class OrderNotFoundException extends RuntimeException {
	OrderNotFoundException(Long id) {
		super("Could not find order " + id);
	}
}
