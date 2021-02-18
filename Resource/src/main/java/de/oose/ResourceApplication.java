package de.oose;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.Logbook;

@SpringBootApplication
public class ResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
		
	/** 
	 * Log all headers
	 */
	@Bean
	public Logbook makeLogBook() {
		return Logbook.builder().headerFilter(h -> h).build();
	}
	
}
