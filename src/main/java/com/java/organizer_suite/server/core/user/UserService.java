package com.java.organizer_suite.server.core.user;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import com.java.organizer_suite.server.Service;
import com.java.organizer_suite.server.core.model.User;

public interface UserService extends Service<User> {

	CollectionModel<EntityModel<User>> getAllByFirstname(String firstname);

	CollectionModel<EntityModel<User>> getAllBySurname(String surname);

}
