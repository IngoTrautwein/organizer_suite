package com.example.organizer_suite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Sollte (unter Windows) der Port belegt sein, kann durch den Befehl in der Powershell der Prozess identifiziert werden:
 * 
 * --> port number: 8080 (Standardport)
 * Get-Process -Id (Get-NetTCPConnection -LocalPort portNumber).OwningProcess
 * 
 * Manchmal läuft der Webserverb einer Exception weiter, obwohl in der IDE das nicht angezeigt wird.
 * @author IngoTrautwein
 *
 */
@SpringBootApplication
//@ComponentScan(basePackageClasses = UserController.class)
public class OrganizerSuiteApplication {

  public static void main(String... args) {
    SpringApplication.run(OrganizerSuiteApplication.class, args);
  }
}