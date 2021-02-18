package de.oose;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ResourceRestController {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private RestTemplate restTemplate;
	
	private final String dataServerUri;
	
	public ResourceRestController(@Value("${rest.data-server-uri}") String dataServerUri, RestTemplate restTemplate) {
		this.dataServerUri = dataServerUri;
		this.restTemplate = restTemplate;
	}

	@GetMapping("/resource/user")
	public ResponseEntity<String> user(@RequestHeader(AUTHORIZATION_HEADER) String authTokenString) {
		var response = restTemplate.exchange(dataServerUri, HttpMethod.GET, null, Integer.class);
		if (response.getStatusCodeValue() >= 200 && response.getStatusCodeValue() < 300) {
			return ResponseEntity.ok("Received number: " + response.getBody());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting data.");
	}

	@GetMapping("/resource/admin")
	@PreAuthorize("hasRole('ROLE_admin')")
	public ResponseEntity<String> admin() {
		return ResponseEntity.ok("Admin access allowed.");
	}
}
