package com.example.organizer_suite.rest.messaging;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.organizer_suite.server.core.model.Messaging;

@RestController
class MessagingController {

	private final MessagingRepository repository;
	private final MessagingModelAssembler assembler;

	MessagingController(MessagingRepository repository, MessagingModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	
	@GetMapping("/messagings")
	CollectionModel<EntityModel<Messaging>> all() {
		List<EntityModel<Messaging>> messagings = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(messagings, linkTo(methodOn(MessagingController.class).all()).withSelfRel());
	}

	@PostMapping("/messagings")
	ResponseEntity<?> newMessaging(@RequestBody Messaging newMessaging) throws URISyntaxException {

		EntityModel<Messaging> entityModel = assembler.toModel(repository.save(newMessaging));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	// Single item

	@GetMapping("/messagings/{id}")
	EntityModel<Messaging> one(@PathVariable Long id) {
		Messaging messaging = repository.findById(id).orElseThrow(() -> new MessagingNotFoundException(id));

		return assembler.toModel(messaging);
	}

	@PutMapping("/messagings/{id}")
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

	@DeleteMapping("/messagings/{id}")
	ResponseEntity<?> deleteMessaging(@PathVariable Long id) {
		/**
		 * Es fehlt eine Exception, wenn kein Datensatz gefunden wird. 
		 * Aktuell wird ein Servercode 500 zurückgeliefert
		 */
	  repository.deleteById(id);

	  return ResponseEntity.noContent().build();
	}
}