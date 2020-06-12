package com.example.organizer_suite;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Test {
	
	
	public static void main(String[] args) {
		log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
	}
}
