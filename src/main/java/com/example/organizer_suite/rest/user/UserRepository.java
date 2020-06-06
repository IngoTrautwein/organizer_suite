package com.example.organizer_suite.rest.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organizer_suite.server.core.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}