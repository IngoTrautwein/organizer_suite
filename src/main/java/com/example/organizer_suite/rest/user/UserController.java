package com.example.organizer_suite.rest.user;

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

import com.example.organizer_suite.server.core.model.User;

@RestController
class UserController {

	private final UserRepository repository;
	private final UserModelAssembler assembler;

	UserController(UserRepository repository, UserModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root

	@GetMapping("/users")
	CollectionModel<EntityModel<User>> all() {
		List<EntityModel<User>> users = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return new CollectionModel<>(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}

	@PostMapping("/users")
	ResponseEntity<?> newUser(@RequestBody User newUser) throws URISyntaxException {

		EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	// Single item

	@GetMapping("/users/{id}")
	EntityModel<User> one(@PathVariable Long id) {
		User employee = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		return assembler.toModel(employee);
	}

	@PutMapping("/users/{id}")
	ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id)
			throws URISyntaxException {

		User updatedUser = repository.findById(id).map(employee -> {
			employee.setFirstName(newUser.getFirstName());
			employee.setSurname(newUser.getSurname());
			return repository.save(employee);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});

		EntityModel<User> entityModel = assembler.toModel(updatedUser);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping("/users/{id}")
	ResponseEntity<?> deleteUser(@PathVariable Long id) {
		/**
		 * Es fehlt eine Exception, wenn kein Datensatz gefunden wird. 
		 * Aktuell wird ein Servercode 500 zurückgeliefert
		 */
	  repository.deleteById(id);

	  return ResponseEntity.noContent().build();
	}
}