package com.example.organizer_suite.rest.messaging;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.organizer_suite.server.core.model.Messaging;

public interface MessagingRepository extends JpaRepository<Messaging, Long> {

	List<Messaging> findBySender(@Param("Sender") Long Id);
	
	List<Messaging> findByRecipient(@Param("Recipient") Long Id);
}