package com.java.organizer_suite.server.core.messaging;

import java.net.URISyntaxException;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import com.java.organizer_suite.server.core.model.Messaging;

public class MessagingServiceImpl implements MessagingService {

	@Override
	public CollectionModel<EntityModel<Messaging>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> create(Messaging obj) throws URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityModel<Messaging> getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> replace(Messaging newObj, Long id) throws URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
