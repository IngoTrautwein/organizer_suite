package com.java.organizer_suite.server.core.messaging;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.organizer_suite.server.core.messaging.exceptions.MessagingNotFoundException;
import com.java.organizer_suite.server.core.model.Messaging;
import com.java.organizer_suite.server.core.user.UserRepository;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final MessagingRepository repository;
	@Autowired
	private final MessagingModelAssembler assembler;
	
	MessagingServiceImpl(UserRepository userRepository, MessagingRepository repository, MessagingModelAssembler assembler) {
		this.userRepository = userRepository;
		this.assembler = assembler;
		this.repository = repository;
	}
	
	@Override
	public CollectionModel<EntityModel<Messaging>> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> create(Messaging obj) throws URISyntaxException {
		EntityModel<Messaging> entityModel = assembler.toModel(repository.save(obj));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@Override
	public EntityModel<Messaging> getById(Long id) {
		Messaging messaging = repository.findById(id).orElseThrow(() -> new MessagingNotFoundException(id));

		return assembler.toModel(messaging);
	}

	@Override
	public ResponseEntity<?> replace(Messaging newObj, Long id) throws URISyntaxException {

		Messaging updatedMessaging = repository.findById(id).map(messaging -> {
			messaging.setText(newObj.getText());
			return repository.save(messaging);
		}).orElseGet(() -> {
			newObj.setId(id);
			return repository.save(newObj);
		});

		EntityModel<Messaging> entityModel = assembler.toModel(updatedMessaging);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	
	public CollectionModel<EntityModel<Messaging>> getAllSent(@PathVariable Long id) {
		List<EntityModel<Messaging>> messagings = repository.findBySender(id).stream().map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(messagings, linkTo(methodOn(MessagingController.class).allSent(id)).withSelfRel());
	}
	
	public CollectionModel<EntityModel<Messaging>> getAllReceived(@PathVariable Long id) {
		List<EntityModel<Messaging>> messagings = repository.findByRecipient(id).stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(messagings,
				linkTo(methodOn(MessagingController.class).allReceived(id)).withSelfRel());
	}

}
