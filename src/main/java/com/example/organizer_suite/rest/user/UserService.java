package com.example.organizer_suite.rest.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.example.organizer_suite.Service;
import com.example.organizer_suite.server.core.model.User;

public interface UserService extends Service<User> {

	CollectionModel<EntityModel<User>> getAllByFirstname(String firstname);

	CollectionModel<EntityModel<User>> getAllBySurname(String surname);

}
