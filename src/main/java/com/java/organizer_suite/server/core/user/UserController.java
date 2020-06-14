package com.java.organizer_suite.server.core.user;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.organizer_suite.server.core.model.User;

@RestController
@RequestMapping(path = "/user")
class UserController {

	@Autowired
	private final UserService userService;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	UserController(UserService userService) {
		LOGGER.info("Instantiate UserController");
		this.userService = userService;
	}

	@GetMapping(path = "/all")
	@ResponseBody
	CollectionModel<EntityModel<User>> all() {
		return userService.getAll();
	}

	@GetMapping(path = "/allByFirstname/{firstname}")
	@ResponseBody
	CollectionModel<EntityModel<User>> allByFirstname(@PathVariable String firstname) {
		return userService.getAllByFirstname(firstname);
	}

	@GetMapping(path = "/allBySurname/{surname}")
	@ResponseBody
	CollectionModel<EntityModel<User>> allBySurname(@PathVariable String surname) {
		return userService.getAllBySurname(surname);
	}

	@PostMapping(path = "/add")
	@ResponseBody
	ResponseEntity<?> newUser(@Valid @RequestBody User newUser) throws URISyntaxException {
		return userService.create(newUser);
	}

	@GetMapping(path = "/{id}")
	@ResponseBody
	EntityModel<User> one(@PathVariable Long id) {
		return userService.getById(id);
	}

	@PutMapping(path = "/{id}")
	@ResponseBody
	ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) throws URISyntaxException {
		return userService.replace(newUser, id);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseBody
	ResponseEntity<?> deleteUser(@PathVariable Long id) {
		return userService.delete(id);
	}
	
}