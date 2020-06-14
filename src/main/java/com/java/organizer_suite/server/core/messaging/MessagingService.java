package com.java.organizer_suite.server.core.messaging;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.java.organizer_suite.server.Service;
import com.java.organizer_suite.server.core.model.Messaging;

public interface MessagingService extends Service<Messaging> {

	CollectionModel<EntityModel<Messaging>> getAllSent(Long id);

	CollectionModel<EntityModel<Messaging>> getAllReceived(Long id);

}
