package com.example.organizer_suite.rest.messaging;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.organizer_suite.server.core.model.Messaging;

@Component
class MessagingModelAssembler implements RepresentationModelAssembler<Messaging, EntityModel<Messaging>> {

	@Override
	public EntityModel<Messaging> toModel(Messaging messaging) {
		return EntityModel.of(messaging,
				linkTo(methodOn(MessagingController.class).one(messaging.getId())).withSelfRel(),
				linkTo(methodOn(MessagingController.class).allSent(messaging.getSender())).withRel("sender"),
				linkTo(methodOn(MessagingController.class).allReceived(messaging.getRecipient())).withRel("recipient"));
	}
}