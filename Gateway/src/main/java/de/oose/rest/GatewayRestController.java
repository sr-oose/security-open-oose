package de.oose.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayRestController {
	@GetMapping("/echo/{input}")
	ResponseEntity<String> echo(@PathVariable String input){
		return ResponseEntity.ok(input);
	}
	
}
