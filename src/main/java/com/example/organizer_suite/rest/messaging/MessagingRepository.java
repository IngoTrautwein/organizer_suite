package com.example.organizer_suite.rest.messaging;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.organizer_suite.server.core.model.Messaging;

public interface MessagingRepository extends JpaRepository<Messaging, Long> {

}