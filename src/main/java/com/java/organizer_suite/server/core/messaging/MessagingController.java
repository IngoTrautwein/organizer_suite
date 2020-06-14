package com.java.organizer_suite.server.core.messaging;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.organizer_suite.server.core.messaging.exceptions.MessagingNotFoundException;
import com.java.organizer_suite.server.core.model.Messaging;

@RestController
@RequestMapping(path="/user/{id}/messaging")
class MessagingController {

	@Autowired
	private final MessagingRepository repository;
	@Autowired
	private final MessagingModelAssembler assembler;

	MessagingController(MessagingRepository repository, MessagingModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	
	@GetMapping("/allSent")
	CollectionModel<EntityModel<Messaging>> allSent(@PathVariable Long id) {
		List<EntityModel<Messaging>> messagings = repository.findBySender(id).stream().map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(messagings, linkTo(methodOn(MessagingController.class).allSent(id)).withSelfRel());
	}
	
	@GetMapping("/allReceived")
	CollectionModel<EntityModel<Messaging>> allReceived(@PathVariable Long id) {
		List<EntityModel<Messaging>> messagings = repository.findByRecipient(id).stream().map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(messagings, linkTo(methodOn(MessagingController.class).allReceived(id)).withSelfRel());
	}

	@PostMapping("/add")
	ResponseEntity<?> newMessaging(@RequestBody Messaging newMessaging) throws URISyntaxException {

		EntityModel<Messaging> entityModel = assembler.toModel(repository.save(newMessaging));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	// Single item

	@GetMapping("/{id}")
	EntityModel<Messaging> one(@PathVariable Long id) {
		Messaging messaging = repository.findById(id).orElseThrow(() -> new MessagingNotFoundException(id));

		return assembler.toModel(messaging);
	}

	@PutMapping("/{id}")
	ResponseEntity<?> replaceMessaging(@RequestBody Messaging newMessaging, @PathVariable Long id)
			throws URISyntaxException {

		Messaging updatedMessaging = repository.findById(id).map(messaging -> {
			messaging.setText(newMessaging.getText());
			return repository.save(messaging);
		}).orElseGet(() -> {
			newMessaging.setId(id);
			return repository.save(newMessaging);
		});

		EntityModel<Messaging> entityModel = assembler.toModel(updatedMessaging);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteMessaging(@PathVariable Long id) {
		/**
		 * Es fehlt eine Exception, wenn kein Datensatz gefunden wird. 
		 * Aktuell wird ein Servercode 500 zurückgeliefert
		 */
	  repository.deleteById(id);

	  return ResponseEntity.noContent().build();
	}
}