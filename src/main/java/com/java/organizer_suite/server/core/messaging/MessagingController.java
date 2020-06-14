package com.java.organizer_suite.server.core.messaging;

import java.net.URISyntaxException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.organizer_suite.server.core.model.Messaging;

@RestController
@RequestMapping(path = "/user/{id}/messaging")
class MessagingController {

	@Autowired
	private final MessagingService service;

	MessagingController(MessagingService service) {
		this.service = service;
	}

	// Aggregate root

	@GetMapping("/allSent")
	CollectionModel<EntityModel<Messaging>> allSent(@PathVariable Long id) {
		return service.getAllSent(id);
	}

	@GetMapping("/allReceived")
	CollectionModel<EntityModel<Messaging>> allReceived(@PathVariable Long id) {
		return service.getAllReceived(id);
	}

	@PostMapping("/add")
	ResponseEntity<?> newMessaging(@RequestBody Messaging newMessaging) throws URISyntaxException {
		return service.create(newMessaging);
	}

	// Single item

	@GetMapping("/{id}")
	EntityModel<Messaging> one(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	ResponseEntity<?> replaceMessaging(@RequestBody Messaging newMessaging, @PathVariable Long id)
			throws URISyntaxException {
		return service.replace(newMessaging, id);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteMessaging(@PathVariable Long id) {
		/**
		 * Es fehlt eine Exception, wenn kein Datensatz gefunden wird. Aktuell wird ein
		 * Servercode 500 zurückgeliefert
		 */
		return service.delete(id);
	}
}