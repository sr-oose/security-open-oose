package de.oose.rest;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataRestController {
	
	private Random random = new Random(7L);

	@GetMapping("/data")
	@PreAuthorize("hasRole('ROLE_user')")
	public ResponseEntity<Integer> getRandomNumber() {
		return ResponseEntity.ok(random.nextInt(1000));
	}
}
