package com.example.organizer_suite.rest.user;

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

import com.example.organizer_suite.rest.messaging.MessagingRepository;
import com.example.organizer_suite.server.core.model.Messaging;
import com.example.organizer_suite.server.core.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private final UserRepository repository;
	@Autowired
	private final MessagingRepository messagingRepository;
	@Autowired
	private final UserModelAssembler assembler;

	UserServiceImpl(UserRepository repository, MessagingRepository messagingRepository, UserModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
		this.messagingRepository = messagingRepository;
	}

	@Override
	public CollectionModel<EntityModel<User>> getAll() {
		List<EntityModel<User>> users = repository.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}

	@Override
	public CollectionModel<EntityModel<User>> getAllByFirstname(String firstname) {
		List<EntityModel<User>> users = repository.findByFirstname(firstname).stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}

	@Override
	public CollectionModel<EntityModel<User>> getAllBySurname(String surname) {
		List<EntityModel<User>> users = repository.findBySurname(surname).stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}

	@Override
	public ResponseEntity<?> create(User newUser) throws URISyntaxException {
		EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@Override
	public EntityModel<User> getById(Long id) {
		User employee = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		return assembler.toModel(employee);
	}

	@Override
	public ResponseEntity<?> replace(User newUser, Long id) throws URISyntaxException {
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

	@Override
	public ResponseEntity<?> delete(Long id) {
		/**
		 * Es fehlt eine Exception, wenn kein Datensatz gefunden wird. Aktuell wird ein
		 * Servercode 500 zurückgeliefert
		 */
		List<Messaging> messagingSender = messagingRepository.findBySender(id);
		messagingSender.forEach(messaging -> {
			messagingRepository.deleteById(messaging.getId());
		});
		
		List<Messaging> messagingRecipient = messagingRepository.findByRecipient(id);
		messagingRecipient.forEach(messaging -> {
			messagingRepository.deleteById(messaging.getId());
		});
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
