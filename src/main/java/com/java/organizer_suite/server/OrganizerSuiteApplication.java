package com.java.organizer_suite.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sollte (unter Windows) der Port belegt sein, kann durch den Befehl in der
 * Powershell der Prozess identifiziert werden:
 * 
 * --> port number: 8080 (Standardport) Get-Process -Id (Get-NetTCPConnection
 * -LocalPort portNumber).OwningProcess
 * 
 * Manchmal läuft der Webserverb einer Exception weiter, obwohl in der IDE das
 * nicht angezeigt wird.
 * 
 * @author IngoTrautwein
 *
 */
@SpringBootApplication
public class OrganizerSuiteApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(OrganizerSuiteApplication.class);
	
	public static void main(String... args) {
		LOGGER.info("Run Application");
		SpringApplication.run(OrganizerSuiteApplication.class, args);
	}
}