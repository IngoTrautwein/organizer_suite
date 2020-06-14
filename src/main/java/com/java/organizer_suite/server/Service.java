package com.java.organizer_suite.server;

import java.net.URISyntaxException;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface Service<T> {

	CollectionModel<EntityModel<T>> getAll();

	ResponseEntity<?> create(T obj) throws URISyntaxException;

	EntityModel<T> getById(Long id);

	ResponseEntity<?> replace(T newObj, @PathVariable Long id) throws URISyntaxException;

	ResponseEntity<?> delete(Long id);

}
