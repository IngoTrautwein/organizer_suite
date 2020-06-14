package com.java.organizer_suite.server.core.messaging.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class MessagingNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(MessagingNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String userNotFoundHandler(MessagingNotFoundException ex) {
    return ex.getMessage();
  }
}