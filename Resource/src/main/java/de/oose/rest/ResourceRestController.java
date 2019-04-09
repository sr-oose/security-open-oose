package de.oose.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceRestController {

	
	@GetMapping("/resource")
	public ResponseEntity<String> ressource() {
		return ResponseEntity.ok("Ressource successfully received");
	}
}
