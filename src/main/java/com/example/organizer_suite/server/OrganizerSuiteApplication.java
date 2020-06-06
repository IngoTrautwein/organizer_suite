package com.example.organizer_suite.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
public class OrganizerSuiteApplication {

  public static void main(String... args) {
    SpringApplication.run(OrganizerSuiteApplication.class, args);
  }
}