package com.java.organizer_suite.server.core.messaging;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MessagingInstantConverter implements AttributeConverter<Instant, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(Instant date) {
		if (date == null) {
			return null;
		}

		Timestamp timestamp = Timestamp.from(date);

		return timestamp;
	}

	@Override
	public Instant convertToEntityAttribute(Timestamp dbTimestamp) {
		if (dbTimestamp == null) {
			return null;
		}

		Instant instant = dbTimestamp.toInstant();

		return instant;
	}

}
