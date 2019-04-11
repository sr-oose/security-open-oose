package de.oose.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceRestController {

	
	@GetMapping("/resource/user")
	public ResponseEntity<String> user() {
		return ResponseEntity.ok("User access allowed.");
	}
	
	@GetMapping("/resource/admin")
	@PreAuthorize("hasRole('admin')")
	public ResponseEntity<String> admin() {
		return ResponseEntity.ok("Admin access allowed.");
	}
}
