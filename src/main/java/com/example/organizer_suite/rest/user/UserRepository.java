package com.example.organizer_suite.rest.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.organizer_suite.server.core.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByFirstname(@Param("firstname") String firstname);
	
	List<User> findBySurname(@Param("surname") String surname);
	
}