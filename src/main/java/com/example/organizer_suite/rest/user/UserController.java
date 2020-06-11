package com.example.organizer_suite.rest.user;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.organizer_suite.server.core.model.User;


@RestController
@RequestMapping(path="/user")
class UserController {

	@Autowired
	private final UserRepository repository;
	@Autowired
	private final UserModelAssembler assembler;

	UserController(UserRepository repository, UserModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	
	@GetMapping(path="/all")
	@ResponseBody CollectionModel<EntityModel<User>> all() {
		List<EntityModel<User>> users = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}
	
	@GetMapping(path="/allByFirstname/{firstname}")
	@ResponseBody CollectionModel<EntityModel<User>> allByFirstname(@PathVariable String firstname) {
		List<EntityModel<User>> users = repository.findByFirstname(firstname).stream().map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}

	@GetMapping(path="/allBySurname/{surname}")
	@ResponseBody CollectionModel<EntityModel<User>> allBySurname(@PathVariable String surname) {
		List<EntityModel<User>> users = repository.findBySurname(surname).stream().map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}
	
	@PostMapping(path="/add")
	@ResponseBody ResponseEntity<?> newUser(@RequestBody User newUser) throws URISyntaxException {

		EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	// Single item

	@GetMapping(path="/{id}")
	@ResponseBody EntityModel<User> one(@PathVariable Long id) {
		User employee = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		return assembler.toModel(employee);
	}

	@PutMapping(path="/{id}")
	@ResponseBody ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id)
			throws URISyntaxException {

		User updatedUser = repository.findById(id).map(employee -> {
			employee.setFirstname(newUser.getFirstname());
			employee.setSurname(newUser.getSurname());
			return repository.save(employee);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});

		EntityModel<User> entityModel = assembler.toModel(updatedUser);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@DeleteMapping(path="{id}")
	@ResponseBody ResponseEntity<?> deleteUser(@PathVariable Long id) {
		/**
		 * Es fehlt eine Exception, wenn kein Datensatz gefunden wird. 
		 * Aktuell wird ein Servercode 500 zurückgeliefert
		 */
	  repository.deleteById(id);

	  return ResponseEntity.noContent().build();
	}
}