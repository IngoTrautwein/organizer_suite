package com.example.organizer_suite.rest.user;


class UserNotFoundException extends RuntimeException {

  UserNotFoundException(Long id) {
    super("Could not find user " + id);
  }
}