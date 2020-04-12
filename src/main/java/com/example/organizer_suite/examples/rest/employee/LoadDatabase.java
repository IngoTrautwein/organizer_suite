package com.example.organizer_suite.examples.rest.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration

/**
 * Bevor Lombok genutzt werden kann, muss die Installation durchgeführt werden.
 * ~/.m2/repository/org/projectlombok/lombok/1.12.6/lombok-1.12.6.jar --> Version anpassen!
 * dann das Eclipse-Verzeichnis auswählen und Eclipse neustarten.
 * Leider nicht bestandteil der Dokumententation:
 * https://spring.io/guides/tutorials/rest/
 * 
 * Andere Dependency wie MongoDB/MySQL müssen entfernt werden, da sonst JPA auf diese Treiber zurückgreift.
 * 
 * @author IngoTrautwein
 *
 */
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo", "Baggins", "burglar")));
			log.info("Preloading " + repository.save(new Employee("Frodo", "Baggins", "thief")));
		};
	}
}