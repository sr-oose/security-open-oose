package de.oose.rest;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.oose.config.SecurityContextUtils;

@RestController
@RequestMapping("/api")
public class FrontendRestController {
	
	@GetMapping(path = "/username")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public ResponseEntity<String> getAuthorizedUserName() {

		return ResponseEntity.ok(SecurityContextUtils.getUserName());
	}

	@GetMapping(path = "/roles")
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	public ResponseEntity<Set<String>> getAuthorizedUserRoles() {
		return ResponseEntity.ok(SecurityContextUtils.getUserRoles());
	}
}